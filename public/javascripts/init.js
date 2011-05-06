$(document).ready(function() {
	jQuery.ajaxSetup({
        dataFilter: function(data, dataType) {
            return data;  
        }
    });
	
	$("input").focus(function () {
		if($(this).val() == this.defaultValue) $(this).val("");
	}).blur(function(){
		if($(this).val() == "") $(this).val(this.defaultValue);
	});
});

function showMessage(msg) {
	$("#messagestrip").html(msg).fadeIn(2000).fadeOut(400);
}

function showError(msg) {
	$("#errorstrip").html(msg).fadeIn(2000).fadeOut(400);
}