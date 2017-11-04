<!DOCTYPE html>
<html >
<head>
<title>Home Page</title>
</head>
<body onload="document.f.username.focus();">
  <div class="container">
  	<%@include file="header.jsp"%>
    <div>
    	<form action="/register" method="POST">
	        User Name: <input type="text" name="username" value="" placeholder="Email" />
	        Password: <input type="password" name="password" placeholder="Password" />
	        Confirm Password: <input type="password" placeholder="Confirm Password" />
	        <input type="submit" id="register" value="Submit"
	          class="btn btn-primary" style="margin-top: 6px;" />
	    </form>
    </div>
  </div>
</body>
</html>