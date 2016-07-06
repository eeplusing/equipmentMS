<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统日志</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>

<style type="text/css">
    input,select{ 
           height: 20px;
           border: 1px solid #DDD; 
    }
    input:FOCUS {
	       border: 1px solid #9CF;
    }
    input:HOVER {
	       border: 1px solid #9CF;
    }
</style>

<script type="text/javascript">
    function fixWidth(percent)     
    {     
       return (document.body.clientWidth-32) * percent ;      
    }  
    
	$(function() {
		$('#operLog').datagrid({
			title : '系统日志',
			iconCls : 'icon-list',
			//width : 885,
			//height : 495,
			pageSize : 15,//默认选择的分页是每页5行数据
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			singleSelect : true, //单选模式
			collapsible : true,//显示可折叠按钮
			url : 'operlog_list.action',
			queryParams : {
				operType : $("#operType").val(), /*向后台传入的pperType : $("#OperType").val()中的pperType不是#OperType,后台接收到的是pperType不是#OperType*/
				operator : $("#operator").val(),
				operLogTimeST : $("#operLogTimeST").val(),
				operLogTimeED : $("#operLogTimeED").val()
			},
			loadMsg : '装载中...',
			columns : [ [ {
				field : 'operLogId',
				width : 0,
				align : 'center',
				hidden : 'true'
			}, {
				field : 'operType',
				title : '操作类型',
				width : fixWidth(0.2),
				align : 'center',
				formatter:function(value, rowData, rowIndex){
	                            if(value == 1) return "登陆系统";
	                            else if(value == 2) return "退出系统";
	                            else if(value == 3) return "备品入库";
	                            else if(value == 4) return "备品出库";
	                            else if(value == 5) return "文档上传";
	                            else if(value == 6) return "文档下载";
	                            else if(value == 7) return "文档删除";
	                            else if(value == 8) return "删除设备";
	                            else if(value == 9) return "更新设备";
	                            else if(value == 0) return "增加设备";
	                            else if(value == 18) return "删除备品";
	                            else if(value == 19) return "更新备品";
	                            else if(value == 10) return "增加备品";
	                            else if(value == 11) return "其他";
	                      }
			}, {
				field : 'operLogContent',
				title : '操作信息',
				width : fixWidth(0.2),
				align : 'center'
			}, {
				field : 'operator',
				title : '操作人员',
				width : fixWidth(0.2),
				align : 'center'
			}, {
				field : 'operLogTime',
				title : '操作时间',
				width : fixWidth(0.2),
				align : 'center'
			}, {
				field : 'operLogRemark',
				title : '操作描述',
				width : fixWidth(0.2),
				align : 'center'
			} ] ],
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

	//刷新数据
	function searchs() {
		$('#operLog').datagrid("reload", {
			operType : $("#operType").val(), /*向后台传入的pperType : $("#OperType").val()中的pperType不是#OperType,后台接收到的是pperType不是#OperType*/
			operator : $("#operator").val(),
			operLogTimeST : $("#operLogTimeST").val(),
			operLogTimeED : $("#operLogTimeED").val()
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
					      <span style="margin-left: 10px"></span>
                                                                                 操作类型：<select id="operType" style="height:25px; margin-right: 10px; vertical-align: middle;">
                              <option value="-1" selected="selected">所有类型</option>
                              <option value="1">登陆系统</option>
                              <option value="2">退出系统</option>
                              <option value="3">备品入库</option>
                              <option value="4">备品出库</option>
                              <option value="5">文档上传</option>
                              <option value="6">文档下载</option>
                              <option value="7">文档删除</option>
                              <option value="8">删除设备</option>
                              <option value="9">更新设备</option>
                              <option value="0">增加设备</option>
                              <option value="18">删除备品</option>
                              <option value="19">更新备品</option>
                              <option value="10">增加备品</option>
                              <option value="11">其他</option>
                          </select>
                   操作人员：<input type="text" id="operator" style="height:25px; margin-right: 10px;" />
                    起始：<input type="text" id="operLogTimeST" onfocus="$(this).calendar()" maxlength="10" style="height:25px; margin-right: 10px; vertical-align: middle;" />
                    结束：<input type="text" id="operLogTimeED" onfocus="$(this).calendar()" maxlength="10" style="height:25px; margin-right: 10px; vertical-align: middle;" />
						 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchs();">查询</a>
				</form>
			</div>
		</div>
	</div>
	<table id="operLog" class="easyui-datagrid"></table>
	<div id="getPager"></div>
</body>
</html>