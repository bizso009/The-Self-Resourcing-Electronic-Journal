$(document).ready(function() {
	jQuery.ajaxSetup({
        dataFilter: function(data, dataType) {
            return data;  
        }
    });
	
	$("#browseButton").click(function(){
		browse();
	});
});