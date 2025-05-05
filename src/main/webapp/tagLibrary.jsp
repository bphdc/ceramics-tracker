<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="bg-[#0d0e11] text-white font-sans">
    <main class="pl-64 min-h-screen px-8 py-20 flex items-center justify-center">
      <div class="w-full max-w-5xl bg-[#ebebeb]/5 border border-gray-800 p-10 rounded-2xl shadow-xl space-y-10">

        <div class="text-center">
          <h1 class="text-4xl font-semibold text-[#cff245] mb-2">Tag Library</h1>
        </div>

        <c:choose>
          <%@include file="youMustBeLoggedIn.jsp"%>
          <c:otherwise>

            <div class="text-right">
              <a href="addTag"
                 class="inline-block text-white bg-amber-600 py-2 px-5 rounded-lg hover:bg-amber-700 transition font-medium">
                + Add New Tag
              </a>
            </div>

            <div class="overflow-x-auto mt-6">
              <table class="min-w-full text-sm text-center bg-[#2d2f37] rounded-xl shadow-md overflow-hidden">
                <thead class="bg-amber-600 text-white text-base">
                  <tr>
                    <th class="px-6 py-4">Name</th>
                    <th class="px-6 py-4">Actions</th>
                  </tr>
                </thead>
                <tbody class="divide-y divide-gray-700 bg-[#1c1f26]">
                  <c:forEach var="tag" items="${tags}">
                    <tr class="hover:bg-[#3a3d47] transition">
                      <td class="px-6 py-4 text-[#cff245] font-medium">${tag.name}</td>
                      <td class="px-6 py-4">
                        <a href="editTag?tagId=${tag.tagId}" class="text-[#cff245] hover:underline mr-4">Edit</a>
                        <a href="deleteTag?tagId=${tag.tagId}" class="text-red-500 hover:underline">Delete</a>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>

          </c:otherwise>
        </c:choose>

      </div>
    </main>
  </body>
</html>
