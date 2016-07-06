<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    
    <title>备品入库</title>
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
	</style>
    
    <script type="text/javascript">
       var modle="no-scanning";
        var spart_info = "";
        function save(){
            var iodepotGetter = $("#iodepotGetter").val().replace(/(^\s*)|(\s*$)/g, "");
            if(iodepotGetter == null || iodepotGetter == "" || iodepotGetter.length == 0){
                 $.messager.alert('提示', "出库人不能为空");
                 return false;
            }
            $.ajax({
				type : "post",
				url : "spart_outStock.action",
				data : "spartIds=" + spart_info + "&iodepotGetter=" + $("#iodepotGetter").val() + "&iodepotOperator=" + $("#iodepotOperator").val(),
				success: function(msg){
					$.messager.alert('出库', msg);
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
         
         function selSpart(){
             $('#spart').datagrid({
	             width:500,
	             height:308,
	             pageSize : 10,//默认选择的分页是每页5行数据
	             pageList : [10],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'spart_list.action',
	             queryParams:{//初始查询参数
	                  sparepartNo : '',
	                       typeId : $("#typeId").val(),
	                  requestType : 'out',
	                  equipmentId : '0'
	             },
	             idField:'sparepartId',
                 loadMsg:'装载中...',
                 columns:[[
                 	 {field:'sparepartId',hidden:'true'},
	                 {field:'sparepartNo',title:'备品编号',width:124,align:'center', sortable:true},
	                 {field:'sparepartName',title:'备品名称',width:124,align:'center', sortable:true},
	                 {field:'sparepartModle',title:'规格型号',width:123,align:'center', sortable:true},
	                 {field:'typeName',title:'备品类型',width:123,align:'center', sortable:true}
                 ]],
	             onDblClickCell:function(){ 
                     var selectedRow = $('#spart').datagrid('getSelections');
                     if(selectedRow.length > 0){
                         if(spart_info.indexOf(";" + selectedRow[0].sparepartId + ";") < 0 && spart_info.indexOf(selectedRow[0].sparepartId + ";") != 0){
                         	 $("#xhtml").html($("#xhtml").html() + "<tr id='E" + selectedRow[0].sparepartId + "' onclick='selRow(\"" + selectedRow[0].sparepartId + "\");'><td>" + selectedRow[0].sparepartNo + "</td><td>" + selectedRow[0].sparepartName + "</td><td>" + selectedRow[0].sparepartModle + "</td><td>" + selectedRow[0].typeName + "</td></tr>")
                             spart_info += selectedRow[0].sparepartId + ";";
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
                 selSpart();
             }
         }
         
         function clearTxt(){
             $("#sparepartNo").val("");
             selSpart();
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
                 var spart_ids = spart_info.split(";");
                 spart_info = "";
                 for(var i = 0; i < spart_ids.length - 1; i++){
                     if(spart_ids[i] != ostr){
                         spart_info += spart_ids[i] + ";";
                     }
                 }
                 oid = "";
             }else{
                 alert("请先选中,再点击删除");
             }
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
         
         function outstock(e){
           var keycode = e.keyCode || e.which || e.charCode;
           if(keycode == 13){
	           if(modle == "scanning"){
	               var sparepartNo = $("#scan-box").val().replace(/(^\s*)|(\s*$)/g, "");
	               if(sparepartNo != null && sparepartNo != "" && sparepartNo.length > 0){
		               $.ajax({
						   type : "post",
						   url : "spart_scanning.action",
						   data : "modle=out" + "&typeId=" + $("#typeId").val() + "&sparepartNo=" + $("#scan-box").val(),
						   success: function(msg){
							   if(msg == "no-spart"){
							       $("#scan-box").val("");
							       $.messager.alert('提示', "无此备品编号或无库存");
							   }else{
							       $("#scan-box").val("");
						           var ms = msg.split(";");
						           if(spart_info.indexOf(";" + ms[0] + ";") < 0 && spart_info.indexOf(ms[0] + ";") != 0){
		                         	   $("#xhtml").html($("#xhtml").html() + "<tr id='E" + ms[0] + "' onclick='selRow(\"" + ms[0] + "\");'><td>" + ms[1] + "</td><td>" + ms[2] + "</td><td>" + ms[3] + "</td><td>" + ms[4] + "</td></tr>");
		                               spart_info += ms[0] + ";";
		                               move_close();
		                           }
						       }
						   }
					   });
				   }
		       }
           } 
        }
    </script>
  </head>
  
  <body>
     <div id="loading"></div>
  	 <form method="post" style="padding: 10px; ">
  	     <div style="margin-bottom: 10px;">
  	         <input type="hidden" name="typeId" id="typeId" value="${sparepart.typeId}"/>
  	         <label style="width: 80px; font-size: 16px;">出库人：</label>
  	         <input type="text" name="iodepotGetter" id="iodepotGetter" style="width: 30%; height: 25px; background: #FFF; font-size: 17px;"/>
  	         <label style="width: 80px; font-size: 16px;">操作员：</label>
  	         <input type="text" name="iodepotOperator" id="iodepotOperator" readonly="readonly" value="${iodepotOperator}" style="width: 30%; height: 25px; background: #FFF; font-size: 17px;"/>
  	     </div>
  	 </form>
  	 <input type="button" onclick="save();" value="确定" style="height:25px; margin-left: 220px;">
  	 <input type="button" onclick="move_open();" value="添加备品" style="height:25px;">
  	 <input type="button" onclick="see();" id="open-scan" value="启用扫描" style="height:25px;">
  	 <input type="button" onclick="removeTag();" value="删除行" style="height:25px;">
  	 <input type="text" id="scan-box" onkeyup="outstock(event);" style="height:25px;">
  	 <div style="height:390px; margin-top:10px;">
	  	 <table id="browser">
	  	 	 <caption>出库备品清单</caption>
	  	 	 <thead>
			     <tr>
			         <th style="border-bottom-color: #000;">备品编号</th>
			         <th style="border-bottom-color: #000;">备品名称</th>
			         <th style="border-bottom-color: #000;">规格型号</th>
			         <th style="border-bottom-color: #000;">备品类型</th>
			     </tr>
			 </thead>
			 <tbody id="xhtml">
			 </tbody>
	  	 </table>
  	 </div>
  	 <div id="move" style="position:absolute; width:500px; height:350px; padding:0px; border:1px solid #999; left:50px; top:100px; background:#FFF; display:none;">
  	      <div style="width:100%; height: 35px; padding:0px; background:#9CF; cursor:move;" onmousedown="mdown(event);">
  	          <label style="font-size: 22px; margin-top:0px; display: inline;">备品出库</label>
  	          <span style="margin-left: 370px;"></span>
  	          <a href="javascript:void(0)"  onclick="move_close();" style="color: #000; font-size: 14px">[关闭]</a>
  	      </div>
  	      <input type="text" value="输入备品编号按回车键查询" id="sparepartNo" onkeyup="searchs(event);" style="width:100%">
  	      <table id="spart" class="easyui-datagrid"></table>
  	  </div>
  </body>
</html>