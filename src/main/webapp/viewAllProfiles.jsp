<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="sidebar.jsp"%>

<html>


<body>

<h2>Member Directory</h2>

<div class="directory">
    <c:choose>
        <c:when test="${not empty users}">
            <c:forEach var="user" items="${users}">
                <div class="user">
                    <a href="viewProfile?userId=${user.id}">
                        <c:choose>
                            <c:when test="${empty user.profilePicture}">
                                <img src="images/noprofilepicture.png" alt="Default Profile Picture" width="100">
                            </c:when>
                            <c:otherwise>
                                <img src="${user.profilePicture}" alt="Profile Picture" width="100">
                            </c:otherwise>
                        </c:choose>
                    </a>
                    <br>
                    <a href="viewProfile?userId=${user.id}">${user.username}</a>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No users found.</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
