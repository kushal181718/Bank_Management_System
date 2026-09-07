<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Deposit Money</title>

<style>

body {
    margin: 0;
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #3b82f6, #06b6d4);
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
    color: #2563eb;
    margin-bottom: 30px;
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
    background: #16a34a;
    color: white;
    font-size: 17px;
    cursor: pointer;
}

button:hover {
    background: #15803d;
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

    <h1>Deposit Amount</h1>

   <%
    Integer accNum = (Integer) session.getAttribute("accNum");

    double currentBalance = 0;

    if (accNum != null) {
        try {
            currentBalance = com.bms.model.BankImple.getBalance(accNum);
            session.setAttribute("bal", currentBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

<p>
    Current Balance:
    <b>₹ <%= currentBalance %></b>
</p>

    <form action="DepositServlet" method="post">

        <label>Enter Amount</label>

        <input
            type="number"
            name="amount"
            min="1"
            step="0.01"
            placeholder="Enter deposit amount"
            required>

        <button type="submit">
            Deposit
        </button>

    </form>

    <a class="back" href="Dashboard.jsp">
        ← Back to Dashboard
    </a>

</div>

</body>
</html>