/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.servlet;

import hoangtd.templates.PagingTemplate;
import hoangtd.article.ArticleDAO;
import hoangtd.article.ArticleDTO;
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
@WebServlet(name = "SearchArticleServlet", urlPatterns = {"/SearchArticleServlet"})
public class SearchArticleServlet extends HttpServlet {

    private final int AMOUNT_OF_ARTICLE = 20;
    private final String SEARCH_PAGE = "index.jsp";

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
        String searchValue = request.getParameter("txtSearch").trim();
        String numberPageStr = request.getParameter("numberPage");
        if (numberPageStr == null) {
            numberPageStr = "1";
        }
        try {
            if (!searchValue.isEmpty()) {
                ArticleDAO articleDAO = new ArticleDAO();
                PagingTemplate pagingTemplete = new PagingTemplate();
                int row = articleDAO.getQuantityOfArtitleResult("Active", searchValue);
                int totalOfPage = pagingTemplete.getQuantityOfPage(AMOUNT_OF_ARTICLE, row);
                ArrayList<ArticleDTO> articles = new ArrayList<>();
                boolean numberChecked = pagingTemplete.checkNumber(numberPageStr);
                if (numberChecked) {
                    int numberOfPage = Integer.parseInt(numberPageStr);
                    if (numberOfPage > totalOfPage) {
                        request.setAttribute("ERROR", "Don't have any blog");
                    } else {
                        if (numberOfPage == 0) {
                            articles = articleDAO.searchArticle(searchValue, numberOfPage, AMOUNT_OF_ARTICLE, "Active");
                        } else if (numberOfPage > 0) {
                            numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                            articles = articleDAO.searchArticle(searchValue, numberOfPage, AMOUNT_OF_ARTICLE, "Active");
                        } else {
                            request.setAttribute("ERROR", "Don't have any blog");
                        }
                        if (!articles.isEmpty()) {
                            request.setAttribute("NUMBEROFPAGE", totalOfPage);
                            request.setAttribute("SEARCHVALUE", articles);
                        }
                    }
                } else {
                    request.setAttribute("ERROR", "Don't have any blog");
                }

            }
        }  catch (NamingException e) {
            log("SearchArticleServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("SearchArticleServlet SQLException " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(SEARCH_PAGE);
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
