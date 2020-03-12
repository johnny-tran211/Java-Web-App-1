<%-- 
    Document   : index
    Created on : Jan 7, 2020, 10:55:23 PM
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
        <c:if test="${empty requestScope.SEARCHVALUE}">
            <c:if test="${param.action == null}">
                <c:url var="urlRewriting" value="ServletController">
                    <c:param name="action" value="Get Article" />
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
            <div class="content">
                <div class="content-detail">
                    <div class="title-article">
                        Search Article
                    </div>
                    <div class="form">
                        <form action="ServletController">
                            <div class="form-group">
                                <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                                       placeholder="Search" name="txtSearch" value="${param.txtSearch.trim()}" />
                                <button type="submit" value="Search" name="action"><i class="fas fa-search"></i></button>
                            </div>
                        </form>
                    </div>
                    <div class="button-reload">
                        <c:url var="urlRewriting" value="ServletController">
                            <c:param name="action" value="Get Article" />
                        </c:url>
                        <a href="${urlRewriting}">Reload</a>
                    </div>
                    <c:if test="${not empty requestScope.SEARCHVALUE}">
                        <div class="table-detail">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Title</th>
                                        <th scope="col">Short Description</th>
                                        <th scope="col">Author</th>
                                        <th scope="col">Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="article" items="${requestScope.SEARCHVALUE}" varStatus="counter">
                                        <tr>
                                            <th scope="row">${counter.count}</th>
                                            <td>${article.title}</td>
                                            <td>${article.shortDescription}</td>
                                            <td>${article.author}</td>
                                            <td>${article.date}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <nav aria-label="..." class="paging">
                            <ul class="pagination pagination-lg">
                                <c:if test="${not empty requestScope.NUMBEROFPAGE}">
                                    <c:forEach var="page" begin="1" end="${requestScope.NUMBEROFPAGE}"> 
                                        <c:url var="urlRewriting" value="ServletController"> 
                                            <c:param name="action" value="Search" />
                                            <c:param name="txtSearch" value="${param.txtSearch}" />
                                            <c:param name="numberPage" value="${page}" />
                                        </c:url>
                                        <li class="page-item"><a class="page-link number" href="${urlRewriting}">${page}</a></li>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${not empty requestScope.NUMBEROFINDEXPAGE}">
                                        <c:forEach var="page" begin="1" end="${requestScope.NUMBEROFINDEXPAGE}"> 
                                            <c:url var="urlRewriting" value="ServletController"> 
                                                <c:param name="action" value="Get Article" />
                                                <c:param name="txtSearch" value="${param.txtSearch}" />
                                                <c:param name="numberPage" value="${page}" />
                                            </c:url>
                                        <li class="page-item"><a class="page-link number" href="${urlRewriting}">${page}</a></li>
                                        </c:forEach>
                                    </c:if>
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
