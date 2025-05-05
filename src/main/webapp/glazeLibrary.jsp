<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">

  <body class="bg-[#0d0e11] text-white font-sans">
    <div class="max-w-7xl mx-auto px-6 py-12 space-y-6 flex flex-col items-center">
      <h1 class="text-3xl font-bold text-[#cff245] text-center mb-6">Glaze Library</h1>

      <c:choose>
        <%@include file="youMustBeLoggedIn.jsp"%>
        <c:otherwise>
          <a href="addGlaze" class="inline-block text-white bg-amber-600 py-2 px-4 rounded hover:bg-amber-700 transition mb-6">Add a New Glaze to Library</a>

          <div class="flex justify-center px-6">
            <div class="overflow-x-auto bg-[#2d2f37] shadow-md rounded-lg w-full max-w-7xl">
              <table class="min-w-full table-auto text-center">
                <thead>
                  <tr class="bg-amber-600 text-white">
                    <th class="px-4 py-2">Name</th>
                    <th class="px-4 py-2">Description</th>
                    <th class="px-4 py-2">Type</th>
                    <th class="px-4 py-2">Actions</th>
                  </tr>
                </thead>
                <tbody class="bg-[#1c1f26]">
                  <c:forEach var="glaze" items="${glazes}">
                    <tr class="border-b hover:bg-gray-800">
                      <td class="px-4 py-2">${glaze.name}</td>
                      <td class="px-4 py-2">${glaze.description}</td>
                      <td class="px-4 py-2">${glaze.type}</td>
                      <td class="px-4 py-2">
                        <a href="editGlaze?glazeId=${glaze.glazeId}" class="text-[#cff245] hover:underline mr-2">Edit</a>
                        <a href="deleteGlaze?glazeId=${glaze.glazeId}" class="text-red-600 hover:underline">Delete</a>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </c:otherwise>
      </c:choose>

    </div>

  </body>

</html>
