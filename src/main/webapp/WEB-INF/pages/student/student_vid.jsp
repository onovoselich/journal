<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="ua.softserve.logic.Number" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/student-vid.css">
<div class="header-of-main">відомість успішності за ${sum}-й симестр</div>
<div class="main-part">
    <div class="tabs"><ul>

        <%for(int i=1; i<=8;i++){%>
        <li><a href="/student/vidomist?sum=<%=i%>"><%=Number.arabic2roman(i)%></a></li>

        <%;}%>
    </ul>
    </div>
    <div class="tabs"><ul><li>  <a href="/student/vidomist?sum=${sum}">Всі предмети</a></li>
    </ul></div>
 <div class="table">
<table>


    <tr><td></td>
        <c:if test='${!marks.get("Іспит").isEmpty()}'>
        <th colspan=${marks.get("Іспит").size()}>
            Іспити
        </th></c:if>
        <c:if test='${!marks.get("Залік").isEmpty()}'>
        <th colspan=${marks.get("Залік").size()}>
            Заліки
        </th></c:if>
        <c:if test='${!marks.get("Д/З").isEmpty()}'>
        <th colspan=${marks.get("Д/З").size()}>
            Д\З
        </th></c:if>

    </tr>
    <tr><th>Предмет</th>
    <c:forEach var = "columnName" items = "${marks}">


            <c:forEach var = "cc" items = "${columnName.value}">
                <td><div>
                    ${cc.key}</div>
                </td>
            </c:forEach>

    </c:forEach>
    </tr>
    <tr></tr>
    <tr><th>Оцінка</th>
        <c:forEach var = "columnName" items = "${marks}">

            <c:forEach var = "cc" items = "${columnName.value}">
                <td>
                        ${cc.value}
                </td>
            </c:forEach>

        </c:forEach>
    </tr>
    <tr><th>Викладач</th>
        <c:forEach var = "columnName" items = "${marks}">

            <c:forEach var = "cc" items = "${columnName.value}">
                <td><div>
                        ${cc.key.getTeacher()}</div>
                </td>
            </c:forEach>

        </c:forEach>
    </tr>
    </table>
</div>

</div>
