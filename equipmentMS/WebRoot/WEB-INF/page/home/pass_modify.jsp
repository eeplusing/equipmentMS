<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>密码修改</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>

    <script type="text/javascript">
	    function modify(){
	         var oldPassword = $("#oldPassword").val().replace(/(^\s*)|(\s*$)/g, "");
	         var newPassword = $("#newPassword").val().replace(/(^\s*)|(\s*$)/g, "");
	         var affirm = $("#affirm").val().replace(/(^\s*)|(\s*$)/g, "");
	         
	         if(newPassword != affirm){
	             $.messager.alert('提示', "两次密码不一致");
	             return false;
	         }
	         
	         if(oldPassword == null || oldPassword.length == 0){
	             $.messager.alert('提示', "原始密码为空!");
	             return false;
	         }
	            
	         if(newPassword == null){
	             $.messager.alert('提示', "密码至少6位");
	             return false;
	         }
	            
	         $.ajax({
				 type : "post",
				 url : "pass_modify.action",
				 data : "oldPassword=" + oldPassword + "&newPassword=" + newPassword,
				 success: function(msg){
					 if(msg == "pass-error"){
					     $.messager.alert('提示', "原始密码错误");
					 } else if(msg == "error"){
					     $.messager.alert('提示', "密码修改失败");
					 } else if(msg == "no-user"){
					     $.messager.alert('提示', "账号不存在");
					 } else if(msg == "success"){
					     returnValue = "success"; 
                         window.close(); 
					 } 
				 }
			});
	     }
    </script>
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
    <div id="move" style="width:398px; height:149px; padding:0px; margin: 0px; border:1px solid #999; background:#FFF;">
  	      <div style="width:100%; height: 25px; background:#9CF; font-size: 20px; text-align: center;" >
  	          密码修改
  	      </div>
  	      <div style="width:100%;">
  	          <div style="margin: 5px;">
  	              原始密码:<input type="password" id="oldPassword" name="oldPassword" style="width: 85%; height: 20px;">
  	          </div>
  	          <div style="margin: 5px;">
  	              新设密码:<input type="password"  id="newPassword" name="newPassword" style="width: 85%; height: 20px;">
  	          </div>
  	          <div style="margin: 5px;">
  	              密码确认:<input type="password" id="affirm" name="affirm" style="width: 85%; height: 20px;">
  	          </div>
  	          <div>
  	              <input type="button" onclick="modify();" value="确定"  style="margin-left: 45%; width: 40px; height: 20px; margin-top: 5px;">
  	          </div>
  	      </div>
  	  </div>
  </body>
</html>
