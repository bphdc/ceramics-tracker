<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html lang="en">
  <body class="body-main">
    <div class="section">
      <div class="section-header">
        <h1 class="heading-main">Welcome to the Ceramics Tracker</h1>
        <p class="heading-sub">
          Track your ceramic projects, glazes, and more with our easy-to-use tool.
        </p>
      </div>

      <c:choose>
        <c:when test="${empty userName}">
          <p class="body-text">
              Whether you're tracking your latest ceramics project or exploring new glaze combinations, everything you need is just a click away.
              Happy creating!
            </p>
        </c:when>

        <c:otherwise>
          <div class="card">
            <p class="body-text">
              Welcome, <c:out value="${userName}" />!
              We're glad you're here.
              Use the navigation sidebar to manage your profile, add new projects, explore glaze and tag libraries, and get inspired.
            </p>

            <p class="body-text">
              Whether you're tracking your latest ceramics project or exploring new glaze combinations, everything you need is just a click away.
              Happy creating!
            </p>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </body>
</html>
