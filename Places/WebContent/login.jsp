<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Places</title>
<link rel="stylesheet" href="Resources/Login/login.css">
</head>
<body>
			<form class="loginform" id="register-form" action="register.php" method="post" novalidate="novalidate">

				<input type="text" name="username" placeholder="Username" required>
				<input type="password" name="password" placeholder="Password"
					required> 
				<input type="submit" value="Login">
				<input class="register" type="submit" value="Register">
			</form>


</body>
</html>