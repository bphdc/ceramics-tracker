<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">

<body class="bg-[#0d0e11] text-white font-sans">
<div class="max-w-4xl mx-auto px-6 py-12 space-y-8">

    <c:choose>
        <%@include file="youMustBeLoggedIn.jsp"%>
        <c:otherwise>

            <div class="bg-[#2d2f37] p-8 rounded-lg shadow-md">
                <h2 class="text-4xl font-bold text-[#cff245] mb-4">${project.name}</h2>
                <p class="text-gray-400 text-lg">${project.description}</p>
            </div>

            <c:if test="${not empty projectEntries}">
                <div class="bg-[#2d2f37] p-6 rounded-lg shadow-md space-y-4">
                    <h3 class="text-2xl font-semibold text-[#cff245]">Project Entries</h3>
                    <ul class="list-disc list-inside space-y-2">
                        <c:forEach var="entry" items="${projectEntries}">
                            <li>
                                <span class="text-gray-500 text-sm">${entry.createdAt}</span> -
                                <span class="text-gray-400">${entry.entryText}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <c:if test="${not empty projectImages}">
                <div class="bg-[#2d2f37] p-6 rounded-lg shadow-md space-y-4">
                    <h3 class="text-2xl font-semibold text-[#cff245]">Image Gallery</h3>
                    <div class="grid grid-cols-2 sm:grid-cols-3 gap-4">
                        <c:forEach var="image" items="${projectImages}">
                            <a href="${image.imageUrl}" target="_blank" class="block">
                                <img src="${image.imageUrl}" alt="Project Image" class="rounded-lg object-cover w-full h-40 hover:opacity-80 transition">
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty tags || not empty glazes}">
                <div class="bg-[#2d2f37] p-6 rounded-lg shadow-md space-y-6">
                    <h3 class="text-2xl font-semibold text-[#cff245]">Glazes and Tags</h3>

                    <c:if test="${not empty glazes}">
                        <div>
                            <h4 class="text-xl font-semibold text-[#cff245] mb-4">Glazes</h4>
                            <ul class="space-y-3">
                                <c:forEach var="glaze" items="${glazes}">
                                    <li class="bg-[#1a1b1f] rounded-2xl p-4 shadow-sm hover:shadow-md transition text-gray-300 border border-[#2c2d31]">
                                        <p class="text-[#cff245] font-semibold text-lg">${glaze.name}</p>
                                        <p class="text-sm italic text-gray-400">${glaze.type}</p>
                                        <p class="text-sm mt-1">${glaze.description}</p>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>


                    <c:if test="${not empty tags}">
                        <div>
                            <h4 class="text-xl font-semibold text-[#cff245] mb-2">Tags</h4>
                            <ul class="list-disc list-inside space-y-2">
                                <c:forEach var="tag" items="${tags}">
                                    <li class="text-gray-400">
                                        <span class="font-medium">${tag.name}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                </div>
            </c:if>

            <div class="text-center space-y-4">
                <a href="searchProjects" class="text-[#cff245] hover:underline font-medium block">
                    Back to Search
                </a>

                <c:if test="${loggedInUser.id == projectUser.id}">
                    <div class="flex justify-center gap-6">
                        <a href="editProject?projectId=${project.projectId}" class="text-blue-700 hover:underline font-medium">
                            Edit Project
                        </a>
                        <a href="deleteProject?projectId=${project.projectId}" class="text-red-600 hover:underline font-medium">
                            Delete Project
                        </a>
                    </div>
                </c:if>
            </div>

        </c:otherwise>
    </c:choose>

</div>
</body>

</html>
