<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная - Test</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<header>
    <nav class="container">
        <div class="logo">
            <h2><i class="fas fa-blog"></i> Test Blog</h2>
        </div>
        <ul class="nav-links">
            <c:if test="${not empty sessionScope.user_name}">
                <li>Добро пожаловать, ${sessionScope.user_name}!</li>
                <li><a href="${pageContext.request.contextPath}/posts">Посты</a></li>
                <li><a href="${pageContext.request.contextPath}/create">Создать пост</a></li>
                <li><a href="${pageContext.request.contextPath}/auth?logout=true">Выйти</a></li>
            </c:if>
            <c:if test="${empty sessionScope.user_name}">
                <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
                <li><a href="${pageContext.request.contextPath}/register">Регистрация</a></li>
            </c:if>
        </ul>
    </nav>
</header>

<main class="container">
    <div class="hero">
        <h1>Добро пожаловать в блог!</h1>
        <p>Контрольная по ОРИСу - Платформа для блогов</p>
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/posts" class="btn btn-primary">
                <i class="fas fa-list"></i> Смотреть посты
            </a>
            <c:if test="${sessionScope.role != 'unknown_user'}">
                <a href="${pageContext.request.contextPath}/create" class="btn btn-secondary">
                    <i class="fas fa-plus"></i> Создать пост
                </a>
            </c:if>
            <c:if test="${sessionScope.role == 'moderator'}">
                <a href="#" class="btn btn-danger">
                    <i class="fas fa-shield-alt"></i> Режим модератора
                </a>
            </c:if>
        </div>
    </div>
</main>

<footer>
    <div class="container">
        <p>&copy; 2024 Test. Контрольная по ОРИСу.</p>
    </div>
</footer>
</body>
</html>