<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
<div class="title-of-content">--%>
<div class="header-of-top-part">
        <ul>


            <!--Доступне усім-->
            <li><a href="/">Головна</a></li>
            <!--Доступне неавторизованим-->
            <sec:authorize access="isAnonymous()">
                <li><a href="/login">Увійти</a></li>
            </sec:authorize>
            <!--Доступне авторизованим-->
            <sec:authorize access="isAuthenticated()">

                <!--Доступне студенту-->
                <sec:authorize access="hasRole('ROLE_STUDENT')">

                    <li><a href="/student">Мій кабінет</a><br></li>
                </sec:authorize>
                <!--Доступне викладачу-->
                <sec:authorize access="hasRole('ROLE_TEACHER')">

                    <li><a href="/teacher">Мій кабінет</a><br></li>
                </sec:authorize>
                <!--Доступне секретарю-->
                <sec:authorize access="hasRole('ROLE_ADMIN')">

                    <li><a href="/secretary/teachers">Викладачі</a></li>
                    <li><a href="/secretary/students">Студенти</a></li>
                    <li><a href="/secretary/groups">Групи</a></li>
                    <li><a href="/secretary/subjects">Предмети</a></li>
                </sec:authorize>

                <li><a href="/logout">Вихід</a></li>
            </sec:authorize>
        </ul>
    </div>

<%--</div>--%>

