<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main>
    <h1>Вход</h1>
    <section>
        <form method="post">
            <label for="username">Логин:</label><br>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Пароль:</label><br>
            <input type="password" id="password" name="password" required><br><br>
            <button type="submit" class="button">Войти</button>
        </form>
        <p><a href="${pageContext.request.contextPath}/register">Нет аккаунта? Регистрация</a></p>
        <% if ("invalid".equals(request.getParameter("error"))) { %>
            <p style="color: red;">Неверный логин или пароль!</p>
        <% } else if ("empty".equals(request.getParameter("error"))) { %>
            <p style="color: red;">Заполните все поля!</p>
        <% } %>
        <% if ("true".equals(request.getParameter("registered"))) { %>
            <p style="color: green">Регистрация прошла успешно! Войдите.</p>
        <% } %>
    </section>
</main>
<jsp:include page="/jsp/partial/footer.jsp" />

<script src="/js/script.js"></script>
</body>
</html>