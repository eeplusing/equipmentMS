<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>基本信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
    
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>

    <script type="text/javascript">
         /*定义全局变量， 定义选中的标签页,设备ID*/
	     $(function(){
	         $('#instocklist').datagrid({
	             title:'入库信息表',
	             iconCls:'icon-list',
	             //width:1420,
	             //height:370,
	             pageSize : 8,//默认选择的分页是每页5行数据
	             pageList : [8],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'instock_list.action',
	             queryParams:{//初始查询参数
	                  sparepartNo : $("#sparepartNo").val(),
	                  instockTimeStart:$("#instockTimeStart").val(),
	                  requestType: 'list',
	                  instockTimeEnd:$("#instockTimeEnd").val()
	             },
	             idField:'instockId',
                 loadMsg:'装载中...',
                 frozenColumns:[[
                 	 {field:'instockId',hidden:'true'},
	                 {field:'sparepartNo',title:'备品编号',width:100,align:'center'},
	                 {field:'sparepartName',title:'备品名称',width:100,align:'center'},
	                 {field:'sparepartModle',title:'规格型号',width:100,align:'center'},
	                 {field:'typeName',title:'类型',width:100,align:'center'},
	                 {field:'sparepartUnit',title:'计量单位',width:100,align:'center'}
                 ]],
	             columns:[[
	                 {field:'sparepartSetplace',title:'安放地点',width:150,align:'center'},
	                 {field:'instockSparepartReceiver',title:'入库备品接收人',width:100,align:'center'},
	                 {field:'instockSparepartProvider',title:'入库备品发放人',width:150,align:'center'},
	                 {field:'instockSparepartSupervisor',title:'入库备品监督人',width:100,align:'center'},
	                 {field:'instockTime',title:'入库时间',width:200,align:'center'},
	                 {field:'instockAmount',title:'入库数量',width:100,align:'center'},
	                 {field:'instockComment',title:'入库备注',width:100,align:'center'}
	             ]],
	             onClickRow: function (rowIndex, rowData) {
	             				$("instockId").attr("value",rowData.instockId);
	             				search_info(rowData.instockId);
                             }, 
	             toolbar:[{
							  id:'btnadd', 
							  text:'入库', 
							  iconCls:'icon-add', 
							  handler:function(){
							      var url = "instock_openWindow.action";
							      window.top.show_fg();
							      window.top.show_instock(url);
							      searchs();
							  } 
						  },'-',{
							  text:'余量为零的入库单', 
							  handler:function(){
							      selSpart();
							  } 
						  },'-',{
							  text:'导出', 
							  iconCls:'icon-export', 
							  handler:function(){
							      window.top.show_fg();
							      window.top.show_di("in");
							  } 
						  }
	             ],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	         
	         function selSpart(){
             	$('#instocklist').datagrid({
	             	//width:1420,
	             	//height:370,
	             	pageSize : 8,//默认选择的分页是每页5行数据
	             	pageList : [8],//可以选择的分页集合
	             	nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             	striped : true,//设置为true将交替显示行背景。
	             	singleSelect: true, //单选模式
	             	remoteSort: false,
	             	fitColumns: false,
	             	autoRowHeight: false,
	             	url:'instock_list.action',
	             	queryParams:{//初始查询参数
	                  	sparepartNo : $("#spareNo").val(),
	                  	instockTimeStart:$("#instockTimeStart").val(),
	                  	instockTimeEnd:$("#instockTimeEnd").val(),
	                  	requestType : 'zero'
	             	},
	             	idField:'instockId',
                 	loadMsg:'装载中...',
                 	frozenColumns:[[
                 	 	{field:'instockId',hidden:'true'},
	                 	{field:'sparepartNo',title:'备品编号',width:100,align:'center'},
	                 	{field:'sparepartName',title:'备品名称',width:100,align:'center'},
	                 	{field:'sparepartModle',title:'规格型号',width:100,align:'center'},
	                 	{field:'typeName',title:'类型',width:100,align:'center'},
	                 	{field:'sparepartUnit',title:'计量单位',width:100,align:'center'}
                 	]],
	             	columns:[[
	                 	{field:'sparepartSetplace',title:'安放地点',width:150,align:'center'},
	                 	{field:'instockSparepartReceiver',title:'入库备品接收人',width:100,align:'center'},
	                 	{field:'instockSparepartProvider',title:'入库备品发放人',width:150,align:'center'},
	                 	{field:'instockSparepartSupervisor',title:'入库备品监督人',width:100,align:'center'},
	                	{field:'instockTime',title:'入库时间',width:200,align:'center'},
	                 	{field:'instockAmount',title:'入库数量',width:100,align:'center'},
	                 	{field:'instockComment',title:'入库备注',width:100,align:'center'}
	             ]],
	             	pagination : true
	         	});
         	}
	         
	         
	         
	         $('#outstocklist').datagrid({
	             title:'出库信息表',
	             iconCls:'icon-list',
	             //width:1420,
	             //height:370,
	             pageSize : 8,//默认选择的分页是每页5行数据
	             pageList : [ 8],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'outstock_list.action',
	             queryParams:{//初始查询参数
	                  sparepartNo : $("#sparepartNo").val(),
	                  instockId : $("#instockId").val(),
	                  instockTimeStart:$("#instockTimeStart").val(),
	                  instockTimeEnd:$("#instockTimeEnd").val()
	             },
	             idField:'outstockId',
                 loadMsg:'装载中...',
                 frozenColumns:[[
                 	 {field:'outstockId',hidden:'true'},
	                 {field:'sparepartNo',title:'备品编号',width:100,align:'center'},
	                 {field:'sparepartName',title:'备品名称',width:100,align:'center'},
	                 {field:'sparepartModle',title:'规格型号',width:100,align:'center'},
	                 {field:'typeName',title:'类型',width:100,align:'center'},
	                 {field:'sparepartUnit',title:'计量单位',width:100,align:'center'}
                 ]],
	             columns:[[
	                 {field:'outstockSparepartReceiver',title:'出库备品接收人',width:100,align:'center'},
	                 {field:'outstockSparepartProvider',title:'出库备品发放人',width:150,align:'center'},
	                 {field:'instockTime',title:'入库时间',width:200,align:'center'},
	                 {field:'outstockTime',title:'出库时间',width:200,align:'center'},
	                 {field:'sparepartUseplace',title:'使用地点',width:200,align:'center'},
	                 {field:'outstockAmount',title:'出库数量',width:100,align:'center'},
	                 {field:'remainAmount',title:'剩余数量',width:100,align:'center'},
	                 {field:'outStockComment',title:'出库备注',width:100,align:'center'}
	             ]],
	             toolbar:[{
							  id:'btnadd', 
							  text:'出库', 
							  iconCls:'icon-add', 
							  handler:function(){
							      var url = "outstock_openWindow.action";
							      window.top.show_fg();
							      window.top.show_outstock(url);
							      searchs();
							  } 
						  },'-',{
							  text:'导出', 
							  iconCls:'icon-export', 
							  handler:function(){
							      window.top.show_fg();
							      window.top.show_di("out");
							  } 
						  }],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     });
	     
	      //刷新数据
	     function searchs(){
	     	 $("#instockId").attr("value","");
	         $('#instocklist').datagrid("reload",{
	            sparepartNo : $("#sparepartNo").val(),
	            requestType: 'list',
	            instockTimeStart : $("#instockTimeStart").val(),
	            instockTimeEnd : $("#instockTimeEnd").val()
	         });
	         $('#outstocklist').datagrid("reload",{
	            sparepartNo : $("#sparepartNo").val(),
	            instockTimeStart : $("#instockTimeStart").val(),
	            instockTimeEnd : $("#instockTimeEnd").val(),
	            instockId : $("#instockId").val()
	         });
			 
	     }
	     
	     function search_info(instockId){
	     	 $("#instockId").attr("value",instockId);
	         $('#outstocklist').datagrid({
	             title:'出库信息表',
	             iconCls:'icon-list',
	             //width:1420,
	             //height:370,
	             pageSize : 8,//默认选择的分页是每页5行数据
	             pageList : [8],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'outstock_list.action',
	             queryParams:{//初始查询参数
	                  sparepartNo : $("#sparepartNo").val(),
	                  instockId : $("#instockId").val(),
	                  instockTimeStart:$("#instockTimeStart").val(),
	                  instockTimeEnd:$("#instockTimeEnd").val()
	             },
	             idField:'outstockId',
                 loadMsg:'装载中...',
                 frozenColumns:[[
                 	 {field:'outstockId',hidden:'true'},
	                 {field:'sparepartNo',title:'备品编号',width:100,align:'center'},
	                 {field:'sparepartName',title:'备品名称',width:100,align:'center'},
	                 {field:'sparepartModle',title:'规格型号',width:100,align:'center'},
	                 {field:'typeName',title:'类型',width:100,align:'center'},
	                 {field:'sparepartUnit',title:'计量单位',width:100,align:'center'}
                 ]],
	             columns:[[
	                 {field:'outstockSparepartReceiver',title:'出库备品接收人',width:100,align:'center'},
	                 {field:'outstockSparepartProvider',title:'出库备品发放人',width:150,align:'center'},
	                 {field:'instockTime',title:'入库时间',width:200,align:'center'},
	                 {field:'outstockTime',title:'出库时间',width:200,align:'center'},
	                 {field:'sparepartUseplace',title:'使用地点',width:200,align:'center'},
	                 {field:'outstockAmount',title:'出库数量',width:100,align:'center'},
	                 {field:'remainAmount',title:'剩余数量',width:100,align:'center'},
	                 {field:'outStockComment',title:'出库备注',width:100,align:'center'}
	             ]],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     }
     </script>
  </head>
  <input type="hidden" id="instockId" />
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
      <div style="width: 100%; height: 40px;">
		  <div class="gridOutLow">
			  <div id="tab_post" style="padding-top: 10px;">
				  <form id="form1" name="form1">
					  <span style="padding-left: 10px"></span>
					    备件编号：<input id="sparepartNo" name="sparepartNo" value="" style="height:25px; margin-right: 10px;"/>
					   入库时间段：<input class="easyui-validatebox" type="text" id="instockTimeStart" name="instockTimeStart" maxlength="10" value="" onfocus="$(this).calendar()" style="height:25px;"/> ~~~~
					   <input class="easyui-validatebox" type="text" id="instockTimeEnd" maxlength="10" name="instockTimeEnd" value="" onfocus="$(this).calendar()" style="height:25px; margin-right: 10px;"/> 
					  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs();" >查询</a>
				  </form>
		      </div>
		  </div>
      </div>
      
      <div style="height: 5px; border:none;"></div>
      <table id="instocklist" class="easyui-datagrid"></table>
      <div style="height: 10px; border:none;"></div>
      <table id="outstocklist" class="easyui-datagrid"></table>
  </body>
</html>
