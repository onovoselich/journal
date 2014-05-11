<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8"  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Курсач НОВОСАД</title>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width">
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.datepicker.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.core.css">--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datepicker.js"></script>--%>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/homepage.css">


</head>
<body>
<header>

<tiles:insertAttribute name="header"/>
</header>

<section class="content-of-home-page">
<tiles:insertAttribute name="body_title"/>
    <div class="main-content">

        <c:if test="${not empty message}">
            <span class="message">MESSAGE: <%=response.getHeader("message")%></span>
        </c:if>
        <tiles:insertAttribute name="body"/>
    </div>
</section>
<footer>
<tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>