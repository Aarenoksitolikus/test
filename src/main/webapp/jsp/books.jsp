<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Каталог книг</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h2>Каталог книг</h2>

    <!-- Кнопка добавления книги для администратора -->
    <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'ADMIN'}">
        <p><a href="/books/create">Добавить книгу</a></p>
    </c:if>

    <!-- Список всех книг -->
    <c:forEach var="book" items="${books}">
        <div style="border: 1px solid #ccc; padding: 10px; margin: 10px 0;">
            <h3>${book.title}</h3>
            <p><strong>Автор:</strong> ${book.author}</p>
            <p><strong>Доступна:</strong> ${book.available ? 'Да' : 'Нет'}</p>

            <!-- Кнопка "Взять" для пользователей -->
            <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'USER' && book.available}">
                <form action="/borrow" method="post" style="display: inline;">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <button type="submit">Взять книгу</button>
                </form>
            </c:if>

            <!-- Кнопки управления для администратора -->
            <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'ADMIN'}">
                <a href="/books/edit?id=${book.id}">Редактировать</a>
                <form action="/books/delete" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="${book.id}">
                    <button type="submit" onclick="return confirm('Удалить книгу?')">Удалить</button>
                </form>
            </c:if>
        </div>
    </c:forEach>
</main>
<jsp:include page="/jsp/partial/footer.jsp" />
</body>
</html>