<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/js/interactive_form_teacher.js"></script>
<script>

    var gr_sub = new Array();
    $(document).ready(function () {
        gr_sub = JSON.parse('<c:out value="${group_json}"/>'.replace(/&#034;/g, "\""));


        createGroupList(gr_sub);

    });
</script>

<div class="header-of-main">${teacher}, це ваш кабінет</div>
<div class="main-part vidomist">

    <form method="get" action="/teacher/vidomist">

        <div>
        <span>

            Групи:

        </span>
            <ol id="gr_list">
                <%--                <%int i = 0;%>
                <c:forEach var="group" items="${group_list}">
                                <li>
                                    <input id="group_id<%=++i%>" type="radio" value="${group.getId()}" name="group_id"/>
                                    <label for="group_id<%=i%>">

                                            ${group}

                                    </label>
                                </li>
                            </c:forEach>--%>
            </ol>
        </div>
        <div>
        <span>

            Предмети:

        </span>
            <ol id="subj_list">
                <%--
                <c:forEach var="subj" items="${subject_list}">
                   <li>
                                    <input  id="subject_id<%=++i%>" type="radio" value="${subj.getId()}" name="subject_id"/>
                                    <label for="subject_id<%=i%>">

                                            ${subj}

                                    </label>
                                </li>
                                </c:forEach>--%>
            </ol>
        </div>
        <input type="submit" value="Відомість"/>
    </form>

</div>
