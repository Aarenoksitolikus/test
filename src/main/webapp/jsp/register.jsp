<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>

<html>

<head>
    <title>Регистрация</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</head>

<body class="username-body">
<div class="username-div">
    <h1 class="username-h1">Dungeons and Gags</h1>
    <h2>Форма регистрации</h2>

    <form method="post" action="${pageContext.request.contextPath}/register">
        <label class="username-label" for="first">
            username:
        </label>
        <input type="text" id="username" name="username"
               placeholder="Введите ваш username" required class="username-input">
        <label class="username-label" for="password">
            Password:
        </label>
        <input type="password" id="password" name="password"
               placeholder="Введите ваш пароль" required class="username-input">
        <div class="wrap">
            <button type="submit" class="username-button">
                Зарегистрироваться
            </button>
        </div>
    </form>
    <p>Уже зарегистрированы?
        <a href="${pageContext.request.contextPath}/login" style="text-decoration: none;">
            Войти в аккаунт
        </a>
    </p>
</div>
</body>

<jsp:include page="/jsp/partial/footer.jsp"/>
</html>