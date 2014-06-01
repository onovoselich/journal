<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="title-of-content">
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
    <div class="photo-kep">
        <img src="${pageContext.request.contextPath}/resources/img/kep.png" alt="kep">

        <div class="right-part-title">
            Коледж електронних приладів Івано-Франківського національного технічного університету нафти і газу створено
            Постановою Кабінету Міністрів України № 526 від 29 травня 1997 року та наказом Міністерства освіти України №
            218 від 20.06.1997 року на базі Івано-Франківського технікуму електронних приладів
        </div>
    </div>
</div>

