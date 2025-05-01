<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>


  <body class="bg-amber-50 text-gray-800 font-sans">

    <main class="pl-64 min-h-screen flex items-center justify-center">
      <div class="w-full max-w-2xl px-8 py-12 bg-white rounded-lg shadow-lg">

        <div class="text-center mb-8">
          <h2 class="text-3xl font-extrabold text-amber-900 mb-4">Confirm Deletion</h2>
          <p class="text-lg text-amber-800">${customMessage}</p>
        </div>

        <form action="${formAction}" method="post" class="flex flex-col items-center space-y-6">

          <input type="hidden" name="${hiddenName1}" value="${hiddenValue1}" />
          <c:if test="${not empty hiddenName2}">
            <input type="hidden" name="${hiddenName2}" value="${hiddenValue2}" />
          </c:if>

          <div class="flex space-x-4">
            <button type="submit"
              class="bg-red-600 hover:bg-red-700 text-white font-semibold py-3 px-6 rounded-lg shadow-md transition-all duration-300">
              Delete
            </button>

            <a href="${cancelUrl}"
              class="inline-flex items-center justify-center bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-3 px-6 rounded-lg shadow-md transition-all duration-300">
              Cancel
            </a>
          </div>

        </form>

      </div>
    </main>

  </body>

