if(jQuery){(function(a){a.extend(a.fn,{uploadify:function(b){a(this).each(function(){var f=a.extend({id:a(this).attr("id"),uploader:"uploadify.swf",script:"uploadify.php",expressInstall:null,folder:"",height:30,width:120,cancelImg:"cancel.png",wmode:"opaque",scriptAccess:"sameDomain",fileDataName:"Filedata",method:"POST",queueSizeLimit:999,simUploadLimit:1,queueID:false,displayData:"percentage",removeCompleted:true,onInit:function(){},onSelect:function(){},onSelectOnce:function(){},onQueueFull:function(){},onCheck:function(){},onCancel:function(){},onClearQueue:function(){},onError:function(){},onProgress:function(){},onComplete:function(){},onAllComplete:function(){}},b);a(this).data("settings",f);var e=location.pathname;e=e.split("/");e.pop();e=e.join("/")+"/";var g={};g.uploadifyID=f.id;g.pagepath=e;if(f.buttonImg){g.buttonImg=escape(f.buttonImg)}if(f.buttonText){g.buttonText=escape(f.buttonText)}if(f.rollover){g.rollover=true}g.script=f.script;g.folder=escape(f.folder);if(f.scriptData){var h="";for(var d in f.scriptData){h+="&"+d+"="+f.scriptData[d]}g.scriptData=escape(h.substr(1))}g.width=f.width;g.height=f.height;g.wmode=f.wmode;g.method=f.method;g.queueSizeLimit=f.queueSizeLimit;g.simUploadLimit=f.simUploadLimit;if(f.hideButton){g.hideButton=true}if(f.fileDesc){g.fileDesc=f.fileDesc}if(f.fileExt){g.fileExt=f.fileExt}if(f.multi){g.multi=true}if(f.auto){g.auto=true}if(f.sizeLimit){g.sizeLimit=f.sizeLimit}if(f.checkScript){g.checkScript=f.checkScript}if(f.fileDataName){g.fileDataName=f.fileDataName}if(f.queueID){g.queueID=f.queueID}if(f.onInit()!==false){a(this).css("display","none");a(this).after('<div id="'+a(this).attr("id")+'Uploader"></div>');swfobject.embedSWF(f.uploader,f.id+"Uploader",60,25,"9.0.24",f.expressInstall,g,{quality:"high",wmode:f.wmode,allowScriptAccess:f.scriptAccess},{},function(i){if(typeof(f.onSWFReady)=="function"&&i.success){f.onSWFReady()}});if(f.queueID==false){a("#"+a(this).attr("id")+"Uploader").after('<div id="'+a(this).attr("id")+'Queue" class="uploadifyQueue"></div>')}else{a("#"+f.queueID).addClass("uploadifyQueue")}}if(typeof(f.onOpen)=="function"){a(this).bind("uploadifyOpen",f.onOpen)}a(this).bind("uploadifySelect",{action:f.onSelect,queueID:f.queueID},function(k,i,j){if(k.data.action(k,i,j)!==false){var l=Math.round(j.size/1024*100)*0.01;var m="KB";if(l>1000){l=Math.round(l*0.001*100)*0.01;m="MB"}var n=l.toString().split(".");if(n.length>1){l=n[0]+"."+n[1].substr(0,2)}else{l=n[0]}if(j.name.length>20){fileName=j.name.substr(0,20)+"..."}else{fileName=j.name}queue="#"+a(this).attr("id")+"Queue";if(k.data.queueID){queue="#"+k.data.queueID}a(queue).append('<div id="'+a(this).attr("id")+i+'" class="uploadifyQueueItem"><div class="cancel"><a href="javascript:jQuery(\'#'+a(this).attr("id")+"').uploadifyCancel('"+i+'\')"><img src="'+f.cancelImg+'" border="0" /></a></div><span class="fileName">'+fileName+" ("+l+m+')</span><span class="percentage"></span><div class="uploadifyProgress"><div id="'+a(this).attr("id")+i+'ProgressBar" class="uploadifyProgressBar"><!--Progress Bar--></div></div></div>')}});a(this).bind("uploadifySelectOnce",{action:f.onSelectOnce},function(i,j){i.data.action(i,j);if(f.auto){if(f.checkScript){a(this).uploadifyUpload(null,false)}else{a(this).uploadifyUpload(null,true)}}});a(this).bind("uploadifyQueueFull",{action:f.onQueueFull},function(i,j){if(i.data.action(i,j)!==false){alert("The queue is full.  The max size is "+j+".")}});a(this).bind("uploadifyCheckExist",{action:f.onCheck},function(n,m,l,k,p){var j=new Object();j=l;j.folder=(k.substr(0,1)=="/")?k:e+k;if(p){for(var i in l){var o=i}}a.post(m,j,function(s){for(var q in s){if(n.data.action(n,s,q)!==false){var r=confirm("Do you want to replace the file "+s[q]+"?");if(!r){document.getElementById(a(n.target).attr("id")+"Uploader").cancelFileUpload(q,true,true)}}}if(p){document.getElementById(a(n.target).attr("id")+"Uploader").startFileUpload(o,true)}else{document.getElementById(a(n.target).attr("id")+"Uploader").startFileUpload(null,true)}},"json")});a(this).bind("uploadifyCancel",{action:f.onCancel},function(n,j,m,o,i,l){if(n.data.action(n,j,m,o,l)!==false){if(i){var k=(l==true)?0:250;a("#"+a(this).attr("id")+j).fadeOut(k,function(){a(this).remove()})}}});a(this).bind("uploadifyClearQueue",{action:f.onClearQueue},function(k,j){var i=(f.queueID)?f.queueID:a(this).attr("id")+"Queue";if(j){a("#"+i).find(".uploadifyQueueItem").remove()}if(k.data.action(k,j)!==false){a("#"+i).find(".uploadifyQueueItem").each(function(){var l=a(".uploadifyQueueItem").index(this);a(this).delay(l*100).fadeOut(250,function(){a(this).remove()})})}});var c=[];a(this).bind("uploadifyError",{action:f.onError},function(m,i,l,k){if(m.data.action(m,i,l,k)!==false){var j=new Array(i,l,k);c.push(j);a("#"+a(this).attr("id")+i).find(".percentage").text(" - "+k.type+" Error");a("#"+a(this).attr("id")+i).find(".uploadifyProgress").hide();a("#"+a(this).attr("id")+i).addClass("uploadifyError")}});if(typeof(f.onUpload)=="function"){a(this).bind("uploadifyUpload",f.onUpload)}a(this).bind("uploadifyProgress",{action:f.onProgress,toDisplay:f.displayData},function(k,i,j,l){if(k.data.action(k,i,j,l)!==false){a("#"+a(this).attr("id")+i+"ProgressBar").animate({width:l.percentage+"%"},250,function(){if(l.percentage==100){a(this).closest(".uploadifyProgress").fadeOut(250,function(){a(this).remove()})}});if(k.data.toDisplay=="percentage"){displayData=" - "+l.percentage+"%"}if(k.data.toDisplay=="speed"){displayData=" - "+l.speed+"KB/s"}if(k.data.toDisplay==null){displayData=" "}a("#"+a(this).attr("id")+i).find(".percentage").text(displayData)}});a(this).bind("uploadifyComplete",{action:f.onComplete},function(l,i,k,j,m){if(l.data.action(l,i,k,unescape(j),m)!==false){a("#"+a(this).attr("id")+i).find(".percentage").text(" - Completed");if(f.removeCompleted){a("#"+a(l.target).attr("id")+i).fadeOut(250,function(){a(this).remove()})}a("#"+a(l.target).attr("id")+i).addClass("completed")}});if(typeof(f.onAllComplete)=="function"){a(this).bind("uploadifyAllComplete",{action:f.onAllComplete},function(i,j){if(i.data.action(i,j)!==false){c=[]}})}})},uploadifySettings:function(f,j,c){var g=false;a(this).each(function(){if(f=="scriptData"&&j!=null){if(c){var i=j}else{var i=a.extend(a(this).data("settings").scriptData,j)}var l="";for(var k in i){l+="&"+k+"="+i[k]}j=escape(l.substr(1))}g=document.getElementById(a(this).attr("id")+"Uploader").updateSettings(f,j)});if(j==null){if(f=="scriptData"){var b=unescape(g).split("&");var e=new Object();for(var d=0;d<b.length;d++){var h=b[d].split("=");e[h[0]]=h[1]}g=e}}return g},uploadifyUpload:function(b,c){a(this).each(function(){if(!c){c=false}document.getElementById(a(this).attr("id")+"Uploader").startFileUpload(b,c)})},uploadifyCancel:function(b){a(this).each(function(){document.getElementById(a(this).attr("id")+"Uploader").cancelFileUpload(b,true,true,false)})},uploadifyClearQueue:function(){a(this).each(function(){document.getElementById(a(this).attr("id")+"Uploader").clearFileUploadQueue(false)})}})})(jQuery)};
/**scduploadify v0.1*
**2012-04-23  如果设置分类只有一种，也不显示分类列表，并且在上传的时候，默认为这个分类
*             在显示的时候，可以设置参数filtertype ，表示只显示的分类
* 2012-05-04  修改了，文件说明的显示宽度为，一般字段的两倍
* 2012-05-17  修改列表类型可以对应不同的附件选择类型
* 2012-05-23  修改提示信息的时候，如果引用了easyui框架的话，提示用easyui的messager提示
* 2012-05-26  修改支持国际化
**/
if(jQuery)(
	function(jQuery){
	   /**开始扩展插件**/
		jQuery.extend(jQuery.fn,{
		    scduploadify:function(options){
		       jQuery(this).each(function(){
		    	    jQuery(this).empty();
		    	    
		            /***开始初始化参数**/
					var settings = jQuery.extend({
						    'entitypath':"org.safari.platform.modules.sys.entity.Media",//实体类包名  
						    'idfield':"id",  //实体主键字段名
						    'filetype':[{"name":"头像","value":"1","fileext":"*.txt;*.doc;*.docx;*.pdf","accept":"text/plain,application/msword,application/pdf"},
						                {"name":"图片","value":"2","fileext":"*.png;*.jpg;*.bmp","accept":"image/png,image/jpeg,image/bmp"},
						                {"name":"音频","value":"3"},
						                {"name":"视频","value":"4"},
						                {"name":"文件","value":"5"},
						                {"name":"二维码","value":"6"},
						                {"name":"其他","value":"7"}],   //文件类型
					        'fields':[],
				      		'mode':"add",
				      		'reftno':"",  //关联ID
					        'reftable':"gemo",  //关联的表名称
					        'filehandlerurl':"",  //附件操作的的url
					        'filtertype':[],    //筛选的分类，默认全部显示
					        
						    //以下为uploadify的参数
						    'uploader'	:	"./uploadify.swf",
							'script'    :	"./scduploadify.jsp",
							'cancelImg' :	"./cancel.png",
							'buttonImg':    "./selectfile.jpg",
							'hideButton':   true,
							'folder'	:	"",//上传文件存放的路径,
							'queueSizeLimit'	:	2,//限制上传文件的数量
							'fileExt'	:	"*.*", //限制文件类型以分号隔开
							'fileDesc'	:	"*.*",//限制文件类型描述
							'auto'		:	true,//是否自动上传
							'multi'		:	true,//是否允许多文件上传
							'simUploadLimit':	1,//同时运行上传的进程数量
							'buttonText':	"files",
							'method'	:	"GET",
							'scriptData':	{},//这个参数用于传递用户自己的参数，此时'method' 必须设置为GET, 后台可以用request.getParameter('name')获取名字的值
							'sizeLimit' :   5*1024*1024  //默认5M
					},options);
					
					 /**获取当前系统使用语言编码**/
					var lang = top.language;//获取当前语言环境
					if(undefined != lang && null != lang && "" != lang){
						lang  = lang.toUpperCase();
					}else{
						lang = "ZH_CN";
					}
					
					 /**根据使用语言选择按钮**/
					var selectFilePicPath = settings.buttonImg;
					settings.buttonImg = selectFilePicPath.substring(0, selectFilePicPath.lastIndexOf("/"))+"/selectfile"+lang+".png";
					
					if(settings.fields.length==0){ //使用默认值
						settings=jQuery.extend(settings,{ 'fields':
						    [
						        {"name":scduploadifyLanguage[lang]["name"],"value":"name"},
					        	{"name":scduploadifyLanguage[lang]["type"],"value":"type"},
					        	{"name":scduploadifyLanguage[lang]["size"],"value":"size"},
					        	{"name":scduploadifyLanguage[lang]["func"],"value":"func"}
					        ]});
					}
					
					/**缓存参数**/
					jQuery(this).data('settings',settings);
					jQuery(this).data('iscomplete',true); //是否上传已经完成
					jQuery(this).data('filelist',new Array()); //上传文件列表
					jQuery(this).data('lang',lang);
					
					/**生成显示文件列表的table**/
					var thisID = jQuery(this).attr('id'); //当前div 的ID
					
					 /**判断是否引用了easyui插件**/
					var alertObject =typeof(jQuery.messager);
					var isRefEasyUI=true;
					if(alertObject=="undefined"){isRefEasyUI=false;}
					
					 /**文件大小说明**/
					if(settings.mode != 'view'){
					    jQuery(this).append("<div style='color:red;float:left;padding:4px;'>"+scduploadifyLanguage[lang]["filelimit"]+convertFileSize(settings.sizeLimit)+"</div>");
					}
					
					/**初始化表格**/
					var tableid=jQuery(this).attr('id')+"Table"; //tableID
					jQuery("<table>",{'id':tableid,'class':"scduploadifytable"}).appendTo(this);
					var fileListTable = jQuery("#"+tableid);
					var filetablehead = jQuery("<tr>",{"class":"tablehead"});//表头
					var blankrow = jQuery("<tr>");
					var fieldsLength = settings.fields.length;
					jQuery.each(settings.fields,function(i,item){
						 if(item.value == "desc"){fieldsLength++; }
					});
					var fieldswidth =parseInt( 100 /fieldsLength );
					jQuery.each(settings.fields,function(i,item){
						if(item.value == 'desc'){ 
							 filetablehead.append("<td style=\"width:"+fieldswidth*2+"%;\">"+scduploadifyLanguage[lang]["desc"]+"</td>");
						}else{
							 filetablehead.append("<td style=\"width:"+fieldswidth+"%;\">"+scduploadifyLanguage[lang][item.value]+"</td>");
						}
						//blankrow.append("<td>&nbsp;</td>");  //默认添加空行数据
					});
					fileListTable.append(filetablehead);
					fileListTable.append(blankrow);
					
					if(settings.mode != "view"){  //非查看模式
							var accept = "";
							/**生成第一排div**/
							var infirstdivID=jQuery(this).attr('id')+"Firstdiv"; //修改为table
							jQuery("<table id='scduploadifyuploadtable' width='100%'><tr id='"+infirstdivID+"'></tr></table>").appendTo(this);
							
							/**生成类型选择， 存放文档类型，文件名显示框，上传组件**/
							if(settings.filetype.length>1){
								var selectID = jQuery(this).attr('id')+"filetype";
								var selectItem = jQuery("<select onchange=scduploadifyfiletypechange('"+ thisID +"') id='"+selectID+"'></select>").css('width',"80px");
								jQuery.each(settings.filetype,function(i,data){
									accept = data.accept;
									if(undefined != data.fileext && null != data.fileext ){
									   selectItem.append("<option value='"+data.value+"' fileext='"+data.fileext+"'>"+data.name+"</option>");
									}else{
										selectItem.append("<option value='"+data.value+"'>"+data.name+"</option>");
									}
								});
								jQuery("<td>",{'width':"80px",'height:':"22px"}).append(selectItem).appendTo(jQuery("#"+infirstdivID));
							}else{
								var filetype = settings.filetype[0];
								accept = filetype.accept;
								if(undefined != filetype.fileext && null != filetype.fileext){
									settings.fileExt = filetype.fileext;
									settings.fileDesc = filetype.name +"(" + filetype.fileext + ")";
								}
							}
							
							/**文件名显示的文本框 **/
							var filenameInputID = jQuery(this).attr('id')+"FileName";
							var filenameInput = jQuery("<input>",{'id':filenameInputID,'type':'text','readonly':'readonly','width':'100%','height':'20px'});
							jQuery("<td>",{}).append(filenameInput).appendTo(jQuery("#"+infirstdivID));
							
							/**生成input节点**/
							var uploadifyID = thisID + "uploadify"; //根据传入的divID，生产ID
							var uploadfiyinput = jQuery("<input>", {
						        'type': "file",
						        'id':uploadifyID,
						        'accept':accept
						     });
							
							jQuery("<td>",{"width":"80px","align":"center"}).append(uploadfiyinput).appendTo(jQuery("#"+infirstdivID));
							
							/**生成上传进度显示条**/
							var uploadifyqueueID = jQuery(this).attr('id')+"queue";
							jQuery("<div>", {
						        'id':uploadifyqueueID,
						        'class':"uploadifyQueue div-percent"
						     }).appendTo(this);
							
							//拼接scriptData
							var fileType = "";
							if(settings.filetype.length>1){
								fileType = jQuery("#"+thisID+"filetype").find('option:selected').val();
					    	}else if(settings.filetype.length ==1){
					    		fileType = settings.filetype[0].value;
					    	}
							
							settings.scriptData = {
									'entitypath':settings.entitypath,
						    		'idfield':settings.idfield,
						    		'idtype':fileType
							};
							
							/**生成并初始化uploadify**/
							var uploadifysettings = jQuery.extend(settings,{id:uploadifyID,queueID:uploadifyqueueID,
								onComplete:function(event, ID, fileObj, response, data){
									
									/**上传完成**/
									var fileid = jQuery.trim(response);
									if(fileid.length == 0){
										if(isRefEasyUI){
											$.messager.alert(scduploadifyLanguage[lang]["error"],scduploadifyLanguage[lang]["failedinfo"],'error');
										}else{
											alert(scduploadifyLanguage[lang]["failedinfo"]);
										}
										return;
									}
									
									//文件类型
									var filetype="";
									if(settings.filetype.length>1){
										 filetype = jQuery("#"+thisID+"filetype").find('option:selected').val();
									}else if(settings.filetype.length==1){
										filetype =settings.filetype[0].value;
									}
									
									//文件大小
									var filesize=convertFileSize(fileObj.size);
									
									//文件上传后的ID
									var fileobject ={'id':fileid,"name":fileObj.name,'type':filetype,'size':filesize,'desc':jQuery("#"+thisID+"ThirddivDESC").val()};
									
									scduploadifyBindToTable(tableid,settings.mode,fileobject,thisID);
									
									jQuery("#"+ settings.reftno).val(fileid);
									jQuery("#"+thisID).data('filelist').push(fileobject);
									
									var filelist = jQuery("#"+thisID).data("filelist");
									var limitCount = settings.queueSizeLimit;
									if(filelist.length >= limitCount){
										jQuery("#"+filenameInputID).val("");
										jQuery("#scduploadifyuploadtable").hide();
									}
									
									jQuery("#"+thisID+"ThirddivUploadButton").removeAttr("disabled");
									jQuery("#"+thisID).data('iscomplete',true);
								},onSelect:function(event,ID,fileObj){
									document.getElementById(uploadifyID + 'Uploader').clearFileUploadQueue(false);
									jQuery("#"+filenameInputID).val(fileObj.name);
									jQuery("#"+thisID).data('iscomplete',false);
								},onError:function(){
									if(isRefEasyUI){
										$.messager.alert(scduploadifyLanguage[lang]["error"],scduploadifyLanguage[lang]["failederror"]+convertFileSize(settings.sizeLimit),'error');
									}else{
										alert(scduploadifyLanguage[lang]["failederror"]+convertFileSize(settings.sizeLimit));
									}
								    jQuery("#"+filenameInputID).val("");
								    jQuery("#"+thisID).data('iscomplete',true);
							    }
							});//增加ID参数 增加queueID参数,增加完成后参数
							
							jQuery("#"+uploadifyID).uploadify(uploadifysettings); 
							/**生产第三行--1,输入标签，2,说明输入框，3,上传按钮，4,清空按钮**/
							var inthirddivID=jQuery(this).attr('id')+"Thirddiv";
							jQuery("<table width='100%' class='inthirddiv'><tr id='"+inthirddivID+"'></tr></table>").appendTo(this);
							var inthirddiv=jQuery("#"+inthirddivID); //tr
							inthirddiv.append("<td align='center' width='80px'>"+scduploadifyLanguage[lang]["desc"]+"：</td>");
							descInput= jQuery("<input>",{
								'type':'text',
								'id':inthirddivID+"DESC",
								'width':"99%"
							})
							jQuery("<td>",{}).append(descInput).appendTo(inthirddiv);
							var uploadbutton= jQuery("<input>",{
								'id':inthirddivID+"UploadButton",
								'type':'button',
								'value':scduploadifyLanguage[lang]["upload"], 
								'class':"button",
								'click':function(){
								    if(jQuery("#"+inthirddivID+"DESC").val().length>2000){
								    	if(isRefEasyUI){
								    	   $.messager.alert(scduploadifyLanguage[lang]["error"],scduploadifyLanguage[lang]["desclimit"],'error');
								    	}else{
								    		alert(scduploadifyLanguage[lang]["desclimit"]);
								    	}
								    	return;
								    }
								    if(jQuery("#"+filenameInputID).val().length>0){
								    	jQuery(this).attr("disabled","disabled");  	
								    	jQuery("#"+uploadifyID).uploadifySettings("scriptData",{
								    		'entitypath':settings.entitypath,
								    		'idfield':settings.idfield,
								    		'name':jQuery("#"+filenameInputID).val(),
								    	});
								    	if(settings.filetype.length>1){
								    		jQuery("#"+uploadifyID).uploadifySettings("scriptData",{
								    			"type":jQuery("#"+thisID+"filetype").find('option:selected').html()
								    		});
								    	}else if(settings.filetype.length ==1){
								    		jQuery("#"+uploadifyID).uploadifySettings("scriptData",{"type":settings.filetype[0].name});
								    	}
								    	jQuery("#"+uploadifyID).uploadifyUpload();
								    }else{
								    	if(isRefEasyUI){
								    		$.messager.alert(scduploadifyLanguage[lang]["info"],scduploadifyLanguage[lang]["selectinfo"],'info');
								    	}else{ 
								    		alert(scduploadifyLanguage[lang]["selectinfo"]);
								    	}
								    }
								}
							});
							var clearbutton= jQuery("<input>",{
								'type':'button',
								'value':scduploadifyLanguage[lang]["clear"],
								'class':"button",
								'click':function(){
								    jQuery("#"+filenameInputID).val("");
								    jQuery("#"+thisID+"ThirddivDESC").val(""); //清空说明
								    jQuery("#"+uploadifyID).uploadifyClearQueue();
								    jQuery("#"+thisID+"ThirddivUploadButton").removeAttr("disabled");
								    jQuery("#"+thisID).data('iscomplete',true);
								}
							});
							jQuery("<td>",{'width':"130px",'align':"center"}).append(uploadbutton).append("&nbsp;").append(clearbutton).appendTo(inthirddiv);
					}
					
					/**读取数据**/
					if(settings.reftno.length > 0){
						var ids = jQuery("#"+settings.reftno).val();
						if(undefined != ids && "undefined" != ids  
								&& null != ids  && "" != ids){
							jQuery.post(settings.filehandlerurl+"query",{"ids":ids},function(data){
								   if(data.length>0){
									   jQuery.each(data,function(i,n){
										   var fileitemdata = null;
										   var isshow=false;
										   var typename = null;
										  
										   if(undefined != settings.filtertype && "undefined" != settings.filtertype  
													&& null != settings.filtertype  && "" != settings.filtertype){//过滤显示类型
											   if(settings.filtertype == n.type){
												   jQuery.each(settings.filetype,function(j,k){
													   if(k.value == n.type){
														   typename = k.name;
														   isshow = true;
													   }
												   });
											   }
										   }else{
											   isshow = true;
										   }
										   if(isshow){
											   fileitemdata={
												   "id":n.id,
												   "name":n.name,
												   "type":typename,
												   "size":n.length
											   };
											   scduploadifyBindToTable(tableid,settings.mode,fileitemdata,thisID);
											   jQuery("#"+thisID).data('filelist').push(fileitemdata);
											   var filelist = jQuery("#"+thisID).data("filelist");
											   var limitCount = settings.queueSizeLimit;
											   if(filelist.length >= limitCount){
													jQuery("#scduploadifyuploadtable").hide();
											   }
										   }
									   });
								   }
							},"json");
						}
					}
					/**增加一个 下载iframe**/	
					jQuery(this).append("<iframe id='"+thisID+"Iframe' style='display: none;'></iframe>");
		  /********结束初始化一个组件  end***/		
				});
		    } ,  
		 /***扩展函数**/
		scduploadifySettings:function(settingName, settingValue, resetObject) {
			var returnValue = false;
			jQuery(this).each(function() {
				var uploadifyID=jQuery(this).attr('id')+"uploadify";
				if (settingName == 'scriptData' && settingValue != null) { //给scriptData赋值
					if (resetObject) {
						var scriptData = settingValue;
					} else {
						var scriptData = jQuery.extend(jQuery(this).data('settings').scriptData, settingValue);
						var uploadifyData=jQuery.extend(jQuery("#"+uploadifyID).data('settings').scriptData,settingValue);
					}
					var scriptDataString = '';
					for (var name in scriptData) {
						scriptDataString += '&' + name + '=' + scriptData[name];
					}
					settingValue = escape(scriptDataString.substr(1));
				}
				//更新flash参数
				returnValue = document.getElementById(uploadifyID + 'Uploader').updateSettings(settingName, settingValue);
			});
			if (settingValue == null) {
				if (settingName == 'scriptData') {
					var returnSplit = unescape(returnValue).split('&');
					var returnObj   = new Object();
					for (var i = 0; i < returnSplit.length; i++) {
						var iSplit = returnSplit[i].split('=');
						returnObj[iSplit[0]] = iSplit[1];
					}
					returnValue = returnObj;
				}
			}
			return returnValue;
		 },
		 scduploadifyUpload:function(ID,checkComplete) {
			jQuery(this).each(function() {
				var uploadifyID = jQuery(this).attr('id')+"uploadify";
				if (!checkComplete) checkComplete = false;
				document.getElementById( uploadifyID+ 'Uploader').startFileUpload(ID, checkComplete);
			});
		},
		scduploadifyCancel:function(ID) {
			jQuery(this).each(function() {
				var uploadifyID=jQuery(this).attr('id')+"uploadify";
				document.getElementById( uploadifyID+ 'Uploader').cancelFileUpload(ID, true, true, false);
			});
		},
		scduploadifyClearQueue:function() {
			jQuery(this).each(function() {
				var uploadifyID=jQuery(this).attr('id')+"uploadify";
				document.getElementById(uploadifyID + 'Uploader').clearFileUploadQueue(false);
			});
		},
		scduploadifyfilenamelist:function(){
			var filename="";
			var fileitem="";
			jQuery(this).each(function() {
				var filelist=jQuery(this).data("filelist");
				if(filelist.length>0){
					filename="[";
					jQuery.each(filelist,function(i,n){
						fileitem="{\"id\":\""+n.id;
						fileitem=fileitem+"\",\"desc\":\""+encodeURI(n.filedesc)+"\"}";
						if(i==0){
							filename+=fileitem;
						}
						else{
							filename=filename+","+fileitem;
						}
				    });
				    filename+="]";
				}
			})
			return filename;
		}
		 /**扩展结束***/
		})
})(jQuery);

/**把文件绑定到文件列表table**/
function scduploadifyBindToTable(tableid,mode,fileobject,uploadifyID){
	var lang = jQuery("#"+uploadifyID).data('lang');
	var filetable=jQuery("#"+tableid);
	var tabletr=jQuery("<tr>",{
		'id':fileobject.id
	}); 
	filetable.append(tabletr);
	var fields=jQuery("#"+uploadifyID).data('settings').fields;
	var haveFilefunc=false;
	jQuery.each(fields,function(i,item){
		if(item.value!='func'){
			tabletr.append("<td>"+fileobject[item.value]+"</td>");
		}else{
			haveFilefunc=true;
		}
	});
	if(!haveFilefunc){
		return;
	}
	var tabletd;
	tabletd =jQuery("<td>",{});
		tabletr.append(tabletd);//功能
		jQuery("<label>",{
			'class':'deletebutton',
			'click':function(){
			    scduploadifyDownloadfile(uploadifyID,fileobject.id);
			},
		    'text':scduploadifyLanguage[lang]["download"]
		}).css("cursor","pointer").appendTo(tabletd);
	tabletd.append("&nbsp;&nbsp;");
	if(mode=="add" || mode=="alter"){	
		jQuery("<label>",{
			"class":'deletebutton',
			"click":function(){
			    scduploadifyDeletefile(uploadifyID,fileobject.id);
			},
		    'text':scduploadifyLanguage[lang]["delete"]
		}).css("cursor","pointer").appendTo(tabletd);
	}
}

/**删除文件**/
function scduploadifyDeletefile(scduploaifyID,id){
	var alertObject = typeof(jQuery.messager);
	var lang = jQuery("#"+scduploaifyID).data('lang');
	if( undefined != alertObject){
	   $.messager.confirm(scduploadifyLanguage[lang]["info"], scduploadifyLanguage[lang]["deleteinfo"], function(r){
			if (r){
			    var entitypath = jQuery("#"+scduploaifyID).data("settings").entitypath;
				var deleteurl = jQuery("#"+scduploaifyID).data("settings").filehandlerurl+"delete?id="+id;
				
				$.ajax({
					type:"get",
					url:deleteurl,
					success: function(data){
						if(data == "success"){
							jQuery("#"+id).remove();
						    var filelist = jQuery("#"+scduploaifyID).data("filelist");
						    jQuery.each(filelist,function(i,n){
						    	if(n.id==id){
						    		filelist.splice(i,1);
						    	}
						    });
						    
						    jQuery("#"+scduploaifyID).data("filelist",filelist);
							var limitCount =  jQuery("#"+scduploaifyID).data("settings").queueSizeLimit;
							if(filelist.length >= limitCount){
								jQuery("#scduploadifyuploadtable").hide();
							}else{
								jQuery("#scduploadifyuploadtable").show();
							}
						}else{
							$.messager.alert(scduploadifyLanguage[lang]["info"],
									scduploadifyLanguage[lang]["infoerror"],"error");
						}
					},
					error:function(data){
						$.messager.alert(scduploadifyLanguage[lang]["info"],
								scduploadifyLanguage[lang]["infoerror"],"error");
					}
				});
			}
		});
	}else{
	   if(!confirm(scduploadifyLanguage[lang]["deleteinfo"])){
		   return;
	   }else{
		    var entitypath = jQuery("#"+scduploaifyID).data("settings").entitypath;
			var deleteurl = jQuery("#"+scduploaifyID).data("settings").filehandlerurl+"delete?id="+id;
			
			$.ajax({
				type:"get",
				url:deleteurl,
				success: function(data){
					if(data == "success"){
						jQuery("#"+id).remove();
					    var filelist = jQuery("#"+scduploaifyID).data("filelist");
					    jQuery.each(filelist,function(i,n){
					    	if(n.id==id){
					    		filelist.splice(i,1);
					    	}
					    });
					    jQuery("#"+scduploaifyID).data("filelist",filelist);
					    
					    jQuery("#"+scduploaifyID).data("filelist",filelist);
						var limitCount =  jQuery("#"+scduploaifyID).data("settings").queueSizeLimit;
						if(filelist.length >= limitCount){
							jQuery("#scduploadifyuploadtable").hide();
						}else{
							jQuery("#scduploadifyuploadtable").show();
						}
					}else{
						alert(scduploadifyLanguage[lang]["infoerror"]);
					}
				},
				error:function(data){
					alert(scduploadifyLanguage[lang]["infoerror"]);
				}
			});
		}
	}
}

/**下载文件**/
function scduploadifyDownloadfile(scduploaifyID,id){
	var downloadurl = jQuery("#"+scduploaifyID).data("settings").filehandlerurl+"down?id="+id;
	var entitypath = jQuery("#"+scduploaifyID).data("settings").entitypath;
	jQuery("#"+scduploaifyID+"Iframe").attr("src",downloadurl);
}

/**获取文件**/
function scduploadifyGetFileType(filetypelist,filetypeval){
	var filetype="";
	jQuery.each(filetypelist,function(i,n){
		if(n.value==filetypeval)filetype=n.name;
	});
	return filetype;
}

/**获取多个控件的所有附件
*scduploadifylist 为所有的控件的字符串数组***/
function scduploadifygetallfile(scduploadifylist){
	if(scduploadifylist.length==0){
		return "";
	}
	var filename="";
	var fileitem="";
	filename="[";
	var alllength = 0;
	jQuery.each(scduploadifylist,function(k,scdid) {
		var filelist=jQuery("#"+scdid).data("filelist");
		if(filelist.length>0){	
			jQuery.each(filelist,function(i,n){
				fileitem="{\"id\":\""+n.id;
				fileitem=fileitem+"\",\"documentdescription\":\""+n.filedesc+"\"}";
				if(alllength==0){
					filename+=fileitem;
				}
				else{
					filename=filename+","+fileitem;
				}
				alllength++;
		    });  
		}
	});
	filename+="]";
	if(filename=="[]"){filename="";}
	return filename;
}

/**计算大小**/
function convertFileSize(filesize) 
{ 
	var strUnit="Bytes"; 
	var strAfterComma=""; 
	var intDivisor=1; 
	if(filesize>=1024*1024) { 
		strUnit = "MB"; 
		intDivisor=1024*1024; 
	} 
	else if(filesize>=1024) { 
		strUnit = "KB"; 
		intDivisor=1024; 
	} 
	if(intDivisor==1) return filesize + " " + strUnit; 
	return (filesize / intDivisor).toFixed(2) + " " + strUnit; 
} 

/**文件类型改变**/
function scduploadifyfiletypechange(uploadifyIDs){
   var ext = jQuery("#"+uploadifyIDs+"filetype option:selected");
   if(ext.length ==1){
	    var extStr = ext[0].fileext;
	    if(extStr != "undefined" && extStr!=null){
	    	var extName = jQuery(ext[0]).html();
	        jQuery("#"+uploadifyIDs+"uploadify").uploadifySettings("fileExt" , extStr,"1");
	        jQuery("#"+uploadifyIDs+"uploadify").uploadifySettings("fileDesc",extName+"("+extStr+")" ,"1");
	    }
	    else{
	    	jQuery("#"+uploadifyIDs+"uploadify").uploadifySettings("fileExt" , "*.*","1");
	        jQuery("#"+uploadifyIDs+"uploadify").uploadifySettings("fileDesc","(*.*)" ,"1");
	    }
   }
}

var scduploadifyLanguage={
	"ZH_CN":{
	     "name":"文件名",
	     "type":"文件类型",
	     "size":"文件大小",
	     "desc":"文件说明",
	     "func":"功能",
	     "filelimit":"上传附件大小不大于",
	     "filecount":"上传附件数量不超过",
	     "error":"错误",
	     "info":"提示",
	     "failedinfo":"文件上传失败",
	     "failederror":"上传出错,上传附件大小不大于",
	     "desclimit":"文件说明不能大于2000字！",
	     "selectinfo":"没有选中文件",
	     "upload":"上传",
	     "clear":"清空",
	     "download":"下载",
	     "delete":"删除",
	     "deleteinfo":"确认删除？",
		 "deleteerror":"删除失败"
	},
	"EN_US":{
		 "name":"Filename",
	     "type":"Filetype",
	     "size":"Filesize",
	     "desc":"Description",
	     "func":"Function",
	     "filelimit":"Upload attachment size is less than",
	     "filecount":"Upload attachment count is less than",
	     "error":"Error",
	     "info":"Info",
	     "failedinfo":"File upload failed",
	     "failederror":"File upload error,Upload attachment size is less than",
	     "desclimit":"Description of the file cannot be greater than 2000 characters",
	     "selectinfo":"No selected files",
	     "upload":"Upload",
	     "clear":"Clear",
	     "download":"Download",
	     "delete":"Delete",
	     "deleteinfo":"Confirm delete?",
	     "deleteerror":"Delete failed"
	}
}