$(function () {
	$(".but_b").mouseover(function () {
		$(this).addClass("but_b02");
	}).mouseout(function () {
		$(this).removeClass("but_b02");
	});
	
	if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
		$(".gridOut,.gridOutLow,.gridOut25,.gridOut170,.gridOutLowDown,.gridOutTabs").css("border-right","1px solid #99BBE8");
		$("#tab_kq").css({
			"border-right":"1px solid #99BBE8",
			"border-bottom":"1px solid #99BBE8",
		});
	}
	
	$(".commonAdd input[type='radio']").css({
		'width':'20px',
		'height':'25px',
		'padding-top':'10px'
	});
});

