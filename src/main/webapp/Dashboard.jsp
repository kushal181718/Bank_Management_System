<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Dashboard</title>

<style>
*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, sans-serif;
}

body{
    background:linear-gradient(to right,#4facfe,#00f2fe);
}

.header{
    background:#0d6efd;
    color:white;
    padding:20px;
    text-align:center;
    font-size:30px;
    font-weight:bold;
}

.container{
    width:90%;
    margin:40px auto;
}

h2{
    text-align:center;
    color:white;
    margin-bottom:30px;
}

.card-container{
    display:grid;
    grid-template-columns:repeat(3,1fr);
    gap:25px;
}

.card{
    background:white;
    border-radius:10px;
    padding:30px;
    text-align:center;
    box-shadow:0 0 10px rgba(0,0,0,0.3);
}

.card h3{
    margin-bottom:20px;
    color:#333;
}

.card button{
    width:100%;
    padding:12px;
    border:none;
    border-radius:5px;
    color:white;
    font-size:16px;
    cursor:pointer;
}

.withdraw{
    background:#dc3545;
}

.deposit{
    background:#198754;
}

.view{
    background:#0d6efd;
}

.history{
    background:#fd7e14;
}

.transfer{
    background:#6f42c1;
}

.logout{
    background:#212529;
}

button:hover{
    opacity:0.9;
}
</style>

</head>
<body>

<div class="header">
    BANK MANAGEMENT SYSTEM
</div>

<div class="container">

    <h2>Welcome to Your Dashboard : <%= session.getAttribute("name")%></h2>
    
	
    <div class="card-container">

        <div class="card">
            <h3>Withdraw Amount</h3>
            <form action="withdraw.jsp">
                <button class="withdraw">Withdraw</button>
            </form>
        </div>

        <div class="card">
            <h3>Deposit Amount</h3>
            <form action="deposit.jsp">
                <button class="deposit">Deposit</button>
            </form>
        </div>

        <div class="card">
            <h3>View Account</h3>
            <form action="view.jsp">
                <button class="view">View Account</button>
            </form>
        </div>

        <div class="card">
            <h3>Transaction History</h3>
            <form action="TransactionHistoryServlet">
                <button class="history">View History</button>
            </form>
        </div>

        <div class="card">
            <h3>Transfer Amount</h3>
            <form action="transfer.jsp">
                <button class="transfer">Transfer</button>
            </form>
        </div>

        <div class="card">
            <h3>Logout</h3>
            <form action="LogoutServlet" method="get">
        		<button class="logout" type="submit">Logout</button>
    		</form>
        </div>

    </div>

</div>

</body>
</html>