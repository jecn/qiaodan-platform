package org.safari.platform.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: 正则表达式验证</p>
 * <p>Description: </p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author: zhuzhiwang
 * @date: 2014-5-15
 */
public class RegexUtil {

	/*  
	    ^ 必须以什么开头
		$ 必须以什么结尾
		* 前面字符有0个或多个
		？ 前面字符有0个或1个
		+ 前面字符有1个或多个
	*/
	private static final String rnum= "^(([+]?)|([-]{1}))?(([1-9]{1}[0-9]*((\\.{0}[0-9]*)|(\\.{1}[0-9]+)))|(([0]{1}\\.{1}[0-9]+)|([0]{1})))*$"; //验证有理数
	private static final String prnum = "^([+]?)(([1-9]{1}[0-9]*((\\.{0}[0-9]*)|(\\.{1}[0-9]+)))|(([0]{1}\\.{1}[0-9]+)|([0]{1})))*$"; //验证非负有理数
	private static final String nrnum = "^-(([1-9]{1}[0-9]*((\\.{0}[0-9]*)|(\\.{1}[0-9]+)))|(([0]{1}\\.{1}[0-9]+)|([0]{1})))*$"; //验证非正有理数
	private static final String num = "^(([+]?)|([-]{1}))(0|[1-9][0-9]*)?$";    //验证整数
	private static final String pnum = "^[+]?([1-9][0-9]*)?$";   //验证正整数
	private static final String nnum = "^[+]?(0|[1-9][0-9]*)?$"; //验证非负整数
	private static final String mnum = "^-([1-9][0-9]*)?$";    //验证负整数
	private static final String anum = "^(-(0|[1-9][0-9]*)?)|(0)$";   //验证非正整数
	private static final String afloat = "^-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$" ; //验证非正浮点数
	private static final String nfloat = "^[+]{0,1}([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";    //验证非负浮点数字
	private static final String tellphone = "^[+]{0,1}(\\(\\d{3,4}\\)|\\d{3,4}-{0,1})?\\d{7,8}$";    //验证电话号码  区号与座机号必须以-分割
	private static final String cellphone = "^((13[0-9])|(147)|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$";        //验证手机号码
	private static final String email = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";    // 验证邮箱
	private static final String english = "^[A-Za-z]+$";  //验证英文字母
	private static final String benglish = "^[A-Z]+$"; //验证英文大写字母
	private static final String senglish = "^[a-z]+$"; //验证英文小写字母
	private static final String sinogram = "^[\u4e00-\u9fa5]+$";    //验证汉字
	private static final String numeng = "^[A-Za-z0-9]+$";  //验证数字与字母
	private static final String numbeng = "^[A-Z0-9]+$";    //验证数字与大写字母
	private static final String numseng = "^[a-z0-9]+$";    //验证数字与小写字母
	private static final String numchn = "^[0-9\\u4e00-\\u9fa5\\-]+$";    //验证数字、汉字、-
	private static final String engchn = "^[A-Za-z\\u4e00-\\u9fa5\\-]+$";    //验证字母、汉字、-
	private static final String bengchn = "^[A-Z\\u4e00-\\u9fa5\\-]+$";    //验证大写字母、汉字、-
	private static final String sengchn = "^[a-z\\u4e00-\\u9fa5\\-]+$";    //验证小写字母、汉字、-
	private static final String numengchn = "^[A-Za-z0-9\\u4e00-\\u9fa5\\-]+$";    //验证数字、字母、汉字、-
	private static final String numengchnmark = "^[A-Za-z0-9\\u4e00-\\u9fa5\\pP\\p{Punct}]*$"; //验证数字、字母、汉字、-、标点符号
	private static final String dates = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|"
									  + "(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468]"
									  + "[1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])"
									  + "|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))"
									  + "\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";         //验证日期
	
	private static final String idNum = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])"; 
	private static final String filter = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";     //过滤特殊符号
	private static final String ipv4 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."  
                                     + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";   //验证IPV4
	private static final String no = "^[A-Za-z0-9]{6,16}$";   //账号规则
	private static final String mark = "^((\\pP*)|(\\p{Punct}*))+$";   //标点符号  ^(\\p{P}*)|[-_——]*$
	private static final String nopic = "^[A-Za-z0-9\\u4e00-\\u9fa5\\`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{1,20}$";
	private static final String qq = "^[1-9][0-9]{5,16}$";  //qq账号规则
	
	/**
	 * 验证有理数
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isRnum(String numbers){
		Pattern p=Pattern.compile(rnum);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证非负有理数
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isPrnum(String numbers){
		Pattern p=Pattern.compile(prnum);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证非正有理数
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isNrnum(String numbers){
		Pattern p=Pattern.compile(nrnum);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证整数
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isNum(String numbers){
		Pattern p=Pattern.compile(num);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证正整数   可以以"+"开头
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isPnum(String numbers){
		Pattern p=Pattern.compile(pnum);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证非负整数   可以以"+"开头
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isNnum(String numbers){
		Pattern p=Pattern.compile(nnum);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证负整数  必须以"-"开头
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isMnum(String numbers){
		Pattern p=Pattern.compile(mnum);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证非正整数  必须以"-"开头
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isAnum(String numbers){
		Pattern p=Pattern.compile(anum);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证非正浮点数   必须以"-"开头
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isAfloat(String numbers){
		Pattern p=Pattern.compile(afloat);
		Matcher m=p.matcher(numbers);
		return m.matches();
	}
	
	/**
	 * 验证非负浮点数  可以以"+"开头
	 * @param numbers String
	 * @return boolean
	 */
	public static boolean isNfloat(String numbers){     
        Pattern p = Pattern.compile(nfloat);     
        Matcher m = p.matcher(numbers);     
        return m.matches();     
    } 
	
	/**
	 * 验证电话号码  可以以"+"开头   xxx-xxxxxxx   xxx-xxxxxxxx   xxxx-xxxxxxx  xxxx-xxxxxxxx  xxxxxxx  xxxxxxxx
	 * @param tellphones String
	 * @return boolean
	 */ 
	public static boolean isTellphone(String tellphones){
		Pattern p=Pattern.compile(tellphone);
		Matcher m=p.matcher(tellphones);
		return m.matches();
	}
	
	/**
	 * 验证手机号
	 * @param cellphones String
	 * @return boolean
	 */
	public static boolean isCellphone(String cellphones){     
        Pattern p = Pattern.compile(cellphone);     
        Matcher m = p.matcher(cellphones);     
        return m.matches();     
    } 
   
    /**
     * 验证电子邮箱
     * @param emails String
     * @return boolean
     */
    public static boolean isEmail(String emails){     
        Pattern p = Pattern.compile(email);     
        Matcher m = p.matcher(emails);     
        return m.matches();     
    } 
    
    /**
     * 验证日期   
     * 格式    xxxx/xx/xx   xxxx/xx/xx xx:xx:xx    xxxx-xx-xx    xxxx-xx-xx xx:xx:xx
     * @param date String
     * @return boolean
     */
    public static boolean isDate(String date){     
        Pattern p = Pattern.compile(dates);     
        Matcher m = p.matcher(date);     
        return m.matches();     
    } 
    
    /**
     * 英文字母
     * @param englishs String
     * @return boolean
     */
    public static boolean isEnglish(String englishs){
    	Pattern p=Pattern.compile(english);
    	Matcher m=p.matcher(englishs);
    	return m.matches();
    }
    
    /**
     * 大写英文字母
     * @param benglishs String
     * @return boolean
     */
    public static boolean isBenglish(String benglishs){
    	Pattern p=Pattern.compile(benglish);
    	Matcher m=p.matcher(benglishs);
    	return m.matches();
    }
    
    /**
     * 小写英文字母
     * @param senglishs String
     * @return boolean
     */
    public static boolean isSenglish(String senglishs){
    	Pattern p=Pattern.compile(senglish);
    	Matcher m=p.matcher(senglishs);
    	return m.matches();
    }
    
    /**
     * 必须汉字
     * @param sinograms String
     * @return boolean
     */
    public static boolean isSinogram(String sinograms){
    	Pattern p=Pattern.compile(sinogram);
    	Matcher m=p.matcher(sinograms);
    	return m.matches();
    }
    
    /**
     * 字母与数字的组合
     * @param numengs String
     * @return boolean
     */
    public static boolean isNumEnglish(String numengs){
    	Pattern p=Pattern.compile(numeng);
    	Matcher m=p.matcher(numengs);
    	return m.matches();
    }
    
    /**
     * 大写字母与数字的组合
     * @param numbengs String
     * @return boolean
     */
    public static boolean isNumBenglish(String numbengs){
    	Pattern p=Pattern.compile(numbeng);
    	Matcher m=p.matcher(numbengs);
    	return m.matches();
    }
    
    /**
     * 小写字母与数字的组合
     * @param numsengs String
     * @return boolean
     */
    public static boolean isNumSenglish(String numsengs){
    	Pattern p=Pattern.compile(numseng);
    	Matcher m=p.matcher(numsengs);
    	return m.matches();
    }
    
    /**
     * 字母、数字、- 组合
     * @param numengchns String
     * @return boolean
     */
    public static boolean isNumEnglishChinese(String numengchns){
    	Pattern p=Pattern.compile(numengchn);
    	Matcher m=p.matcher(numengchns);
    	return m.matches();
    }
    
    /**
     * 字母、数字、标点符号、- 组合
     * @param numengchns String
     * @return boolean
     */
    public static boolean isNumEnglishChineseMark(String numengchnmarks){
    	Pattern p=Pattern.compile(numengchnmark);
    	Matcher m=p.matcher(numengchnmarks);
    	return m.matches();
    }
    
	/**
	 * 数字、汉字、- 组合
	 * @param numchns String
	 * @return boolean
	 */
	public static boolean isNumChinese(String numchns){
		Pattern p=Pattern.compile(numchn);
		Matcher m=p.matcher(numchns);
		return m.matches();
	}
	
	/**
	 * 字母、汉字、- 组合
	 * @param engchns String
	 * @return boolean
	 */
	public static boolean isEnglishChinese(String engchns){
		Pattern p=Pattern.compile(engchn);
		Matcher m=p.matcher(engchns);
		return m.matches();
	}
	
	/**
	 * 大写字母、汉字、- 组合
	 * @param bengchns String
	 * @return boolean
	 */
	public static boolean isBenglishChinese(String bengchns){
		Pattern p=Pattern.compile(bengchn);
		Matcher m=p.matcher(bengchns);
		return m.matches();
	}
	
	/**
	 * 小写字母、汉字、- 组合
	 * @param sengchns String
	 * @return boolean
	 */
	public static boolean isSenglishChinese(String sengchns){
		Pattern p=Pattern.compile(sengchn);
		Matcher m=p.matcher(sengchns);
		return m.matches();
	}
	
	/**
	 * 判断是否符合身份证号规则
	 * @param idNums String
	 * @return boolean
	 */
	public static boolean isIdNum(String idNums){
		Pattern p=Pattern.compile(idNum);
		Matcher m=p.matcher(idNums);
		return m.matches();
	}
	
	/**
	 * 判断是否有特殊字符
	 * @param filters String
	 * @return boolean
	 */
	public static boolean isFilter(String filters){
		Pattern p=Pattern.compile(filter);
		Matcher m=p.matcher(filters);
		return m.find();
	}
	
	/**
	 * 过滤有特殊字符
	 * @param filters String
	 * @return String
	 */
	public static String filterStr(String filters){
		Pattern p=Pattern.compile(filter);
		Matcher m=p.matcher(filters);
		return m.replaceAll("").trim();
	}
	
    /**
     * 过滤出数字
     * @param number String
     * @return String
     */
	public static String filterNumber(String number){
	    number = number.replaceAll("[^(0-9)]", "");
	    return number;
	}
	  
    /**
     * 过滤出字母
     * @param alph String
     * @return String
     */
	public static String filterAlphabet(String alph){
	    alph = alph.replaceAll("[^(A-Za-z)]", "");
	    return alph;
	}
	 
	/**
	  * 过滤出中文
	  * @param chin String
	  * @return String
	  */
	public static String filterChinese(String chin){
	    chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
	    return chin;
	}
	
	/**
	  * 过滤出字母、数字和中文
	  * @param character String
	  * @return String
	  */
	public static String filter(String character){
	    character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
	    return character;
	}

	/**
	 * 全角转半角
	 * @param fullWidthStr 全角字符串
	 * @return String
	 */
	public static String fullWidth2halfWidth(String fullWidthStr) {
        if (null == fullWidthStr || fullWidthStr.length() <= 0) {
            return "";
        }
        char[] charArray = fullWidthStr.toCharArray();
        //对全角字符转换的char数组遍历
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = (int) charArray[i];
            //如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
            if (charIntValue >= 65281 && charIntValue <= 65374) {
                charArray[i] = (char) (charIntValue - 65248);
            } else if (charIntValue == 12288) {
                charArray[i] = (char) 32;
            }
        }
        return new String(charArray);
    }
	
	/**
	 * 验证IP地址
	 * @param ips String
	 * @return boolean
	 */
	public static boolean isIpv4(String ips){
		Pattern p = Pattern.compile(ipv4);
		Matcher m = p.matcher(ips);
		return m.matches();
	}
	
	/**
	 * 验证是否符合账号命名规则
	 * @param nos String
	 * @return boolean
	 */
	public static boolean isNo(String nos){
		Pattern p = Pattern.compile(no);
		Matcher m = p.matcher(nos);
		return m.matches();
	}
	
	/**
	 * 验证字符串是否符合指定长度
	 * @param str String
	 * @param len int
	 * @return boolean
	 */
	public static boolean isMatchLegth(String str,int len){
		int length = str.trim().length();
		if(length <= len){
			return true;
		}
		return false;
	}
	
	/**
     * 标点符号
     * @param marks String
     * @return boolean
     */
	public static boolean isMark(String marks){
		Pattern p = Pattern.compile(mark);
		Matcher m = p.matcher(marks);
		return m.matches();
	}
	
	/**
	 * 字母、数字、中文、标点符号
	 * @date 2015-7-16
	 * @param nopics String
	 * @return boolean
	 */
	public static boolean isNopic(String nopics){
		Pattern p = Pattern.compile(nopic);
		Matcher m = p.matcher(nopics);
		return m.matches();
	}
	
	/**
	 * QQ号
	 * @date 2015-7-16
	 * @param nopics String
	 * @return boolean
	 */
	public static boolean isQQ(String qqs){
		Pattern p = Pattern.compile(qq);
		Matcher m = p.matcher(qqs);
		return m.matches();
	}
	
}
