/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.login;

import com.br.api.connect.ConnectDB;
import com.br.api.data.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wattana
 */
public class Login extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        String strCono = new String("cono");
        String strDivi = new String("divi");
        String strComp = new String("comp");

        if ("".equals(request.getParameter("username"))) {
            request.setAttribute("msg", "Username is Require.");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else if ("".equals(request.getParameter("password"))) {
            request.setAttribute("msg", "Password is Require.");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else if ("".equals(request.getParameter("company"))) {
            request.setAttribute("msg", "Company is Require.");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {

            String username = request.getParameter("username").toUpperCase();
            String password = request.getParameter("password");
            String Location = request.getParameter("location");

            String[] getCompany = request.getParameter("company").split(" : ");
            String cono = getCompany[0];
            String divi = getCompany[1];
            String comp = getCompany[2];
            Connection conn = null;
            try {

                conn = ConnectDB.LoginDB(username, password);
                if (conn != null) {
                    session.setAttribute(strCono, cono);
                    session.setAttribute(strDivi, divi);
                    session.setAttribute(strComp, comp);
                    session.setAttribute("username", username);
                    session.setAttribute("locations", Location);

                    response.sendRedirect("./");
                } else {
                    request.setAttribute("msg", "Password is incorrect.");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
            } catch (Exception ex) {
                String MessageErrors = ConnectDB.ErrorLogs;

                if (MessageErrors.equals("java.sql.SQLException: java.io.IOException: Bad return code from signon info: 0x3000d")) {
                    request.setAttribute("msg", "Password is Expired Please Change Password");
                    request.setAttribute("msg2", "ChangePW");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                } else if (MessageErrors.equals("java.sql.SQLException: java.io.IOException: Password is incorrect.")) {
                    request.setAttribute("msg", "Username and Password is incorrect");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                } else if (MessageErrors.equals("java.sql.SQLException: java.io.IOException: Bad return code from signon info: 0x20002")) {
                    request.setAttribute("msg", "Username has Disable Please Contract ICT");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                } else if (MessageErrors.equals("java.sql.SQLException: java.io.IOException: User ID is not known.")) {
                    request.setAttribute("msg", "User ID is not known");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", MessageErrors);
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
        }
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
