<%-- register.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация - Блог-платформа</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <style>
        .register-container {
            max-width: 500px;
            margin: 2rem auto;
            padding: 2rem;
            background: #fffef7;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            border: 1px solid #d4c8b5;
        }

        body.dark-theme .register-container {
            background: #2a2218;
            border-color: #5a4a32;
        }

        .register-title {
            text-align: center;
            color: #5c4b3c;
            margin-bottom: 2rem;
            font-family: "Helvetica Neue", Arial, sans-serif;
            font-size: 2rem;
        }

        body.dark-theme .register-title {
            color: #e8d8c0;
        }

        .register-form {
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
        }

        .password-requirements {
            font-size: 0.85rem;
            color: #666;
            margin-top: 0.25rem;
        }

        body.dark-theme .password-requirements {
            color: #b8a890;
        }

        .requirement {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            margin-bottom: 0.25rem;
        }

        .requirement.valid {
            color: #28a745;
        }

        .requirement.invalid {
            color: #dc3545;
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

        .submit-btn:hover:not(:disabled) {
            transform: translateY(-2px);
            background: linear-gradient(135deg, #9c8466 0%, #7d6c56 100%);
        }

        .submit-btn:disabled {
            background: #cccccc;
            cursor: not-allowed;
            transform: none;
        }

        .login-link {
            text-align: center;
            margin-top: 1.5rem;
            color: #666;
        }

        body.dark-theme .login-link {
            color: #b8a890;
        }

        .login-link a {
            color: #8b7355;
            text-decoration: none;
            font-weight: 600;
        }

        .login-link a:hover {
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
            font-weight: 500;
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
            font-weight: 500;
        }

        body.dark-theme .success-message {
            background: #1b2d32;
            color: #6ea8fe;
            border-color: #0c5460;
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
    <div class="register-container">
        <h1 class="register-title">Регистрация</h1>

        <c:if test="${not empty param.error}">
            <c:choose>
                <c:when test="${param.error == 'invalid'}">
                    <div class="error-message">
                        ❌ Неверные данные. Имя пользователя должно быть длиннее 6 символов, пароль - не менее 6 символов.
                    </div>
                </c:when>
                <c:when test="${param.error == 'exists'}">
                    <div class="error-message">
                        ❌ Пользователь с таким именем уже существует.
                    </div>
                </c:when>
            </c:choose>
        </c:if>

        <c:if test="${not empty param.success}">
            <div class="success-message">
                ✅ Регистрация прошла успешно! Теперь вы можете войти в систему.
            </div>
        </c:if>

        <form class="register-form" action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="username" class="form-label">Имя пользователя</label>
                <input type="text"
                       id="username"
                       name="username"
                       class="form-input"
                       placeholder="Введите имя пользователя"
                       required
                       minlength="7">
                <div class="password-requirements">
                    <div id="usernameRequirement" class="requirement invalid">
                        ⚫ Имя пользователя должно быть длиннее 6 символов
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="form-label">Пароль</label>
                <input type="password"
                       id="password"
                       name="password"
                       class="form-input"
                       placeholder="Введите пароль"
                       required
                       minlength="6">
                <div class="password-requirements">
                    <div id="passwordRequirement" class="requirement invalid">
                        ⚫ Пароль должен содержать не менее 6 символов
                    </div>
                </div>
            </div>

            <button type="submit" class="submit-btn" id="submitBtn">
                Зарегистрироваться
            </button>
        </form>

        <div class="login-link">
            Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войдите</a>
        </div>
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
        const usernameInput = document.getElementById('username');
        const passwordInput = document.getElementById('password');
        const usernameRequirement = document.getElementById('usernameRequirement');
        const passwordRequirement = document.getElementById('passwordRequirement');
        const submitBtn = document.getElementById('submitBtn');

        function validateForm() {
            const usernameValid = usernameInput.value.length > 6;
            const passwordValid = passwordInput.value.length >= 6;

            // Обновляем отображение требований
            if (usernameInput.value.length > 0) {
                if (usernameValid) {
                    usernameRequirement.classList.remove('invalid');
                    usernameRequirement.classList.add('valid');
                    usernameRequirement.innerHTML = '✅ Имя пользователя соответствует требованиям';
                } else {
                    usernameRequirement.classList.remove('valid');
                    usernameRequirement.classList.add('invalid');
                    usernameRequirement.innerHTML = '❌ Имя пользователя должно быть длиннее 6 символов';
                }
            }

            if (passwordInput.value.length > 0) {
                if (passwordValid) {
                    passwordRequirement.classList.remove('invalid');
                    passwordRequirement.classList.add('valid');
                    passwordRequirement.innerHTML = '✅ Пароль соответствует требованиям';
                } else {
                    passwordRequirement.classList.remove('valid');
                    passwordRequirement.classList.add('invalid');
                    passwordRequirement.innerHTML = '❌ Пароль должен содержать не менее 6 символов';
                }
            }
            submitBtn.disabled = !(usernameValid && passwordValid);
            return usernameValid && passwordValid;
        }
        usernameInput.addEventListener('input', validateForm);
        passwordInput.addEventListener('input', validateForm);
        document.querySelector('.register-form').addEventListener('submit', function(e) {
            if (!validateForm()) {
                e.preventDefault();
                alert('Пожалуйста, исправьте ошибки в форме');
            }
        });

        validateForm();
    });
</script>
</body>
</html>
