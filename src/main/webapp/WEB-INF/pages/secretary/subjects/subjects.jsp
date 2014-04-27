<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div class="header-of-main">Предмети</div>
<div class="main-part">
    <div class="table">
    <table>
        <tr>
            <th>Id</th>
            <th>Назва</th>
            <th>Кредити ECTS</th>
            <th>Години</th>
            <th>Форма контролю</th>

        </tr>
        <c:forEach items="${subj_list}" var="subj">
        <form action="/secretary/subjects/alter_subject" method="post">
            <tr>
                <td><input type="text" size="3" name="id" value="${subj.getId()}" readonly/></td>
                <td><input type="text" name="name" size="50" value="${subj.getName()}"/></td>
                <td><input type="text" name="ects" onkeypress="return isNumberPointKey(event)"  value="${subj.getEcts()}"/></td>
                <td><input type="text" name="hours" size="5" onkeypress="return isNumberKey(event)"  value="${subj.getHours()}"/></td>
                <td><select name="controlForm">
                    <option value=""  selected></option>
                    <option value="Іспит" <c:if test="${subj.getControlForm().equals('Іспит')}">selected</c:if>>Іспит</option>
                    <option value="Залік" <c:if test="${subj.getControlForm().equals('Залік')}">selected</c:if>>Залік</option>
                    <option value="Д/З" <c:if test="${subj.getControlForm().equals('Д/З')}">selected</c:if>>Д/З</option>
                </select></td>
                <td>
                    <input type="submit"  value="upd"/>

                    <input type="reset" value="cancel"/>

                </td>
            </tr>
            </form>
        </c:forEach>
        <tr></tr>
        <form action="/secretary/subjects/add_subject" method="post">
            <tr>
                <td><input type="text" name="id" size="3" value="" readonly/></td>
                <td><input type="text" name="name" size="50" value=""/></td>
                <td><input type="text" name="ects" onkeypress="return isNumberPointKey(event)"  value=""/></td>
                <td><input type="text" name="hours" size="5" onkeypress="return isNumberKey(event)"  value=""/></td>
                <td><select name="controlForm">
                    <option value=""  selected></option>
                    <option value="Іспит">Іспит</option>
                    <option value="Залік">Залік</option>
                    <option value="Д/З">Д/З</option>
                </select></td>
                <td>
                    <input type="submit"  value="add"/>

                    <input type="reset" value="cancel"/>

                </td>
            </tr>
        </form>

    </table>
</div>

    </div>


