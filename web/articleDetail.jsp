<%-- 
    Document   : articleDetail
    Created on : Jan 8, 2020, 6:02:25 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Soft Skills Blog</title>
        <link rel="stylesheet" href="css/articleDetail.css">
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
    </head>
    <body class="body-change">
        <c:if test="${empty requestScope.ARTICLEDETAIL}">
            <c:if test="${param.action == null}">
                <c:url var="urlRewriting" value="ServletController">
                    <c:param name="action" value="Article Title" />
                </c:url>
                <c:redirect url="${urlRewriting}" />
            </c:if>
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
            <c:set var="article" value="${requestScope.ARTICLEDETAIL}"/>
            <c:if test="${not empty requestScope.ARTICLEDETAIL}">
                <div class="content">
                    <div class="content-detail">
                        <div class="button-back">
                            <c:url var="urlRewriting" value="ServletController">
                                <c:param name="action" value="Article Title" />
                            </c:url>
                            <a href="${urlRewriting}">Back to title</a>
                        </div>
                        <div class="title-article">
                            ${article.title}
                        </div>
                        <div class="date">
                            ${article.date}
                        </div>
                        <div class="author">
                            Author : ${article.author}
                        </div>
                        <div class="short-description">
                            <div class="title">
                                Short Description
                            </div>
                            <div class="content">
                                ${article.shortDescription}
                            </div>
                        </div>
                        <div class="content-blog">
                            <div class="title">
                                Content
                            </div>
                            <div class="content">
                                ${article.content}
                            </div>
                        </div>
                    </div>
                    <div class="comment">
                        <div class="comment-detail">
                            <div class="title-comment">
                                Comment
                            </div>
                            <c:forEach var="comment" items="${requestScope.COMMENT}">
                                <div class="content-comment">
                                    <div class="username">
                                        ${comment.username}
                                    </div>
                                    <div class="date">
                                        ${comment.date}
                                    </div>
                                    <div class="content-detail-comment">
                                        ${comment.comment}
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${not empty requestScope.ERRORCOMMENT}">
                                <div class="no-record">
                                    ${requestScope.ERRORCOMMENT}
                                </div>
                            </c:if>
                            <div class="form">
                                <c:if test="${sessionScope.USER.role == 'member'}">
                                    <form action="ServletController" method="POST">
                                        <div class="form-group">
                                            <textarea name="txtComment" class="form-control" rows="5" id="comment" placeholder="Comment"></textarea>
                                            <input type="hidden" name="txtTitle" value="${article.title}" />
                                        </div></br>
                                        <div class="button">
                                            <input type="submit" value="Post Comment" name="action" />
                                        </div>
                                    </form>
                                </c:if>
                                <c:if test="${sessionScope.USER.role != 'member'}">
                                    <div class="button">
                                        <a href="login.jsp">Login to comment</a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty article}">
                <div class="content">
                    <div class="content-error">
                        <div class="no-record">
                            ${requestScope.ERROR}
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
</html>
