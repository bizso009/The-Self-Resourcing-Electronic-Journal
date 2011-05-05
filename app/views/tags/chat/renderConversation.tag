<div id="chat${_arg}"></div>
<script type="text/javascript" />
function setup${_arg}()
{            
    $('#sendMsgForm${_arg}').ajaxForm(function() {
     	$('#sendMsgForm${_arg}').find('textarea[name="msg"]').val("");
       	alert('Your message was sent successfully.');
    });
    //refresh${_arg}();
}
$('#chat${_arg}').load('/chat/view?convID=${_arg}',
function ()
{
	setTimeout(setup${_arg}(),100);	
});
</script>
<script type="text/javascript" src="/chat/refreshScript?convID=${_arg}"></script>