<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>

<html>

<body>
    <div class="container">
        <h1>Create a New Project</h1>

        <c:choose>
            <c:when test="${empty userName}">
                <p>You must be logged in to create a project. <a href="login.jsp">Log in</a></p>
            </c:when>
            <c:otherwise>
                <form action="addProject" method="post">
                    <div class="form-group">
                        <label for="name">Project Name:</label>
                        <input type="text" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Project Description:</label>
                        <textarea id="description" name="description" rows="4" required></textarea>
                    </div>
                    <button type="submit">Create Project</button>
                </form>

                <c:if test="${not empty projectId}">
                    <h2>Next Steps</h2>
                    <p>Your project has been created! What would you like to do next?</p>
                    <ul>
                        <li><a href="addEntry?projectId=${projectId}">Add an Entry</a></li>
                        <li><a href="uploadImage?projectId=${projectId}">Upload Images</a></li>
                    </ul>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
