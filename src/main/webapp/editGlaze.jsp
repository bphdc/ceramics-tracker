<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
<body class="bg-[#0d0e11] text-white font-sans">

<main class="pl-64 min-h-screen flex items-center justify-center">
    <div class="w-full max-w-3xl px-10 py-12 bg-[#ebebeb]/5 rounded-2xl border border-gray-800 shadow-xl">

        <div class="text-center mb-10">
            <h1 class="text-4xl font-extrabold text-[#cff245] mb-6">Edit Glaze</h1>
        </div>

        <c:choose>
            <%@include file="youMustBeLoggedIn.jsp"%>

            <c:otherwise>
                <form action="editGlaze" method="post" class="space-y-6">

                    <div>
                        <label for="name" class="block text-lg font-medium text-gray-200 mb-2">Glaze Name:</label>
                        <input type="text" id="name" name="name" value="${glaze.name}" required
                               class="w-full border border-gray-700 bg-transparent text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]" />
                    </div>

                    <div>
                        <label for="description" class="block text-lg font-medium text-gray-200 mb-2">Glaze Description:</label>
                        <textarea id="description" name="description" rows="4" required
                                  class="w-full border border-gray-700 bg-transparent text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">${glaze.description}</textarea>
                    </div>

                    <div class="space-y-4">
                        <label class="block text-lg font-medium text-gray-200 mb-2">Glaze Type:</label>
                        <div class="flex items-center space-x-6">
                            <div class="flex items-center">
                                <input type="radio" id="underglaze" name="glazeType" value="underglaze" ${glaze.type == 'Underglaze' ? 'checked' : ''} required
                                       class="h-4 w-4 text-[#cff245] focus:ring-[#cff245]" />
                                <label for="underglaze" class="ml-2 text-white text-lg">Underglaze</label>
                            </div>
                            <div class="flex items-center">
                                <input type="radio" id="overglaze" name="glazeType" value="overglaze" ${glaze.type == 'Overglaze' ? 'checked' : ''} required
                                       class="h-4 w-4 text-[#cff245] focus:ring-[#cff245]" />
                                <label for="overglaze" class="ml-2 text-white text-lg">Overglaze</label>
                            </div>
                        </div>
                    </div>

                    <div class="text-center">
                        <button type="submit"
                                class="bg-[#cff245] hover:bg-lime-400 text-black font-semibold py-3 px-8 rounded-xl shadow-md transition-all duration-300">
                            Edit Glaze
                        </button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>

    </div>
</main>

</body>
</html>
