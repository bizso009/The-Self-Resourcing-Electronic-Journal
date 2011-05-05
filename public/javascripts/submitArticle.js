/**
 * Java Script for submit article
 */

$(document).ready(function(){
	
	$('#addAuthor').click(function()
	{
		var authors = parseInt($('#numberAuthors').val());
		var newAuthors = authors+1;
		$('#authors').append(
				'<div class="formHeader" id="author'+newAuthors+'">'+
		    	'<span class="left">'+
		    		'<input type="radio" name="author[]">'+
		    	'</span>'+
		    	'<span class="right">'+
		    		'<span>'+
		    			'<input type="text" name="firstName[]" value="First Name">'+
		    		'</span>'+
		    		'<span>'+
		    			'<input type="text" name="lastName[]" value="Last Name">'+
		    		'</span>'+
		    		'<span>'+
		    			'<input type="text" name="email[]" value="Email Address">'+
		    		'</span>'+
		    		'<span>'+
		    			'<input type="button" class="button blue" id="remove" value="Remove">'+
		    		'</span>'+
		    	'</span>'+
		    '</div>'+
		    '<div class="spacingMedium"></div>'
		);
		$('#numberAuthors').val(newAuthors);
	});
	
	$('#remove').click(function()
	{
		var authorId = $(this).parent().parent().attr("id");
		alert(authorId);
		$('#'+authorId).remove();
	});
});