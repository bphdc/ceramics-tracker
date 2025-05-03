<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>
<html lang="en">

<body class="bg-[#0d0e11] text-white font-sans">
<div class="max-w-4xl mx-auto px-6 py-12 space-y-12">

    <div class="text-center mb-10">
        <h1 class="text-3xl font-bold text-[#cff245]">Edit Tag</h1>
    </div>

    <c:choose>
        <%@include file="youMustBeLoggedIn.jsp"%>
        <c:otherwise>
            <form action="editTag" method="post" class="bg-[#ebebeb]/5 p-6 rounded-2xl shadow-md space-y-6 border border-gray-800">
                <div class="form-group">
                    <label for="name" class="block text-sm font-medium text-gray-300 mb-2">Tag Name:</label>
                    <input type="text" id="name" name="name" value="${tag.name}" required
                           class="w-full bg-transparent text-white border border-gray-700 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                </div>

                <div class="flex justify-center gap-4">
                    <button type="submit"
                            class="bg-[#cff245] text-black py-2 px-4 rounded-xl font-semibold hover:bg-lime-400 transition">
                        Edit Tag
                    </button>
                </div>
            </form>
        </c:otherwise>
    </c:choose>

</div>
</body>

</html>
