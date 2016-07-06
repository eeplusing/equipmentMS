function colinMenu(opt, options){
    var menu_htm = "<div>";
    for(var index = 0; index < opt.length; index++){
        menu_htm += "<ul class=\"f_ul\" id=\"" + opt[index].id +"\"><li class=\"f_c_menu\"><span><font>+</font>" + opt[index].text + "</span></li><li class=\"s_c_menu\"><div><ul>";
        for(var i = 0; i <opt[index].children.length; i++ ){
            //menu_htm += "<li><a href=\"javascript:void(0);\" rev=\"" + opt[index].children[i].url + "\"><font style=\"background: url('css/equms/img/" + opt[index].children[i].icon + ".png') no-repeat -1px 4px;\">" + opt[index].children[i].text + "</font></a></li>";
            menu_htm += "<li><a href=\"javascript:void(0);\" rev=\"" + opt[index].children[i].url + "\"><font>" + opt[index].children[i].text + "</font></a></li>";
        }
        menu_htm += "</ul><div style=\"clear: both;\"></div></div></li></ul>";
    }
    menu_htm += "<ul class=\"f_ul\" id=\"123\"><li class=\"f_c_menu\"><span><font>+</font>地理系统</span></li><li class=\"s_c_menu\"><div><ul>";
    menu_htm += "<li><a href=\"http://svr-ems-lzbz:8220/\" target=\"_blank\"><font>地理信息</font></a></li>";
    menu_htm += "</ul><div style=\"clear: both;\"></div></div></li></ul>";
    menu_htm += "<div style=\"clear: both;\"></div></div>";
    $("#" + options.id).html(menu_htm);
    menuInitialize(options.callback);
}

function menuInitialize(callback){
    $("ul.f_ul:first").css("background", "#FFF");
    $("li.s_c_menu:first").css("display", "block");
    $("li.f_c_menu:first").children("span").children("font").text("-");
    $("li.f_c_menu").each(function(){
        $(this).click(function(){
            if($(this).next(".s_c_menu").css("display") == "none"){
                $("li.s_c_menu").each(function(){
	                $(this).slideUp(300, function(){
	                    $(this).parent().css("background", "#DFE7FE");
	                    $(this).prev(".f_c_menu").children("span").children("font").text("+");
	                });
	            });
	            $(this).next(".s_c_menu").slideDown(300);
	            $(this).children("span").children("font").text("-");
	            $(this).parent().css("background", "#FFF");
            }else{
                $(this).next(".s_c_menu").slideUp(300, function(){
                    $(this).parent().css("background", "#DFE7FE");
                });
                $(this).children("span").children("font").text("+");
            }
        });
    });
    
    $("#c-menu a").each(function(){
        $(this).click(function(){
        	if($(this).attr("href") != "javascript:void(0);"){
        	    $(this).click();
        	}else if(typeof(callback) == "function"){
				callback($(this).attr("rev"));
		    }else{
		    	alert("Method does not exist!");
		    }
        });
    });
}