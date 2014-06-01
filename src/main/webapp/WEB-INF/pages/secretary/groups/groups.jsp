<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.datepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.theme.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.core.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datepicker.js"></script>
<script>
    $(document).ready(function () {
        $(".data").datepicker();
    });
</script>
<div class="header-of-main">ГРУПИ</div>
<div class="main-part">
    <a href="/secretary/groups">all groups</a> <br>

    <div class="table">
        <table>
            <tbody>
            <tr>
                <th>Id</th>
                <th>Назва</th>
                <th>Спеціальність</th>
                <th>Вступ</th>
                <th>Випуск</th>
                <th>Куратор</th>
                <th>Курс</th>
            </tr>
            <c:forEach var="group" items="${groups_list}">
                <form action="/secretary/groups/alter_group" method="post">
                    <tr>
                        <td><input name="id" size="2" type="text" value="${group.getId()}" readonly/></td>
                        <td><input name="name" size="7" type="text" required="required" value="${group.getName()}"/>
                        </td>
                        <td><select name="degree_id" required="required">
                            <option value="" selected></option>
                            <c:forEach var="spec" items="${specs_list}">
                                <option value="${spec.getId()}"
                                        <c:if test="${spec.getId() == group.getSpec().getId()}">selected </c:if>>${spec.getDegree()}
                                    | ${spec.getSpecName()}</option>
                            </c:forEach>
                        </select></td>
                        <td><input name="startDate" class="data" size="9" type="text" value="${group.getStartDate()}"/>
                        </td>
                        <td><input name="finishDate" size="9" class="data" type="text"
                                   value="${group.getFinishDate()}"/></td>
                        <td><select name="curator_id" required="required">
                            <option value="" selected></option>
                            <c:forEach var="teacher" items="${teacher_list}">
                                <option value="${teacher.getId()}"
                                        <c:if test="${teacher.getId().equals(group.getCurator().getId())}">selected</c:if>>${teacher} </option>
                            </c:forEach>
                        </select></td>
                        <td>


                            <select name="educYear" required="required">
                                <option value="" selected></option>
                                <c:forEach var="i" begin="1" end="4">
                                    <option value="${i}"
                                            <c:if test="${group.getEducYear() == i}">selected</c:if>>${i}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><a href="/secretary/students/group${group.getId()}"><img class="str-down" alt="right"
                                                                                     src="${pageContext.request.contextPath}/resources/img/str-right.png"/></a>
                        </td>
                        <td>
                            <input type="submit" value="upd"/>

                            <input type="reset" value="cancel"/>

                        </td>
                        <td>
                            <a href="/secretary/groups/appointments?id=${group.getId()}">appointments</a>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            <form action="/secretary/groups/add_group" method="post">
                <tr>
                    <td><input name="id" size="2" type="text" value="" readonly/></td>
                    <td><input name="name" required="required" size="7" type="text" value=""/></td>
                    <td><select required="required" name="degree_id">
                        <option value="" selected></option>
                        <c:forEach var="spec" items="${specs_list}">
                            <option value="${spec.getId()}">${spec.getDegree()} | ${spec.getSpecName()}</option>
                        </c:forEach>
                    </select></td>
                    <td><input name="startDate" class="data" size="9" type="text" value=""/></td>
                    <td><input name="finishDate" class="data" size="9" type="text" value=""/></td>
                    <td><select name="curator_id" required="required">
                        <option value="" selected></option>
                        <c:forEach var="teacher" items="${teacher_list}">
                            <option value="${teacher.getId()}">${teacher} </option>
                        </c:forEach>
                    </select></td>
                    <td>

                        <select name="educYear" required="required">
                            <option value="" selected></option>
                            <c:forEach var="i" begin="1" end="4">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                    <td>
                        <input type="submit" value="add"/>

                        <input type="reset" value="cancel"/>

                    </td>
                </tr>
            </form>
            </tbody>
        </table>
    </div>
</div>
