<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
<body class="inter antialiased bg-[#0d0e11] text-white">

<main class="pl-64 min-h-screen px-8 py-20 flex items-center justify-center">
    <div class="w-full max-w-3xl bg-[#ebebeb]/5 p-10 rounded-2xl border border-gray-800 shadow-xl space-y-10">

        <div class="text-center">
            <h1 class="text-4xl font-semibold text-[#cff245] tracking-tight mb-4">
                Create a New Project
            </h1>
        </div>

        <c:choose>
            <%@include file="youMustBeLoggedIn.jsp"%>

            <c:otherwise>
                <form action="addProject" method="post" class="space-y-6">

                    <div>
                        <label for="name" class="block text-lg font-medium text-[#cff245] mb-2">Project Name:</label>
                        <input type="text" id="name" name="name" required maxlength="100"
                               class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]" />
                    </div>

                    <div>
                        <label for="description" class="block text-lg font-medium text-[#cff245] mb-2">Project Description:</label>
                        <textarea id="description" name="description" rows="4" required maxlength="500"
                                  class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]"></textarea>
                    </div>

                    <div class="text-center">
                        <button type="submit"
                                class="bg-[#cff245] text-black font-semibold py-3 px-8 rounded-xl shadow-lg hover:bg-lime-300 transition-all duration-300">
                            Create Project
                        </button>
                    </div>

                </form>

                <c:if test="${not empty projectId}">
                    <div class="mt-10 text-center bg-[#cff245]/10 p-6 rounded-xl border border-[#cff245]/30 shadow-inner">
                        <h2 class="text-2xl font-semibold text-[#cff245] mb-4">Next Steps</h2>
                        <p class="text-lg text-gray-300 mb-6">
                            Your project has been created! What would you like to do next?
                        </p>
                        <ul class="space-y-2">
                            <li>
                                <a href="editProject?projectId=${projectId}"
                                   class="text-[#cff245] hover:underline text-lg">
                                    Add Entry, Glazes, Tags, or Pictures
                                </a>
                            </li>
                        </ul>
                    </div>
                </c:if>

            </c:otherwise>
        </c:choose>

    </div>
</main>

</body>
</html>
