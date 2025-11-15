<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>register</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h1>Регистрация</h1>
    <section>
        <form method="post" action="${pageContext.request.contextPath}/register">
            <label for="username">Логин:</label><br>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Пароль:</label><br>
            <input type="password" id="password" name="password" required><br><br>
            <button type="submit" class="button">Зарегистрироваться</button>
        </form>
        <p><a href="${pageContext.request.contextPath}/login">Уже есть аккаунт? Войти</a></p>
        <% if (request.getParameter("error") != null) { %>
            <p><%= request.getParameter("error") %></p>
        <% } %>
    </section>
</main>
<jsp:include page="/jsp/partial/footer.jsp" />

<script src="/js/script.js"></script>
</body>
</html>