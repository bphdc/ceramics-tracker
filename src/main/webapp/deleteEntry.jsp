<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<c:set var="customMessage" value="Are you sure you want to delete your project entry?" />
<c:set var="formAction" value="deleteEntry" />
<c:set var="hiddenName1" value="entryId" />
<c:set var="hiddenValue1" value="${entry.id}" />
<c:set var="hiddenName2" value="projectId" />
<c:set var="hiddenValue2" value="${project.projectId}" />
<c:set var="cancelUrl" value="viewProject?projectId=${project.projectId}" />


<%@include file="deleteConfirmation.jsp"%>

</html>
