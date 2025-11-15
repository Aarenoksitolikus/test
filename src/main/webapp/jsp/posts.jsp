<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Все посты</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="partial/header.jsp"/>

    <main class="container">
        <h1>Все посты</h1>

        <c:forEach var="post" items="${posts}">
            <div class="card" style="margin-bottom: 20px;">
                <h3>
                    <a href="posts/view/${post.id}">${post.title}</a>
                </h3>
                <p><strong>Автор:</strong> ${post.authorName}</p>
                <p><strong>Дата:</strong> ${post.createdDate}</p>
                <p>${post.content.length() > 150 ? post.content.substring(0, 150) + '...' : post.content}</p>

                <c:if test="${not empty sessionScope.user && (sessionScope.user.role == 'MODERATOR' || sessionScope.user.id == post.authorId)}">
                    <a href="#" style="color: blue;">[Редактировать]</a>
                </c:if>
            </div>
        </c:forEach>
    </main>

    <jsp:include page="partial/footer.jsp"/>
</body>
</html>