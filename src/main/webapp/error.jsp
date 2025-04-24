<%@include file="head.jsp"%>
<%@include file="sidebar.jsp"%>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Page Not Found</title>
</head>
<body>
<div class="container">
<h2>Whoops! Something went wrong</h2>
<p>${message}</p>
<a href="index.jsp">Go Home</a>
</div>
</body>
</html>