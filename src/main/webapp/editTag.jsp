<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>
<html lang="en">

  <body class="bg-amber-50 text-gray-800 font-sans">
    <div class="max-w-4xl mx-auto px-6 py-12 space-y-12">

      <div class="text-center mb-10">
        <h1 class="text-3xl font-bold text-amber-900">Edit Tag</h1>
      </div>

      <c:choose>
        <c:when test="${empty userName}">
          <p class="text-center text-lg text-gray-600">
            You must be logged in to edit a tag. <a href="login.jsp" class="text-amber-600 hover:underline">Log in</a>
          </p>
        </c:when>
        <c:otherwise>
          <form action="editTag" method="post" class="bg-white p-6 rounded-lg shadow-md space-y-6">
            <div class="form-group">
              <label for="name" class="block text-sm font-medium text-gray-700 mb-2">Tag Name:</label>
              <input type="text" id="name" name="name" value="${tag.name}" required
                class="w-full border-gray-300 rounded px-3 py-2 focus:ring-amber-500 focus:border-amber-500">
            </div>

            <div class="flex justify-center gap-4">
              <button type="submit"
                class="bg-amber-600 text-white py-2 px-4 rounded hover:bg-amber-700 transition">
                Edit Tag
              </button>
            </div>
          </form>
        </c:otherwise>
      </c:choose>

    </div>
  </body>

</html>
