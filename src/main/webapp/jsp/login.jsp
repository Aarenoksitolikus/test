<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Логин</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</head>
<body class="username-body">
<div class="username-div">
    <h1 class="username-h1">Dungeons and Gags</h1>
    <h2>Впишите ваши данные для входа</h2>

    <form method="post" action="${pageContext.request.contextPath}/login">
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
                Войти в аккаунт
            </button>
        </div>
    </form>
    <p>Не зарегистрированы?
        <a href="${pageContext.request.contextPath}/register" style="text-decoration: none;">
            Создать Аккаунт
        </a>
    </p>
</div>
</body>

<jsp:include page="/jsp/partial/footer.jsp" />
</html>