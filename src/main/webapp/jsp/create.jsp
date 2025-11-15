<%-- create.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создать пост - Блог-платформа</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <style>
        .create-container {
            max-width: 800px;
            margin: 2rem auto;
            padding: 2rem;
            background: #fffef7;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            border: 1px solid #d4c8b5;
        }

        body.dark-theme .create-container {
            background: #2a2218;
            border-color: #5a4a32;
        }

        .create-title {
            text-align: center;
            color: #5c4b3c;
            margin-bottom: 2rem;
            font-family: "Helvetica Neue", Arial, sans-serif;
        }

        body.dark-theme .create-title {
            color: #e8d8c0;
        }

        .create-form {
            display: flex;
            flex-direction: column;
            gap: 1.5rem;
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

        .form-input {
            padding: 12px 15px;
            border: 1px solid #d4c8b5;
            border-radius: 5px;
            font-size: 1rem;
            font-family: "Georgia", serif;
            background: #f8f4e9;
            transition: border-color 0.3s;
        }

        body.dark-theme .form-input {
            background: #3a2c20;
            border-color: #5a4a32;
            color: #e8d8c0;
        }

        .form-input:focus {
            outline: none;
            border-color: #8b7355;
            box-shadow: 0 0 0 2px rgba(139, 115, 85, 0.2);
        }

        .form-textarea {
            padding: 12px 15px;
            border: 1px solid #d4c8b5;
            border-radius: 5px;
            font-size: 1rem;
            font-family: "Georgia", serif;
            background: #f8f4e9;
            min-height: 300px;
            resize: vertical;
            transition: border-color 0.3s;
        }

        body.dark-theme .form-textarea {
            background: #3a2c20;
            border-color: #5a4a32;
            color: #e8d8c0;
        }

        .form-textarea:focus {
            outline: none;
            border-color: #8b7355;
            box-shadow: 0 0 0 2px rgba(139, 115, 85, 0.2);
        }

        .char-count {
            font-size: 0.85rem;
            color: #666;
            text-align: right;
        }

        body.dark-theme .char-count {
            color: #b8a890;
        }

        .char-count.warning {
            color: #dc3545;
        }

        .form-actions {
            display: flex;
            gap: 1rem;
            justify-content: flex-end;
            margin-top: 1rem;
        }

        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 25px;
            font-size: 1rem;
            font-weight: bold;
            cursor: pointer;
            transition: transform 0.3s;
            font-family: "Helvetica Neue", Arial, sans-serif;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }

        .btn-submit {
            background: linear-gradient(135deg, #8b7355 0%, #6d5c46 100%);
            color: white;
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            background: linear-gradient(135deg, #9c8466 0%, #7d6c56 100%);
        }

        .btn-cancel {
            background: #6c757d;
            color: white;
        }

        .btn-cancel:hover {
            transform: translateY(-2px);
            background: #5a6268;
        }

        .error-message {
            background: #f8d7da;
            color: #721c24;
            padding: 12px 15px;
            border-radius: 5px;
            border: 1px solid #f5c6cb;
            margin-bottom: 1rem;
            text-align: center;
        }

        body.dark-theme .error-message {
            background: #2d1b1f;
            color: #f1aeb5;
            border-color: #842029;
        }

        .success-message {
            background: #d1edff;
            color: #0c5460;
            padding: 12px 15px;
            border-radius: 5px;
            border: 1px solid #bee5eb;
            margin-bottom: 1rem;
            text-align: center;
        }

        body.dark-theme .success-message {
            background: #1b2d32;
            color: #6ea8fe;
            border-color: #0c5460;
        }

        @media (max-width: 768px) {
            .create-container {
                margin: 1rem;
                padding: 1.5rem;
            }

            .form-actions {
                flex-direction: column;
            }

            .btn {
                width: 100%;
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
    <div class="create-container">
        <h1 class="create-title">Создать новый пост</h1>

        <!-- Сообщения об ошибках -->
        <c:if test="${not empty param.error}">
            <c:choose>
                <c:when test="${param.error == 'empty'}">
                    <div class="error-message">
                        ❌ Заполните все поля
                    </div>
                </c:when>
                <c:when test="${param.error == 'title_too_long'}">
                    <div class="error-message">
                        ❌ Заголовок не должен превышать 255 символов
                    </div>
                </c:when>
                <c:when test="${param.error == 'server'}">
                    <div class="error-message">
                        ❌ Ошибка сервера. Попробуйте позже
                    </div>
                </c:when>
            </c:choose>
        </c:if>

        <form class="create-form" action="${pageContext.request.contextPath}/posts/create" method="post">
            <div class="form-group">
                <label for="title" class="form-label">Заголовок</label>
                <input type="text"
                       id="title"
                       name="title"
                       class="form-input"
                       placeholder="Введите заголовок поста"
                       maxlength="255"
                       required>
                <div class="char-count" id="titleCount">0/255</div>
            </div>

            <div class="form-group">
                <label for="content" class="form-label">Содержание</label>
                <textarea id="content"
                          name="content"
                          class="form-textarea"
                          placeholder="Напишите содержание вашего поста..."
                          required></textarea>
                <div class="char-count" id="contentCount">0 символов</div>
            </div>

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/posts" class="btn btn-cancel">
                    Отмена
                </a>
                <button type="submit" class="btn btn-submit">
                    Опубликовать
                </button>
            </div>
        </form>
    </div>
</div>

<footer>
    <div class="container">
        <p>&copy; 2024 Блог-платформа. Все права защищены.</p>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const titleInput = document.getElementById('title');
        const contentInput = document.getElementById('content');
        const titleCount = document.getElementById('titleCount');
        const contentCount = document.getElementById('contentCount');

        // Счетчик символов для заголовка
        titleInput.addEventListener('input', function() {
            const count = this.value.length;
            titleCount.textContent = `${count}/255`;

            if (count > 240) {
                titleCount.classList.add('warning');
            } else {
                titleCount.classList.remove('warning');
            }
        });

        // Счетчик символов для содержания
        contentInput.addEventListener('input', function() {
            const count = this.value.length;
            contentCount.textContent = `${count} символов`;
        });

        // Валидация формы
        document.querySelector('.create-form').addEventListener('submit', function(e) {
            const title = titleInput.value.trim();
            const content = contentInput.value.trim();

            if (!title || !content) {
                e.preventDefault();
                alert('Заполните все поля');
                return;
            }

            if (title.length > 255) {
                e.preventDefault();
                alert('Заголовок не должен превышать 255 символов');
                return;
            }
        });
    });
</script>
</body>
</html>
