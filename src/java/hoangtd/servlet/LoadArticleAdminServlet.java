/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.servlet;

import hoangtd.article.ArticleDAO;
import hoangtd.article.ArticleDTO;
import hoangtd.registration.UserDTO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
@WebServlet(name = "LoadArticleAdminServlet", urlPatterns = {"/LoadArticleAdminServlet"})
public class LoadArticleAdminServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String ADMIN_PAGE = "adminManagement.jsp";
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
        String url = LOGIN_PAGE;
        boolean accessDenied = true;
        String numberPageStr = request.getParameter("numberPage");
        if (numberPageStr == null) {
            numberPageStr = "1";
        }
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO userDTO = (UserDTO) session.getAttribute("USER");
                if (userDTO != null) {
                    if (userDTO.getRole().equals("admin")) {
                        url = ADMIN_PAGE;

                        PagingTemplate pagingTemplete = new PagingTemplate();
                        ArticleDAO articleDAO = new ArticleDAO();
                        int totalOfPage = pagingTemplete.getQuantityOfAdminPage(AMOUNT_OF_ARTICLE);
                        ArrayList<ArticleDTO> articles = new ArrayList<>();
                        boolean numberChecked = pagingTemplete.checkNumber(numberPageStr);
                        if (numberChecked) {
                            accessDenied = false;
                            int numberOfPage = Integer.parseInt(numberPageStr);
                            if (numberOfPage > totalOfPage) {
                                request.setAttribute("ERROR", "Don't have any blog");
                            } else {
                                if (numberOfPage == 0) {
                                    articles = articleDAO.getArtitleForAdmin(numberOfPage, AMOUNT_OF_ARTICLE);
                                } else if(numberOfPage > 0){
                                    numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                    articles = articleDAO.getArtitleForAdmin(numberOfPage, AMOUNT_OF_ARTICLE);
                                }else{
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
                }
            }
            if (accessDenied) {
                request.setAttribute("ROLE", "You are not allow to access. This place is just for admin");
            }
        } catch (NamingException e) {
            log("LoadArticleAdminServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("LoadArticleAdminServlet SQLException " + e.getMessage());
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
