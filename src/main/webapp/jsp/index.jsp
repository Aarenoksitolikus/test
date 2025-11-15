<%-- index.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Блог-платформа</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<!-- Header with full navigation -->
<header>
    <div class="container">
        <nav>
            <div class="logo">📝 Блог-платформа</div>
            <ul class="nav-links">
                <li><a href="jsp/index">Главная</a></li>
                <li><a href="jsp/posts">Посты</a></li>
                <li><a href="jsp/comments">Комментарии</a></li>
            </ul>
            <div class="auth-links">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span class="user-info">Привет, ${sessionScope.user.username}!</span>
                        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login">Войти</a>
                        <a href="${pageContext.request.contextPath}/register">Регистрация</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <button id="menuToggle" class="menu-toggle">☰</button>
        </nav>
    </div>
</header>

<nav id="mainNav" class="nav-sidebar">
    <ul>
        <li><a href="/index">Главная</a></li>
        <li><a href="jsp/posts">Посты</a></li>
        <li><a href="jsp/comments">Комментарии</a></li>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li><span class="user-info">Привет, ${sessionScope.user.username}!</span></li>
                <li><a href="/logout">Выйти</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="$/login">Войти</a></li>
                <li><a href="$/register">Регистрация</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    <button class="close-btn">×</button>
</nav>

<div class="overlay" id="overlay"></div>

<div class="container main-content">
    <section class="hero">
        <h1>Добро пожаловать в блог-платформу</h1>
        <p>Место, где можно делиться своими мыслями, идеями и находить единомышленников. Присоединяйтесь к нашему сообществу!</p>
        <c:if test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/register" class="btn">Начать писать</a>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/posts/create" class="btn">Создать пост</a>
        </c:if>
    </section>

    <section class="features">
        <div class="feature-card">
            <h3>📝 Создание постов</h3>
            <p>Публикуйте свои мысли, делитесь опытом и находите аудиторию для ваших идей.</p>
        </div>
        <div class="feature-card">
            <h3>💬 Обсуждения</h3>
            <p>Комментируйте посты других пользователей и участвуйте в интересных дискуссиях.</p>
        </div>
        <div class="feature-card">
            <h3>👥 Сообщество</h3>
            <p>Станьте частью активного сообщества авторов и читателей.</p>
        </div>
    </section>
</div>

<footer>
    <div class="container">
        <p>&copy; 2024 Блог-платформа. Все права защищены.</p>
    </div>
</footer>

<script src="js/script.js"></script>
</body>
</html>
