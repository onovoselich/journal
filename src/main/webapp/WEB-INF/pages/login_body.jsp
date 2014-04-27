<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
<div >

<c:if test="${not empty error_message}">
     <span class="message" ><font color="red" >${error_message}</font></span>
    </c:if>
    <div class="header-of-main"></div>
    <div class="main-part">


        <div>

            ВХІД У СИСТЕМУ

        </div>
        <img class="lock" alt="lock" height="87px" src="${pageContext.request.contextPath}/resources/img/locked.png"/>
        <div class="form">
            <form novalidate="novalidate" method = "post" action="/j_spring_security_check">
                <input class="email" type="email"  name = "j_username" placeholder="Username"/>
                <input class="password" type="password" name = "j_password" placeholder="Password"/>
                <input type="submit" name="btn-login" value="Login"/>
            </form>
        <span>
            <a href="#">

                Забув пароль

            </a>
        </span>
        </div>

    </div>


</div>