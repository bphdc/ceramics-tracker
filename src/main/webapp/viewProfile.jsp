<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>


<body>
<h1>${user.name}'s Profile</h1>


<div>
    <img src="${user.profilePicture != null ? user.profilePicture : 'images/noprofilepicture.png'}"
         alt="Profile Picture" width="150" height="150">
    <p><strong>Username:</strong> ${user.username}</p>
    <p><strong>Name:</strong> ${user.name}</p>
    <p><strong>Bio:</strong> ${user.bio != null ? user.bio : 'No bio available.'}</p>
</div>

<hr>

<h2>Projects</h2>
<c:choose>
    <c:when test="${not empty projects}">
        <ul>
            <c:forEach var="project" items="${projects}">
                <li>
                    <strong>${project.name}</strong><br>
                        ${project.description}
                </li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>No projects available.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
