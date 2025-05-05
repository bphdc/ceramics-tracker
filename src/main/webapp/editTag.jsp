<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="bg-[#0d0e11] text-white font-sans">
    <main class="pl-64 min-h-screen px-8 py-20 flex items-center justify-center">
      <div class="w-full max-w-xl bg-[#ebebeb]/5 border border-gray-800 p-10 rounded-2xl shadow-xl space-y-8">

        <div class="text-center">
          <h1 class="text-3xl font-bold text-[#cff245]">Edit Tag</h1>
        </div>

        <c:choose>
          <%@include file="youMustBeLoggedIn.jsp"%>
          <c:otherwise>
            <form action="editTag" method="post" class="space-y-6">
              <div>
                <label for="name" class="block text-sm font-medium text-gray-300 mb-2">Tag Name:</label>
                <input type="text" id="name" name="name" value="${tag.name}" required
                       class="w-full bg-transparent text-white border border-gray-700 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
              </div>

              <div class="flex justify-center">
                <button type="submit"
                        class="bg-[#cff245] text-black py-2 px-6 rounded-xl font-semibold hover:bg-lime-400 transition">
                  Save Changes
                </button>
              </div>
            </form>
          </c:otherwise>
        </c:choose>

      </div>
    </main>
  </body>
</html>
