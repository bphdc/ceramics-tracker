<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>


<html>

<body>
    <h2>Edit Your Project</h2>

    <!-- Basic Info Section -->
    <fieldset>
        <legend><h3>Basic Info</h3></legend>
        <form action="editProject" method="post" enctype="multipart/form-data">
            <label for="name">Project Name:</label>
            <input type="text" id="name" name="name" value="${project.name}" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required>${project.description}</textarea>

            <label for="glazes">Add Glazes:</label>
            <select id="glazes" name="glazes" multiple>
                <c:forEach var="glaze" items="${availableGlazes}">
                    <option value="${glaze.name}" <c:if test="${fn:contains(selectedGlazes, glaze)}">selected</c:if>>${glaze}</option>
                </c:forEach>
            </select>

            <label for="tags">Add Tags:</label>
            <select id="tags" name="tags" multiple>
                <c:forEach var="tag" items="${availableTags}">
                    <option value="${tag.name}" <c:if test="${fn:contains(selectedTags, tag)}">selected</c:if>>${tag}</option>
                </c:forEach>
            </select>

            <button type="submit">Save Changes</button>
        </form>
    </fieldset>

    <hr>

    <!-- Image Upload Section -->
    <fieldset>
    <legend><h3>Images</h3></legend>

    <form action="addImages" method="post" enctype="multipart/form-data">
        <label for="fileUpload">Upload Images (up to 5 at a time):</label>
        <input type="hidden" name="projectId" value="${project.projectId}">
        <input type="file" id="fileUpload" name="images" multiple accept="image/*" onchange="handleFileUpload()" required>
        <div class="image-preview" id="imagePreview"></div>

        <button type="submit">Upload Images</button>
    </form>

    <c:if test="${not empty images}">
        <h4>Previously Uploaded Images</h4>
        <div style="display: flex; flex-wrap: wrap;">
            <c:forEach var="image" items="${images}">
                <div>
                    <img src="${image.imageUrl}" alt="Uploaded Image" width="150" height="150" style="margin: 5px; border-radius: 5px;">
                    <a href="deleteImage?id=${image.imageId}&projectId=${project.projectId}">Delete</a>
                </div>
            </c:forEach>
        </div>
    </c:if>
</fieldset>


    <hr>

    <!-- Blog Entries Section -->
    <fieldset>
        <legend><h3>Project Blog</h3></legend>
        <form action="addEntry" method="post">
            <label for="newEntry">Add a new blog entry:</label>
            <textarea id="newEntry" name="newEntry" rows="3" required></textarea>
            <button type="submit">Add Entry</button>
            <input type="hidden" name="projectId" value="${project.projectId}">
        </form>

        <c:if test="${not empty entries}">
            <h4>Previous Entries</h4>
            <div style="border: 1px solid #ddd; padding: 10px; border-radius: 5px;">
                <c:forEach var="entry" items="${entries}">
                    <div class="blog-entry">
                        <p>${entry.entryText}</p>
                        <a href="editEntry?id=${entry.id}&projectId=${project.projectId}">Edit</a> |
                        <a href="deleteEntry?id=${entry.id}&projectId=${project.projectId}">Delete</a>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </fieldset>
</body>

<script>
    function handleFileUpload() {
        let fileInput = document.getElementById("fileUpload");
        let preview = document.getElementById("imagePreview");

        // Clear previous previews
        preview.innerHTML = "";

        let files = fileInput.files;

        if (files.length > 5) {
            alert("You can only upload up to 5 images at a time.");
            fileInput.value = ""; // Reset input
            return;
        }

        // Loop through selected files and create previews
        for (let i = 0; i < files.length; i++) {
            let img = document.createElement("img");
            img.src = URL.createObjectURL(files[i]);
            img.style.width = "100px";
            img.style.height = "100px";
            img.style.margin = "5px";
            img.style.borderRadius = "5px";
            img.style.objectFit = "cover";
            preview.appendChild(img);
        }
    }
</script>


</html>
