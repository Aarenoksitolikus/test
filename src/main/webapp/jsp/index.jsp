<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная - Кафе</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/jsp/partial/header.jsp" />

    <main class="container">
        <div style="text-align: center; padding: 4rem 0;">
            <h1 style="font-size: 3rem; color: #2c3e50; margin-bottom: 1rem;">☕ Добро пожаловать в наше кафе!</h1>
            <p style="font-size: 1.25rem; color: #7f8c8d; margin-bottom: 3rem;">
                Система управления заказами
            </p>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 2rem; margin: 3rem 0;">
                <div class="product-card">
                    <h3>👥 Для гостей</h3>
                    <p>• Просмотр меню с фильтрацией</p>
                    <p>• Ознакомление с ассортиментом</p>
                    <a href="/menu" class="btn" style="margin-top: 1rem; display: inline-block;">Смотреть меню</a>
                </div>

                <div class="product-card">
                    <h3>👤 Для пользователей</h3>
                    <p>• Создание заказов</p>
                    <p>• Просмотр истории</p>
                    <p>• Отслеживание статуса</p>
                    <c:if test="${empty user}">
                        <a href="/register" class="btn" style="margin-top: 1rem; display: inline-block;">Зарегистрироваться</a>
                    </c:if>
                </div>

                <div class="product-card">
                    <h3>⚙️ Для администраторов</h3>
                    <p>• Управление меню</p>
                    <p>• Управление заказами</p>
                    <p>• Просмотр статистики</p>
                    <c:if test="${not empty user && user.role == 'ADMIN'}">
                        <a href="/admin" class="btn btn-success" style="margin-top: 1rem; display: inline-block;">Панель управления</a>
                    </c:if>
                </div>
            </div>

            <div style="margin-top: 3rem;">
                <c:if test="${empty user}">
                    <a href="/register" class="btn btn-success" style="font-size: 1.25rem; padding: 1rem 2rem; margin-right: 1rem;">
                        Начать работу
                    </a>
                </c:if>
                <a href="/menu" class="btn" style="font-size: 1.25rem; padding: 1rem 2rem;">
                    Посмотреть меню →
                </a>
            </div>
        </div>
    </main>

    <jsp:include page="/jsp/partial/footer.jsp" />

    <script src="/js/script.js"></script>
</body>
</html>