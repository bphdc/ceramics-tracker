<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<c:set var="customMessage" value="Are you sure you want to delete ${tag.name}? User projects may be affected." />
<c:set var="formAction" value="deleteTag" />
<c:set var="hiddenName1" value="tagId" />
<c:set var="hiddenValue1" value="${tag.tagId}" />
<c:set var="cancelUrl" value="tagLibrary" />

<%@include file="deleteConfirmation.jsp"%>

</html>
