<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备发放</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-calendar.css" />
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/component/jquery-calendar.js"></script>
    <style type="text/css">
       body, #all,#add-form{
           width: 100%;
           overflow-x: hidden;
           margin: 0px;
           padding: 0px;
       }
       
       #top, #info{
           width: 711px;
           margin: 5px auto;
           border: 1px solid #416AA3;
           border-collapse: collapse;
       }
       
       #top thead{
           text-align: center;
           font-weight: bolder;
           color: #5072A1;
           height: 35px;
           background: #E5EEFF;
           border-bottom: 1px solid #416AA3;
       }
       
       #top thead tr th{
           font-size: 20px;
       }
       
       #top tbody tr{
           height: 28px;
           border-bottom: 1px solid #416AA3;
       }
       
       td.fr{
          width: 96px;
          padding: 0px 2px;
          text-align: right;
          font-size: 12px;
       }
       
       td.fl{
          width: 166px;
          padding: 0px 2px;
          text-align: left;
          font-size: 12px;
       }
       
       td.rl{
          border-right: 1px solid #416AA3;
       }
       
       tr.tit{
          text-align: center;
          font-weight: bolder;
          color: #5072A1;
          height: 35px;
          background: #E5EEFF;
          border-bottom: 1px solid #416AA3;
       }
       
       tr.tit th{
          font-size: 20px;
       }
       
       tr.fs{
          height: 30px;
          color: #666;
          background: #EFEFEF;
          border-bottom: 1px solid #CCC;
       }
       
       tr.fs th{
          font-size: 16px;
          border-right: 1px solid #CCC;
       }
       
       th.station{
          width: 100px;
       }
       th.receiver, th.provideDate{
          width: 80px;
       }
       th.amount{
          width: 50px;
       }
       th.remark{
          width: 390px;
       }
       
       #info tbody tr{
          height: 25px;
          border-bottom: 1px solid #CCC;
       }
       
       #info tbody tr td{
          font-size: 14px;
          text-align: left;
          border-right: 1px dashed #CCC;
       }
       td.remark{
          border-right: none;
       }
       
       td.amount input{
          width: 60px;
          height: 25px;
          border: none;
       }
       
       td.receiver input, td.provideDate input{
          width: 80px;
          height: 25px;
          border: none;
       }
       
       td.remark input{
          width: 390px;
          height: 25px;
          border: none;
       }
       
       #save_buton{
          display: block;
          margin-top: 10px;
          width: 50px;
          height: 25px;
          border: 1px solid #416AA3;
          color: #416AA3;
          background: #E5EEFF;
          margin: 0px auto;
          cursor: pointer;
       }
    </style>
    
    <script type="text/javascript">
       $(function(){
           $("#equ_no").change(function(){
               if($(this).val() == "---"){
                   $("#equ_name").text("");
                   $("#equ_modle").text("");
                   $("#equ_amount").text("");
                   $("#equ_start_date").text("");
                   $("#equ_use_years").text("");
                   $("#equ_area_code").text("");
               }else{
                   $.ajax({
					   type : "POST",
					   url : "provide_loadEquipment.action?equ_no=" + $(this).val(),
					   dataType : "text",
					   success : function(msg) {
					       var equ = eval('(' + msg + ')');
					       $("#equ_name").text(equ.name);
                           $("#equ_modle").text(equ.modle);
                           $("#equ_amount").text(equ.amount);
                           $("#equ_start_date").text(equ.startDate);
                           $("#equ_use_years").text(equ.useYears);
                           $("#equ_area_code").text(equ.areaCode);
					   }
				   });
               }
           });
           
           $("#save_buton").click(function(){
               var amount = $("#equ_amount").text()*1;
               var equno = $("#equ_no").val();
               
               var amountA = $("#amountA").val();
               var receiverA = $("#receiverA").val();
               var provideDateA = $("#provideDateA").val();
               var remarkA = $("#remarkA").val();
               
               var amountB = $("#amountB").val();
               var receiverB = $("#receiverB").val();
               var provideDateB = $("#provideDateB").val();
               var remarkB = $("#remarkB").val();
               
               var amountC = $("#amountC").val();
               var receiverC = $("#receiverC").val();
               var provideDateC = $("#provideDateC").val();
               var remarkC = $("#remarkC").val();
               
               var amountD = $("#amountD").val();
               var receiverD = $("#receiverD").val();
               var provideDateD = $("#provideDateD").val();
               var remarkD = $("#remarkD").val();
               
               var amountE = $("#amountE").val();
               var receiverE = $("#receiverE").val();
               var provideDateE = $("#provideDateE").val();
               var remarkE = $("#remarkE").val();
               
               var amountF = $("#amountF").val();
               var receiverF = $("#receiverF").val();
               var provideDateF = $("#provideDateF").val();
               var remarkF = $("#remarkF").val();
               
               var amountG = $("#amountG").val();
               var receiverG = $("#receiverG").val();
               var provideDateG = $("#provideDateG").val();
               var remarkG = $("#remarkG").val();
               
               var amountH = $("#amountH").val();
               var receiverH = $("#receiverH").val();
               var provideDateH = $("#provideDateH").val();
               var remarkH = $("#remarkH").val();
               
               var amountI = $("#amountI").val();
               var receiverI = $("#receiverI").val();
               var provideDateI = $("#provideDateI").val();
               var remarkI = $("#remarkI").val();
               
               var amountJ = $("#amountJ").val();
               var receiverJ = $("#receiverJ").val();
               var provideDateJ = $("#provideDateJ").val();
               var remarkJ = $("#remarkJ").val();
               
               var amountK = $("#amountK").val();
               var receiverK = $("#receiverK").val();
               var provideDateK = $("#provideDateK").val();
               var remarkK = $("#remarkK").val();
               
               var amountL = $("#amountL").val();
               var receiverL = $("#receiverL").val();
               var provideDateL = $("#provideDateL").val();
               var remarkL = $("#remarkL").val();
               
               var amountM = $("#amountM").val();
               var receiverM = $("#receiverM").val();
               var provideDateM = $("#provideDateM").val();
               var remarkM = $("#remarkM").val();
               
               var amountN = $("#amountN").val();
               var receiverN = $("#receiverN").val();
               var provideDateN = $("#provideDateN").val();
               var remarkN = $("#remarkN").val();
               
               var amountO = $("#amountO").val();
               var receiverO = $("#receiverO").val();
               var provideDateO = $("#provideDateO").val();
               var remarkO = $("#remarkO").val();
               
               var amountP = $("#amountP").val();
               var receiverP = $("#receiverP").val();
               var provideDateP = $("#provideDateP").val();
               var remarkP = $("#remarkP").val();
               
               var num_reg = /^[1-9]{1}\d*$/;
               
               var num_validate = num_reg.test(amountA) && num_reg.test(amountB) && num_reg.test(amountC) && num_reg.test(amountD)
                       && num_reg.test(amountE) && num_reg.test(amountF) && num_reg.test(amountG) && num_reg.test(amountH)
                       && num_reg.test(amountI) && num_reg.test(amountJ) && num_reg.test(amountK) && num_reg.test(amountL)
                       && num_reg.test(amountM) && num_reg.test(amountN) && num_reg.test(amountO) && num_reg.test(amountP);
               /*if(!num_validate){
                   alert("数量格式不合法");
                   return false;
               }*/
               num_validate = amount >= (amountA*1 + amountB*1 + amountC*1 + amountD*1 + amountE*1 + amountF*1 + amountG*1 + amountH*1 +
                                         amountI*1 + amountJ*1 + amountK*1 + amountL*1 + amountM*1 + amountN*1 + amountO*1 + amountP*1);
               if(!num_validate){
                   alert("发放数量总和大于设备实际所有数");
                   return false;
               }
               
               var query_params = "equno=" + equno + "&amount=" + amount 
                                  + "&amountA=" + amountA*1 + "&receiverA=" + receiverA + "&provideDateA=" + provideDateA + "&remarkA=" + remarkA
                                  + "&amountB=" + amountB*1 + "&receiverB=" + receiverB + "&provideDateB=" + provideDateB + "&remarkB=" + remarkB
                                  + "&amountC=" + amountC*1 + "&receiverC=" + receiverC + "&provideDateC=" + provideDateC + "&remarkC=" + remarkC
                                  + "&amountD=" + amountD*1 + "&receiverD=" + receiverD + "&provideDateD=" + provideDateD + "&remarkD=" + remarkD
                                  + "&amountE=" + amountE*1 + "&receiverE=" + receiverE + "&provideDateE=" + provideDateE + "&remarkE=" + remarkE
                                  + "&amountF=" + amountF*1 + "&receiverF=" + receiverF + "&provideDateF=" + provideDateF + "&remarkF=" + remarkF
                                  + "&amountG=" + amountG*1 + "&receiverG=" + receiverG + "&provideDateG=" + provideDateG + "&remarkG=" + remarkG
                                  + "&amountH=" + amountH*1 + "&receiverH=" + receiverH + "&provideDateH=" + provideDateH + "&remarkH=" + remarkH
                                  + "&amountI=" + amountI*1 + "&receiverI=" + receiverI + "&provideDateI=" + provideDateI + "&remarkI=" + remarkI
                                  + "&amountJ=" + amountJ*1 + "&receiverJ=" + receiverJ + "&provideDateJ=" + provideDateJ + "&remarkJ=" + remarkJ
                                  + "&amountK=" + amountK*1 + "&receiverK=" + receiverK + "&provideDateK=" + provideDateK + "&remarkK=" + remarkK
                                  + "&amountL=" + amountL*1 + "&receiverL=" + receiverL + "&provideDateL=" + provideDateL + "&remarkL=" + remarkL
                                  + "&amountM=" + amountM*1 + "&receiverM=" + receiverM + "&provideDateM=" + provideDateM + "&remarkM=" + remarkM
                                  + "&amountN=" + amountN*1 + "&receiverN=" + receiverN + "&provideDateN=" + provideDateN + "&remarkN=" + remarkN
                                  + "&amountO=" + amountO*1 + "&receiverO=" + receiverO + "&provideDateO=" + provideDateO + "&remarkO=" + remarkO
                                  + "&amountP=" + amountP*1 + "&receiverP=" + receiverP + "&provideDateP=" + provideDateP + "&remarkP=" + remarkP;
               
               $.ajax({
					   type : "POST",
					   url : "provide_add.action",
					   data : query_params,
					   dataType : "text",
					   success : function(msg) {
					       if(msg == "1"){
					           alert("操作成功");
					       }else if(msg == "2"){
					           alert("该批次已经发放，不能重复发放");
					       }else{
					           alert("操作失败");
					       }
					   }
			   });
           });
       });
    </script>
  </head>
  
  <body>
      <div id="all">
          <div id="add-form">
              <table id="top">
                  <thead>
                      <tr>
                          <th colspan="6">设备信息</th>
                      </tr>
                  </thead>
                  <tbody>
                      <tr>
                          <td class="fr">批次编号:</td>
                          <td class="fl rl">
                              <select name="equ_no" id="equ_no">
                                  <option value="---">---请选择---</option>
                                  <c:forEach items="${equs}" var="equ">
                                      <option value="${equ.equipmentNo}">${equ.equipmentNo}</option>
                                  </c:forEach>
                              </select>
                          </td>
                          <td class="fr">设备名称:</td>
                          <td class="fl rl" id="equ_name"></td>
                          <td class="fr">规格型号:</td>
                          <td class="fl" id="equ_modle"></td>
                      </tr>
                      <tr>
                          <td class="fr">数量:</td>
                          <td class="fl rl" id="equ_amount"></td>
                          <td class="fr">启用日期:</td>
                          <td class="fl rl" id="equ_start_date"></td>
                          <td class="fr">使用年限:</td>
                          <td class="fl" id="equ_use_years"></td>
                      </tr>
                      <tr>
                          <td class="fr">区段号:</td>
                          <td colspan="5" class="fl" id="equ_area_code"></td>
                      </tr>
                  </tbody>
              </table>
              
              <table id="info">
                  <thead>
                      <tr class="tit">
                          <th colspan="5" class="title">发放明细</th>
                      </tr>
                      <tr class="fs">
                          <th class="field station">地点</th>
                          <th class="field amount">数量</th>
                          <th class="field receiver">领取人</th>
                          <th class="field provideDate">发放时间</th>
                          <th class="field remark" style="border: none;">备注</th>
                      </tr>
                  </thead>
                  <tbody>
                      <tr>
                          <td class="station" id="stationA">车站领导</td>
                          <td class="amount"><input type="text" id="amountA" value=""></td>
                          <td class="receiver"><input type="text" id="receiverA" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateA" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkA" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationB">党办室</td>
                          <td class="amount"><input type="text" id="amountB" value=""></td>
                          <td class="receiver"><input type="text" id="receiverB" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateB" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkB" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationC">办公室</td>
                          <td class="amount"><input type="text" id="amountC" value=""></td>
                          <td class="receiver"><input type="text" id="receiverC" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateC" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkC" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationD">人劳科</td>
                          <td class="amount"><input type="text" id="amountD" value=""></td>
                          <td class="receiver"><input type="text" id="receiverD" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateD" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkD" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationE">财务科</td>
                          <td class="amount"><input type="text" id="amountE" value=""></td>
                          <td class="receiver"><input type="text" id="receiverE" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateE" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkE" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationF">技术科</td>
                          <td class="amount"><input type="text" id="amountF" value=""></td>
                          <td class="receiver"><input type="text" id="receiverF" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateF" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkF" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationG">教育科</td>
                          <td class="amount"><input type="text" id="amountG" value=""></td>
                          <td class="receiver"><input type="text" id="receiverG" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateG" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkG" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationH">安全科</td>
                          <td class="amount"><input type="text" id="amountH" value=""></td>
                          <td class="receiver"><input type="text" id="receiverH" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateH" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkH" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationI">信息科</td>
                          <td class="amount"><input type="text" id="amountI" value=""></td>
                          <td class="receiver"><input type="text" id="receiverI" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateI" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkI" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationJ">货运科</td>
                          <td class="amount"><input type="text" id="amountJ" value=""></td>
                          <td class="receiver"><input type="text" id="receiverJ" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateJ" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkJ" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationK">调度车间</td>
                          <td class="amount"><input type="text" id="amountK" value=""></td>
                          <td class="receiver"><input type="text" id="receiverK" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateK" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkK" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationL">机务车间</td>
                          <td class="amount"><input type="text" id="amountL" value=""></td>
                          <td class="receiver"><input type="text" id="receiverL" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateL" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkL" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationM">运转一车间</td>
                          <td class="amount"><input type="text" id="amountM" value=""></td>
                          <td class="receiver"><input type="text" id="receiverM" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateM" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkM" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationN">运转二车间</td>
                          <td class="amount"><input type="text" id="amountN" value=""></td>
                          <td class="receiver"><input type="text" id="receiverN" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateN" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkN" value=""></td>
                      </tr>
                      <tr>
                          <td class="station" id="stationO">综合设备车间</td>
                          <td class="amount"><input type="text" id="amountO" value=""></td>
                          <td class="receiver"><input type="text" id="receiverO" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateO" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkO" value=""></td>
                      </tr>
                      <tr style="border-bottom: none;">
                          <td class="station" id="stationP">货检车间</td>
                          <td class="amount"><input type="text" id="amountP" value=""></td>
                          <td class="receiver"><input type="text" id="receiverP" value=""></td>
                          <td class="provideDate"><input type="text" id="provideDateP" onfocus="$(this).calendar()" maxlength="10" value=""></td>
                          <td class="remark"><input type="text" id="remarkP" value=""></td>
                      </tr>
                  </tbody>
              </table>
              <input type="button" value="保存" id="save_buton">
              <div style="clear: both;"></div>
          </div>
          <div style="clear: both;"></div>
      </div>
  </body>
</html>
