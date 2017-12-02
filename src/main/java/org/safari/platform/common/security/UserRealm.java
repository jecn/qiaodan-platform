package org.safari.platform.common.security;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.security.shiro.session.SessionDAO;
import org.safari.platform.common.servlet.ValidateCodeServlet;
import org.safari.platform.common.utils.PropertiesUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.utils.security.Encodes;
import org.safari.platform.modules.sys.entity.LogLogin;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.service.LogLoginService;
import org.safari.platform.modules.sys.service.UserService;
import org.safari.platform.modules.sys.utils.LogUtil;
import org.safari.platform.modules.sys.utils.UserUtil;
import org.safari.platform.modules.sys.web.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统安全认证实现类:身份验证和授权
 * @author Alitalk
 * @date 2017-02-15
 */
@Service
public class UserRealm extends AuthorizingRealm {

	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LogLoginService mlogLoginService;
	
	@Autowired
	private UserService muserService;
	
	@Autowired
	private SessionDAO sessionDao;
	
	/**
	 * 身份验证，认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		String message = null;
		AuthenticationInfo info = null;
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		int activeSessionSize = sessionDao.getActiveSessions(false).size();
		if (LOG.isDebugEnabled()){
			LOG.debug("login submit, active session size: {}, username: {}", activeSessionSize, token.getUsername());
		}
		
		try {
			// 校验登录验证码
			if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)){
				Session session = UserUtil.getSession();
				String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
				if (null == token.getCaptcha() || !token.getCaptcha().toUpperCase().equals(code)){
					message = "验证码为空或错误, 请重试";
					throw new AuthenticationException("msg:验证码错误, 请重试.");
				}
			}
			
			// 校验用户名密码
			User user = muserService.findByUsername(token.getUsername());
			if (user != null) {
				if (Global.LOGIN_LIMITED.equals(user.getStat())){
					message = "该帐号禁止登录";
					throw new AuthenticationException("msg:该帐号禁止登录.");
				}
				
				if (Global.LOGIN_PAUSE.equals(user.getStat())){
					message = "该帐号暂停使用";
					throw new AuthenticationException("msg:该帐号暂停使用.");
				}
				
				byte[] salt = Encodes.decodeHex(user.getSalt());
				info = new SimpleAuthenticationInfo(new Principal(user, token.isMobileLogin()), 
							user.getPassword(), ByteSource.Util.bytes(salt), getName());
				
			} else {
				message = "该帐号未注册或已注销";
			}
		}finally{
			LogLogin log = new LogLogin();
			log.setUsername(token.getUsername());
			log.setIp(token.getHost());
			if(!StringUtil.isEmpty(message)){
				log.setStat(Global.NO);
				log.setMsg(message);
			}else{
				log.setStat(Global.YES);
			}
			
			//放到一公共变量里，定时提交
			LogUtil.saveLog(log);
		}
		
		return info;
	}

	/**
	 * 授权，授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		
		// 获取当前已登录的用户是否多账号同时登录
		if (!PropertiesUtil.getBoolean("user.multiAccountLogin")){  //不允许多账号同时登录
			Collection<Session> sessions = sessionDao.getActiveSessions(true, principal, UserUtil.getSession());
			if (sessions.size() > 0){
				// 如果是登录进来的，则踢出已在线用户
				if (UserUtil.getSubject().isAuthenticated()){
					for (Session session : sessions){
						sessionDao.delete(session);
					}
				}else{
					// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
					UserUtil.getSubject().logout();
					throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
				}
			}
		}
		
		User user = muserService.findByUsername(principal.getUsername());
		if (null != user) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			
			/*
				List<Privilege> list = UserUtil.getPrivilegeList();
				for (Privilege privilege : list){
					if (StringUtil.isNotBlank(privilege.getPermission())){
						// 添加基于Permission的权限信息
						for (String permission : StringUtil.split(privilege.getPermission(),",")){
							info.addStringPermission(permission);
						}
					}
				}
			*/
			
			// 添加用户权限
			info.addStringPermission("user");
			
			// 添加用户角色信息
			for (Role role : user.getRoleList()){
				info.addRole(role.getEname());
			}
			
			return info;
		} else {
			return null;
		}
	}
	
	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}
	
	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
        		authorizationValidate(permission);
            }
        }
		return super.isPermitted(permissions, info);
	}
	
	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}
	
	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
            	authorizationValidate(permission);
            }
        }
		return super.isPermittedAll(permissions, info);
	}
	
	/**
	 * 授权验证方法
	 * @param permission
	 */
	private void authorizationValidate(Permission permission){
		// 模块授权预留接口
	}
	
	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Global.SHIRO_HASH_ALGORITHM);
		matcher.setHashIterations(Global.SHIRO_HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	
	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(Principal principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	
}
