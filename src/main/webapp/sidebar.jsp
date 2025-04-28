<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<aside class="bg-gray-800 w-64 min-h-screen shadow-lg p-6 space-y-6 fixed top-0 left-0 text-gray-200">
  <h2 class="text-2xl font-semibold text-white mb-8">Menu</h2>

  <c:choose>
    <c:when test="${empty userName}">
      <a href="logIn" class="block text-indigo-400 hover:text-white transition-colors duration-200">Log In</a>
    </c:when>

    <c:otherwise>
      <p class="text-lg font-medium text-amber-100 mb-6">Welcome, <c:out value="${userName}" />!</p>

      <nav class="space-y-4">
        <div>
          <h3 class="text-lg font-semibold text-amber-300">My Profile</h3>
          <a href="updateProfile" class="block text-indigo-300 hover:text-white transition-colors duration-200">Update Profile</a>
          <a href="viewProfile?userId=${userId}" class="block text-indigo-300 hover:text-white transition-colors duration-200">My Profile</a>
        </div>


        <div>
          <h3 class="text-lg font-semibold text-amber-300">Projects</h3>
          <a href="addProject" class="block text-indigo-300 hover:text-white transition-colors duration-200">Add Project</a>
          <a href="searchProjects?searchType=myProjects" class="block text-indigo-300 hover:text-white transition-colors duration-200">View My Projects</a>
          <a href="searchProjects" class="block text-indigo-300 hover:text-white transition-colors duration-200">Search Projects</a>
        </div>


        <div>
          <h3 class="text-lg font-semibold text-amber-300">Community</h3>
          <a href="viewAllProfiles" class="block text-indigo-300 hover:text-white transition-colors duration-200">Member Directory</a>
          <a href="searchAI" class="block text-indigo-500 hover:text-white italic transition-colors duration-200">Get Inspo</a>
        </div>


        <a href="logout" class="block text-red-500 hover:text-white transition-colors duration-200 mt-6">Log Out</a>


        <c:if test="${userRole == 'admin'}">
          <hr class="my-4 border-gray-500">
          <h3 class="text-lg font-semibold text-green-400">Admin</h3>
          <a href="glazeLibrary" class="block text-green-300 hover:text-white transition-colors duration-200">Glaze Library</a>
          <a href="tagLibrary" class="block text-green-300 hover:text-white transition-colors duration-200">Tag Library</a>
        </c:if>
      </nav>
    </c:otherwise>
  </c:choose>
</aside>
