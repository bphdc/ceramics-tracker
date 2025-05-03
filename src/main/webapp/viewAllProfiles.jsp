<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="bg-[#0d0e11] text-white font-sans">
    <div class="max-w-6xl mx-auto px-6 py-12">
      <h2 class="text-3xl font-bold text-[#cff245] text-center mb-8">Member Directory</h2>

      <c:choose>
        <%@include file="youMustBeLoggedIn.jsp"%>
        <c:when test="${empty users}">
          <p class="text-center text-gray-600">No users found.</p>
        </c:when>
        <c:otherwise>
          <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            <c:forEach var="user" items="${users}">
              <div class="bg-[#2d2f37] p-6 rounded-lg shadow-md text-center hover:shadow-lg transition">
                <a href="viewProfile?userId=${user.id}">
                  <c:choose>
                    <c:when test="${empty user.profilePicture}">
                      <img src="images/noprofilepicture.png" alt="Default Profile Picture" class="w-24 h-24 rounded-full mx-auto mb-4">
                    </c:when>
                    <c:otherwise>
                      <img src="${user.profilePicture}" alt="Profile Picture" class="w-24 h-24 rounded-full mx-auto mb-4">
                    </c:otherwise>
                  </c:choose>
                  <p class="text-lg font-semibold text-[#cff245]">${user.username}</p>
                </a>
              </div>
            </c:forEach>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </body>
</html>
