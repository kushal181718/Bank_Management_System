package com.bms.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.bms.model.BankImple;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignupServ")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("username");
        String email = req.getParameter("email");
        String phoneString = req.getParameter("phone");
        String pass = req.getParameter("password");

        try {

            long phone = Long.parseLong(phoneString);

            boolean b = BankImple.createAccount(
                    name,
                    email,
                    phone,
                    pass
            );

            if (b) {

                System.out.println("Account created successfully");

                RequestDispatcher rd =
                        req.getRequestDispatcher("login.html");

                rd.forward(req, resp);

            } else {

                resp.getWriter().println(
                        "<h2>Account creation failed</h2>"
                );
            }

        } catch (NumberFormatException e) {

            resp.getWriter().println(
                    "<h2>Invalid phone number</h2>"
            );

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

            resp.getWriter().println(
                    "<h2>Database Error</h2>"
            );

            resp.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}