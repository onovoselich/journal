<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


        <img  src="${pageContext.request.contextPath}/resources/photoes/${student.getId()}.jpg" alt="">
        <div class="main-text">
            <ul>
                <li class="student-name">${student.getFullName()}</li>
                <c:if test="${not empty student.getbDate()}">
                    <li class="birth-day">${student.getbDate()} р.н.</li>
                </c:if>
                <c:if test="${not empty student.getEducForm()}">
                    <li class="study-form">${student.getEducForm()} форма навчання</li>
                </c:if>
                <c:if test="${not empty student.getEdukType()}">
                    <li class="derg-zamovlenya">${student.getEdukType()} замовлення</li>
                </c:if>

                <li class="address">${student.getAddress()}</li>
                <li class="phone">${student.getPhone()}</li>
            </ul>
        </div>


