/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.servlet;

import hoangtd.article.ArticleDAO;
import hoangtd.article.ArticleErrorObject;
import hoangtd.registration.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
@WebServlet(name = "PostArticleServlet", urlPatterns = {"/PostArticleServlet"})
public class PostArticleServlet extends HttpServlet {

    private final String POST_ARTICLE_PAGE = "articlePosting.jsp";
    private final String STATUS = "New";
    private final String LOGIN_PAGE = "login.jsp";

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
        String email = request.getParameter("txtEmail");
        String title = request.getParameter("txtTitle").trim();
        String shortDescription = request.getParameter("txtShortDescription").trim();
        String content = request.getParameter("txtContent").trim();
        String url = LOGIN_PAGE;
        boolean errorState = false;
        boolean accessDenied = true;
        ArticleErrorObject errors = new ArticleErrorObject();
        try {
            if (title.isEmpty()) {
                errors.setTitleEmptyError("Input value of title");
                errorState = true;
                accessDenied = false;
                url = POST_ARTICLE_PAGE;
            }
            if (shortDescription.isEmpty()) {
                errors.setShortDescriptionEmptyError("Input value of short escription");
                errorState = true;
                accessDenied = false;
                url = POST_ARTICLE_PAGE;
            }
            if (content.isEmpty()) {
                errors.setContentEmptyError("Input value of content");
                accessDenied = false;
                errorState = true;
                url = POST_ARTICLE_PAGE;
            }
            if (errorState == false) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    UserDTO userDTO = (UserDTO) session.getAttribute("USER");
                    if (userDTO != null) {
                        if (userDTO.getRole().equals("member")) {
                            url = POST_ARTICLE_PAGE;
                            accessDenied = false;
                            ArticleDAO articleDAO = new ArticleDAO();
                            long millis = System.currentTimeMillis();
                            java.sql.Timestamp date = new Timestamp(millis);
                            boolean result = articleDAO.addNewBlog(title, shortDescription, email, date, content, STATUS);
                            if (result) {
                                request.setAttribute("STATE", "POST BLOG SUCCESSFULLY!!!!");
                            } else {
                                request.setAttribute("STATE", "POST BLOG FAIL!!!!");
                            }
                        }
                    }
                }

            } else {
                request.setAttribute("ERROR", errors);
            }
            if (accessDenied) {
                request.setAttribute("ROLE", "You are not allow to access. This place is just for member.");
            }
        } catch (NamingException e) {
            log("PostArticleServlet NamingException " + e.getMessage());

        } catch (SQLException e) {
            log("PostArticleServlet SQLException " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                errors.setTitleDuplicateError(title + " is existed !!!");
                request.setAttribute("ERROR", errors);
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
