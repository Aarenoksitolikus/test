<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Меню - Кафе</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/jsp/partial/header.jsp" />

    <main class="container">
        <h1 style="text-align: center; margin-bottom: 2rem; color: #2c3e50;">Меню кафе</h1>

        <div style="text-align: center; margin: 2rem 0;">
            <a href="/menu?category=all"
               style="margin: 0 0.5rem; padding: 0.5rem 1rem; background: #3498db; color: white; text-decoration: none; border-radius: 4px;">
                Все
            </a>
            <a href="/menu?category=coffee"
               style="margin: 0 0.5rem; padding: 0.5rem 1rem; background: #3498db; color: white; text-decoration: none; border-radius: 4px;">
                Кофе
            </a>
            <a href="/menu?category=tea"
               style="margin: 0 0.5rem; padding: 0.5rem 1rem; background: #3498db; color: white; text-decoration: none; border-radius: 4px;">
                Чай
            </a>
            <a href="/menu?category=dessert"
               style="margin: 0 0.5rem; padding: 0.5rem 1rem; background: #3498db; color: white; text-decoration: none; border-radius: 4px;">
                Десерты
            </a>
        </div>

        <c:forEach var="product" items="${products}">
            <div style="background: #f9f9f9; border: 1px solid #ddd; padding: 1.5rem; margin: 1rem 0; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);">
                <div style="display: flex; justify-content: space-between; align-items: center;">
                    <div>
                        <h3 style="color: #2c3e50; margin-bottom: 0.5rem;">${product.name}</h3>
                        <p style="color: #7f8c8d; font-style: italic;">${product.category}</p>
                    </div>
                    <div style="font-size: 1.5rem; color: #27ae60; font-weight: bold;">
                        ${product.price} руб.
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty products}">
            <div style="text-align: center; color: #7f8c8d; margin: 3rem 0;">
                <p>В этой категории пока нет товаров</p>
                <a href="/menu?category=all"
                   style="display: inline-block; margin-top: 1rem; padding: 0.5rem 1rem; background: #3498db; color: white; text-decoration: none; border-radius: 4px;">
                    Посмотреть все товары
                </a>
            </div>
        </c:if>
    </main>

    <jsp:include page="/jsp/partial/footer.jsp" />

    <script src="/js/script.js"></script>
</body>
</html>