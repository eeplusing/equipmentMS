<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    
    <title>加入文档</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ajaxfileupload.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI-2.3.3.js"></script>
    <style>
        body{
           width: 620px;
           height: 600px;
           overflow: hidden;
        }
		#browser{
			width:95%;
			margin:0px 17px;
			border-collapse:collapse;
			font-family:arial;
			text-align:center;
			border:1px solid #bbb;
		}
		#browser thead tr{
		    height:30px;
		}
		#xhtml td{	
			border:1px solid #bbb;
			background-color:#EEE;
			color:#123;
			font-size:16px;
			padding:5px 0px;
		}
		#browser thead th {
			border:1px solid #bbb;
			background-color:#EEE;
			color:#000;	
			padding:10px 0px;
			font-size:16px;
		}
		#browser caption{
		    border:1px solid #bbb;
		    border-bottom:none;
			font-weight:bold;
			padding:6px 0px;
			color:#000;	
			font-size:25px;
		}
		
		input[type="button"]{
	       	border:1px solid #0A93D4; 
	        width: 110px; 
	        height:30px;
	        padding-left: 15px;
	        color:#FFF;
	        font-weight:bolder;
	        background: url('${pageContext.request.contextPath}/images/abutton.png') no-repeat 0px -8px;
	        cursor: pointer;
		}
	</style>
    
    <script type="text/javascript">
       var datum_info = "";
        function save(){
            $.ajax({
				type : "post",
				url : "equ_datum.action",
				data : "datumIds=" + datum_info + "&equipmentId=" + $("#equipmentId").val(),
				success: function(msg){
					$.messager.alert('提示', msg);
				}
			});
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
         
         function selDatum(){
             $('#datum').datagrid({
	             width:500,
	             pageSize : 10,//默认选择的分页是每页5行数据
	             pageList : [10],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'datum_list.action',
	             queryParams:{//初始查询参数
	                  datumTypeId : '0',
	                  datumFormatId : '0',
	                  datumName : $("#datumName").val(),
	                  requestType : 'rele',
	                  equipmentId : $("#equipmentId").val()
	             },
	             idField:'datumId',
                 loadMsg:'装载中...',
                 columns:[[
                 	 {field:'datumId',hidden:'true'},
	                 {field:'datumName',title:'文档名称',width:150,align:'center', sortable:true},
	                 {field:'datumTypeName',title:'文档类别',width:100,align:'center', sortable:true},
	                 {field:'datumFormatName',title:'文档格式',width:100,align:'center', sortable:true}
                 ]],
	             onDblClickCell:function(){ 
                     var selectedRow = $('#datum').datagrid('getSelections');
                     if(selectedRow.length > 0){
                         if(datum_info.indexOf(";" + selectedRow[0].datumId + ";") < 0 && datum_info.indexOf(selectedRow[0].datumId + ";") != 0){
                         	 $("#xhtml").html($("#xhtml").html() + "<tr id='E" + selectedRow[0].datumId + "' onclick='selRow(\"" + selectedRow[0].datumId + "\");'><td>" + selectedRow[0].datumName + "</td><td>" + selectedRow[0].datumTypeName + "</td><td>" + selectedRow[0].datumFormatName + "</td></tr>");
                             datum_info += selectedRow[0].datumId + ";";
                             move_close();
                         }
                     }
                 },
	             pagination : true
	         });
         }
         
         function searchs(e){
             var keycode = e.keyCode || e.which || e.charCode;
             if(keycode == 13){
                 selDatum();
             }
         }
         
         function clearTxt(){
             $("#datumName").val("");
             selDatum();
         }
         
         function move_close(){
             $("#move").css("display", "none");
         }
         
         function move_open(){
             $("#move").css("display", "block");
         }
         
         var oid = "";
         function selRow(eid){
             if(oid != ""){
                 var otds = document.getElementById(oid).getElementsByTagName("td");
                 setColor(otds, "#EEE");
             }
             
             var ntds = document.getElementById("E" + eid).getElementsByTagName("td");
             setColor(ntds, "#FFF");
             oid = "E" + eid;
         }
         
         function setColor(tds, colors){
             for(var i = 0; i < tds.length; i++){
                 tds[i].style.backgroundColor=colors;
             }
         }
         
         function removeTag(){
             if(oid != ""){
                 var node = document.getElementById(oid);
                 node.parentNode.removeChild(node);
                 var ostr = oid.substring(1, oid.length);
                 var datum_ids = datum_info.split(";");
                 datum_info = "";
                 for(var i = 0; i < datum_ids.length - 1; i++){
                     if(datum_ids[i] != ostr){
                         datum_info += datum_ids[i] + ";";
                     }
                 }
                 oid = "";
             }else{
                 alert("请先选中,再点击删除");
             }
         }
    </script>
  </head>
  
  <body>
     <div id="loading"></div>
  	 <form method="post" style="padding: 10px; ">
  	     <div style="margin-bottom: 10px;">
  	         <input type="hidden" name="equipmentId" id="equipmentId" value="${equipment.equipmentId}"/>
  	         <label style="width: 80px; font-size: 14px;">设备编号：</label>
  	         <input type="text" name="equipmentNo" id="equipmentNo" readonly="readonly" value="${equipment.equipmentNo}" style="width: 30%; height: 25px; background: #FFF; font-size: 17px; color:#666; border: 1px solid #26A0DA;"/>
  	         <label style="width: 80px; font-size: 14px;">设备名称：</label>
  	         <input type="text" name="equipmentName" id="equipmentName" readonly="readonly" value="${equipment.equipmentName}" style="width: 30%; height: 25px; background: #FFF; font-size: 17px; color:#666; border: 1px solid #26A0DA;"/>
  	     </div>
  	     <div style="margin-bottom: 10px;">
  	         <label style="width: 80px; font-size: 14px;">规格型号：</label>
  	         <input type="text" name="equipmentModle" id="equipmentModle" readonly="readonly" value="${equipment.equipmentModle}" style="width: 30%; height: 25px; background: #FFF; font-size: 17px; color:#666; border: 1px solid #26A0DA;"/>
  	         <label style="width: 80px; font-size: 14px;">生产厂商：</label>
  	         <input type="text" name="equipmentProducer" id="equipmentProducer" readonly="readonly" value="${equipment.equipmentProducer}" style="width: 30%; height: 25px; background: #FFF; font-size: 17px; color:#666; border: 1px solid #26A0DA;"/>
  	     </div>
  	 </form>
  	 <input type="button" onclick="save();" value="确定" style="margin-left: 140px;">
  	 <input type="button" onclick="move_open();" value="添加文档">
  	 <input type="button" onclick="removeTag();" value="删除行">
  	 <div style="height:390px; margin-top:10px;">
	  	 <table id="browser">
	  	 	 <caption>文档清单</caption>
	  	 	 <thead>
			     <tr>
			         <th style="border-bottom-color: #000;">文档名称</th>
			         <th style="border-bottom-color: #000;">文档类型</th>
			         <th style="border-bottom-color: #000;">文档格式</th>
			     </tr>
			 </thead>
			 <tbody id="xhtml">
			 </tbody>
	  	 </table>
  	 </div>
  	 
  	 <div id="move" style="position: absolute;padding: 0px;border:0px solid #999;left:30px;top:100px;display:none;width: 560px;height:440px;background:url('${pageContext.request.contextPath}/images/pop-up4.png');">
  	     <div style="padding:32px 20px 0px 20px; height: 25px; cursor:move;" onmousedown="mdown(event);">
  	         <div style="float: left;">
  	             <div style="font-size: 12px; font-weight: bold; color: #FFF; height: 25px; display: block;">关联文档<div style="clear: both;"></div></div>
  	             <div style="clear: both;"></div>
  	         </div>
  	         <div style="width: 50px; float: right;">
  	             <a href="javascript:void(0)"  onclick="move_close();" style="float: right; color: #000; font-size: 14px">[关闭]</a>
  	             <div style="clear: both;"></div>
  	         </div>
  	     </div>
  	     <div style="margin: 10px 17px;">
  	         <input type="text" value="输入文档名称按回车键查询" id="datumName" onfocus="clearTxt();" onkeyup="searchs(event);" style="width:200px; height: 25px; margin-bottom: 10px;">
  	         <table id="datum" class="easyui-datagrid"></table>
  	     </div>
  	 </div>
  </body>
</html>