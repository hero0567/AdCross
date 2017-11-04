
<!DOCTYPE html>
<html >
<head>
<title>Home Page</title>
</head>
<body onload="document.f.username.focus();">
  <div class="container">
  	<%@include file="header.jsp"%>
    <div class="content">
    	<spring:message code="app.hello"/>
       This is home page. <sec:authentication property="name"/>   
    </div>
  </div>
</body>
</html>