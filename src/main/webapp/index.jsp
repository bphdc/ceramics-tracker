<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<html>
<body>
<h2>Hello World!</h2>

<c:choose>
    <c:when test="${empty userName}">
        <a href = "logIn">Log in</a>
    </c:when>
    <c:otherwise>
        <h3>Welcome ${userName}</h3>
        <a href = "addProject">Add a New Project</a>
    </c:otherwise>
</c:choose>



</body>
</html>
