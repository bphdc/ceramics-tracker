<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="bg-[#0d0e11] text-white font-sans">
    <div class="max-w-3xl mx-auto px-6 py-12">
      <div class="text-center mb-10">
        <h2 class="text-3xl font-bold text-[#cff245] mb-4">Update Your Profile</h2>
      </div>

      <c:choose>
        <%@include file="youMustBeLoggedIn.jsp"%>
        <c:otherwise>
          <div class="bg-[#2d2f37] p-6 rounded-lg shadow-md space-y-6">
            <form action="updateProfile" method="POST" enctype="multipart/form-data" class="space-y-6">
              <div class="form-group">
                <label for="profilePicture" class="block text-sm font-medium text-gray-200">Profile Picture:</label>
                <input type="file" name="profilePicture" id="profilePicture"
                       class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
              </div>

              <div class="form-group">
                <label for="name" class="block text-sm font-medium text-gray-200">Name:</label>
                <input type="text" name="name" id="name" value="${user.name}"
                       class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">
              </div>

              <div class="form-group">
                <label for="bio" class="block text-sm font-medium text-gray-200">Bio:</label>
                <textarea name="bio" id="bio" rows="4"
                          class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-[#cff245]">${user.bio}</textarea>
              </div>

              <button type="submit" class="w-full bg-amber-600 text-white py-2 rounded hover:bg-amber-700 transition">Update Profile</button>
            </form>
          </div>

          <div class="text-center mt-6">
            <p class="text-lg text-gray-200">Current Profile Picture:</p>
            <img src="<c:out value='${user.profilePicture}'/>" alt="Profile Picture" class="w-24 h-24 rounded-full object-cover shadow-sm mx-auto">
          </div>

          <div class="text-center mt-6">
            <p>
              <a href="viewProfile?userId=${user.id}" class="text-[#cff245] hover:underline">Back to Profile</a>
            </p>
            <p>
              <a href="deleteUser?userId=${user.id}" class="text-red-600 hover:underline">Delete Profile</a>
            </p>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </body>
</html>
