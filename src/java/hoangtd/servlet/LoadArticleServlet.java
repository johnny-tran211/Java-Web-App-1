/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.servlet;

import hoangtd.article.ArticleDAO;
import hoangtd.article.ArticleDTO;
import hoangtd.templates.PagingTemplate;
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
@WebServlet(name = "LoadArticleServlet", urlPatterns = {"/LoadArticleServlet"})
public class LoadArticleServlet extends HttpServlet {

    private final String INDEX_PAGE = "index.jsp";
    private final String STATUS = "Active";
    private final int AMOUNT_OF_ARTICLE = 20;

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
        String numberPageStr = request.getParameter("numberPage");
        if (numberPageStr == null) {
            numberPageStr = "1";
        }
        try {
            PagingTemplate pagingTemplate = new PagingTemplate();
            int totalOfPage = pagingTemplate.getQuantityTitleOfPage(AMOUNT_OF_ARTICLE);
            ArrayList<ArticleDTO> listDTO = new ArrayList<>();
            boolean number = pagingTemplate.checkNumber(numberPageStr);
            if (number) {
                ArticleDAO articleDAO = new ArticleDAO();
                ArrayList<ArticleDTO> articles = new ArrayList<>();
                int numberOfPage = Integer.parseInt(numberPageStr);
                if (numberOfPage > totalOfPage) {
                    request.setAttribute("ERROR", "Don't have any blog");
                } else {
                    if (numberOfPage == 0) {
                        articles = articleDAO.getArtitle(numberOfPage, AMOUNT_OF_ARTICLE, STATUS);
                    } else if (numberOfPage > 0) {
                        numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                        articles = articleDAO.getArtitle(numberOfPage, AMOUNT_OF_ARTICLE, STATUS);
                    } else {
                        request.setAttribute("ERROR", "Don't have any blog");
                    }
                    if (!articles.isEmpty()) {
                        request.setAttribute("NUMBEROFINDEXPAGE", totalOfPage);
                        request.setAttribute("SEARCHVALUE", articles);
                    }
                }
            } else {
                request.setAttribute("ERROR", "Don't have any blog");
            }

        } catch (NamingException e) {
            log("LoadArticleServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("LoadArticleServlet SQLException " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(INDEX_PAGE);
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
