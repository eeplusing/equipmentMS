<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加公告</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>
    <style type="text/css">
        label{
            font-size: 16px;
            color:#666;
        }
        input{
           width:80%;
           height: 25px;
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
       	function save(){
	     	 var title = $("#title").val();
	         var publisher = $("#publisher").val();
	         var content = $("#content").val();
	         if((title == null || title == "" || title.length == 0)){
				     $.messager.alert('提示', '标题不能为空');
			 } else if((publisher == null || publisher == "" || publisher.length == 0)){
				     $.messager.alert('提示', '发布人不能为空');
			 } else if((content == null || content == "" || content.length == 0)){
				     $.messager.alert('提示', '内容不能为空');
			 } else {
				 $.ajax({
	                 type : "post",
	                 url : "notice_save.action",
	                 data : "title=" + title + "&publisher=" + publisher + "&content=" + content,
	                 success: function(msg){
	                     $.messager.alert('添加公告',msg); 
	                     window.close();
	                 }
	             });
			 }
		}
    </script>
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
      <div style="height:30px; background: #EEE; text-align: center;"><span style="font-size: 18px;">添加公告</span></div>
      <div style="margin: 5px;">
    	  <span style="margin-left: 32px;"></span><label for="title">标题:</label>
    	  <input class="easyui-validatebox"  class="txt" type="text" id="title" value=""/>
      </div>
      <div style="margin: 5px;">
    	  <span style="margin-left: 16px;"></span><label for="Publisher">发布人:</label>
    	  <input class="easyui-validatebox"  readonly="readonly" value="${loginuser.userName}" class="txt" type="text" id="publisher"/>
      </div>
      <div style="margin: 5px;">
    	  <label for="Content">公告内容:</label>
    	  <textarea class="easyui-validatebox" id ="content" cols="70" rows="18"></textarea>
      </div>
      <div style="margin: 5px;">
          <input type="button" onclick="save();" style="margin-left: 14.5%;" value="确定">
      </div>
  </body>
</html>
