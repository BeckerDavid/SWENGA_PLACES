<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Places</title>
<link rel="stylesheet" href="./Login/login.css">
</head>
<body>
	<form class="loginform" id="login-form"
		method="post">

		<input type="text" name="username" placeholder="Username" required>
		<input type="password" name="password" placeholder="Password" required>

		<a href="login">
			<button type="button" class="btn btn-success" type="submit">Login</button>
		</a> 
		
		<a href="./register">
			<button type="button" class="btn btn-success">Register</button>
		</a>
	</form>



</body>
</html>