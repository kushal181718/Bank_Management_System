<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>View Account</title>

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
    width: 450px;
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

.details {
    margin-bottom: 20px;
}

.details p {
    font-size: 18px;
    border-bottom: 1px solid #ddd;
    padding: 12px;
}

.label {
    font-weight: bold;
}

button {
    width: 100%;
    padding: 14px;
    border: none;
    border-radius: 10px;
    background: #2563eb;
    color: white;
    font-size: 17px;
    cursor: pointer;
}

button:hover {
    background: #1d4ed8;
}

</style>

</head>

<body>

<div class="card">

    <h1>Account Details</h1>

    <div class="details">

        <p>
            <span class="label">Account Number:</span>
            <%= session.getAttribute("accNum") %>
        </p>

        <p>
            <span class="label">Name:</span>
            <%= session.getAttribute("name") %>
        </p>

        <p>
            <span class="label">Email:</span>
            <%= session.getAttribute("email") %>
        </p>

        <p>
            <span class="label">Phone:</span>
            <%= session.getAttribute("phone") %>
        </p>

        <p>
            <span class="label">Balance:</span>
            ₹ <%= session.getAttribute("bal") %>
        </p>

    </div>

    <button onclick="window.location.href='Dashboard.jsp'">
        Back to Dashboard
    </button>

</div>

</body>
</html>