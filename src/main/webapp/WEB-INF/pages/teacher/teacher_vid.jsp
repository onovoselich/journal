<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="ua.softserve.logic.Number" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.datepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.theme.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.core.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scroll-after-post.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/number_validation.js"></script>
<script>
    var sum = parseInt('<c:out value="${cur_sum}"/>');
    $(document).ready(function () {
        $(".data").datepicker();
        $(".tablesorter").tablesorter({sortList: [
            [0, 0]
        ]});
    });

</script>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/students.css">

<div class="header-of-main"> ${group} - ${subject} (${subject.getControlForm()})</div>
<div class="main-part">

    <div class="tabs">
        <ul>

            <c:forEach var="i" items="${sum_lst}">

                <li><a id="tab_${i}" href="#"><%=Number.arabic2roman((Integer) pageContext.getAttribute("i"))%>
                </a></li>

            </c:forEach>
        </ul>
    </div>

    <c:forEach var="i" items="${sum_lst}">
        <div class="table tabcontent" id="content_${i}">
            <div class="outfiles">
                <ul>
                    <li>
                        <a href="/teacher/vidomist.xls?group_id=${group.getId()}&subject_id=${subject.getId()}&sum=${i}"><img
                                alt="XLS" src="${pageContext.request.contextPath}/resources/img/xls.png"/></a></li>
                    <li>
                        <a href="/teacher/vidomist.pdf?group_id=${group.getId()}&subject_id=${subject.getId()}&sum=${i}"><img
                                alt="PDF" src="${pageContext.request.contextPath}/resources/img/pdf.png"/></a></li>
                </ul>

            </div>

            <table id="table${i}" class="tablesorter">
                <thead>
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
                </thead>
                <tbody>
                <%int i = 0;%>
                <c:forEach items="${stud_mark_list.get(i)}" var="row">
                <tr>
                    <form method="post">
                        <input type="hidden" name="student_id" value="${row.key.getId()}">
                        <input type="hidden" name="teac_subj_grp_id" value="${teac_subj_grp_id.get(i)}">
                        <input type="hidden" name="group_id" value="${group.getId()}">
                        <input type="hidden" name="subject_id" value="${subject.getId()}">
                        <td>
                            <%=++i%>
                        </td>
                        <td>
                            <a href="/teacher/studentinfo?stud_id=${row.key.getId()}"> ${row.key}</a>
                        </td>
                        <td>
                                ${row.key.getGradebook()}
                        </td>
                        <td>
                                <%--<select name="mark">
                                    <option value="" selected></option>
                            <c:forEach var="j" begin="1" end="12">
                                        <option value="${j}" <c:if test="${row.value.getMark() == j}">selected</c:if>>${j}</option>
                                   </c:forEach>
                                </select>--%>
                                    <input class="integer" size="3" type="number" name="mark" max="12" min="1"
                                    <c:if test="${row.key.getStatus() == 'vidrah'}">
                                           readonly="readonly" disabled="disabled"
                                    </c:if>

                                           value="${row.value.getMark()}">

                        </td>
                        <td>
                            <input name="date" class="data" size="9" type="text" value="${row.value.getDate()}"
                                    <c:if test="${row.key.getStatus() == 'vidrah'}">
                                        readonly = "readonly" disabled="disabled"
                                    </c:if>
                                    />


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
    </c:forEach>
    <br/>
    <small>ПІДКАЗКА: Впорядкувати за кількома полями можна натиснувши Shift та обравши друге, третє, або навіть четверте
        поле!
    </small>
    <br/>

</div>
