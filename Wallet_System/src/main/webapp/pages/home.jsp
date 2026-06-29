<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%

User user = (User)session.getAttribute("user");

if(user == null){

    response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
    return;

}

%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>E-Wallet Dashboard</title>

<style>

*{

margin:0;
padding:0;
box-sizing:border-box;
font-family:Arial;

}

body{

background:#0F172A;

}

header{

background:#1E293B;
padding:20px;
display:flex;
justify-content:space-between;
align-items:center;
color:white;

}

header h2{

color:#38BDF8;

}

.logout{

text-decoration:none;
background:#EF4444;
padding:10px 20px;
color:white;
border-radius:8px;

}

.container{

width:1100px;
margin:auto;
margin-top:40px;

}

.welcome{

background:white;
padding:25px;
border-radius:15px;
margin-bottom:25px;

}

.welcome h1{

color:#2563EB;

}

.cards{

display:grid;
grid-template-columns:repeat(2,1fr);
gap:20px;

}

.card{

background:white;
padding:30px;
border-radius:15px;
text-align:center;
transition:.3s;

}

.card:hover{

transform:translateY(-5px);

}

.card h2{

margin-bottom:15px;
color:#2563EB;

}

.card p{

margin-bottom:20px;

}

.card a{

text-decoration:none;
background:#2563EB;
color:white;
padding:12px 25px;
border-radius:10px;

}

.balance{

margin-top:30px;
background:#10B981;
color:white;
padding:25px;
border-radius:15px;
text-align:center;

}

.balance h1{

font-size:40px;

}

</style>

</head>

<body>

<header>

<h2>E-Wallet System</h2>

<a class="logout"
href="<%=request.getContextPath()%>/WalletController?action=logout">

Logout

</a>

</header>

<div class="container">

<div class="welcome">

<h1>

Welcome

<%=user.getFirst_name()%>

<%=user.getLast_name()%>

</h1>

<p>

Phone :

<%=user.getPhone()%>

</p>

</div>

<div class="cards">

<div class="card">

<h2>Deposit</h2>

<p>Add money to your wallet.</p>

<a href="deposit.jsp">

Deposit

</a>

</div>

<div class="card">

<h2>Withdraw</h2>

<p>Withdraw money from your wallet.</p>

<a href="withdraw.jsp">

Withdraw

</a>

</div>

<div class="card">

<h2>Transfer</h2>

<p>Transfer money to another user.</p>

<a href="transfer.jsp">

Transfer

</a>

</div>

<div class="card">

<h2>Check Balance</h2>

<p>View your wallet balance.</p>

<a href="<%=request.getContextPath()%>/WalletController?action=balance">

Balance

</a>

</div>

</div>

</div>

</body>

</html>