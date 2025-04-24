<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>
<html>

<body>
    <div class="container">
        <h1>Add a New Glaze</h1>

        <c:choose>
            <c:when test="${empty userName}">
                <p>You must be logged in to create a glaze. <a href="login.jsp">Log in</a></p>
            </c:when>
            <c:otherwise>
                <form action="addGlaze" method="post">
                    <div class="form-group">
                        <label for="name">Glaze Name:</label>
                        <input type="text" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Glaze Description:</label>
                        <textarea id="description" name="description" rows="4" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Glaze Type:</label><br>
                        <input type="radio" id="underglaze" name="glazeType" value="underglaze" required>
                        <label for="underglaze">Underglaze</label>
                        <input type="radio" id="overglaze" name="glazeType" value="overglaze" required>
                        <label for="overglaze">Overglaze</label>
                    </div>
                    <button type="submit">Add Glaze</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
