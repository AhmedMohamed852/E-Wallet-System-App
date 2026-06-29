<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@page import="model.User"%>

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

<title>Withdraw</title>

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

width:450px;
background:white;
padding:35px;
border-radius:15px;

}

h1{

text-align:center;
margin-bottom:25px;
color:#DC2626;

}

input{

width:100%;
padding:15px;
margin-top:15px;
border-radius:10px;
border:1px solid #ccc;

}

textarea{

width:100%;
height:120px;
padding:15px;
margin-top:15px;
border-radius:10px;
border:1px solid #ccc;
resize:none;

}

button{

width:100%;
padding:15px;
margin-top:20px;
background:#DC2626;
color:white;
border:none;
border-radius:10px;
font-size:18px;
cursor:pointer;

}

button:hover{

background:#B91C1C;

}

.back{

display:block;
margin-top:20px;
text-align:center;
text-decoration:none;
background:#2563EB;
color:white;
padding:12px;
border-radius:10px;

}

</style>

</head>

<body>

<div class="container">

<h1>Withdraw Money</h1>

<form action="<%=request.getContextPath()%>/WalletController?action=withdraw"
method="post">

<input
type="number"
step="0.01"
name="amount"
placeholder="Amount"
required>

<textarea
name="description"
placeholder="Description"></textarea>

<button>

Withdraw

</button>

</form>

<a class="back"
href="home.jsp">

Back To Home

</a>

</div>

</body>

</html>