<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>
<html>

<body>
    <div class="container">
        <h1>Edit Tag</h1>

        <c:choose>
            <c:when test="${empty userName}">
                <p>You must be logged in to edit a tag. <a href="login.jsp">Log in</a></p>
            </c:when>
            <c:otherwise>
                <form action="editTag" method="post">
                    <div class="form-group">
                        <label for="name">Tag Name:</label>
                        <input type="text" id="name" name="name" value="${tag.name}" required>
                    </div>
                    <button type="submit">Edit Tag</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
