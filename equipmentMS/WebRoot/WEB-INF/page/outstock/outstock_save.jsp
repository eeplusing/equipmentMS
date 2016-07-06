<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>备品出库</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ajaxfileupload.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI-2.3.3.js"></script>
    <style type="text/css">
    .ButtonButton
	{
 		float:right; 
 		border-radius:4px; 
 		background-color: #F40; 
 		margin-top:14px; 
 		margin-bottom:5px; 
 		margin-left:10px; 
 		color:#fff;  
 		text-align: center;  
 		padding-top:0px; 
 		font-size:16px; 
 		font-weight:bold; 
 		cursor:pointer; 
 		height:30px; 
 		width:100px; 
 		border-radius:4px; 
 		border:none;
	}
	
    body
    { 
    	font-size: 20px;
    }
        
    .show
    {
    	width:95%;
		padding:6px 17px;
        
     }
        
    .show caption
	{
	    border:0px;
		font-weight:bold;
		padding:6px 0px 0px 23px;
		color:#000;	
		font-size:25px;
	}
		
	.show tbody label 
	{
		font-size:14px;
	}
        
	#xhtml td
	{	
		border:0px;
		background-color:#EEE;
		color:#123;
		font-size:20px;
		padding:5px 0px;
	}
	
    #browser
    {
		width:99.5%;
		border-collapse:collapse;
		font-family:arial;
		text-align:center;
		border:0px;
	}
		
	#browser thead tr
	{
	    height:30px;
	}
		
	#browser thead th 
	{
		border:0px;
		background-color:#589FD3;
		color:#FFF;	
		padding:10px 0px;
		font-size:20px;
	}
		
	#browser caption
	{
	    border:0px;
	    border-bottom:none;
		font-weight:bold;
		padding:6px 0px 0px 0px;
		color:#000;	
		font-size:25px;
	}
	
	#browser caption label
	{
		margin:6px -305px 0px 0px;
	}
    </style>
    
    <script type="text/javascript">
    	 var spart_info = "";
    	 var spart_inwo = "";
    	 function save(){
         	$.ajax({
				type : "post",
				url : "outstock_outStock.action",
				data : "spartInfos=" + spart_info ,
				success: function(msg){
					$.messager.alert('出库', msg);
				}
		  	});
        }
         
         function move_close(){
             $("#move").css("display", "none");
         }
         
         function move_open(){
             $("#move").css("display", "block");
         }
         
         function searchs(e){
             var keycode = e.keyCode || e.which || e.charCode;
             if(keycode == 13){
	         	$("#spartspart").css("display","block");
                selSpart();
             }
         }
         
         function selSpart(){
             $('#spart').datagrid({
	             width:1084,
	             height:437,
	             pageSize : 5,//默认选择的分页是每页5行数据
	             pageList : [5,10,50],//可以选择的分页集合
	             nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取
	             striped : true,//设置为true将交替显示行背景。
	             singleSelect: true, //单选模式
	             remoteSort: false,
	             fitColumns: false,
	             autoRowHeight: false,
	             url:'instock_list.action',
	             queryParams:{//初始查询参数
	                  sparepartNo : $("#spareNo").val(),
	                  instockTimeStart:$("#instockTimeStart").val(),
	                  instockTimeEnd:$("#instockTimeEnd").val(),
	                  requestType : 'out'
	             },
	             idField:'instockId',
                 loadMsg:'装载中...',
                 columns:[[
                 	 {field:'instockId',hidden:'true'},
	                 {field:'sparepartNo',title:'备品编号',width:135,align:'center'},
	                 {field:'sparepartName',title:'备品名称',width:133,align:'center'},
	                 {field:'sparepartModle',title:'规格型号',width:133,align:'center'},
	                 {field:'typeName',title:'备品类型',width:133,align:'center'},
	                 {field:'sparepartUnit',title:'计量单位',width:133,align:'center'},
	                 {field:'instockTime',title:'入库时间',width:133,align:'center'},
	                 {field:'remainAmount',title:'剩余数量',width:133,align:'center',
	                 		formatter:function(value, rowData, rowIndex){
	                 				if(spart_inwo.indexOf("/D" + rowData.instockId + ";") < 0 && spart_inwo.indexOf("D"+rowData.instockId +";") != 0)
	                 				{
	                 					spart_inwo += "D"+rowData.instockId+";"+value+";"+value+"/";
	                               	 	return '<input id="' +rowData.instockId+ '" style="border:0px;text-align:center;background-color:#EEE;" readonly="readonly" type="text"  value="' + value + '" />';
	                 				}
	                 				else
	                 				{
	                 					var sp = spart_inwo.split("/");
                     					for(var i = 0 ; i < sp.length ; i++)
                     					{
                     						var spa = sp[i].split(";");
                     						if(spa[0].substring(1) == rowData.instockId){
	                               	 			return '<input id="' +rowData.instockId+ '" style="border:0px;text-align:center;background-color:#EEE;" readonly="readonly" type="text"  value="' + spa[1] + '" />';
                     						}
                     					}	
	                 				}
                            }
                      },
	                 {field:'instockComment',title:'入库备注',width:135,align:'center'}
                 ]],
	             onDblClickCell:function(){ 
                     var selectedRow = $('#spart').datagrid('getSelections');
                     if(selectedRow.length > 0)
                     {
                     	var i = $("#outstockAmount").val();
                     	var remainnum = selectedRow[0].remainAmount - i ;
                     	if(spart_info.indexOf("/" + selectedRow[0].instockId + ";") < 0 && spart_info.indexOf(selectedRow[0].instockId + ";") != 0)
                     	{
                     		if($("#outstockSparepartReceiver").val().trim().length == 0 || $("#outstockSparepartReceiver").val() == "")
                     		{
                     			alert("请输入出库备品接收人.");
                     			return false;
                     		}
                     		if($("#outstockSparepartProvider").val().trim().length == 0 || $("#outstockSparepartProvider").val() == "")
                     		{
                     			alert("请输入出库备品发放人.");
                     			return false;
                     		}
                     		if($("#sparepartUseplace").val().trim().length == 0 || $("#sparepartUseplace").val() == "")
                     		{
                     			alert("请输入使用地点.");
                     			return false;
                     		}
                     		if($("#outStockComment").val().trim().length == 0 || $("#outStockComment").val() == "")
                     		{
                     			alert("出库备注.");
                     			return false;
                     		}
                     		if($("#outstockAmount").val()>0)
	                     	{
                     			if(remainnum >= 0)
                     			{
                     				var sp = spart_inwo.split("/");
                     				spart_inwo = "";
                     				for(var j = 0 ; j < sp.length ; j++)
                     				{
                     					if(sp[j].length >0)
	                     				{
    	                 					var spa = sp[j].split(";");
        	             					if(spa[0] == "D"+ selectedRow[0].instockId)
            	         					{
                	     						spart_inwo +=  spa[0] +";"+ remainnum +";"+spa[2]+"/";
                    	 					}
                     						else
                     						{	
                     							spart_inwo += spa[0] + ";" + spa[1]+";"+ spa[2]+"/";
	                     					}
                     					}
	                     			}
			                   		$("#" + selectedRow[0].instockId).attr('value',remainnum);
        	             			$("#xhtml").html($("#xhtml").html() + "<tr id='E" + selectedRow[0].instockId + "' onclick='selRow(\"" + selectedRow[0].instockId + ";" + selectedRow[0].sparepartNo +";"+ selectedRow[0].sparepartName +";" + selectedRow[0].sparepartModle + ";" + selectedRow[0].typeName +";" +selectedRow[0].sparepartUnit + ";" + selectedRow[0].instockTime + ";" + remainnum + ";" + selectedRow[0]. instockComment + ";" + $("#outstockSparepartReceiver").val()+ ";" + $("#outstockSparepartProvider").val() +";" + $("#sparepartUseplace").val() +";"+ $("#outstockAmount").val() +";"+ $("#outStockComment").val() + "\");'><td>" + selectedRow[0].sparepartNo + "</td><td>" + selectedRow[0].sparepartName + "</td><td>" + selectedRow[0].sparepartModle + "</td><td>" + selectedRow[0].typeName + "</td></tr>");
                	        		spart_info += selectedRow[0].instockId +";" +selectedRow[0].sparepartNo +";"+ selectedRow[0].sparepartName +";" + selectedRow[0].sparepartModle + ";" + selectedRow[0].typeName +";" +selectedRow[0].sparepartUnit + ";" + selectedRow[0].instockTime + ";" + remainnum + ";" + selectedRow[0]. instockComment + ";" + $("#outstockSparepartReceiver").val()+ ";" + $("#outstockSparepartProvider").val() +";" + $("#sparepartUseplace").val() +";"+ $("#outstockAmount").val() +";"+ $("#outStockComment").val() + "/";
            	            		move_close();
                   	 			}
                   				else
	              				{
    	         					alert("出库数量过多");
	                  			}
    	               		}
                     		else
           	         		{
               	     			alert("出库数量不能少于1");
                   	 		}
	                    }
    	                else
						{
            	         	alert("本次已进行出库操作，如需再次出库请下次再操作");
                	    }
	                 }
                 },
	             pagination : true
	         });
         }
         
		var oid = "";
         function selRow(eid){
         	var eids = eid.split(";");
         	
             if(oid != ""){
                 var otds = document.getElementById(oid).getElementsByTagName("td");
                 setColor(otds, "#EEE");
             }
             
             var ntds = document.getElementById("E" + eids[0]).getElementsByTagName("td");
             setColor(ntds, "#EAF8FF");
             oid = "E" + eids[0];
             $("#instockId").attr("value",eids[0]);
             $("#sparepartNo1").attr("value",eids[1]);
             $("#sparepartName1").attr("value",eids[2]);
             $("#sparepartModle1").attr("value",eids[3]);
             $("#typeName1").attr("value",eids[4]);
             $("#sparepartUnit1").attr("value",eids[5]);
             $("#instockTime1").attr("value",eids[6]);
             $("#remainAmount1").attr("value",eids[7]);
             $("#inStockComment1").attr("value",eids[8]);
             $("#outstockSparepartReceiver1").attr("value",eids[9]);
             $("#outstockSparepartProvider1").attr("value",eids[10]);
             $("#sparepartUseplace1").attr("value",eids[11]);
             $("#outstockAmount1").attr("value",eids[12]);
             $("#outStockComment1").attr("value",eids[13]);
         }
         
         function removeTag(){
             if(oid != "")
             {
                 var node = document.getElementById(oid);
                 node.parentNode.removeChild(node);
                 var ostr = oid.substring(1, oid.length);
                 
                 var spart_inwo1 = spart_inwo.split("/");
                 spart_inwo = "";
                 for(var i = 0 ; i<spart_inwo1.length ; i++)
                 {
                 	var spart_inwo2 = spart_inwo1[i].split(";");
                 	if(spart_inwo2[0] !=0)
                 	{
                 		if(spart_inwo2[0] == "D"+ostr)
                 		{
                 			spart_inwo += spart_inwo2[0] +";"+spart_inwo2[2]+";"+spart_inwo2[2] + "/";
                 			$("#"+ostr).attr('value' , spart_inwo2[2]);
                 		}
                 		else
                 		{
                 			spart_inwo += spart_inwo1[i] + "/";
                 		}
                 	}
                 }
                 
                 var spart = spart_info.split("/");
                 spart_info = "";
                 for(var i = 0 ; i<spart.length ; i++)
                 {
                 	var spart_ids = spart[i].split(";");
                 	if(spart_ids[0] != ostr && spart[i].length >0)
                 	{
                 		spart_info += spart[i] + "/";
                 	}
                 }
                 oid = "";
             }
             else
             {
                 alert("请先选中,再点击删除");
             }
         }
         
         function setColor(tds, colors){
             for(var i = 0; i < tds.length; i++)
             {
                 tds[i].style.backgroundColor=colors;
             }
         }
		
    </script>
  </head>
  <body>
      <object id="PSKPrn" classid="clsid:81C07687-3353-4ABA-B108-94BCE81E5CBA" codebase="PSKPrn.ocx#version=1,0,0,1" width="0" height="0"></object>
      <form id="form1" name="form1">
	  	<div style="height:0px; margin-bottom:12px;">
	  		<img style=" width:1130px; heidht:40px;" src="${pageContext.request.contextPath}/images/kongbai.png">
      	</div>
      	<label style="padding:6px 0px 0px 10px; font-weight:bold; color:#FFF; font-size:16px;">备品出库信息</label>
	      <table style="margin-top:20px;" class="show">
	          <tbody>
	            <tr>
	            	<input type="hidden" readonly="readonly"  id="instockId" name="instockId"/>
	                <td style="text-align: right;"><label for="sparepartNo1">备品编号:</label></td>
	                <td><input class="easyui-validatebox" style=" width:130px; border:0px; font-size:14px;" type="text" readonly="readonly"  id="sparepartNo1" name="sparepartNo1" value=""/></td>
	                <td style="text-align: right;"><label  for="sparepartName1">备品名称:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" type="text" readonly="readonly" id="sparepartName1" name="sparepartName1" value=""/></td>
	                <td style="text-align: right;"><label for="sparepartModle1">规格型号:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="sparepartModle1" name="sparepartModle1" value=""/></td>
	                <td style="text-align: right;"><label for="typeName1">备品类型:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="typeName1" name="typeName1" value=""/></td>
	                <td style="text-align: right;"><label for="sparepartUnit1">计量单位:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="sparepartUnit1" name="sparepartUnit1" value=""/></td>
	             </tr>
	             <tr>
	             	<td style="text-align: right;"><label for="instockTime1">入库时间:</label></td>
	                <td><input class="easyui-validatebox" style="font-size:14px; border:0px; width:130px;" readonly="readonly" type="text" id="instockTime1" name="instockTime1" value=""/></td>
	                <td style="text-align: right;"><label for="remainAmount1">剩余数量:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="remainAmount1" name="remainAmount1" value=""/></td>
	                <td style="text-align: right;"><label for="inStockComment1">入库备注:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="inStockComment1" name="inStockComment1" value=""/></td>
	                <td style="text-align: right;"><label for="outstockSparepartReceiver1">出库备品接收人:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="outstockSparepartReceiver1" name="outstockSparepartReceiver1" value=""/></td>
	                <td style="text-align: right;"><label for="outstockSparepartProvider1">出库备品发放人:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="outstockSparepartProvider1" name="outstockSparepartProvider1" value=""/></td>
	             </tr>
	             <tr>
	             	<td style="text-align: right;"><label for="sparepartUseplace1">使用地点:</label></td>
	                <td><input class="easyui-validatebox" style="font-size:14px; border:0px; width:130px;" readonly="readonly" type="text" id="sparepartUseplace1" name="sparepartUseplace1" value=""/></td>
	                <td style="text-align: right;"><label for="outstockAmount1">出库数量:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="outstockAmount1" name="outstockAmount1" value=""/></td>
	                <td style="text-align: right;"><label for="outStockComment1">出库备注:</label></td>
	                <td><input class="easyui-validatebox" style="width:120px; font-size:14px; border:0px;" readonly="readonly" type="text" id="outStockComment1" name="outStockComment1" value=""/></td>
	             </tr>
	          </tbody>
	      	</table>
	      </form>
	      <br/>
	      <br/>
	      <br/>
	      <div style="height:495px; margin-top:-70px;">
	  	  	<table id="browser">
	  	 		<div style="height:0px; margin-bottom:12px;">
	  				<img style=" width:1130px; heidht:40px;" src="${pageContext.request.contextPath}/images/kongbai1.png">
      			</div>
      			<label style="padding:6px 0px 0px 10px; font-weight:bold; color:#FFF; font-size:16px;">出库备品清单</label>
	  	 		<caption>
	  	 			<input type="button" onclick="save();" class="ButtonButton" value="确定"/>
  	 	 			<input type="button" onclick="move_open();" class="ButtonButton" value="添加备品"/>
  	 	 			<input type="button" onclick="removeTag();" class="ButtonButton" value="删除行"/>
	  	 		</caption>	
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
  	 
		 <div id="move" style="position:absolute; width:1100px; height:595px; border:1px solid #000; top:100px; background:#FFF; display:none;">
  	     	<div style="width:100%; margin-left:-11px; border:0px solid #000; height: 0px; background:#9CF;">
  	      	  <img style="width:1102px; margin-left:9px; height: 595px" src="${pageContext.request.contextPath}/images/pop-up.png"/>
  	      	</div>
  	     	<div style=" position: absolute; z-index:9;">
  	        	<a href="javascript:void(0)"  onclick="move_close();" >
					<img style="margin-left:1043px; margin-top:18px; width:40px; height:40px;" src="${pageContext.request.contextPath}/images/closebutton.png">
				</a>
			</div>
  	     	<div style="height:0px; margin-left:25px; margin-bottom:10px; margin-top:38px;">
	  			<img style=" width:1050px; heidht:10px;" src="${pageContext.request.contextPath}/images/kongbai1.png">
      		</div>
  	        <label style="font-size: 16px; margin-left:50px;  font-weight:bold; color:#FFF;">备品出库</label>
  	      	<table style="margin-top:10px; margin-left:30px;">
  	      		<tr>
	                 <td style="text-align: right;"><label for="outstockAmount" style="font-size:14px;">出库数量:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="outstockAmount" name="outstockAmount" value=""/>&nbsp;&nbsp;&nbsp;</td>
	                 <td style="text-align: right;">&nbsp;&nbsp;&nbsp;<label for="outstockSparepartReceiver" style="font-size:14px;">出库备品接收人:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="outstockSparepartReceiver" name="outstockSparepartReceiver" value=""/>&nbsp;&nbsp;&nbsp;</td>
	                 <td style="text-align: right;"><label for="outstockSparepartProvider" style="font-size:14px;">出库备品发放人:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="outstockSparepartProvider" name="outstockSparepartProvider" value=""/>&nbsp;&nbsp;&nbsp;</td>
	        	</tr>
	        	<tr>
	                 <td style="text-align: right;"><label for="sparepartUseplace" style="font-size:14px;">使用地点:</label></td>
	                 <td colspan="3"><input class="easyui-validatebox" type="text"  style="width:430px;" id="sparepartUseplace" name="sparepartUseplace" value=""/>&nbsp;&nbsp;&nbsp;</td>
	                 <td style="text-align: right;"><label for="outStockComment" style="font-size:14px;">出库备注:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="outStockComment" name="outStockComment" value=""/></td>
	        	</tr>
	        </table>
			<div style="margin-left:90px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	      		<input type="text"  value="输入备品编号按回车键查询或使用扫描枪扫描二维码" id="spareNo" onkeyup="searchs(event);" style="font-size:14px; width:400px;">&nbsp;&nbsp;&nbsp;
  	 	  		&nbsp;&nbsp;&nbsp;
  	 	  		<label style="font-size:14px;"> 入库时间段</label>
  	 	  		<input class="easyui-validatebox" type="text" id="instockTimeStart" name="instockTimeStart" maxlength="10" value="" onfocus="$(this).calendar()"/>~~~~
				<input class="easyui-validatebox" type="text" id="instockTimeEnd" maxlength="10" name="instockTimeEnd" value="" onfocus="$(this).calendar()"/>
			</div>
				<div  id="spartspart" style="display:none; margin-left:7px; border:0px;"> 
  	      			<table id="spart"  class="easyui-datagrid"></table>
  	      		</div>
  	  </div>     
	  <div id="loading"></div>
  </body>
</html>
