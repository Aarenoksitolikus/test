<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav>
        <ul class="nav-links">
            <li><a href="/">Главная</a></li>
            <li><a href="/menu">Меню</a></li>
            <c:if test="${empty user}">
                <li><a href="/login">Войти</a></li>
                <li><a href="/register">Регистрация</a></li>
            </c:if>
            <c:if test="${not empty user}">
                <li><span>Привет, ${user.username}!</span></li>
                <c:if test="${user.role == 'ADMIN'}">
                    <li><a href="/admin" style="background: #e74c3c;">Админ-панель</a></li>
                </c:if>
                <li><a href="/logout">Выйти</a></li>
            </c:if>
        </ul>
    </nav>
</header>