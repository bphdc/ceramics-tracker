<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>
<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">
    <div class="max-w-3xl mx-auto px-6 py-12">
      <div class="text-center mb-10">
        <h2 class="text-3xl font-bold text-amber-900 mb-4">Search Projects</h2>
        <p class="text-lg text-gray-700">Find projects by tag, keyword, or view all available projects.</p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-md space-y-6">

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
          <label for="tag" class="block text-amber-800 font-medium mb-1">Search by Tag:</label>
          <select name="tag" id="tag" class="w-full border-gray-300 rounded px-3 py-2">
            <c:forEach var="tag" items="${availableTags}">
              <option value="${tag}">${tag}</option>
            </c:forEach>
          </select>
          <button type="submit" class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition">
            Search
          </button>
        </form>

        <form action="searchProjects" method="get" class="space-y-4">
          <input type="hidden" name="searchType" value="byKeyword">
          <label for="keyword" class="block text-amber-800 font-medium mb-1">Search by Keyword:</label>
          <input type="text" id="keyword" name="keyword" placeholder="Enter keyword"
            class="w-full border-gray-300 rounded px-3 py-2">
          <button type="submit" class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition">
            Search
          </button>
        </form>

      </div>
    </div>
  </body>
</html>
