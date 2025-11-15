<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход в систему</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h2>Вход в систему</h2>

    <!-- Сообщение об успешной регистрации -->
    <c:if test="${param.registered != null}">
        <div style="color: green;">Регистрация успешна! Теперь войдите в систему.</div>
    </c:if>

    <!-- Показываем ошибки если есть -->
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <!-- Форма входа -->
    <form action="/login" method="post">
        <div>
            <input type="text" name="username" placeholder="Имя пользователя" required>
        </div>
        <div>
            <input type="password" name="password" placeholder="Пароль" required>
        </div>
        <button type="submit">Войти</button>
    </form>

    <!-- Ссылка на регистрацию -->
    <p><a href="/register">Зарегистрироваться</a></p>
</main>
</body>
</html>