<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>
<html>

<body>
<div class="container">
    <h2>Search Projects</h2>

    <form action="searchProjects" method="get">
        <input type="hidden" name="searchType" value="myProjects">
        <button type="submit">Show My Projects</button>
    </form>

    <form action="searchProjects" method="get">
        <input type="hidden" name="searchType" value="allProjects">
        <button type="submit">Show All Projects</button>
    </form>

    <form action="searchProjects" method="get">
        <input type="hidden" name="searchType" value="byTag">
        <label for="tag">Search by Tag:</label>
        <select name="tag" id="tag">
            <c:forEach var="tag" items="${availableTags}">
                <option value="${tag}">${tag}</option>
            </c:forEach>
        </select>
        <button type="submit">Search</button>
    </form>

    <form action="searchProjects" method="get">
        <input type="hidden" name="searchType" value="byKeyword">
        <label for="keyword">Search by Keyword:</label>
        <input type="text" id="keyword" name="keyword" placeholder="Enter keyword">
        <button type="submit">Search</button>
    </form>
</div>
</body>
</html>
