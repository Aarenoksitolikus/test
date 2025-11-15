<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>User Books</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h2>${user.username}'s Books</h2>

    <c:forEach var="record" items="${borrowRecords}">
        <div style="border: 1px solid #ccc; padding: 10px; margin: 10px 0;">
            <p><strong>${record.bookTitle}</strong></p>
            <p>Borrowed: ${record.borrowDate}</p>
            <c:if test="${record.returnDate != null}">
                <p>Returned: ${record.returnDate}</p>
            </c:if>
        </div>
    </c:forEach>

    <c:if test="${empty borrowRecords}">
        <p>No books borrowed</p>
    </c:if>

    <p><a href="/admin/users">Back</a></p>
</main>
<jsp:include page="/jsp/partial/footer.jsp" />
</body>
</html>