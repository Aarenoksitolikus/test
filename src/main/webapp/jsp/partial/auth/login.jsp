<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход - Кафе</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/jsp/partial/header.jsp" />

    <main class="container">
        <div style="max-width: 400px; margin: 2rem auto; background: #f9f9f9; padding: 2rem; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);">
            <h2 style="text-align: center; margin-bottom: 2rem; color: #2c3e50;">Вход в систему</h2>

            <c:if test="${not empty error}">
                <div style="background: #e74c3c; color: white; padding: 1rem; border-radius: 4px; margin-bottom: 1rem;">
                    ${error}
                </div>
            </c:if>

            <c:if test="${not empty param.success}">
                <div style="background: #27ae60; color: white; padding: 1rem; border-radius: 4px; margin-bottom: 1rem;">
                    ${param.success}
                </div>
            </c:if>

            <form method="post" action="/login">
                <div style="margin-bottom: 1rem;">
                    <label style="display: block; margin-bottom: 0.5rem; font-weight: bold;">Имя пользователя:</label>
                    <input type="text" name="username" required
                           style="width: 100%; padding: 0.75rem; border: 1px solid #ddd; border-radius: 4px;">
                </div>

                <div style="margin-bottom: 1.5rem;">
                    <label style="display: block; margin-bottom: 0.5rem; font-weight: bold;">Пароль:</label>
                    <input type="password" name="password" required
                           style="width: 100%; padding: 0.75rem; border: 1px solid #ddd; border-radius: 4px;">
                </div>

                <button type="submit"
                        style="width: 100%; padding: 0.75rem; background: #3498db; color: white; border: none; border-radius: 4px; cursor: pointer;">
                    Войти
                </button>
            </form>

            <p style="text-align: center; margin-top: 1.5rem;">
                Нет аккаунта? <a href="/register" style="color: #3498db;">Зарегистрируйтесь</a>
            </p>
        </div>
    </main>

    <jsp:include page="/jsp/partial/footer.jsp" />

    <script src="/js/script.js"></script>
</body>
</html>