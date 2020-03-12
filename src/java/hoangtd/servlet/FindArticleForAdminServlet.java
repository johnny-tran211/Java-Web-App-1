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
@WebServlet(name = "FindArticleForAdminServlet", urlPatterns = {"/FindArticleForAdminServlet"})
public class FindArticleForAdminServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String ADMIN_MANAGEMENT_PAGE = "adminManagement.jsp";
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
        String content = request.getParameter("txtSearchContent").trim();
        String title = request.getParameter("txtTitle").trim();
        String[] checkbox = request.getParameterValues("cboStatus");
        String numberPageStr = request.getParameter("numberPage");
        if (numberPageStr == null) {
            numberPageStr = "1";
        }
        int totalOfPage;
        PagingTemplate pagingTemplete = new PagingTemplate();
        ArticleDAO articleDAO = new ArticleDAO();
        int numberOfPage = 0;
        ArrayList<ArticleDTO> articles = new ArrayList<>();
        boolean errorState = false;
        boolean accessDenied = true;
        String url = ADMIN_MANAGEMENT_PAGE;
        try {
            if (content.isEmpty()) {
                accessDenied = false;
                request.setAttribute("CONTENTEMPTY", "Input content to search");
                errorState = true;
            }

            if (checkbox != null) {
                accessDenied = false;
                for (String cbx : checkbox) {
                    if (!cbx.equals("Active") && !cbx.equals("Delete") && !cbx.equals("New")) {
                        request.setAttribute("CBXERROR", "only 3 options to choose");
                        errorState = true;
                    }
                }
            }
            if (errorState == false) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    UserDTO userDTO = (UserDTO) session.getAttribute("USER");
                    if (userDTO != null) {
                        if (userDTO.getRole().equals("admin")) {
                            accessDenied = false;
                            boolean numberChecked = pagingTemplete.checkNumber(numberPageStr);
                            if (numberChecked) {
                                if (title.isEmpty() && checkbox == null) {
                                    totalOfPage = pagingTemplete.getQuantityOfAdminPageByContent(AMOUNT_OF_ARTICLE, content);
                                    numberOfPage = Integer.parseInt(numberPageStr);
                                    if (numberOfPage > totalOfPage) {
                                        request.setAttribute("ERROR", "Don't have any blog");
                                    } else {
                                        if (numberOfPage == 0) {
                                            articles = articleDAO.searchArticleAdminByContent(content, numberOfPage, AMOUNT_OF_ARTICLE);
                                        } else if (numberOfPage > 0) {
                                            numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                            articles = articleDAO.searchArticleAdminByContent(content, numberOfPage, AMOUNT_OF_ARTICLE);
                                        } else {
                                            request.setAttribute("ERROR", "Don't have any blog");
                                        }
                                        if (!articles.isEmpty()) {
                                            request.setAttribute("NUMBEROFSEARCHPAGE", totalOfPage);
                                            request.setAttribute("SEARCHVALUE", articles);
                                        }
                                    }
                                } else if (!title.isEmpty() && checkbox == null) {
                                    totalOfPage = pagingTemplete.getQuantityOfAdminPageByContentTitle(AMOUNT_OF_ARTICLE, content, title);
                                    numberOfPage = Integer.parseInt(numberPageStr);
                                    if (numberOfPage > totalOfPage) {
                                        request.setAttribute("ERROR", "Don't have any blog");
                                    } else {
                                        if (numberOfPage == 0) {
                                            articles = articleDAO.searchArticleAdminByContentTitle(content, numberOfPage, AMOUNT_OF_ARTICLE, title);
                                        } else if (numberOfPage > 0) {
                                            numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                            articles = articleDAO.searchArticleAdminByContentTitle(content, numberOfPage, AMOUNT_OF_ARTICLE, title);
                                        } else {
                                            request.setAttribute("ERROR", "Don't have any blog");
                                        }
                                        if (!articles.isEmpty()) {
                                            request.setAttribute("NUMBEROFSEARCHPAGE", totalOfPage);
                                            request.setAttribute("SEARCHVALUE", articles);
                                        }
                                    }
                                } else if (title.isEmpty() && checkbox != null) {
                                    switch (checkbox.length) {
                                        case 1:
                                            totalOfPage = pagingTemplete.getQuantityOfAdminPageByContent1CheckBox(AMOUNT_OF_ARTICLE, content, checkbox[0]);
                                            numberOfPage = Integer.parseInt(numberPageStr);
                                            if (numberOfPage > totalOfPage) {
                                                request.setAttribute("ERROR", "Don't have any blog");
                                            } else {
                                                if (numberOfPage == 0) {
                                                    articles = articleDAO.searchArticleAdminByContent1Checkbox(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0]);
                                                } else if (numberOfPage > 0) {
                                                    numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                                    articles = articleDAO.searchArticleAdminByContent1Checkbox(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0]);
                                                } else {
                                                    request.setAttribute("ERROR", "Don't have any blog");
                                                }
                                                if (!articles.isEmpty()) {
                                                    request.setAttribute("NUMBEROFSEARCHPAGE", totalOfPage);
                                                    request.setAttribute("SEARCHVALUE", articles);
                                                }
                                            }
                                            break;

                                        case 2:
                                            totalOfPage = pagingTemplete.getQuantityOfAdminPageByContent2CheckBox(AMOUNT_OF_ARTICLE, content, checkbox[0], checkbox[1]);
                                            numberOfPage = Integer.parseInt(numberPageStr);
                                            if (numberOfPage > totalOfPage) {
                                                request.setAttribute("ERROR", "Don't have any blog");
                                            } else {
                                                if (numberOfPage == 0) {
                                                    articles = articleDAO.searchArticleAdminByContent2Checkbox(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], checkbox[1]);
                                                } else if (numberOfPage > 0) {
                                                    numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                                    articles = articleDAO.searchArticleAdminByContent2Checkbox(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], checkbox[1]);
                                                } else {
                                                    request.setAttribute("ERROR", "Don't have any blog");
                                                }
                                                if (!articles.isEmpty()) {
                                                    request.setAttribute("NUMBEROFSEARCHPAGE", totalOfPage);
                                                    request.setAttribute("SEARCHVALUE", articles);
                                                }
                                            }
                                            break;
                                        case 3:
                                            totalOfPage = pagingTemplete.getQuantityOfAdminPageByContent3CheckBox(AMOUNT_OF_ARTICLE, content, checkbox[0], checkbox[1], checkbox[2]);
                                            numberOfPage = Integer.parseInt(numberPageStr);
                                            if (numberOfPage > totalOfPage) {
                                                request.setAttribute("ERROR", "Don't have any blog");
                                            } else {
                                                if (numberOfPage == 0) {
                                                    articles = articleDAO.searchArticleAdminByContent3Checkbox(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], checkbox[1], checkbox[2]);
                                                } else if (numberOfPage > 0) {
                                                    numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                                    articles = articleDAO.searchArticleAdminByContent3Checkbox(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], checkbox[1], checkbox[2]);
                                                } else {
                                                    request.setAttribute("ERROR", "Don't have any blog");
                                                }
                                                if (!articles.isEmpty()) {
                                                    request.setAttribute("NUMBEROFSEARCHPAGE", totalOfPage);
                                                    request.setAttribute("SEARCHVALUE", articles);
                                                }
                                            }
                                            break;
                                    }

                                } else if (!title.isEmpty() && checkbox != null) {
                                    switch (checkbox.length) {
                                        case 1:
                                            totalOfPage = pagingTemplete.getQuantityOfAdminPageByContent1CheckBoxTitle(AMOUNT_OF_ARTICLE, content, checkbox[0], title);
                                            numberOfPage = Integer.parseInt(numberPageStr);
                                            if (numberOfPage > totalOfPage) {
                                                request.setAttribute("ERROR", "Don't have any blog");
                                            } else {
                                                if (numberOfPage == 0) {
                                                    articles = articleDAO.searchArticleAdminByContent1CheckboxTitle(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], title);
                                                } else if (numberOfPage > 0) {
                                                    numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                                    articles = articleDAO.searchArticleAdminByContent1CheckboxTitle(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], title);
                                                } else {
                                                    request.setAttribute("ERROR", "Don't have any blog");
                                                }
                                                if (!articles.isEmpty()) {
                                                    request.setAttribute("NUMBEROFSEARCHPAGE", totalOfPage);
                                                    request.setAttribute("SEARCHVALUE", articles);
                                                }
                                            }
                                            break;

                                        case 2:
                                            totalOfPage = pagingTemplete.getQuantityOfAdminPageByContent2CheckBoxTitle(AMOUNT_OF_ARTICLE, content, checkbox[0], checkbox[1], title);
                                            numberOfPage = Integer.parseInt(numberPageStr);
                                            if (numberOfPage > totalOfPage) {
                                                request.setAttribute("ERROR", "Don't have any blog");
                                            } else {
                                                if (numberOfPage == 0) {
                                                    articles = articleDAO.searchArticleAdminByContent2CheckboxTitle(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], checkbox[1], title);
                                                } else if (numberOfPage > 0) {
                                                    numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                                    articles = articleDAO.searchArticleAdminByContent2CheckboxTitle(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], checkbox[1], title);
                                                } else {
                                                    request.setAttribute("ERROR", "Don't have any blog");
                                                }
                                                if (!articles.isEmpty()) {
                                                    request.setAttribute("NUMBEROFSEARCHPAGE", totalOfPage);
                                                    request.setAttribute("SEARCHVALUE", articles);
                                                }
                                            }
                                            break;
                                        case 3:
                                            totalOfPage = pagingTemplete.getQuantityOfAdminPageByContent3CheckBoxTitle(AMOUNT_OF_ARTICLE, content, checkbox[0], checkbox[1], checkbox[2], title);
                                            numberOfPage = Integer.parseInt(numberPageStr);
                                            if (numberOfPage > totalOfPage) {
                                                request.setAttribute("ERROR", "Don't have any blog");
                                            } else {
                                                if (numberOfPage == 0) {
                                                    articles = articleDAO.searchArticleAdminByContent3CheckboxTitle(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], checkbox[1], checkbox[2], title);
                                                } else if (numberOfPage > 0) {
                                                    numberOfPage = (numberOfPage - 1) * AMOUNT_OF_ARTICLE;
                                                    articles = articleDAO.searchArticleAdminByContent3CheckboxTitle(content, numberOfPage, AMOUNT_OF_ARTICLE, checkbox[0], checkbox[1], checkbox[2], title);
                                                } else {
                                                    request.setAttribute("ERROR", "Don't have any blog");
                                                }
                                                if (!articles.isEmpty()) {
                                                    request.setAttribute("NUMBEROFSEARCHPAGE", totalOfPage);
                                                    request.setAttribute("SEARCHVALUE", articles);
                                                }
                                            }
                                            break;
                                    }
                                }
                            } else {
                                request.setAttribute("ERROR", "Don't have any blog");
                            }

                        }
                    }
                }
            }
            if (accessDenied) {
                url = LOGIN_PAGE;
                request.setAttribute("ROLE", "You are not allow to access. This place is just for admin.");
            }
        }catch (NamingException e) {
            log("FindArticleForAdminServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            log("FindArticleForAdminServlet SQLException " + e.getMessage());
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
