<%@include file="head.jsp"%>
<%@include file="sidebar.jsp"%>
<%@ page isErrorPage="true" %>

<html lang="en">

<head>
    <title>Page Not Found</title>
</head>

<body class="bg-amber-50 text-gray-800 font-sans">
<div class="max-w-4xl mx-auto px-6 py-12 space-y-12">

    <div class="text-center mb-8">
        <h2 class="text-3xl font-bold text-amber-900">Whoops! Page Not Found (404)</h2>
    </div>

    <div class="text-center">
        <img src="images/brokenpottery.png" alt="Page Not Found" class="w-full max-w-2xl mx-auto rounded-lg shadow-lg mb-8">
    </div>

    <div class="bg-white p-6 rounded-lg shadow-md space-y-4">
        <p class="text-lg text-gray-600 mb-4">The page you are looking for does not exist.</p>
        <a href="index.jsp" class="text-amber-600 hover:underline font-medium text-lg">Go Home</a>
    </div>

</div>
</body>

</html>

