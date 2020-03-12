/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ServletController", urlPatterns = {"/ServletController"})
public class ServletController extends HttpServlet {

    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SEARCH_ARTICLE_SERVLET = "SearchArticleServlet";
    private final String SEARCH_TITLE_SERVLET = "SearchTitleServlet";
    private final String SEARCH_ARTICLE_DETAIL_SERVLET = "SearchArticleDetailServlet";
    private final String REGISTER_SERVLET = "RegisterServlet";
    private final String POST_ARTICLE_SERVLET = "PostArticleServlet";
    private final String LOAD_ARTICLE_ADMIN_SERVLET = "LoadArticleAdminServlet";
    private final String LOAD_ARTICLE_SERVLET = "LoadArticleServlet";
    private final String POST_COMMENT_SERVLET = "PostCommentServlet";
    private final String FIND_ARTICLE_FOR_ADMIN_SERVLET = "FindArticleForAdminServlet";
    private final String ARTICLE_FOR_ADMIN_SERVLET = "SearchArticleDetailAdminServlet";
    private final String ACTIVE_BLOG_SERVLET = "ActiveBlogServlet";
    private final String DELETE_BLOG_SERVLET = "DeleteBlogServlet";
    private final String RESTORE_BLOG_SERVLET = "RestoreServlet";
    private final String DELETE_ALL_SERVLET = "DeleteAllServlet";
    private final String NOT_FOUND = "notFound.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        String url = NOT_FOUND;
        try {
            if (action.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (action.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (action.equals("Search")) {
                url = SEARCH_ARTICLE_SERVLET;
            } else if (action.equals("Article Title")) {
                url = SEARCH_TITLE_SERVLET;
            } else if (action.equals("Article Detail")) {
                url = SEARCH_ARTICLE_DETAIL_SERVLET;
            } else if (action.equals("Register")) {
                url = REGISTER_SERVLET;
            } else if (action.equals("Post")) {
                url = POST_ARTICLE_SERVLET;
            } else if (action.equals("Post Comment")) {
                url = POST_COMMENT_SERVLET;
            } else if (action.equals("Get Article For Admin")) {
                url = LOAD_ARTICLE_ADMIN_SERVLET;
            } else if (action.equals("Find")) {
                url = FIND_ARTICLE_FOR_ADMIN_SERVLET;
            } else if (action.equals("Search Article Detail for Admin")) {
                url = ARTICLE_FOR_ADMIN_SERVLET;
            } else if (action.equals("Active Blog")) {
                url = ACTIVE_BLOG_SERVLET;
            } else if (action.equals("Delete Blog")) {
                url = DELETE_BLOG_SERVLET;
            } else if (action.equals("Restore Blog")) {
                url = RESTORE_BLOG_SERVLET;
            } else if (action.equals("Get Article")) {
                url = LOAD_ARTICLE_SERVLET;
            } else if (action.equals("Delete all")) {
                url = DELETE_ALL_SERVLET;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
