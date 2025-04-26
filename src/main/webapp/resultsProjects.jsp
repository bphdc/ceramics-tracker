<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html lang="en">

<body class="bg-amber-50 text-gray-800 font-sans">
  <div class="max-w-4xl mx-auto px-6 py-12 space-y-8">

    <div class="text-center">
      <h2 class="text-3xl font-bold text-amber-900 mb-6">Search Results</h2>
    </div>

    <c:choose>
      <c:when test="${empty projects}">
        <div class="text-center bg-white p-6 rounded-lg shadow-md">
          <p class="text-gray-600 text-lg">No projects found.</p>
        </div>
      </c:when>
      <c:otherwise>
        <div class="space-y-6">
          <c:forEach var="project" items="${projects}">
            <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition">
              <a href="viewProject?projectId=${project.projectId}" class="text-2xl font-semibold text-amber-700 hover:underline">
                ${project.name}
              </a>
              <p class="mt-2 text-gray-600">${project.description}</p>
            </div>
          </c:forEach>
        </div>
      </c:otherwise>
    </c:choose>

    <div class="text-center mt-8">
      <a href="searchProjects" class="text-amber-700 hover:underline font-medium">
        ← Back to Search
      </a>
    </div>

  </div>
</body>

</html>
