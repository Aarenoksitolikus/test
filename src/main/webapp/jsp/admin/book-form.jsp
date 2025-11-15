<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${empty book ? 'Add Book' : 'Edit Book'}</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h2>${empty book ? 'Add Book' : 'Edit Book'}</h2>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <form action="${empty book ? '/books/create' : '/books/update'}" method="post">
        <c:if test="${not empty book}">
            <input type="hidden" name="id" value="${book.id}">
        </c:if>

        <div>
            <input type="text" name="title" placeholder="Title" value="${book.title}" required>
        </div>

        <div>
            <input type="text" name="author" placeholder="Author" value="${book.author}" required>
        </div>

        <button type="submit">Save</button>
        <a href="/books">Cancel</a>
    </form>
</main>
<jsp:include page="/jsp/partial/footer.jsp" />
</body>
</html>