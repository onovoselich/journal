<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<!DOCTYPE html>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vukladach-two.css">
<div>
<c:if test="${not empty my_group}">
    <tiles:insertAttribute name="group_info"/><br>
</c:if>
    <tiles:insertAttribute name="content"/>
</div>