<%-- 
    Document   : articlePosting
    Created on : Jan 9, 2020, 9:20:42 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Soft Skills Blog</title>
        <link rel="stylesheet" href="css/articlePosting.css">
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
    </head>
    <body class="body-change">
        <c:set var="username" value="${sessionScope.USER}"/>
        <c:if test="${sessionScope.USER.role != 'member'}">
            <jsp:forward page="login.jsp">
                <jsp:param name="role" value="You are not allow to access. This place is just for member. Please login first or sign up account" />
            </jsp:forward>
        </c:if>
        <div class="cover">
           <!-- navbar -->
            <div class="navbar-myself">
                <div class="right-navbar">
                    <div class="logo">
                        <c:url var="urlRewriting" value="ServletController">
                            <c:param name="action" value="Get Article" />
                        </c:url>
                        <a href="${urlRewriting}">SOFT SKILLS BLOG</a>

                    </div>
                </div>
                <div class="left-navbar">
                    <c:if test="${not empty sessionScope.USER}">
                        <div class="welcome">
                            Welcome, ${sessionScope.USER.name}
                        </div>
                        <c:url var="urlRewriting" value="ServletController">
                            <c:param name="action" value="Logout" />
                        </c:url>
                        <div class="logout">
                            <a href="${urlRewriting}">log out</a>
                        </div>
                    </c:if>
                    <c:if test="${empty sessionScope.USER}">
                        <div class="logout">
                            <a href="login.jsp">log in</a>     
                        </div>
                        <div class="logout">
                            <a href="register.jsp">sign up</a>
                        </div>
                    </c:if>
                </div>

            </div>
            <!-- sidebar -->
            <div class="sidebar">
                <c:url var="urlRewriting" value="ServletController">
                    <c:param name="action" value="Get Article" />
                </c:url>
                <a href="${urlRewriting}" class="sub-sidebar">Home</a>
                <c:if test="${sessionScope.USER.role == 'admin'}">
                    <c:url var="urlRewriting3" value="ServletController">
                        <c:param name="action" value="Get Article For Admin" />
                    </c:url>
                    <a href="${urlRewriting3}" class="sub-sidebar">Admin Management</a>
                </c:if>
                <c:url var="urlRewriting2" value="ServletController">
                    <c:param name="action" value="Article Title" />
                </c:url>

                <a href="${urlRewriting2}" class="sub-sidebar">Article Detail</a>
                <a href="articlePosting.jsp" class="sub-sidebar">Post Article</a>

            </div>
            <!--hody-->
            <div class="content">
                <div class="content-detail">
                    <div class="title-article">
                        Post Blog
                    </div>

                    <c:if test="${not empty requestScope.STATE}">
                        <div class="success">
                        ${requestScope.STATE}
                        </div>
                    </c:if>
                    <div class="form">
                        <form action="ServletController" method="POST">
                            <input type="hidden" name="txtEmail" value="${username.email}" />
                            <div class="form-group">
                                <label>Title:</label></br>
                                <c:if test="${not empty requestScope.ERROR}">
                                    <div class="error">
                                        ${requestScope.ERROR.titleEmptyError}
                                    </div>
                                    <div class="error">
                                        ${requestScope.ERROR.titleDuplicateError}
                                    </div>
                                </c:if>                               
                                <input type="text" class="form-control"
                                       placeholder="Title" name="txtTitle" value="${param.txtTitle}" />
                            </div></br>
                            <div class="form-group">
                                <label>Short Description:</label></br>
                                <c:if test="${not empty requestScope.ERROR}">
                                    <div class="error">
                                        ${requestScope.ERROR.shortDescriptionEmptyError}
                                    </div>
                                </c:if>
                                <input type="text" class="form-control"
                                       placeholder="Short Description" name="txtShortDescription" value="${param.txtShortDescription}"/>
                            </div></br>
                            <div class="form-group">
                                <label for="comment">Content:</label></br>
                                <c:if test="${not empty requestScope.ERROR}">
                                    <div class="error">
                                        ${requestScope.ERROR.contentEmptyError}
                                    </div>
                                </c:if>
                                <textarea class="form-control" rows="5" id="comment" placeholder="Content" name="txtContent">${param.txtContent}</textarea>
                            </div></br>
                            <div class="button">
                                <input type="submit" value="Post" name="action" />
                            </div>
                        </form>
                    </div>
                </div>

            </div>
    </body>

</html>
