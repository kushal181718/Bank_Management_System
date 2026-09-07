package com.bms.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.bms.model.BankImple;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        double amount;

        try {

            amount = Double.parseDouble(req.getParameter("amount"));

        } catch (NumberFormatException e) {

            resp.getWriter().println(
                "<h2>Invalid amount</h2>"
            );

            return;
        }

        if (amount <= 0) {

            resp.getWriter().println(
                "<h2>Amount must be greater than zero</h2>"
            );

            return;
        }

        HttpSession session = req.getSession();

        Integer accNum =
                (Integer) session.getAttribute("accNum");

        if (accNum == null) {

            resp.getWriter().println(
                "<h2>Session expired. Please login again.</h2>"
            );

            return;
        }

        try {

            boolean result =
                    BankImple.withdraw(accNum, amount);

            if (result) {

                double newBalance =
                        BankImple.getBalance(accNum);

                session.setAttribute("bal", newBalance);

                resp.sendRedirect("Dashboard.jsp");

            } else {

                resp.getWriter().println(
                    "<h2>Insufficient Balance</h2>"
                );

                resp.getWriter().println(
                    "<p>You don't have enough balance.</p>"
                );

                resp.getWriter().println(
                    "<a href='withdraw.jsp'>Go Back</a>"
                );
            }

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