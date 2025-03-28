<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>


<html>


<body>
<h1>Tag Library</h1>

<a href="addTag">Add a New Tag to Library</a>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tag" items="${tags}">
        <tr>
            <td>${tag.tagId}</td>
            <td>${tag.name}</td>
            <td>
                <a href="editTag?tagId=${tag.id}">Edit</a>
                <a href="deleteTag?tagId=${tag.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
