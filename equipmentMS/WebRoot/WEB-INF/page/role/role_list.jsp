<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色管理主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    
    
    <script type="text/javascript">
         function fixWidth(percent)     
         {     
            return (document.body.clientWidth-32) * percent ;      
         }  
         
	     $(function(){
	         $('#role').datagrid({
	             title:'角色管理列表',
	             iconCls:'icon-list',
	             //width:885,
	             //height:495,
	             pageSize : 15,//默认选择的分页是每页5行数据
	             pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
	             nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             url:'role_list.action',
	             
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'roleId',width:0,align:'center',hidden:'true'},
	                 {field:'name',title:'名称',width:fixWidth(0.35),align:'center'},
	                 {field:'describe',title:'描述',width:fixWidth(0.35),align:'center'},
	                 {field:'opt',title:'操作',width:fixWidth(0.3),align:'center',
                            formatter:function(value, rowData, rowIndex){
                                return '<a href="#" onclick="giveRight(\'' + rowData.roleId + '\')">授权</a>';
                            }
                     }
	             ]],
	             toolbar:[{ 
							  id:'btnadd', 
							  text:'添加角色', 
							  iconCls:'icon-add', 
							  handler:function(){
								  $("#roleId").val("");
	                              $("#name").val("");
			                      $("#describe").val("");
							      $("#move").css("display", "block");
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'删除角色', 
							  iconCls:'icon-remove', 
							  handler:function(){
								  $.messager.confirm('确认','确认删除?',function(row){
								      if(row){
								          var selectedRow = $('#role').datagrid('getSelections');  //获取选中行 
										  $.ajax({
										      url:"role_delete.action",  //调用后台方法是吗
										      type:"post", 
										      cache:false,
										      data:"roleId="+selectedRow[0].roleId,
										      success:function(msg){
										          $.messager.alert('删除用户', msg);
										          searchs();
										      }
										  });
								      }
								  });
							  }
						  },'-',{
						      id:'btncut',  
							  text:'编辑角色', 
							  iconCls:'icon-edit', 
							  handler:function(){
							      var selectedRow = $('#role').datagrid('getSelections');  //获取选中行
								  if(selectedRow.length > 0){
                                      $("#roleId").val(selectedRow[0].roleId);
	                                  $("#name").val(selectedRow[0].name);
			                          $("#describe").val(selectedRow[0].describe);
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
	     
	   
	     function giveRight(role_id){
	         window.showModalDialog("right_getRightTree.action?roleId=" + role_id,"","dialogWidth=1000px;dialogHeight=600px");
	         searchs();
	     }
	     
	     function save(){
	         var roleId = $("#roleId").val();
	         var name = $("#name").val();
			 var describe = $("#describe").val();
			 if((name == null || name == "" || name.length == 0)){
				 $.messager.alert('提示', '角色名称不能为空');
				 
			 }else if((describe == null || describe == "" || describe.length == 0)){
			     $.messager.alert('提示', '描述不能为空');
			 }else{
			     if(roleId.length == 0){
			         $.ajax({
						 type : "post",
						 url : "role_save.action",
						 data : "name=" + name + "&describe=" + describe,
						 success: function(msg){
						     move_close();
						     $.messager.alert('添加角色', msg);
						 }
					 });
			     }else{
			         $.ajax({
						 type : "post",
						 url : "role_modify.action",
						 data : "roleId=" + roleId + "&name=" + name + "&describe=" + describe,
						 success: function(msg){
						     move_close();
						     $.messager.alert('编辑角色', msg);
						 }
					 });
			     }
			 }
			 searchs();
		}
		     
	    function move_close(){
	         $("#move").css("display", "none");
	         $("#roleId").val("");
	         $("#name").val("");
			 $("#describe").val("");
	    }
	     
	     
	      //刷新数据
	     function searchs(){
	         $('#role').datagrid("reload");
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
     </script>
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">    
  	  <table id="role" class="easyui-datagrid"></table>
      <div id="move" style="position:absolute;  padding:0px; border:0px solid #999; left:220px; top:200px; background:#FFF; display:none;">
  	      <div style="width:100%; height: 20px; background:#9CF; cursor:move;" onmousedown="mdown(event);">
  	      	 <img style="width:535px; height: 350px" src="${pageContext.request.contextPath}/images/pop-up1.png"/>
  	      </div>
  	      <div style="width:100%; margin-left: 150px; margin-top: 70px;">
  	          <input type="hidden"  id="roleId">
  	          <div>
  	              <span style="font-size: 14px;font-weight: bolder;">名称:</span>
  	              <input type="text" id="name" name="name" style="width: 200px; height: 25px;">
  	          </div>
  	          <div style="margin-top: 25px;">
  	              <span style="font-size: 14px;font-weight: bolder;">描述:</span>
  	              <input type="text"  id="describe" name="describe" style="width: 200px; height: 25px;">
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
