<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="ua.softserve.logic.Number" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/student-vid.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tabs.js"></script>
<script>
    var sum = parseInt('<c:out value="${sum}"/>');
</script>

<div class="header-of-main">відомість успішності</div>
<div class="main-part">
    <div class="tabs">
        <ul>

            <%for (int i = 1; i <= 8; i++) {%>
            <li><a id="tab_<%=i%>" href="#"><%=Number.arabic2roman(i)%>
            </a></li>

            <%
                    ;
                }
            %>
        </ul>
        <%--<c:if test="${toshow != null}">

            <div class="tabs"><ul><li>  <a href="/student/vidomist?sum=${sum}">Всі предмети</a></li>
            </ul></div>
        </c:if>--%>

        <c:forEach var="i" items="${marks}">
            <div id="content_${i.key}" class="table tabcontent">
                <table>


                    <tr>
                        <td></td>
                        <c:if test='${!i.value.get("Іспит").isEmpty()}'>
                            <th colspan=${i.value.get("Іспит").size()}>
                                Іспити
                            </th>
                        </c:if>
                        <c:if test='${!i.value.get("Залік").isEmpty()}'>
                            <th colspan=${i.value.get("Залік").size()}>
                                Заліки
                            </th>
                        </c:if>
                        <c:if test='${!i.value.get("Д/З").isEmpty()}'>
                            <th colspan=${i.value.get("Д/З").size()}>
                                Д\З
                            </th>
                        </c:if>

                    </tr>
                    <tr>
                        <th>Предмет</th>
                        <c:forEach var="columnName" items="${i.value}">


                            <c:forEach var="cc" items="${columnName.value}">
                                <td>
                                    <div>
                                            ${cc.key}</div>
                                </td>
                            </c:forEach>

                        </c:forEach>
                    </tr>
                    <tr></tr>
                    <tr>
                        <th>Оцінка</th>
                        <c:forEach var="columnName" items="${i.value}">

                            <c:forEach var="cc" items="${columnName.value}">
                                <td>
                                        ${cc.value}
                                </td>
                            </c:forEach>

                        </c:forEach>
                    </tr>
                    <tr>
                        <th>Викладач</th>
                        <c:forEach var="columnName" items="${i.value}">

                            <c:forEach var="cc" items="${columnName.value}">
                                <td>
                                    <div>
                                            ${cc.key.getTeacher()}</div>
                                </td>
                            </c:forEach>

                        </c:forEach>
                    </tr>
                </table>
            </div>
        </c:forEach>

    </div>
