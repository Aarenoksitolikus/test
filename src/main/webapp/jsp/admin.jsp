<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Админ Панель</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<form action="${pageContext.request.contextPath}/admin" method="post">
    <label>
        Создание книги
    </label>

    <input type="text" name="title" placeholder="Введите название книги" required>
    <input type="text" name="author" placeholder="Введите автора книги" required>

    <button type="submit" name="action" value="createBook">Добавить книгу</button>
    <button type="submit" name="action" value="deleteBook">Удалить книгу</button>
</form>

</body>
</html>
