<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Login</title>

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

width:380px;
background:white;
padding:40px;
border-radius:15px;
box-shadow:0 0 20px rgba(0,0,0,.3);

}

h1{

text-align:center;
margin-bottom:30px;
color:#2563EB;

}

input{

width:100%;
padding:15px;
margin-top:15px;
border:1px solid #ccc;
border-radius:10px;

}

button{

width:100%;
padding:15px;
margin-top:20px;
border:none;
background:#2563EB;
color:white;
font-size:18px;
border-radius:10px;
cursor:pointer;

}

button:hover{

background:#1E40AF;

}

a{

display:block;
text-align:center;
margin-top:20px;
text-decoration:none;
color:#2563EB;

}

</style>

</head>

<body>

<div class="container">

<h1>E-Wallet Login</h1>

<form action="<%=request.getContextPath()%>/WalletController?action=login"
method="post">

<input
type="text"
name="first_name"
placeholder="First Name"
required>

<input
type="password"
name="password"
placeholder="Password"
required>

<button>

Login

</button>

</form>

<a href="<%=request.getContextPath()%>/pages/signup.jsp">

Create Account

</a>

</div>

</body>

</html>