<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统公告</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>

<script type="text/javascript">
    function fixWidth(percent)     
    {     
       return (document.body.clientWidth-32) * percent ;      
    }  
         
	$(function() {
		$('#notice').datagrid({
			title : '系统公告',
			iconCls : 'icon-list',
			//width : 885,
			//height : 495,
			pageSize : 15,//默认选择的分页是每页5行数据
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			singleSelect : true, //单选模式
			collapsible : true,//显示可折叠按钮
			url : 'notice_list.action',
			queryParams : {
				title : $("#title").val(), 
				publisher : $("#publisher").val(),
				noticeTimeST : $("#noticeTimeST").val(),
				noticeTimeED : $("#noticeTimeED").val()
			},
			
			loadMsg : '装载中...',
			columns : [ [ {
				field : 'noticeId',
				width : 0,
				align : 'center',
				hidden : 'true'
			}, {
				field : 'title',
				title : '公告标题',
				width : fixWidth(0.3),
				align : 'center'
			}, {
				field : 'content',
				width : 0,
				align : 'center',
				hidden : 'true'
			}, {
				field : 'publisher',
				title : '发布人员',
				width : fixWidth(0.2),
				align : 'center'
			}, {
				field : 'noticeTime',
				title : '发布时间',
				width : fixWidth(0.3),
				align : 'center'
			} ,{field:'opt',title:'操作',width:fixWidth(0.2),align:'center',
                            formatter:function(value, rowData, rowIndex){
                                return '<a href="#" onclick="Open(\'' + rowData.noticeId + '\')">打开</a>';
                            }
                     }] ],
			toolbar:[{ 
							  id:'btnadd', 
							  text:'添加公告', 
							  iconCls:'icon-add', 
							  handler:function(){
								  window.showModalDialog("notice_openSaveWindow.action","","dialogWidth=500px;dialogHeight=400px");
								  searchs();
							  } 
						  },'-',{
						      id:'btncut',  
							  text:'删除公告', 
							  iconCls:'icon-remove', 
							  handler:function(){
								  $.messager.confirm('确认','确认删除?',function(row){
								      if(row){
								          var selectedRow = $('#notice').datagrid('getSelections');  //获取选中行 
										  $.ajax({
										      url:"notice_delete.action",  //调用后台方法
										      type:"post", 
										      cache:false,
										      data:"noticeId="+selectedRow[0].noticeId,
										      success:function(msg){
										          $.messager.alert('删除用户', msg);
										          searchs();
										      }
										  });
								      }
								  });
							  }
						  }
	             ],
			pagination : true,//分页
			rownumbers : true
		//行数
		});

		var p = $('#dataGrid').datagrid('getPager');
		if (p) {
			$(p).pagination({
				beforePageText : "第",
				afterPageText : "共{pages}页",
				displayMsg : "共{total}条记录"
			});
		}
	});
	function Open(noticeId){
	         window.showModalDialog("notice_openModifyWindow.action?noticeId=" + noticeId,"","dialogWidth=700px;dialogHeight=600px");
	         searchs();
	     }
	//刷新数据
	function searchs() {
		$('#notice').datagrid("reload", {
			    title : $("#title").val(), 
				publisher : $("#publisher").val(),
				noticeTimeST : $("#noticeTimeST").val(),
				noticeTimeED : $("#noticeTimeED").val()
		});
		return false;
	}
</script>
</head>

<body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
	<div style="width: 100%; height: 40px;">
		<div class="gridOutLow">
			<div id="tab_post" style="padding-top: 10px;">
				<form id="form1" name="form1">
					<span style="padding-left: 10px"></span>
					    标题：<input type="text" id="title" style="height:25px; margin-right: 10px;" /> 
					操作人员：<input type="text" id="publisher" style="height:25px; margin-right: 10px;" />
                        起始：<input type="text" id="noticeTimeST" onfocus="$(this).calendar()" maxlength="10" style="height:25px; margin-right: 10px;"/>
                        结束：<input type="text" id="noticeTimeED" onfocus="$(this).calendar()" maxlength="10" style="height:25px; margin-right: 10px;" />
                              <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs();">查询</a>
				</form>
			</div>
		</div>
	</div>
	<table id="notice" class="easyui-datagrid"></table>
	<div id="getPager"></div>
</body>
</html>