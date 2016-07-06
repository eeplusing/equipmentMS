<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'test.jsp' starting page</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>

    <script type="text/javascript">
         /*定义全局变量， 定义选中的标签页,设备ID*/
	     $(function(){
	         $('#sparePart').datagrid({
	             title:'备品备件',
	             iconCls:'icon-list',
	             width:1080,
	             height:382,
	             pageSize : 10,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'spart_list.action',
	             queryParams:{//初始查询参数
	                  sparepartNo : $("#sparepartNo").val(),
	                       typeId : '0',
	                  requestType : 's',
	                  equipmentId : '0'
	             },
	             idField:'sparepartId',
                 loadMsg:'装载中...',
                 frozenColumns:[[
                 	 {field:'sparepartId',hidden:'true'},
	                 {field:'sparepartNo',title:'备品编号',width:100,align:'center', sortable:true},
	                 {field:'sparepartName',title:'备品名称',width:100,align:'center', sortable:true},
	                 {field:'sparepartModle',title:'规格型号',width:100,align:'center', sortable:true},
	                 {field:'typeName',title:'类型',width:100,align:'center', sortable:true},
	                 {field:'sparepartUnit',title:'计量单位',width:100,align:'center', sortable:true}
                 ]],
	             columns:[[
	                 {field:'sparepartPrice',title:'单价（万）',width:150,align:'center', sortable:true},
	                 {field:'sparepartProducer',title:'生产商',width:100,align:'center', sortable:true},
	                 {field:'sparepartSupplier',title:'经销商',width:150,align:'center', sortable:true},
	                 {field:'sparepartSetplace',title:'安放地点',width:100,align:'center', sortable:true},
	                 {field:'sparepartStartDate',title:'更换时间',width:100,align:'center', sortable:true},
	                 {field:'sparepartUsemonths',title:'更换周期（月）',width:100,align:'center', sortable:true},
	                 {field:'sparepartRemark',title:'备注信息',width:100,align:'center', sortable:true}
	             ]],
	             onClickRow: function (rowIndex, rowData) {
                                 search_info(rowData.sparepartNo);
                             }, 
	             toolbar:[{
							  id:'btnadd', 
							  text:'添加', 
							  iconCls:'icon-add', 
							  handler:function(){
							      var url = "spart_openWindow.action?modle=" + "save";
							      window.showModalDialog(url,"","dialogWidth=750px;dialogHeight=600px");
							      searchs();
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'删除', 
							  iconCls:'icon-remove', 
							  handler:function(){
								  $.messager.confirm('确认','确认删除?',function(row){
								      if(row){
								          var selectedRow = $('#sparePart').datagrid('getSelections');  //获取选中行 
										  $.ajax({
										      url:"spart_delete.action",
										      type:"post", 
										      cache:false,
										      data:"id=" + selectedRow[0].sparepartId,
										      success:function(msg){
										          $.messager.alert('删除备件', msg);
										          searchs();
										      }
										  });
								      }
								  });
							  }
						  },'-',{
						      id:'btncut',  
							  text:'编辑', 
							  iconCls:'icon-edit', 
							  handler:function(){
								  var selectedRow = $('#sparePart').datagrid('getSelections');
								  if(selectedRow.length > 0){
								      var url = "spart_openWindow.action?modle=" + "modify" + "&id=" + selectedRow[0].sparepartId;
							          window.showModalDialog(url,"","dialogWidth=750px;dialogHeight=600px");
							          searchs();
								  }
							  }
						  },'-',{
						      id:'btncut',  
							  text:'入库', 
							  handler:function(){
								  var selectedRow = $('#sparePart').datagrid('getSelections');
								  if(selectedRow.length > 0){
								      var url = "spart_openWindow.action?modle=" + "inStock" + "&id=" + selectedRow[0].sparepartId;
							          window.showModalDialog(url,"","dialogWidth=750px;dialogHeight=600px");
							          searchs();
								  }
							  }
						  },'-',{
						      id:'btncut',  
							  text:'出库', 
							  handler:function(){
								  var selectedRow = $('#sparePart').datagrid('getSelections');
								  if(selectedRow.length > 0){
								      var url = "spart_openWindow.action?modle=" + "outStock" + "&id=" + selectedRow[0].sparepartId;
							          window.showModalDialog(url,"","dialogWidth=750px;dialogHeight=600px");
							          searchs();
								  }
							  }
						  }
	             ],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     });
	     
	      //刷新数据
	     function searchs(){
	         $('#sparePart').datagrid("reload",{
	                  sparepartNo : $("#sparepartNo").val(),
	                       typeId : '0',
	                  requestType : 's',
	                  equipmentId : '0'

	         });
			 return false;
	     }
	     
	     function search_info(sparepartNo){
	         $('#iodepot-info').datagrid({
	             width:1080,
	             height:200,
	             pageSize : 5,//默认选择的分页是每页5行数据
	             pageList : [5],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'spart_iodepot.action',
	             queryParams:{
	                       sparepartNo : sparepartNo
	             },
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'iodepotOpertype',title:'出入库类型',width:150,align:'center',
				         formatter:function(value, rowData, rowIndex){
	                            if(value == 0) return "出库操作";
	                            else if(value == 1) return "入库操作";
	                     }
	                 },
	                 {field:'iodepotTime',title:'操作时间',width:200,align:'center', sortable:true},
	                 {field:'iodepotGetter',title:'归还人',width:150,align:'center',
				         formatter:function(value, rowData, rowIndex){
	                            if(value == "") return "-";
	                            else return value;
	                     }
	                 },
	                 {field:'iodepotReturner',title:'领取人',width:100,align:'center',
				         formatter:function(value, rowData, rowIndex){
	                            if(value == "") return "-";
	                            else return value;
	                     }
	                 },
	                 {field:'iodepotOperator',title:'操作人员',width:100,align:'center', sortable:true},
	                 {field:'iodepotRemark',title:'备注信息',width:300,align:'center',
				         formatter:function(value, rowData, rowIndex){
	                            if(value == "") return "-";
	                            else return value;
	                     }
	                 }
	             ]],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     }
     </script>
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
      <div style="width: 100%; height: 40px;">
		  <div class="gridOutLow">
			  <div id="tab_post" style="padding-top: 10px;">
				  <form id="form1" name="form1">
					  <span style="padding-left: 10px"></span>
					    备件编号<input id="sparepartNo" name="sparepartNo" value=""/>
					  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs();">查询</a>
				  </form>
		      </div>
		  </div>
      </div>
      <table id="sparePart" class="easyui-datagrid"></table>
      <div style="width:1080px;height:90px;padding-top:10px">
   		  <div id="tt" class="easyui-tabs" region="center" style="overflow-y:hidden;">
			  <div title="出入库记录">
			      <table id="iodepot-info" class="easyui-datagrid"></table>
			  </div>
		  </div>
	  </div>
  </body>
</html>
