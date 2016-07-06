<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色授权</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/t_style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/themes/icon.css"/>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
    <style type="text/css">
        body{ color:#666;}
        .fm{font-size: 18px; font-weight: bolder; color: #333;}
        .sm{font-size: 16px;
            margin-left: 20px;
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
        
        #loading{
	       display: none;
	       position: absolute;
	       width: 105px;
	       height: 104px;
	       z-index: 9;
	       background: url('${pageContext.request.contextPath}/images/load_11.gif') no-repeat;
	    }
    </style>
    
    <script type="text/javascript">
       var bh = 0;
        var bw = 0;
        $(function(){
            bh = document.body.scrollHeight;//获取当前窗体的总高度
            bw = document.body.scrollWidth;
         
            var has_right = $("#hasRight").html();
            if(has_right.indexOf("[]") > 0){
                $("#right input").each(function(){
                  var value = $(this).val();
                    if(has_right.indexOf(value + "[]") == 0 || has_right.indexOf("[]" + value + "[]") > 0){
                        $(this).attr('checked','true');
                    }
                });
            }
            
            $(".r_branch").each(function(){
                $(this).click(function(){
                    var _fchecked = $(this).attr('checked');
                    $(this).parent().next().find("input").each(function(){
                        if(_fchecked == "checked"){
                            $(this).attr('checked', true);
                        }else{
                            $(this).attr('checked', false);
                        }
                        
                    });
                });
            });
        });
        function win_close(){
            window.close();
        }
        
        function save(){
             show_loading();
             var rids = "";
             $(".r_leaf").each(function(){
                 if($(this).attr('checked') == "checked"){
                     rids += $(this).val() + ";";
                 }
             });
             if(rids.length > 0){
                 $.ajax({
					 url:"right_save.action",
					 type:"post", 
					 cache:false,
					 data:"rightIds=" + rids + "&roleId=" + $("#roleId").val(),
					 success:function(msg){
						 close_loading();
						 $.messager.alert('提示', msg);
					 }
			     });
             }
        }
        
        function show_fg(){
	        $("#fullbg").css({
	            height: bh,
	            width: bw, 
	            display: "block"
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
	        $("#fullbg").hide();
	        $("#loading").hide();
	    }
    </script>
  </head>
  <body style="width: 100%; height:100%; margin: 0px; padding: 0px; overflow: hidden;">
      <div id="right" style="width: 830px;  margin: 20px auto; line-height: 200%; padding: 0px; border:0px solid #000;">
          <input type="hidden" value="${roleId}" id="roleId">
          <c:forEach items="${rightMap}" var="fm">
               <ul>
                   <li class="fm"><input class="r_branch" type="checkbox">${fm.key}</li>
                   <li>
                       <ul>
                           <c:forEach items="${fm.value}" var="sm">
                               <li class="sm">
                                   <span style="font-size: 16px;">${sm.key}:</span>
                                   <c:forEach items="${sm.value}" var="right">
                                       <c:if test="${right.name == '设备关联文档'}">
                                           <input class="r_leaf" type="checkbox" value="${right.rightId}" style="margin-left: 72px;">
                                           <span style="font-size: 14px;">${right.name}</span>
                                       </c:if>
                                       <c:if test="${right.name != '设备关联文档'}">
                                           <input class="r_leaf" type="checkbox" value="${right.rightId}">
                                           <span style="font-size: 14px;">${right.name}</span>
                                       </c:if>
                                       
                                   </c:forEach>
                                   <div style="clear: both;"></div>
                               </li>
                           </c:forEach>
                       </ul>
                       <div style="clear: both;"></div>
                   </li>
               </ul>
          </c:forEach>
          <div style="clear: both;"></div>
      </div>
      <div style="width: 180px; margin: 20px auto;">
          <a href="javascript:void(0)" id="savebut" class="easyui-linkbutton"  iconCls="icon-save" onclick="save();">保存</a>
  	      <a href="javascript:void(0)" id="savebut" class="easyui-linkbutton"  iconCls="icon-cancel" onclick="win_close();" style="margin-left: 15px;">取消</a>
      </div>
      
      <div id="hasRight" style="display : none;">${hasRight}</div>
      
      <div id="fullbg"></div>
      <div id="loading"></div>
  </body>
</html>
