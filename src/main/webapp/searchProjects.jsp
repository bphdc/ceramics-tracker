<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>

<html>

<body>
<div class="container">
    <h2>Search Projects</h2>

    <!-- Button to show user's own projects -->
    <form action="SearchProjectsServlet" method="get">
        <input type="hidden" name="searchType" value="myProjects">
        <button type="submit">Show My Projects</button>
    </form>

    <!-- Button to show all projects -->
    <form action="SearchProjectsServlet" method="get">
        <input type="hidden" name="searchType" value="allProjects">
        <button type="submit">Show All Projects</button>
    </form>

    <!-- Search by Tags -->
    <form action="SearchProjectsServlet" method="get">
        <input type="hidden" name="searchType" value="byTag">
        <label for="tag">Search by Tag:</label>
        <select name="tag" id="tag">
            <c:forEach var="tag" items="${availableTags}">
                <option value="${tag}">${tag}</option>
            </c:forEach>
        </select>
        <button type="submit">Search</button>
    </form>

    <!-- Search by Keyword -->
    <form action="SearchProjectsServlet" method="get">
        <input type="hidden" name="searchType" value="byKeyword">
        <label for="keyword">Search by Keyword:</label>
        <input type="text" id="keyword" name="keyword" placeholder="Enter keyword">
        <button type="submit">Search</button>
    </form>
</div>
</body>
</html>
