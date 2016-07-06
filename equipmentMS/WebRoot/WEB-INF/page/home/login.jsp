<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆页</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <style type="text/css">
        body{
              width: 100%;
              height: 100%;
              margin: 0px;
              padding: 0px;
              overflow: hidden; 
        }
        
        #all{
              width: 1841px;
              height: 952px;
              margin: 0px auto;
              border: 0px solid #000;
        }
        #bg{
              width: 100%;
              height: 100%;
              border: 0px solid #F00;
        }
        .login-x{
              position: absolute;
              top: 42%;
              left: 63%;
              width: 270px;
              height:185px;
              border-radius: 5px;
              z-index: 2;
        }
        .login-x input.txt{
             width: 200px;
             height: 25px;
             font-size: 16px;
             color: #555;
             margin: 20px 0px;
             font-family: "宋体", "楷体";
             border: 1px solid #666;
             border-radius: 2px;
        }
        .login-x input.buton{
             display: inline;
             width: 80px;
             height: 27px;
             font-size: 16px;
             font-weight: bold;
             color: #FFF;
             margin-left: 105px;
             background: #2F8BB6;
             border: none;
        }
        
        .login-x span{
             font-size: 16px;
             font-weight: bold;
             height: 37px;
             color: #FFF;
             font-family: "黑体", "楷体", "宋体";
        }
        
        .activate-txt{
            border: 1px solid #999;
        }
        .activate-txt:HOVER,.activate-txt:FOCUS {
	        border: 1px solid #EED91E;
        }
        
        #move{
            position: absolute;
            width: 935px;
            height: 479px;
            padding: 0px;
            background: url("images/pop-up3.png");
            z-index: 9;
            display: none;
        }
        #full_bg{
            display: none;
            background: #BFBFBF;
	        left: 0;
	        opacity: 0.5; /*CSS标准.该属性支持Firefox, Safari和 Opera.0.5表示透明度为50%*/
	        position: absolute;
	        top: 0;
	        z-index: 3;
	        filter: alpha(opacity = 50); /*透明度兼容IE6*/
	        -moz-opacity: 0.5; /*透明度兼容老版本的Mozilla浏览器*/
	        -khtml-opacity: 0.5; /*透明度兼容老版本的Safari浏览器*/
        }
    </style>
    <script type="text/javascript">
       var bh = 0;
        var bw = 0;
        $(function(){
            bh = document.body.scrollHeight;
            bw = document.body.scrollWidth;
            winSize();
            $(window).resize(function(){
                winSize();
            });
        });
        
        function winSize(){
           var bsh = document.body.offsetHeight;
           var bsw = document.body.offsetWidth;
           $("#all").css({"height" : bsh,
                          "width" : bsw
           });
           $("#bg").css({"height" : bsh,
                         "width" : bsw
           });
        }
        
        function checkUser(){
            var loginAccount = $("#login_account").val().replace(/(^\s*)|(\s*$)/g, "");
            if(loginAccount == null || loginAccount == "" || loginAccount.length == 0){
                $.messager.alert('提示', "请输入账号");
            }else{
                $.ajax({
				    type : "post",
					url : "account_check.action",
					data : "loginAccount=" + loginAccount,
					success: function(msg){
						if(msg == "no-activated"){
						    $.messager.alert('提示', "登录前请激活账号");
						    $("#account").val(loginAccount);
						    $("#full_bg").css({
					            height: bh,
					            width: bw, 
					            display: "block"
					        });
					        $("#move").css({
					            left: (bw - 935)*0.5,
					            top: (bh - 479)*0.5, 
					            display: "block"
					        });
						}else if(msg == "is-activated"){
						    login();
						}else{
						    $.messager.alert('提示', "账户不存在");
						}
					}
				});
            }
        }
        
        function modify(){
            //var email_reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
           var userName = $("#userName").val().replace(/(^\s*)|(\s*$)/g, ""); 
          
            
            if($("#loginPassword").val() != $("#password").val()){
                $.messager.alert('提示', "两次密码不一致");
                return false;
            }
            
            if(userName == null || userName == "" || userName.length == 0){
                $.messager.alert('提示', "请输入姓名");
                return false;
            }
            
            /*if(!email_reg.test($("#email").val())){
                $.messager.alert('提示', "邮箱格式错误");
                return false;
            }*/
            
            $.ajax({
				type : "post",
				url : "account_activate.action",
				data : "loginPassword=" + $("#loginPassword").val() + "&userPhone=" + $("#userPhone").val() + "&email=" + $("#email").val() + "&account=" + $("#account").val() + "&userName=" + $("#userName").val(),
				success: function(msg){
				    if(msg == "success"){
				        document.location.href="home.action";
				    }else{
				        $.messager.alert('提示', "账号激活失败");
				    }
				}
			});
        }
        
        function login(){
            document.forms[0].submit();
        }
        function move_close(){
	         $("#move").hide();
	         $("#loginPassword").val("");
	         $("#password").val("");
			 $("#userPhone").val("");
			 $("#email").val("");
			 $("#full_bg").hide();
	     }
	     
        var a;
         document.onmouseup=function(){
             if(!a){
                 return;
             }
             document.all?a.releaseCapture():window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
             a="";
         };
         document.onmousemove=function (d){
             if(!a){
                 return;
             }
             if(!d){
                 d=event;
             }
             a.style.left=(d.clientX-b)+"px";
             a.style.top=(d.clientY-c)+"px";
         };
         function mdown(e){
             a=document.getElementById("move");
             document.all?a.setCapture():window.captureEvents(Event.MOUSEMOVE);
             b=e.clientX-parseInt(a.style.left);
             c=e.clientY-parseInt(a.style.top);
         }
    </script>
  </head>
  
  <body>
      <div id="all">
          <img id="bg" alt="" src="${pageContext.request.contextPath}/images/login.jpg">
	      <div class="login-x">
	          <form action="home_login.action" method="post">
	              <span class="d-left">用户名:</span><input class="txt" type="text" id="login_account" name="user.loginAccount" value="">
	              <span>密<label style="margin-left: 16px;"></label>码:</span><input class="txt" type="password" id="login_password" name="user.loginPassword" value="">
	              <input class="buton" type="button"  onclick="checkUser();" value="登   录"  style="cursor:pointer;">
	          </form>
	          <div style="clear: both;"></div>
	      </div>
	      
	      <div id="full_bg"></div>
	      <div id="move">
	          
	  	      <div style="width:100%; height: 53px; cursor:move;" onmousedown="mdown(event);">
	  	          <div style="clear: both; height: 33px;"></div>
	  	      	  <span style="margin-left: 30px; font-size: 20px; padding-top: 5px; color: #EFEFEF; font-weight: bolder;">账号激活</span>
	  	      	  <a href="javascript:void(0);" onclick="move_close();" style="float: right; margin-right: 30px; color: #000;">[关闭]</a>
	  	      </div>
	  	      <div style="width:100%;">
	  	          <input type="hidden" id="account">
	  	          <div style="width:400px; margin: 40px auto 20px; font-size: 16px; color: #333;">
	  	              登陆密码：<input type="password" class="activate-txt" id="loginPassword" name="loginPassword" style="width: 250px; height: 20px; border: 1px solid #53B7F5;">
	  	          </div>
	  	          <div style="width:400px; margin: 20px auto; font-size: 16px; color: #333;">
	  	              密码确认：<input type="password" class="activate-txt" id="password" name="password" style="width: 250px; height: 20px; border: 1px solid #53B7F5;">
	  	          </div>
	  	          <div style="width:400px; margin: 20px auto; font-size: 16px; color: #333;">
	  	              姓<span style="margin-left: 32px;"></span>名：<input type="text" class="activate-txt" id="userName" name="userName" style="width: 250px; height: 20px; border: 1px solid #53B7F5;">
	  	          </div>
	  	          <div style="width:400px; margin: 20px auto; font-size: 16px; color: #333;">
	  	              手机号码：<input type="text" class="activate-txt" id="userPhone" name="userPhone" style="width: 250px; height: 20px; border: 1px solid #53B7F5;">
	  	          </div>
	  	          <div style="width:400px; margin: 20px auto; font-size: 16px; color: #333;">
	  	              电子邮箱：<input type="text" class="activate-txt"  id="email" name="email" style="width: 250px; height: 20px; border: 1px solid #53B7F5;">
	  	          </div>
	  	          <div style="margin: 20px auto; width: 170px;">
	  	              <input type="button" style="background: #0A93D4; width: 75px; height: 25px; cursor: pointer; font-style: 12px; color:#EFEFEF; border: 1px solid #315160; border-radius: 5px;" onclick="modify();" value="确定">
	  	              <input type="button" style="background: #0A93D4; width: 75px; height: 25px; cursor: pointer; font-style: 12px; color:#EFEFEF; margin-left: 15px; border: 1px solid #315160; border-radius: 5px;" onclick="move_close();" value="取消">
	  	          </div>
	  	      </div>
	  	  </div>
	  	  
	  	  <div style="clear: both;"></div>
  	  </div>
  </body>
</html>
