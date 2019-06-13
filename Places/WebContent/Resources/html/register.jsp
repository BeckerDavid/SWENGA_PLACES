<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<link rel="stylesheet" href="../css/register.css">
</head>
<body>
<div class="formstyle">
<h1>Sign Up</h1>
<form class="signupform" id="signup-form" method="post">

	<input type="text" name="username" placeholder="Username" required>

    <input type="password" name="password" placeholder="Password" required>
	<input type="password" name="password2" placeholder="Confirm Password" required>
	<input type="text" name="firstname" placeholder="First Name" required>
	<input type="text" name="lastname" placeholder="Last Name" required>
	<input type="email" name="mail" placeholder="E-Mail Address" required>
	<input type="text" name="country" placeholder="Country" required>
	<input type="date" name="dayofbirth" placeholder="Day of Birth" required>
	
	<a href="login">
			<button type="button" class="btn1">Submit</button>
	</a> 
</form>
</div>

</body>
</html>