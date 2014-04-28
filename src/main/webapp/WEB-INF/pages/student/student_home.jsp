<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="ua.softserve.logic.Number" %>
<div class="header-of-main">${student}, це ваш кабінет</div>
<div class="main-part bottom">
    <div class="tabs"><ul>

            <%for(int i=1; i<=8;i++){%>
        <li><a href="/student/<%=i%>sumestr"><%=Number.arabic2roman(i)%></a></li>

            <%;}%>

            <div class="col">
                <form action="/student/vidomist" >

<c:forEach var="subj" items="${subjectList}">
                    <div class="input">
                        <input type="checkbox" name="subj" id="sub${subj.getId()}" value="${subj.getId()}" />
                        <label for="sub${subj.getId()}">${subj.getName()}(${subj.getControlForm()})</label>
                    </div>


</c:forEach>

                    <input type="hidden" name="sum" value="${sum}">
                    <input class="btn-ok" type="submit" value="OK" >                       </form>
            </div>


</div>
