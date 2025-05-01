<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>


<html lang="en">
  <body class="inter antialiased bg-[#0d0e11] text-white">

    <main class="min-h-screen px-8 py-20">
      <div class="w-full max-w-5xl mx-auto space-y-12">

        <div class="text-center">
          <h1 class="text-5xl font-semibold tracking-tighter mb-4">
            Welcome to the Ceramics Tracker
          </h1>
          <p class="text-xl text-gray-400">
            Track your ceramic projects, glazes, and more with our easy-to-use tool.
          </p>
        </div>

        <c:choose>
          <c:when test="${empty userName}">
            <div class="text-center space-y-4">
              <p class="text-lg text-gray-300">
                Whether you're tracking your latest ceramics project or exploring new glaze combinations,<br>
                everything you need is just a click away.
              </p>
              <p class="text-sm text-gray-500">
                Log in or create an account to get started.
              </p>
            </div>
          </c:when>

          <c:otherwise>
            <div class="bg-[#ebebeb]/5 p-8 rounded-2xl border border-gray-800 shadow-xl space-y-6">
              <p class="text-xl font-medium text-[#cff245]">
                Welcome, <c:out value="${userName}" />! We're glad you're here.
              </p>

              <p class="text-lg text-gray-300">
                Use the sidebar to manage your profile, add new projects, explore the glaze/tag libraries, and get inspired.
              </p>

              <p class="text-lg text-gray-300">
                Whether you're tracking your latest ceramic project or mixing your own glazes, everything you need is right here.
              </p>
            </div>
          </c:otherwise>
        </c:choose>

      </div>
    </main>
  </body>
</html>
