<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">

    <div class="max-w-6xl mx-auto px-6 py-12">
      <h2 class="text-3xl font-bold text-amber-900 text-center mb-8">Member Directory</h2>

      <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        <c:choose>
          <c:when test="${not empty users}">
            <c:forEach var="user" items="${users}">
              <div class="bg-white p-6 rounded-lg shadow-md text-center">
                <a href="viewProfile?userId=${user.id}">
                  <c:choose>
                    <c:when test="${empty user.profilePicture}">
                      <img src="images/noprofilepicture.png" alt="Default Profile Picture" class="w-24 h-24 rounded-full mx-auto mb-4">
                    </c:when>
                    <c:otherwise>
                      <img src="${user.profilePicture}" alt="Profile Picture" class="w-24 h-24 rounded-full mx-auto mb-4">
                    </c:otherwise>
                  </c:choose>
                  <p class="text-lg font-semibold text-gray-800">${user.username}</p>
                </a>
              </div>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <p class="text-center text-gray-600">No users found.</p>
          </c:otherwise>
        </c:choose>
      </div>
    </div>

  </body>
</html>
