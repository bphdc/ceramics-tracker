<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">

  <body class="bg-amber-50 text-gray-800 font-sans">

    <div class="max-w-7xl mx-auto px-6 py-12 space-y-6 flex flex-col items-center">


      <h1 class="text-3xl font-bold text-amber-900 text-center mb-6">Glaze Library</h1>


      <a href="addGlaze" class="inline-block text-white bg-amber-600 py-2 px-4 rounded hover:bg-amber-700 transition mb-6">Add a New Glaze to Library</a>

      <div class="flex justify-center px-6">
        <div class="overflow-x-auto bg-white shadow-md rounded-lg w-full max-w-7xl">
        <table class="min-w-full table-auto text-center">
          <thead>
            <tr class="bg-amber-600 text-white">
              <th class="px-4 py-2">Name</th>
              <th class="px-4 py-2">Description</th>
              <th class="px-4 py-2">Type</th>
              <th class="px-4 py-2">Actions</th>
            </tr>
          </thead>
          <tbody class="bg-white">
            <c:forEach var="glaze" items="${glazes}">
              <tr class="border-b hover:bg-amber-50">
                <td class="px-4 py-2">${glaze.name}</td>
                <td class="px-4 py-2">${glaze.description}</td>
                <td class="px-4 py-2">${glaze.type}</td>
                <td class="px-4 py-2">
                  <a href="editGlaze?glazeId=${glaze.glazeId}" class="text-amber-600 hover:underline mr-2">Edit</a>
                  <a href="deleteGlaze?glazeId=${glaze.glazeId}" class="text-red-600 hover:underline">Delete</a>
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
