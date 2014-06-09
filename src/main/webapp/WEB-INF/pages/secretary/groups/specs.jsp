<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/number_validation.js"></script>
<div class="header-of-main">СПЕЦІАЛЬНОСТІ</div>
<div class="main-part">
    <div class="table">
        <table>
            <tbody>
            <tr>
                <th>Id</th>
                <th>Код</th>
                <th>Назва</th>
                <th>Відділення</th>
                <th>Зав. відділення</th>
            </tr>
            <c:forEach var="spec" items="${specs_list}">
                <form action="/secretary/groups/alter_spec" enctype="multipart/form-data" method="post">
                    <tr>
                        <td><input type="text" size="3" name="id" value="${spec.getId()}" readonly/></td>
                        <td><input type="text" size="8" required="required" class="float" name="degree"
                                   value="${spec.getDegree()}"/></td>
                        <td><input type="text" size="24" required="required" name="specName"
                                   value="${spec.getSpecName()}"/></td>
                        <td><input type="text" size="24" required="required" name="viddil" value="${spec.getViddil()}"/>
                        </td>
                        <td><select name="zavViddilId" required="required">
                            <option value="" selected></option>
                            <c:forEach var="teacher" items="${teacher_list}">
                                <option value="${teacher.getId()}"
                                        <c:if test="${teacher.getId().equals(spec.getZavViddil().getId())}">selected</c:if>>${teacher} </option>
                            </c:forEach>

                        </select></td>
                            <%--<td><a href="/secretary/groups?vid=${spec.getId()}"><img class="str-down" alt="right"
                                                                                     src="${pageContext.request.contextPath}/resources/img/str-right.png"/></a>
                            </td>--%>
                        <td>
                            <input type="submit" value="upd"/>
                            <input type="reset" value="cancel"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            <tr></tr>
            <form action="/secretary/groups/add_spec" enctype="multipart/form-data" method="post">
                <tr>
                    <td><input type="text" size="3" value="" readonly/></td>
                    <td><input type="text" size="8" required="required" name="degree" class="float" value=""/></td>
                    <td><input type="text" size="24" required="required" name="specName" value=""/></td>
                    <td><input type="text" size="24" required="required" name="viddil" value=""/></td>
                    <td><select name="zavViddilId" required="required">
                        <option value="" selected></option>
                        <c:forEach var="teacher" items="${teacher_list}">
                            <option value="${teacher.getId()}">${teacher} </option>
                        </c:forEach>

                    </select></td>
                    <%--<td></td>--%>
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
