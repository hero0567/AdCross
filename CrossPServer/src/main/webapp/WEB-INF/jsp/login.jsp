<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body onload="document.f.username.focus();">
  <div class="container">
    <%@include file="header.jsp"%>
    <div class="content" style="margin-top: 50px; margin-left: 200px;">
      <h2>Login with Username and Password</h2>
      <form name="form" action="/login" method="POST">
        <fieldset>
          <input type="text" name="username" value="" placeholder="Username" />
          <input type="password" name="password" placeholder="Password" />
        </fieldset>
        <input type="submit" id="login" value="Login"
          class="btn btn-primary" style="margin-top: 6px;" />
      </form>
    </div>
  </div>
</body>
</html>