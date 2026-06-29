<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Sign Up</title>

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

width:420px;
background:white;
padding:40px;
border-radius:15px;

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

}

button:hover{

background:#1E40AF;

}

a{

display:block;
margin-top:20px;
text-align:center;
text-decoration:none;
color:#2563EB;

}

</style>

</head>

<body>

<div class="container">

<h1>Create Account</h1>

<form action="<%=request.getContextPath()%>/WalletController?action=signUp"
method="post">

<input
type="text"
name="first_name"
placeholder="First Name"
required>

<input
type="text"
name="last_name"
placeholder="Last Name"
required>

<input
type="text"
name="phone"
placeholder="Phone Number"
required>

<input
type="password"
name="password"
placeholder="Password"
required>

<button>

Create Account

</button>

</form>

<a href="<%=request.getContextPath()%>/pages/login.jsp">

Already Have Account?

</a>

</div>

</body>

</html>