<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Withdraw Money</title>

<style>

body {
    margin: 0;
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #ef4444, #f97316);
    min-height: 100vh;

    display: flex;
    justify-content: center;
    align-items: center;
}

.card {
    width: 420px;
    background: white;
    padding: 35px;
    border-radius: 18px;
    box-shadow: 0 15px 35px rgba(0,0,0,.3);
}

h1 {
    text-align: center;
    color: #dc2626;
    margin-bottom: 30px;
}

.balance {
    text-align: center;
    font-size: 18px;
    margin-bottom: 25px;
}

label {
    display: block;
    font-weight: bold;
    margin-bottom: 8px;
}

input {
    width: 100%;
    padding: 13px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 8px;
    margin-bottom: 20px;
    font-size: 16px;
}

button {
    width: 100%;
    padding: 14px;
    border: none;
    border-radius: 8px;
    background: #dc2626;
    color: white;
    font-size: 17px;
    cursor: pointer;
}

button:hover {
    background: #b91c1c;
}

.back {
    display: block;
    text-align: center;
    margin-top: 20px;
    text-decoration: none;
    color: #2563eb;
}

</style>

</head>

<body>

<div class="card">

    <h1>Withdraw Amount</h1>

    <%
        Integer accNum = (Integer) session.getAttribute("accNum");

        double currentBalance = 0;

        if (accNum != null) {
            try {
                currentBalance =
                    com.bms.model.BankImple.getBalance(accNum);

                session.setAttribute("bal", currentBalance);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    %>

    <div class="balance">
        Current Balance:
        <b>₹ <%= currentBalance %></b>
    </div>

    <form action="WithdrawServlet" method="post">

        <label>Enter Amount</label>

        <input
            type="number"
            name="amount"
            min="1"
            step="0.01"
            placeholder="Enter withdrawal amount"
            required>

        <button type="submit">
            Withdraw
        </button>

    </form>

    <a class="back" href="Dashboard.jsp">
        ← Back to Dashboard
    </a>

</div>

</body>
</html>