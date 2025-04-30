<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">
    <div class="max-w-2xl mx-auto px-6 py-12 space-y-8">

      <div class="bg-white p-6 rounded-lg shadow-md">
        <h2 class="text-3xl font-bold text-amber-900 mb-6 text-center">Generate an AI Image</h2>

        <form action="generateAIImage" method="post" class="space-y-4">
          <div>
            <label for="prompt" class="block text-sm font-medium text-gray-700 mb-1">Enter a description:</label>
            <input
              type="text"
              id="prompt"
              name="prompt"
              value="${prompt != null ? prompt : ''}"
              required
              class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-amber-500"
            />
          </div>

          <button
            type="submit"
            class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition"
          >
            Generate
          </button>
        </form>

        <c:if test="${not empty errorMessage}">
          <p class="text-red-600 mt-4">${errorMessage}</p>
        </c:if>

        <c:if test="${not empty imageUrl}">
          <div class="mt-8 text-center">
            <h3 class="text-xl font-semibold text-amber-800 mb-4">Results for: "<span class="italic">${prompt}</span>"</h3>
            <img src="${imageUrl}" alt="AI generated image" class="mx-auto rounded shadow-md max-w-full" />
          </div>
        </c:if>
      </div>

    </div>
  </body>
</html>
