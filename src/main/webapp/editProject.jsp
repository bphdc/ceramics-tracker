<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
<body class="bg-[#0d0e11] text-white font-sans">
<div class="max-w-4xl mx-auto px-6 py-12 space-y-12">

    <div class="text-center mb-10">
        <h2 class="text-3xl font-bold text-[#cff245] mb-4">Edit Your Project</h2>
    </div>

    <c:choose>
        <%@include file="youMustBeLoggedIn.jsp"%>

        <c:otherwise>

            <!-- basic info -->
            <div class="bg-[#ebebeb]/5 p-6 rounded-2xl shadow-md space-y-6 border border-gray-800">
                <h3 class="text-2xl font-semibold text-[#cff245] mb-4">Basic Info</h3>
                <form action="editProject" method="post" class="space-y-4">
                    <input type="hidden" name="projectId" value="${project.projectId}">

                    <div>
                        <label for="name" class="block text-sm font-medium text-gray-300 mb-1">Project Name:</label>
                        <input type="text" id="name" name="name" value="${project.name}" required maxlength="100"
                               class="w-full border border-gray-700 bg-transparent text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                    </div>

                    <div>
                        <label for="description" class="block text-sm font-medium text-gray-300 mb-1">Description:</label>
                        <textarea id="description" name="description" rows="4" required maxlength="500"
                                  class="w-full border border-gray-700 bg-transparent text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">${project.description}</textarea>
                    </div>

                    <div>
                        <label for="glazes" class="block text-sm font-medium text-gray-300 mb-1">Add Glazes:</label>
                        <select id="glazes" name="glazes" multiple
                                class="w-full border border-gray-700 bg-transparent text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                            <c:forEach var="glaze" items="${availableGlazes}">
                                <option value="${glaze.glazeId}" <c:if test="${fn:contains(selectedGlazes, glaze)}">selected</c:if>>${glaze.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label for="tags" class="block text-sm font-medium text-gray-300 mb-1">Add Tags:</label>
                        <select id="tags" name="tags" multiple
                                class="w-full border border-gray-700 bg-transparent text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                            <c:forEach var="tag" items="${availableTags}">
                                <option value="${tag.tagId}" <c:if test="${fn:contains(selectedTags, tag)}">selected</c:if>>${tag.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="w-full bg-[#cff245] text-black py-2 rounded-xl font-semibold hover:bg-lime-400 transition">
                        Save Changes
                    </button>
                </form>
            </div>

            <!-- images -->
            <div class="bg-[#ebebeb]/5 p-6 rounded-2xl shadow-md space-y-6 border border-gray-800">
                <h3 class="text-2xl font-semibold text-[#cff245] mb-4">Images</h3>
                <form action="addImages" method="post" enctype="multipart/form-data" class="space-y-4">
                    <input type="hidden" name="projectId" value="${project.projectId}">

                    <div>
                        <label for="fileUpload" class="block text-sm font-medium text-gray-300 mb-1">Upload Images (up to 5 at a time):</label>
                        <input type="file" id="fileUpload" name="images" multiple accept="image/*" onchange="handleFileUpload()" required
                               class="w-full border border-gray-700 bg-transparent text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
                        <div id="imagePreview" class="flex flex-wrap mt-4 gap-2"></div>
                    </div>

                    <button type="submit" class="w-full bg-[#cff245] text-black py-2 rounded-xl font-semibold hover:bg-lime-400 transition">
                        Upload Images
                    </button>
                </form>

                <c:if test="${not empty images}">
                    <h4 class="text-xl font-semibold text-gray-300 mt-6 mb-4">Previously Uploaded Images</h4>
                    <div class="flex flex-wrap gap-4">
                        <c:forEach var="image" items="${images}">
                            <div class="flex flex-col items-center">
                                <img src="${image.imageUrl}" alt="Uploaded Image" class="w-36 h-36 rounded object-cover shadow-md border border-gray-700">
                                <a href="deleteImage?id=${image.imageId}&projectId=${project.projectId}" class="text-red-500 hover:underline mt-2 text-sm">Delete</a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>

            <!-- blog -->
            <div class="bg-[#ebebeb]/5 p-6 rounded-2xl shadow-md space-y-6 border border-gray-800">
                <h3 class="text-2xl font-semibold text-[#cff245] mb-4">Project Blog</h3>
                <form action="addEntry" method="post" class="space-y-4">
                    <input type="hidden" name="projectId" value="${project.projectId}">

                    <div>
                        <label for="entryText" class="block text-sm font-medium text-gray-300 mb-1">Blog Entry:</label>
                        <textarea id="entryText" name="entryText" rows="4" required maxlength="5000"
                                  class="w-full border border-gray-700 bg-transparent text-white rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]"></textarea>
                    </div>

                    <button type="submit" class="w-full bg-[#cff245] text-black py-2 rounded-xl font-semibold hover:bg-lime-400 transition">
                        Add Entry
                    </button>
                </form>
            </div>

            <c:if test="${not empty entries}">
                <h4 class="text-xl font-semibold text-gray-300 mt-6 mb-4">Previous Entries</h4>
                <div class="space-y-6">
                    <c:forEach var="entry" items="${entries}">
                        <div class="bg-[#1a1b1f] p-4 rounded-xl border border-gray-700">
                            <form action="editEntry" method="post" class="space-y-2">
                                <input type="hidden" name="entryId" value="${entry.id}">
                                <input type="hidden" name="projectId" value="${project.projectId}">

                                <textarea name="entryText" rows="3" class="w-full bg-transparent border border-gray-600 text-white rounded-lg px-3 py-2 focus:ring-2 focus:ring-[#cff245]">${entry.entryText}</textarea>

                                <div class="flex justify-between items-center mt-2">
                                    <button type="submit" class="bg-[#cff245] text-black px-4 py-1 rounded-lg font-medium hover:bg-lime-400 transition">
                                        Save
                                    </button>
                                    <a href="deleteEntry?entryId=${entry.id}&projectId=${project.projectId}"
                                       class="text-red-500 hover:underline text-sm">Delete</a>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

        </c:otherwise>
    </c:choose>

</div>
</body>
</html>
