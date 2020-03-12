/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.servlet;

import hoangtd.article.ArticleDAO;
import hoangtd.article.ArticleDTO;
import hoangtd.comment.CommentDAO;
import hoangtd.comment.CommentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
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
@WebServlet(name = "SearchArticleDetailServlet", urlPatterns = {"/SearchArticleDetailServlet"})
public class SearchArticleDetailServlet extends HttpServlet {

    private final String ARTICLE_DETAIL_PAGE = "articleDetail.jsp";

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
        String title = request.getParameter("txtTitle");
        try {
            ArticleDAO articleDAO = new ArticleDAO();
            ArticleDTO articleDetail = articleDAO.searchArticleDetail(title, "Active");
            if (articleDetail != null) {
                CommentDAO commentDAO = new CommentDAO();
                ArrayList<CommentDTO> comments = commentDAO.searchComment(title);
                if (comments.isEmpty()) {
                    request.setAttribute("ARTICLEDETAIL", articleDetail);
                } else {
                    request.setAttribute("COMMENT", comments);
                    request.setAttribute("ARTICLEDETAIL", articleDetail);
                }

            }else{
                 request.setAttribute("ERROR", "This blog is not actived or not existed");
            }
        } catch (NamingException e) {
            log("SearchArticleDetailServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("SearchArticleDetailServlet SQLException " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(ARTICLE_DETAIL_PAGE);
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
