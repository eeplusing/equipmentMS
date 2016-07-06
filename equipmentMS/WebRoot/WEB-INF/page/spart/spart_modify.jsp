<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑备品信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>
    
    <script type="text/javascript">
       function save(){
            var sparepartName = $("#sparepartName").val().replace(/(^\s*)|(\s*$)/g, "");
            var sparepartUnit = $("#sparepartUnit").val().replace(/(^\s*)|(\s*$)/g, "");
            var sparepartPrice = $("#sparepartPrice").val().replace(/(^\s*)|(\s*$)/g, "");
            var money_reg=/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
            if(sparepartName == null || sparepartName == "" || sparepartName.length == 0){
                $.messager.alert('提示', "备品名称不能为空");
                return false;
            }
            if(sparepartUnit == null || sparepartUnit == "" || sparepartUnit.length == 0){
                $.messager.alert('提示', "计量单位不能为空");
                return false;
            }
         	$.ajax({
			    url:"spart_modify.action",
				type:"post", 
				cache:false,
				data:params(),
				beforeSend:function(XMLHttpRequest){ 
                    $("#loading").html("<img src='${pageContext.request.contextPath}/images/loading.gif'/>"); 
                }, 
				success:function(msg){
				    $("#loading").empty();
				    $.messager.alert('编辑备品信息', msg);
				},
				complete:function(XMLHttpRequest,textStatus){
				    $("#loading").empty();
				}
			});
         }
         
         function params(){
              var query_params = "sparepartId=" + $("#sparepartId").val();
                  query_params += "&sparepartNo=" + $("#sparepartNo").val();
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
       <table>
	      <thead></thead>
	      <tbody>
	          <tr>
	             <th colspan="6">编辑备品</th>
	          </tr>
	          <tr>
	             <td style="text-align: right;"><label style="color:red;" for="sparepartNo">备品编号:</label></td>
	             <td><input type="hidden" id="sparepartId" value="${sparepart.sparepartId}"/><input class="easyui-validatebox" type="text" id="sparepartNo" name="sparepartNo" readonly="readonly" value="${sparepart.sparepartNo}"/></td>
	             <td style="text-align: right;"><label style="color:red;" for="sparepartName">备品名称:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartName" name="sparepartName" value="${sparepart.sparepartName}"/></td>
	             <td style="text-align: right;"><label for="sparepartModle">规格型号:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartModle" name="sparepartModle" value="${sparepart.sparepartModle}"/></td>
	          </tr>
	          <tr>
	             <td style="text-align: right;"><label for="typeId">备品类型:</label></td>
	             <td><select name="sparepart.typeId" id="typeId">
	                     <c:forEach items="${types}" var="type">
			    		     <c:if test="${type.typeId == sparepart.typeId}">
			    		         <option value="${type.typeId}" selected="selected">${type.typeName}</option>
			    		     </c:if>
			    		     <c:if test="${type.typeId != sparepart.typeId}">
			    		         <option value="${type.typeId}">${type.typeName}</option>
			    		     </c:if>
			    		 </c:forEach>
			      </select>
		      </td>
	             <td style="text-align: right;"><label style="color:red;" for="sparepartUnit">计量单位:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartUnit" name="sparepartUnit" value="${sparepart.sparepartUnit}"/></td>
	             <td style="text-align: right;"><label style="color:red;" for="sparepartPrice">单价（万）:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartPrice" name="sparepartPrice" value="${sparepart.sparepartPrice}"/></td>
	          </tr>
	          <tr>
	             <td style="text-align: right;"><label for="sparepartProducer">生产商:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartProducer" name="sparepartProducer" value="${sparepart.sparepartProducer}"/></td>
	             <td style="text-align: right;"><label for="sparepartSupplier">经销商:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartSupplier" name="sparepartSupplier" value="${sparepart.sparepartSupplier}"/></td>
	             <td style="text-align: right;"><label for="sparepartSetplace">安放地点:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartSetplace" name="sparepartSetplace" value="${sparepart.sparepartSetplace}"/></td>
	          </tr>
	          <tr>
	             <td style="text-align: right;"><label for="sparepartStartDate">更换时间:</label></td>
	             <td><input class="easyui-validatebox" style="width:75px;" type="text" id="sparepartStartDate" name="sparepartStartDate" maxlength="10" value="${sparepart.sparepartStartDate}" onfocus="$(this).calendar()"/></td>
	             <td style="text-align: right;"><label style="color:red;" for="sparepartUsemonths">更换周期（月）:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartUsemonths" name="sparepartUsemonths" value="${sparepart.sparepartUsemonths}"/></td>
	             <td style="text-align: right;"><label for="sparepartRemark">备注信息:</label></td>
	             <td><input class="easyui-validatebox" type="text" id="sparepartRemark" name="sparepartRemark" value="${sparepart.sparepartRemark}"/></td>
	          </tr>
	      </tbody>
	  </table>
	  <input type="button" onclick="save();" value="确定" style="margin-left: 46%; margin-top: 5px;"/>
 	  <div id="loading"></div>
  </body>
</html>
