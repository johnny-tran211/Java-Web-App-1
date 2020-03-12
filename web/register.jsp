<%-- 
    Document   : register
    Created on : Jan 9, 2020, 3:20:53 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Soft Skills Blog</title>
        <link type="text/css" rel="stylesheet" href="css/register.css" />
    </head>
    <body>
        <div class="cover">
            <div class="sub-cover">
                <div class="form">
                    <div class="title">
                        Register
                    </div>
                    <c:if test="${not empty requestScope.Fail}">
                        <div class="error">
                            ${requestScope.Fail}
                        </div>
                    </c:if>
                    <c:set var="error" value="${requestScope.ERROR}" />
                    <form action="ServletController" method="POST">
                        <div class="input">
                            <p>Email Address:</p>
                            <input type="text" name="txtUsername" value="${param.txtUsername}" />
                        </div>
                        <c:if test="${not empty error.emailFormError}">
                            <div class="error">
                                ${error.emailFormError}
                            </div>
                        </c:if>
                        <c:if test="${not empty error.duplicateEmailError}">
                            <div class="error">
                                ${error.duplicateEmailError}
                            </div>
                        </c:if>
                        <div class="input">
                            <p>Full Name:</p>
                            <input type="text" name="txtFullname" value="${param.txtFullname}" />
                        </div>
                        <c:if test="${not empty error.fullnameLengthError}">
                            <div class="error">
                                ${error.fullnameLengthError}
                            </div>
                        </c:if>

                        <div class="input">
                            <p>Password:</p>
                            <input type="password" name="txtPassword" value="" />
                        </div>
                        <c:if test="${not empty error.passwordLengthError}">
                            <div class="error">
                                ${error.passwordLengthError}
                            </div>
                        </c:if>
                        <div class="input">
                            <p>Confirm Password:</p>
                            <input type="password" name="txtConfirmPassword" value="" />
                        </div>
                        <c:if test="${not empty error.confirmPasswordError}">
                            <div class="error">
                                ${error.confirmPasswordError}
                            </div>
                        </c:if>
                        <div class="button">
                            <input type="submit" value="Register" name="action"/>
                        </div>
                    </form>
                    <div class="register">
                        <span>Back to login <a href="login.jsp">Log in</a></span>
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

    </form>
</body>
</html>
