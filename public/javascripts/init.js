$(document).ready(function() {
	jQuery.ajaxSetup({
        dataFilter: function(data, dataType) {
            return data;  
        }
    });
});

function showMessage(msg) {
	$("#messagestrip").html(msg).fadeIn(2000).fadeOut(400);
}

function showError(msg) {
	$("#errorstrip").html(msg).fadeIn(2000).fadeOut(400);
}