<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h2>Регистрация</h2>

    <!-- Показываем ошибки регистрации -->
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <!-- Форма регистрации -->
    <form action="/register" method="post">
        <div>
            <input type="text" name="username" placeholder="Имя пользователя" required>
        </div>
        <div>
            <input type="password" name="password" placeholder="Пароль" required>
        </div>
        <div>
            <input type="password" name="confirmPassword" placeholder="Подтвердите пароль" required>
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form>

    <!-- Ссылка на вход -->
    <p><a href="/login">Войти в систему</a></p>
</main>
</body>
</html>