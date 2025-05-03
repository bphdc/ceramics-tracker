<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">

  <body class="bg-[#0d0e11] text-white font-sans">
    <div class="max-w-4xl mx-auto px-6 py-12 space-y-8">

      <div class="text-center">
        <h2 class="text-3xl font-bold text-[#cff245] mb-6">Search Results</h2>
      </div>

      <c:choose>
        <%@include file="youMustBeLoggedIn.jsp"%>
        <c:otherwise>
          <c:when test="${empty projects}">
            <div class="text-center bg-[#2d2f37] p-6 rounded-lg shadow-md">
              <p class="text-gray-400 text-lg">No projects found.</p>
            </div>
          </c:when>
          <c:otherwise>
            <div class="space-y-6">
              <c:forEach var="project" items="${projects}">
                <div class="bg-[#2d2f37] p-6 rounded-lg shadow-md hover:shadow-lg transition">
                  <a href="viewProject?projectId=${project.projectId}" class="text-2xl font-semibold text-[#cff245] hover:underline">
                    ${project.name}
                  </a>
                  <p class="mt-2 text-gray-400">${project.description}</p>
                </div>
              </c:forEach>
            </div>
          </c:otherwise>
        </c:otherwise>
      </c:choose>

      <div class="text-center mt-8">
        <a href="searchProjects" class="text-[#cff245] hover:underline font-medium">
          Back to Search
        </a>
      </div>

    </div>
  </body>

</html>
