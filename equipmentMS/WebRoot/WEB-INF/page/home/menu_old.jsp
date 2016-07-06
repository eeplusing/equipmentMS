<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
  </head>
  
  <body style="overflow:hidden; margin: 0px; padding: 0px; width: 100%; height: 100%;">
      <div id="cc" class="easyui-layout" style="margin: 0px; padding: 0px; width:264px; height:100%;">
          <div id="aa" class="easyui-accordion" region="center" title="MENU" style="overflow:auto; margin: 0px; padding:  0px;">
		      <c:forEach items="${trees}" var="tree">
			      <div title="${tree.firstName}" iconcls="icon-save" style="overflow: hidden; width: 100%; margin: 0px; padding:  0px;">
			          <ul class="easyui-tree" style="overflow: hidden; width: 100%; margin: 0px; padding:  0px;">
			              <c:forEach items="${tree.modles}" var="modle">
			                  <li style="overflow: hidden; width: 100%; margin: 0px; padding:  0px;" >
			                      <span><a target="main" href="${pageContext.request.contextPath}${modle.url}">${modle.name}</a></span>
			                  </li>
			              </c:forEach>
			          </ul>
			      </div>
			  </c:forEach>
			  <div title="位置管理" iconcls="icon-save" style="overflow: hidden; width: 100%; margin: 0px; padding:  0px;">
			      <ul class="easyui-tree" style="overflow: hidden; width: 100%; margin: 0px; padding:  0px;">
			          <li style="overflow: hidden; width: 100%; margin: 0px; padding:  0px;" >
			              <span><a target="_blank" href="http://svr-ems-lzbz:8220/">位置信息</a></span>
			          </li>
			      </ul>
			  </div>
	      </div>
	  </div>
  </body>
</html>