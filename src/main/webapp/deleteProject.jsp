<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<body>
    <h2>Confirm Project Deletion</h2>
    <p>Are you sure you want to delete <strong>${project.name}</strong>?</p>
    <form action="deleteProject" method="post">
        <input type="hidden" name="projectId" value="${project.projectId}">
        <input type="submit" value="Delete">
        <a href="viewProject.jsp?projectId=${project.projectId}"><button type="button">Cancel</button></a>
    </form>
</body>

</html>
