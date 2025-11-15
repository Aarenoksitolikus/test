<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Посты - Test</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<header>
    <nav class="container">
        <div class="logo">
            <h2><i class="fas fa-list"></i> Все посты</h2>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/main"><i class="fas fa-home"></i> Главная</a></li>
            <c:if test="${not empty sessionScope.user_name}">
                <li><a href="${pageContext.request.contextPath}/create">Создать пост</a></li>
            </c:if>
        </ul>
    </nav>
</header>

<main class="container">
    <c:if test="${sessionScope.role != 'unknown_user'}">
        <div class="create-post">
            <a href="${pageContext.request.contextPath}/create" class="btn btn-primary">
                <i class="fas fa-plus"></i> Создать новый пост
            </a>
        </div>
    </c:if>

    <div class="posts-list">
        <c:forEach var="post" items="${posts}">
            <div class="post-card">
                <h3>${post.title}</h3>
                <p>${post.content}</p>
                <div class="post-meta">
                    <span><i class="fas fa-calendar"></i> ${post.createdDate}</span>
                    <span><i class="fas fa-user"></i> ID автора: ${post.authorId}</span>
                </div>
                <div class="post-actions">
                    <a href="${pageContext.request.contextPath}/comment?postId=${post.id}" class="btn btn-small">
                        <i class="fas fa-comment"></i> Комментарии
                    </a>
                    <c:if test="${sessionScope.user_id == post.authorId || sessionScope.role == 'moderator'}">
                        <a href="#" class="btn btn-small btn-secondary">
                            <i class="fas fa-edit"></i> Редактировать
                        </a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty posts}">
        <div class="card">
            <p><i class="fas fa-info-circle"></i> Постов пока нет. Будьте первым!</p>
        </div>
    </c:if>
</main>

<footer>
    <div class="container">
        <p>&copy; 2024 Test. Контрольная по ОРИСу.</p>
    </div>
</footer>
</body>
</html>