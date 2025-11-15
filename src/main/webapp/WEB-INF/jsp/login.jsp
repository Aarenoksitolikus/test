<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Login</h2>
<c:if test="${not empty error}">
  <div style="color:red">${error}</div>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
  <input name="username" placeholder="Username" required />
  <input name="password" type="password" placeholder="Password" required minlength="8" />
  <button type="submit">Login</button>
</form>
<a href="${pageContext.request.contextPath}/register">Register</a>
</body>
</html>
