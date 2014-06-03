<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="ua.softserve.logic.Number" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/number_validation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scroll-after-post.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chbx_required.js"></script>
<script>
    var sum = "all";
</script>

<div class="header-of-main">Предмети</div>
<div class="main-part">
    <div class="tabs">
        <ul>
            <li><a id="tab_all" href="#">all</a></li>
            <c:forEach items="${spec_list}" var="sp">
                <li>
                    <a id="tab_${sp.getId()}" href="#">${sp}</a>
                </li>
            </c:forEach></ul>
    </div>
    <div class="table">
        <table>
            <tr>
                <th>Id</th>
                <th>Назва</th>
                <th>Скорочено</th>
                <th>Спеціальність</th>
                <th>ECTS</th>
                <th>Години</th>
                <th>Форма контролю</th>

            </tr>
            <c:forEach items="${subj_list}" var="subj">
                <tr class="tabcontent content_${subj.getSpetiality().getId()}">
                    <form action="/secretary/subjects/alter_subject" method="post">

                        <td><input type="text" size="3" name="id" value="${subj.getId()}" readonly/></td>
                        <td><input type="text" name="name" required="required" size="30" value="${subj.getName()}"/>
                        <td><input type="text" name="shortcut" size="10"
                                   value="${subj.getShortcut()}"/>
                        </td>
                        <td><select name="specId" required="required">
                            <option value="" selected></option>
                            <c:forEach items="${spec_list}" var="sp">

                                <option value="${sp.getId()}"
                                        <c:if test="${sp.getId() == subj.getSpetiality().getId()}">selected</c:if>>${sp}</option>
                            </c:forEach></select>
                        </td>
                        <td><input type="text" required="required" name="ects" size="6" class="float"
                                   value="${subj.getEcts()}"/></td>
                        <td><%--<input type="text" required="required"  name="hours" size="5"  class="integer"  value="${subj.getHours()}"/>--%>
                            <table>
                                <tr>
                                    <c:forEach var="i" begin="1" end="8">
                                        <td>
                                            <input
                                                    <c:if test="${subj.getSums().keySet().contains(i)}">checked </c:if>
                                                    required="required" type="checkbox" name="sum"
                                                    id="sum${i}_${subj.getId()}" value="${i}">
                                            <label for="sum${i}_${subj.getId()}"><%=Number.arabic2roman((Integer) pageContext.getAttribute("i"))%>
                                            </label>
                                        </td>
                                    </c:forEach>
                                </tr>
                                <tr>
                                    <c:forEach var="i" begin="1" end="8">
                                        <td>
                                            <input class="integer" size="1" type="text" name="sum${i}"
                                                   value="${subj.getSums().get(i)}">

                                        </td>
                                    </c:forEach>
                                </tr>
                            </table>
                        </td>
                        <td><select name="controlForm" required="required">
                            <option value="" selected></option>
                            <option value="Іспит" <c:if test="${subj.getControlForm().equals('Іспит')}">selected</c:if>>
                                Іспит
                            </option>
                            <option value="Залік" <c:if test="${subj.getControlForm().equals('Залік')}">selected</c:if>>
                                Залік
                            </option>
                            <option value="Д/З" <c:if test="${subj.getControlForm().equals('Д/З')}">selected</c:if>>
                                Д/З
                            </option>
                            <option value="other" <c:if test="${subj.getControlForm().equals('other')}">selected</c:if>>
                                Інше
                            </option>
                        </select></td>
                        <td>
                            <input type="submit" value="upd"/>

                            <input type="reset" value="cancel"/>

                        </td>

                    </form>
                </tr>
            </c:forEach>
            <tr></tr>
            <form action="/secretary/subjects/add_subject" method="post">
                <tr>
                    <td><input type="text" name="id" size="3" value="" readonly/></td>
                    <td><input type="text" name="name" required="required" size="30" value=""/></td>
                    <td><input type="text" name="shortcut" size="10" value=""/>
                    <td><select name="specId" required="required">

                        <option value="" selected></option>
                        <c:forEach items="${spec_list}" var="sp">
                            <option value="${sp.getId()}">${sp}</option>
                        </c:forEach></select>
                    </td>
                    <td><input type="text" name="ects" size="6" class="float" value=""/></td>
                    <td>
                        <table>
                            <tr>
                                <c:forEach var="i" begin="1" end="8">
                                    <td>
                                        <input required="required" type="checkbox" name="sum" id="sum${i}_a"
                                               value="${i}">
                                        <label for="sum${i}_a"><%=Number.arabic2roman((Integer) pageContext.getAttribute("i"))%>
                                        </label>
                                    </td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <c:forEach var="i" begin="1" end="8">
                                    <td>
                                        <input class="integer" size="1" type="text" name="sum${i}" value="">

                                    </td>
                                </c:forEach>
                            </tr>
                        </table>

                    </td>
                    <td><select name="controlForm" required="required">
                        <option value="" selected></option>
                        <option value="Іспит">Іспит</option>
                        <option value="Залік">Залік</option>
                        <option value="Д/З">Д/З</option>
                        <option value="other">Інше</option>
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


