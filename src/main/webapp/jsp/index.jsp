<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Главная - Кафе "У Ориса"</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="components/header.jsp"/>

    <div class="container">
        <div class="hero">
            <h2>Добро пожаловать в наше кафе!</h2>
            <p>Лучший кофе и десерты в городе</p>
            <a href="${pageContext.request.contextPath}/menu" class="btn">Смотреть меню</a>
        </div>

        <div class="filters">
            <strong>Категории:</strong>
            <a href="?category=COFFEE" class="${param.category == 'COFFEE' ? 'active' : ''}">☕ Кофе</a>
            <a href="?category=TEA" class="${param.category == 'TEA' ? 'active' : ''}">🍵 Чай</a>
            <a href="?category=DESSERT" class="${param.category == 'DESSERT' ? 'active' : ''}">🍰 Десерты</a>
            <a href="?category=FOOD" class="${param.category == 'FOOD' ? 'active' : ''}">🥪 Еда</a>
            <a href="${pageContext.request.contextPath}/" class="${empty param.category ? 'active' : ''}">Все</a>
        </div>

        <div class="products-grid">
            <c:forEach var="product" items="${products}">
                <div class="product-card">
                    <h3>${product.name}</h3>
                    <p class="price">${product.price} руб.</p>
                    <span class="category">${product.category}</span>

                    <c:if test="${not empty sessionScope.user}">
                        <form action="${pageContext.request.contextPath}/cart/add" method="post" style="margin-top: 10px;">
                            <input type="hidden" name="productId" value="${product.id}">
                            <div class="form-group">
                                <label>Количество:</label>
                                <input type="number" name="quantity" value="1" min="1" max="10" style="width: 60px;">
                            </div>
                            <button type="submit" class="btn">В корзину</button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>

        <c:if test="${empty products}">
            <div class="empty-state">
                <h3>Товары не найдены</h3>
                <p>Попробуйте выбрать другую категорию</p>
            </div>
        </c:if>
    </div>

    <jsp:include page="components/footer.jsp"/>
</body>
</html>