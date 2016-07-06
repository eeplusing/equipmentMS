<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>账号管理</title>
    
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
	         $('#user').datagrid({
	             title:'用户信息列表',
	             iconCls:'icon-list',
	             //width:885,
	             //height:495,
	             pageSize : 15,//默认选择的分页是每页5行数据
	             pageList : [5, 10, 15, 20],//可以选择的分页集合
	             nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             collapsible : true,//显示可折叠按钮
	             url:'user_list.action',
	             queryParams:{
	                 account : $("#account").val(),
	                    name : $("#name").val(),
	                  roleId : $("#roleId").val()
	             },
	             idField:'loginAccount',
                 loadMsg:'装载中...',
	             columns:[[
	                 {field:'userId',hidden:'true'},
	                 {field:'loginAccount',title:'账号',width:fixWidth(0.15),align:'center'},
	                 {field:'userName',title:'姓名',width:fixWidth(0.15),align:'center'},
	                 {field:'userPhone',title:'手机号码',width:fixWidth(0.2),align:'center'},
	                 {field:'isActivated',title:'是否激活',width:fixWidth(0.15),align:'center',
	                        formatter:function(value, rowData, rowIndex){
	                            if(value == 0) return "否";
	                            else if(value == 1) return "是";
	                        }
	                 },
	                 {field:'email',title:'邮箱',width:fixWidth(0.2),align:'center'},
	                 {field:'roleName',title:'角色',width:fixWidth(0.15),align:'center',
	                        formatter:function(value, rowData, rowIndex){
	                            if(value == null || value == "") return "无角色";
	                            else return value;
	                        }
	                 }
	             ]],
	             toolbar:[{ 
							  id:'btnadd', 
							  text:'添加账号', 
							  iconCls:'icon-add', 
							  handler:function(){
							      $("#one").css("display", "block");
							      $("#move").css("display", "block").css("height", "110px");
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'删除账号', 
							  iconCls:'icon-remove', 
							  handler:function(){
								  $.messager.confirm('确认','确认删除?',function(row){
								      if(row){
								          var selectedRow = $('#user').datagrid('getSelections');  //获取选中行 
										  $.ajax({
										      url:"user_delete.action",
										      type:"post", 
										      cache:false,
										      data:"loginAccount="+selectedRow[0].loginAccount,
										      success:function(msg){
										          $.messager.alert('删除用户', msg);
										          searchs();
										      }
										  });
								      }
								  });
							  }
						  },'-',{ 
							  id:'btnadd', 
							  text:'分配角色', 
							  iconCls:'icon-edit', 
							  handler:function(){
							      var selectedRow = $('#user').datagrid('getSelections');
							      if(selectedRow.length > 0){
							          $("#one").css("display", "none");
								      $("#userId").val(selectedRow[0].userId);
		                              $("#loginAccount").val("");
				                      $("#role_id").val(selectedRow[0].roleId);
								      $("#move").css("display", "block").css("height", "85px");
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
	     
	     function save(){
	         var userId = $("#userId").val().replace(/(^\s*)|(\s*$)/g, "");
	         var loginAccount = $("#loginAccount").val().replace(/(^\s*)|(\s*$)/g, "");
	         var role_id = $("#role_id").val();
			 if(userId.length == 0){
			     if(loginAccount == null || loginAccount == "" || loginAccount.length == 0){
			         $.messager.alert('提示', "账户不能为空");
			     }else{
			         $.ajax({
						 type : "post",
						 url : "user_save.action",
						 data : "loginAccount=" + loginAccount + "&roleId=" + role_id,
						 success: function(msg){
						     move_close();
						     $.messager.alert('添加账户', msg);
						 }
					 });
			     }
			 }else{
			         $.ajax({
						 type : "post",
						 url : "user_modifyRole.action",
						 data : "roleId=" + role_id + "&userId=" + userId,
						 success: function(msg){
						     move_close();
						     $.messager.alert('角色分配', msg);
						 }
					 });
			 }
			 searchs();
	     }
	     
	      //刷新数据
	     function searchs(){
	         $('#user').datagrid("reload",{
	             account : $("#account").val(),
	             name : $("#name").val(),
	             roleId : $("#roleId").val()
	         });
			 return false;
	     }
	     
	     function move_close(){
	         $("#move").css("display", "none");
	         $("#userId").val("");
	         $("#loginAccount").val("");
			 $("#role_id").val("0");
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
					  账户：<input type="text" id="account" style="height:25px; margin-right: 10px; vertical-align: middle;" />
					  姓名：<input type="text" id="name" style="height:25px; margin-right: 10px; vertical-align: middle;" />
					  角色：<select name="roleId" id="roleId" style="height:25px; margin-right: 10px; vertical-align: middle;">
			  	               <option value="0">所有角色</option>
			  	               <c:forEach items="${roles}" var="role">
			  	                   <option value="${role.roleId}">${role.name}</option>
			  	               </c:forEach>
  	           			</select>
					  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs();">查询</a>
				  </form>
		      </div>
		  </div>
      </div>
  	  <table id="user" class="easyui-datagrid"></table>
  	  <div id="move" style="position:absolute; padding:0px; border:0px solid #999; left:220px; top:200px; background:#FFF; display:none;">  
  	      <div style="width:100%; height: 20px; background:#9CF; cursor:move;" onmousedown="mdown(event);">
  	      	 <img style="width:535px; height: 350px" src="${pageContext.request.contextPath}/images/pop-up1.png"/>
  	      </div>
  	      <div style="width:100%; margin-left: 150px; margin-top: 70px;">
  	          <input type="hidden" id="userId" name="userId">
  	          <div id="one">
  	              <span style="font-size: 14px; font-weight: bolder;">账号:</span>
  	              <input type="text" id="loginAccount" name="loginAccount" style="width: 200px; height: 25px;">
  	          </div>
  	          <div style=" margin-top: 25px; ">
  	             <span style="font-size: 14px;font-weight: bolder;"> 角色:</span>
  	                  <select name="roleId" id="role_id" style="width:200px; height: 25px;">
				  	          <option value="0">默认角色</option>
				  	       <c:forEach items="${roles}" var="role">
				  	           <option value="${role.roleId}">${role.name}</option>
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