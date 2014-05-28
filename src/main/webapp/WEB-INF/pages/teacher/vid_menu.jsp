<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/interactive_vid_menu.js"></script>

<script>
var gr_sub_v = new Array();

$(document).ready(function(){
gr_sub_v = JSON.parse('<c:out value="${group_list_for_vid}"/>'.replace(/&#034;/g,"\""));
    grLstInit(gr_sub_v);
});


</script>
<div class="header-of-main">Відомості<a href="#" id="vidomostipopup" class="popup">↑</a></div>
<div class="main-part">
<form method="get">
    <label for="group">Група: </label>
<select id="group" name="group_id">
</select>
    <label for="sub">Предмет: </label>
<select id="sub" name="subject_id">
</select>
    <label for="sum">Симестр: </label>
    <input id="sum" class="integer" type="number" name="sum" min="1" max="8">
    <label>Формат: </label>
    <input type="radio" checked id="pdf" value=".pdf" name="format">
    <label for="pdf">Pdf</label>
    <input type="radio" id="xls" value=".xls" name="format">
    <label for="xls">Xls</label>
    <br/>

    <input type="submit" formaction="teacher/vidomist" value="Звичайна відомість">
    <input type="submit" value="Зведена відомість">
    <input type="submit" value="Супер відомість">

    </form>

</div>
