<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<body>
    <div class="container">
        <h2>${project.name}</h2>
        <p>${project.description}</p>

        <c:if test="${not empty projectEntries}">
        <h3>Project Entries</h3>
        <ul>
            <c:forEach var="entry" items="${projectEntries}">
                <li>${entry.createdAt} - ${entry.entryText}</li>
            </c:forEach>
        </ul>
        </c:if>

        <c:if test="${not empty projectImages}">
        <h3>Image Gallery</h3>
        <div class="image-gallery">
            <c:forEach var="image" items="${projectImages}">
                <a href="${image.imageUrl}" target="_blank">
                    <img src="${image.imageUrl}" alt="Project Image" width="150" height="150" style="margin: 5px; border-radius: 5px;">
                </a>
            </c:forEach>
        </div>
        </c:if>

        <br>
        <a href="searchProjects">Back to Search</a>

        <c:if test="${loggedInUser.id == projectUser.id}">
            <br>
            <a href="editProject?projectId=${project.projectId}">Edit Project</a>
            <br>
            <a href="deleteProject?projectId=${project.projectId}">Delete Project</a>
        </c:if>
    </div>
</body>
</html>
