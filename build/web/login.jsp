<%-- 
    Document   : login
    Created on : Jan 9, 2020, 1:59:58 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Soft Skills Blog</title>
        <link type="text/css" rel="stylesheet" href="css/login.css"/>
    </head>
    <body>


        <div class="cover">
            <div class="sub-cover">
                <div class="form">
                    <div class="title">
                        Soft Skills Blog
                    </div>
                    <c:if test="${not empty requestScope.SUCCESS}">
                        <div class="success">
                        ${requestScope.SUCCESS}
                        </div>
                    </c:if>
                    <c:if test="${not empty param.role}">
                        <div class="error">
                            ${param.role}
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.ROLE}">
                        <div class="error">
                            ${requestScope.ROLE}
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.ERROR}">
                        <div class="error">                                
                            <h5>${requestScope.ERROR}</h5>
                        </div>
                    </c:if>
                    <form action="ServletController" method="POST">
                        <div class="input">
                            <p>Email Address:</p>
                            <input type="text" name="txtUsername" value="" />
                        </div>
                        <div class="input">
                            <p>Password:</p>
                            <input type="password" name="txtPassword" value="" />
                        </div>
                        <div class="button">
                            <input type="submit" value="Login" name="action"/>
                        </div>

                    </form>
                    <div class="register">
                        <span>Don't have an account? <a href="register.jsp">Register</a></span>
                    </div>
                     <div class="register">
                         <c:url var="urlRewriting" value="ServletController">
                             <c:param name="action" value="Get Article" />
                         </c:url>
                        <span>Back to home <a href="${urlRewriting}">Home Page</a></span>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
