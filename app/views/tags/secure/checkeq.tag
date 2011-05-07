#{if session.username && controllers.Secure.Security.invoke("checkeq", _arg)}
    #{doBody /}
#{/if}