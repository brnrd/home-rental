<%-- 
    Document   : register
    Created on : Mar 31, 2013, 6:15:51 PM
    Author     : Bernard <bernard.debecker@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        <title>Test page</title>
        <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <form class="form-signin">
                <h2 class="form-signin-heading">Please register</h2>
                <input type="text" class="input-block-level" placeholder="First name">
                <input type="text" class="input-block-level" placeholder="Last name">
                <input type="text" class="input-block-level" placeholder="Country">
                <input type="text" class="input-block-level" placeholder="Email address">
                <input type="password" class="input-block-level" placeholder="Password">
                <button action ="" class="btn btn-large btn-primary" type="submit">Register</button>
            </form>
        </div> 
    </jsp:body>
</t:layout>