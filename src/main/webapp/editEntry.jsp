<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
<body class="bg-[#0d0e11] text-white font-sans">

<main class="pl-64 min-h-screen flex items-center justify-center">
    <div class="w-full max-w-3xl px-10 py-12 bg-[#ebebeb]/5 rounded-2xl border border-gray-800 shadow-xl">

        <div class="text-center mb-10">
            <h1 class="text-4xl font-extrabold text-[#cff245] mb-6">Edit Tag</h1>
        </div>

        <c:choose>
            <%@include file="youMustBeLoggedIn.jsp"%>

            <c:otherwise>
                <form action="editEntry" method="post" class="space-y-6">

                    <div>
                        <label for="entryText" class="block text-lg font-medium text-gray-200 mb-2">Edit blog entry:</label>
                        <textarea id="entryText" name="entryText" rows="6" required maxlength="5000"
                                  class="w-full border border-gray-700 bg-transparent rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-[#cff245]">{{entry.entryText}}</textarea>
                    </div>

                    <input type="hidden" name="projectId" value="${project.projectId}" />

                    <div class="flex justify-between">
                        <button type="submit"
                                class="bg-[#cff245] hover:bg-lime-400 text-black font-semibold py-3 px-8 rounded-xl shadow-md transition-all duration-300">
                            Edit Entry
                        </button>

                        <a href="viewProject?${project.projectId}">
                            <button type="button"
                                    class="bg-gray-700 hover:bg-gray-600 text-white font-semibold py-3 px-8 rounded-xl shadow-md transition-all duration-300">
                                Cancel
                            </button>
                        </a>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>

    </div>
</main>

</body>
</html>
