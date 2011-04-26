<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://journal.shef.ac.uk/tags" prefix="jt" %>
<%
	ArrayList<String> names = (ArrayList<String>)request.getAttribute("names");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test page</title>
</head>
<body>
<center>Hello test page.</center>
<br />
<%= request.getAttribute("servletName").toString() %>
<br />
<br />
<center>
<% 
	for (int i=0; i<names.size(); i++)
	{
%><%= names.get(i) %><br />
<%
	}
%>
</center>
<jt:simpleTag/>
</body>
</html>