<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>


<html>

<body>
<h2>Confirm Tag Deletion</h2>
<p>Are you sure you want to delete <strong>${tag.name}</strong>? User projects may be effected.</p>
<form action="deleteTag" method="post">
    <input type="hidden" name="tagId" value="${tag.tagId}">
    <input type="submit" value="Delete">
    <a href="tagLibrary"><button type="button">Cancel</button></a>
</form>
</body>

</html>
