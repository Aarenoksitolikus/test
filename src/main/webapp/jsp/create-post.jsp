<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создать пост - Test</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="container">
    <h1><i class="fas fa-plus"></i> Создать новый пост</h1>

    <% if (request.getParameter("error") != null) { %>
    <div class="error-message">
        <i class="fas fa-exclamation-circle"></i>
        <% if ("missing".equals(request.getParameter("error"))) { %>
        Заполните все поля
        <% } else { %>
        Ошибка при создании поста
        <% } %>
    </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/create" method="post" class="post-form">
        <div class="form-group">
            <label for="title"><i class="fas fa-heading"></i> Заголовок</label>
            <input type="text" id="title" name="title" placeholder="Введите заголовок поста" required>
        </div>
        <div class="form-group">
            <label for="content"><i class="fas fa-file-text"></i> Содержание</label>
            <textarea id="content" name="content" placeholder="Введите содержание поста" rows="6" required></textarea>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-check"></i> Опубликовать
            </button>
            <a href="${pageContext.request.contextPath}/posts" class="btn btn-secondary">
                <i class="fas fa-times"></i> Отмена
            </a>
        </div>
    </form>
</div>
<jsp:include page="/jsp/partial/footer.jsp"/>
</body>
</html>