<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script>
    var gr_sub = [
        {"gr_id":1, "gr_name":"GH-01-1", "subj_list":[
                {"subj_id":1, "subj_name":"OOP"}//,
                //{},
                //{}
            ]
        }/*,
        {},{}*/];


    document.onload(function() {
        var grlist = document.getElementById("gr_list");

        for(group in gr_sub){
            grlist.appendChild("<li onClick='onGroupClick("+group.gr_id+")'>"+group.gr_name+"</li>");
        }
    });

    function onGroupClick(gr_id)
    {
        var subjlist = document.getElementById("subj_list");
        for(group in gr_sub){
            if(group.gr_id == gr_id)
                for(subj in group.subj_list)
                    subjlist.appendChild("<li >"+subj.gr_name+"</li>");
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
            <ol ig="subj_list">
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

    </form>

</div>
