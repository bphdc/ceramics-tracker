<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
<body class="inter antialiased bg-[#0d0e11] text-white">

<main class="pl-64 min-h-screen px-8 py-20 flex items-center justify-center">
    <div class="w-full max-w-3xl bg-[#ebebeb]/5 p-10 rounded-2xl border border-gray-800 shadow-xl space-y-10">

        <div class="text-center">
            <h1 class="text-4xl font-semibold text-[#cff245] tracking-tight mb-4">
                Add a New Glaze
            </h1>
        </div>

        <c:choose>
            <%@include file="youMustBeLoggedIn.jsp"%>

            <c:otherwise>
                <form action="addGlaze" method="post" class="space-y-6">

                    <div>
                        <label for="name" class="block text-lg font-medium text-[#cff245] mb-2">Glaze Name:</label>
                        <input type="text" id="name" name="name" required
                               class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]" />
                    </div>

                    <div>
                        <label for="description" class="block text-lg font-medium text-[#cff245] mb-2">Glaze Description:</label>
                        <textarea id="description" name="description" rows="4" required
                                  class="w-full bg-transparent border border-gray-700 rounded-lg px-4 py-2 text-white placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-[#cff245]"></textarea>
                    </div>

                    <div>
                        <label class="block text-lg font-medium text-[#cff245] mb-4">Glaze Type:</label>
                        <div class="flex items-center space-x-8">
                            <div class="flex items-center">
                                <input type="radio" id="underglaze" name="glazeType" value="underglaze" required
                                       class="h-5 w-5 text-[#cff245] focus:ring-[#cff245] border-gray-600 bg-transparent" />
                                <label for="underglaze" class="ml-2 text-gray-300 text-lg">Underglaze</label>
                            </div>
                            <div class="flex items-center">
                                <input type="radio" id="overglaze" name="glazeType" value="overglaze" required
                                       class="h-5 w-5 text-[#cff245] focus:ring-[#cff245] border-gray-600 bg-transparent" />
                                <label for="overglaze" class="ml-2 text-gray-300 text-lg">Overglaze</label>
                            </div>
                        </div>
                    </div>

                    <div class="text-center">
                        <button type="submit"
                                class="bg-[#cff245] text-black font-semibold py-3 px-8 rounded-xl shadow-lg hover:bg-lime-300 transition-all duration-300">
                            Add Glaze
                        </button>
                    </div>

                </form>
            </c:otherwise>
        </c:choose>

    </div>
</main>

</body>
</html>
