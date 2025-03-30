<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>

<html>

<body>
    <div class="container">
        <h1>Add a New Tag</h1>

        <c:choose>
            <c:when test="${empty userName}">
                <p>You must be logged in to create a tag. <a href="login.jsp">Log in</a></p>
            </c:when>
            <c:otherwise>
                <form action="addTag" method="post">
                    <div class="form-group">
                        <label for="name">Tag Name:</label>
                        <input type="text" id="name" name="name" required>
                    </div>
                    <button type="submit">Add Tag</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
