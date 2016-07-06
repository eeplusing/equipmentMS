$(function(){
    $("input.file-colinUI").each(function(){
        var _fileHTML = "<div class=\"colin-file-ui\"><input type=\"text\" readonly=\"readonly\" class=\"colin-file-text\"><input type=\"button\" class=\"colin-file-button\" value=\"浏览\">";
        _fileHTML += "<input type=\"file\" class=\"colin-file-authentic\" name=\"" + $(this).attr("name") + "\" id=\"" + $(this).attr("id") + "\"/></div>";
        $(this).prev().after(_fileHTML);
        $(this).remove();
    });
    
    $(".colin-file-text").focus(function(){
    	var name = $(this).next(".colin-file-button").next(".colin-file-authentic").attr("name");
    	var id = $(this).next(".colin-file-button").next(".colin-file-authentic").attr("id");
        _createElement($(this), name, id);
    });
    $(".colin-file-button").click(function(){
    	var name = $(this).next(".colin-file-authentic").attr("name");
    	var id = $(this).next(".colin-file-authentic").attr("id");
    	_createElement($(this), name, id);
    });
});

function _createElement(obj, name, id){
	var parent = $(obj).parent();
	var _fileHTML = "<input type=\"text\" readonly=\"readonly\" class=\"colin-file-text\"><input type=\"button\" class=\"colin-file-button\" value=\"浏览\">";
	    _fileHTML += "<input type=\"file\" class=\"colin-file-authentic\" name=\"" + name + "\" id=\"" + id + "\"/>";
    $(parent).html(_fileHTML);
    
    $(parent).children(".colin-file-authentic").change(function(){
        $(this).parent().children(".colin-file-text").val($(this).val());
    });
    
    $(parent).children(".colin-file-text").focus(function(){
    	_createElement($(this), name, id);
    });
    
    $(parent).children(".colin-file-button").click(function(){
    	_createElement($(this), name, id);
    });
    
    $(parent).children(".colin-file-authentic").click();
}