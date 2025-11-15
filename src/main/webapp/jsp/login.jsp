<%-- login.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход - Блог-платформа</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <style>
        .login-container {
            max-width: 400px;
            margin: 2rem auto;
            padding: 2rem;
            background: #fffef7;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            border: 1px solid #d4c8b5;
        }

        body.dark-theme .login-container {
            background: #2a2218;
            border-color: #5a4a32;
        }

        .login-title {
            text-align: center;
            color: #5c4b3c;
            margin-bottom: 2rem;
            font-family: "Helvetica Neue", Arial, sans-serif;
        }

        body.dark-theme .login-title {
            color: #e8d8c0;
        }

        .login-form {
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
        }

        body.dark-theme .form-input {
            background: #3a2c20;
            border-color: #5a4a32;
            color: #e8d8c0;
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
            margin-top: 1rem;
        }

        .submit-btn:hover {
            transform: translateY(-2px);
        }

        .register-link {
            text-align: center;
            margin-top: 1.5rem;
            color: #666;
        }

        body.dark-theme .register-link {
            color: #b8a890;
        }

        .register-link a {
            color: #8b7355;
            text-decoration: none;
            font-weight: 600;
        }

        .register-link a:hover {
            text-decoration: underline;
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
    </style>
</head>
<body>
<!-- Header (такой же как в других страницах) -->
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
                <a href="${pageContext.request.contextPath}/login">Войти</a>
                <a href="${pageContext.request.contextPath}/register">Регистрация</a>
            </div>
            <button id="menuToggle" class="menu-toggle">☰</button>
        </nav>
    </div>
</header>

<div class="container main-content">
    <div class="login-container">
        <h1 class="login-title">Вход в систему</h1>

        <c:if test="${not empty param.error}">
            <c:choose>
                <c:when test="${param.error == 'empty'}">
                    <div class="error-message">
                        ❌ Заполните все поля
                    </div>
                </c:when>
                <c:when test="${param.error == 'invalid'}">
                    <div class="error-message">
                        ❌ Неверное имя пользователя или пароль
                    </div>
                </c:when>
            </c:choose>
        </c:if>

        <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username" class="form-label">Имя пользователя</label>
                <input type="text"
                       id="username"
                       name="username"
                       class="form-input"
                       placeholder="Введите имя пользователя"
                       required>
            </div>

            <div class="form-group">
                <label for="password" class="form-label">Пароль</label>
                <input type="password"
                       id="password"
                       name="password"
                       class="form-input"
                       placeholder="Введите пароль"
                       required>
            </div>

            <button type="submit" class="submit-btn">
                Войти
            </button>
        </form>

        <div class="register-link">
            Нет аккаунта? <a href="${pageContext.request.contextPath}/register">Зарегистрируйтесь</a>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
