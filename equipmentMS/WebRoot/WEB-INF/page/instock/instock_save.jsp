<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>备品入库</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.jqprint-0.3.js"></script>
    <style type="text/css">
        body{ font-size: 14px;}
        
        table{
        	font-size: 14px;
        	margin: 57px 0px 0px 150px;
        	border-collapse:collapse; 
    	}
    	tbody{
      		border: 1px solid #8DB2E3;
    	}
    	tbody tr{
        	height: 30px;
        	border-bottom: 1px solid #8DB2E3;
    	}
    	tbody tr td{
        	border-right: 1px solid #8DB2E3;
   	 	}
    	td.field-name{
        	text-align: right;
        	background: #EEEEFF;
    	}
    	td.field-value input{
        	height: 30px;
        	width: 150px;
        	border: none;
    	}
    	td.field-value select{
        	height: 30px;
        	width: 150px;	
        	border: none;
    	}
    	td.field-value-remark input{
        	height: 30px;
	        width: 373px;
    	    border: none;
    	}
    	#save-equ{
       		width: 60px;
       		height:30px;
       		background:#1ABFF5;
       		margin-left: 234px;
       		color: #FFF;
       		font-weight: bolder;
       		border:1px solid #1ABFF5;
    	}
    	div.button-area{
       		height: 20px;
       		color: #15428B;
       		border-radius: 2px;
       		margin-top: 30px;
       		margin-left:100px;
       		cursor: pointer; 
    	}
    	#create-doc{
       		width: 100px;
       		height:30px;
       		background:#1ABFF5;
       		margin-left: 5px;	
       		color: #FFF;
       		font-weight: bolder;
       		border:1px solid #1ABFF5;
    	}
    	#print-doc{
       		width: 60px;
       		height:30px;
       		background:#1ABFF5;
       		margin-left: 5px;
       		color: #FFF;
       		font-weight: bolder;
       		border:1px solid #1ABFF5;
    	}
    	#doc_img{
       		width: 150px;
       		height: 150px;
       		margin: 2px auto;
    	}
    	#doc_img img{
       		width: 150px;
       		height: 150px;
    	}
    </style>
    
    <script type="text/javascript">
       var equ_no = "";
        function createDoc(){
             $.ajax({
			     url:"instock_createDoc.action",
				 type:"post", 
				 cache:false,
				 data:"sparepartNo=" + $("#sparepartNo").val(),
				 beforeSend:function(XMLHttpRequest){ 
                     $("#loading").html("<img src='${pageContext.request.contextPath}/images/loading.gif'/>"); 
                 }, 
				 success:function(msg){
				     $("#loading").empty();
				     var ms = msg.split(";");
					 $("#doc_img").html("<img src='equ_showImage.action?imageName=" + ms[0] + "'/>" );
					 equ_no = ms[1];
				 },
				 complete:function(XMLHttpRequest,textStatus){
				     $("#loading").empty();
				 }
			 });
         }
         
    	 
         function save(){
             var sparepartNo = $("#sparepartNo").val().trim().replace(/(^\s*)|(\s*$)/g, "");
             var sparepartName = $("#sparepartName").val().trim().replace(/(^\s*)|(\s*$)/g, "");
             var sparepartModle = $("#sparepartModle").val().trim().replace(/(^\s*)|(\s*$)/g, "");
             var instockAmount = $("#instockAmount").val().trim().replace(/(^\s*)|(\s*$)/g, "");
             if(sparepartNo == null || sparepartNo == "" || sparepartNo.length == 0)
             {
                 $.messager.alert('提示', "备品编号不能为空");
                 return false;
             }
             if(sparepartName == null || sparepartName == "" || sparepartName.length == 0)
             {
                 $.messager.alert('提示', "备品名称不能为空");
                 return false;
             }
             if(sparepartModle == null || sparepartModle == "" || sparepartModle.length == 0)
             {
                 $.messager.alert('提示', "规格型号不能为空");
                 return false;
             }
             if(instockAmount == null || instockAmount <= 0 || instockAmount.length == 0)
             {
                 $.messager.alert('提示', "入库数量不能少于1");
                 return false;
             }
             if($("#sparepartUnit").val().trim() == "" || $("#sparepartUnit").val().trim().length == 0)
             {
                 $.messager.alert('提示', "计量单位不能为空");
                 return false;
             }
             if($("#sparepartSetplace").val().trim() == "" || $("#sparepartSetplace").val().trim().length == 0)
             {
                 $.messager.alert('提示', "安放地点不能为空");
                 return false;
             }
             if($("#instockSparepartReceiver").val().trim() == "" || $("#instockSparepartReceiver").val().trim().length == 0)
             {
                 $.messager.alert('提示', "入库备品接收人不能为空");
                 return false;
             }
             if($("#instockSparepartProvider").val().trim() == "" || $("#instockSparepartProvider").val().trim().length == 0)
             {
                 $.messager.alert('提示', "入库备品发放人不能为空");
                 return false;
             }
             if($("#instockSparepartSupervisor").val().trim() == "" || $("#instockSparepartSupervisor").val().trim().length == 0)
             {
                 $.messager.alert('提示', "入库备品监督人不能为空");
                 return false;
             }
             if($("#instockComment").val().trim() == "" || $("#instockComment").val().trim().length == 0)
             {
                 $.messager.alert('提示', "入库备注不能为空");
                 return false;
             }
         	 $.ajax({
			     url:"instock_instock.action",
				 type:"post", 
				 cache:false,
				 data:params(),
				 beforeSend:function(XMLHttpRequest){ 
                 }, 
				 success:function(msg){
				     $.messager.alert('备品入库', msg);
				     close_fg();
				 },
				 complete:function(XMLHttpRequest,textStatus){
				 }
			 });
         }
         
         function print(){
             var sparepartNo = $("#sparepartNo").val().replace(/(^\s*)|(\s*$)/g, "");
             if(sparepartNo == null || sparepartNo == "" || sparepartNo.length == 0){
                 $.messager.alert('提示', "备品编号为空,不能生成二维码");
                 return false;
             }else{
        	     PSKPrn.OpenPort("POSTEK C168/200s");
	             PSKPrn.PTKClearBuffer();
	             PSKPrn.PTKSetPrintSpeed(4);
	             PSKPrn.PTKSetDarkness(10);
	             PSKPrn.PTKSetLabelHeight(300, 24);
	             PSKPrn.PTKSetLabelWidth(824);

                 //二维码
	             PSKPrn.PTKDrawBar2DQR(300, 20, 90, 90, 0, 5, 2, 0, 0, sparepartNo);
	             
	             PSKPrn.PTKDrawTextTrueTypeW(300, 120, 36, 0, "微软雅黑", 1, 500, false, false, false, "B5", "NO:" + sparepartNo);
	             PSKPrn.PTKPrintLabel(1, 1);
	             PSKPrn.ClosePort();
             }
         }
         
         function params(){
              var query_params = "sparepartNo=" + $("#sparepartNo").val();
                  query_params += "&sparepartName=" + $("#sparepartName").val();
                  query_params += "&sparepartModle=" + $("#sparepartModle").val();
                  query_params += "&typeId=" + $("#typeId").val();
                  query_params += "&sparepartUnit=" + $("#sparepartUnit").val();
                  query_params += "&sparepartSetplace=" + $("#sparepartSetplace").val();
                  query_params += "&instockSparepartReceiver=" + $("#instockSparepartReceiver").val();
                  query_params += "&instockSparepartProvider=" + $("#instockSparepartProvider").val();
                  query_params += "&instockSparepartSupervisor=" + $("#instockSparepartSupervisor").val();
                  query_params += "&instockAmount=" + $("#instockAmount").val();
                  query_params += "&instockComment=" + $("#instockComment").val();
              return query_params;
         }
    </script>
  </head>
  <body>
  	  <div id="fullbg"></div>
      <object id="PSKPrn" classid="clsid:81C07687-3353-4ABA-B108-94BCE81E5CBA" codebase="PSKPrn.ocx#version=1,0,0,1" width="0" height="0"></object>
      <form id="form1" name="form1">
      <table>
	        <tbody>
	            <tr>
	                <td colspan="2" rowspan="5">
	                    <div id="doc_img"></div>
	                </td>
	                <td class="field-name"><label>备品编号:</label></td>
	                <td class="field-value"><input type="text" id="sparepartNo"  value=""/></td>
	                <td class="field-name"><label>备品名称:</label></td>
	                <td class="field-value"><input type="text" id="sparepartName"  value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>规格型号:</label></td>
	                <td class="field-value"><input type="text" id="sparepartModle"  value=""/></td>
	                <td class="field-name"><label>计量单位:</label></td>
	                <td class="field-value"><input type="text" id="sparepartUnit" value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>安放地点:</label></td>
	                <td class="field-value"><input type="text" id="sparepartSetplace" value=""/></td>
	                <td class="field-name"><label>入库备注:</label></td>
	                <td class="field-value"><input type="text" id="instockComment"  value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>入库数量:</label></td>
	                <td class="field-value"><input type="text" id="instockAmount" value=""/></td>
	                <td class="field-name"><label>入库备品接收人:</label></td>
	                <td class="field-value"><input type="text" id="instockSparepartReceiver"  value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>入库备品发放人:</label></td>
	                <td class="field-value"><input type="text" id="instockSparepartProvider"  value=""/></td>
	                <td class="field-name"><label>入库备品监督人:</label></td>
	                <td class="field-value"><input type="text" id="instockSparepartSupervisor"  value=""/></td>
	            </tr>
	            <tr>
	                <td style="text-align: right;"><label for="typeId">备品类型:</label></td>
	                 <td><select name="equipment.typeId" id="typeId">
			    		     <c:forEach items="${types}" var="type">
							     <option value="${type.typeId}">${type.typeName}</option>
						     </c:forEach>
			    		 </select>
		    		</td>
	            </tr>
	            <tr>
	            </tr>
	            <tr>
	            </tr>
	        </tbody>
	    </table>
	     <div class="button-area">
	          <input type="button" onclick="save();" id="save-equ" value="确 定">
	          <input type="button" onclick="createDoc();" id="create-doc" value="生成二维码">
	          <input type="button" value="打 印" onclick="print();" id="print-doc"/>
	     </div>
      </form>
  </body>
</html>
