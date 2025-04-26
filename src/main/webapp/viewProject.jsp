<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html lang="en">

<body class="bg-amber-50 text-gray-800 font-sans">
  <div class="max-w-4xl mx-auto px-6 py-12 space-y-8">

    <div class="bg-white p-8 rounded-lg shadow-md">
      <h2 class="text-4xl font-bold text-amber-900 mb-4">${project.name}</h2>
      <p class="text-gray-700 text-lg">${project.description}</p>
    </div>

    <c:if test="${not empty projectEntries}">
      <div class="bg-white p-6 rounded-lg shadow-md space-y-4">
        <h3 class="text-2xl font-semibold text-amber-800">Project Entries</h3>
        <ul class="list-disc list-inside space-y-2">
          <c:forEach var="entry" items="${projectEntries}">
            <li>
              <span class="text-gray-500 text-sm">${entry.createdAt}</span> -
              <span class="text-gray-700">${entry.entryText}</span>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:if>

    <c:if test="${not empty projectImages}">
      <div class="bg-white p-6 rounded-lg shadow-md space-y-4">
        <h3 class="text-2xl font-semibold text-amber-800">Image Gallery</h3>
        <div class="grid grid-cols-2 sm:grid-cols-3 gap-4">
          <c:forEach var="image" items="${projectImages}">
            <a href="${image.imageUrl}" target="_blank" class="block">
              <img src="${image.imageUrl}" alt="Project Image" class="rounded-lg object-cover w-full h-40 hover:opacity-80 transition">
            </a>
          </c:forEach>
        </div>
      </div>
    </c:if>

    <div class="text-center space-y-4">
      <a href="searchProjects" class="text-amber-700 hover:underline font-medium block">
        ← Back to Search
      </a>

      <c:if test="${loggedInUser.id == projectUser.id}">
        <div class="flex justify-center gap-6">
          <a href="editProject?projectId=${project.projectId}" class="text-blue-700 hover:underline font-medium">
            Edit Project
          </a>
          <a href="deleteProject?projectId=${project.projectId}" class="text-red-600 hover:underline font-medium">
            Delete Project
          </a>
        </div>
      </c:if>
    </div>

  </div>
</body>

</html>
