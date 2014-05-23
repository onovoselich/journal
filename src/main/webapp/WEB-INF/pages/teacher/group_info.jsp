<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/studinfo-display.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/content-popup.js"></script>

<div class="header-of-main">Це ваша група: ${my_group} (${my_group.getEducYear()} курс)<a href="#" id="teachergrouppopup" class="popup">↑</a></div>
<div class="main-part">

    <div class="list-student">
        <ol>
            <c:forEach var="stud" items="${grp_students}">
                <li >
                       <a id="a${stud.getId()}" href="#"> ${stud}</a>
                </li>
            </c:forEach>
        </ol>
</div>
<c:forEach var="stud"  items="${grp_students}">
       <c:set var="student" value="${stud}" scope="request"/>
    <c:if test="${not empty student}">
        <div class="student-card-left" id="d${student.getId()}"><div class="header-of-main student-card student-card-header">

            Картка студента

            <a href="#">x</a>

        </div><div class="main-part student-card-main">
        <tiles:insertAttribute name="student_card"/>
            </div>
        </div>
    </c:if>
</c:forEach>



</div>
