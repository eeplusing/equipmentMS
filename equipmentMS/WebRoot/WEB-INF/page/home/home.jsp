<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
    <title>设备管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery.fileDownload.js"></script>
<style type="text/css">
    body{
         width: 100%;
         height: 100%;
         margin: 0px;
         padding:0px;
    }
    #all{
        overflow-x: hidden;
        width: 1316px;
        height: 100%;
        margin: auto;
        text-align: center;
        padding:0;
        border-right: 1px solid #9CF;
    }
    li{
       list-style: none;
    }
    li.head{
       background: #FFF url("${pageContext.request.contextPath}/images/head.jpg") no-repeat 0px 0px;
    }
    
    div.account-info{
        text-align: right;
        margin-right: 40px;
    }
    
    div.account-info a{
        font-size: 14px;
        font-weight: bolder;
        color: #436EEE;
    }
    
    div.account-info span{
        font-size: 14px;
        font-weight: bolder;
        color: #8A8A8A;
    }
    
    div.account-info a:HOVER{
        text-decoration: none;
    }
    
    #fullbg{
       display: none;
       background: #BFBFBF;
	   left: 0;
	   opacity: 0.5; /*CSS标准.该属性支持Firefox, Safari和 Opera.0.5表示透明度为50%*/
	   position: absolute;
	   top: 0;
	   z-index: 3;
	   filter: alpha(opacity = 50); /*透明度兼容IE6*/
	   -moz-opacity: 0.5; /*透明度兼容老版本的Mozilla浏览器*/
	   -khtml-opacity: 0.5; /*透明度兼容老版本的Safari浏览器*/
    }
    
    #date-in{
        display: none;
        position: absolute;
        width:915px;
        height:479px;
        z-index: 9;
        padding: 0px 10px;
        border: 0px solid #416AA3;
        border-radius: 5px;
        background: url('${pageContext.request.contextPath}/images/pop-up1.png');
    }
    #date-equ{
        display: none;
        position: absolute;
        width:915px;
        height:479px;
        z-index: 9;
        padding: 0px 10px;
        border: 0px solid #416AA3;
        border-radius: 5px;
        background: url('${pageContext.request.contextPath}/images/pop-up1.png');
    }
    div.center{
        width: 320px;
        margin: 100px auto;
    }
    
    #date-in input[type=text]{
        width: 230px;
        height: 30px;
        margin-top: 10px; 
    }
    #date-equ input[type=text]{
        width: 230px;
        height: 30px;
        margin-top: 10px; 
    }
    
    #date-in input[type=button]{
        width: 140px;
        height: 42px;
        margin-top: 10px;
        background: url('${pageContext.request.contextPath}/images/abutton.png');
        color: #fff;
        border-radius: 2px;
        font-weight: bolder;
        font-size: 14px;
        border: 0px solid #098FD2;
    }
    #date-equ input[type=button]{
        width: 140px;
        height: 42px;
        margin-top: 10px;
        background: url('${pageContext.request.contextPath}/images/abutton.png');
        color: #fff;
        border-radius: 2px;
        font-weight: bolder;
        font-size: 14px;
        border: 0px solid #098FD2;
    }
  
    input.conf{
        margin-left:20px;
        
    }
    
    input.cancel{
        margin-left: 10px;
    }
    
    #add-equ{
        display: none;
        width: 934px;
        height: 479px;
        position: absolute;
        z-index: 9;
        background: url('${pageContext.request.contextPath}/images/pop-up3.png');"
    }
    #add-equ table{
        font-size: 14px;
        margin: 57px 0px 0px 150px;
        border-collapse:collapse; 
        
    }
   
    #add-equ thead tr{
        height: 35px;
     
    }
    #add-equ tbody{
      border: 1px solid #8DB2E3;
    }
    th.title{
        font-size: 20px;
        font-weight: bold;
        color: #FFFFFF;
        float: left;
        margin-top: -25px;
        margin-left: -118px;
    }
     a.colse{
        font-size: 12px;
        color: #3B3B49;
        float: right;
        margin-right: 36px;
        margin-top: 35px;
        font-weight: bolder;
    }
    a.colse:HOVER{
        text-decoration: none;
    }
    
    #add-equ tbody tr{
        height: 30px;
        border-bottom: 1px solid #8DB2E3;
    }
    #add-equ tbody tr td{
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
   
    div.button-area{
       height: 20px;
       color: #15428B;
       border-radius: 2px;
       margin-top: 30px;
       margin-left:100px;
       cursor: pointer; 
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
    
    #loading{
       display: none;
       position: absolute;
       width: 105px;
       height: 104px;
       z-index: 9;
       background: url('${pageContext.request.contextPath}/images/load_11.gif') no-repeat;
    }
    
    #updateMM{
       position: absolute;
       padding: 0px;
       display:none;
       width: 560px;
       height:440px;
       z-index:9;
       background:url('images/pop-up4.png');
    }
    
    #pass_info{
       margin: 10px;
    }
    
    #pass_info div{
       width: 60%;
       margin: 25px auto;
       font-size: 14px;
       font-weight: bold;
       color: #666;
    }
    
    #pass_info div input[type=password]{
       width: 230px;
       height: 30px;
       border: 1px solid #0A93D4;
    }
    
    #pass_info div input[type=button]{
       width: 142px;
       height: 41px;
       border: none;
       cursor: pointer;
    }
    
    input.pass_cancel{
        margin-left: 20px;
        background: url("images/close.png");
    }
    
    input.pass_save{
        background: url("images/save.png");
    }
</style>
<script type="text/javascript">
    var iden = "";
    var bh = 0;
    var bw = 0;
    var equ_oper_type = "add";
    $(function(){
    
        $("#head").css("height", 150);
        $("#body").css("height", 830);
        
        bh = document.body.scrollHeight;//获取当前窗体的总高度
        bw = document.body.scrollWidth;
    });
    
    function update(){
        show_fg();
        $("#updateMM").css({
            left: (bw - 560)*0.5,
            top: (bh - 440)*0.5, 
            display: "block"
        });
    }
     
     function show_outstock(url){
    	$("#mpage").attr("src",url);
    	$("#stockout").css({display: "block"});
    }
    
    function show_instock(url){
    	$("#mpage1").attr("src",url);
    	$("#ruku").css({display: "block"});
    }
      function move_close(){
        close_fg();
    	$("#mpage").attr("src","");
    	$("#stockout").css("display", "none");
    	window.main.searchs();
    }
     
    function move_close1(){
        close_fg();
    	$("#mpage1").attr("src","");
    	$("#ruku").css("display", "none");
    	window.main.searchs();
    }
    function show_fg(){
        $("#fullbg").css({
            height: bh,
            width: bw, 
            display: "block"
        });
    }
     
    function close_fg(){
        $("#fullbg").hide();
    }
     
    function show_di(iden_code){
    if(iden_code == 'in' || iden_code == 'out')
    {
        $("#date-in").css({
            left: (bw - 934)*0.5,
            top: (bh - 497)*0.5, 
            display: "block"
        });
    }
    if(iden_code == 'pro' || iden_code == 'equ')
    {
        $("#date-equ").css({
            left: (bw - 934)*0.5,
            top: (bh - 497)*0.5, 
            display: "block"
        });
    }
        
        iden = iden_code;
    }
     
    function close_di(){
        $("#date-in").hide();
        $("#date-equ").hide();
    }
    
    function cancel_equexp(){
        close_di();
        close_fg();
    }
     
    function excelExp(){
        close_di();
        var url = "";
        var exp_data = "";
        if(iden == "equ"){
            url = "equ_downLoad.action";
            exp_data = "sdate=" + $("#ss-date").val() + "&edate=" + $("#ee-date").val() + "&eno=" + $("#e-NO").val();
        }else if(iden == "pro"){
            url = "provide_downLoad.action";
            exp_data = "sdate=" + $("#ss-date").val() + "&edate=" + $("#ee-date").val() + "&eno=" + $("#e-NO").val();
        }else if(iden == "in"){
            url = "iostock_downLoad.action";
            exp_data = "sdate=" + $("#s-date").val() + "&edate=" + $("#e-date").val() + "&type=in" + "&sno=" + $("#s-NO").val();
        }else if(iden == "out"){
            url = "iostock_downLoad.action";
            exp_data = "sdate=" + $("#s-date").val() + "&edate=" + $("#e-date").val() + "&type=out" + "&sno=" + $("#s-NO").val();
        }
        $.fileDownload(url, {
		    preparingMessageHtml : "文件正在生成,请稍后...",
			failMessageHtml : "文件生成失败，请重试",
			httpMethod : "POST",
			data : exp_data
		});
		close_fg();
    }
    
    function loadMain(url){
        $("#main").attr("src", url);
    }
    function setHeight (height){
        $("#head").css("height", 150);
        $("#body").css("height", 830);
    }
    
    function show_div(div_id, q_type){
        equ_oper_type = q_type;
        if(q_type == "add"){
            $("#create-doc").css("display", "inline");
	        $("#print-doc").css("display", "inline");
	        $("#save-equ").css("margin-left", "234px");
            $("#title").text("新增设备");
        }else if(q_type == "modify"){
            $("#create-doc").css("display", "none");
	        $("#print-doc").css("display", "none");
	        $("#save-equ").css("margin-left", "304px");
            $("#equipmentNo").attr("readonly", "readonly");
            $("#title").text("编辑设备");
        }
        var dh = $(div_id).css("height").replace("px", "")*1;
        var dw = $(div_id).css("width").replace("px", "")*1;
       
        $(div_id).css({
            left: (bw - dw)*0.5,
            top: (bh - dh)*0.5, 
            display: "block"
        });
    }   
    
    function colse_div(div_id){
        close_fg();
        $(div_id).hide();
        $("#equipmentNo").val("");
        $("#equipmentName").val("");
        $("#equipmentModle").val("");
        $("#equipmentProducer").val("");
        $("#equipmentSupplier").val("");
        $("#equipmentBuyTime").val("");
        $("#equipmentBuyType").val("");
        $("#equipmentRecipient").val("");
        $("#equipmentProvider").val("");
        $("#equipmentDirector").val("");
        $("#equipmentStartDate").val("");
        $("#equipmentUseYears").val("");
        $("#equipmentMonetaryAmount").val("");
        $("#amount").val("");
        $("#areaCode").val("");
        $("#remark").val("");
        $("#equipmentId").val("");
        $("#doc_img").html("" );
        $("#equipmentNo").removeAttr("readonly");
        window.main.searchs();
    }
    
    function loadData(r_data){
        $("#equipmentNo").val(r_data[0].equipmentNo);
        $("#equipmentName").val(r_data[0].equipmentName);
        $("#equipmentModle").val(r_data[0].equipmentModle);
        $("#equipmentProducer").val(r_data[0].equipmentProducer);
        $("#equipmentSupplier").val(r_data[0].equipmentSupplier);
        $("#equipmentBuyTime").val(r_data[0].equipmentBuyTime);
        $("#equipmentBuyType").val(r_data[0].equipmentBuyType);
        $("#equipmentRecipient").val(r_data[0].equipmentRecipient);
        $("#equipmentProvider").val(r_data[0].equipmentProvider);
        $("#equipmentDirector").val(r_data[0].equipmentDirector);
        $("#equipmentStartDate").val(r_data[0].startDate);
        $("#equipmentUseYears").val(r_data[0].equipmentUseYears);
        $("#equipmentMonetaryAmount").val(r_data[0].equipmentMonetaryAmount);
        $("#amount").val(r_data[0].amount);
        $("#areaCode").val(r_data[0].areaCode);
        $("#remark").val(r_data[0].remark);
        $("#equipmentId").val(r_data[0].equipmentId);
        $("#typeName").val(r_data[0].typeName);
        $("#useState").val(r_data[0].useState);
        $("#doc_img").html("<img src='equ_showImage.action?imageName=${codepath}" + r_data[0].equipmentNo + ".png'/>" );
    }
    
    function createDoc(){
        var equipmentNo = $("#equipmentNo").val().replace(/(^\s*)|(\s*$)/g, "");
        if(equipmentNo == null || equipmentNo == "" || equipmentNo.length == 0){
            $.messager.alert('提示', "请输入设备编号");
            return false;
        }
        $.ajax({
            url:"equ_createDoc.action",
            type:"post", 
            cache:false,
            data:"equipmentNo=" + $("#equipmentNo").val(),
            success:function(msg){
                var ms = msg.split(";");
                $("#doc_img").html("<img src='equ_showImage.action?imageName=" + ms[0] + "'/>" );
            }
        });
    }
         
    function save(){
        var equipmentNo = $("#equipmentNo").val().replace(/(^\s*)|(\s*$)/g, "");
        var equipmentName = $("#equipmentName").val().replace(/(^\s*)|(\s*$)/g, "");
        if(equipmentNo == null || equipmentNo == "" || equipmentNo.length == 0){
            $.messager.alert('提示', "设备编号不能为空");
            return false;
        }
        if(equipmentName == null || equipmentName == "" || equipmentName.length == 0){
            $.messager.alert('提示', "设备名称不能为空");
            return false;
        }
        if(equ_oper_type == "add"){
	        $.ajax({
			    url:"equ_save.action",
			    type:"post", 
			    cache:false,
				data:params(),
				success:function(msg){
				     $.messager.alert('添加设备', msg);
				}
		    });
        }else if(equ_oper_type == "modify"){
            $.ajax({
			    url:"equ_modify.action",
				type:"post", 
			    cache:false,
				data:params() + "&equipmentId=" + $("#equipmentId").val(),
				success:function(msg){
				    $.messager.alert('编辑设备', msg);
				}
			});
        }
        
    }
         
    function print(){
         var equipmentNo = $("#equipmentNo").val().replace(/(^\s*)|(\s*$)/g, "");
         if(equipmentNo == null || equipmentNo == "" || equipmentNo.length == 0){
             $.messager.alert('提示', "设备编号为空,不能生成二维码");
             return false;
         }else{
        	 PSKPrn.OpenPort("POSTEK C168/200s");
	         PSKPrn.PTKClearBuffer();
	         PSKPrn.PTKSetPrintSpeed(4);
	         PSKPrn.PTKSetDarkness(10);
	         PSKPrn.PTKSetLabelHeight(300, 24);
	         PSKPrn.PTKSetLabelWidth(824);

             //二维码
	         PSKPrn.PTKDrawBar2DQR(300, 20, 90, 90, 0, 5, 2, 0, 0, equipmentNo);
	             
	         PSKPrn.PTKDrawTextTrueTypeW(300, 120, 36, 0, "微软雅黑", 1, 500, false, false, false, "B5", "NO:" + equipmentNo);
	         PSKPrn.PTKPrintLabel(1, 1);
	         PSKPrn.ClosePort();
         }
    }
         
    function params(){
         var query_params = "equipmentNo=" + $("#equipmentNo").val();
              query_params += "&equipmentName=" + $("#equipmentName").val();
              query_params += "&equipmentModle=" + $("#equipmentModle").val();
              query_params += "&typeName=" + $("#typeName").val();
              query_params += "&equipmentProducer=" + $("#equipmentProducer").val();
              query_params += "&equipmentSupplier=" + $("#equipmentSupplier").val();
              query_params += "&equipmentBuyTime=" + $("#equipmentBuyTime").val();
              query_params += "&equipmentBuyType=" + $("#equipmentBuyType").val();
              query_params += "&equipmentRecipient=" + $("#equipmentRecipient").val();
              query_params += "&equipmentProvider=" + $("#equipmentProvider").val();
              query_params += "&equipmentDirector=" + $("#equipmentDirector").val();
              query_params += "&useState=" + $("#useState").val();
              query_params += "&equipmentStartDate=" + $("#equipmentStartDate").val();
              query_params += "&equipmentUseYears=" + $("#equipmentUseYears").val();
              query_params += "&equipmentMonetaryAmount=" + $("#equipmentMonetaryAmount").val();
              query_params += "&amount=" + $("#amount").val();
              query_params += "&areaCode=" + $("#areaCode").val();
              query_params += "&remark=" + $("#remark").val();
         return query_params;
    }
    
    function loadType(){
        $.ajax({
            url:"index_loadType.action",
            type:"post", 
            cache:false,
            success:function(msg){
                var ms = msg.split(";");
                var type_html = "";
                for(var i = 0; i < ms.length; i++){
                    type_html += "<option value=\"" + ms[i] + "\">" + ms[i] + "</option>";
                }
                $("#typeName").html(type_html);
            }
        });
    }
    
    function loadState(){
        $.ajax({
            url:"index_loadState.action",
            type:"post", 
            cache:false,
            success:function(msg){
                var ms = msg.split(";");
                var state_html = "";
                for(var i = 0; i < ms.length; i++){
                    state_html += "<option value=\"" + ms[i] + "\">" + ms[i] + "</option>";
                }
                $("#useState").html(state_html);
            }
        });
    }
    
    function show_loading(){
        show_fg();
        $("#loading").css({
            left: (bw - 105)*0.5,
            top: (bh - 104)*0.5, 
            display: "block"
        });
    }
    function close_loading(){
        close_fg();
        $("#loading").hide();
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
        a=document.getElementById("updateMM");
        document.all?a.setCapture():window.captureEvents(Event.MOUSEMOVE);
        b=e.clientX-parseInt(a.style.left);
        c=e.clientY-parseInt(a.style.top);
    }
    
    function mm_close(){
        close_fg();
        $("#updateMM").hide();
        $("#oldPassword").val("");
        $("#newPassword").val("");
        $("#affirm").val("");
    }
    
    function modify(){
	    var oldPassword = $("#oldPassword").val().replace(/(^\s*)|(\s*$)/g, "");
	    var newPassword = $("#newPassword").val().replace(/(^\s*)|(\s*$)/g, "");
	    var affirm = $("#affirm").val().replace(/(^\s*)|(\s*$)/g, "");
	         
	    if(newPassword != affirm){
	        $.messager.alert('提示', "两次密码不一致");
	        return false;
	    }
	         
	    if(oldPassword == null || oldPassword.length == 0){
	        $.messager.alert('提示', "原始密码为空!");
	        return false;
	    }
	    
	    $.ajax({
			type : "post",
			url : "pass_modify.action",
			data : "oldPassword=" + oldPassword + "&newPassword=" + newPassword,
			success: function(msg){
			    if(msg == "pass-error"){
				    $.messager.alert('提示', "原始密码错误");
			    } else if(msg == "error"){
				    $.messager.alert('提示', "密码修改失败");
			    } else if(msg == "no-user"){
				    $.messager.alert('提示', "账号不存在");
			    } else if(msg == "success"){
			        mm_close();
			        $.messager.alert('提示', "密码修改成功，请重新登录");
                    document.getElementById("drop_out").click();
			    } 
			}
	    });
    }
</script>
</head>
<body>
    <div id="all" style="margin: 0px auto; padding: 0px; width: 1316px;">
         <ul style="margin:0px; padding: 0px; width: 100%; border: 1px solid #9CF;">
             <li class="head" style="margin: 0px; padding: 0px; border: 0px solid #F00;" id="head">
                 <div style="height: 15px;"></div>
                 <div class="account-info">
                     <a href="home_login" id="drop_out" title="退出系统">退出</a><span style="margin-left:5px;">|</span>
                     <a href="javascript:void(0)" title="修改个人密码" onclick="update();">修改密码</a>
                 </div>
             </li>
             <li style="margin: 0px; padding: 0px; height:84%; border-top: 1px solid #9CF; overflow: hidden;" id="body">
                 <div style="overflow:hidden; width: 200px; height:100%; border-right: 1px solid #9CF; display:inline; float: left;">
                     <iframe src="index_menu.action" id="menu" frameborder="0" style="width: 100%; height:100%; border: none;" scrolling="auto" name="menu"></iframe>
                     <div style="clear: both;"></div>
                 </div>
                 <div style="width: 1097px; height:100%; display:inline; float: left; overflow: hidden;">
                     <iframe src="index_main.action" id="main" frameborder="0" style=" overflow: hidden; width: 100%; height:100%; border: none;" scrolling="auto" name="main"></iframe>
                     <div style="clear: both;"></div>
                 </div>
                 <div style="clear: both;"></div>
             </li>
         </ul>
         <div style="clear: both;"></div>
    </div>
   
    <div id="fullbg"></div>
    <div id="loading"></div>
    
    <!-- 数据导出 -->
    <div id="date-in">
        <div class="center">
	        <span style="font-size: 14px; font-weight: bolder;"> 起始日期：</span>
	        <input type="text" maxlength="10" id="s-date" value="" onfocus="$(this).calendar()"/><br><br>
	  	    <span style="font-size: 14px; font-weight: bolder;">结束日期：</span>
	  	    <input type="text" maxlength="10" id="e-date" value="" onfocus="$(this).calendar()"/><br><br><br>
	  	    <span style="font-size: 14px; font-weight: bolder;">备品编号：</span>
	  	    <input type="text" maxlength="10" id="s-NO" /><br><br><br>
	  	   
	  	    <input class="conf" type="button" value="确  定" onclick="excelExp();">
	  	    <input class="cancel" type="button" value="取  消" onclick="cancel_equexp();">
	  	    <div style="clear: both;"></div>
        </div>
  	    <div style="clear: both;"></div>
  	</div>
  	<!-- 设备导出 -->
  	<div id="date-equ">
        <div class="center">
	        <span style="font-size: 14px; font-weight: bolder;"> 起始日期：</span>
	        <input type="text" maxlength="10" id="ss-date" value="" onfocus="$(this).calendar()"/><br><br>
	  	    <span style="font-size: 14px; font-weight: bolder;">结束日期：</span>
	  	    <input type="text" maxlength="10" id="ee-date" value="" onfocus="$(this).calendar()"/><br><br><br>
	  	     <span style="font-size: 14px; font-weight: bolder;">批次编号：</span>
	  	    <input type="text" maxlength="10" id="e-NO" value="" /><br><br><br>
	  	   
	  	    <input class="conf" type="button" value="确  定" onclick="excelExp();">
	  	    <input class="cancel" type="button" value="取  消" onclick="cancel_equexp();">
	  	    <div style="clear: both;"></div>
        </div>
  	    <div style="clear: both;"></div>
  	</div>
  	<!-- 批次新增 -->
  	<div id="add-equ">
  	    <object id="PSKPrn" classid="clsid:81C07687-3353-4ABA-B108-94BCE81E5CBA" codebase="PSKPrn.ocx#version=1,0,0,1" width="0" height="0"></object>
  	    <table>
	        <thead>
	            <tr>  
	                <th colspan="6" class="title" id="title" >新增设备</th>
	                <a class="colse" href="javascript:void(0)" onclick="colse_div('#add-equ');">[关闭]</a>
	            </tr>
	        </thead>
	        <tbody>
	            <tr>
	                <td colspan="2" rowspan="5">
	                    <div id="doc_img"></div>
	                </td>
	                <td class="field-name"><label>批次编号</label></td>
	                <td class="field-value"><input type="text" id="equipmentNo" value=""/></td>
	                <td class="field-name"><label>设备名称</label></td>
	                <td class="field-value"><input type="text" id="equipmentName" value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>规格型号</label></td>
	                <td class="field-value"><input type="text" id="equipmentModle" value=""/></td>
	                <td class="field-name"><label>生产商</label></td>
	                <td class="field-value"><input type="text" id="equipmentProducer" value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>供应商</label></td>
	                <td class="field-value"><input type="text" id="equipmentSupplier" value=""/></td>
	                <td class="field-name"><label>购置时间</label></td>
	                <td class="field-value"><input type="text" maxlength="10" id="equipmentBuyTime" value="" onfocus="$(this).calendar()"/></td>	                
	            </tr>
	            <tr>
	                <td class="field-name"><label>购置方式</label></td>
	                <td class="field-value"><input type="text" id="equipmentBuyType" value=""/></td>
	                <td class="field-name"><label>设备接收人</label></td>
	                <td class="field-value"><input type="text" id="equipmentRecipient" value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>设备发放人</label></td>
	                <td class="field-value"><input type="text" id="equipmentProvider" value=""/></td>
	                <td class="field-name"><label>设备监督人</label></td>
	                <td class="field-value"><input type="text" id="equipmentDirector" value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>设备类型</label></td>
	                <td class="field-value">
	                     <select id="typeName"></select>
		    		</td>
	                <td class="field-name"><label>启用日期</label></td>
	                <td class="field-value"><input type="text" maxlength="10" id="equipmentStartDate" value="" onfocus="$(this).calendar()"/></td>
	                <td class="field-name"><label>使用年限(年)</label></td>
	                <td class="field-value"><input type="text" id="equipmentUseYears" value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>使用状态</label></td>
	                <td class="field-value">
	                    <select id="useState"></select>
		    		</td>
		    		<td class="field-name"><label>购买金额(万)</label></td>
	                <td class="field-value"><input type="text" id="equipmentMonetaryAmount" value=""/></td>
	                <td class="field-name"><label>区段号</label></td>
	                <td class="field-value"><input type="text" id="areaCode" value=""/></td>
	            </tr>
	            <tr>
	                <td class="field-name"><label>数量</label></td>
	                <td class="field-value"><input type="text" maxlength="10" id="amount" value=""/></td>
	                <td class="field-name"><label>备注</label></td>
	                <td class="field-value-remark" colspan="3"><input type="text" id="remark" value=""/></td>
	            </tr>
	        </tbody>
	    </table>
	     <div class="button-area">
	          <input type="button" onclick="save();" id="save-equ" value="确 定">
	          <input type="button" onclick="createDoc();" id="create-doc" value="生成二维码">
	          <input type="button" value="打 印" onclick="print();" id="print-doc"/>
	     </div>
  	</div>
  	<!-- 出库 -->
  	<div id="stockout" style="z-index: 9; width:1200px; height: 800px; position:absolute;padding:0px; border:0px solid #999; left:350px; top:100px; background:#FFF; display:none;">
  	      <div style="width:100%; border:0px solid #00F; height: 0px; background:#9CF;">
  	      	  <img style="width:1200px; height: 800px" src="${pageContext.request.contextPath}/images/pop-up.png"/>
  	      </div>
  	      <div id="dialog_text" style=" border:0px solid #00F;" >
	   	    <a href="javascript:void(0)"  onclick="move_close();" style="color: #000; font-size: 14px">
				<img style="margin-left:1130px; margin-top:20px; width:40px; height:40px;" src="${pageContext.request.contextPath}/images/closebutton.png"></a>
		  	<iframe src="" style="border:0px solid #F00; margin-top:5px; margin-left:27px; width:1152px; height: 719px;" id="mpage" scrolling="auto" name="modify_main"></iframe>
		 </div>
  	</div>
  	
  	<!-- 入库 -->
  	<div id="ruku" style="display: none; width: 934px; height: 479px; position: absolute; z-index: 9; left:500px; top:100px; background: url('${pageContext.request.contextPath}/images/pop-up3.png');">
  		<div><label style="left:40px; top:35px; position: absolute; font-weight:bold; color:#FFF; font-size:16px;">备品入库</label>
  		</div>
  	     <div id="dialog_text" style=" border:0px solid #00F;" >
	   	    <a href="javascript:void(0)"  onclick="move_close1();" style="color: #000; font-size: 14px">
				<img style="margin-left:850px; margin-top:15px; width:30px; height:30px;" src="${pageContext.request.contextPath}/images/closebutton.png"></a>
		  	<iframe src="" style="border:0px solid #F00; margin-top:5px; margin-left:27px; width:934px; height: 479px;" id="mpage1" scrolling="no" name="modify_main"></iframe>
		 </div>
  	</div>
  	<!-- 编辑批次 -->
  	<input id="equipmentId" type="hidden" value="">
  	
  	<!-- 修改密码 -->
  	<div id="updateMM">
  	    <div style="padding:32px 20px 0px 20px; height: 25px; cursor:move;" onmousedown="mdown(event);">
  	        <div style="float: left;">
  	            <div style="font-size: 12px; font-weight: bold; color: #FFF; height: 25px; display: block;">修改密码<div style="clear: both;"></div></div>
  	            <div style="clear: both;"></div>
  	        </div>
  	        <div style="width: 50px; float: right;">
  	            <a href="javascript:void(0)"  onclick="mm_close();" style="float: right; color: #000; font-size: 14px">[关闭]</a>
  	            <div style="clear: both;"></div>
  	        </div>
  	    </div>
  	    <div id="pass_info">
  	        <div>
  	            原始密码：<input type="password" id="oldPassword" name="oldPassword">
  	        </div>
  	        <div>
  	            新设密码：<input type="password"  id="newPassword" name="newPassword">
  	        </div>
  	        <div>
  	            密码确认：<input type="password" id="affirm" name="affirm">
  	        </div>
  	        <div style="margin-top: 30px;">
  	            <input type="button" onclick="modify();" class="pass_save">
  	            <input type="button" onclick="mm_close();" class="pass_cancel">
  	        </div>
  	    </div>
  	</div>
</body>
</html>