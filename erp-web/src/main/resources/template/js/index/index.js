
var loadBottomMenuIcon;//是否只展示图标，1是  0否

var friendList = null;//好友列表
var friendChooseList = "";//已选中群组成员

var etiger = {};//socket

var layim = '';//聊天对象

var childParams = {};//子页面操作时传递的值

var parentRowId;//index所需要修改东西时的id

layui.config({
    base: basePath, //指定 winui 路径
    version: skyeyeVersion
}).extend({  //指定js别名
    window: 'js/winui.window',
    desktop: 'js/winui.desktop',//桌面加载模块
    start: 'js/winui.start',//左下角开始菜单
    helper: 'js/winui.helper'
}).define(['window', 'desktop', 'start', 'helper', 'layim', 'radialin', 'contextMenu', 'dragula', 'fullcalendar', 'jqueryUI', 'form', 'laydate'], function (exports) {
    var $ = layui.jquery,
    	form = layui.form,
    	laydate = layui.laydate;
    var winuiLoad;
    layim = layui.layim;
    
    var userId = "";
    
    $(document).attr("title", sysMessageMainMation.mationTitle);
    $(".load-title").find("font").html(sysMessageMainMation.mationTitle + $(".load-title").find("font").html());
    
    //页面加载进度条
    winuiLoad = radialIndicator($('#winui-load'), {
    	barBgColor: '#E3E3E3',
        barColor: '#8A2BE2',
        radius: 100,
        barWidth: 5,
        initValue: 0,
        fontSize: 15,
        percentage: true,
        end: function(val){
        	if(val == '100'){
        		$(".winui-load-mation").hide(1000);
        	}
        }
    });
    
    $(function () {
    	var loadIndex = 20;
    	winuiLoad.animate(loadIndex);
    	var loadInterval = setInterval(function(){ 
    		if(loadIndex < 90){
    			loadIndex = loadIndex + 3;
    			winuiLoad.animate(loadIndex);
    		}else{
    			clearInterval(loadInterval);
    		}
    	}, 10);
    	AjaxPostUtil.request({url:reqBasePath + "login002", params:{}, type:'json', callback:function(json){
   			if(json.returnCode == 0){
   				$("#userPhoto").attr("src", fileBasePath + json.bean.userPhoto);
   				$("#userName").html(json.bean.userCode + '(' + json.bean.userName + ')');
   				userId = json.bean.id;
   				loadBottomMenuIcon = json.bean.loadBottomMenuIcon;
   				if(isNull(json.bean.winBgPicUrl)){
   					json.bean.winBgPicUrl = fileBasePath + '/images/upload/winbgpic/default.jpg';
   				}else{
   					json.bean.winBgPicUrl = fileBasePath + json.bean.winBgPicUrl;
   				}
   				if(isNull(json.bean.winLockBgPicUrl)){
   					json.bean.winLockBgPicUrl = fileBasePath + '/images/upload/winbgpic/default.jpg';
   				}else{
   					json.bean.winLockBgPicUrl = fileBasePath + json.bean.winLockBgPicUrl;
   				}
   				//加载win系统内容
   		        initWinConfig(json);
   		        //加载聊天
   		        initTalk();
   		        //加载右键
   		        initRightMenu();
	   		    //扩展桌面助手工具
	   		    winui.helper.addTool([{
	   		         tips: '锁屏',
	   		         icon: 'fa-power-off',
	   		         click: function (e) {
	   		             winui.lockScreen(function (password) {
	   		            	if(!isNull(password)){
		   		         		var pJudge = false;
		   		         		AjaxPostUtil.request({url:reqBasePath + "login008", params:{password: password}, type:'json', callback:function(json){
		   		      	   			if(json.returnCode == 0){
		   		      	   				pJudge = true;
		   		      	   			}else{
		   		      	   				pJudge = false;
		   		      	   				winui.window.msg(json.returnMessage, { shift: 6 });
		   		      	   			}
		   		      	   		}, async: false});
		   		         		return pJudge;
		   		         	}else{
		   		         		winui.window.msg('请输入密码', { shift: 6 });
		   		                 return false;
		   		         	}
	   		             });
	   		         }
	   		     }, {
	   		         tips: '消息通知',
	   		         icon: 'fa-list-ul',
	   		         click: function (e) {
	   		        	winui.window.openSysNotice(loadBottomMenuIcon);
	   		         }
	   		     }]);
	   		     
	   		     //进度条加载到100
  		         if(loadIndex != 100){
  		        	clearInterval(loadInterval);
  		        	winuiLoad.animate(100);
  		         }
	   		     
	   		     // 判断是否显示锁屏（这个要放在最后执行）
	   		     if (window.localStorage.getItem("lockscreen") == "true") {
	   		         winui.lockScreen(function (password) {
	   		           	 if(!isNull(password)){
	   		        		 var pJudge = false;
	   		        		 AjaxPostUtil.request({url:reqBasePath + "login008", params:{password: password}, type:'json', callback:function(json){
	   		     	   			 if(json.returnCode == 0){
	   		     	   				 pJudge = true;
	   		     	   			 }else{
	   		     	   				 pJudge = false;
	   		     	   				 winui.window.msg(json.returnMessage, { shift: 6 });
	   		     	   			 }
	   		     	   		 }, async: false});
	   		        		 return pJudge;
	   		        	 }else{
	   		        		 winui.window.msg('请输入密码', { shift: 6 });
	   		                 return false;
	   		        	 }
	   		         });
	   		     }
		   		 
   			}else{
   				location.href = "login.html";
   			}
   		}});
    });
    
    //初始化右键
    function initRightMenu(){
    	$("body").contextMenu({
			width: 150, // width
			itemHeight: 30, // 菜单项height
			bgColor: "#FFFFFF", // 背景颜色
			color: "#0A0A0A", // 字体颜色
			fontSize: 12, // 字体大小
			hoverBgColor: "#99CC66", // hover背景颜色
			target: function(ele) { // 当前元素
			},
			menu: [{ // 菜单项
					text: "新建",
					icon: "fa fa-plus-square",
					children: [{
						text: "盒子",
						icon: "fa fa-dropbox",
						callback: function() {
							_openNewWindows({
								url: "../../tpl/index/createMenuBox.html", 
								title: "新建盒子",
								pageId: "createMenuBoxDialog",
								area: ['600px', '200px'],
								skin: 'top-message-mation',
								callBack: function(refreshCode){
					                if (refreshCode == '0') {
					                	var boxStr = '<div class="winui-desktop-item win-menu-group" id="' + childParams.id + '" win-id="' + childParams.id + '" win-url="--" win-title="' + childParams.menuBoxName + '" win-opentype="2" win-maxopen="-1" win-menuiconbg="#44adb1">'
														+ '<div class="winui-icon winui-icon-font">'
															+ '<div class="icon-drawer"></div>'
															+ '<div class="icon-child">'
															+ '</div>'
														+ '</div>'
														+ '<p>' + childParams.menuBoxName + '</p>'
													+ '</div>';
					                	$("#winui-desktop").html($("#winui-desktop").html() + boxStr);
					                	//重新排列桌面
					    				winui.util.locaApp();
					    				//重新初始化拖拽事件
					    				initMenuToBox();
					    				winui.util.reloadOnClick(function (id, elem) {
					                    	var item = $(elem);
					                    	if(item.find(".icon-drawer").length > 0){
					                    		showBigWin(elem);
					                    	}else{
					                    		OpenWindow(elem);
					                    	}
					                    });
					                	top.winui.window.msg("操作成功", {icon: 1,time: 2000});
					                } else if (refreshCode == '-9999') {
					                	top.winui.window.msg("操作失败", {icon: 2,time: 2000});
					                }
								}});
						}
					}, {
						text: "快捷方式",
						icon: "fa fa-chain",
						callback: function() {
							_openNewWindows({
								url: "../../tpl/index/createMenu.html", 
								title: "新建快捷方式",
								pageId: "createMenuDialog",
								area: ['700px', '450px'],
								skin: 'top-message-mation',
								callBack: function(refreshCode){
					                if (refreshCode == '0') {
					                	var str = '';
					                	var iconTypeI = '';
					                	var menuIcon = '';
					                	if(childParams.menuIconType === '1' || childParams.menuIconType == 1){
					                		str = '<i class="fa ' + childParams.menuIcon + ' fa-fw" win-i-id="' + childParams.id + '"></i>';
					                		iconTypeI = 'winui-icon-font';
					                		menuIcon = childParams.menuIcon;
					                	}else if(childParams.menuIconType === '2' || childParams.menuIconType == 2){
					                		str = '<img src="' + fileBasePath + childParams.menuIconPic + '"/>';
					                		iconTypeI = 'winui-icon-img';
					                		menuIcon = childParams.menuIconPic;
					                	}
					                	var boxStr = '<div class="winui-desktop-item sec-btn winui-this" win-id="' + childParams.id + '" win-url="' + childParams.menuUrl + '" win-title="' + childParams.titleName + '" win-opentype="' + childParams.openType + '" win-maxopen="-1" win-menuiconbg="' + childParams.menuIconBg + '" win-menuiconcolor="' + childParams.menuIconColor + '" win-icon="' + menuIcon + '">'
								                		+ '<div class="winui-icon ' + iconTypeI + '" style="background-color: ' + childParams.menuIconBg + '; color:' + childParams.menuIconColor + '">' + str + '</div>'
								                		+ '<p>' + childParams.menuName + '</p>'
								                	'</div>';
					                	$("#winui-desktop").html($("#winui-desktop").html() + boxStr);
					                	//重新排列桌面
					    				winui.util.locaApp();
					    				//重新初始化拖拽事件
					    				initMenuToBox();
					    				winui.util.reloadOnClick(function (id, elem) {
					                    	var item = $(elem);
					                    	if(item.find(".icon-drawer").length > 0){
					                    		showBigWin(elem);
					                    	}else{
					                    		OpenWindow(elem);
					                    	}
					                    });
					    				//重置右键事件
					                    initDeskTopMenuRightClick();
					                	top.winui.window.msg("操作成功", {icon: 1,time: 2000});
					                } else if (refreshCode == '-9999') {
					                	top.winui.window.msg("操作失败", {icon: 2,time: 2000});
					                }
								}});
						}
					}]
				}, {
					text: "--",
				}, {
					text: "全屏模式",
					icon: "fa fa-fw fa-object-group",
					callback: function() {
						fullScreen();
					}
				}, {
					text: "退出全屏",
					icon: "fa fa-fw fa-object-ungroup",
					callback: function() {
						exitFullScreen();
					}
				}, {
					text: "--",
				}, {
					text: "主题设置",
					icon: "fa fa-fw fa-paw",
					callback: function() {
						winui.window.openTheme(loadBottomMenuIcon);
					}
				}, {
					text: "锁屏",
					icon: "fa fa-fw fa-power-off",
					callback: function() {
						winui.lockScreen(function (password) {
	   		            	if(!isNull(password)){
		   		         		var pJudge = false;
		   		         		AjaxPostUtil.request({url:reqBasePath + "login008", params:{password: password}, type:'json', callback:function(json){
		   		      	   			if(json.returnCode == 0){
		   		      	   				pJudge = true;
		   		      	   			}else{
		   		      	   				pJudge = false;
		   		      	   				winui.window.msg(json.returnMessage, { shift: 6 });
		   		      	   			}
		   		      	   		}, async: false});
		   		         		return pJudge;
		   		         	}else{
		   		         		winui.window.msg('请输入密码', { shift: 6 });
		   		                return false;
		   		         	}
	   		             });
					}
				}, {
					text: "--",
				}, {
					text: "刷新",
					icon: "fa fa-refresh",
					callback: function() {
						window.location.reload(true);
					}
				}, {
					text: "清除缓存",
					icon: "fa fa-trash-o",
					callback: function() {
						winui.window.msg('缓存清楚成功，请刷新页面', { shift: 1 });
						localStorage.clear();
						sessionStorage.clear();
					}
				}, {
					text: "--",
				}, {
					text: "退出系统",
					icon: "fa fa-sign-out",
					callback: function() {
						winui.hideStartMenu();
						winui.window.confirm('确认注销吗?', {id: 'exit-confim', icon: 3, title: '提示', skin: 'msg-skin-message', success: function(layero, index){
							var times = $("#exit-confim").parent().attr("times");
							var zIndex = $("#exit-confim").parent().css("z-index");
							$("#layui-layer-shade" + times).css({'z-index': zIndex});
						}}, function (index) {
				        	AjaxPostUtil.request({url:reqBasePath + "login003", params:{}, type:'json', callback:function(json){
				 	   			if(json.returnCode == 0){
					 	   			if (etiger != null) {
						 	   			etiger.socket.close();
						 	   		}
					 	   			location.href = "login.html";
				 	   			}else{
					 	   			if (etiger != null) {
						 	   			etiger.socket.close();
						 	   		}
				 	   				location.href = "login.html";
				 	   			}
				 	   		}});
				        });
					}
				}, {
					text: "关于",
					icon: "fa fa-info-circle",
					callback: function() {
						winui.window.config({
				            anim: 0,
				            miniAnim: 0,
				            maxOpen: -1
				        }).open({
				            id: '关于我们',
				            type: 1,
				            title: '<i class="fa fa-fw title-icon fa-info-circle" style="background-color: #0491fe;color: #ecf3f8;"></i><font class="win-title-class">关于我们</font>',
				            content: '<p style="padding:20px;">万鹏信息系统有限公司<br/><br/>官方网站：http://www.gzwpinfo.com/<br/><br/>版权：<br/><br/>作者：万鹏信息系统有限公司<br/><br/>版本：1.0.0</p>',
				            loadBgColor: '#0491fe',
				            loadIcon: 'fa fa-info-circle',
				            loadIconColor: '#ecf3f8',
				            area: ['400px', '400px'],
				            maxOpen: '-1',
				            loadBottomMenuIcon: loadBottomMenuIcon,
				            iconTitle: '<i class="fa fa-fw title-icon-big fa-info-circle" style="background-color: #0491fe;color: #ecf3f8;"></i>',
				            refresh:true
				        });
					}
				}
			]
		});
    }
    
    function initTalk(){
    	layim.config({
			brief: false,// 是否简约模式（如果true则不显示主面板）
			title: '天眼',
			init: {
				url: reqBasePath + "companychat001",
				data: {userToken: getCookie('userToken'), loginPCIp: returnCitySN["cip"]}
			},//好友接口
			members: {
				url: reqBasePath + 'companytalkgroup007',
				data: {userToken: getCookie('userToken'), loginPCIp: returnCitySN["cip"]}
			},//群员借口
			uploadImage: {
				url: reqBasePath + "common003?type=9", //（返回的数据格式见下文）
				type: '' //默认post
			},//上传图片接口
			uploadFile: {
				url: reqBasePath + "common003?type=10", //（返回的数据格式见下文）
				type: '' //默认post
			},//上传文件接口
			initSkin: '5.jpg', //1-5 设置初始背景
			notice: true, //是否开启桌面消息提醒，默认false
			msgbox: '../../tpl/index/invitation.html', //消息盒子页面地址，若不开启，剔除该项即可
			joingroup: {
				url: '../../tpl/index/searchMation.html' //加入群聊页面地址，若不开启，剔除该项即可
			}, 
			customMation: {
				url: '../../tpl/index/customMation.html' //加入群聊页面地址，若不开启，剔除该项即可
			}, 
			find: {
				url: '../../tpl/index/createGroup.html', //发现页面地址，若不开启，剔除该项即可
				brforeCallback: function(){
					friendList = $(".layim-list-friend").prop("outerHTML");
				},//加载页面前执行的方法
				returnCallback: function(){
					var toStrId = friendChooseList.split(",");
					$.each(toStrId, function(i, item){
						if(!isNull(item)){
							var sendMessage = {
								to: item,//收信人id
								type: 7,//群组邀请消息
							};
							etiger.socket.send(JSON.stringify(sendMessage));
						}
					});
				}
			},
			chatLog: '../../tpl/index/chatLog.html', //聊天记录页面地址，若不开启，剔除该项即可
		});
    	
    	//聊天socket
    	etiger.socket = {
			webSocket : "",
			init : function() {
				if (!window.WebSocket) {
					alert("你的浏览器不支持websocket，请升级到IE10以上浏览器，或者使用谷歌、火狐、360浏览器。");
					return;
				}
				webSocket = new WebSocket(webSocketPath + "talkwebsocket/" + userId);
				webSocket.onerror = function(event) {
					alert("websockt连接发生错误，请刷新页面重试!")
				};
				webSocket.onopen = function(event) {
					// webSocket.send("_online_user_"+currentId);
				};
				webSocket.onmessage = function(event) {
					var received_msg = event.data;
	    			try {
	            	    if (typeof JSON.parse(received_msg) == "object") {
	            		    var jsonData = JSON.parse(received_msg);
	            		    if(jsonData.messageType == '1'){//上线提醒
	            		    	layim.setFriendStatus(jsonData.userId, 'online');
	            		    }else if(jsonData.messageType == '2'){//下线提醒
	            		    	layim.setFriendStatus(jsonData.userId, 'offline');
	            		    }else if(jsonData.messageType == '3'){//在线名单
	            		    	$.each(jsonData.onlineUsers, function(i, item){
	            		    		layim.setFriendStatus(item, 'online');
	            		    	});
	            		    }else if(jsonData.messageType == '4'){//普通消息
	    						var mine = jsonData.fromId;
	    						var name = jsonData.username;
	    						var avatar = jsonData.avatar;
	    						var content = jsonData.textMessage;
	    						layim.getMessage({
	    							username : name,
	    							avatar : avatar,
	    							id : mine,
	    							type : 'friend',
	    							content : content
	    						});
	            		    }else if(jsonData.messageType == '5'){//系统消息
	            		    	
	            		    }else if(jsonData.messageType == '6'){//全体消息
	            		    	
	            		    }else if(jsonData.messageType == '7'){//群组邀请消息
	            		    	var messageTotal = layim.getMessageTotal();
	            		    	messageTotal++;
	            		    	layim.msgbox(messageTotal);
	            		    }else if(jsonData.messageType == '8'){//隐身消息
	            		    	layim.setFriendStatus(jsonData.userId, 'offline');
	            		    }else if(jsonData.messageType == '9'){//隐身上线消息
	            		    	layim.setFriendStatus(jsonData.userId, 'online');
	            		    }else if(jsonData.messageType == '10'){//搜索账号入群审核同意后通知用户加载群信息
	    						var groupId = jsonData.id;
	    						var groupname = jsonData.groupname;
	    						var avatar = jsonData.avatar;
	    						layim.addList({
			 	   					type: 'group', //列表类型，只支持friend和group两种
			 	   					avatar: avatar, //群组头像
			 	   					groupname: groupname, //群组名称
			 	   					id: groupId, //群组id
			 	   				});
	            		    }else if(jsonData.messageType == '11'){//群聊
	            		    	var groupId = jsonData.id;
	    						var name = jsonData.username;
	    						var avatar = jsonData.avatar;
	    						var content = jsonData.textMessage;
	    						layim.getMessage({
	    							username : name,
	    							avatar : avatar,
	    							id : groupId,
	    							type : 'group',
	    							content : content
	    						});
	            		    }else if(jsonData.messageType == '12'){//退出群聊--群主接收消息
	            		    	var groupId = jsonData.groupId;
	            		    	var userName = jsonData.userName;
	            		    	layim.getMessage({
	    							username : '系统消息',
	    							avatar : '../../assets/images/eye.png',
	    							id : groupId,
	    							type : 'group',
	    							content : userName + '退出了群聊。'
	    						});
	            		    }else if(jsonData.messageType == '13'){//解散群聊--群员接收消息
	            		    	var groupId = jsonData.id;
	            		    	var userName = jsonData.userName;
	            		    	layim.getMessage({
	    							username : '系统消息',
	    							avatar : '../../assets/images/eye.png',
	    							id : groupId,
	    							type : 'group',
	    							content : userName + '解散了群聊。'
	    						});
	            		    	layim.removeList({
	            		            id: groupId,
	            		            type: 'group'
	            		    	});
	            		    }else if(jsonData.messageType == '1301'){//群聊被解散后，成员再次发送消息，返回的内容
	            		    	var groupId = jsonData.groupId;
	            		    	layim.getMessage({
	    							username : '系统消息',
	    							avatar : '../../assets/images/eye.png',
	    							id : groupId,
	    							type : 'group',
	    							content : '该群聊已被解散，消息发送失败。'
	    						});
	            		    }
	            	    }
	                } catch(e) {
	                	console.log(e);
	                }
				}
			},
			send : function(data) {
				this.waitForConnection(function() {// 连接建立才能发送消息
					webSocket.send(data);
				}, 500);
			},
			sendData : function(data) {
				this.waitForConnection(function() {
					webSocket.send(data);
				}, 500);
			},
			waitForConnection : function(callback, interval) {// 判断连接是否建立
				if (webSocket.readyState === 1) {
					callback();
				} else {
					var that = this;
					setTimeout(function() {
						that.waitForConnection(callback, interval);
					}, interval);
				}
			},
			close: function(){
				webSocket.close();
			}
		};
    	
    	//监听在线状态的切换事件
		layim.on('online', function(data) {
			if(data == 'online'){//上线
				var sendMessage = {
					userId: userId,//发送人id
					type: 9,
				};
				etiger.socket.send(JSON.stringify(sendMessage));
			}else if(data == 'hide'){//下线
				var sendMessage = {
					userId: userId,//发送人id
					type: 8,
				};
				etiger.socket.send(JSON.stringify(sendMessage));
			}
		});
		
		//监听签名修改
		layim.on('sign', function(value) {
			AjaxPostUtil.request({url:reqBasePath + "companychat002", params:{userSign: value}, type:'json', callback:function(json){
 	   			if(json.returnCode == 0){
 	   				winui.window.msg('保存成功', {icon: 1, skin: 'msg-skin-message'});
 		   		}else{
 	   				winui.window.msg(json.returnMessage, {shift: 6, skin: 'msg-skin-message'});
 	   			}
 	   		}});
		});
		
		//监听layim建立就绪
		layim.on('ready', function(res) {
			//初始化websocket
	    	etiger.socket.init();
			layim.msgbox(0); //模拟消息盒子有新消息，实际使用时，一般是动态获得
			//判断当前是否是锁屏状态，以防止刷新后依然可以进行聊天
			if (window.localStorage.getItem("lockscreen") == "true") {
				$('.talk-btn').hide();
			}
		});
		
		//监听发送消息
		layim.on('sendMessage', function(data) {
			var To = data.to;
			var mine = data.mine;
			if(To.type === 'group') {
				var sendMessage = {
					username: mine.username,
					avatar: mine.avatar,
					to: To.id,
					type: 11,
					userId: userId,
					message: data.mine.content
				};
				etiger.socket.send(JSON.stringify(sendMessage));
			} else {//个人聊天
				var sendMessage = {
					message: data.mine.content,//消息
					userId: userId,//发送人id
					to: To.id,//收信人id
					type: 4,
					username: mine.username,
					avatar: mine.avatar
				};
				etiger.socket.send(JSON.stringify(sendMessage));
			}
		});
		
		//监听查看群员
		layim.on('members', function(data) {
			console.log(data);
		});

		//监听聊天窗口的切换
		layim.on('chatChange', function(res) {
			var type = res.data.type;
			console.log(res.data.id)
			if(type === 'friend') {
				//模拟标注好友状态
			} else if(type === 'group') {
				//模拟系统消息
			}
		});
    }
    
    //初始化配置信息
    function initWinConfig(json){
    	//设置窗口点击事件
    	$("body").on("click", ".sec-clsss-btn", function(e){
    		winui.window.close($('#childWindow').parent());
    		OpenWindow($(this).prop("outerHTML"));
    	});

    	winui.config({
            settings: {
                color: json.bean.winThemeColor,
                taskbarMode: json.bean.winTaskPosition,
                startSize: json.bean.winStartMenuSize,
                bgSrc: json.bean.winBgPicUrl,
                lockBgSrc: json.bean.winLockBgPicUrl,
                vagueBgSrc: json.bean.winBgPicVague,
                vagueBgSrcValue: json.bean.winBgPicVagueValue
            },
            desktop: {//桌面菜单栏
                options: {
                	url: reqBasePath + 'login004',
                    method: 'get',
                    data: {loginPCIp: returnCitySN["cip"]}
                },    //可以为{}
                done: function (desktopApp) {
                    desktopApp.onclick(function (id, elem) {
                    	var item = $(elem);
                    	if(item.find(".icon-drawer").length > 0){
                    		showBigWin(elem);
                    	}else{
                    		OpenWindow(elem);
                    	}
                    }),
                    //重置右键事件
                    initDeskTopMenuRightClick();
                }
            },
            menu: {//左下角菜单栏右键效果
                options: {
                    url: reqBasePath + 'login005',
                    method: 'get',
                    data: {loginPCIp: returnCitySN["cip"]}
                },
                done: function (menuItem) {
                    //监听开始菜单点击
                    menuItem.onclick(function (elem) {
                        OpenWindow(elem);
                    });
                    menuItem.contextmenu({
                        item: [{
                        	icon: 'fa-folder-open-o', 
                        	text: '打开',
                        	callBack: function (id, elem) {
                            	var item = $(elem);
                            	if(item.find(".icon-drawer").length > 0){
                            		showBigWin(elem);
                            	}else{
                            		OpenWindow(elem);
                            		winui.window.close($('#childWindow').parent());
                            	}
                            }
                        }, {
                        	icon: ' fa-copy', 
                        	text: '发送到桌面',
                        	callBack: function (id, elem) {
                        		AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop010", params:{rowId: id}, type:'json', callback:function(json){
                        			if(json.returnCode == 0){
        			 	   				top.winui.window.msg("操作成功", {icon: 1,time: 2000});
	        			 	   			var thisMenuIcon = json.bean.icon;
	        		        			var thisMenuBg = json.bean.menuIconBg;
	        		        			var thisMenuIconColor = json.bean.menuIconColor;
	        		        			var thisMenuId = json.bean.id;
	        		        			var thisMenuUrl = json.bean.pageURL;
	        		        			var thisMenuTitle = json.bean.title;
	        		        			var thisMenuOpenType = json.bean.openType;
	        		        			var thisMenuMaxOpen = json.bean.maxOpen;
        			 	   				var menuIconType = json.bean.menuIconType;
        			 	   				
        			 	   				var str = "";
        			 	   				var iconTypeI = ""
        			 	   				if(menuIconType === '1' || menuIconType == 1){
        			 	   					iconTypeI = "winui-icon-font";
        			 	   					thisMenuIcon = json.bean.icon;
        			 	   				}else if(menuIconType === '2' || menuIconType == 2){
        			 	   					iconTypeI = "winui-icon-img";
        			 	   					str = '<img src="' + fileBasePath + json.bean.menuIconPic + '" />';
        			 	   					thisMenuIcon = json.bean.menuIconPic;
        			 	   				}
        			 	   				if(json.bean.parentId != '0'){//二级菜单
        			 	   					var parentId = json.bean.parentId.split(',')[0];
        			 	   					if($("#winui-desktop").find('div[id="' + parentId + '"]').length == 0){//二级菜单的父菜单没有在桌面
        			 	   						var boxStr = '<div class="winui-desktop-item sec-btn winui-this" win-id="' + thisMenuId + '" win-url="' + thisMenuUrl + '" win-title="' + thisMenuTitle + '" win-opentype="' + thisMenuOpenType + '" win-maxopen="-1" win-menuiconbg="' + thisMenuBg + '" win-menuiconcolor="' + thisMenuIconColor + '" win-icon="' + thisMenuIcon + '">'
        			 	   						+ '<div class="winui-icon ' + iconTypeI + '" style="background-color: ' + thisMenuBg + '; color:' + thisMenuIconColor + '">' + str + '</div>'
        			 	   						+ '<p>' + thisMenuTitle + '</p>'
        			 	   						'</div>';
        			 	   						$("#winui-desktop").html($("#winui-desktop").html() + boxStr);
        			 	   						//重新排列桌面
        			 	   						winui.util.locaApp();
        			 	   						//重新初始化拖拽事件
        			 	   						initMenuToBox();
        			 	   						winui.util.reloadOnClick(function (id, elem) {
        			 	   							var item = $(elem);
        			 	   							if(item.find(".icon-drawer").length > 0){
        			 	   								showBigWin(elem);
        			 	   							}else{
        			 	   								OpenWindow(elem);
        			 	   							}
        			 	   						});
        			 	   						//重置右键事件
        			 	   						initDeskTopMenuRightClick();
        			 	   					}else{//二级菜单的父菜单在桌面上
        			 	   						var iTag = '<i class="fa icon-drawer-icon" win-i-id="' + thisMenuId + '">' + str + '</i>';
        			 	   						$("#winui-desktop").find('div[id="' + parentId + '"]').find('div[class="icon-drawer"]').append(iTag);
        			 	   						var menuStr = '<div class="winui-desktop-item sec-clsss-btn sec-btn" win-id="' + thisMenuId + '" win-url="' + thisMenuUrl + '" win-title="' + thisMenuTitle + '" win-opentype="' + thisMenuOpenType + '" win-maxopen="' + thisMenuMaxOpen + '" win-menuiconbg="' + thisMenuBg + '" win-menuiconcolor="' + thisMenuIconColor + '" win-icon="' + thisMenuIcon + '">'
        			 	   						+ '<div class="winui-icon ' + iconTypeI + '" style="background-color: ' + thisMenuBg + '">' + str + '</div>'
        			 	   						+ '<p>' + thisMenuTitle + '</p></div>';
        			 	   						$("#winui-desktop").find('div[id="' + parentId + '"]').find('div[class="icon-child"]').append(menuStr);
        			 	   						//重新排列桌面
        			 	   						winui.util.locaApp();
        			 	   						winui.util.reloadOnClick(function (id, elem) {
        			 	   							var item = $(elem);
        			 	   							if(item.find(".icon-drawer").length > 0){
        			 	   								showBigWin(elem);
        			 	   							}else{
        			 	   								OpenWindow(elem);
        			 	   							}
        			 	   						});
        			 	   					}
        			 	   				}else{//一级菜单
        			 	   					var boxStr = '<div class="winui-desktop-item sec-btn winui-this" win-id="' + thisMenuId + '" win-url="' + thisMenuUrl + '" win-title="' + thisMenuTitle + '" win-opentype="' + thisMenuOpenType + '" win-maxopen="-1" win-menuiconbg="' + thisMenuBg + '" win-menuiconcolor="' + thisMenuIconColor + '" win-icon="' + thisMenuIcon + '">'
        			 	   					+ '<div class="winui-icon ' + iconTypeI + '" style="background-color: ' + thisMenuBg + '; color:' + thisMenuIconColor + '">' + str + '</div>'
        			 	   					+ '<p>' + thisMenuTitle + '</p>'
        			 	   					'</div>';
        			 	   					$("#winui-desktop").html($("#winui-desktop").html() + boxStr);
        			 	   					//重新排列桌面
        			 	   					winui.util.locaApp();
        			 	   					//重新初始化拖拽事件
        			 	   					initMenuToBox();
        			 	   					winui.util.reloadOnClick(function (id, elem) {
        			 	   						var item = $(elem);
        			 	   						if(item.find(".icon-drawer").length > 0){
        			 	   							showBigWin(elem);
        			 	   						}else{
        			 	   							OpenWindow(elem);
        			 	   						}
        			 	   					});
        			 	   					//重置右键事件
        			 	   					initDeskTopMenuRightClick();
        			 	   				}
        			 		   		}else{
        			 	   				winui.window.msg(json.returnMessage, {shift: 6, skin: 'msg-skin-message'});
        			 	   			}
        			 	   		}});
                        	}
                        }, {
                        	text: '--'
                        }, {
                        	icon: 'fa-qq', 
                        	text: '自定义',
                        	callBack: function (id, elem) {
                        		winui.window.msg('自定义回调');
                        	}
                        }]
                    });
                }
            }
        }).init({
            audioPlay: true, //是否播放音乐（开机音乐只会播放一次，第二次播放需要关闭当前页面从新打开，刷新无用）
            renderBg: true //是否渲染背景图 （由于js是写在页面底部，所以不太推荐使用这个来渲染，背景图应写在css或者页面头部的时候就开始加载）
        }, function () {
            initMenuToBox();
        });
    }

    //开始菜单磁贴点击
    $('.winui-tile').on('click', function () {
        OpenWindow(this);
    });

    //开始菜单左侧主题按钮点击
    $('.winui-start-individuation').on('click', function () {
        winui.window.openTheme(loadBottomMenuIcon);
    });
    
    //个人中心点击
    $('.winui-start-syspersonal').on('click', function () {
        winui.window.openSysPersonal(loadBottomMenuIcon);
    });
    
    //展开
    $('.winui-start-item.winui-start-show').on('click', function () {
    	if($(".winui-start-left").css("width") === '210px'){//当前状态：展开
    		$(".winui-start-left").animate({
    			width: '48px', 
    		});
    		$(".winui-start-left").css({'background-color': 'rgba(0, 0, 0, 0.3)'});
    	}else{//当前状态：关闭
    		$(".winui-start-left").animate({
    			width: '210px', 
    		});
    		$(".winui-start-left").css({'background-color': 'rgba(0, 0, 0, 0.9)'});
    	}
    });
    
    $(".winui-start-left").mouseleave(function (){
    	$(".winui-start-left").animate({
			width: '48px', 
		});
    	$(".winui-start-left").css({'background-color': 'rgba(0, 0, 0, 0.3)'});
    });
    
    //打开窗口的方法（可自己根据需求来写）
    function OpenWindow(menuItem) {
		var $this = $(menuItem);
        var url = $this.attr('win-url');
        var menuIconBg = $this.attr("win-menuiconbg");
        var menuIconColor = $this.attr("win-menuiconcolor");
        var menuIcon = $this.attr("win-icon");
        var str = '', iconStr = '';
        if(menuIcon.indexOf('fa-') != -1){//icon图标
	        str = '<i class="fa fa-fw title-icon ';//图标+文字
	        iconStr = '<i class="fa fa-fw title-icon-big ';//图标
	        if(!isNull(menuIcon)){
	        	str += menuIcon;
	        	iconStr += menuIcon;
	        }
	        str += '" style="';
	        iconStr += '" style="';
	        if(!isNull(menuIconBg)){
	        	str += 'background-color: ' + menuIconBg + ';';
	        	iconStr += 'background-color: ' + menuIconBg + ';';
	        }
	        if(!isNull(menuIconColor)){
	        	str += 'color: ' + menuIconColor + ';';
	        	iconStr += 'color: ' + menuIconColor + ';';
	        }
	        str += '"></i>';
	        iconStr += '"></i>';
        }else{//图片
        	str = '<img class="title-icon" src="' + menuIcon + '"/>';
        	iconStr = '<img class="title-icon-big" src="' + menuIcon + '"/>';
        }
        var title = str + '<font class="win-title-class">' + $this.attr('win-title') + '</font>';
        var iconTitle = iconStr;
        var id = $this.attr('win-id');
        var type = parseInt($this.attr('win-opentype'));
        var maxOpen = parseInt($this.attr('win-maxopen')) || -1;
        if (url == 'theme') {
            winui.window.openTheme(loadBottomMenuIcon);
            return;
        }
        if (!url || !title || !id) {
            winui.window.msg('菜单配置错误（菜单链接、标题、id缺一不可）');
            return;
        }
        if((url.substr(0, 7).toLowerCase() == "http://"
        	|| url.substr(0, 8).toLowerCase() == "https://")
        	&& url.indexOf("/tpl/") != -1){
        	url += '?userToken=' + getCookie("userToken");
        }
        var content;
        if (type === 1) {
            $.ajax({
                type: 'get',
                url: url,
                async: false,
                success: function (data) {
                    content = data;
                },
                error: function (e) {
                    var page = '';
                    switch (e.status) {
                        case 404:
                            page = '404.html';
                            break;
                        case 500:
                            page = '500.html';
                            break;
                        default:
                            content = "打开窗口失败";
                    }
                    $.ajax({
                        type: 'get',
                        url: reqBasePath + 'tpl/sysmessage/' + page,
                        async: false,
                        success: function (data) {
                            content = data;
                        },
                        error: function () {
                            layer.close(load);
                        }
                    });
                }
            });
        } else {
            content = url;
        }
        //核心方法（参数请看文档，config是全局配置 open是本次窗口配置 open优先级大于config）
        winui.window.config({
            anim: 0,
            miniAnim: 0,
            maxOpen: -1
        }).open({
            id: id,
            type: type,
            title: title,
            content: content,
            loadBgColor: menuIconBg,
            loadIcon: menuIcon,
            loadIconColor: menuIconColor,
            area: ['90vw','90vh'],
            maxOpen: maxOpen,
            loadBottomMenuIcon: loadBottomMenuIcon,
            iconTitle: iconTitle,
            refresh:true
        });
    }
    
    //打开二级窗口
    function showBigWin(menuItem){
    	var menu = $(menuItem);
    	var str = '<div class="childWindow-title-input-box">'
    				+ '<input type="text" placeholder="请输入盒子标题" class="layui-input" value="' + menu[0].outerText + '" id="childWindowInput"/>'
    				+ '<button class="layui-btn layui-btn-primary layui-btn-sm" id="cancleChildWindow">取消</button>'
    				+ '<button class="layui-btn layui-btn-normal layui-btn-sm" id="saveChildWindow" rowid="' + menu[0].id + '">保存</button>'
    				+ '</div>';
    	winui.window.config({
            anim: 0,
            miniAnim: 0,
            maxOpen: -1
        }).open({
        	id: 'childWindow',
            type: 1,
    		title: '<font id="childWindowtext">' + menu[0].outerText + '</font>' + str,
    		closeBtn: 1,
    		fixed: false,
    		move: false,
            content: menu.find(".icon-child").html(),
            area: ['600px', '250px'],
            shadeClose: true,
            skin: 'sec-clsss top-message-mation',
            scrollbar: false,
            shade: 0.5,
            maxmin: false,
            success: function(layero, index){
            	dragula([document.getElementById('childWindow'), document.getElementById('winui-desktop')], {
            		revertOnSpill: true,
            		accepts: function(el, target) {//拖拽中
        				return target !== document.getElementById('childWindow');
        			}
            	}).on('drag', function (el) {//开始拖拽
            		var times = $("#childWindow").parent().attr("times");
            		$("#layui-layer-shade" + times).css({width: 0});
            	}).on('drop', function (el, container) {//放置
            		var times = $("#childWindow").parent().attr("times");
            		$("#layui-layer-shade" + times).css({width: '100%'});
            		var boxId = $(container).attr("win-id");//盒子id
        			var drawer = $('#' + boxId).find(".icon-drawer");//盒子icon
        			var child = $('#' + boxId).find(".icon-child");//盒子child
        			
        			var thisMenuIcon = $(el).eq(0).attr("win-icon");
        			var thisMenuBg = $(el).eq(0).attr("win-menuiconbg");
        			var thisMenuIconColor = $(el).eq(0).attr("win-menuIconColor");
        			var thisMenuId = $(el).eq(0).attr("win-id");
        			var thisMenuUrl = $(el).eq(0).attr("win-url");
        			var thisMenuTitle = $(el).eq(0).attr("win-title");
        			var thisMenuOpenType = $(el).eq(0).attr("win-opentype");
        			var thisMenuMaxOpen = $(el).eq(0).attr("win-maxopen");
        			
        			$('i[win-i-id="' + thisMenuId + '"]').remove();
        			$('div[class="winui-desktop-item sec-clsss-btn sec-btn"][win-id="' + thisMenuId + '"]').remove();
        			$(el).removeClass('sec-clsss-btn');
        			$(el).find('div[class="winui-icon winui-icon-font"]').html('<i class="fa ' + thisMenuIcon + ' fa-fw" style="background-color: ' + thisMenuBg + '; color: ' + thisMenuIconColor + '" win-i-id="' + thisMenuId + '"></i>');
        			//重新排列桌面
        			winui.util.locaApp();
        			winui.util.reloadOnClick(function (id, elem) {
        				var item = $(elem);
        				if(item.find(".icon-drawer").length > 0){
        					showBigWin(elem);
        				}else{
        					OpenWindow(elem);
        				}
        			});
        			AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop004", params:{rowId: thisMenuId, parentId: ""}, type:'json', callback:function(json){
    					if(json.returnCode == 0){
    					}else{
    						winui.window.msg(json.returnMessage, { shift: 6 });
    					}
    				}});
        			
            	}).on('cancel', function (el, container) {//拖拽取消
            		var times = $("#childWindow").parent().attr("times");
            		$("#layui-layer-shade" + times).css({width: '100%'});
            	});
            },
            end: function(layero, index){
            	initMenuToBox();
            }
        });
    	initDeskTopMenuRightClick();
    }
    
    //盒子标题双击
    $("body").on('dblclick', '#childWindowtext', function(e){
    	$(this).hide();
    	$(".childWindow-title-input-box").show();
    	$("#childWindowInput").val($('#childWindowtext').html());
    });
    //盒子标题取消
    $("body").on('click', '#cancleChildWindow', function(e){
    	$('#childWindowtext').show();
    	$(".childWindow-title-input-box").hide();
    });
    //盒子标题保存
    $("body").on('click', '#saveChildWindow', function(e){
    	if(isNull($("#childWindowInput").val())){
    		winui.window.msg('请输入盒子标题', {shift: 6, skin: 'msg-skin-message'});
    	}else{
    		var rowId = $(this).attr("rowid");
    		var menuName = $("#childWindowInput").val();
    		AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop005", params:{rowId: rowId}, type:'json', callback:function(json){
				if(json.returnCode == 0){
					var menuType = json.bean.menuType;
					if(menuType == '1'){//系统菜单
						winui.window.msg('无法编辑系统菜单。', {shift: 6, skin: 'msg-skin-message'});
					}else if(menuType == '3'){//自定义菜单盒子
						var params = {
   		        			menuBoxName: menuName,
   		        			rowId: rowId
   			        	};
   			        	AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop007", params:params, type:'json', callback:function(json){
   			 	   			if(json.returnCode == 0){
	   			 	   			$("#winui-desktop").find('div[id="' + rowId + '"]').attr("win-title", menuName);
			                	$("#winui-desktop").find('div[id="' + rowId + '"]').find('p').html(menuName);
			                	$('#childWindowtext').show();
			            		$(".childWindow-title-input-box").show();
			            		$('#childWindowtext').html(menuName);
   			 	   			}else{
   			 	   				winui.window.msg(json.returnMessage, {icon: 2,time: 2000});
   			 	   			}
   			 	   		}});
					}
				}else{
					winui.window.msg(json.returnMessage, { shift: 6 });
				}
			}});
    	}
    });

    //注销登录
    $('.logout').on('click', function () {
        winui.hideStartMenu();
        winui.window.confirm('确认注销吗?', {id: 'exit-confim', icon: 3, title: '提示', skin: 'msg-skin-message', success: function(layero, index){
			var times = $("#exit-confim").parent().attr("times");
			var zIndex = $("#exit-confim").parent().css("z-index");
			$("#layui-layer-shade" + times).css({'z-index': zIndex});
		}}, function (index) {
        	AjaxPostUtil.request({url:reqBasePath + "login003", params:{}, type:'json', callback:function(json){
 	   			if(json.returnCode == 0){
	 	   			if (etiger != null) {
		 	   			etiger.socket.close();
		 	   		}
	 	   			location.href = "login.html";
 	   			}else{
	 	   			if (etiger != null) {
		 	   			etiger.socket.close();
		 	   		}
 	   				location.href = "login.html";
 	   			}
 	   		}});
        });
    });

    //添加多窗口按钮
    $('body').on('mouseenter', '.win-box, .win-box-btn', function(e){
		$("#detail").css({"top": (e.pageY+10) + "px", "left": (e.pageX+10) + "px", "z-index": $(this).parent().parent().css('z-index') + 1});
		$('#detail').text($(this).data("text"));
		$('#detail').show();
    }).on('mouseleave', '.win-box, .win-box-btn', function(){
        $('#detail').hide();
    });
    
    //窗口操作编辑按钮
    $('body').on('click', '.win-box-title-edit', function(e){
    	$(".win-box-title-edit-close").show();//显示关闭按钮
    	$(".win-box-title-edit").hide();//隐藏编辑按钮
    	$(".win-box-del").show();//显示删除按钮
    	$("#addNewWin").hide();//隐藏添加按钮
    	$(".win-first").hide();//隐藏默认的一个窗口
    });
    
    //窗口操作关闭按钮
    $('body').on('click', '.win-box-title-edit-close', function(e){
    	$(".win-box-title-edit").show();//显示编辑按钮
    	$(".win-box-title-edit-close").hide();//隐藏关闭按钮
    	$(".win-box-del").hide();//隐藏删除按钮
    	$("#addNewWin").show();//显示添加按钮
    	$(".win-first").show();//显示默认的一个窗口
    });
    
    //窗口删除按钮
    $('body').on('click', '.win-box-del', function(e){
    	if($(this).parent().hasClass("win-box-check")){//判断当前要删除的桌面是否是被选中桌面
    		$(this).parent().removeClass("win-box-check");
    		$(".win-first").addClass("win-box-check");
    	}
    	$(this).parent().animate({'margin-top': '-40px', 'opacity': '0'}, 500, function(){
    		var id = $(this).data("id");
    		//遍历该任务栏上的任务项并删除
    		$(".winui-taskbar").find('ul[rowid="' +id + '"]').find("li").each(function(e){
    			var winId = $(this).attr("win-id");
    			$('div[class="layui-layer-content"][id="' + winId + '"]').parent().remove();
    		});
    		if(!$(".winui-taskbar").find('ul[rowid="' +id + '"]').is(':hidden')){//如果显示
    			$(".winui-taskbar").find('ul[rowid="' + id + '"]').animate({'opacity': '0'}, 500, function(){//动画隐藏要删除的显示的任务栏
    				$(this).remove();
    			});
    			$(".winui-taskbar").find('ul[rowid="00000000000000000000000000000000"]').animate({'opacity': '1'}, 500, function(){//动画显示默认任务栏
        			$(this).show();
        			$(".winui-taskbar-task-name div").html(1);
        		});
    		}else{//隐藏直接删除
    			$(".winui-taskbar").find('ul[rowid="' +id + '"]').remove();
    		}
			$(this).remove();
			resetWin();
		});
    	e.stopPropagation();
    });
    
    //窗口切换
    $('body').on('click', '.win-box', function(e){
    	if(!$(this).hasClass("win-box-check")){//当前元素不是选中元素
    		var text = $(this).data("text");
    		$(".win-box").removeClass("win-box-check");
    		$(this).addClass("win-box-check");
    		$(".winui-taskbar").find("ul:visible").animate({'opacity': '0'}, 500, function(){
    			$(this).hide();
    			var id = $(this).attr("rowid");
        		//遍历该任务栏上的任务项并隐藏
        		$(".winui-taskbar").find('ul[rowid="' +id + '"]').find("li").each(function(e){
        			var winId = $(this).attr("win-id");
        			$('div[class="layui-layer-content"][id="' + winId + '"]').parent().addClass('layui-hide');
        		});
    		});
    		$(".winui-taskbar").find('ul[rowid="' + $(this).data("id") + '"]').animate({'opacity': '1'}, 500, function(){
    			$(this).show();
    		});
    		$(".winui-taskbar-task-name div").html($(this).index() + 1);//设置左下角桌面序号
    	}
    });
    
    //添加新窗口
    $('body').on('click', '#addNewWin', function(e){
    	if($('.win-box-content div').length < 7){
    		$(".win-box").removeClass("win-box-check");//移除所有的选中事件
    		var id = _getRandomString(32);
    		var text = "桌面" + ($('.win-box-content .win-box').length + 1);
    		var str = '<div class="win-box win-box-check" data-text="' + text + '" data-id="' + id + '"><i class="win-box-del fa fa-trash-o fa-lg"></i></div>';
    		$(this).before(str);
    		$(".winui-taskbar").find("ul:visible").animate({'opacity': '0'}, 500, function(){
    			$(this).hide();
    			var id = $(this).attr("rowid");
    			//遍历该任务栏上的任务项并隐藏
    			$(".winui-taskbar").find('ul[rowid="' +id + '"]').find("li").each(function(e){
    				var winId = $(this).attr("win-id");
    				$('div[class="layui-layer-content"][id="' + winId + '"]').parent().addClass('layui-hide');
    			});
    		});
    		var bottomMenu = '<ul class="winui-taskbar-task" rowid="' + id + '"></ul>';
    		$(".winui-taskbar").find("ul").after(bottomMenu);
    		$(".winui-taskbar-task-name div").html($('.win-box-content div').length -1);
    	}else{
    		winui.window.msg('最多只能添加五个桌面哦。', { shift: 6 });
    	}
    });
    
    //多桌面工具控制栏
    $('.left-hide-menu').on('click', function () {
    	if($(this).find('i').hasClass("fa-angle-double-right")){//当前状态：展开
    		$(".win-boxs").animate({
    			'margin-right': '-250px', 
    		}, function(){
    			$(".left-hide-menu").find("i").removeClass("fa-angle-double-right");
    			$(".left-hide-menu").find("i").addClass("fa-angle-double-left");
    		});
    	}else{//当前状态：关闭
    		$(".win-boxs").animate({
    			'margin-right': '0px', 
    		}, function(){
    			$(".left-hide-menu").find("i").addClass("fa-angle-double-right");
    			$(".left-hide-menu").find("i").removeClass("fa-angle-double-left");
    		});
    	}
    });
    
    //重置桌面信息
    function resetWin(){
    	var winBox = $('.win-box-content .win-box');
    	$.each(winBox, function(i, item){
    		if(!$(item).hasClass('win-first')){
    			$(item).data('text', '桌面' + (i + 1));
    		}
    	});
    	$(".winui-taskbar-task-name div").html($('.win-box-check').index() + 1);//设置左下角桌面序号
    }
    
    //桌面管理鼠标移入移出事件
	$(".win-boxs").mouseover(function (){
    	$(".win-boxs").css({'z-index': 9999999999});
	}).mouseout(function (){
    	$(".win-boxs").css({'z-index': 1989});
	});
    
	/**
	 * 初始化菜单向文件夹中移动
	 */
	function initMenuToBox(){
		//初始化完毕回调
		var winMenuGroup = $("#winui-desktop .win-menu-group");
		var menuGroup = new Array();
		menuGroup.push(document.getElementById('winui-desktop'));
		$.each(winMenuGroup, function(i, item){
			menuGroup.push(document.getElementById($(item).attr("id")));
		});
		
		//向盒子中拖拽
		dragula(menuGroup, {
		}).on('drop', function (el, container) {//放置
			if($(container).attr("win-url") == '--'){//直接加入box盒子中
				var boxId = $(container).attr("win-id");//盒子id
				var thisMenuId = $(el).eq(0).attr("win-id");
				if( $('#' + boxId).find(".winui-icon-img").find(".icon-drawer").find('i[win-i-id="' + thisMenuId + '"]').length == 0){
					var drawer = $('#' + boxId).find(".icon-drawer");//盒子icon
					var child = $('#' + boxId).find(".icon-child");//盒子child
					var thisMenuIcon = $(el).eq(0).attr("win-icon");
					var thisMenuBg = $(el).eq(0).attr("win-menuiconbg");
					var thisMenuIconColor = $(el).eq(0).attr("win-menuIconColor");
					var thisMenuUrl = $(el).eq(0).attr("win-url");
					var thisMenuTitle = $(el).eq(0).attr("win-title");
					var thisMenuOpenType = $(el).eq(0).attr("win-opentype");
					var thisMenuMaxOpen = $(el).eq(0).attr("win-maxopen");
					
					var iconTypeI = "", iconSmallI = "", iconBigI = "";
					if(thisMenuIcon.indexOf('fa-') != -1){//icon图标
						iconTypeI = "winui-icon-font";
						iconBigI = '<i class="fa ' + thisMenuIcon + ' fa-fw" style="background-color: ' + thisMenuBg + '; color: ' + thisMenuIconColor + '" win-i-id="' + thisMenuId + '"></i>';
						iconSmallI = '<i class="fa fa-fw icon-drawer-icon ' + thisMenuIcon + '" style="background-color: ' + thisMenuBg + '; color: ' + thisMenuIconColor + '" win-i-id="' + thisMenuId + '"></i>';
					}else{//图片
						iconTypeI = "winui-icon-img";
						iconBigI = '<img src="' + fileBasePath + thisMenuIcon + '" />';
						iconSmallI = '<i class="fa icon-drawer-icon" win-i-id="' + thisMenuId + '"><img src="' + fileBasePath + thisMenuIcon + '" /></i>';
					}
					var menuStr = '<div class="winui-desktop-item sec-clsss-btn sec-btn" win-id="' + thisMenuId + '" win-url="' + thisMenuUrl + '" win-title="' + thisMenuTitle + '" win-opentype="' + thisMenuOpenType + '" win-maxopen="' + thisMenuMaxOpen + '" win-menuiconbg="' + thisMenuBg + '" win-menuiconcolor="' + thisMenuIconColor + '" win-icon="' + thisMenuIcon + '">'
					+ '<div class="winui-icon ' + iconTypeI + '" style="background-color: ' + thisMenuBg + '">' + iconBigI + '</div>'
					+ '<p>' + thisMenuTitle + '</p></div>';
					
					drawer.html(drawer.html() + iconSmallI);//在盒子内部追加icon
					child.html(child.html() + menuStr);
					$('#' + boxId).children('div[win-id="' + thisMenuId + '"]').remove();
					$('div[class="winui-desktop-item sec-btn"][win-id="' + thisMenuId + '"]').remove();
					//重新排列桌面
					winui.util.locaApp();
					winui.util.reloadOnClick(function (id, elem) {
        				var item = $(elem);
        				if(item.find(".icon-drawer").length > 0){
        					showBigWin(elem);
        				}else{
        					OpenWindow(elem);
        				}
        			});
					AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop004", params:{rowId: thisMenuId, parentId: boxId}, type:'json', callback:function(json){
						if(json.returnCode == 0){
						}else{
							winui.window.msg(json.returnMessage, { shift: 6 });
						}
					}});
				}
				
			}
		});
	}
	
	function initDeskTopMenuRightClick(){
		winui.desktop.initRightMenu({
            item: [{
            	icon: 'fa fa-folder-open-o',
        		text: "打开",
        		callBack: function (id, elem) {
                	var item = $(elem);
                	if(item.find(".icon-drawer").length > 0){
                		showBigWin(elem);
                	}else{
                		OpenWindow(elem);
                		winui.window.close($('#childWindow').parent());
                	}
                }
            }, {
            	text: '--'
            }, {
            	icon: 'fa fa-tags',
        		text: "重命名/配置",
        		callBack: function (id, elem, events) {
        			AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop005", params:{rowId: id}, type:'json', callback:function(json){
						if(json.returnCode == 0){
							var menuType = json.bean.menuType;
							if(menuType == '1'){//系统菜单
								winui.window.msg('无法编辑系统菜单。', {shift: 6, skin: 'msg-skin-message'});
							}else if(menuType == '2'){//自定义快捷方式
								winui.window.close($('#childWindow').parent());
								parentRowId = id;
								_openNewWindows({
									url: "../../tpl/index/editMenu.html", 
									title: "重置快捷方式",
									pageId: "editMenuDialog",
									area: ['700px', '450px'],
									skin: 'top-message-mation',
									callBack: function(refreshCode){
										if (refreshCode == '0') {
						                	$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').attr("win-title", childParams.titleName);
						                	$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').attr("win-url", childParams.menuUrl);
						                	$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').attr("win-menuiconbg", childParams.menuIconBg);
						                	$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').attr("win-menuiconcolor", childParams.menuIconColor);
						                	if(childParams.menuIconType === '1' || childParams.menuIconType == 1){//icon
						                		$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').attr('class', 'winui-icon winui-icon-font');
						                		$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').attr("win-icon", childParams.menuIcon);
						                		if($("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').find('i').length == 0){
						                			var str = '<i class="" win-i-id="' + childParams.rowId + '"></i>';
						                			$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').html(str);
						                		}
						                		$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').find('i').attr("class", "fa " + childParams.menuIcon + " fa-fw");
						                		$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').find('i').css({'color': childParams.menuIconColor});
						                		$("#winui-desktop").find('i[win-i-id="' + childParams.rowId + '"]').css({'background-color': childParams.menuIconBg, 'color': childParams.menuIconColor});
						                		var iconITag = $("#winui-desktop").find('i[win-i-id="' + childParams.rowId + '"]');
							                	$.each(iconITag, function(i, item){
							                		if($(item).hasClass("icon-drawer-icon")){
								                		$(item).attr("class", "fa " + childParams.menuIcon + " fa-fw icon-drawer-icon");
								                	}else{
								                		$(item).attr("class", "fa " + childParams.menuIcon + " fa-fw");
								                	}
							                	});
						                	}else if(childParams.menuIconType === '2' || childParams.menuIconType == 2){//图片
						                		$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').attr('class', 'winui-icon winui-icon-img');
						                		$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').attr("win-icon", childParams.menuIconPic);
						                		if($("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').find('i').length > 0){
						                			var str = '<img src="' + fileBasePath + childParams.menuIconPic + '">';
						                			$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').html(str);
						                		}
						                	}
						                	$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('div').css({'background-color': childParams.menuIconBg, 'color': childParams.menuIconColor});
						                	$("#winui-desktop").find('div[win-id="' + childParams.rowId + '"]').find('p').html(childParams.menuName);
						                	top.winui.window.msg("操作成功", {icon: 1,time: 2000});
						                } else if (refreshCode == '-9999') {
						                	top.winui.window.msg("操作失败", {icon: 2,time: 2000});
						                }
									}});
							}else if(menuType == '3'){//自定义菜单盒子
								parentRowId = id;
								_openNewWindows({
									url: "../../tpl/index/editMenuBox.html", 
									title: "重命名盒子",
									pageId: "editMenuBoxDialog",
									area: ['600px', '200px'],
									skin: 'top-message-mation',
									callBack: function(refreshCode){
						                if (refreshCode == '0') {
						                	$("#winui-desktop").find('div[id="' + childParams.rowId + '"]').attr("win-title", childParams.menuBoxName);
						                	$("#winui-desktop").find('div[id="' + childParams.rowId + '"]').find('p').html(childParams.menuBoxName);
						                	top.winui.window.msg("操作成功", {icon: 1,time: 2000});
						                } else if (refreshCode == '-9999') {
						                	top.winui.window.msg("操作失败", {icon: 2,time: 2000});
						                }
									}});
							}
						}else{
							winui.window.msg(json.returnMessage, { shift: 6 });
						}
					}});
                }
            }, {
            	text: '--'
            }, {
            	icon: 'fa fa-trash-o fa-lg',
        		text: "删除",
        		callBack: function (id, elem, events) {
                    layer.confirm('确定永久删除吗？', { id: 'delete-always-menu', icon: 3, title: '删除快捷方式/文件夹', skin: 'msg-skin-message', success: function(layero, index){
            			var times = $("#delete-always-menu").parent().attr("times");
            			var zIndex = $("#delete-always-menu").parent().css("z-index");
            			$("#layui-layer-shade" + times).css({'z-index': zIndex});
            		}}, function (index) {
    					layer.close(index);
    					AjaxPostUtil.request({url:reqBasePath + "sysevewindragdrop003", params:{rowId: id}, type:'json', callback:function(json){
    						if(json.returnCode == 0){
    							$(elem).remove();
    							$("i[win-i-id=" + $(elem).attr('win-id') + "]").remove();
    							$("div[win-id=" + $(elem).attr('win-id') + "]").remove();
    							//从新排列桌面app
    							events.reLocaApp();
    						}else{
    							winui.window.msg(json.returnMessage, { shift: 6 });
    						}
    					}});
    				});
                },
            }]
        });
	}
	
	//顶部滚动事件
	$('body').on('click', '#left-scoolor', function(e){
		var scoolor = $('.switch-menu').scrollLeft();
		if(scoolor > 0){
			scoolor = scoolor - ($('.switch-menu').width() - 200) / 2;
			$('.switch-menu').scrollLeft(scoolor);
		}
	});
	$('body').on('click', '#right-scoolor', function(e){
		var scoolor = $('.switch-menu').scrollLeft();
		if(scoolor < ($('.switch-menu').width() - 200)){
			scoolor = ($('.switch-menu').width() - 200) / 2 + scoolor;
			$('.switch-menu').scrollLeft(scoolor);
		}
	});
	
	initLoadTopScoolor();
	//监听窗口变化
	$(window).resize(function(){
		initLoadTopScoolor();
	});
	
	function initLoadTopScoolor(){
		$('.switch-menu').scrollLeft(10);
		if($('.switch-menu').scrollLeft() > 0){
			$(".switch-menu-scoolor").show();
		}else{
			$(".switch-menu-scoolor").hide();
		}
		$('.switch-menu').scrollLeft(0);
	}

	//页面刷新或者关闭时，关闭socket
	window.onbeforeunload = function(){
		if (etiger != null) {
			etiger.socket.close();
		}
	}

    exports('index', {});
});
