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

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int receiverAccNum;
        double amount;

        try {

            receiverAccNum =
                    Integer.parseInt(req.getParameter("receiverAccNum"));

            amount =
                    Double.parseDouble(req.getParameter("amount"));

        } catch (NumberFormatException e) {

            resp.getWriter().println("<h2>Invalid input</h2>");
            return;
        }

        if (amount <= 0) {

            resp.getWriter().println(
                    "<h2>Amount must be greater than zero</h2>"
            );

            return;
        }

        HttpSession session = req.getSession();

        Integer senderAccNum =
                (Integer) session.getAttribute("accNum");

        if (senderAccNum == null) {

            resp.getWriter().println(
                    "<h2>Session expired. Please login again.</h2>"
            );

            return;
        }

        // Don't allow transfer to the same account
        if (senderAccNum == receiverAccNum) {

            resp.getWriter().println(
                    "<h2>You cannot transfer money to your own account.</h2>"
            );

            resp.getWriter().println(
                    "<a href='transfer.jsp'>Go Back</a>"
            );

            return;
        }

        try {

            boolean result =
                    BankImple.transfer(
                            senderAccNum,
                            receiverAccNum,
                            amount
                    );

            if (result) {

                // Update sender's session balance
                double newBalance =
                        BankImple.getBalance(senderAccNum);

                session.setAttribute("bal", newBalance);

                resp.sendRedirect("Dashboard.jsp");

            } else {

                resp.getWriter().println(
                        "<h2>Transfer Failed</h2>"
                );

                resp.getWriter().println(
                        "<p>Check the receiver account or your balance.</p>"
                );

                resp.getWriter().println(
                        "<a href='transfer.jsp'>Go Back</a>"
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