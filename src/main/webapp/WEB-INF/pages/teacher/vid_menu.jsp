<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/interactive_vid_menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tabs.js"></script>

<script>
    var sum = 1;
    var gr_sub_v = new Array();

    $(document).ready(function () {
        gr_sub_v = JSON.parse('<c:out value="${group_list_for_vid}"/>'.replace(/&#034;/g, "\""));
        grLstInit(gr_sub_v);
    });


</script>
<div class="header-of-main">Відомості<a href="#" id="vidomostipopup" class="popup">↑</a></div>
<div class="main-part">
    <div class="tabs">
        <ul>
            <li><a id="tab_1" href="#">Звичайна</a></li>
            <li><a id="tab_2" href="#">Зведена</a></li>
            <li><a id="tab_3" href="#">Загальна</a></li>
        </ul>
    </div>
    <form id="vidmenu" method="get">
        <div>
        <label for="group">Група: </label>
        <select id="group" name="group_id">
        </select>
        </div>

        <div class="tabcontent content_1">
        <label for="sub">Предмет: </label>
        <select id="sub" name="subject_id">
        </select>
        </div>

        <div class="tabcontent content_1 content_2">
            <label for="sum">Симестр: </label>
            <input id="sum" class="integer" type="number" name="sum" min="1" max="8"/>
        </div>
        <div class="tabcontent content_1 content_2">
        <label>Формат: </label>
        <input type="radio" checked id="pdf" value=".pdf" name="format">
        <label for="pdf">Pdf</label>
        <input type="radio" id="xls" value=".xls" name="format">
        <label for="xls">Xls</label>
        </div>
        <br/>


        <input class="tabcontent content_1" type="submit" formaction="teacher/vidomist" value="OK">
        <input class="tabcontent content_2" type="submit" formaction="teacher/zvvidomist" value="OK">
        <input class="tabcontent content_3" type="submit" value="OK">

    </form>

</div>
