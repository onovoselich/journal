<%@ page import="java.util.Map" %>
<%@ page import="ua.softserve.entities.Group" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.softserve.entities.Subject" %>
<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>
<script>
    function jstlObgectToJs(obj){
        console.log(obj);

    }
    var gr_sub = [
        {"gr_id":1, "gr_name":"GH-01-1", "subj_list":[
            {"subj_id":1, "subj_name":"OOP"}
            ,{"subj_id":2, "subj_name":"Math"}//,
                //{},
                //{}
            ]
        }/*,
        {},{}*/];

$(document).ready(function(){
    var json = "<c:out value="${group_json}"/>";

    jstlObgectToJs(JSON.parse( json));

    for(var i in gr_sub){
        var li =  $('<li></li>').html(
                "<input id=gr_id"+gr_sub[i].gr_id+" type='radio' value="+gr_sub[i].gr_id+" name='group_id'/><label for=gr_id"+gr_sub[i].gr_id+">"+gr_sub[i].gr_name+"</label>"
                )
        $('#gr_list').append(li);
    }
    $('#gr_list li').click(onGroupClick);

});
    function onGroupClick(){
        $('#subj_list').empty();
        for(var i in gr_sub)
            if(gr_sub[i].gr_id==parseInt($(this).find('input').attr('id').substr(5)))
                for(var j in gr_sub[i].subj_list){
                    var li =  $('<li></li>').html(
                            "<input  id=subject_id"+gr_sub[i].subj_list[j].subj_id+" type='radio' value="+gr_sub[i].subj_list[j].subj_id+" name='subject_id'/><label for=subject_id"+gr_sub[i].subj_list[j].subj_id+">"+gr_sub[i].subj_list[j].subj_name+"</label>"
                    );
                    $('#subj_list').append(li);
                }


    }



    </script>

    <div class="header-of-main">${teacher}, це ваш кабінет</div>
    <div class="main-part vidomist">

    <form method="get" action="/teacher/vidomist">

        <div>
        <span>

            Групи:

        </span>
            <ol id="gr_list">
<%--                <%int i = 0;%>
<c:forEach var="group" items="${group_list}">
                <li>
                    <input id="group_id<%=++i%>" type="radio" value="${group.getId()}" name="group_id"/>
                    <label for="group_id<%=i%>">

                            ${group}

                    </label>
                </li>
            </c:forEach>--%>
            </ol>
        </div>
        <div>
        <span>

            Предмети:

        </span>
            <ol id="subj_list">
<%--
<c:forEach var="subj" items="${subject_list}">
   <li>
                    <input  id="subject_id<%=++i%>" type="radio" value="${subj.getId()}" name="subject_id"/>
                    <label for="subject_id<%=i%>">

                            ${subj}

                    </label>
                </li>
                </c:forEach>--%>
            </ol>
        </div>
        <input type="submit" value="Відомість"/>
        ${group_map}
    </form>

</div>
