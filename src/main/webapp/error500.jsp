<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<%@ page isErrorPage="true" %>

<html lang="en">

<head>
    <title>Page Not Found</title>
</head>

<body class="bg-[#0d0e11] text-white font-sans">
<div class="max-w-4xl mx-auto px-6 py-12 space-y-12">

    <div class="text-center mb-8">
        <h2 class="text-3xl font-bold text-[#cff245]">Whoops! Something went wrong</h2>
    </div>

    <div class="text-center">
        <img src="${pageContext.request.contextPath}/images/brokenpottery.png" alt="Error Image" class="w-full max-w-sm mx-auto rounded-lg shadow-lg mb-8 border border-gray-700">

    </div>

    <div class="bg-[#ebebeb]/5 p-6 rounded-2xl shadow-md border border-gray-800 space-y-4 text-center">
        <p class="text-lg text-gray-300 mb-4 text-center">There was a problem completing your request. If the problem persists, reach out to the site administrator.</p>
        <a href="index.jsp" class="text-[#cff245] hover:underline font-medium text-lg text-center">Go Home</a>
    </div>

</div>
</body>

</html>
