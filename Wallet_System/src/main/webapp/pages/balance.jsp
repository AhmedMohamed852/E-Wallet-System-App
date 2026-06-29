<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@page import="model.User"%>

<%
User user=(User)session.getAttribute("user");

if(user==null){

response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
return;

}

Double balance=(Double)request.getAttribute("balance");

if(balance==null){

balance=0.0;

}
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Wallet Balance</title>

<style>

*{

margin:0;
padding:0;
box-sizing:border-box;
font-family:Arial;

}

body{

background:#0F172A;
display:flex;
justify-content:center;
align-items:center;
height:100vh;

}

.container{

width:500px;
background:white;
padding:40px;
border-radius:15px;
text-align:center;
box-shadow:0 0 20px rgba(0,0,0,.3);

}

h1{

color:#2563EB;
margin-bottom:30px;

}

.balance{

font-size:45px;
font-weight:bold;
color:#10B981;
margin-bottom:30px;

}

.back{

display:block;
text-decoration:none;
background:#2563EB;
color:white;
padding:15px;
border-radius:10px;

}

.back:hover{

background:#1D4ED8;

}

</style>

</head>

<body>

<div class="container">

<h1>Current Balance</h1>

<div class="balance">

<%=balance%> EGP

</div>

<a
class="back"
href="home.jsp">

Back To Home

</a>

</div>

</body>

</html>