<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<!DOCTYPE html>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/student-page.css">
<div>

    <div class="header-of-main student-card student-card-header">Картка студента</div>
    <div class="main-part student-card-main">
        <tiles:insertAttribute name="stud_card"/>
    </div>

    <tiles:insertAttribute name="content"/>
</div>