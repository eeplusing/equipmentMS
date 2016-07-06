<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>导航</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/equms/colin.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/equms/colin.js"></script>
    
    <script type="text/javascript">
        $(function(){
            colinMenu(${tree}, {
                       id:"c-menu",
                       callback: function(url){
                           window.parent.loadMain(url);
                       }
            });
        });
    </script>  
  </head>
     
  <body>
      <div id="all">
          <div id="c-menu"></div>
          <div style="clear: both;"></div>
      </div>
  </body>
</html>
