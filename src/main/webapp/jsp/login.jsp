<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">

<form method="post" action="/login">
    <div>
        <label>
            Имя пользователя:<br>
            <input type="username" name="username" maxlength="50" pattern="^[a-zA-Z0-9_]{1,50}$" required />
        </label>
    </div>
    <div>
        <label>
            Пароль:<br>
            <input type="password" name="password" id="password" oninput="check();" minlength="8" maxlength="255" required />
        </label>
    </div>
    <div>
        <input type="submit" id="submit" value="Войти">
    </div>
</form>
<p class="error">
    <c:choose>
    <c:when test="${param.error == null}"></c:when>
    <c:otherwise>Не удалось войти.</c:otherwise>
    </c:choose>
</p>

</main>
<jsp:include page="/jsp/partial/footer.jsp" />

<script src="/js/script.js"></script>
</body>
</html>