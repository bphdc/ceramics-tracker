<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="sidebar.jsp"%>

<html>

<c:set var="customMessage" value="Are you sure you want to delete ${project.name}?" />
<c:set var="formAction" value="deleteProject" />
<c:set var="hiddenName1" value="projectId" />
<c:set var="hiddenValue1" value="${project.projectId}" />
<c:set var="cancelUrl" value="viewProject.jsp?projectId=${project.projectId}" />

<%@include file="deleteConfirmation.jsp"%>


</html>
