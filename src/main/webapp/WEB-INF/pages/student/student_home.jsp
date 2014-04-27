<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="header-of-main">${student}, це ваш кабінет</div>
<div class="main-part bottom">
    <div class="tabs"><ul>
        <li><a href="/student/1sumestr">I</a></li>
        <li><a href="/student/2sumestr">II</a></li>
        <li><a href="/student/3sumestr">III</a></li>
        <li><a href="/student/4sumestr">IV</a></li>
        <li><a href="/student/5sumestr">V</a></li>
        <li><a href="/student/6sumestr">VI</a></li>
        <li><a href="/student/7sumestr">VII</a></li>
        <li><a href="/student/8sumestr">VIII</a> </li>
    </ul>
    </div>

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
