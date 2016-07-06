<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>设备及备品备件信息</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    
<style type="text/css">
body,div,p,span,hr,ul,ol,li,form,input,textarea,h1,h2,h3,h4,table,tr,td,caption,iframe,img {
	margin: 0px;
	padding: 0;
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif, "宋体";
}

body{
     overflow: hidden;
}

#spart{
   margin-top: 20px;
}

li {
	list-style: none;
}

.left {
    padding-top: 5px;
	float: left;
	width: 100px;
	height: 20px;
	border-bottom: 1px dashed #DDD;
}

.right {
	float: right;
}

.txt_left {
    text-align: left;
    width: 101px;
    border-right: 1px dashed #DDD;
}

.txt_right {
	text-align: right;
}

.odd {
     background: #EEF;
}

.clear {
	clear: both;
}

.tl{
    width: 707px;
}

#all {
    width: 808px;
    margin: 20px auto;
    border: 1px solid #99BBE8;
}
li.equ_title{
    height: 25px;
    text-align: center;
    font-size: 14px;
    color: #666;
    font-weight: bold;
    padding-top: 7px;
    background: #99BBE8;
}

</style>

</head>

<body>
	<div id="all">
	   <div id="equ">
	       <ul>
	           <li class="equ_title">设备参数</li>
	           <li class="left txt_right odd">批次编号:</li><li class="left txt_left odd">${equ.equipmentNo}</li>
	           <li class="left txt_right odd">设备名称:</li><li class="left txt_left odd">${equ.equipmentName}</li>
	           <li class="left txt_right odd">规格型号:</li><li class="left txt_left odd">${equ.equipmentModle}</li>
	           <li class="left txt_right odd">设备类型:</li><li class="left txt_left odd">${equ.typeName}</li>
	           <li class="left txt_right odd">生产商:</li><li class="left txt_left odd tl">${equ.equipmentProducer}</li>
	           <li class="left txt_right">供应商:</li><li class="left txt_left tl">${equ.equipmentSupplier}</li>
	           <li class="left txt_right">购置时间:</li><li class="left txt_left">${equ.equipmentBuyTime}</li>
	           <li class="left txt_right">购置方式:</li><li class="left txt_left">${equ.equipmentBuyType}</li>
	           <li class="left txt_right odd">设备接收人:</li><li class="left txt_left odd">${equ.equipmentRecipient}</li>
	           <li class="left txt_right odd">设备发放人:</li><li class="left txt_left odd">${equ.equipmentProvider}</li>
	           <li class="left txt_right odd">设备监督人:</li><li class="left txt_left odd">${equ.equipmentDirector}</li>
	           <li class="left txt_right odd">使用状态:</li><li class="left txt_left odd">${equ.useState}</li>
	           <li class="left txt_right">启用日期:</li><li class="left txt_left">${equ.equipmentStartDate}</li>
	           <li class="left txt_right">使用年限:</li><li class="left txt_left">${equ.equipmentUseYears}</li>
	           <li class="left txt_right">购买金额（万）:</li><li class="left txt_left">${equ.equipmentMonetaryAmount}</li>
	           <li class="left txt_right">数量:</li><li class="left txt_left">${equ.amount}</li>
	           <li class="left txt_right">区段号:</li><li class="left txt_left">${equ.areaCode}</li>
	           <li class="left txt_right">备注:</li><li class="left txt_left">${equ.remark}</li>
	       </ul>
	       <div style="clear: both;"></div>
	   </div>
       <div style="clear: both;"></div>
	</div>
</body>
</html>
