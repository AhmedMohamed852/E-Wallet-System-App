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

<title>Transfer Money</title>

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

width:460px;
background:white;
padding:35px;
border-radius:15px;
box-shadow:0 0 15px rgba(0,0,0,.4);

}

h1{

text-align:center;
margin-bottom:25px;
color:#2563EB;

}

input{

width:100%;
padding:15px;
margin-top:15px;
border-radius:10px;
border:1px solid #ccc;
font-size:16px;

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
background:#2563EB;
color:white;
border:none;
border-radius:10px;
font-size:18px;
cursor:pointer;

}

button:hover{

background:#1D4ED8;

}

.back{

display:block;
margin-top:20px;
text-align:center;
text-decoration:none;
background:#64748B;
color:white;
padding:12px;
border-radius:10px;

}

</style>

</head>

<body>

<div class="container">

<h1>Transfer Money</h1>

<form
action="<%=request.getContextPath()%>/WalletController?action=transfer"
method="post">

<input
type="text"
name="phone"
placeholder="Receiver Phone Number"
required>

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

Transfer Money

</button>

</form>

<a
class="back"
href="home.jsp">

Back To Home

</a>

</div>

</body>

</html>