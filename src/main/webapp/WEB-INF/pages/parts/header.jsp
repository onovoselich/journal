<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--<div class="left-part-header">
    <span>КЕП</span>
    <span>ІФНТУНГ</span>
</div>--%>
<div class="right-part-header">

    <span><a href="/" id="active">:: Home</a></span>

    <sec:authorize access="isAnonymous()">
        <span><a href="/login">:: Sign in</a></span>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <span><a href="/logout">:: Logout</a></span>
    </sec:authorize>
</div>