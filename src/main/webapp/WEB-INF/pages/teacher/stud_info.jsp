<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/student-page.css">
<div class="header-of-main student-card student-card-header">Картка студента</div>
<div class="main-part student-card-main">
    <tiles:insertAttribute name="student_card"/>
</div>