<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Мой профиль</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Библиотека</a>
            <div class="navbar-nav">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Главная</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/auth/logout">Выйти</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <h1>Мой профиль</h1>
        <p><strong>Имя пользователя:</strong> ${sessionScope.user.username}</p>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success">
                <c:choose>
                    <c:when test="${param.success == 'book_returned'}">Книга успешно возвращена!</c:when>
                </c:choose>
            </div>
        </c:if>

        <h2>Мои книги</h2>
        <c:choose>
            <c:when test="${not empty borrowedBooks}">
                <div class="row">
                    <c:forEach var="record" items="${borrowedBooks}">
                        <div class="col-md-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${record.book.title}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${record.book.author}</h6>
                                    <p class="card-text">
                                        <small>Взята: ${record.borrowDate}</small>
                                    </p>
                                    <form action="${pageContext.request.contextPath}/borrow/return" method="post">
                                        <input type="hidden" name="recordId" value="${record.id}">
                                        <button type="submit" class="btn btn-warning btn-sm">Вернуть книгу</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>У вас нет взятых книг</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>