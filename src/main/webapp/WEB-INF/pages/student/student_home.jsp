<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="ua.softserve.logic.Number" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tabs.js"></script>
<script>
    var sum = parseInt('<c:out value="${sum}"/>');


</script>
<div class="header-of-main">${student}, це ваш кабінет</div>
<div class="main-part bottom">
    <div class="tabs"><ul>

            <%for(int i=1; i<=8;i++){%>
        <li><a id="tab_<%=i%>" href="#"><%=Number.arabic2roman(i)%></a></li>

            <%;}%>
                </ul></div>

            <div class="col">

                <c:forEach var="i" items="${subjectList}">
                    <form action="/student/vidomist" method="post">
                    <div class="tabcontent" id="content_${i.key}">
                    <c:forEach var="subj" items="${i.value}">

                    <div class="input">
                        <input type="checkbox" name="subj" id="${i.key}sub${subj.getId()}" value="${subj.getId()}" />
                        <label for="${i.key}sub${subj.getId()}">${subj.getName()}(${subj.getControlForm()})</label>
                    </div>

                    </c:forEach>
                        <input type="hidden" name="sum" value="${i.key}">
                        <input class="btn-ok" type="submit" value="OK" >
                    </form>
            </div>
</c:forEach>

            </div>


</div>