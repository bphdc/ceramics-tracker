<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>
<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">
  <div class="max-w-7xl mx-auto px-6 py-12 space-y-6 flex flex-col items-center">

      <h1 class="text-3xl font-bold text-amber-900 text-center mb-6">Tag Library</h1>

      <a href="addTag" class="inline-block text-white bg-amber-600 py-2 px-4 rounded hover:bg-amber-700 transition mb-6">Add a New Tag to Library</a>
    <div class="flex justify-center px-6">
    <div class="overflow-x-auto bg-white shadow-md rounded-lg w-full max-w-7xl">
        <table class="min-w-full table-auto text-center">
          <thead>
            <tr class="bg-amber-600 text-white">
              <th class="px-4 py-2">Name</th>
              <th class="px-4 py-2">Actions</th>
            </tr>
          </thead>
          <tbody class="bg-white">
            <c:forEach var="tag" items="${tags}">
              <tr class="border-b hover:bg-amber-50">
                <td class="px-4 py-2">${tag.name}</td>
                <td class="px-4 py-2">
                  <a href="editTag?tagId=${tag.tagId}" class="text-amber-600 hover:underline mr-2">Edit</a>
                  <a href="deleteTag?tagId=${tag.tagId}" class="text-red-600 hover:underline">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  </body>
</html>
