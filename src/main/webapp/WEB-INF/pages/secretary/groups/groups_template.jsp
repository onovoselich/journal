<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scroll-after-post.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/groups.css">
<div>


    <tiles:insertAttribute name="specs"/><br>
    <tiles:insertAttribute name="groups"/>
</div>