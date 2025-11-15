<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Мои книги</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h2>Мои книги</h2>

    <!-- Список взятых книг пользователя -->
    <c:forEach var="record" items="${borrowRecords}">
        <div style="border: 1px solid #ccc; padding: 10px; margin: 10px 0;">
            <h3>${record.bookTitle}</h3>
            <p><strong>Дата взятия:</strong> ${record.borrowDate}</p>

            <!-- Кнопка возврата для книг которые еще не возвращены -->
            <c:if test="${record.returnDate == null}">
                <form action="/return" method="post">
                    <input type="hidden" name="recordId" value="${record.id}">
                    <button type="submit">Вернуть книгу</button>
                </form>
            </c:if>
        </div>
    </c:forEach>

    <!-- Сообщение если книг нет -->
    <c:if test="${empty borrowRecords}">
        <p>У вас нет взятых книг</p>
    </c:if>
</main>
<jsp:include page="/jsp/partial/footer.jsp" />
</body>
</html>