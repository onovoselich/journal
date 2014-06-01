<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scroll-after-post.js"></script>

<div class="header-of-main">Викладачі</div>
<div class="main-part">
    <div class="table">
        <table>
            <tr>
                <th>id</th>
                <th>Ім'я</th>
                <th>По батькові</th>
                <th>Прізвище</th>
                <th>Login</th>
                <%--<th>Spec</th>
                <tH>Dyplom</tH>
                <th>Category</th>
                <th>Degree</th>--%>
            </tr>
            <c:forEach var="teac" items="${teachers_list}">
                <form action="/secretary/teachers/alter_teacher" method="post">
                    <tr>
                        <td><input type="text" name="id" size="3" value="${teac.getId()}" readonly/></td>
                        <td><input type="text" size="10" name="name" required="required" value="${teac.getName()}"/>
                        </td>
                        <td><input type="text" name="patronimic" value="${teac.getPatronimic()}"/></td>
                        <td><input type="text" size="10" name="surname" required="required"
                                   value="${teac.getSurname()}"/></td>
                        <td><c:if test="${not empty teac.getLogin()}">
                            ${teac.getLogin()} &nbsp<a href="/secretary/teachers/upd_user?login=${teac.getLogin()}">new password</a>
                        </c:if>
                            <c:if test="${empty teac.getLogin()}">
                                <a href="/secretary/teachers/add_user?id=${teac.getId()}">register</a>
                            </c:if></td>
                            <%-- <td><input type="text" name="spec" value="${teac.getSpec()}"/></td>
                             <td><input type="text" name="dyplom" value="${teac.getDyplom()}"/></td>
                             <td><input type="text" name="category" value="${teac.getCategory()}"/></td>
                             <td><input type="text" name="degree" value="${teac.getDegree()}"/></td>--%>
                        <td>
                            <input type="submit" value="upd"/>

                            <input type="reset" value="cancel"/>

                        </td>
                        <td>
                            <a href="/secretary/teachers/appointments?id=${teac.getId()}">appointments</a>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            <form action="/secretary/teachers/add_teacher" method="post">
                <tr></tr>
                <tr>
                    <td><input type="text" name="id" size="3" value="" readonly/></td>
                    <td><input type="text" size="10" name="name" required="required" value=""/></td>
                    <td><input type="text" name="patronimic" value=""/></td>
                    <td><input type="text" size="10" name="surname" required="required" value=""/></td>
                    <td>${teac.getLogin()}</td>
                    <%-- <td><input type="text" name="spec" value=""/></td>
                     <td><input type="text" name="dyplom" value=""/></td>
                     <td><input type="text" name="category" value=""/></td>
                     <td><input type="text" name="degree" value=""/></td>--%>
                    <td>
                        <input type="submit" value="add"/>

                        <input type="reset" value="cancel"/>

                    </td>
                </tr>

            </form>
        </table>
    </div>
</div>


