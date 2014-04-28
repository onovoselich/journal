<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page import="ua.softserve.logic.Number" %>

<div class="header-of-main">ГРУПИ ТА ПРЕДМЕТИ ВИКЛАДАЧА ${teacher}</div>
<div class="main-part">
    <div class="table">
        <table>
            <tr>
                <th>Група</th>
                <th>Предмет</th>
                <th>Симестр</th>
            </tr>

                <c:forEach var="tuple" items="${tgs_list}">
                    <form action="/secretary/teachers/upd_tgs" method="post">
                        <input type="hidden" name="teac_id" value="${teacher.getId()}">
                        <input type="hidden" name="id" value="${tuple.key}">
                        <tr><td><select name="gr_id" required="required">
                            <option value="" selected></option>
                            <c:forEach items="${grp_list}" var="grp">
                                <option value="${grp.getId()}" <c:if test="${grp.getId() == tuple.value.getX().getId()}">selected</c:if>>${grp}</option>
                            </c:forEach>
                        </select></td>
                            <td><select name="subj_id" required="required" >
                                <option value="" selected></option>

                                <c:forEach items="${subj_list}" var="subjlist">
                                    <optgroup label="${subjlist.key.getSpecName()}">
                                        <c:forEach items="${subjlist.value}" var="subj">

                                            <option <c:if test="${subj.getId() == tuple.value.getY().getId()}">selected</c:if> value="${subj.getId()}" >${subj}</option>
                                        </c:forEach>
                                    </optgroup>
                                </c:forEach>
                            </select></td>
                            <td>
                                <c:forEach var="i" begin="1" end="8">

                                    <input <c:if test="${tuple.value.getZ().contains(i)}">checked </c:if> type="checkbox" name="sum" id="sum${i}" value="${i}">
                                    <label for="sum${i}"> <%=Number.arabic2roman((Integer)pageContext.getAttribute("i"))%> </label>
                                </c:forEach>
                            </td>
                            <td>
                                <input type="submit"  value="upd"/>
                                <input type="reset" value="cancel"/>
                            </td>
                        </tr>
                    </form>
            </c:forEach>
            <form action="/secretary/teachers/add_tgs" method="post">
                <input type="hidden" name="teac_id" value="${teacher.getId()}">
                <tr><td><select name="gr_id" required="required" >
                    <option value="" selected></option>
                    <c:forEach items="${grp_list}" var="grp">
                        <option value="${grp.getId()}" >${grp}</option>
                    </c:forEach>
                </select></td>
                    <td><select name="subj_id" required="required" >
                        <option value="" selected></option>
                        <c:forEach items="${subj_list}" var="subjlist">
                            <optgroup label="${subjlist.key.getSpecName()}">
                            <c:forEach items="${subjlist.value}" var="subj">

                                <option value="${subj.getId()}" >${subj}</option>
                            </c:forEach>
                            </optgroup>
                        </c:forEach>
                    </select></td>
                    <td>
                        <c:forEach var="i" begin="1" end="8">

                            <input  type="checkbox" name="sum" id="sum${i}" value="${i}">
                            <label for="sum${i}"> <%=Number.arabic2roman((Integer)pageContext.getAttribute("i"))%> </label>
                        </c:forEach>
                    </td>
                    <td>
                        <input type="submit"  value="add"/>
                        <input type="reset" value="cancel"/>
                    </td>
                </tr>
            </form>
        </table>
</div>

    </div>


