<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/studinfo-display.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/content-popup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/number_validation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/studinfo-display.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vukladach-two.css">
<div>
    <c:if test="${ i_am_curator != null}">
        <tiles:insertAttribute name="group_info"/><br>

    </c:if>
    <c:if test="${ (i_am_curator != null) ||  (i_am_zavvidd != null)}">
        <tiles:insertAttribute name="vid_menu"/><br>

    </c:if>


    <tiles:insertAttribute name="content"/>
</div>