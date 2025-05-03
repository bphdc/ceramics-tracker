<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">

<body class="bg-[#0d0e11] text-white font-sans">
<div class="max-w-3xl mx-auto px-6 py-12">
    <div class="text-center mb-10">
        <h2 class="text-3xl font-bold text-[#cff245] mb-4">Search Projects</h2>
        <p class="text-lg text-gray-400">Find projects by tag, keyword, or view all available projects.</p>
    </div>

    <div class="bg-[#2d2f37] p-6 rounded-lg shadow-md space-y-6">

        <c:choose>
            <%@include file="youMustBeLoggedIn.jsp"%>
            <c:otherwise>
                <form action="searchProjects" method="get" class="space-y-4">
                    <input type="hidden" name="searchType" value="myProjects">
                    <button type="submit" class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition">
                        Show My Projects
                    </button>
                </form>

                <form action="searchProjects" method="get" class="space-y-4">
                    <input type="hidden" name="searchType" value="allProjects">
                    <button type="submit" class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition">
                        Show All Projects
                    </button>
                </form>

                <form action="searchProjects" method="get" class="space-y-4">
                    <input type="hidden" name="searchType" value="byTag">
                    <div class="form-group">
                        <label for="tag" class="block text-[#cff245] font-medium mb-1">Search by Tag:</label>
                        <select name="tag" id="tag" class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-amber-500">
                            <c:forEach var="tag" items="${availableTags}">
                                <option value="${tag.tagId}">${tag.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition">
                        Search
                    </button>
                </form>

                <form action="searchProjects" method="get" class="space-y-4">
                    <input type="hidden" name="searchType" value="byGlaze">
                    <div class="form-group">
                        <label for="tag" class="block text-[#cff245] font-medium mb-1">Search by Glaze:</label>
                        <select name="glaze" id="glaze" class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-amber-500">
                            <c:forEach var="glaze" items="${availableGlazes}">
                                <option value="${glaze.glazeId}">${glaze.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition">
                        Search
                    </button>
                </form>

                <form action="searchProjects" method="get" class="space-y-4">
                    <input type="hidden" name="searchType" value="byKeyword">
                    <div class="form-group">
                        <label for="keyword" class="block text-[#cff245] font-medium mb-1">Search by Keyword:</label>
                        <input type="text" id="keyword" name="keyword" placeholder="Enter keyword"
                               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-amber-500">
                    </div>
                    <button type="submit" class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition">
                        Search
                    </button>
                </form>
            </c:otherwise>
        </c:choose>

    </div>
</div>
</body>

</html>
