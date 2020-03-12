<%-- 
    Document   : adminManagement
    Created on : Jan 10, 2020, 1:12:45 PM
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
                <div class="content-detail">
                    <div class="title-article">
                        Admin Manager
                    </div>
                    <c:if test="${not empty requestScope.SUCCESS}">
                        <div class="success">
                            ${requestScope.SUCCESS}
                        </div>   
                    </c:if>
                    <c:if test="${not empty requestScope.FAIL}">
                        <div class="no-record">
                            ${requestScope.FAIL}

                        </div>   
                    </c:if>
                    <c:if test="${not empty requestScope.ERROR}">
                        <div class="no-record">
                            ${requestScope.ERROR}

                        </div>   
                    </c:if>
                    <c:if test="${not empty requestScope.CHECKBOXEMPTY}">
                        <div class="no-record">
                            ${requestScope.CHECKBOXEMPTY}

                        </div>   
                    </c:if>

                    <div class="form">
                        <form action="ServletController">
                            <div class="form-group form-group-detail">
                                <c:if test="${not empty requestScope.CONTENTEMPTY}">
                                    <div class="no-record"> 
                                        ${requestScope.CONTENTEMPTY}
                                    </div>
                                </c:if>
                                <input type="text" class="form-control" placeholder="Search by content {*}" name="txtSearchContent" value="${param.txtSearchContent}" />
                                <input type="text" class="form-control" placeholder="Search by title" name="txtTitle" value="${param.txtTitle}" />
                                <div class="boxes">
                                    <p>Status: </p>
                                    <c:if test="${not empty requestScope.CBXERROR}">
                                        <div class="no-record"> 
                                            ${requestScope.CBXERROR}
                                        </div>
                                    </c:if>
                                    <input class="checkbox" type="checkbox" name="cboStatus" value="Active" 
                                           <c:forEach var="status" items="${paramValues.cboStatus}">
                                               <c:if test="${status == 'Active'}">
                                                   checked="checked"
                                               </c:if>
                                           </c:forEach>
                                           />

                                    <label>Active</label>
                                    <input class="checkbox" type="checkbox" name="cboStatus" value="Delete" 
                                           <c:forEach var="status" items="${paramValues.cboStatus}">
                                               <c:if test="${status == 'Delete'}">
                                                   checked="checked"
                                               </c:if>
                                           </c:forEach>
                                           />
                                    <label>Delete</label>
                                    <input class="checkbox" type="checkbox" name="cboStatus" value="New" 
                                           <c:forEach var="status" items="${paramValues.cboStatus}">
                                               <c:if test="${status == 'New'}">
                                                   checked="checked"
                                               </c:if>
                                           </c:forEach>
                                           />
                                    <label>New</label>


                                </div>
                                <div class="button">
                                    <input type="submit" value="Find" name="action" />
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="button-reload">
                        <c:url var="urlRewriting" value="ServletController">
                            <c:param name="action" value="Get Article For Admin" />
                        </c:url>
                        <a href="${urlRewriting}">RELOAD</a>
                    </div>
                    <c:if test="${not empty requestScope.SEARCHVALUE}">
                        <form action="ServletController" method="POST">
                            <div class="table-detail">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Title</th>
                                            <th scope="col">Short Description</th>
                                            <th scope="col">Author</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Status</th>
                                            <th scope="col">
                                                <div class="button-delete">
                                                    <input type="submit" name="action" value="Delete all" />
                                                </div>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="article" items="${requestScope.SEARCHVALUE}" varStatus="counter">
                                            <tr>
                                                <th scope="row">${counter.count}</th>
                                                    <c:url var="urlRewriting3" value="ServletController">
                                                        <c:param name="action" value="Search Article Detail for Admin" />
                                                        <c:param name="txtDetailTitle" value="${article.title}" />
                                                    </c:url>
                                                <td><a href="${urlRewriting3}" class="article-detail">${article.title}</a></td>
                                                <td>${article.shortDescription}</td>
                                                <td>${article.author}</td>
                                                <td>${article.date}</td>
                                                <c:if test="${article.status == 'Delete'}">
                                                    <td class="red-status">${article.status}</td> 
                                                </c:if>
                                                <c:if test="${article.status == 'Active'}">
                                                    <td class="green-status">${article.status}</td> 
                                                </c:if>
                                                <c:if test="${article.status == 'New'}">
                                                    <td class="blue-status">${article.status}</td> 
                                                </c:if>
                                                <c:if test="${article.status == 'Active' || article.status == 'New'}">
                                                    <th scope="row">
                                                        <input type="checkbox" name="cbxDelete" value="${article.title}" />
                                                    </th>
                                                </c:if>
                                                <c:if test="${article.status == 'Delete'}">
                                                    <th scope="row">
                                                        <input type="checkbox" disabled="true" />
                                                    </th>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                        <nav aria-label="..." class="paging">
                            <ul class="pagination pagination-lg">
                                <c:if test="${not empty requestScope.NUMBEROFPAGE}">
                                    <c:forEach var="numberPage" begin="1" end="${requestScope.NUMBEROFPAGE}">
                                        <c:url var="urlRewriting" value="ServletController">
                                            <c:param name="action" value="Get Article For Admin"/>
                                            <c:param name="numberPage" value="${numberPage}" />
                                        </c:url>
                                        <li class="page-item"><a class="page-link number" href="${urlRewriting}">${numberPage}</a></li>
                                        </c:forEach> 
                                    </c:if>
                                    <c:if test="${not empty requestScope.NUMBEROFSEARCHPAGE}">
                                        <c:forEach var="numberPage2" begin="1" end="${requestScope.NUMBEROFSEARCHPAGE}">
                                            <c:url var="urlRewriting2" value="ServletController">
                                                <c:param name="action" value="Find" />
                                                <c:param name="txtSearchContent" value="${param.txtSearchContent}" />
                                                <c:param name="txtTitle" value="${param.txtTitle}" />
                                                <c:if test="${not empty paramValues.cboStatus}">
                                                    <c:forEach var="status" items="${paramValues.cboStatus}">
                                                        <c:param name="cboStatus" value="${status}" />
                                                    </c:forEach>
                                                </c:if>

                                            <c:param name="numberPage" value="${numberPage2}" />
                                        </c:url>
                                        <li class="page-item"><a class="page-link number" href="${urlRewriting2}">${numberPage2}</a></li>
                                        </c:forEach> 
                                    </c:if>

                            </ul>
                        </nav>
                    </c:if>
                </div>

            </div>
        </div>
    </body>
</html>
