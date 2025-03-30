<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>


<html>


<body>
<h1>Glaze Library</h1>

<a href="addGlaze">Add a New Glaze to Library</a>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="glaze" items="${glazes}">
        <tr>
            <td>${glaze.glazeId}</td>
            <td>${glaze.name}</td>
            <td>${glaze.description}</td>
            <td>${glaze.type}</td>
            <td>
                <a href="editGlaze?glazeId=${glaze.glazeId}">Edit</a>
                <a href="deleteGlaze?glazeId=${glaze.glazeId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
