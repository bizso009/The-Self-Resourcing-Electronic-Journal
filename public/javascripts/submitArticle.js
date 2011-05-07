/**
 * Java Script for submit article
 */

function clearClick(){
	$("input[type=text]").focus(function() {
		if(this.value == this.defaultValue) $(this).val("");
	}).blur(function() {
		if(this.value == "") $(this).val(this.defaultValue);
	});
}

$(document).ready(function(){
	clearClick();
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
		$('#author'+newAuthors+' .right').append('		    			<input type="text" name="affiliation" value="Affiliation">');
		$('#author'+newAuthors+' .right').append('		    			<input type="button" class="button blue" id="remove" onClick="removeAuthor(\''+newAuthors+'\');" value="Remove">');
		$('#author'+newAuthors+' .right').append('		    	</span>');
		$('#authors').append('		    </div>');
		$('#authors').append('		    <div class="spacingMedium" id="spacing'+newAuthors+'"></div>');		
		$('#numberAuthors').val(newAuthors);
		
		clearClick();
	});
	
	var data = $( 'textarea.editor' );
	
	$('.submitForm').submit(function()
	{
		$('#authorError').html("");
		$('#titleError').html("");
		$('#keywordError').html("");
		$('#summaryError').html("");
		$('#fileError').html("");
		
		var errors = 0;
		var authorErrors = 0;
		var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
		
		$("#authors input[type=text]").each(function (){
			var ivalue = $(this).val();
			if(ivalue == "" || ivalue == this.defaultValue){
				errors = 1;
				authorErrors = 1;
			}
			if(this.name == "email" && !pattern.test(ivalue)){
				errors = 1;
				authorErrors = 1;
			}
		});
		
		$("#form #form input[type=text]").each(function (){
			var ivalue = $(this).val();
			if(ivalue == "" || ivalue == this.defaultValue){
				if(this.name == "title"){
					$('#titleError').html("Required Field");
					errors = 1;
				}
				if(this.name == "keywords"){
					$('#keywordError').html("Required Field");
					errors = 1;
				}
			}
		});
		
		var editor = CKEDITOR.instances.editor_kama;
		var summary = editor.getData();
		
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
			return true;
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



