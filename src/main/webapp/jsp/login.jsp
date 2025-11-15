<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Вход</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="partial/header.jsp"/>

    <main class="container">
        <div class="card" style="max-width: 400px; margin: 50px auto;">
            <h2>Вход</h2>

            <c:if test="${not empty error}">
                <div style="color: red; margin: 10px 0;">${error}</div>
            </c:if>

            <form action="login" method="post">
                <div style="margin: 10px 0;">
                    <input type="text" name="username" placeholder="Имя пользователя" required
                           style="width: 100%; padding: 10px;">
                </div>
                <div style="margin: 10px 0;">
                    <input type="password" name="password" placeholder="Пароль" required
                           style="width: 100%; padding: 10px;">
                </div>
                <button type="submit" style="width: 100%; padding: 10px; background: #2c3e50; color: white; border: none;">
                    Войти
                </button>
            </form>

            <p style="text-align: center; margin-top: 15px;">
                Нет аккаунта? <a href="registration">Зарегистрироваться</a>
            </p>
        </div>
    </main>

    <jsp:include page="partial/footer.jsp"/>
</body>
</html>