<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统首页</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/base.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/rotate3di.js"></script>
    
    <style type="text/css">
        body,div,ul,li{margin:0px; padding:0px;}
        body{width:100%; height:100%;}
        li{height:25px; border: 0px solid #000; font-size: 20px; text-align: left; cursor:pointer;}
    </style>
    
    <script type="text/javascript">
         function modle(url){
              url = "${pageContext.request.contextPath}/main_page.action?redirectAction=" + url + "&time=" + new Date();
              $.post(url, null, function(msg){
                  $("#main_page").html(msg);
              });
              myrotate();
          }
         /*实现DIV3D旋转展示*/
        function myrotate(){
        	 $(".main").find('div').stop().rotate3Di('unflip');
        	 $(".main").find('div').stop().rotate3Di('flip', 300, {direction: 'clockwise', sideChange: function(){}});
        	 
         }
    </script>
  </head>
  <body>
      <div id="cc" class="easyui-layout" style="width:100%; height:100%;">
          <div class="head" region="north" title="North" style="height:100px;">wdadad</div>
          <div id="aa" class="easyui-accordion" region="west" title="West" style="width:200px;">
	          <c:forEach items="${trees}" var="tree">
	              <div title="${tree.firstName}" iconcls="icon-save" style="overflow: auto;">
	                  <ul class="easyui-tree">
	                      <c:forEach items="${tree.modles}" var="modle">
	                          <li style="overflow: auto;">
	                              <span><a href="#" onclick="modle('${modle.url}')">${modle.name}</a></span>
	                          </li>
	                      </c:forEach>
	                  </ul>
	              </div>
	          </c:forEach>
	          
          </div>
          <div class="main" region="center" title="Center" style="padding:5px;">
              <div id="main_page" style="width: 100%; height: 100%; background: #EEE;">
              </div>
          </div>
      </div>
  </body>
</html>
