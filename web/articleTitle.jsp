<%-- 
    Document   : articleDetail
    Created on : Jan 8, 2020, 1:11:50 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Soft Skills Blog</title>
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
    </head>
    <body class="body-change">
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
                <div class="content-detail">
                    <div class="title-article">
                        Title Of Article
                    </div>
                    <c:if test="${not empty requestScope.SEARCHTITLE}">
                        <div class="table-detail">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Title</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="title" items="${requestScope.SEARCHTITLE}" varStatus="counter">
                                        <tr>
                                            <c:url var="urlRewriting" value="ServletController">
                                                <c:param name="action" value="Article Detail" />
                                                <c:param name="txtTitle" value="${title}" />
                                            </c:url>
                                            <th scope="row">${counter.count}</th>
                                            <td><a class="article-detail" href="${urlRewriting}">${title}</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <nav aria-label="..." class="paging">
                            <ul class="pagination pagination-lg">
                                <c:forEach var="page" begin="1" end="${requestScope.NUMBEROFPAGE}"> 
                                    <c:url var="urlRewriting" value="ServletController"> 
                                        <c:param name="action" value="Article Title" />
                                        <c:param name="numberPage" value="${page}" />
                                    </c:url>
                                    <li class="page-item"><a class="page-link number" href="${urlRewriting}">${page}</a></li>
                                    </c:forEach>

                            </ul>
                        </nav>
                    </c:if>
                    <c:if test="${not empty requestScope.ERROR}">
                        <div class="no-record">
                            ${requestScope.ERROR}
                        </div>
                    </c:if>
                </div>

            </div>
        </div>
    </body>


</html>
