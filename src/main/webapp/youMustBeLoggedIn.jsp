
<%@include file="taglib.jsp"%>



<c:when test="${empty userName}">
    <div class="text-center bg-[#cff245]/10 p-6 rounded-xl border border-[#cff245]/30 shadow-inner">
        <p class="text-lg text-[#cff245] mb-4">
            You must be logged in to view this page.
        </p>
        <a href="logIn" class="text-[#cff245] hover:underline text-lg">Log In</a>
    </div>
</c:when>

