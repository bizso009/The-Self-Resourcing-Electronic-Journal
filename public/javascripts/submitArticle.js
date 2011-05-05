/**
 * Java Script for submit article
 */

$(document).ready(function(){
	
	$('#addAuthor').click(function()
	{
		var authors = parseInt($('#numberAuthors').val());
		var newAuthors = authors+1;
		$('#authors').append('		    <div class="formHeader" id="author'+newAuthors+'">');
		$('#author'+newAuthors).append('		    	<span class="left">');
		$('#author'+newAuthors+' .left').append('		    		<input type="radio" name="author[]">');
		$('#author'+newAuthors+' .left').append('		    	</span>');
		$('#author'+newAuthors).append('		    	<span class="right">');
		$('#author'+newAuthors+' .right').append('		    			<input type="text" name="firstName[]" value="First Name">');
		$('#author'+newAuthors+' .right').append('		    			<input type="text" name="lastName[]" value="Last Name">');
		$('#author'+newAuthors+' .right').append('		    			<input type="text" name="email[]" value="Email Address">');
		$('#author'+newAuthors+' .right').append('		    			<input type="button" class="button blue" id="remove" onClick="removeAuthor(\'author'+newAuthors+'\');" value="Remove">');
		$('#author'+newAuthors+' .right').append('		    	</span>');
		$('#authors').append('		    </div>');
		$('#authors').append('		    <div class="spacingMedium"></div>');		
		$('#numberAuthors').val(newAuthors);
	});
	
	
});

function removeAuthor(author)
{
	var id = '#'+author;
	alert(id);
	$(id).remove();
}


