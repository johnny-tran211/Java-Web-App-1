<%-- 
    Document   : adminDetailManagement
    Created on : Jan 12, 2020, 4:17:04 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Soft Skills Blog</title>
        <link rel="stylesheet" href="css/adminDetailManagement.css">
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
    </head>
    <body class="body-change">
        <c:if test="${sessionScope.USER.role != 'admin'}">
            <jsp:forward page="login.jsp">
                <jsp:param name="role" value="You are not allow to access. This place is just for admin." />
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
            <!-- -----body-------- -->
            <div class="content">
                <c:set var="blog" value="${requestScope.SEARCHVALUE}" />
                <div class="content-detail">
                    <div class="button-back">
                         <c:url var="urlRewriting" value="ServletController">
                            <c:param name="action" value="Get Article For Admin" />
                        </c:url>
                        <a href="${urlRewriting}">Back to check blog</a>
                    </div>
                    <c:if test="${not empty blog}">
                        <div class="title-article">
                            ${blog.title}
                        </div>
                        <div class="date">
                            ${blog.date}
                        </div>
                        <div class="author">
                            Author : ${blog.author}
                        </div>
                        <div class="status">
                            Status : 
                            <c:if test="${blog.status == 'Delete'}">
                                <span class="status-delete">Delete</span>
                            </c:if>
                            <c:if test="${blog.status == 'Active'}">
                                <span class="status-active">Active</span>
                            </c:if>
                            <c:if test="${blog.status == 'New'}">
                                <span class="status-new">New</span>
                            </c:if>
                        </div>
                        <div class="short-description">
                            <div class="title">
                                Short Description
                            </div>
                            <div class="content">
                                ${blog.shortDescription}
                            </div>
                        </div>
                        <div class="content-blog">
                            <div class="title">
                                Content
                            </div>
                            <div class="content">
                                ${blog.content}
                            </div>
                        </div>
                        <div class="form">
                            <form action="ServletController">
                                <div class="button">
                                    <input type="hidden" name="txtDetailTitle" value="${blog.title}" />
                                    <c:if test="${blog.status == 'Delete'}">
                                        <input class="restore" type="submit" name="action" value="Restore Blog" />
                                    </c:if>
                                    <c:if test="${blog.status == 'Active'}">
                                        <input class="delete" type="submit" name="action" value="Delete Blog" />
                                    </c:if>
                                    <c:if test="${blog.status == 'New'}">
                                        <input class="delete" type="submit" name="action" value="Delete Blog" />
                                        <input class="active" type="submit" name="action" value="Active Blog" />
                                    </c:if>
                                </div>
                            </form>
                        </div>
                    </c:if>
                </div>
                <c:if test="${empty blog}">
                    <div class="content-error">
                        <div class="no-record">
                            ${requestScope.ERROR}
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
