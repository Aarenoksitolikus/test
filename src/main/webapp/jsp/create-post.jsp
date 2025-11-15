<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Создать пост</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="partial/header.jsp"/>

    <main class="container">
        <div class="card" style="max-width: 800px; margin: 0 auto;">
            <h2>Создать новый пост</h2>

            <c:if test="${not empty error}">
                <div style="color: red; margin: 10px 0;">${error}</div>
            </c:if>

            <form action="posts/create" method="post">
                <div style="margin: 10px 0;">
                    <input type="text" name="title" placeholder="Заголовок поста" required
                           style="width: 100%; padding: 10px; font-size: 16px;">
                </div>
                <div style="margin: 10px 0;">
                    <textarea name="content" placeholder="Содержание поста" required
                              style="width: 100%; padding: 10px; height: 200px; font-size: 14px;"></textarea>
                </div>
                <button type="submit" style="padding: 10px 20px; background: #2c3e50; color: white; border: none;">
                    Опубликовать
                </button>
                <a href="posts" style="margin-left: 10px; color: #2c3e50;">Отмена</a>
            </form>
        </div>
    </main>

    <jsp:include page="partial/footer.jsp"/>
</body>
</html>