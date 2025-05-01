<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>

<html>

<c:set var="customMessage" value="Are you sure you want to delete ${glaze.name}? User projects may be affected." />
<c:set var="formAction" value="deleteGlaze" />
<c:set var="hiddenName1" value="glazeId" />
<c:set var="hiddenValue1" value="${glaze.glazeId}" />
<c:set var="cancelUrl" value="glazeLibrary" />



<%@include file="deleteConfirmation.jsp"%>

</html>
