<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备类型</title>
    
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
	         $('#type').datagrid({
	             title:'物品类型信息表',
	             iconCls:'icon-list',
	             //width:885,
	             //height:495,
	             pageSize : 15,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             url:'type_list.action',
	             queryParams:{//初始查询参数
	                 typeName : $("#typeName").val(),
	                 parentId : $("#parentId").val()
	             },
	             idField:'typeId',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'typeId',hidden:'true'},
	                 {field:'typeName',title:'类型名称',width:fixWidth(0.5),align:'center'},
	                 {field:'parentName',title:'父类名称',width:fixWidth(0.5),align:'center',
	                     formatter:function(value, rowData, rowIndex){
	                            if(value == null) return "总类";
	                            else return value;
	                      }
	                 
	                 }
	             ]],
	             toolbar:[{ 
							  id:'btnadd', 
							  text:'添加类型', 
							  iconCls:'icon-add', 
							  handler:function(){
							      $("#move").css("display", "block");
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'删除类型', 
							  iconCls:'icon-remove', 
							  handler:function(){
								  $.messager.confirm('确认','确认删除?',function(row){
								      if(row){
								          var selectedRow = $('#type').datagrid('getSelections');  //获取选中行 
										  $.ajax({
										      url:"type_delete.action",
										      type:"post", 
										      cache:false,
										      data:"id="+selectedRow[0].typeId,
										      success:function(msg){
										          $.messager.alert('删除类型', msg);
										          searchs();
										      }
										  });
								      }
								  });
							  }
						  },'-',{
						      id:'btncut',  
							  text:'编辑类型', 
							  iconCls:'icon-edit', 
							  handler:function(){
								  var selectedRow = $('#type').datagrid('getSelections');  //获取选中行 
								  if(selectedRow.length > 0){
									  $("#typeIds").val(selectedRow[0].typeId);
									  $("#typeNames").val(selectedRow[0].typeName);
									  $("#parentIds").val(selectedRow[0].parentId);
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
	         $('#type').datagrid("reload",{
	             typeName : $("#typeName").val(),
	             parentId : $("#parentId").val()
	         });
			 return false;
	     }
	     
	     function move_close(){
	         $("#move").css("display", "none");
	         $("#typeIds").val("");
			 $("#typeNames").val("");
			 $("#parentIds").val("0");
	     }
	     
	     function save(){
	         var typeId = $("#typeIds").val();
	         var typeName = $("#typeNames").val().replace(/(^\s*)|(\s*$)/g, "");
			 var parentId = $("#parentIds").val();
			 if(typeName == null || typeName == "" || typeName.length == 0){
			     $.messager.alert('提示', '类型名称不能为空');
			 }else{
			     if(typeId.length == 0){
			         $.ajax({
						 type : "post",
						 url : "type_save.action",
						 data : "typeName=" + typeName + "&parentId=" + parentId,
						 success: function(msg){
						     var ms = msg.split(";");
						     $.messager.alert('添加类型', ms[0]);
						     move_close();
							 searchs();
						 }
					 });
			     }else{
			         $.ajax({
						 url:"type_modify.action",
						 type:"post", 
						 cache:false,
						 data:"typeId=" + typeId + "&typeName=" + typeName + "&parentId=" + parentId,
						 success:function(msg){
						     $.messager.alert('编辑类型', msg);
						     move_close();
						     searchs();
					     }
					 });
			     }
			 }
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
      <div style="width: 100%; height: 40px;">
		  <div class="gridOutLow">
			  <div id="tab_post" style="padding-top: 10px;">
				  <form id="form1" name="form1">
					  <span style="padding-left: 10px"></span>
					  类型名称：<input type="text" id="typeName" style="height:25px; margin-right: 10px; vertical-align: middle;" />
					  父类：<select name="parentId" id="parentId" style="height:25px; margin-right: 10px; vertical-align: middle;">
					     <option value="-1">
								所有
						 </option>
					     <option value="0">
								总类
						 </option>
						 <c:forEach items="${types}" var="type">
							 <option value="${type.typeId}">
									${type.typeName}
							 </option>
						 </c:forEach>
					  </select>
					  <span style="margin-left: 20px;"></span>
					  <a href="javascript:void(0)" id="searchbut" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs();">查询</a>
				  </form>
		      </div>
		  </div>
      </div>
  	  <table id="type" class="easyui-datagrid"></table>
  	  <div id="move" style="position:absolute;padding:0px; border:1px solid #999; left:500px; top:100px; background:#FFF; display:none;">
  	      <div style="width:100%; height: 20px; background:#9CF; cursor:move;" onmousedown="mdown(event);">
  	      	  <img style="width:535px; height: 350px" src="${pageContext.request.contextPath}/images/pop-up1.png"/>
  	      </div>
  	      <div style="width:100%;margin-left: 150px; margin-top: 70px;">
  	          <input type="hidden" id="typeIds" />
  	          <div>
  	              <span style="font-size: 14px; font-weight: bolder;">类型名称:</span>
  	              <input type="text" id="typeNames" name="typeNames" style="width: 200px; height: 25px;">
  	          </div>
  	          <div style="margin-top: 25px;">
  	              <span style="font-size: 14px; font-weight: bolder;">父<span style="margin-left: 30px;"></span>类:</span>
  	              <select id="parentIds" name="parentIds"  style="width: 200px; height: 25px;">
		              <option value="0">总类</option>
					  <c:forEach items="${types}" var="type">
						  <option value="${type.typeId}">
							  ${type.typeName}
						  </option>
					  </c:forEach>
		          </select>
              </div>
              <div style="margin-top: 50px">
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