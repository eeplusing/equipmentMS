<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新增备品</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.jqprint-0.3.js"></script>
    <style type="text/css">
        body{ font-size: 16px;}
    </style>
    
    <script type="text/javascript">
       var equ_no = "";
        function createDoc(){
             $.ajax({
			     url:"spart_createDoc.action",
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
             var sparepartNo = $("#sparepartNo").val().replace(/(^\s*)|(\s*$)/g, "");
             var sparepartName = $("#sparepartName").val().replace(/(^\s*)|(\s*$)/g, "");
             var sparepartUnit = $("#sparepartUnit").val().replace(/(^\s*)|(\s*$)/g, "");
             var sparepartPrice = $("#sparepartPrice").val().replace(/(^\s*)|(\s*$)/g, "");
             var money_reg=/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
             if(sparepartNo == null || sparepartNo == "" || sparepartNo.length == 0){
                 $.messager.alert('提示', "备品编号不能为空");
                 return false;
             }
             if(sparepartName == null || sparepartName == "" || sparepartName.length == 0){
                 $.messager.alert('提示', "备品名称不能为空");
                 return false;
             }
             if(sparepartUnit == null || sparepartUnit == "" || sparepartUnit.length == 0){
                 $.messager.alert('提示', "计量单位不能为空");
                 return false;
             }
         	 $.ajax({
			     url:"spart_save.action",
				 type:"post", 
				 cache:false,
				 data:params(),
				 beforeSend:function(XMLHttpRequest){ 
                     $("#loading").html("<img src='${pageContext.request.contextPath}/images/loading.gif'/>"); 
                 }, 
				 success:function(msg){
				     $("#loading").empty();
				     $.messager.alert('添加备品', msg);
				 },
				 complete:function(XMLHttpRequest,textStatus){
				     $("#loading").empty();
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
                  query_params += "&sparepartPrice=" + $("#sparepartPrice").val();
                  query_params += "&sparepartProducer=" + $("#sparepartProducer").val();
                  query_params += "&sparepartSupplier=" + $("#sparepartSupplier").val();
                  query_params += "&sparepartSetplace=" + $("#sparepartSetplace").val();
                  query_params += "&sparepartStartDate=" + $("#sparepartStartDate").val();
                  query_params += "&sparepartUsemonths=" + $("#sparepartUsemonths").val();
                  query_params += "&sparepartRemark=" + $("#sparepartRemark").val();
              return query_params;
         }
    </script>
  </head>
  <body>
      <object id="PSKPrn" classid="clsid:81C07687-3353-4ABA-B108-94BCE81E5CBA" codebase="PSKPrn.ocx#version=1,0,0,1" width="0" height="0"></object>
      <form id="form1" name="form1">
	      <table>
	          <thead></thead>
	          <tbody>
	              <tr>
	                 <th colspan="6">新增备品</th>
	              </tr>
	              <tr>
	                 <td style="text-align: right;"><label style="color:red;" for="sparepartNo">备品编号:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartNo" name="sparepartNo" value=""/></td>
	                 <td style="text-align: right;"><label style="color:red;" for="sparepartName">备品名称:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartName" name="sparepartName" value=""/></td>
	                 <td style="text-align: right;"><label for="sparepartModle">规格型号:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartModle" name="sparepartModle" value=""/></td>
	              </tr>
	              <tr>
	                 <td style="text-align: right;"><label for="typeId">备品类型:</label></td>
	                 <td><select name="equipment.typeId" id="typeId">
			    		     <c:forEach items="${types}" var="type">
							     <option value="${type.typeId}">${type.typeName}</option>
						     </c:forEach>
			    		 </select>
		    		</td>
	                 <td style="text-align: right;"><label style="color:red;" for="sparepartUnit">计量单位:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartUnit" name="sparepartUnit" value=""/></td>
	                 <td style="text-align: right;"><label style="color:red;" for="sparepartPrice">单价（万）:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartPrice" name="sparepartPrice" value=""/></td>
	              </tr>
	              <tr>
	                 <td style="text-align: right;"><label for="sparepartProducer">生产商:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartProducer" name="sparepartProducer" value=""/></td>
	                 <td style="text-align: right;"><label for="sparepartSupplier">经销商:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartSupplier" name="sparepartSupplier" value=""/></td>
	                 <td style="text-align: right;"><label for="sparepartSetplace">安放地点:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartSetplace" name="sparepartSetplace" value=""/></td>
	              </tr>
	              <tr>
	                 <td style="text-align: right;"><label for="sparepartStartDate">更换时间:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartStartDate" name="sparepartStartDate" maxlength="10" value="" onfocus="$(this).calendar()"/></td>
	                 <td style="text-align: right;"><label style="color:red;" for="sparepartUsemonths">更换周期（月）:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartUsemonths" name="sparepartUsemonths" value=""/></td>
	                 <td style="text-align: right;"><label for="sparepartRemark">备注信息:</label></td>
	                 <td><input class="easyui-validatebox" type="text" id="sparepartRemark" name="sparepartRemark" value=""/></td>
	              </tr>
	          </tbody>
	      </table>
	      <input type="button" onclick="save();" value="确定" style="margin-left: 40%; margin-top: 5px;">
	      <input type="button" onclick="createDoc();" style="margin-left: 5px; margin-top: 5px;" value="生成二维码">
	      <input type="button" value="打印" onclick="print();" style="margin-left: 5px; margin-top: 5px;"/>
	      <div id="doc_img"></div>
	      <div id="loading"></div>
	      <div id="code-area"></div>
      </form>
  </body>
</html>
