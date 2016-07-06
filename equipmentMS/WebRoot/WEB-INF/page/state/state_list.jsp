<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>使用状态</title>
    
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
	         $('#state').datagrid({
	             title:'使用状态信息表',
	             iconCls:'icon-list',
	             //width:885,
	             //height:495,
	             pageSize : 15,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             url:'state_list.action',
	             idField:'useStateId',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'useStateId',hidden:'true'},
	                 {field:'useStateName',title:'状态名称',width:fixWidth(0.4),align:'center'},
	                 {field:'useStateRemark',title:'描述',width:fixWidth(0.6),align:'center'}
	             ]],
	             toolbar:[{ 
							  id:'btnadd', 
							  text:'添加状态', 
							  iconCls:'icon-add', 
							  handler:function(){
							      $("#move").css("display", "block");
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'删除状态', 
							  iconCls:'icon-remove', 
							  handler:function(){
								  $.messager.confirm('确认','确认删除?',function(row){
								      if(row){
								          var selectedRow = $('#state').datagrid('getSelections');  //获取选中行 
										  $.ajax({
										      url:"state_delete.action",
										      type:"post", 
										      cache:false,
										      data:"id="+selectedRow[0].useStateId,
										      success:function(msg){
										          $.messager.alert('删除状态', msg);
										          searchs();
										      }
										  });
								      }
								  });
							  }
						  },'-',{
						      id:'btncut',  
							  text:'编辑状态', 
							  iconCls:'icon-edit', 
							  handler:function(){
							      var selectedRow = $('#state').datagrid('getSelections');  //获取选中行
								  if(selectedRow.length > 0){
                                      $("#useStateId").val(selectedRow[0].useStateId);
	                                  $("#useStateName").val(selectedRow[0].useStateName);
			                          $("#useStateRemark").val(selectedRow[0].useStateRemark);
							          $("#move").css("display", "block");
								  }
							  }
						  }
	             ],
	             pagination : true,//分页
	             rownumbers : true//行数
	         });
	         
	         var p = $('#dataGrid').datagrid('getPager');
             if (p){
                $(p).pagination({
                    beforePageText:"第",
                    afterPageText:"共{pages}页",
                    displayMsg:"共{total}条记录"
                });
             }
	     });
	     
	      //刷新数据
	     function searchs(){
	         $('#state').datagrid("reload",null);
			 return false;
	     }
	     
	     function save(){
	         var useStateId = $("#useStateId").val();
	         var useStateName = $("#useStateName").val().replace(/(^\s*)|(\s*$)/g, "");
			 var useStateRemark = $("#useStateRemark").val();
			 if(useStateName == null || useStateName == "" || useStateName.length == 0){
				 $.messager.alert('提示', '状态名称不能为空');
			 }else{
			     if(useStateId.length == 0){
			         $.ajax({
						 type : "post",
						 url : "state_save.action",
						 data : "useStateName=" + useStateName + "&useStateRemark=" + useStateRemark,
						 success: function(msg){
						     $.messager.alert('添加状态', msg);
						     move_close();
						 }
					 });
			     }else{
			         $.ajax({
						 type : "post",
						 url : "state_modify.action",
						 data : "useStateId=" + useStateId + "&useStateName=" + useStateName + "&useStateRemark=" + useStateRemark,
						 success: function(msg){
						     $.messager.alert('编辑状态', msg);
						     move_close();
						 }
					 });
			     }
			     searchs();
		     }
	     }
	     
	     function move_close(){
	         $("#move").css("display", "none");
	         $("#useStateId").val("");
	         $("#useStateName").val("");
			 $("#useStateRemark").val("");
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
         function mdown(o,e){
             a=o;
             document.all?a.setCapture():window.captureEvents(Event.MOUSEMOVE);
             b=e.clientX-parseInt(a.style.left);
             c=e.clientY-parseInt(a.style.top);
         }
     </script>
 
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
  	  <table id="state" class="easyui-datagrid"></table>
  	  <div id="move" style="position:absolute; padding:0px; border:0px solid #999; left:500px; top:100px; background:#FFF; display:none;" onmousedown="mdown(this,event)">
  	      <div style="width:100%; height: 20px; background:#9CF; cursor:move;" onmousedown="mdown(event);">
  	      	  <img style="width:535px; height: 350px" src="${pageContext.request.contextPath}/images/pop-up1.png"/>
  	      </div>
  	      <div style="width:100%; margin-left: 130px; margin-top: 50px;">
  	          <input type="hidden" name="useStateId" id="useStateId">
  	          <div style="margin: 5px;">
  	             <span style="font-size: 14px; font-weight: bolder;">状态名:</span>
  	              <input type="text" name="useStateName" id="useStateName" style="width: 202px; height: 25px; border: 1px solid #CCC;">
  	          </div>
  	          <div style="margin: 5px;margin-top: 10px;">
  	              <span style="font-size: 14px; font-weight: bolder;">描<span style="padding-left: 14px"></span>述:</span>
  	              <textarea rows="9" cols="35" name="useStateRemark" id="useStateRemark"></textarea>
  	          </div>
  	          <div style="margin-top: 40px;margin-left: 30px;">
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