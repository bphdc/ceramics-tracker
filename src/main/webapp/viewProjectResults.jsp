<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="inter antialiased bg-[#0d0e11] text-white">

    <main class="pl-64 min-h-screen px-8 py-20 flex items-center justify-center">
      <div class="w-full max-w-4xl bg-[#ebebeb]/5 p-10 rounded-2xl border border-gray-800 shadow-xl space-y-10">

        <div class="text-center">
          <h2 class="text-4xl font-semibold text-[#cff245] tracking-tight mb-2">Search Results</h2>
        </div>

        <c:choose>
          <%@include file="youMustBeLoggedIn.jsp"%>

          <c:when test="${empty projects}">
            <div class="text-center bg-[#2d2f37] p-6 rounded-lg shadow-md">
              <p class="text-gray-400 text-lg">No projects found.</p>
            </div>
          </c:when>

          <c:otherwise>
            <div class="space-y-6">
              <c:forEach var="project" items="${projects}">
                <div class="bg-[#2d2f37] p-6 rounded-xl shadow-md hover:shadow-lg transition">
                  <a href="viewProject?projectId=${project.projectId}"
                     class="text-2xl font-semibold text-[#cff245] hover:underline">
                    ${project.name}
                  </a>
                  <p class="mt-2 text-gray-400">${project.description}</p>
                </div>
              </c:forEach>
            </div>
          </c:otherwise>
        </c:choose>

        <div class="text-center pt-8">
          <a href="searchProjects"
             class="text-[#cff245] hover:underline text-base font-medium">
            Back to Search
          </a>
        </div>

      </div>
    </main>

  </body>
</html>
