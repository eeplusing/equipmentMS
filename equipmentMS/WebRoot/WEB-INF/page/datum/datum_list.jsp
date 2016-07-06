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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery.fileDownload.js"></script>
    
    <script type="text/javascript">
         function fixWidth(percent)     
         {     
           return (document.body.clientWidth-32) * percent ;      
         }  
         
	     $(function(){
	         $('#datum').datagrid({
	             title:'文档信息表',
	             iconCls:'icon-list',
	             //width:885,
	             //height:495,
	             pageSize : 15,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             url:'datum_list.action',
	             queryParams:{//初始查询参数
	                  datumTypeId : $("#datumTypeId").val(),
	                  datumFormatId : $("#datumFormatId").val(),
	                  datumName : $("#datumName").val(),
	                  requestType : 'list',
	                  equipmentId : '0'
	             },
	             idField:'datumId',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'datumId',hidden:'true'},
	                 {field:'datumName',title:'文档名称',width:fixWidth(0.4),align:'center'},
	                 {field:'datumTypeName',title:'文档类型',width:fixWidth(0.3),align:'center'},
	                 {field:'datumFormatName',title:'文档格式',width:fixWidth(0.3),align:'center'}
	             ]],
	             toolbar:[{ 
							  id:'btnup', 
							  text:'上传', 
							  iconCls:'icon-save', 
							  handler:function(){
							  	  window.showModalDialog("datum_uploadWindow.action","","dialogWidth=690px;dialogHeight=760px");
								  searchs();
							  }
						  },'-',{ 
							  id:'btndown', 
							  text:'下载', 
							  iconCls:'icon-export', 
							  handler:function(){
							      var selectedRow = $('#datum').datagrid('getSelections');  //获取选中行 
								     if(selectedRow.length > 0){
								          $.fileDownload("datum_download.action",{
				                               preparingMessageHtml : "文件正在生成,请稍后...",
				                               failMessageHtml : "文件生成失败，请重试",
				                               httpMethod : "POST",
				                               data : "datumId=" + selectedRow[0].datumId
			                              });
								      }
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'删除', 
							  iconCls:'icon-remove', 
							  handler:function(){
								  $.messager.confirm('确认','确认删除?',function(row){
								      if(row){
								          var selectedRow = $('#datum').datagrid('getSelections');  //获取选中行 
										  $.ajax({
										      url:"datum_delete.action",
										      type:"post", 
										      cache:false,
										      data:"id="+selectedRow[0].datumId,
										      success:function(msg){
										          $.messager.alert('删除', msg);
										          searchs();
										      }
										  });
								      }
								  });
							  }
						  }
	             ],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	     });
	     
	      //刷新数据
	     function searchs(){
	         $('#datum').datagrid("reload",{
	             datumTypeId : $("#datumTypeId").val(),
	             datumFormatId : $("#datumFormatId").val(),
	             datumName : $("#datumName").val(),
	             requestType : 'list',
	             equipmentId : '0'
	             
	         });
			 return false;
	     }
	     
	     function save(){
	         var datumTypeId = $("#datumTypeId").val();
	         var datumTypeName = $("#datumTypeName").val().replace(/(^\s*)|(\s*$)/g, "");
			 var remark = $("#remark").val();
			 if(datumTypeName == null || datumTypeName == "" || datumTypeName.length == 0){
				 $.messager.alert('提示', '文档类型不能为空');
			 }else{
			     if(datumTypeId.length == 0){
			         $.ajax({
						 type : "post",
						 url : "datumType_save.action",
						 data : "datumTypeName=" + datumTypeName + "&remark=" + remark,
						 success: function(msg){
						     $("#move").css("display", "none");
						     $.messager.alert('添加', msg);
						 }
					 });
			     }else{
			         $.ajax({
						 type : "post",
						 url : "datumType_modify.action",
						 data : "datumTypeId=" + datumTypeId + "&datumTypeName=" + datumTypeName + "&remark=" + remark,
						 success: function(msg){
						     $("#move").css("display", "none");
						     $.messager.alert('编辑', msg);
						 }
					 });
			     }
			     searchs();
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
					  文档类型：<select name="datumTypeId" id="datumTypeId" style="height:25px; margin-right: 10px; vertical-align: middle;">
						     <option value="0">
									所有
							 </option>
							 <c:forEach items="${types}" var="type">
								 <option value="${type.datumTypeId}">
										${type.datumTypeName}
								 </option>
							 </c:forEach>
						  </select>
					  文档格式：<select name="datumFormatId" id="datumFormatId" style="height:25px; margin-right: 10px; vertical-align: middle;">
						     <option value="0">
									所有
							 </option>
							 <c:forEach items="${formats}" var="format">
								 <option value="${format.datumFormatId}">
										${format.datumFormatName}
								 </option>
							 </c:forEach>
						  </select>
					  文档名称：<input id="datumName" name="datumName" type="text" style="height:25px; margin-right: 10px; vertical-align: middle;">
					  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs();">查询</a>
				  </form>
		      </div>
		  </div>
      </div>
  	  <table id="datum" class="easyui-datagrid"></table>
  </body>
</html>