<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Редактировать пост</title>
    <style>
        body { font-family: sans-serif; margin: 40px; }
        textarea { width: 100%; height: 300px; }
        input[type=text] { width: 100%; padding: 8px; }
        button { padding: 10px 20px; }
    </style>
</head>
<body>
<h2>Редактирование</h2>

<form method="post" action="${pageContext.request.contextPath}/edit">
    <input type="hidden" name="id" value="${post.id}">

    <label>Заголовок:</label><br>
    <input type="text" name="title" value="${post.title}"><br><br>

    <label>Текст:</label><br>
    <textarea name="content">${post.content}</textarea><br><br>

    <button type="submit">Сохранить</button>
</form>

</body>
</html>