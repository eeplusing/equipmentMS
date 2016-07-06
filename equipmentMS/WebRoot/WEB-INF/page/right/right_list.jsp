<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限信息</title>
    
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
	         $('#right').datagrid({
	             title:'权限信息表',
	             iconCls:'icon-list',
	             //width:885,
	             //height:460,
	             pageSize : 15,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             url:'right_list.action',
	             idField:'rightId',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'rightId',hidden:'true'},
	                 {field:'name',title:'权限名称',width:fixWidth(0.3),align:'center'},
	                 {field:'modleId',title:'所属模块',width:fixWidth(0.3),align:'center',formatter:function(value, rowData, rowIndex){
	                     if(value == 6) return "账号管理";
	                     else if(value == 8) return "角色管理";
	                     else if(value == 9) return "权限管理";
	                     else if(value == 10) return "部门管理";
	                     else if(value == 11) return "系统公告";
	                     else if(value == 12) return "系统日志";
	                     else if(value == 13) return "设备类型";
	                     else if(value == 14) return "设备标识";
	                     else if(value == 15) return "设备信息";
	                     else if(value == 16) return "使用状态";
	                     else if(value == 17) return "备品信息";
	                     else if(value == 18) return "库存清单";
	                     else if(value == 19) return "库存预警";
	                     else if(value == 20) return "文档类型";
	                     else if(value == 21) return "文档格式";
	                     else if(value == 22) return "文档信息";
	                 }},
	                 {field:'remark',title:'功能描述',width:fixWidth(0.4),align:'center'}
	             ]],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     });
	     
	      //刷新数据
	     function searchs(){
	         $('#right').datagrid("reload",null);
			 return false;
	     }
     </script>
 
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
  	  <table id="right" class="easyui-datagrid"></table>
  </body>
</html>