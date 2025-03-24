<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>


<html>

<body>
    <div class="container">
        <h2>${project.name}</h2>
        <p>${project.description}</p>

        <c:if test="${project.getProjectEntries !=null}">
        <h3>Project Entries</h3>
        <ul>
            <c:forEach var="entry" items="${project.getProjectEntries}">
                <li>${entry.getCreatedAt} - ${entry.getEntryText}</li>
            </c:forEach>
        </ul>
        </c:if>

        <c:if test="${project.getImages !=null}">
        <h3>Image Gallery</h3>
        <div class="image-gallery">
            <c:forEach var="image" items="${project.getImages}">
                <img src="${image.url}" alt="Project Image">
            </c:forEach>
        </div>
        </c:if>

        <br>
        <a href="searchProjects">Back to Search</a>

        <c:if test="${user == project.getUser}">
            <br>
            <a href="editProject?projectId=${project.getProjectId}">Edit Project</a>
            <br>
            <a href="deleteProject?projectId=${project.getProjectId}">Delete Project</a>
        </c:if>
    </div>
</body>
</html>
