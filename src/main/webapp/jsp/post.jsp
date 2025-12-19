<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>${post.title}</title>
</head>
<body>

<h1>${post.title}</h1>

<p>
    ${post.content}
</p>

<p>
    <small>Автор ID: ${post.authorId}</small><br>
    <small>Дата создания: ${post.createdDate}</small>
</p>

<a href="/index">← Назад к списку</a>

<hr>

<h2>Комментарии</h2>

<p>Скоро тут появятся комментарии...</p>

</body>
</html>
