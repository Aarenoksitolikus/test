<%-- posts.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Посты</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<header>
    <div class="container">
        <nav>
            <div class="logo">📝 Блог-платформа</div>
            <ul class="nav-links">
                <li><a href="/index">Главная</a></li>
                <li><a href="/posts">Посты</a></li>
                <li><a href="/comments">Комментарии</a></li>
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
                <li><a href="/login">Войти</a></li>
                <li><a href="/register">Регистрация</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    <button class="close-btn">×</button>
</nav>

<div class="overlay" id="overlay"></div>

<div class="container main-content">
    <div class="posts-header">
        <h1 class="posts-title">Все посты</h1>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/posts/create" class="create-post-btn">+ Создать пост</a>
        </c:if>
    </div>

    <div class="posts-grid">
        <c:choose>
            <c:when test="${not empty posts}">
                <c:forEach var="post" items="${posts}">
                    <div class="post-card">
                        <div class="post-header">
                            <a href="${pageContext.request.contextPath}/posts/view?id=${post.id}" class="post-title">
                                    ${post.title}
                            </a>
                            <div class="post-meta">
                                <div>${post.createdDate}</div>
                                <c:if test="${not empty post.commentCount}">
                                    <div>💬 ${post.commentCount} комментариев</div>
                                </c:if>
                            </div>
                        </div>

                        <div class="post-content">
                                ${post.content}
                        </div>

                        <div class="post-footer">
                            <div class="post-author">
                                👤 ${post.authorUsername}
                            </div>

                            <div class="post-actions">
                                <a href="${pageContext.request.contextPath}/posts/view?id=${post.id}" class="btn btn-comment">
                                    Комментировать
                                </a>

                                <c:if test="${not empty sessionScope.user && (sessionScope.user.role == 'MODERATOR' || sessionScope.user.id == post.authorId)}">
                                    <a href="${pageContext.request.contextPath}/posts/edit?id=${post.id}" class="btn btn-edit">
                                        Редактировать
                                    </a>
                                    <a href="${pageContext.request.contextPath}/posts/delete?id=${post.id}"
                                       class="btn btn-delete"
                                       onclick="return confirm('Вы уверены, что хотите удалить этот пост?')">
                                        Удалить
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="no-posts">
                    <h3>Пока нет постов</h3>
                    <p>Будьте первым, кто опубликует запись!</p>
                    <c:if test="${empty sessionScope.user}">
                        <p><a href="${pageContext.request.contextPath}/login" style="color: #8b7355;">Войдите</a>, чтобы создать пост</p>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<footer>
    <div class="container">
        <p>&copy; 2024 Блог-платформа. Все права защищены.</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
