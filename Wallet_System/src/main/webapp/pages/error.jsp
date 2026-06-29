<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Error</title>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:Arial;
}

body{

height:100vh;
display:flex;
justify-content:center;
align-items:center;
background:#0F172A;

}

.container{

width:500px;
background:white;
padding:40px;
border-radius:15px;
text-align:center;
box-shadow:0 0 20px rgba(0,0,0,.3);

}

.icon{

font-size:70px;

}

h1{

margin:20px 0;
color:#DC2626;

}

p{

font-size:18px;
margin-bottom:30px;
color:#555;

}

a{

display:inline-block;
padding:15px 30px;
background:#2563EB;
color:white;
text-decoration:none;
border-radius:10px;

}

a:hover{

background:#1D4ED8;

}

</style>

</head>

<body>

<div class="container">

<div class="icon">❌</div>

<h1>Something Went Wrong</h1>

<p>

<%

String message = (String)request.getAttribute("message");

if(message == null){

message = "An unexpected error occurred.";

}

out.print(message);

%>

</p>

<a href="<%=request.getContextPath()%>/WalletController?action=homePage">

Back To Home

</a>

</div>

</body>

</html>