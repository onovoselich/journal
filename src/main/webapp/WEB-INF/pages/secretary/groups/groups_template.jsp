<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/groups.css">
<div>


    <tiles:insertAttribute name="specs"/><br>
    <tiles:insertAttribute name="groups"/>
</div>