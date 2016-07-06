<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文档类型</title>
    
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
	         $('#datumType').datagrid({
	             title:'文档类型信息表',
	             iconCls:'icon-list',
	             //width:885,
	             //height:495,
	             pageSize : 15,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             url:'datumType_list.action',
	             idField:'datumTypeId',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'datumTypeId',hidden:'true'},
	                 {field:'datumTypeName',title:'文档类型',width:fixWidth(0.4),align:'center'},
	                 {field:'remark',title:'描述',width:fixWidth(0.6),align:'center'}
	             ]],
	             toolbar:[{ 
							  id:'btnadd', 
							  text:'添加', 
							  iconCls:'icon-add', 
							  handler:function(){
							      $("#datumTypeId").val("");
	                              $("#datumTypeName").val("");
			                      $("#remark").val("");
							      $("#move").css("display", "block");
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'删除', 
							  iconCls:'icon-remove', 
							  handler:function(){
								  $.messager.confirm('确认','确认删除?',function(row){
								      if(row){
								          var selectedRow = $('#datumType').datagrid('getSelections');  //获取选中行 
										  $.ajax({
										      url:"datumType_delete.action",
										      type:"post", 
										      cache:false,
										      data:"id="+selectedRow[0].datumTypeId,
										      success:function(msg){
										          $.messager.alert('删除', msg);
										          searchs();
										      }
										  });
								      }
								  });
							  }
						  },'-',{
						      id:'btnedit',  
							  text:'编辑', 
							  iconCls:'icon-edit', 
							  handler:function(){
							      var selectedRow = $('#datumType').datagrid('getSelections');  //获取选中行 
							      if(selectedRow.length > 0){
							          $("#datumTypeId").val(selectedRow[0].datumTypeId);
							          $("#datumTypeName").val(selectedRow[0].datumTypeName);
							          $("#remark").val(selectedRow[0].remark);
							          $("#move").css("display", "block");
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
	         $('#datumType').datagrid("reload",null);
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
	     
	     function move_close(){
	         $("#move").css("display", "none");
	     }
	     
	     var a;
         document.onmouseup=function(){
             if(!a){
                 return;
             }
             document.all?a.releaseCapture():window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
             a="";
         };
         document.onmousemove=function (d){
             if(!a){
                 return;
             }
             if(!d){
                 d=event;
             }
             a.style.left=(d.clientX-b)+"px";
             a.style.top=(d.clientY-c)+"px";
         };
         function mdown(e){
             a=document.getElementById("move");
             document.all?a.setCapture():window.captureEvents(Event.MOUSEMOVE);
             b=e.clientX-parseInt(a.style.left);
             c=e.clientY-parseInt(a.style.top);
         }
     </script>
 
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
  	  <table id="datumType" class="easyui-datagrid"></table>
  	  <div id="move" style="position:absolute; padding:0px; border:0px solid #999; left:220px; top:100px; background:#FFF; display:none;">
  	      <div style="width:100%; height: 20px; background:#9CF; cursor:move;" onmousedown="mdown(event);">
  	         <img style="width:600px; height: 400px" src="${pageContext.request.contextPath}/images/pop-up1.png"/>
  	      </div>
  	      <div style="width:100%; margin-left: 150px; margin-top: 70px;">
  	           <input type="hidden" name="datumTypeId" id="datumTypeId">
  	           <span style="font-size: 14px; font-weight: bolder;">文档类型:</span>
  	           <input type="text" name="datumTypeName" id="datumTypeName" style="width: 202px; height: 25px;">
  	        <div style=" margin-top: 25px; ">
  	           <span style="font-size: 14px; font-weight: bolder;">描<span style="padding-left: 28px"></span>述:</span>
  	           <textarea rows="9" cols="35" name="remark" id="remark" style="margin:5px 0px;"></textarea>
  	        </div>
  	         <div style="margin-top: 50px; margin-left: 30px;">
  	              <a href="javascript:void(0)" id="savebut" onclick="save();">
  	              <img style="width:100px; heidht:25px;" src="${pageContext.request.contextPath}/images/save.png"></a>
  	                   
  	              <span style="margin-left: 40px"></span>
  	              <a href="javascript:void(0)" id="savebut" onclick="move_close();">
  	                 <img style=" width:100px; heidht:25px;" src="${pageContext.request.contextPath}/images/close.png"></a>
  	          </div>
  	      </div>
  	  </div>
  </body>
</html>