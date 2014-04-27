<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<div class="header-of-main">ПРЕДМЕТИ ГРУПИ ${group}</div>
<div class="main-part">
<div class="table">
        <table>
            <tr>
                <th>Предмет</th>
                <th>Викладач</th>
            </tr>
            <c:forEach var="tuple" items="${tgs_list}">
                <form action="/secretary/groups/upd_tgs" method="post">
                    <input type="hidden" name="gr_id" value="${group.getId()}">

                    <input type="hidden" name="id" value="${tuple.key}">
                <tr>
                    <td><select name="subj_id">
                        <option value="" selected></option>
                        <c:forEach items="${subj_list}" var="subj">
                            <option value="${subj.getId()}" <c:if test="${subj.getId() == tuple.value.getX().getId()}">selected</c:if>>${subj}</option>
                        </c:forEach>
                    </select></td>
                    <td><select name="teac_id">
                        <option value="" selected></option>
                        <c:forEach items="${teac_list}" var="teac">
                            <option value="${teac.getId()}" <c:if test="${teac.getId() == tuple.value.getY().getId()}">selected</c:if>>${teac}</option>
                        </c:forEach>
                    </select></td>
                    <td>
                        <input type="submit"  value="upd"/>
                        <input type="reset" value="cancel"/>
                    </td>
                </tr>
                </form>
            </c:forEach>
            <form action="/secretary/groups/add_tgs" method="post">

                <input type="hidden" name="gr_id" value="${group.getId()}">
                <tr>
                    <td><select name="subj_id">
                        <option value="" selected></option>
                        <c:forEach items="${subj_list}" var="subj">
                            <option value="${subj.getId()}" >${subj}</option>
                        </c:forEach>
                    </select></td>
                    <td><select name="teac_id">
                        <option value="" selected></option>
                        <c:forEach items="${teac_list}" var="teac">
                            <option value="${teac.getId()}" >${teac}</option>
                        </c:forEach>
                    </select></td>
                    <td>
                        <input type="submit" value="add"/>
                        <input type="reset" value="cancel"/>
                    </td>
                </tr>
            </form>

        </table>
</div>
    </div>


