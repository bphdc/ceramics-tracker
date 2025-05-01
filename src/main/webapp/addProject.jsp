<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">

    <main class="pl-64 min-h-screen flex items-center justify-center">
      <div class="w-full max-w-3xl px-8 py-12 bg-white rounded-lg shadow-lg">

        <div class="text-center mb-10">
          <h1 class="text-4xl font-extrabold text-amber-900 mb-6">Create a New Project</h1>
        </div>

        <c:choose>
          <c:when test="${empty userName}">
            <div class="text-center bg-amber-100 p-6 rounded-lg shadow-inner">
              <p class="text-lg text-amber-800 mb-4">
                You must be logged in to create a project.
              </p>
              <a href="login.jsp" class="text-blue-700 hover:underline text-lg">Log In</a>
            </div>
          </c:when>

          <c:otherwise>
            <form action="addProject" method="post" class="space-y-6">

              <div>
                <label for="name" class="block text-lg font-medium text-amber-800 mb-2">Project Name:</label>
                <input type="text" id="name" name="name" required
                  class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-amber-500" />
              </div>

              <div>
                <label for="description" class="block text-lg font-medium text-amber-800 mb-2">Project Description:</label>
                <textarea id="description" name="description" rows="4" required
                  class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-amber-500"></textarea>
              </div>

              <div class="text-center">
                <button type="submit"
                  class="bg-amber-600 hover:bg-amber-700 text-white font-semibold py-3 px-8 rounded-lg shadow-md transition-all duration-300">
                  Create Project
                </button>
              </div>

            </form>

            <c:if test="${not empty projectId}">
              <div class="mt-10 text-center bg-amber-100 p-6 rounded-lg shadow-inner">
                <h2 class="text-2xl font-bold text-amber-800 mb-4">Next Steps</h2>
                <p class="text-lg text-gray-700 mb-6">
                  Your project has been created! What would you like to do next?
                </p>
                <ul class="space-y-2">
                  <li>
                    <a href="editProject?projectId=${projectId}"
                      class="text-blue-700 hover:underline text-lg">
                      Add Entry, Glazes, Tags, or Pictures
                    </a>
                  </li>
                </ul>
              </div>
            </c:if>

          </c:otherwise>
        </c:choose>

      </div>
    </main>

  </body>
</html>
