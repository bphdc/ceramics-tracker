<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>


<html>

<body>
    <div class="container">
        <h2>${project.title}</h2>
        <p>${project.description}</p>

        <h3>Project Entries</h3>
        <ul>
            <c:forEach var="entry" items="${project.entries}">
                <li>${entry.date} - ${entry.notes}</li>
            </c:forEach>
        </ul>

        <h3>Image Gallery</h3>
        <div class="image-gallery">
            <c:forEach var="image" items="${project.images}">
                <img src="${image.url}" alt="Project Image">
            </c:forEach>
        </div>

        <br>
        <a href="searchProjects.jsp">Back to Search</a>
    </div>
</body>
</html>
