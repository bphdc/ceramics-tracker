<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>
<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">
    <div class="max-w-3xl mx-auto px-6 py-12">
      <div class="text-center mb-10">
        <h1 class="text-4xl font-bold text-amber-900 mb-4">Welcome to the Ceramics Tracker</h1>
        <p class="text-lg text-gray-700">
          Track your ceramic projects, glazes, and more with our easy-to-use tool.
        </p>
      </div>

      <c:choose>
        <c:when test="${empty userName}">
          <div class="text-center">
            <a href="logIn" class="inline-block bg-amber-600 text-white px-5 py-2 rounded hover:bg-amber-700 transition">Log In</a>
          </div>
        </c:when>

        <c:otherwise>
          <div class="bg-white p-6 rounded-lg shadow-md space-y-4">
            <p class="text-lg font-medium text-amber-800">Welcome, <c:out value="${userName}" />!</p>

            <div class="grid gap-2">
              <a href="updateProfile" class="text-blue-700 hover:underline">Update Profile</a>
              <a href="viewProfile?userId=${userId}" class="text-blue-700 hover:underline">View My Profile</a>
              <a href="viewAllProfiles" class="text-blue-700 hover:underline">Member Directory</a>
              <a href="addProject" class="text-blue-700 hover:underline">Add a New Project</a>
              <a href="searchProjects" class="text-blue-700 hover:underline">Search Projects</a>
              <a href="searchAI" class="text-blue-400 italic hover:underline">Get inspo (TODO implement)</a>
              <a href="logout" class="text-red-600 hover:underline">Log out</a>
            </div>

            <c:if test="${userRole == 'admin'}">
              <hr class="my-4">
              <div class="space-y-2">
                <a href="glazeLibrary" class="text-green-700 hover:underline">Glaze Library</a><br>
                <a href="tagLibrary" class="text-green-700 hover:underline">Tag Library</a>
              </div>
            </c:if>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </body>
</html>
