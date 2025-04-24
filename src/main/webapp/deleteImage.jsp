<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<body>
    <h2>Confirm Image Deletion</h2>
    <p>Are you sure you want to delete your project image?</p>
    <form action="deleteImage" method="post">
        <input type="hidden" name="imageId" value="${image.imageId}">
        <input type="hidden" name="projectId" value="${project.projectId}">
        <input type="submit" value="Delete">
        <a href="viewProject?${project.projectId}"><button type="button">Cancel</button></a>
    </form>
</body>

</html>
