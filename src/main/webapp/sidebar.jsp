<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<aside class="bg-white w-64 min-h-screen shadow-md p-6 space-y-4 fixed top-0 left-0">
  <h2 class="text-2xl font-bold text-amber-800 mb-6">Menu</h2>

  <c:choose>
    <c:when test="${empty userName}">
      <a href="logIn" class="block text-blue-700 hover:underline">Log In</a>
    </c:when>

    <c:otherwise>
      <p class="text-lg font-medium text-amber-800">Hi, <c:out value="${userName}" />!</p>

      <nav class="space-y-2">
        <a href="updateProfile" class="block text-blue-700 hover:underline">Update Profile</a>
        <a href="viewProfile?userId=${userId}" class="block text-blue-700 hover:underline">My Profile</a>
        <a href="viewAllProfiles" class="block text-blue-700 hover:underline">Member Directory</a>
        <a href="addProject" class="block text-blue-700 hover:underline">Add Project</a>
        <a href="searchProjects?searchType=myProjects" class="block text-blue-700 hover:underline">View My Projects</a>
        <a href="searchProjects" class="block text-blue-700 hover:underline">Search Projects</a>
        <a href="searchAI" class="block text-blue-400 italic hover:underline">Get Inspo</a>
        <a href="logout" class="block text-red-600 hover:underline">Log Out</a>

        <c:if test="${userRole == 'admin'}">
          <hr class="my-4">
          <a href="glazeLibrary" class="block text-green-700 hover:underline">Glaze Library</a>
          <a href="tagLibrary" class="block text-green-700 hover:underline">Tag Library</a>
        </c:if>
      </nav>
    </c:otherwise>
  </c:choose>
</aside>

