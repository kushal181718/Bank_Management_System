package com.bms.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.bms.DTO.Customer;
import com.bms.model.BankImple;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginServ")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        try {

            Customer c = BankImple.login(email, pass);

            if (c != null) {

                HttpSession hs = req.getSession();

                hs.setAttribute("accNum", c.getAccNum());
                hs.setAttribute("name", c.getName());
                hs.setAttribute("email", c.getEmail());
                hs.setAttribute("phone", c.getPhone());
                hs.setAttribute("bal", c.getBal());
                hs.setAttribute("password", c.getPass());

                resp.sendRedirect("Dashboard.jsp");

            } else {

                RequestDispatcher r =
                        req.getRequestDispatcher("login.html");

                r.forward(req, resp);
            }

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
}