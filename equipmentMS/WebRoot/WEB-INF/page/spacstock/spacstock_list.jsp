<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>库存清单</title>
    
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
	         $('#bill').datagrid({
	             title:'库存清单信息表',
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
	             	modle : 'list'
	             },
	             idField:'spacstockId',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'spacstockId',hidden:'true'},
	                 {field:'sparepartName',title:'备品名称',width:fixWidth(0.4),align:'center'},
	                 {field:'spacstockMinamount',title:'安全库存量',width:fixWidth(0.3),align:'center'},
	                 {field:'spacstockAmount',title:'库存量',width:fixWidth(0.3),align:'center'}
	             ]],
	             toolbar:[{
						      id:'btncut',  
							  text:'设置库存下限', 
							  iconCls:'icon-edit', 
							  handler:function(){
								  var selectedRow = $('#bill').datagrid('getSelections');  //获取选中行 
								  if(selectedRow.length > 0){
								      move_open(selectedRow[0].spacstockId);
								  }else{
								      $.messager.alert('提示', '请选择一条库存数据，再设置上下限');
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
	         $('#bill').datagrid("reload");
			 return false;
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
         
         function move_close(){
             $("#move").css("display", "none");
             $("#typeId").val("");
             $("#minamount").val("");
         }
         
         function move_open(tid){
             $("#move").css("display", "block");
             $("#spacstockId").val(tid);
         }
         
         function modify(){
             $.ajax({
			     url:"spacstock_modify.action",
				 type:"post", 
				 cache:false,
				 data:"spacstockId=" + $("#spacstockId").val() + "&minamount=" + $("#minamount").val(),
				 success:function(msg){
				     $.messager.alert('提示', msg);
				     move_close();
				     searchs();
				 }
			 });
             
         }
     </script>
 
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
  	  <table id="bill" class="easyui-datagrid"></table>
	  <div id="move" style="position:absolute; width:200px; height:90px; padding:0px; border:1px solid #999; left:450px; top:200px; background:#FFF; display:none;">
  	      <div style="width:100%; height: 25px; background:#9CF; cursor:move;" onmousedown="mdown(event);">
  	      	  <a href="javascript:void(0);" onclick="move_close();" style="margin-left: 170px; color: #000;">关闭</a>
  	      </div>
  	      <div style="width:100%;">
  	          <input type="hidden" id="spacstockId">
  	          <div style="margin: 5px;">
  	              安全库存量:<input type="text" id="minamount" name="minamount" style="width: 120px;">
  	          </div>
  	          <div>
  	              <input type="button" onclick="modify();" value="确定"  style="margin-left: 70px;">
  	              <input type="button" onclick="move_close();" value="取消"  style="margin-left: 10px;">
  	          </div>
  	      </div>
  	  </div>
  </body>
</html>
