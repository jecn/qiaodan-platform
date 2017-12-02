var errFlag = 0;
var errMsg = {
	required : {
		msg : "必填选项!",
		test : function(obj) {
			return obj.value.length > 0;
		}
	},
	urlDomain : {
		msg : "域名不合法",
		test : function(obj) {
			return !obj.value || /^[^@-]+\.com(\.cn)?$/.test(obj.value);
		}
	},
	commonDomain : {
		msg : "域名不合法",
		test : function(obj) {
			return !obj.value
					|| /(^[A-Za-z0-9\u4e00-\u9fa5]+)((\-([A-Za-z0-9\u4e00-\u9fa5]+))?)(\.([A-Za-z0-9\u4e00-\u9fa5]+)((\-([A-Za-z0-9\u4e00-\u9fa5]+))?))+$/
							.test(obj.value);
		}
	},
	subDomain : {
		msg : "子域名不合法,域名首不能包含www.",
		test : function(obj) {
			return !obj.value
					|| /^(?:(?!(www\.))([A-Za-z0-9\u4e00-\u9fa5]+)((\-([A-Za-z0-9\u4e00-\u9fa5]+))?))(\.([A-Za-z0-9\u4e00-\u9fa5]+)((\-([A-Za-z0-9\u4e00-\u9fa5]+))?))+$/
							.test(obj.value);
		}
	},
	wapDomian : {
		msg : "WAP域名不合法",
		test : function(obj) {
			return !obj.value
					|| /^([A-Za-z0-9])+\.?([A-Za-z0-9])+$/.test(obj.value);
		}
	},
	mastId : {
		msg : "masId不合法",
		test : function(obj) {
			return !obj.value || /^M.{2}AH.{9}$/.test(obj.value);
		}
	},
	masStandard : {
		msg : "MAS标准代码不合法",
		test : function(obj) {
			return !obj.value || /^MAH\d{7}$/.test(obj.value);
		}
	},
	mas06Standard : {
		msg : "MAS标准代码不合法",
		test : function(obj) {
			return !obj.value || /^M06AH\d{9}$/.test(obj.value);
		}
	},
	lineSpeed : {
		msg : "带宽不合法",
		test : function(obj) {
			return !obj.value
					|| /^[2468]$|^[1-9][02468]$|^1[0-4][02468]|^15[024]$/
							.test(obj.value);
		}
	},
	scale : {
		msg : "比例输入不合法",
		test : function(obj) {
			var objArr = obj.value.split(":");
			if (objArr.length == 2
					&& parseInt(objArr[0]) + parseInt(objArr[1]) == 100) {
				return !obj.value
						|| /^([0-9]|[1-9][0-9]):([0-9]|[1-9][0-9])$/
								.test(obj.value);
			} else {
				return false;
			}
		}
	},
	HHmm : {
		msg : "时间格式为HHmm",
		test : function(obj) {
			return !obj.value
					|| /^([0-1][0-9]|[2][0-3])[0-5][0-9]$/.test(obj.value);
		}
	},
	shortNo : {
		msg : "短号格式以61-69开头",
		test : function(obj) {
			return !obj.value || /^[6][1-9]\d{4}$/.test(obj.value);
		}
	},
	email : {
		msg : "邮件地址不合法",
		test : function(obj) {
			return !obj.value	|| /^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$/.test(obj.value);
		}
	},
	dateFormat : {
		msg : "日期格式MM/DD/YYYY",
		test : function(obj) {
			return !obj.value || /^\d{2}\/\d{2}\/\d{2,4}$/.test(obj.value);
		}
	},
	url : {
		msg : "网址不合法。",
		test : function(obj) {
			return !obj.value
					|| /^(http|https|ftp):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/
							.test(obj.value);
		}
	},
	forMoney : {// vflag = 0 不能为负数,vflag = -1 不能为正数
		msg : [ "输入格式有误！", "金额不能为负数！", "金额不能为正数！" ],
		test : function(obj) {
			if (!obj.value)
				return true;
			var num = obj.value;
			var flag = obj.vflag;
			if (flag == null)
				flag = 0;
			num.toString().replace(/\$|\,/g, '');

			if (isNaN(num)) {
				return 0;
			}

			if (flag == 0 && num < 0) // vflag = 0 不能为负数
			{
				return 1;
			}
			if (flag == -1 && num > 0) // vflag = -1 不能为正数
			{
				return 2;
			}
			obj.value = formatAsMoney(num);
			return true;
			// 格式化金额
			function formatAsMoney(mnt) {
				mnt -= 0;
				mnt = (Math.round(mnt * 100)) / 100;
				return (mnt == Math.floor(mnt)) ? mnt + '.00'
						: ((mnt * 10 == Math.floor(mnt * 10)) ? mnt + '0' : mnt);
			}
		}
	},
	upLetter : {
		msg : "必须为大写英文字母",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[A-Z]+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				// obj.select();
				return false;
			}
			return true;
		}
	},
	lowLetter : {
		msg : "必须为小写英文字母",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[a-z]+$/
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	allLetter : {
		msg : "必须为英文",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[A-Za-z]+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	numOrLetter : {
		msg : "必须为英文或数字",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[A-Za-z0-9]+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	numAndLetter : {
		msg : "必须为数字和字母的混合",
		test : function(obj) {
			if (!obj.value)
				return true;
			var sInput = obj.value;
			if (sInput
					&& (!(/^([a-zA-Z]|[0-9]){0,}$/.test(sInput)
							&& /[a-zA-Z]{1}/.test(sInput) && /\d{1}/
							.test(sInput)))) {
				return false;
			}
			return true;
		}

	},
	numLetterChinese : {
		msg : "必须为英文,数字或汉字",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	numLetterChineseLineation : {
		msg : "必须为英文,数字或汉字-",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[A-Za-z0-9\u4e00-\u9fa5\-]+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	postCode : {
		msg : "邮政编码格式错误",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[0-9]{1}(\d){5}$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	commonPhone : {// 校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
		msg : "电话号码格式错误",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[+]{0,1}((0(\d{2}))?([-]{0,1})([1-9]\d{7})|(0(\d{3}))?([-]{0,1})([1-9]\d{6,7}))$/;
			var sInput = obj.value;
			if (obj.value && sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	andCellphone : {// 校验手机、固话、传真号码：可以“+”开头，除数字外，可含有“-”
		msg : "电话号码格式错误",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[+]{0,1}(\d{3,4})?([-]{0,1})?(\d{7,8})$/;
			// var patrn = /^[+]{0,1}(\d{3,4})([-])?(\d{7,8})$/; //不能限制长度，故修改为上面
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	cellPhone : {// 服务号码
		msg : "服务号码格式错误",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[^\u4e00-\u9fa5]{0,}$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	allCellPhone : {// 原手机号码验证
		msg : "手机号码格式错误",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^((\(\d{3}\))|(\d{3}\-))?[12][0358]\d{9}$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	forTelecom : {// 电信
		msg : "不是电信手机号码",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^1[358][39]\d{8}$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	forUnicom : {// 联通
		msg : "不是联通手机号码",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^1[35][0126]\d{8}$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	forMobile : {// 移动
		msg : "不是移动手机号码",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^\d{11}$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	ipAddress : {
		msg : "IP地址错误",
		test : function(obj) {
			if (!obj.value)
				return true;
			var pattern = /^([1-9]|[1-9]\d|(1[0-1|3-9]\d|12[0-6|8-9]|2[0-3]\d|24[0-7]))(\.(\d|[1-9]\d|(1\d{2}|2([0-4]\d|5[0-5])))){3}$/;
			var sInput = obj.value;
			if (sInput.search(pattern) == -1) {
				return false;
			}
			return true;
		}
	},
	num_letter : {
		msg : "必须为数字,英文和下划线",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^\w+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	isSizeOf : {// 需要自定义属性v_maxlength,v_minlength
		msg : [ "数值大小不在指定范围内", "必须为数字" ],
		test : function(obj) {
			if (!obj.value)
				return true;
			var maxval = parseFloat(obj.v_maxlength);
			var minval = parseFloat(obj.v_minlength);
			if (isNaN(obj.value)) {
				return 1;
			} else {
				var selval = parseFloat(obj.value);
			}
			var flag = obj.v_flag;
			if (typeof (flag) == "undefined" || flag == "")
				flag = "TT";
			var lflag = flag.substring(0, 1);// flag.split("|")[0];
			var rflag = flag.substring(1, 2);// flag.split("|")[1];

			if (!isNaN(maxval)) {
				if (rflag == "T") {
					if (selval > maxval) {
						return 0;
					}
				} else {
					if (selval >= maxval) {
						return 0;
					}
				}
			}

			if (!isNaN(minval)) {
				if (lflag == "T") {
					if (selval < minval) {
						return 0;
					}
				} else {
					if (selval <= minval) {
						return 0;
					}
				}
			}
			return true;
		}
	},
	isDigLengthOf : {// 数值的位数,需要自定义属性v_maxlength,v_minlength
		msg : [ "数值位数不在指定范围内", "必须为数字" ],
		test : function(obj) {
			if (!obj.value)
				return true;
			var maxlen = parseFloat(obj.v_maxlength);
			var minlen = parseFloat(obj.v_minlength);
			var patrn = /^([-]{0,1})?[0-9]+((\.{1}?[0-9]{1,13})|(\.{0}?[0-9]{0}))?$/;
			var val = obj.value;
			if (val.search(patrn) == -1) {
				return 1;
			}

			if (isNaN(obj.value) && obj.value != "") {
				return 1;
			}
			if (!isNaN(maxlen)) {
				if ((val + "").length > maxlen) {
					return 0;
				}
			}
			if (!isNaN(minlen)) {
				if ((val + "").length < minlen) {
					return 0;
				}
			}
			return true;
		}
	},
	isLengthOf : {// 需要自定义属性v_maxlength,v_minlength
		msg : "长度不在指定范围内",
		test : function(obj) {
			if (!obj.value)
				return true;
			var maxlen = parseFloat(obj.v_maxlength);
			var minlen = parseFloat(obj.v_minlength);
			var val = obj.value.replace(/(^\s*)|(\s*$)/g, "");
			if (!isNaN(maxlen)) {
				if ((val + "").length > maxlen) {
					return false;
				}
			}
			if (!isNaN(minlen)) {
				if ((val + "").length < minlen) {
					return false;
				}
			}
			return true;
		}
	},
	byteSize : {
		msg : "输入内容的字节长度不符",
		test : function(obj) {
			if (!obj.value)
				return true;
			var maxlen = parseFloat(obj.v_maxlength);
			var minlen = parseFloat(obj.v_minlength);
			var val = obj.value.replace(/(^\s*)|(\s*$)/g, "");
			var cArr = val.match(/[^\x00-\xff]/ig);
			var byteLen = val.length + (cArr == null ? 0 : cArr.length);
			if (!isNaN(maxlen)) {
				if (byteLen > maxlen) {
					return false;
				}
			}
			if (!isNaN(minlen)) {
				if (byteLen < minlen) {
					return false;
				}
			}
			return true;
		}
	},
	samePW : {
		msg : "密码不一致，请重新输入",
		test : function(obj) {
			var pw = document.getElementById(obj.v_pw);
			var confirmPw = document.getElementById(obj.v_confirmPw);
			if (pw.value != "" && confirmPw.value != ""
					&& pw.value != confirmPw.value) {
				return false;
			} else if (pw.value != "" && confirmPw.value != ""
					&& pw.value == confirmPw.value) {
				if (pw.parentNode && pw.parentNode.errstate) {
					hideErrors(pw);
					pw.parentNode.removeAttribute("errstate");
				}
				if (confirmPw.parentNode && confirmPw.parentNode.errstate) {
					hideErrors(confirmPw);
					confirmPw.parentNode.removeAttribute("errstate");
				}
				return true;
			}
			return true;
		}
	},
	chinese : {
		msg : "必须为汉字",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[\u4e00-\u9fa5]+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	letterChinese : {
		msg : "必须为英文或汉字",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[A-Za-z\u4e00-\u9fa5]+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	posInt : {
		msg : "请输入正整数",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[0-9]*[1-9][0-9]*$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	forInt : {
		msg : "必须为整数",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^-?\d+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	nonNegInt : {
		msg : "必须为非负整数",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^\d+$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	negInt : {
		msg : "必须为负整数！",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^-[0-9]*[1-9][0-9]*$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	notNegReal : {
		msg : "必须为非负实数！",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[0-9]+((\.{1}?[0-9]{1,13})|(\.{0}?[0-9]{0}))?$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	forReal : {
		msg : "必须为实数！",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^([-]{0,1})?[0-9]+((\.{1}?[0-9]{1,13})|(\.{0}?[0-9]{0}))?$/;
			var sInput = obj.value;
			if (sInput.search(patrn) == -1) {
				return false;
			}
			return true;
		}
	},
	uploadFile : {// 验证上传文件格式，需要自定义属性v_upType
		msg : [ "请选择要上传的文件！", "请上传后缀名正确的文件" ],
		test : function(obj) {
			if (!obj.value)
				return true;
			var fileName = obj.value.replace(/^\s*/, "").replace(/\s*$/, "");
			if (fileName == "") {
				return 0;
			} else {
				var pos = fileName.lastIndexOf(".");
				if (pos != -1) {
					var suf = fileName.substring(pos + 1, fileName.length)
							.toLowerCase();
					var upType = obj.v_upType.split(",");
					for ( var i = 0; i < upType.length; i++) {
						if (upType[i] == suf) {
							return true;
						}
					}
				}
				;
				return 1;
			}
			return true;
		}
	},
	compareDate : {
		msg : "结束日期比开始日期早",
		test : function(obj) {
			var frontDate = document.getElementById(obj.v_extB);
			var behindDate = document.getElementById(obj.v_extE);
			if (frontDate.value != "" && behindDate.value != ""
					&& frontDate.value > behindDate.value) {
				return false;
			} else if (frontDate.value != "" && behindDate.value != ""
					&& frontDate.value < behindDate.value) {
				if (frontDate.parentNode && frontDate.parentNode.errstate) {
					hideErrors(frontDate);
					frontDate.parentNode.removeAttribute("errstate");
				}
				if (behindDate.parentNode && behindDate.parentNode.errstate) {
					hideErrors(behindDate);
					behindDate.parentNode.removeAttribute("errstate");
				}
				return true;
			}
			return true;
		}
	},
	yyyy : {// yyyy
		msg : "日期格式为yyyy",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "yyyy") == 0)
				return false;
			return true;
		}
	},
	HHmmss : {// HHmmss
		msg : "日期格式为HHmmss",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "HHmmss") == 0)
				return false;
			return true;
		}
	},
	yyyyMMddHHmmss : {// yyyyMMddHHmmss
		msg : "日期格式为yyyyMMddHHmmss",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "yyyyMMddHHmmss") == 0)
				return false;
			return true;
		}
	},
	speyyyyMMddHHmmss : {// yyyyMMddHHmmss
		msg : "日期格式为yyyyMMdd HH:mm:ss",
		test : function(obj) {
			if (getDateFromFormat(obj.value, "yyyyMMdd HH:mm:ss") == 0)
				return false;
			return true;
		}
	},
	MM : {// MM
		msg : "日期格式为MM",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "MM") == 0)
				return false;
			return true;
		}
	},
	dd : {// dd
		msg : "日期格式为dd",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "dd") == 0)
				return false;
			return true;
		}
	},
	yyyyMMdd : {
		msg : "日期格式为yyyyMMdd",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "yyyyMMdd") == 0)
				return false;
			return true;
		}
	},
	yyyyMM : {
		msg : "日期格式为yyyyMM",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "yyyyMM") == 0)
				return false;
			return true;
		}
	},
	yyyyMMddHH : {
		msg : "日期格式为yyyyMMddHH",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "yyyyMMddHH") == 0)
				return false;
			return true;
		}
	},
	"lineyyyyMMdd" : {
		msg : "日期格式为yyyy-MM-dd",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "yyyy-MM-dd") == 0)
				return false;
			return true;
		}
	},
	"ColonHHmmss" : {
		msg : "日期格式为HH:mm:ss",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "HH:mm:ss") == 0)
				return false;
			return true;
		}
	},
	"lineyyyyMMddHHmmss" : {
		msg : "格式为yyyy-MM-dd HH:mm:ss",
		test : function(obj) {
			if (!obj.value)
				return true;
			if (getDateFromFormat(obj.value, "yyyy-MM-dd HH:mm:ss") == 0)
				return false;
			return true;
		}
	},
	moneyFormat : {
		msg : "必须为带0到2位小数的数值",
		test : function(obj) {
			var objValue = obj.value;
			if (!objValue)
				return true;
			if (isNaN(objValue))
				return false;
			var mArr = (objValue + "").split(".");
			if (mArr.length > 1) {
				if (mArr[1].length == 0 || mArr[1].length > 2)
					return false;
			}
			return true;

		}

	},
	multiMoneyFormat : {
		msg : [ "必须为小数", "小数点后位数不对" ],
		test : function(obj) {
			var objValue = obj.value;
			var decNum = obj.v_decNum;
			if (!objValue)
				return true;
			if (isNaN(objValue))
				return 0;
			if (objValue.indexOf("\.") == -1)
				return 0;
			var mArr = (objValue + "").split(".");
			if (mArr.length > 1) {
				if (mArr[1].length != decNum)
					return 1;
			}
			return true;
		}
	},
	haveSpe : {
		msg : "不能输入\\ / < > \' \" & #等字符",
		test : function(obj) {
			if (!obj.value)
				return true;
			return haveSpe(obj.value);
			function haveSpe(str) {
				var comp = "\\/><\'\"&#";
				var aChar = "";
				for ( var i = 0; i < str.length; i++) {
					aChar = str.charAt(i);
					if (comp.indexOf(aChar) != -1)
						return false;
				}
				return true;
			}
		}
	},
	// haveSpeForAll:{ //对所有的文本框进行限制
	// msg:"不能输入\\ < > \' \" & #等字符",
	// test:function(obj)
	// {
	// if(!obj.value) return true;
	// return haveSpe(obj.value);
	// function haveSpe(str)
	// {
	// var comp="\\><\'\"&#";
	// var aChar="";
	// for(var i=0;i<str.length;i++)
	// {
	// aChar=str.charAt(i);
	// if(comp.indexOf(aChar)!=-1)
	// return false;
	// }
	// return true;
	// }
	// }
	// },
	for0_9 : {
		msg : "必须由数字组成",
		test : function(obj) {
			if (!obj.value)
				return true;
			var numStr = "0123456789";
			if (obj.value.length == 0)
				return false;
			if (!isMadeOf(obj.value, numStr))
				return false;
			return true;
			function isMadeOf(val, str) {

				var jj;
				var chr;
				for (jj = 0; jj < val.length; ++jj) {
					chr = val.charAt(jj);
					if (str.indexOf(chr, 0) == -1)
						return false;
				}

				return true;
			}
		}
	},
	custassure : {
		msg : [ "是黑名单客户", "该担保人已经担保过" ],
		test : function(obj) {
			if (obj.v_flag == "black") {
				return 0;
			} else if (obj.v_flag == "idUsed") {
				return 1;
			} else {
				return true;
			}
		}
	},
	isServ : {
		msg : "不符合服务名称格式",
		test : function(obj) {
			if (!obj.value)
				return true;
			var patrn = /^[s]{1}([0-9]|[a-zA-Z]){0,}$/;
			var sInput = obj.value;
			if (!patrn.exec(sInput))
				return false;
			return true;
		}
	}

}
function hideErrors(elem) {
	var next_node = elem.parentNode.lastChild;
	if (next_node && next_node.className == "error_css") {
		elem.parentNode.removeChild(next_node);
		// errFlag--;
	}
}
function showErrors(elem, errors) {
	var next_node = elem.parentNode.lastChild;
	if (!next_node || next_node.className != "error_css") {
		next_node = document.createElement("span");
		next_node.className = "error_css";
		elem.parentNode.appendChild(next_node);
	}
	next_node.innerHTML = errors;
	errFlag++;
}
function validateField(elem) {
	for ( var name in errMsg) {
		var re = new RegExp("(^|\\s)" + name + "(\\s|$)");
		if (re.test(elem.className)) {
			var state = errMsg[name].test(elem);
			if (typeof errMsg[name].msg == 'string' && !state) {
				if (elem.id != "") {
					elem.parentNode.errstate = elem.id
				} else {
					elem.id = (new Date).getTime();
					elem.parentNode.errstate = elem.id
				}
				showErrors(elem, errMsg[name].msg);
				break;
			} else if ((errMsg[name].msg.constructor === Array)
					&& (state !== true)) {
				if (elem.id != "") {
					elem.parentNode.errstate = elem.id
				} else {
					elem.id = (new Date).getTime();
					elem.parentNode.errstate = elem.id
				}
				showErrors(elem, errMsg[name].msg[state]);
				break;
			} else {
				if (elem.parentNode
						&& elem.parentNode.errstate
						&& (elem.id == elem.parentNode.errstate
								|| !document
										.getElementById(elem.parentNode.errstate)
								|| document
										.getElementById(elem.parentNode.errstate).style.display == 'none' || document
								.getElementById(elem.parentNode.errstate).style.visibility == 'hidden')) {
					hideErrors(elem);
					elem.parentNode.removeAttribute("errstate");
				}
			}
			;
		}
	}
}
function validateElement(elem) {
	for ( var name in errMsg) {
		var re = new RegExp("(^|\\s)" + name + "(\\s|$)");
		if (re.test(elem.className)) {
			var state = errMsg[name].test(elem);
			if (typeof errMsg[name].msg == 'string' && !state) {
				if (elem.id != "") {
					elem.parentNode.errstate = elem.id
				} else {
					elem.id = (new Date).getTime();
					elem.parentNode.errstate = elem.id
				}
				showErrors(elem, errMsg[name].msg);
				return false;
			} else if ((errMsg[name].msg.constructor === Array)
					&& (state !== true)) {
				if (elem.id != "") {
					elem.parentNode.errstate = elem.id
				} else {
					elem.id = (new Date).getTime();
					elem.parentNode.errstate = elem.id
				}
				showErrors(elem, errMsg[name].msg[state]);
				return false;
			} else {
				if (elem.parentNode && elem.parentNode.errstate
						&& elem.id == elem.parentNode.errstate) {
					hideErrors(elem);
					elem.parentNode.removeAttribute("errstate");
				}
			}
			;
		}
	}
	return true;
}
$(document).ready(function() {
	var forms = document.getElementsByTagName("form");
	for ( var j = 0; j < forms.length; j++) {
		for ( var i = 0; i < forms[j].elements.length; i++) {
			// 并绑定'change'事件函数
			/*
			 * addEvent(forms[j].elements[i], 'blur', function() {
			 * validateField(this); });
			 */
			$(forms[j].elements[i]).bind("blur", function() {
				validateField(this);
			});
		}
	}
	// 为所有文本框添加特殊字符校验，
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "text") {
			addClass(inputs[i], "haveSpeForAll");
		}
	}
});
function checkAll(form) {
	for ( var i = 0; i < form.elements.length; i++) {
		var v_element = form.elements[i];
		var v_display = 0;
		while (v_element.parentNode && v_element.parentNode.tagName != "FORM"
				&& v_element.parentNode.id != "operation_table") {
			if (v_element.parentNode.style.display == "none"
					|| v_element.style.display == "none"
					|| v_element.type == "hidden"
					|| v_element.parentNode.style.visibility == "hidden"
					|| v_element.style.visibility == "hidden") {
				v_display++;
			}
			v_element = v_element.parentNode;
		}
		if (v_display != 0
				&& form.elements[i].parentNode.errstate
				&& (form.elements[i].parentNode.errstate == form.elements[i].id)) {
			hideErrors(form.elements[i]);
			form.elements[i].parentNode.removeAttribute("errstate");

		}
		if (form.elements[i].disabled == false && v_display == 0) {
			validateField(form.elements[i]);
		} else {
			// hideErrors(form.elements[i]);
		}
	}
	if ($(".error_css")[0]) {
		var preObj = $(".error_css")[0].previousSibling;
		while (preObj && preObj.nodeType != 1) {
			preObj = preObj.previousSibling;
		}
		// preObj.focus();
		// 焦点定位到第一个校验未通过的元素上
		if (preObj && (preObj.type == "text" || preObj.type == "textarea")) {
			preObj.focus();
		} else {
			try {
				preObj.focus();
			} catch (e) {
			}
		}
	}
}

function checksubmit(form_01) {
	errFlag = 0;
	checkAll(form_01);
	if (errFlag) {
		return false;
	} else {
		return true;
	}
}
function getDateFromFormat(val, format) {
	if (val == "")
		return 1;
	val = val + "";
	format = format + "";
	var i_val = 0;
	var i_format = 0;
	var c = "";
	var token = "";
	var token2 = "";
	var x, y;
	var now = new Date();
	var year = now.getYear();
	var month = now.getMonth() + 1;
	var date = 1;
	var hh = now.getHours();
	var mm = now.getMinutes();
	var ss = now.getSeconds();

	while (i_format < format.length) {
		// Get next token from format string
		c = format.charAt(i_format);
		token = "";
		while ((format.charAt(i_format) == c) && (i_format < format.length)) {
			token += format.charAt(i_format++);
		}
		// Extract contents of value based on format token
		if (token == "yyyy") {
			x = 4;
			y = 4;
			year = getInt(val, i_val, x, y);
			if (year == null) {
				return 0;
			}
			i_val += year.length;

		} else if (token == "MM") {
			month = getInt(val, i_val, token.length, 2);
			if (month == null || (month < 1) || (month > 12)) {
				return 0;
			}
			i_val += month.length;
		} else if (token == "dd") {
			date = getInt(val, i_val, token.length, 2);
			if (date == null || (date < 1) || (date > 31)) {
				return 0;
			}
			i_val += date.length;
		}

		else if (token == "HH") {
			hh = getInt(val, i_val, token.length, 2);
			if (hh == null || (hh < 0) || (hh > 23)) {
				return 0;
			}
			i_val += hh.length;
		} else if (token == "mm") {
			mm = getInt(val, i_val, token.length, 2);
			if (mm == null || (mm < 0) || (mm > 59)) {
				return 0;
			}
			i_val += mm.length;
		} else if (token == "ss") {
			ss = getInt(val, i_val, token.length, 2);
			if (ss == null || (ss < 0) || (ss > 59)) {
				return 0;
			}
			i_val += ss.length;
		}

		else {
			if (val.substring(i_val, i_val + token.length) != token) {
				return 0;
			} else {
				i_val += token.length;
			}
		}
	}// while end
	// If there are any trailing characters left in the value, it doesn't match
	if (i_val != val.length) {
		return 0;
	}
	// Is date valid for month?
	if (month == 2) {
		// Check for leap year
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) { // leap
																			// year
			if (date > 29) {
				return 0;
			}
		} else {
			if (date > 28) {
				return 0;
			}
		}
	}
	if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
		if (date > 30) {
			return 0;
		}
	}

	var newdate = new Date(year, month - 1, date, hh, mm, ss);
	return newdate.getTime();
}
function getInt(str, i, minlength, maxlength) {
	for ( var x = maxlength; x >= minlength; x--) {
		var token = str.substring(i, i + x);
		if (token.length < minlength) {
			return null;
		}
		if (isInteger(token)) {
			return token;
		}
	}
	return null;
}
function isInteger(val) {
	var digits = "1234567890";
	for ( var i = 0; i < val.length; i++) {
		if (digits.indexOf(val.charAt(i)) == -1) {
			return false;
		}
	}
	return true;
}

function addClass(element, value) { // 追加样式，而不是替换样式
	var ename;
	if (!element.className) {
		element.className = value;
	} else {
		var sname = element.className.split(" ");
		for ( var i = 0; i < sname.length; i++) {
			if (sname[i].indexOf(value) == 0) {
				ename = sname[i];
			}
		}
		if (!ename) {
			element.className += " ";
			element.className += value;
		}
	}
}

// 当页面上文本框置灰不可用的时候，隐藏其置灰前的验证错误信息
function disabledError(elem) {
	if (elem.parentNode.getAttribute("errstate")) {
		hideErrors(elem);
		elem.parentNode.removeAttribute("errstate");
	}
}

$.extend($.fn.validatebox.defaults.rules, {
	CHS : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message : '请输入汉字'
	},
	ZIP : {
		validator : function(value, param) {
			return /^[1-9]\d{5}$/.test(value);
		},
		message : '邮政编码不存在'
	},
	QQ : {
		validator : function(value, param) {
			return /^[1-9]\d{4,10}$/.test(value);
		},
		message : 'QQ号码不正确'
	},
	mobile : {
		validator : function(value, param) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/.test(value);
		},
		message : '手机号码不正确'
	},
	doubleNumber : {
		validator : function(value, param) {
			return /^[-+]?\d+(\.\d+)?$/.test(value);
		},
		message : '只能是数字小数点负号'
	},
	TaskpointName : {
		validator : function(value, param) {
			return !/^[\"&?%#$]*$/.test(value);
		},
		message : '名称不允许</br>输入双引号、&、?、%、#、$。'
	},
	loginName : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5\w]+$/.test(value);
		},
		message : '名称只允许汉字</br>字母、数字及下划线。'
	},
	safepass : {
		validator : function(value, param) {
			return safePassword(value);
		},
		message : '密码由字母和数字组成，至少6位'
	},
	equalTo : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次输入的字符不一至'
	},
	number : {
		validator : function(value, param) {
			return /^\d+$/.test(value);
		},
		message : '请输入数字'
	},
	idcard : {
		validator : function(value, param) {
			return idCard(value);
		},
		message : '请输入正确的身份证号码'
	}
});

/* 密码由字母和数字组成，至少6位 */
var safePassword = function(value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/
			.test(value));
}

$.extend($.fn.validatebox.defaults.rules,{
		minLength : { // 判断最小长度
			validator : function(value, param) {
				return value.length >= param[0];
			},
			message : '最少输入 {0} 个字符。'
		},

		maxLength : { // 判断最da长度
			validator : function(value, param) {
				return value.length <= param[0];
			},
			message : '最大长度不能超过 {0} 个字符。'
		},
		length : {
			validator : function(value, param) {
				var len = $.trim(value).length;
				return len >= param[0] && len <= param[1];
			},
			message : "输入内容长度必须介于{0}和{1}之间."
		},
		idcard : {// 验证身份证
			validator : function(value) {
				return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
			},
			message : '身份证号码格式不正确'
		},
		intOrFloat : {// 验证整数或小数
			validator : function(value) {
				return /^\d+(\.\d+)?$/i.test(value);
			},
			message : '请输入数字，并确保格式正确'
		},
		percentage : {// 验证百分数
			validator : function(value) {
				return /^([0-9][0-9]?(\.[0-9]{1,2})?)%$|^(0\.[1-9][0-9]?)%$|^(0\.[0-9][1-9])%$|^100(\.00)?%$/i
						.test(value);
			},
			message : '请输入有效的百分数(例：xx.xx%)'
		},
		currency : {// 验证货币
			validator : function(value) {
				return /^\d+(\.\d+)?$/i.test(value);
			},
			message : '货币格式不正确'
		},
		integer : {// 验证整数
			validator : function(value) {
				return /^[+]?[0-9]+\d*$/i.test(value);
			},
			message : '请输入整数'
		},
		chinese : {// 验证中文
			validator : function(value) {
				return /^[\u0391-\uFFE5]+$/i.test(value);
			},
			message : '请输入中文'
		},
		english : {// 验证英语
			validator : function(value) {
				return /^[A-Za-z]+$/i.test(value);
			},
			message : '请输入英文'
		},
		unnormal : {// 验证是否包含空格和非法字符
			validator : function(value) {
				return /.+/i.test(value);
			},
			message : '输入值不能为空和包含其他非法字符'
		},
		general : {
			validator : function(value) {
				return /^[\u4e00-\u9fa5_a-zA-Z0-9\-\:\：]+$/i
						.test(value);
			},
			message : '输入值不能为空和包含其他非法字符'

		},
		generals : {
			validator : function(value) {
				return /^[\w\W]$/i.test(value);

			},
			message : '输入值不能为空和包含其他非法字符'

		},
		vgeneral : {
			validator : function(value) {
				return /^[^ ]+[\s\S]*[^ ]+$/i.test(value)
						& /^[\u4e00-\u9fa5_a-zA-Z0-9\-\:\：\s]+$/i
								.test(value);
			},
			message : '输入值不能少于两个字符和包含其他非法字符'
		},
		username : {// 验证用户名
			validator : function(value) {
				return /^[a-zA-Z][a-zA-Z0-9_]{5,19}$/i.test(value);
			},
			message : '用户名不合法(字母开头，允许6-20字节，允许字母数字下划线)'
		},
		tel : {// 验证电话号码
			validator : function(value) {
				return /^[+]{0,1}(\(\d{3,4}\)|\d{3,4}-{0,1})?\d{7,8}$/i.test(value);
			},
			message : '请输入有效的电话号码'
		},
		mobile : {// 验证手机号码
			validator : function(value) {
				return /^((13[0-9])|(147)|(15[^4,\D])|(18[0-9])|(17[0-9]))\d{8}$/i.test(value);
			},
			message : '请输入有效的手机号'
		},
		phone : {// 电话号码跟手机号码格式同时验证
			validator : function(value) {
				return /^[+]{0,1}(\(\d{3,4}\)|\d{3,4}-{0,1})?\d{7,8}$/i.test(value)
						| /^((13[0-9])|(147)|(15[^4,\D])|(18[0-9])|(17[0-9]))\d{8}$/i.test(value);
			},
			message : '请输入有效的手机号或电话号码'
		},
		fax : {// 验证传真
			validator : function(value) {
				return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
						.test(value);
			},
			message : '请输入有效的传真号码'
		},
		zip : {// 验证邮政编码
			validator : function(value) {
				return /^[1-9]\d{5}$/i.test(value);
			},
			message : '请输入有效的邮政编码'
		},
		ip : {// 验证IP地址
			validator : function(value) {
				return /d+.d+.d+.d+/i.test(value);
			},
			message : 'IP地址格式不正确'
		},
		name : {// 验证姓名，可以是中文或英文
			validator : function(value) {
				return /^[\u0391-\uFFE5]+$/i.test(value)
						| /^\w+[\w\s]+\w+$/i.test(value);
			},
			message : '请输入姓名'
		},
		maxLengthAndfilterBox : { // 判断最大长度和过滤空格
			validator : function(value, param) {
				var leng = value.length <= param[0];
				var box = /^[^ ]+[\s\S]*[^ ]+$/i.test(value)
						& /^[\u4e00-\u9fa5_a-zA-Z0-9\-\:\：\s\、\（\）\(\，\,\)\.\。\;\；\#]+$/i
								.test(value);
				if (leng == true && box == true) {
					return true;
				} else {
					return false;
				}
			},
			message : "输入值不能包含非法字符，最大长度不能超过 {0} 个字符。"
		},
		carNo : {
			validator : function(value) {
				return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);
			},
			message : '车牌号码无效'
		},
		carenergin : {
			validator : function(value) {
				return /^[a-zA-Z0-9]{16}$/.test(value);
			},
			message : '发动机型号无效'
		},
		email : {
			validator : function(value) {
				return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
						.test(value);
			},
			message : '请输入有效的电子邮箱账号'
		},
		price:{
			validator : function(value) {
				return /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/i.test(value);
			},
			message : '请输入合适的值'
		},
		qq : {// 验证QQ,从10000开始
			validator : function(value) {
				return /^[1-9]\d{4,9}$/i.test(value);
			},
			message : 'QQ号码格式不正确'
		},
		msn : {
			validator : function(value) {
				return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
						.test(value);
			},
			message : '请输入有效的msn账号'
		},
		same : {
			validator : function(value, param) {
				if ($("#" + param[0]).val() != "" && value != "") {
					return $("#" + param[0]).val() == value;
				} else {
					return true;
				}
			},
			message : '两次输入的密码不一致！'
		},
		xpoint : {
			validator : function(value) {
				if (value > 0 && value < 180){
					return true;
				}else{
					return false;
				}
			},
			message : '请输入有效的经度'
		},
		ypoint : {
			validator : function(value) {
				if (value > 0 && value < 90){
					return true;
				}else{
					return false;
				}
			},
			message : '请输入有效的纬度'
		},
		unique:{
			validator:function(value,param){
				var url = param[0];  //验证路径
				var propertyName = param[1];  //参数
				var limitLen = param[2];  //字符串达到多大字符时开始验证
				var msg = param[3];  //提示语
				var refer = param[4]; //参照字段  
				
				if (!value) {
					return true;
				}
				
				if(value.length < limitLen){
					return true;
				}
				
				if (refer) {
					url+="&" + refer +"=" + $("#" + refer).val();
				}
				//后台验证
				var data ={};
				data[propertyName]=value;
				var response = $.ajax({
					url : url,
					type : "post",
					datatype : "json",
					async : false,
					data : data
				}).responseText;
				
				if (response == "1" || response == "false") {
					$.fn.validatebox.defaults.rules.unique.message = msg;
					return false;
				} else {
					return true;
				}
			},
			message:''
		}
});