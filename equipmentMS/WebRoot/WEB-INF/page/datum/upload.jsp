<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    
    <title>My JSP 'upload.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ajaxfileupload.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/equms/colinUI.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI-2.3.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/equms/colin-ui.js"></script>
    <style>
		#browser{
			width:650px;
			margin:0px 17px;
			border-collapse:collapse;
			font-family:arial;
			text-align:center;
			border:1px solid #bbb;
		}
		#browser thead tr{
		    height:25px; 
		 
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
			width: 160px;
		}
		div.caption{
		    border:0px solid #bbb;
		    border-bottom:none;
			font-weight:bold;
			padding:6px 0px;
			color:#000;	
			font-size:25px;
		}
	   input.con_firm {
	        border:1px solid #0A93D4; 
	        width: 110px; 
	        height:40px;
	        margin-left:150px;
	        padding-left: 15px;
	        color:#FFF;
	        font-weight:bolder;
	        background: url('${pageContext.request.contextPath}/images/abutton.png');
       }
       input.choice_device{
            border:1px solid #0A93D4; 
	        width: 110px; 
	        height:40px;
	        margin-left:30px;
	        padding-left: 15px;
	        color:#FFF;
	        font-weight:bolder;
	        background: url('${pageContext.request.contextPath}/images/abutton.png');
       }
       input.sw_eep{
            border:1px solid #0A93D4; 
	        width: 110px; 
	        height:40px;
	        margin-left:150px;
	        margin-top:10px;
	        padding-left: 15px;
	        color:#FFF;
	        font-weight:bolder;
	        background: url('${pageContext.request.contextPath}/images/abutton.png');
       }
       input.del_ete{
            border:1px solid #0A93D4; 
	        width: 110px; 
	        height:40px;
	        margin-left:30px;
	        padding-left: 15px;
	        color:#FFF;
	        font-weight:bolder;
	        background: url('${pageContext.request.contextPath}/images/abutton.png');
       }
      
	</style>
    
    <script type="text/javascript">
       var equ_info = "";
        var modle="no-scanning";
        function save(){
            jQuery.blockUI.defaults.fadeOut=500;
            $("#loading").ajaxStart(function(){
                $.blockUI({message:"<img src='${pageContext.request.contextPath}/images/loading.gif'/>文件上传中..."});
            });
            $.ajaxFileUpload({
                url:'datum_load.action',
                secureuri: false,
                fileElementId: 'datumFile',
                dataType: 'text',
                success: function (data, status){
                    if(data == "error"){
                        $.messager.alert('上传', '文档上传失败');
                    }else if(data == "ishave"){
                        $.messager.alert('上传', '文档已经存在不能上传');
                    }else if(data == "empty"){
                        $.messager.alert('上传', '文档为空或文档大小为0不可上传');
                    }else{//文件上传成功,返回文件保存位置
                        $.ajax({
						    type : "post",
						    url : "datum_save.action",
						    data : "datumTypeId=" + $("#datumTypeId").val() + "&datumFormatId=" + $("#datumFormatId").val() + "&datumPath=" + data + "&datumName=" + $("#datumName").val() + "&equIds=" + equ_info,
						    success: function(msg){
						        $.messager.alert('上传', msg);
						    }
					    });
                    }
                }
            });
 
            $("#loading").ajaxComplete(function(){
                $.unblockUI();
            });
        }
        
        function see(){
            if(modle == "scanning"){
               modle = "no-scanning";
               $("#open-scan").val("启用扫描");
            }else{
               modle = "scanning";
               $("#open-scan").val("取消扫描");
            }
            $("#scan-box").focus();
        }
        
        function add_equ(e){
           var keycode = e.keyCode || e.which || e.charCode;
           if(keycode == 13){
               if(modle == "scanning"){
                   var equipmentNo = $("#s_equipmentNo").val().replace(/(^\s*)|(\s*$)/g, "");
	               if(equipmentNo != null && equipmentNo != "" && equipmentNo.length > 0){
	                   $.ajax({
						   type : "post",
						   url : "equ_scanning.action",
						   data : "modle=datum" + "&equipmentNo=" + $("#s_equipmentNo").val(),
						   success: function(msg){
							   if(msg == "no-equ"){
							       $("#s_equipmentNo").val("");
							       $.messager.alert('提示', "无此设备编号");
							   }else{
							       $("#s_equipmentNo").val("");
						           var ms = msg.split(";");
						           if(equ_info.indexOf(";" + ms[0] + ";") < 0 && equ_info.indexOf(ms[0] + ";") != 0){
		                         	   $("#xhtml").html($("#xhtml").html() + "<tr id='E" + ms[0] + "' onclick='selRow(\"" + ms[0] + "\");'><td>" + ms[1] + "</td><td>" + ms[2] + "</td><td>" + ms[3] + "</td><td>" + ms[4] + "</td></tr>");
		                               equ_info += ms[0] + ";";
		                               move_close();
		                           }
						       }
						   }
					   });
	               }
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
         
        function selEqu(){
             $('#equ').datagrid({
	             width:530,
	             pageSize : 10,//默认选择的分页是每页5行数据
	             pageList : [10],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'equ_list.action',
	             queryParams:{//初始查询参数
	                  typeId : '0',
	                  departId : '0',
	                  equipmentIdentId : '0',
	                  useStateId : '0',
	                  equipmentNo : $("#equipmentNo").val()
	             },
	             idField:'equipmentId',
                 loadMsg:'装载中...',
                 columns:[[
                 	 {field:'equipmentId',hidden:'true'},
	                 {field:'equipmentNo',title:'设备编号',width:124,align:'center', sortable:true},
	                 {field:'equipmentName',title:'设备名称',width:124,align:'center', sortable:true},
	                 {field:'equipmentModle',title:'规格型号',width:123,align:'center', sortable:true},
	                 {field:'typeName',title:'设备类型',width:123,align:'center', sortable:true}
                 ]],
	             onDblClickCell:function(){ 
                     var selectedRow = $('#equ').datagrid('getSelections');
                     if(selectedRow.length > 0){
                         if(equ_info.indexOf(";" + selectedRow[0].equipmentId + ";") < 0 && equ_info.indexOf(selectedRow[0].equipmentId + ";") != 0){
                         	 $("#xhtml").html($("#xhtml").html() + "<tr id='E" + selectedRow[0].equipmentId + "' onclick='selRow(\"" + selectedRow[0].equipmentId + "\");'><td>" + selectedRow[0].equipmentNo + "</td><td>" + selectedRow[0].equipmentName + "</td><td>" + selectedRow[0].equipmentModle + "</td><td>" + selectedRow[0].typeName + "</td></tr>");
                             equ_info += selectedRow[0].equipmentId + ";";
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
                selEqu();
            }
        }
         
        function clearTxt(){
            $("#equipmentNo").val("");
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
                var equ_ids = equ_info.split(";");
                equ_info = "";
                for(var i = 0; i < equ_ids.length - 1; i++){
                    if(equ_ids[i] != ostr){
                        equ_info += equ_ids[i] + ";";
                    }
                }
                oid = "";
            }else{
                alert("请先选中,再点击删除");
            }
        }
    </script>
  </head>
  
  <body style="overflow: hidden;">
     <div id="loading"></div>
  	 <form method="post" enctype="multipart/form-data" style="padding: 10px; ">
  	     <div class="caption">关联设备</div>
  	     <div style="margin-bottom: 10px; margin-top: 10px;">
  	         <label style="width: 80px; font-size: 14px;">选择文件（文件大小不能超过2M）：</label>
  	         <input type="file" class="file-colinUI" name="datumFile" id="datumFile"/>
  	     </div>
  	     <div style="margin-bottom: 10px; margin-left: 156px;">
	  	     <label style="width: 80px; font-size: 14px;">文档类型：</label>        
	  	     <select name="datumTypeId" id="datumTypeId" style="width: 35%; height: 25px; border: 1px solid #0A93D4;">
				 <c:forEach items="${types}" var="type">
					 <option value="${type.datumTypeId}">
						 ${type.datumTypeName}
					 </option>
				 </c:forEach>
			 </select>
		  </div>
		   <div style="margin-bottom: 10px; margin-left: 156px;">
		     <label style="width: 80px; font-size: 14px;">文档格式：</label> 
			 <select name="datumFormatId" id="datumFormatId" style="width: 35%; height: 25px; border: 1px solid #0A93D4;">
				 <c:forEach items="${formats}" var="format">
					 <option value="${format.datumFormatId}">
						 ${format.datumFormatName}
					 </option>
				 </c:forEach>
			 </select>
		 </div>
  	     <div style="margin-bottom: 10px; margin-left: 156px;">
	         <label style="width: 80px; font-size: 14px;">文档名称：</label>
		     <input id="datumName" name="datumName" type="text" style="width: 35%; height: 25px; font-size: 17px; border: 1px solid #0A93D4;">
		 </div>
  	 </form>
  	
  	    <input type="button" onclick="save();" value="  确 定" class="con_firm">
  	    <input type="button" onclick="move_open();" value="   选择设备" class=" choice_device">
  	    <input type="button" onclick="removeTag();" value=" 删 除 行" class="del_ete"><br/>
  	    <input type="button" onclick="see();" id="open-scan" value="   启用扫描"  class=" sw_eep">&nbsp;&nbsp;&nbsp;&nbsp;
  	    <input type="text" id="s_equipmentNo" onkeyup="add_equ(event);" style="width: 200px; height:30px;">
  	
  	 <div style="height:390px; margin-top:30px;">
	  	 <table id="browser">
	  	 	 <thead>
			     <tr>
			         <th style="border-bottom-color: #000;">设备编号</th>
			         <th style="border-bottom-color: #000;">设备名称</th>
			         <th style="border-bottom-color: #000;">规格型号</th>
			         <th style="border-bottom-color: #000;">设备类型</th>
			     </tr>
			 </thead>
			 <tbody id="xhtml">
			 </tbody>
	  	 </table>
  	 </div>
  	 <div id="move" style="position: absolute;padding: 0px;border:0px solid #999;left:65px;top:100px;display:none;width: 560px;height:440px;background:url('${pageContext.request.contextPath}/images/pop-up4.png');">
  	     <div style="cursor:move; margin-top: 30px;" onmousedown="mdown(event);">
  	         <span style="margin-left: 20px; color: #FFF; font-weight: bolder;">关联设备</span>
  	         <span style="margin-left:435px; border: 0px solid #000;"></span>
  	         <a href="javascript:void(0)"  onclick="move_close();" style="color: #000; font-size: 14px">[关闭]</a>
  	     </div>
  	     <div style="margin-top:10px; margin-left: 15px">
  	         <input type="text" value="输入设备编号按回车键查询" id="equipmentNo" onkeyup="searchs(event);" style="width:200px; height: 25px;">
  	    </div>
  	    <div style="margin:5px 15px;">
  	        <table id="equ" class="easyui-datagrid"></table>
  	    </div>
  	  </div>
  </body>
</html>
