<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
<body class="inter antialiased bg-[#0d0e11] text-white">

<main class="pl-64 min-h-screen px-6 py-20 flex items-center justify-center">
    <div class="w-full max-w-2xl bg-[#ebebeb]/5 p-10 rounded-2xl border border-gray-800 shadow-xl space-y-8">

        <div class="text-center">
            <h2 class="text-3xl font-semibold text-[#cff245] mb-6">Generate an AI Image</h2>
            <a href="https://thehive.ai/"class="block text-sm text-gray-300 hover:text-white">Powered by hive.ai</a>
        </div>

        <c:choose>
            <%@include file="youMustBeLoggedIn.jsp"%>

            <c:otherwise>
                <form action="generateAIImage" method="post" class="space-y-6">
                    <div>
                        <label for="prompt" class="block text-lg font-medium text-[#cff245] mb-2">Enter a description:</label>
                        <input
                                type="text"
                                id="prompt"
                                name="prompt"
                                value="${prompt != null ? prompt : ''}"
                                required
                                class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]"
                        />
                    </div>

                    <button
                            type="submit"
                            class="w-full bg-[#cff245] text-black font-semibold py-3 px-6 rounded-xl shadow-lg hover:bg-lime-300 transition-all duration-300"
                    >
                        Generate
                    </button>
                </form>

                <c:if test="${not empty errorMessage}">
                    <p class="text-red-500 mt-4 text-center">${errorMessage}</p>
                </c:if>

                <c:if test="${not empty imageUrl}">
                    <div class="mt-10 text-center">
                        <h3 class="text-xl font-semibold text-[#cff245] mb-4">
                            Results for: "<span class="italic">${prompt}</span>"
                        </h3>
                        <img src="${imageUrl}" alt="AI generated image" class="mx-auto rounded-2xl border border-gray-700 shadow-lg max-w-full" />
                    </div>
                </c:if>
            </c:otherwise>
        </c:choose>

    </div>
</main>

</body>
</html>
