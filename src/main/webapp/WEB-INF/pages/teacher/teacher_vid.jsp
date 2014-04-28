<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="ua.softserve.logic.Number" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/students.css">

<div class="header-of-main">Відомість успішності групи ${group} з дисципліни ${subject}</div>
<div class="main-part">

    <div class="tabs"><ul>

        <c:forEach var="i" items="${sum_lst}">

            <li><a href="/teacher/vidomist?group_id=${group.getId()}&subject_id=${subject.getId()}&sum=${i}"><%=Number.arabic2roman((Integer)pageContext.getAttribute("i"))%></a></li>

        </c:forEach>
                </ul></div>
    <div class="table">
<table >
    <tbody>
    <tr>
        <th>
            № з/п
        </th>
        <th>
            П.І.Б. студента
        </th>
        <th>
            № зал книжки
        </th>
        <th>
            Оцінка(12б)
        </th>
        <th>
            Дата
        </th>

    </tr>
<%int i = 0;%>
        <c:forEach items="${stud_mark_list}" var="row">
            <tr>
            <form method="post">
                <input type="hidden" name="student_id" value="${row.key.getId()}">
                <input type="hidden" name="teac_subj_grp_id" value="${teac_subj_grp_id}">
                <input type="hidden" name="group_id" value="${group.getId()}">
                <input type="hidden" name="subject_id" value="${subject.getId()}">
                <td>
                    <%=++i%>
                </td>
                <td>
                    <a href="/teacher/studentinfo?stud_id=${row.key.getId()}" > ${row.key}</a>
                </td>
                <td>
                    ${row.key.getGradebook()}
                </td><td>
                <select name="mark">
                    <option value="" selected></option>
            <c:forEach var="i" begin="1" end="12">
                        <option value="${i}" <c:if test="${row.value.getMark() == i}">selected</c:if>>${i}</option>
                   </c:forEach>
                </select>

                </td>
               <td>
                    <input type="date" class="data" size="10" name="date" value="${row.value.getDate()}">


                <td>
                    <input type="submit" value="upd" formaction="/teacher/updMark/"/>
                <input type="submit" value="del" formaction="/teacher/delMark/"/>
                    <input type="reset" value="cancel"/>

                </td>
            </form>
            <tr>
        </c:forEach>

    </tr>

    </tbody>
    </table>

    </div>

</div>
