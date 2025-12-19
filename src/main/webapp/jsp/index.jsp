<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Главная — Блог</title>
</head>
<body>
<h1>Список постов</h1>

<c:if test="${empty posts}">
    <p>Пока нет ни одного поста.</p>
</c:if>

<c:forEach var="post" items="${posts}">
    <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
        <h2>${post.title}</h2>
        <p>${post.content}</p>
        <small>Автор ID: ${post.authorId}</small><br>
        <small>Дата: ${post.createdDate}</small><br><br>

        <a href="post?id=${post.id}">Читать подробнее →</a>
    </div>
</c:forEach>

</body>
</html>
