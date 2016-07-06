<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>库存预警</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    
    
    <script type="text/javascript">
         function fixWidth(percent)     
         {     
           return (document.body.clientWidth-32) * percent ;      
         }  
         
	     $(function(){
	         $('#alert').datagrid({
	             title:'库存预警信息',
	             iconCls:'icon-list',
	             //width:1080,
	             //height:380,
	             pageSize : 15,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             url:'spacstock_list.action',
	             queryParams:
	             {
	             	modle : 'alert'
	             },
	             idField:'typeId',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'spacstockId',hidden:'true'},
	                 {field:'sparepartName',title:'备品名称',width:fixWidth(0.4),align:'center'},
	                 {field:'spacstockMinamount',title:'安全库存量',width:fixWidth(0.3),align:'center'},
	                 {field:'spacstockAmount',title:'库存量',width:fixWidth(0.3),align:'center'}
	             ]],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     });
	     
     </script>
 
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
  	  <table id="alert" class="easyui-datagrid"></table>
  </body>
</html>
