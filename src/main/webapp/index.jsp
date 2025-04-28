<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html lang="en">
  <body class="bg-amber-50 text-gray-800 font-sans">

    <main class="pl-64 min-h-screen flex items-center justify-center">
      <div class="w-full max-w-4xl px-8 py-12 bg-white rounded-lg shadow-lg">

        <div class="text-center mb-12">
          <h1 class="text-5xl font-extrabold text-amber-900 mb-4">Welcome to the Ceramics Tracker</h1>
          <p class="text-xl text-gray-700">
            Track your ceramic projects, glazes, and more with our easy-to-use tool.
          </p>
        </div>

        <c:choose>
          <c:when test="${empty userName}">
            <div class="text-center">
              <p class="text-lg font-medium text-amber-800">
                Whether you're tracking your latest ceramics project or exploring new glaze combinations, everything you need is just a click away.
                <br><br>Happy creating!
              </p>
            </div>
          </c:when>

          <c:otherwise>
            <div class="bg-amber-100 p-8 rounded-lg space-y-6 shadow-inner">
              <p class="text-xl font-semibold text-amber-800">
                Welcome, <c:out value="${userName}" />! We're glad you're here.
              </p>

              <p class="text-lg text-amber-800">
                Use the navigation sidebar to manage your profile, add new projects, explore glaze and tag libraries, and get inspired.
              </p>

              <p class="text-lg text-amber-800">
                Whether you're tracking your latest ceramics project or exploring new glaze combinations, everything you need is just a click away.
                Happy creating!
              </p>
            </div>
          </c:otherwise>
        </c:choose>

      </div>
    </main>

  </body>
</html>
