<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комментарии - Test</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<header>
    <nav class="container">
        <div class="logo">
            <h2><i class="fas fa-comments"></i> Комментарии</h2>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/posts"><i class="fas fa-arrow-left"></i> К постам</a></li>
            <li><a href="${pageContext.request.contextPath}/main"><i class="fas fa-home"></i> Главная</a></li>
        </ul>
    </nav>
</header>

<main class="container">
    <div class="post-card">
        <h1>${post.title}</h1>
        <p>${post.content}</p>
        <div class="post-meta">
            <span><i class="fas fa-calendar"></i> ${post.createdDate}</span>
            <span><i class="fas fa-user"></i> ID автора: ${post.authorId}</span>
        </div>
    </div>

    <c:if test="${sessionScope.role != 'unknown_user'}">
        <div class="card">
            <h3><i class="fas fa-plus"></i> Добавить комментарий</h3>
            <form action="${pageContext.request.contextPath}/comment" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="postId" value="${post.id}">
                <div class="form-group">
                    <textarea name="content" placeholder="Введите ваш комментарий..." rows="3" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-paper-plane"></i> Отправить
                </button>
            </form>
        </div>
    </c:if>

    <div class="comments-section">
        <h3><i class="fas fa-comments"></i> Комментарии (${comments.size()})</h3>

        <c:if test="${not empty comments}">
            <c:forEach var="comment" items="${comments}">
                <div class="comment-card card">
                    <div class="comment-content">
                        <p>${comment.content}</p>
                    </div>
                    <div class="comment-meta">
                        <span><i class="fas fa-user"></i> ID пользователя: ${comment.userId}</span>
                        <span><i class="fas fa-calendar"></i> ${comment.createdDate}</span>
                    </div>
                    <c:if test="${sessionScope.role != 'unknown_user' && (sessionScope.user_id == comment.userId || sessionScope.role == 'moderator')}">
                        <form action="${pageContext.request.contextPath}/comment" method="post" class="delete-form">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="commentId" value="${comment.id}">
                            <input type="hidden" name="postId" value="${post.id}">
                            <button type="submit" class="btn btn-danger btn-small" onclick="return confirm('Удалить комментарий?')">
                                <i class="fas fa-trash"></i> Удалить
                            </button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${empty comments}">
            <div class="card">
                <p><i class="fas fa-info-circle"></i> Комментариев пока нет. Будьте первым!</p>
            </div>
        </c:if>
    </div>
</main>

<footer>
    <div class="container">
        <p>&copy; 2024 Test. Контрольная по ОРИСу.</p>
    </div>
</footer>
</body>
</html>