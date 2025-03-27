<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>

<html>

<body>
<h2>Update Your Profile</h2>
<form action="updateProfile" method="POST" enctype="multipart/form-data">
    <label for="profilePicture">Profile Picture:</label><br>
    <input type="file" name="profilePicture" id="profilePicture"><br><br>

    <label for="name">Name:</label><br>
    <input type="text" name="name" id="name" value="${user.name}">

    <label for="bio">Bio:</label><br>
    <textarea name="bio" id="bio" rows="4" cols="50">${user.bio}</textarea><br><br>

    <button type="submit">Update Profile</button>
</form>

<p>Current Profile Picture: </p>
<img src="<c:out value='${user.profilePicture}'/>" alt="Profile Picture" width="100"><br><br>

<p><a href="viewProfile?userId=${user.id}">Back to Profile</a></p>
<p><a href="deleteUser?userId=${user.id}">Delete Profile</a></p>
</body>

</html>
