<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备批次</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery.fileDownload.js"></script>
    
    <style type="text/css">
    #images img{
        width: 200px; 
        height: 200px;
        margin: 10px;
        display: inline;
        float: left;
    }
    </style>
    
    <script type="text/javascript">
         /*定义全局变量， 定义选中的标签页,设备ID*/
        var tabId = "#datum-image";
         var equId = "";
	     $(function(){
	         $('#equ').datagrid({
	             title:'设备信息表',
	             iconCls:'icon-list',
	             pageSize : 10,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'equ_list.action',
	             queryParams:{//初始查询参数
	                  typeName : $("#typeName").val(),
	                  useState : $("#useState").val(),
	                  equNo : $("#equNo").val()
	             },
	             idField:'equipmentId',
                 loadMsg:'装载中...',
                 frozenColumns:[[
                 	 {field:'equipmentId',hidden:'true'},
	                 {field:'equipmentNo',title:'批次编号',width:100,align:'center', sortable:true},
	                 {field:'equipmentName',title:'设备名称',width:100,align:'center', sortable:true},
	                 {field:'equipmentModle',title:'规格型号',width:100,align:'center', sortable:true},
	                 {field:'typeName',title:'设备类型',width:100,align:'center', sortable:true},
	                 {field:'equipmentProducer',title:'生产商',width:150,align:'center', sortable:true},
	                 {field:'equipmentSupplier',title:'供应商',width:150,align:'center', sortable:true}
                 ]],
	             columns:[[
	                 {field:'equipmentBuyTime',title:'购置时间',width:100,align:'center', sortable:true},
	                 {field:'equipmentBuyType',title:'购置方式',width:100,align:'center', sortable:true},
	                 {field:'equipmentRecipient',title:'设备接收人',width:100,align:'center', sortable:true},
	                 {field:'equipmentProvider',title:'设备发放人',width:100,align:'center', sortable:true},
	                 {field:'equipmentDirector',title:'设备监督人',width:100,align:'center', sortable:true},
	                 {field:'useState',title:'使用状态',width:100,align:'center', sortable:true},
	                 {field:'startDate',title:'启用日期',width:100,align:'center', sortable:true},
	                 {field:'equipmentUseYears',title:'使用年限（年）',width:100,align:'center', sortable:true},
	                 {field:'equipmentMonetaryAmount',title:'购买金额（万）',width:100,align:'center', sortable:true},
	                 {field:'amount',title:'数量',width:100,align:'center', sortable:true},
	                 {field:'areaCode',title:'区段号',width:100,align:'center', sortable:true},
	                 {field:'remark',title:'备注',width:100,align:'center', sortable:true}
	             ]],
	             onClickRow: function (rowIndex, rowData) {
                                 equId = rowData.equipmentId;
                                 tabSearchs(tabId, equId);
                             }, 
	             toolbar:[{
							  id:'btnadd', 
							  text:'添加批次', 
							  iconCls:'icon-add', 
							  handler:function(){
							      window.top.loadType();
								  window.top.loadState();
							      window.top.show_fg();
							      window.top.show_div("#add-equ", "add");
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'编辑批次', 
							  iconCls:'icon-edit', 
							  handler:function(row){
								  if(row){
								      var selectedRow = $('#equ').datagrid('getSelections');  //获取选中行
								      window.top.loadType();
								      window.top.loadState();
								      window.top.show_fg();
							          window.top.show_div("#add-equ", "modify");
								      window.top.loadData(selectedRow);
								  }
							  }
						  },'-',{
						      id:'btncut',  
							  text:'导出', 
							  iconCls:'icon-export', 
							  handler:function(){
							      window.top.show_fg();
							      window.top.show_di("equ");
							  }
						  }
	             ],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	         $('.tabs-inner').live('click',function(){
		         var subtitle = $(this).children("span").text(); 
		         if(subtitle == "图片"){
		             tabId = "#datum-image";
		         }else if(subtitle == "文档"){
		             tabId = "#datum-txt";
		         }else if(subtitle == "发放明细"){
		             tabId = "#sparePart";
		         }
		         if(equId != "" || subtitle == "发放明细"){
		             tabSearchs(tabId, equId);
		         }
		     });  
	     });
	     
	      //刷新数据
	     function searchs(){
	         $('#equ').datagrid("reload",{
	             typeName : $("#typeName").val(),
	             useState : $("#useState").val(),
	                equNo : $("#equNo").val()
	         });
	         equId = "";
			 return false;
	     }
	     
	     /*刷新关联数据*/
	     function tabSearchs(tabId, equId){
	     	 if(tabId == "#datum-image"){
	     	     imageLoad();
	     	 }else if(tabId == "#datum-txt"){
	     	     txtLoad();
	     	 }else if(tabId == "#sparePart"){
	     	     partLoad();
	     	 }
	     }
	     
	     function txtLoad(){
	         $('#datum-txt').datagrid({
	             pageSize : 8,//默认选择的分页是每页5行数据
	             pageList : [8],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             remoteSort: false,
	             autoRowHeight: false,
	             url:'datum_equList.action',
	             queryParams:{//初始查询参数
	                 equipmentId : equId
	             },
	             idField:'datumId',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'datumId',hidden:'true'},
	                 {field:'datumName',title:'文档名称',width:150,align:'center', sortable:true},
	                 {field:'datumTypeName',title:'文档类别',width:100,align:'center', sortable:true},
	                 {field:'datumFormatName',title:'文档格式',width:100,align:'center', sortable:true}
	             ]],
	             toolbar:[{
							  id:'btnadd', 
							  text:'添加', 
							  iconCls:'icon-add', 
							  handler:function(){
							      var url = "equ_open.action?modle=" + "datum" + "&equipmentId=" + equId;
							      window.showModalDialog(url,"","dialogWidth=620px;dialogHeight=600px");
							      txtReload();
							  }
						  },'-',{
						      id:'btncut',  
							  text:'下载', 
							  iconCls:'icon-edit', 
							  handler:function(){
								   var selectedRow = $('#datum-txt').datagrid('getSelections');  //获取选中行 
								     if(selectedRow.length > 0){
								          $.fileDownload("datum_download.action",{
				                               preparingMessageHtml : "文件正在生成,请稍后...",
				                               failMessageHtml : "文件生成失败，请重试",
				                               httpMethod : "POST",
				                               data : "datumId=" + selectedRow[0].datumId
			                              });
								      }
							  }
						  }
	             ],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     }
	     
	     function txtReload(){
	         $('#datum-txt').datagrid("reload",{
	             equipmentId : equId
	         });
			 return false;
	     }
	     
	     function partLoad(){
	         $('#datum-part').datagrid({
	             title:'设备发放明细表',
	             iconCls:'icon-list',
	             pageSize : 2,//默认选择的分页是每页5行数据
	             pageList : [ 2 , 10],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'provide_list.action',
	             queryParams: {//初始查询参数
	                 equipmentId : equId
	             },
	             idField:'id',
                 loadMsg:'装载中...',
                 frozenColumns:[[
	                 	 {field: 'id', hidden: 'true'},
		                 {field: 'equipmentNo', title: '批次编号', width: 100, align: 'center', sortable: true},
		                 {field: 'equipmentName', title: '设备名称', width: 100, align: 'center', sortable: true},
		                 {field: 'equipresource', title: '来源', width: 100, align: 'center', sortable: true},
		                 {field: 'equipmentModle', title: '规格型号', width: 100, align: 'center', sortable: true},
		                 {field: 'provideDate', title: '发放时间', width: 100, align: 'center', sortable: true},
		                 {field: 'equipmentUseYears', title: '使用年限', width: 100, align: 'center', sortable: true},
		                 {field: 'putplace', title: '存放地点', width: 100, align: 'center', sortable: true},
		                 {field: 'amount', title: '数量', width: 100, align: 'center', sortable: true},
		                 {field: 'receiver', title: '领取人', width: 100, align: 'center', sortable: true},
		                 {field: 'remark', title: '备注', width: 100, align: 'center', sortable: true}
                 ]],
	             toolbar:[{
							  id:'btnadd', 
							  text:'添加', 
							  iconCls:'icon-add', 
							  handler:function(){
							      window.showModalDialog("provide_openSaveWin.action","","dialogWidth=850px;dialogHeight=650px");
								  $('#datum-part').datagrid("reload",null);
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'编辑', 
							  iconCls:'icon-edit', 
							  handler:function(row){
								  if(row){
								      var selectedRow = $('#datum-part').datagrid('getSelections');  //获取选中行
								      window.showModalDialog("provide_openModifyWin.action?id=" + selectedRow[0].id,"","dialogWidth=850px;dialogHeight=650px");
								      $('#datum-part').datagrid("reload",null);
								  }
							  }
						  },'-',{
						      id:'btncut',
							  text:'导出',
							  iconCls:'icon-export',
							  handler:function(){
							      window.top.show_fg();
							      window.top.show_di("pro");
							  }
						  }
	             ],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     }
	     
	     function imageLoad(){
	         if(equId != ""){
	             $("#images").html("");
	             $.ajax({
					 url:"datum_image.action",
					 type:"post",
					 cache:false,
					 data:"equipmentId=" + equId,
					 success:function(msg){
					     if(msg == "no-image"){
					         $("#images").html("");
					     }else{
					         if(msg.indexOf("[]") > 0){
						         var images = msg.split("[]");
						         for(var i = 0; i < images.length - 1; i++){
						             var lastElement = document.createElement("div");
						             lastElement.innerHTML = "<img src='datum_showImage.action?imageUrl=" + images[i] +"'/>";
						             $("#images").append(lastElement);
						         }
					         }
					     }
					     
					 }
				 });
	         }
	     }
     </script>
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
      <div style="width: 100%; height: 40px;">
		  <div class="gridOutLow">
			  <div id="tab_post" style="padding-top: 10px;">
				  <form id="form1" name="form1">
					  <span style="padding-left: 10px"></span>
					  类型：<select name="typeName" id="typeName" style="height:25px; margin-right: 10px; vertical-align: middle;">
						        <option value="ALL">
									所有
							    </option>
							    <c:forEach items="${types}" var="type">
								    <option value="${type.typeName}">
										${type.typeName}
								    </option>
							    </c:forEach>
						    </select>
					  状态：<select name="useState" id="useState" style="height:25px; margin-right: 10px; vertical-align: middle;">
						        <option value="ALL">
									所有
							    </option>
							    <c:forEach items="${useStates}" var="useState">
								    <option value="${useState.useStateName}">
										${useState.useStateName}
								    </option>
							    </c:forEach>
						    </select>
					  编号：<input type="text" id="equNo" style="height: 25px; width: 150px;">
						<span style="margin-left: 20px;"></span>
					  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs();">查询</a>
				  </form>
		      </div>
		  </div>
      </div>
  	  <table id="equ" class="easyui-datagrid"></table>
  	  <div style="width:auto; padding-top:10px; overflow-x:hidden;">
   		  <div id="tt" class="easyui-tabs" region="center" style="overflow-y:hidden;">
			  <div title="图片">
			      <div id="images" style="overflow: hidden;">
			      </div>
			      <div style="clear: both;"></div>
			  </div>
			  <div title="文档">
			      <table id="datum-txt" class="easyui-datagrid"></table>
			      <div style="clear: both;"></div>
			  </div>
			  <div title="发放明细">
			      <table id="datum-part" class="easyui-datagrid"></table>
			      <div style="clear: both;"></div>
			  </div>
		  </div>
		  <div style="clear: both;"></div>
	  </div>
  </body>
</html>