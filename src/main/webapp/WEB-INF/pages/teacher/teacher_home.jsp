<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>




    <div class="header-of-main">${teacher}, це ваш кабінет</div>
    <div class="main-part vidomist">

    <form method="get" action="/teacher/vidomist">

        <div>
        <span>

            Групи:

        </span>
            <ol>
                <%int i = 0;%>
<c:forEach var="group" items="${group_list}">
                <li>
                    <input id="group_id<%=++i%>" type="radio" value="${group.getId()}" name="group_id"/>
                    <label for="group_id<%=i%>">

                            ${group}

                    </label>
                </li>
            </c:forEach>
            </ol>
        </div>
        <div>
        <span>

            Предмети:

        </span>
            <ol>

<c:forEach var="subj" items="${subject_list}">
                <li>
                    <input id="subject_id<%=++i%>" type="radio" value="${subj.getId()}" name="subject_id"/>
                    <label for="subject_id<%=i%>">

                            ${subj}

                    </label>
                </li>
                </c:forEach>
            </ol>
        </div>
        <input type="submit" value="Відомість"/>

    </form>

</div>
