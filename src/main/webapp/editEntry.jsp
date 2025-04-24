<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>
<html>

<body>
    <div class="container">
        <h1>Edit Tag</h1>

        <c:choose>
            <c:when test="${empty userName}">
                <p>You must be logged in to edit an entry. <a href="index.jsp">Log in</a></p>
            </c:when>
            <c:otherwise>
                <form action="editEntry" method="post">
                    <div class="form-group">
                        <label for="entryText">Edit blog entry:</label>
                        <textarea id="entryText" name="entryText" rows="3" required>${entry.entryText}</textarea>
                    </div>
                    <input type="hidden" name="projectId" value="${project.projectId}">
                    <button type="submit">Edit Entry</button>
                    <a href="viewProject?${project.projectId}"><button type="button">Cancel</button></a>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
