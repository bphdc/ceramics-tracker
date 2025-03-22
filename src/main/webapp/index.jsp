<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<html>

<body>
    <div class="container">
        <h1>Welcome to the Ceramics Tracker</h1>
        <p>Track your ceramic projects, glazes, and more with our easy-to-use tool.</p>

        <c:choose>
            <c:when test="${empty userName}">
                <p><a href="logIn">Log In</a></p>
            </c:when>
            <c:otherwise>
                <div class="nav">
                    <p>Welcome, <c:out value="${userName}" />!</p>
                    <a href="updateProfile">Update Profile</a>
                    <a href="viewAllProfiles">Member Directory</a>

                    <a href="addProject">Add a New Project</a>
                    <a href="searchProjects">Search Projects</a>

                    <a href="searchAI">Get inspo</a>

                    <c:if test="${userRole == 'admin'}">
                        <a href="glazeLibrary">Glaze Library</a>
                        <a href="tagLibrary">Tag Library</a>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
