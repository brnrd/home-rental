<%-- 
    Document   : layout
    Created on : Mar 11, 2013, 10:43:11 AM
    Author     : Bernard <bernard.debecker@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Basic template" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" fragment="true" %>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap-responsive.css">
        <jsp:invoke fragment="title"/>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="./index.jsp">Home Rental Project</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <c:choose>
                                <c:when test="${param.isHome == true}" >
                                    <li class="active">
                                    </c:when>
                                    <c:otherwise>
                                    <li class="">
                                    </c:otherwise>
                                </c:choose>

                                <a href="./index.jsp">Home</a>
                            </li>
                            <c:choose>
                                <c:when test="${param.isTest == true}" >
                                    <li class="active">
                                    </c:when>
                                    <c:otherwise>
                                    <li class="">
                                    </c:otherwise>
                                </c:choose>
                                <a href="./test.jsp">Test</a>
                            </li>
                            <c:choose>
                                <c:when test="${param.isRegister == true}" >
                                    <li class="active">
                                    </c:when>
                                    <c:otherwise>
                                    <li class="">
                                    </c:otherwise>
                                </c:choose>
                            <a href="./register.jsp">Register</a>
                    </ul>
                    <!--Add if logged then just display the username with maybe a user menu-->
                    <form class="navbar-form pull-right">
                        <input class="span2" type="text" placeholder="Email">
                        <input class="span2" type="password" placeholder="Password">
                        <button type="submit" class="btn">Log in</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <header class="jumbotron subhead">
            <div class="span6"></div>
        </header>
        <div class="row">
            <div class="span6">
                <br>
                <br>
                <br>
                <jsp:doBody/>
            </div>
        </div>
    </div>
</body>
</html>