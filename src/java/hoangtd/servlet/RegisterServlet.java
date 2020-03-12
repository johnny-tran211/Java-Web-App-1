/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.servlet;

import hoangtd.registration.RegisterErrorObject;
import hoangtd.registration.RegistrationDAO;
import hoangtd.templates.EncodingTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String REGISTER_PAGE = "register.jsp";
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
        String username = request.getParameter("txtUsername");
        String fullname = request.getParameter("txtFullname");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        boolean errorState = false;
        String url = REGISTER_PAGE;
        RegisterErrorObject errors = new RegisterErrorObject();
        try {

            if (!username.matches("[a-zA-Z0-9_]{2,30}[@][a-zA-Z0-9]{2,30}([.][a-zA-Z0-9]{2,5}){1,2}")) {
                errors.setEmailFormError("Ex: hoang_td@fpt.edu.vn or hoang@gmail.com (2-30chars@2-30chars.2-5chars(.2-5chars))");
                errorState = true;
            }
            if (fullname.length() > 50 || fullname.length() < 2) {
                errors.setFullnameLengthError("Length of fullname is from 2 to 50");
                errorState = true;
            }
            if (password.length() > 30 || password.length() < 6) {
                errors.setPasswordLengthError("Length of password is from 6 to 30");
                errorState = true;
            } else if (password.equals(confirmPassword) == false) {
                errors.setConfirmPasswordError("Password didn't match. try again");
                errorState = true;
            }
            if (errorState == false) {
                password = EncodingTemplate.getSHA256EncodingString(password);
                RegistrationDAO registrationDAO = new RegistrationDAO();
                boolean result = registrationDAO.signUpAccount(username, fullname, password);
                if (result == true) {
                    request.setAttribute("SUCCESS", "Sign Up Successfully!!!");
                    url = LOGIN_PAGE;
                } else {
                    request.setAttribute("Fail", "Sign Up Failed!!!");
                }
            } else {
                request.setAttribute("ERROR", errors);
            }
        } catch (NoSuchAlgorithmException e) {
            log("RegisterServlet NoSuchAlgorithmException " + e.getMessage());
        } catch (NamingException e) {
            log("RegisterServlet NamingException " + e.getMessage());
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate")) {
                errors.setDuplicateEmailError(username + " is existed !!!");
                request.setAttribute("ERROR", errors);
            }
            log("RegisterServlet SQLException " + e.getMessage());
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
