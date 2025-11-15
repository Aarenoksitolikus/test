<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <nav>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/index">Главная</a></li>
            <li><a href="${pageContext.request.contextPath}/posts">Посты</a></li>

            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/login">Вход</a></li>
                    <li><a href="${pageContext.request.contextPath}/registration">Регистрация</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/posts/create">Создать пост</a></li>
                    <li><span>Привет, ${sessionScope.user.username} (${sessionScope.user.role})</span></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Выход</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>