<%-- posts.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Посты - Блог-платформа</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <style>
        .posts-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 2px solid #d4c8b5;
        }

        body.dark-theme .posts-header {
            border-bottom-color: #5a4a32;
        }

        .posts-title {
            color: #5c4b3c;
            font-size: 2.5rem;
            margin: 0;
        }

        body.dark-theme .posts-title {
            color: #e8d8c0;
        }

        .create-post-btn {
            background: linear-gradient(135deg, #8b7355 0%, #6d5c46 100%);
            color: white;
            padding: 0.8rem 2rem;
            text-decoration: none;
            border-radius: 25px;
            font-weight: bold;
            transition: transform 0.3s;
            border: none;
            cursor: pointer;
            font-family: "Helvetica Neue", Arial, sans-serif;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            display: inline-block;
        }

        .create-post-btn:hover {
            transform: translateY(-2px);
            background: linear-gradient(135deg, #9c8466 0%, #7d6c56 100%);
        }

        .posts-grid {
            display: grid;
            gap: 2rem;
            margin: 2rem 0;
        }

        .post-card {
            background: #fffef7;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            border: 1px solid #d4c8b5;
            transition: transform 0.3s;
        }

        body.dark-theme .post-card {
            background: #2a2218;
            border-color: #5a4a32;
        }

        .post-card:hover {
            transform: translateY(-5px);
        }

        .post-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 1rem;
        }

        .post-title {
            font-size: 1.5rem;
            color: #5c4b3c;
            text-decoration: none;
            font-family: "Helvetica Neue", Arial, sans-serif;
            font-weight: 600;
        }

        body.dark-theme .post-title {
            color: #e8d8c0;
        }

        .post-title:hover {
            color: #8b7355;
        }

        .post-meta {
            color: #666;
            font-size: 0.9rem;
            text-align: right;
            min-width: 150px;
        }

        body.dark-theme .post-meta {
            color: #b8a890;
        }

        .post-content {
            color: #555;
            margin-bottom: 1rem;
            line-height: 1.6;
            display: -webkit-box;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }

        body.dark-theme .post-content {
            color: #b8a890;
        }

        .post-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 1.5rem;
            padding-top: 1rem;
            border-top: 1px solid #e0d6c5;
        }

        body.dark-theme .post-footer {
            border-top-color: #5a4a32;
        }

        .post-author {
            color: #8b7355;
            font-weight: 500;
        }

        body.dark-theme .post-author {
            color: #c8b8a0;
        }

        .post-actions {
            display: flex;
            gap: 0.5rem;
        }

        .btn {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-size: 0.9rem;
            font-family: "Helvetica Neue", Arial, sans-serif;
            transition: all 0.3s;
        }

        .btn-edit {
            background: #28a745;
            color: white;
        }

        .btn-edit:hover {
            background: #218838;
        }

        .btn-delete {
            background: #dc3545;
            color: white;
        }

        .btn-delete:hover {
            background: #c82333;
        }

        .btn-comment {
            background: #007bff;
            color: white;
        }

        .btn-comment:hover {
            background: #0056b3;
        }

        .no-posts {
            text-align: center;
            padding: 3rem;
            color: #666;
            font-style: italic;
        }

        body.dark-theme .no-posts {
            color: #b8a890;
        }

        .success-message {
            background: #d1edff;
            color: #0c5460;
            padding: 12px 15px;
            border-radius: 5px;
            border: 1px solid #bee5eb;
            margin-bottom: 2rem;
            text-align: center;
        }

        body.dark-theme .success-message {
            background: #1b2d32;
            color: #6ea8fe;
            border-color: #0c5460;
        }

        .error-message {
            background: #f8d7da;
            color: #721c24;
            padding: 12px 15px;
            border-radius: 5px;
            border: 1px solid #f5c6cb;
            margin-bottom: 2rem;
            text-align: center;
        }

        body.dark-theme .error-message {
            background: #2d1b1f;
            color: #f1aeb5;
            border-color: #842029;
        }

        @media (max-width: 768px) {
            .posts-header {
                flex-direction: column;
                gap: 1rem;
                text-align: center;
            }

            .post-header {
                flex-direction: column;
                gap: 0.5rem;
            }

            .post-meta {
                text-align: left;
            }

            .post-footer {
                flex-direction: column;
                gap: 1rem;
                align-items: flex-start;
            }

            .post-actions {
                width: 100%;
                justify-content: flex-end;
            }
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <nav>
            <div class="logo">📝 Блог-платформа</div>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/">Главная</a></li>
                <li><a href="${pageContext.request.contextPath}/posts">Посты</a></li>
                <li><a href="${pageContext.request.contextPath}/comments">Комментарии</a></li>
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
        <li><a href="${pageContext.request.contextPath}/">Главная</a></li>
        <li><a href="${pageContext.request.contextPath}/posts">Посты</a></li>
        <li><a href="${pageContext.request.contextPath}/comments">Комментарии</a></li>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li><span class="user-info">Привет, ${sessionScope.user.username}!</span></li>
                <li><a href="${pageContext.request.contextPath}/logout">Выйти</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
                <li><a href="${pageContext.request.contextPath}/register">Регистрация</a></li>
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

    <!-- Сообщения об успехе/ошибке -->
    <c:if test="${not empty param.success}">
        <c:choose>
            <c:when test="${param.success == 'created'}">
                <div class="success-message">
                    ✅ Пост успешно создан!
                </div>
            </c:when>
        </c:choose>
    </c:if>

    <c:if test="${not empty param.error}">
        <c:choose>
            <c:when test="${param.error == 'deleted'}">
                <div class="success-message">
                    ✅ Пост успешно удален!
                </div>
            </c:when>
        </c:choose>
    </c:if>

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
                                <div>
                                    <fmt:formatDate value="${post.createdDate}" pattern="dd.MM.yyyy HH:mm"/>
                                </div>
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
