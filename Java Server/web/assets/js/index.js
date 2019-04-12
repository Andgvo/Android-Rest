$(document).ready(function(){
	$("#sticker").sticky({topSpacing:0, zIndex:10});
});
$('#sticker').on('sticky-start', function() { $('.nav-scroller').addClass('navbar-shadow'); });
$('#sticker').on('sticky-end', function() { $('.nav-scroller').removeClass('navbar-shadow'); });