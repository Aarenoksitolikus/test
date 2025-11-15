<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Библиотека</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Библиотека</a>
            <div class="navbar-nav">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/profile">Профиль</a>
                        <c:if test="${sessionScope.user.role == 'ADMIN'}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin">Админ-панель</a>
                        </c:if>
                        <a class="nav-link" href="${pageContext.request.contextPath}/auth/logout">Выйти</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="${pageContext.request.contextPath}/auth/login">Войти</a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/auth/register">Регистрация</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <h1>Добро пожаловать в библиотеку</h1>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success">
                <c:choose>
                    <c:when test="${param.success == 'book_borrowed'}">Книга успешно взята!</c:when>
                </c:choose>
            </div>
        </c:if>

        <c:if test="${not empty param.error}">
            <div class="alert alert-danger">
                <c:choose>
                    <c:when test="${param.error == 'book_not_available'}">Книга недоступна для взятия</c:when>
                </c:choose>
            </div>
        </c:if>

        <h2>Список книг</h2>
        <div class="row">
            <c:forEach var="book" items="${books}">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${book.title}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${book.author}</h6>
                            <p class="card-text">
                                <span class="badge ${book.available ? 'bg-success' : 'bg-danger'}">
                                    ${book.available ? 'Доступна' : 'Выдана'}
                                </span>
                            </p>
                            <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'USER' && book.available}">
                                <form action="${pageContext.request.contextPath}/borrow/take" method="post">
                                    <input type="hidden" name="bookId" value="${book.id}">
                                    <button type="submit" class="btn btn-primary btn-sm">Взять книгу</button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>

</main>
<jsp:include page="/jsp/partial/footer.jsp" />

<script src="/js/script.js"></script>
</body>
</html>