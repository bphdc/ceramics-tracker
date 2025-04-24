<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<body>
    <h2>Confirm Entry Deletion</h2>
    <p>Are you sure you want to delete your project entry?</p>
    <form action="deleteEntry" method="post">
        <input type="hidden" name="entryId" value="${entry.id}">
        <input type="hidden" name="projectId" value="${project.projectId}">
        <input type="submit" value="Delete">
        <a href="viewProject?${project.projectId}"><button type="button">Cancel</button></a>
    </form>
</body>

</html>
