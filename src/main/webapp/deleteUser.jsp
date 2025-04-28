<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<c:set var="customMessage" value="Are you sure you want to delete your profile? Your projects and pictures will also be deleted." />
<c:set var="formAction" value="deleteUser" />
<c:set var="hiddenName1" value="userId" />
<c:set var="hiddenValue1" value="${user.id}" />
<c:set var="cancelUrl" value="viewProfile?id=${user.id}" />



<%@include file="deleteConfirmation.jsp"%>

</html>
