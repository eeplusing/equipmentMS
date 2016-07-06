<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看公告</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/plugins/jquery.form.js"></script>
  </head>

  <body style="margin: 10px;">
    	<div style="border: 0px solid #000; text-align: center;">
    		<label class="easyui-validatebox" style="font-size: 18px; color: #666; font-weight: bold;">标题:${notice.title}</label>
        </div>
        <div>
    		<textarea class="easyui-validatebox"   cols="83" rows="30" readonly="readonly" id="userName" style="border: none; overflow: auto;">${notice.content}</textarea>
        </div>
       
        <div>
    		<label style="font-size: 16px; color: #666;">发布时间:${notice.noticeTime}</label>
        </div>
  </body>
</html>
