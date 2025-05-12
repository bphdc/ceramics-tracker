<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>

<body class="inter antialiased bg-[#0d0e11] text-white">

  <main class="pl-64 min-h-screen flex items-center justify-center">
    <div class="w-full max-w-2xl px-10 py-12 bg-[#ebebeb]/5 rounded-2xl border border-gray-800 shadow-xl">

      <div class="text-center mb-10">
        <h2 class="text-3xl font-bold text-[#cff245] mb-4">Confirm Deletion</h2>
        <p class="text-lg text-gray-300">${customMessage}</p>
      </div>

      <c:choose>
          <%@include file="youMustBeLoggedIn.jsp"%>

        <c:otherwise>

      <form action="${formAction}" method="post" class="flex flex-col items-center space-y-6">

        <input type="hidden" name="${hiddenName1}" value="${hiddenValue1}" />
        <c:if test="${not empty hiddenName2}">
          <input type="hidden" name="${hiddenName2}" value="${hiddenValue2}" />
        </c:if>

        <div class="flex space-x-6">
          <button type="submit"
            class="bg-red-600 hover:bg-red-700 text-white font-semibold py-3 px-6 rounded-xl shadow-md transition-all duration-300">
            Delete
          </button>

          <a href="${cancelUrl}"
            class="inline-flex items-center justify-center bg-gray-700 hover:bg-gray-600 text-white font-semibold py-3 px-6 rounded-xl shadow-md transition-all duration-300">
            Cancel
          </a>
        </div>

      </form>

      </c:otherwise>
      </c:choose>

    </div>
  </main>

</body>
