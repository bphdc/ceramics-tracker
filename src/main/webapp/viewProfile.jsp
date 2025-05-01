<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">

    <div class="max-w-4xl mx-auto px-6 py-12">
      <h1 class="text-3xl font-bold text-amber-900 text-center mb-8">${user.name}'s Profile</h1>

      <div class="bg-white p-6 rounded-lg shadow-md flex flex-col items-center space-y-6">
        <img src="${user.profilePicture != null ? user.profilePicture : 'images/noprofilepicture.png'}"
             alt="Profile Picture" class="w-36 h-36 rounded-full border-4 border-amber-300 mb-4">

        <div class="text-center">
          <p class="text-lg font-semibold text-gray-800"><strong>Username:</strong> ${user.username}</p>
          <p class="text-lg text-gray-700"><strong>Name:</strong> ${user.name}</p>
          <p class="text-lg text-gray-700"><strong>Bio:</strong> ${user.bio != null ? user.bio : 'No bio available.'}</p>
        </div>
      </div>

      <hr class="my-8">

      <h2 class="text-2xl font-semibold text-amber-800 mb-4">Projects</h2>

      <c:choose>
        <c:when test="${not empty projects}">
          <ul class="space-y-4">
            <c:forEach var="project" items="${projects}">
              <li class="bg-white p-4 rounded-lg shadow-md hover:shadow-lg transition">
                <a href="viewProject?projectId=${project.projectId}" class="text-amber-600 font-semibold hover:underline">
                  ${project.name}
                </a>
                <p class="text-gray-600">${project.description}</p>
              </li>
            </c:forEach>
          </ul>
        </c:when>
        <c:otherwise>
          <p class="text-gray-600">No projects available.</p>
        </c:otherwise>
      </c:choose>
    </div>

  </body>
</html>
