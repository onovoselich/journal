<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/homepage.css">
<div>
    <sec:authorize access="isAuthenticated()">

        <!--Доступне студенту-->
        <sec:authorize access="hasRole('ROLE_STUDENT')">
            <span class="message">Вітаємо! Ви ввійшли як студент</span>
        </sec:authorize>
        <!--Доступне викладачу-->
        <sec:authorize access="hasRole('ROLE_TEACHER')">
            <span class="message"> Вітаємо! Ви ввійшли як викладач</span>
        </sec:authorize>
        <!--Доступне секретарю-->
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <span class="message">Вітаємо! Ви ввійшли як адміністратор</span>
        </sec:authorize>

    </sec:authorize>

    <div class="header-of-main">КОРИСТУВАЧІ:</div>
    <div class="main-part">
        <img src="${pageContext.request.contextPath}/resources/img/graf.png" alt="IMAGE">

        <div class="main-text">

            <b>Студенти:</b>
            <br/>
            olololeg [123];<br/>

            <b>Викладачі:</b><br/>
            lev [123];<br/>
            dldl [123];<br/>

            <b>Адміністратор:</b><br/>
            admin [123];<br/>

        </div>
    </div>

</div>
