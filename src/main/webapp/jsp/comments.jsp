<%-- comments.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комментарии - Блог-платформа</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <style>
        .comments-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 2px solid #d4c8b5;
        }

        body.dark-theme .comments-header {
            border-bottom-color: #5a4a32;
        }

        .comments-title {
            color: #5c4b3c;
            font-size: 2.5rem;
            margin: 0;
        }

        body.dark-theme .comments-title {
            color: #e8d8c0;
        }

        .comments-container {
            display: flex;
            flex-direction: column;
            gap: 1.5rem;
            margin: 2rem 0;
        }

        .comment-card {
            background: #fffef7;
            padding: 1.5rem;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
            border: 1px solid #d4c8b5;
            transition: transform 0.3s;
        }

        body.dark-theme .comment-card {
            background: #2a2218;
            border-color: #5a4a32;
        }

        .comment-card:hover {
            transform: translateY(-2px);
        }

        .comment-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 1rem;
        }

        .comment-author {
            font-weight: 600;
            color: #8b7355;
            font-family: "Helvetica Neue", Arial, sans-serif;
        }

        body.dark-theme .comment-author {
            color: #c8b8a0;
        }

        .comment-date {
            color: #666;
            font-size: 0.9rem;
        }

        body.dark-theme .comment-date {
            color: #b8a890;
        }

        .comment-content {
            color: #555;
            line-height: 1.6;
            margin-bottom: 1rem;
            white-space: pre-wrap;
        }

        body.dark-theme .comment-content {
            color: #b8a890;
        }

        .post-reference {
            background: #f8f4e9;
            padding: 1rem;
            border-radius: 5px;
            margin-bottom: 1rem;
            border-left: 4px solid #8b7355;
        }

        body.dark-theme .post-reference {
            background: #3a2c20;
            border-left-color: #8b7355;
        }

        .post-reference a {
            color: #5c4b3c;
            text-decoration: none;
            font-weight: 600;
            font-family: "Helvetica Neue", Arial, sans-serif;
        }

        body.dark-theme .post-reference a {
            color: #e8d8c0;
        }

        .post-reference a:hover {
            color: #8b7355;
            text-decoration: underline;
        }

        .comment-actions {
            display: flex;
            justify-content: flex-end;
            gap: 0.5rem;
            margin-top: 1rem;
            padding-top: 1rem;
            border-top: 1px solid #e0d6c5;
        }

        body.dark-theme .comment-actions {
            border-top-color: #5a4a32;
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
            display: inline-block;
            text-align: center;
        }

        .btn-delete {
            background: #dc3545;
            color: white;
        }

        .btn-delete:hover {
            background: #c82333;
        }

        .no-comments {
            text-align: center;
            padding: 3rem;
            color: #666;
            font-style: italic;
            background: #fffef7;
            border-radius: 10px;
            border: 1px solid #d4c8b5;
        }

        body.dark-theme .no-comments {
            background: #2a2218;
            border-color: #5a4a32;
            color: #b8a890;
        }

        .comment-form-container {
            background: #fffef7;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
            border: 1px solid #d4c8b5;
            margin-bottom: 2rem;
        }

        body.dark-theme .comment-form-container {
            background: #2a2218;
            border-color: #5a4a32;
        }

        .comment-form-title {
            color: #5c4b3c;
            margin-bottom: 1rem;
            font-family: "Helvetica Neue", Arial, sans-serif;
        }

        body.dark-theme .comment-form-title {
            color: #e8d8c0;
        }

        .comment-form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .form-label {
            font-weight: 600;
            color: #5c4b3c;
            font-family: "Helvetica Neue", Arial, sans-serif;
        }

        body.dark-theme .form-label {
            color: #c8b8a0;
        }

        .form-textarea {
            padding: 12px 15px;
            border: 1px solid #d4c8b5;
            border-radius: 5px;
            font-size: 1rem;
            font-family: "Georgia", serif;
            background: #f8f4e9;
            min-height: 100px;
            resize: vertical;
        }

        body.dark-theme .form-textarea {
            background: #3a2c20;
            border-color: #5a4a32;
            color: #e8d8c0;
        }

        .form-textarea:focus {
            outline: none;
            border-color: #8b7355;
        }

        .submit-btn {
            background: linear-gradient(135deg, #8b7355 0%, #6d5c46 100%);
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 25px;
            font-size: 1.1rem;
            font-weight: bold;
            cursor: pointer;
            transition: transform 0.3s;
            font-family: "Helvetica Neue", Arial, sans-serif;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            align-self: flex-start;
        }

        .submit-btn:hover {
            transform: translateY(-2px);
            background: linear-gradient(135deg, #9c8466 0%, #7d6c56 100%);
        }

        .submit-btn:disabled {
            background: #cccccc;
            cursor: not-allowed;
            transform: none;
        }

        @media (max-width: 768px) {
            .comments-header {
                flex-direction: column;
                gap: 1rem;
                text-align: center;
            }

            .comment-header {
                flex-direction: column;
                gap: 0.5rem;
            }

            .comment-date {
                text-align: left;
            }

            .comment-actions {
                justify-content: flex-start;
            }

            .comment-form-container {
                padding: 1.5rem;
            }
        }

        @media (max-width: 480px) {
            .comment-form-container {
                padding: 1rem;
            }

            .comments-title {
                font-size: 2rem;
            }
        }
    </style>
</head>
<body>
<!-- Header with full navigation -->
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

<!-- Sidebar Navigation for Mobile -->
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

<!-- Overlay for mobile menu -->
<div class="overlay" id="overlay"></div>

<!-- Main Content -->
<div class="container main-content">
    <div class="comments-header">
        <h1 class="comments-title">Комментарии</h1>
    </div>

    <!-- Форма добавления комментария (если передан postId) -->
    <c:if test="${not empty param.postId && not empty sessionScope.user}">
        <div class="comment-form-container">
            <h3 class="comment-form-title">Добавить комментарий</h3>
            <form class="comment-form" action="${pageContext.request.contextPath}/comments/create" method="post">
                <input type="hidden" name="postId" value="${param.postId}">
                <div class="form-group">
                    <label for="content" class="form-label">Текст комментария</label>
                    <textarea id="content" name="content" class="form-textarea"
                              placeholder="Введите ваш комментарий..." required></textarea>
                </div>
                <button type="submit" class="submit-btn">Опубликовать комментарий</button>
            </form>
        </div>
    </c:if>

    <div class="comments-container">
        <c:choose>
            <c:when test="${not empty comments}">
                <c:forEach var="comment" items="${comments}">
                    <div class="comment-card">
                        <!-- Ссылка на пост -->
                        <div class="post-reference">
                            К посту:
                            <a href="${pageContext.request.contextPath}/posts/view?id=${comment.postId}">
                                    ${comment.postTitle}
                            </a>
                        </div>

                        <div class="comment-header">
                            <div class="comment-author">👤 ${comment.userUsername}</div>
                            <div class="comment-date">
                                <fmt:formatDate value="${comment.createdDate}" pattern="dd.MM.yyyy HH:mm"/>
                            </div>
                        </div>

                        <div class="comment-content">
                                ${comment.content}
                        </div>

                        <!-- Действия для модераторов -->
                        <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'MODERATOR'}">
                            <div class="comment-actions">
                                <a href="${pageContext.request.contextPath}/comments/delete?id=${comment.id}"
                                   class="btn btn-delete"
                                   onclick="return confirm('Вы уверены, что хотите удалить этот комментарий?')">
                                    Удалить
                                </a>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="no-comments">
                    <h3>Пока нет комментариев</h3>
                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <p><a href="${pageContext.request.contextPath}/login" style="color: #8b7355;">Войдите</a>, чтобы оставить комментарий</p>
                        </c:when>
                        <c:when test="${empty param.postId}">
                            <p>Перейдите к посту, чтобы оставить комментарий</p>
                        </c:when>
                        <c:otherwise>
                            <p>Будьте первым, кто оставит комментарий к этому посту!</p>
                        </c:otherwise>
                    </c:choose>
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
