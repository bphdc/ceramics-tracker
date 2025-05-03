<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="bg-[#0d0e11] text-white font-sans">

    <div class="max-w-4xl mx-auto px-6 py-12">
      <h1 class="text-3xl font-bold text-[#cff245] text-center mb-8">${user.name}'s Profile</h1>

      <c:choose>
        <%@include file="youMustBeLoggedIn.jsp"%>
        <c:otherwise>

      <div class="bg-[#2d2f37] p-6 rounded-lg shadow-md flex flex-col items-center space-y-6">
        <img src="${user.profilePicture != null ? user.profilePicture : 'images/noprofilepicture.png'}"
             alt="Profile Picture" class="w-36 h-36 rounded-full border-4 border-[#cff245] mb-4">

        <div class="text-center">
          <p class="text-lg font-semibold text-[#cff245]"><strong>Username:</strong> ${user.username}</p>
          <p class="text-lg text-gray-400"><strong>Name:</strong> ${user.name}</p>
          <p class="text-lg text-gray-400"><strong>Bio:</strong> ${user.bio != null ? user.bio : 'No bio available.'}</p>
        </div>
      </div>

      <hr class="my-8 border-[#cff245]">

      <h2 class="text-2xl font-semibold text-[#cff245] mb-4">Projects</h2>

      <c:choose>
        <c:when test="${not empty projects}">
          <ul class="space-y-4">
            <c:forEach var="project" items="${projects}">
              <li class="bg-[#2d2f37] p-4 rounded-lg shadow-md hover:shadow-lg transition">
                <a href="viewProject?projectId=${project.projectId}" class="text-[#cff245] font-semibold hover:underline">
                  ${project.name}
                </a>
                <p class="text-gray-400">${project.description}</p>
              </li>
            </c:forEach>
          </ul>
        </c:when>
        <c:otherwise>
          <p class="text-gray-400">No projects available.</p>
        </c:otherwise>
      </c:choose>

      </c:otherwise>
    </c:choose>
    </div>

  </body>
</html>
