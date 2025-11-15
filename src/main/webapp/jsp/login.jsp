<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login - Library System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/jsp/fragments/header.jsp" />

    <div class="container">
        <h1>Login</h1>

        <c:if test="${not empty error}">
            <div style="color: red; margin: 10px 0;">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post" style="max-width: 400px;">
            <div style="margin: 10px 0;">
                <label>Username:</label>
                <input type="text" name="username" required style="width: 100%; padding: 8px;">
            </div>
            <div style="margin: 10px 0;">
                <label>Password:</label>
                <input type="password" name="password" required style="width: 100%; padding: 8px;">
            </div>
            <button type="submit" style="padding: 10px 20px; background: #2c3e50; color: white; border: none; cursor: pointer;">Login</button>
        </form>

        <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
    </div>

    <jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>