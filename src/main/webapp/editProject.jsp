<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>


<html>

<body>

<h2>Edit Your Project</h2>

<form action="editProject" method="post" enctype="multipart/form-data">

    <label for="name">Project Name:</label>
    <input type="text" id="name" name="name" value="${project.name}" required>

    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" required>${project.description}</textarea>

    <label for="fileUpload">Upload Images (up to 5 at a time):</label>
    <input type="file" id="fileUpload" name="images" multiple accept="image/*" onchange="handleFileUpload()">
    <div class="image-preview" id="imagePreview"></div>

    <div id="addMoreImages" class="hidden">
        <p>Would you like to add more images?</p>
        <button type="button" onclick="toggleAddMoreImages('yes')">Yes</button>
        <button type="button" onclick="toggleAddMoreImages('no')">No</button>
    </div>

    <label for="glazes">Add Glazes:</label>
    <select id="glazes" name="glazes" multiple>
        <c:forEach var="glaze" items="${availableGlazes}">
            <option value="${glaze.name}"
                <c:if test="${fn:contains(selectedGlazes, glaze)}">selected</c:if>
            >${glaze}</option>
        </c:forEach>
    </select>

    <label for="tags">Add Tags:</label>
    <select id="tags" name="tags" multiple>
        <c:forEach var="tag" items="${availableTags}">
            <option value="${tag.name}"
                <c:if test="${fn:contains(selectedTags, tag)}">selected</c:if>
            >${tag}</option>
        </c:forEach>
    </select>

    <button type="submit">Save Changes</button>
</form>

<hr>

<!-- Blog Entries Section -->
<h3>Project Blog</h3>

<form action="addEntry" method="post">
    <label for="newEntry">Add a new blog entry:</label>
    <textarea id="newEntry" name="newEntry" rows="3"></textarea>
    <button type="submit">Add Entry</button>
    <input type="hidden" name="projectId" value="${project.projectId}">
</form>

<h4>Previous Entries</h4>
<c:forEach var="entry" items="${entries}" varStatus="status">
    <div class="blog-entry">
        <p>${entry}</p>
        <form action="editEntry" method="post" style="display:inline;">
            <input type="hidden" name="entryIndex" value="${status.index}">
            <input type="hidden" name="projectId" value="${project.projectId}">
            <button type="submit">Edit</button>
        </form>
        <form action="deleteEntry" method="post" style="display:inline;">
            <input type="hidden" name="entryIndex" value="${status.index}">
            <input type="hidden" name="projectId" value="${project.projectId}">
            <button type="submit">Delete</button>
        </form>
    </div>
</c:forEach>

</body>

<script>
    function handleFileUpload() {
        let fileInput = document.getElementById("fileUpload");
        let preview = document.getElementById("imagePreview");
        let addMoreDiv = document.getElementById("addMoreImages");

        preview.innerHTML = "";
        let files = fileInput.files;
        if (files.length > 5) {
            alert("You can only upload up to 5 images at a time.");
            fileInput.value = "";
            return;
        }

        for (let i = 0; i < files.length; i++) {
            let img = document.createElement("img");
            img.src = URL.createObjectURL(files[i]);
            img.style.width = "100px";
            img.style.height = "100px";
            preview.appendChild(img);
        }

        addMoreDiv.classList.remove("hidden");
    }

    function toggleAddMoreImages(choice) {
        if (choice === 'yes') {
            document.getElementById("fileUpload").value = "";
            document.getElementById("addMoreImages").classList.add("hidden");
        }
    }
</script>

</html>
