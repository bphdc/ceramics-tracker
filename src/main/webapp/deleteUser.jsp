<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<body>
    <h2>Confirm User Profile Deletion</h2>
    <p>Are you sure you want to delete your profile? Your projects and pictures will also be deleted</p>
    <form action="deleteUser" method="post">
        <input type="hidden" name="userId" value="${user.id}">
        <input type="submit" value="Delete">
        <a href="viewProfile?id=${user.id}"><button type="button">Cancel</button></a>
    </form>
</body>

</html>
