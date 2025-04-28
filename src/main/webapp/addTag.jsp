<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">

    <main class="pl-64 min-h-screen flex items-center justify-center">
      <div class="w-full max-w-3xl px-8 py-12 bg-white rounded-lg shadow-lg">

        <div class="text-center mb-10">
          <h1 class="text-4xl font-extrabold text-amber-900 mb-6">Add a New Tag</h1>
        </div>

        <c:choose>
          <c:when test="${empty userName}">
            <div class="text-center bg-amber-100 p-6 rounded-lg shadow-inner">
              <p class="text-lg text-amber-800 mb-4">
                You must be logged in to create a tag.
              </p>
              <a href="login.jsp" class="text-blue-700 hover:underline text-lg">Log In</a>
            </div>
          </c:when>

          <c:otherwise>
            <form action="addTag" method="post" class="space-y-6">

              <div>
                <label for="name" class="block text-lg font-medium text-amber-800 mb-2">Tag Name:</label>
                <input type="text" id="name" name="name" required
                  class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-amber-500" />
              </div>

              <div class="text-center">
                <button type="submit"
                  class="bg-amber-600 hover:bg-amber-700 text-white font-semibold py-3 px-8 rounded-lg shadow-md transition-all duration-300">
                  Add Tag
                </button>
              </div>

            </form>
          </c:otherwise>
        </c:choose>

      </div>
    </main>

  </body>
</html>
