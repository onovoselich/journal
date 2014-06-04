<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/content-popup.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/student-page.css">
<div>
    <c:if test="${not empty alarm}">
        <div class="header-of-main" style="background-color: red">Увага</div>
        <div class="main-part"><p>У вас 2 або більше негативних оцінок!<br/>Ви будетевідраховані.</p></div>
    </c:if>


    <div class="header-of-main student-card student-card-header">Картка студента<a href="#" id="studentinfopopup"
                                                                                   class="popup">↑</a></div>
    <div class="main-part student-card-main">
        <tiles:insertAttribute name="stud_card"/>
    </div>

    <tiles:insertAttribute name="content"/>
</div>