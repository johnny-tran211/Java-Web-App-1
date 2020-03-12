<%-- 
    Document   : notFound
    Created on : Jan 19, 2020, 12:19:55 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/notFound.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div class="error">
            <div class="middle-title">
                <p>
                    ACTION NOT FOUND
                </p>
            </div>
            <div class="bottom-title">
                <p>
                    Sorry, the action you're looking for is not found.
                </p>
            </div>
            <div class="button">
                <div class="button-left">
                    <c:url var="urlRewriting" value="ServletController">
                        <c:param name="action" value="Get Article" />
                    </c:url>
                    <a href="${urlRewriting}"> Home </a>
                </div>
            </div>
        </div>
    </body>
</html>
