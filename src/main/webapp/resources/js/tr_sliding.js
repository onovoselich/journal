$(document).ready(function(){

    $('.none').css('display', 'none');
	$('.str-down').click(function(event){
		$(event.target).parents('tr').next('tr').children('td.none').slideToggle(1000,"swing");
		$(event.target).toggleClass("up");
	});
});
