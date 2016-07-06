$(function(){
	$("[name='easyTip']").each(function(){
		$(this).addClass("onShow");
	});
	$("[reg]").blur(function(){
		validate($(this));
	});
	
	$("[reg]").click(function(){
		$(this).nextAll("[name='easyTip']").eq(0).removeClass();
		
		var tipObj = $(this).nextAll("[name='easyTip']").eq(0);
		if(strTrim(tipObj[0].innerHTML).length ==0){
	    	tipObj[0].innerHTML = tipObj[0].title+"不能为空！";
	    }
		$(this).nextAll("[name='easyTip']").eq(0).addClass("onFocus");				   
	});
});

function strTrim(str) {
    if (!isObj(str)) {
        return 'undefined';
    }
    else {
        str = str.replace(/^\s+|\s+$/g, '');
        return str;
    }
}

function isObj(str) {
    if (str == null || typeof (str) == 'undefined') {
        return false;
    }
    else {
        return true;
    }
}

function validate(obj){
  if(typeof (obj) != 'undefined'){
	var reg = new RegExp(obj.attr("reg"));
	var objValue = obj.attr("value");
	var tipObj = obj.nextAll("[name='easyTip']").eq(0);
	tipObj.removeClass();
	if(strTrim(objValue).length==0){//非空
	   tipObj[0].innerHTML = tipObj[0].title+"不能为空！";
		tipObj.addClass("onError");
		return false;
	}
	else{
	    tipObj[0].innerHTML = "";
		tipObj.addClass("onCorrect");
		return true;
	}
	
	if(!reg.test(objValue)){
	    tipObj[0].innerHTML = tipObj[0].title+"不能为空！";
		tipObj.addClass("onError");
		return false;
	}else{
	    tipObj[0].innerHTML = "";
		tipObj.addClass("onCorrect");
		return true;
	}
	}
	else{
		return false;
	}
}