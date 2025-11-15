<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="header">
    <div class="logo">
        <h1>Кафе "Орис"</h1>
    </div>
    <nav class="nav">
        <a href="${pageContext.request.contextPath}/">Главная</a>
        <a href="${pageContext.request.contextPath}/menu">Меню</a>

        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/login">Войти</a>
                <a href="${pageContext.request.contextPath}/register">Регистрация</a>
            </c:when>
            <c:otherwise>
                <span>Привет, ${sessionScope.user.username}!</span>
                <a href="${pageContext.request.contextPath}/cart"> Корзина</a>
                <a href="${pageContext.request.contextPath}/orders">Мои заказы</a>
                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/admin">Админ-панель</a>
                </c:if>
                <a href="${pageContext.request.contextPath}/logout">Выйти</a>
            </c:otherwise>
        </c:choose>
    </nav>
</div>