<div id="chat${_convID}"></div>
<script type="text/javascript" />
function setup${_convID}()
{
	if (${_readOnly}!=1)
	{            
    $('#sendMsgForm${_convID}').ajaxForm(function() {
     	$('#sendMsgForm${_convID}').find('textarea[name="msg"]').val("");
       	alert('Your message was sent successfully.');
    });
    //refresh${_convID}();
    }
}
$('#chat${_convID}').load('/chat/view?convID=${_convID}&readOnly=${_readOnly}',
function ()
{
	setTimeout(setup${_convID}(),100);	
});
</script>
<script type="text/javascript" src="/chat/refreshScript?convID=${_convID}&readOnly=${_readOnly}"></script>