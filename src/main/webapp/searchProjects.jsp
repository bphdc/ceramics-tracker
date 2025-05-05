<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
<body class="inter antialiased bg-[#0d0e11] text-white">

<main class="pl-64 min-h-screen px-8 py-20 flex items-center justify-center">
    <div class="w-full max-w-3xl bg-[#ebebeb]/5 p-10 rounded-2xl border border-gray-800 shadow-xl space-y-10">

        <div class="text-center">
            <h2 class="text-4xl font-semibold text-[#cff245] tracking-tight mb-2">Search Projects</h2>
            <p class="text-lg text-gray-400">Find projects by tag, keyword, or view all available projects.</p>
        </div>

        <c:choose>
            <%@include file="youMustBeLoggedIn.jsp"%>
            <c:otherwise>
                <form action="searchProjects" method="get" class="space-y-6">
                    <input type="hidden" name="searchType" value="myProjects">
                    <button type="submit"
                            class="w-full bg-[#cff245] text-black font-semibold py-3 px-8 rounded-xl shadow-md hover:bg-lime-300 transition-all duration-300">
                        Show My Projects
                    </button>
                </form>

                <form action="searchProjects" method="get" class="space-y-6">
                    <input type="hidden" name="searchType" value="allProjects">
                    <button type="submit"
                            class="w-full bg-[#cff245] text-black font-semibold py-3 px-8 rounded-xl shadow-md hover:bg-lime-300 transition-all duration-300">
                        Show All Projects
                    </button>
                </form>

                <form action="searchProjects" method="get" class="space-y-6">
                    <input type="hidden" name="searchType" value="byTag">
                    <div>
                        <label for="tag" class="block text-lg font-medium text-[#cff245] mb-2">Search by Tag:</label>
                        <select name="tag" id="tag"
                                class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                            <c:forEach var="tag" items="${availableTags}">
                                <option value="${tag.tagId}">${tag.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit"
                            class="w-full bg-[#cff245] text-black font-semibold py-3 px-8 rounded-xl shadow-md hover:bg-lime-300 transition-all duration-300">
                        Search
                    </button>
                </form>

                <form action="searchProjects" method="get" class="space-y-6">
                    <input type="hidden" name="searchType" value="byGlaze">
                    <div>
                        <label for="glaze" class="block text-lg font-medium text-[#cff245] mb-2">Search by Glaze:</label>
                        <select name="glaze" id="glaze"
                                class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                            <c:forEach var="glaze" items="${availableGlazes}">
                                <option value="${glaze.glazeId}">${glaze.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit"
                            class="w-full bg-[#cff245] text-black font-semibold py-3 px-8 rounded-xl shadow-md hover:bg-lime-300 transition-all duration-300">
                        Search
                    </button>
                </form>

                <form action="searchProjects" method="get" class="space-y-6">
                    <input type="hidden" name="searchType" value="byKeyword">
                    <div>
                        <label for="keyword" class="block text-lg font-medium text-[#cff245] mb-2">Search by Keyword:</label>
                        <input type="text" id="keyword" name="keyword" placeholder="Enter keyword"
                               class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                    </div>
                    <button type="submit"
                            class="w-full bg-[#cff245] text-black font-semibold py-3 px-8 rounded-xl shadow-md hover:bg-lime-300 transition-all duration-300">
                        Search
                    </button>
                </form>
            </c:otherwise>
        </c:choose>

    </div>
</main>

</body>
</html>
