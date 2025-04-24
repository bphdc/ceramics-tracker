<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<body>
<div class="container">
    <h2>Search Results</h2>

    <c:choose>
        <c:when test="${empty projects}">
            <p>No projects found.</p>
        </c:when>
        <c:otherwise>
            <ul class="project-list">
                <c:forEach var="project" items="${projects}">
                    <li>
                        <a href="viewProject?projectId=${project.projectId}">${project.name}</a>
                        <p>${project.description}</p>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>

    <br>
    <a href="searchProjects">Back to Search</a>
</div>
</body>
</html>
