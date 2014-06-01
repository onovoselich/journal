<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/register.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<div class="header-of-main">НОВИЙ ПАРОЛЬ</div>
<div class="main-part">


    <div>


    </div>
    <img class="lock" alt="lock" src="${pageContext.request.contextPath}/resources/img/locked.png"/>

    <div class="form">
        <form novalidate="novalidate" action="/secretary/users/upd_user" method="post">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="referer" value="<%=request.getHeader("referer")%>">
            <input class="email" type="email" name="login" readonly value="${login}" placeholder="Username"/>
            <input class="password" required="required" type="text" name="password" placeholder="Password"/>
            <input type="submit" value="ok" name="btn-login"/>
            <input type="reset" value="cancel" name="btn-reset"/>
        </form>
    </div>
</div>
