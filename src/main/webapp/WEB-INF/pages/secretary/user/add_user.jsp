<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/register.css">
<div class="header-of-main">НОВИЙ КОРИСТУВАЧ</div>
<div class="main-part">

    <div>




    </div>
    <img class="lock" alt="lock" src="${pageContext.request.contextPath}/resources/img/locked.png"/>
        <div class="form">
            <form novalidate="novalidate" action="/secretary/users/add_user" method="post">
                <input type="hidden" name="role" value="${role}">
                <input type="hidden" name="id" value="${id}">
                <input class="email" required="required"  type="email" name = "login"   placeholder="Username"/>
                <input class="password"  required="required" type="text"  name = "password" placeholder="Password"/>

                <input type="submit"  value="ok"/>
                <input type="reset" value="cancel"/>
            </form>
        </div>
</div>

</div>
