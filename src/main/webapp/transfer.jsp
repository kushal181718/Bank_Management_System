<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Transfer Money</title>

<style>

body {
    margin: 0;
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #7c3aed, #2563eb);
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
    color: #4f46e5;
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
    background: #4f46e5;
    color: white;
    font-size: 17px;
    cursor: pointer;
}

button:hover {
    background: #4338ca;
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

    <h1>Transfer Money</h1>

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

    <form action="TransferServlet" method="post">

        <label>Receiver Account Number</label>

        <input
            type="number"
            name="receiverAccNum"
            placeholder="Enter receiver account number"
            min="1"
            required>

        <label>Transfer Amount</label>

        <input
            type="number"
            name="amount"
            placeholder="Enter amount"
            min="1"
            step="0.01"
            required>

        <button type="submit">
            Transfer Money
        </button>

    </form>

    <a class="back" href="Dashboard.jsp">
        ← Back to Dashboard
    </a>

</div>

</body>
</html>