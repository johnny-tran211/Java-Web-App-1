/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.servlet;

import hoangtd.article.ArticleDAO;
import hoangtd.article.ArticleDTO;
import hoangtd.registration.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "SearchArticleDetailAdminServlet", urlPatterns = {"/SearchArticleDetailAdminServlet"})
public class SearchArticleDetailAdminServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String ARTICLE_DETAIL_ADMIN_PAGE = "adminDetailManagement.jsp";

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
        String title = request.getParameter("txtDetailTitle");
        String url = LOGIN_PAGE;
        boolean accessDenied = true;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO userDTO = (UserDTO) session.getAttribute("USER");
                if (userDTO != null) {
                    if (userDTO.getRole().equals("admin")) {
                        accessDenied = false;
                        url = ARTICLE_DETAIL_ADMIN_PAGE;
                        ArticleDAO articleDAO = new ArticleDAO();
                        ArticleDTO articleDetail = articleDAO.searchArticleDetailAdmin(title);
                        if (articleDetail != null) {
                            request.setAttribute("SEARCHVALUE", articleDetail);
                        }else{
                            request.setAttribute("ERROR", "Don't have this blog");
                        }
                    }
                }
            }
             if (accessDenied) {
                request.setAttribute("ROLE", "You are not allow to access. This place is just for admin");
            }
        } catch (NamingException e) {
            log("SearchArticleDetailAdminServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("SearchArticleDetailAdminServlet SQLException " + e.getMessage());
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
