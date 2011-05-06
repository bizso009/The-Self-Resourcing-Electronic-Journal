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
		$('#author'+newAuthors+' .left').append('		    		<input type="radio" name="author" value="'+newAuthors+'">');
		$('#author'+newAuthors+' .left').append('		    	</span>');
		$('#author'+newAuthors).append('		    	<span class="right">');
		$('#author'+newAuthors+' .right').append('		    			<input type="hidden" name="authNumber" value="'+newAuthors+'">');
		$('#author'+newAuthors+' .right').append('		    			<input type="text" name="firstName" value="First Name">');
		$('#author'+newAuthors+' .right').append('		    			<input type="text" name="lastName" value="Last Name">');
		$('#author'+newAuthors+' .right').append('		    			<input type="text" name="email" value="Email Address">');
		$('#author'+newAuthors+' .right').append('		    			<input type="text" name="affil" value="Affiliation">');
		$('#author'+newAuthors+' .right').append('		    			<input type="button" class="button blue" id="remove" onClick="removeAuthor(\''+newAuthors+'\');" value="Remove">');
		$('#author'+newAuthors+' .right').append('		    	</span>');
		$('#authors').append('		    </div>');
		$('#authors').append('		    <div class="spacingMedium" id="spacing'+newAuthors+'"></div>');		
		$('#numberAuthors').val(newAuthors);
	});
	
	var data = $( 'textarea.editor' );
	
	$('.submitForm').submit(function()
	{
		var editor = CKEDITOR.instances.editor_kama;
		var summary = editor.getData();
		$('#authorError').html("");
		$('#titleError').html("");
		$('#keywordError').html("");
		$('#summaryError').html("");
		$('#fileError').html("");
		var values = $('.submitForm').serialize();
		var newValues = values.split("&");
		var errors = 0;
		var authorErrors = 0;
		for(var field in newValues)
		{
			var pair = newValues[field];
			var pairArray = pair.split("=");
			var key = "";
			var value = "";
			key = pairArray[0];
			value = pairArray[1];
			var html = "";
			if(key == "firstName")
			{
				if(value == "" || value == "First+Name")
				{
					errors = 1;
					authorErrors = 1;
				}
			}
			
			if(key == "lastName")
			{
				if(value == "" || value == "Last+Name")
				{
					errors = 1;
					authorErrors = 1;
				}
			}
			
			if(key == "email")
			{
				if(value == "" || value == "Email+Address")
				{
					errors = 1;
					authorErrors = 1;
				}
			}
			
			if(key == "affiliation")
			{
				if(value == "" || value=="Affiliation")
				{
					errors = 1;
					authorErrors = 1;
				}
			}
			
			if(key == "title")
			{
				if(value == "")
				{
					errors = 1;
					$('#titleError').html("Required Field");
				}
			}
			
			if(key=="keywords")
			{
				if(value=="")
				{
					errors = 1;
					$('#keywordError').html("Required Field");
				}
			}
		}
		
		if(summary == "")
		{
			errors = 1;
			$('#summaryError').html("Summary cannot be empty.")
		}
		
		var file = $('#file').val();
		if(file == "")
		{
			errors = 1;
			$('#fileError').html("Please Choose a File");
		}
		
		if(errors == 1)
		{
			if(authorErrors == 1)
			{
				$('#authorError').html("One of the above author details is invalid. Please correct this before continuing.");
			}
			$('.error').slideDown("slow");
			return false;
		}else
		{
		
		}
	});
	
	
});

function removeAuthor(author)
{
	var authorId = '#author'+author;
	var spacingId = '#spacing'+author
	$(authorId).remove();
	$(spacingId).remove();
}



