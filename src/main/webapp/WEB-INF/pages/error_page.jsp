<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
<div >



    <div class="header-of-main"></div>
    <div class="main-part">


        <div>

            ПОМИЛКА  <%=response.getStatus()%>

        </div>
        <img class="lock" height="87px" alt="warning" src="${pageContext.request.contextPath}/resources/img/warning.png"/>

 <%if ((Throwable)request.getAttribute("javax.servlet.error.exception")!=null) {%>
        <br/><%=((Throwable)request.getAttribute("javax.servlet.error.exception")).getMessage()%>



    <!--
    Failed URL: <%=request.getRequestURL()%>
    Exception: <%=((Throwable)request.getAttribute("javax.servlet.error.exception")).getMessage()%>

    -->
<% ;}%>
</div>
  </div>
