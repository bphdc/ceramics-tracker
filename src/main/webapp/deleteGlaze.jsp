<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>


<html>

<body>
    <h2>Confirm Glaze Deletion</h2>
    <p>Are you sure you want to delete <strong>${glaze.name}</strong>? User projects may be effected.</p>
    <form action="deleteGlaze" method="post">
        <input type="hidden" name="glazeId" value="${glaze.glazeId}">
        <input type="submit" value="Delete">
        <a href="glazeLibrary"><button type="button">Cancel</button></a>
    </form>
</body>

</html>
