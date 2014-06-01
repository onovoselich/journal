<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tr_sliding.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scroll-after-post.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tabs.js"></script>
<script>
    var sum = "all";

    $(document).ready(function () {
        $(".data").datepicker();
    });
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.core.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/students.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.datepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.ui.theme.css">

<div class="header-of-main">Студенти</div>
<div class="main-part">
<div class="tabs">
    <ul>
        <li><a id="tab_all" href="/secretary/students">all</a></li>
        <%-- <c:forEach items="${spec_list}" var="sp"><li>
             <a id="tab_s${sp.getId()}" href="/secretary/students/spec" >${sp}</a>
         </li></c:forEach></ul>
     </div>

     <div class="tabs"><ul>--%>
        <c:forEach items="${groups_list}" var="gr">
            <li>
                <a id="tab_g${gr.getId()}" href="/secretary/students/group">${gr}</a>
            </li>
        </c:forEach></ul>
</div>
<div class="table">
    <table>
        <tr>
            <th>Id</th>
            <th>Ім'я</th>
            <th>По батькові</th>
            <th>Прізвище</th>
            <th>Група</th>
            <th>№ зал. книжки</th>
            <th>Login</th>
            <%-- <th>Gender</th>
             <th>BDate</th>
             <th>Gradebook</th>
             <th>EducForm</th>
             <th>EducType</th>
             <th>Phone</th>
             <th>Address</th>--%>
        </tr>
        <c:forEach var="student" items="${students_list}">
            <form action="/secretary/students/alter_student" enctype="multipart/form-data" method="post">
                <tr class="tabcontent content_g${student.getGroupId()}">

                    <td><input name="id" size="3" type="text" readonly value="${student.getId()}"/></td>
                    <td><input name="name" required="required" type="text" size="8" value="${student.getName()}"/></td>
                    <td><input name="patronimic" type="text" size="8" value="${student.getPatronimic()}"/></td>
                    <td><input name="surname" required="required" type="text" size="8" value="${student.getSurname()}"/>
                    </td>
                    <td><select name="groupId" required="required">
                        <option value="" selected></option>
                        <c:forEach var="grp" items="${groups_list}">
                            <option value="${grp.getId()}"
                                    <c:if test="${grp.getId() == student.getGroupId()}">selected</c:if>>${grp.getName()}</option>
                        </c:forEach>
                    </select></td>
                    <td>
                        <input name="gradebook" required="required" type="text" size="8"
                               value="${student.getGradebook()}"/>
                    </td>
                    <td><c:if test="${not empty student.getLogin()}">
                        ${student.getLogin()} &nbsp<a href="/secretary/students/upd_user?login=${student.getLogin()}">new
                        password</a>
                    </c:if>
                        <c:if test="${empty student.getLogin()}">
                            <a href="/secretary/students/add_user?id=${student.getId()}">register</a>
                        </c:if></td>
                    <td>
                        <img class="str-down" alt="down"
                             src="${pageContext.request.contextPath}/resources/img/str-down.png"/>
                    </td>
                    <td rowspan="2">
                        <input type="submit" value="upd"/>
                        <input type="reset" value="cancel"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" class="none">
                        <img src="${pageContext.request.contextPath}/resources/photoes/${student.getId()}.jpg"
                             alt="" align="left"><br/>

                        <div class="list-span">

    <span>

        Стать:<br/>

    </span>
    <span>

        Дата нар:<br/>

    </span>
    <span>

        Форма навчання:<br/>

    </span>
    <span>

         Замовлення:<br/>

    </span>
    <span>

        Телефон:<br/>

    </span>
    <span>

        Адреса:<br/>

    </span>
                            <span>Фото:</span>

                        </div>
                        <div><select name="gender">
                            <option value="" selected></option>
                            <option value="ч" <c:if test="${student.getGender().equals('ч')}">selected</c:if>>ч.
                            </option>
                            <option value="ж" <c:if test="${student.getGender().equals('ж')}">selected</c:if>>ж.
                            </option>
                        </select><br/>
                            <input name="bDate" class="data" type="text" size="10" value="${student.getbDate()}"/><br/>
                            <select name="educForm">
                                <option value="" selected></option>
                                <option value="денна"
                                        <c:if test="${student.getEducForm().equals('денна')}">selected</c:if>>
                                    денна
                                </option>
                                <option value="заочна"
                                        <c:if test="${student.getEducForm().equals('заочна')}">selected</c:if>>
                                    заочна
                                </option>
                            </select><br/>

                            <select name="edukType">
                                <option value="" selected></option>
                                <option value="державне"
                                        <c:if test="${student.getEdukType().equals('державне')}">selected</c:if>>
                                    державне
                                </option>
                                <option value="платне"
                                        <c:if test="${student.getEdukType().equals('платне')}">selected</c:if>>
                                    платне
                                </option>
                            </select><br/>

                            <input name="phone" type="text" size="8" value="${student.getPhone()}"/><br/>
                            <input name="address" type="text" size="8"
                                   value="${student.getAddress()}"/><br/>

                            <input name="image" type="file"><br>
                        </div>
                    </td>
                </tr>
                </tr>

            </form>
        </c:forEach>
        <tr></tr>
        <form action="/secretary/students/add_student" method="post">
            <tr>
                <td><input name="id" size="3" type="text" readonly value=""/></td>
                <td><input name="name" required="required" type="text" size="8" value=""/></td>
                <td><input name="patronimic" type="text" size="8" value=""/></td>
                <td><input name="surname" required="required" type="text" size="8" value=""/></td>
                <td><select name="groupId" required="required">
                    <option value="" selected></option>
                    <c:forEach var="grp" items="${groups_list}">
                        <option value="${grp.getId()}">${grp.getName()}</option>
                    </c:forEach>
                </select></td>
                <td><input name="gradebook" required="required" type="text" size="8" value="${student.getGradebook()}"/>
                </td>
                <td></td>
                <td>
                    <img class="str-down" alt="down"
                         src="${pageContext.request.contextPath}/resources/img/str-down.png"/>
                </td>
                <td rowspan="2">
                    <input type="submit" value="add"/>
                    <input type="reset" value="cancel"/>
                </td>
            </tr>
            <tr>
                <td colspan="8" class="none">

                    Стать: <select name="gender">
                    <option value="" selected></option>
                    <option value="ч">ч.</option>
                    <option value="ж">ж.</option>
                </select><br/>
                    Дата нар:<input name="bDate" class="data" type="text" size="10" value="${student.getbDate()}"/><br/>
                    Форма навчання:<select name="educForm">
                    <option value="" selected></option>
                    <option value="денна">денна</option>
                    <option value="заочна">заочна</option>
                </select><br/>

                    Замовлення: <select name="edukType">
                    <option value="" selected></option>
                    <option value="державне">державне</option>
                    <option value="платне">платне</option>
                </select><br/><br/>

                    Телефон: <input name="phone" type="text" size="8"/><br/>
                    Адреса: <input name="address" type="text" size="8"/><br/><br/>

                </td>
            </tr>
            </tr>

        </form>
    </table>
</div>
</div>


