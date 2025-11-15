<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${post.title}</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="partial/header.jsp"/>

    <main class="container">
        <div class="card">
            <h1>${post.title}</h1>
            <p><strong>Автор:</strong> ${post.authorName}</p>
            <p><strong>Дата:</strong> ${post.createdDate}</p>
            <div style="margin: 20px 0; line-height: 1.6;">
                ${post.content}
            </div>
        </div>

        <!-- Комментарии -->
        <div class="card" style="margin-top: 30px;">
            <h3>Комментарии (${comments.size()})</h3>

            <c:forEach var="comment" items="${comments}">
                <div style="border-bottom: 1px solid #eee; padding: 10px 0;">
                    <strong>${comment.userName}</strong>
                    <span style="color: #666; font-size: 12px;">${comment.createdDate}</span>
                    <p>${comment.content}</p>

                    <c:if test="${sessionScope.user.role == 'MODERATOR'}">
                        <form action="comments/delete/${comment.id}" method="post" style="display: inline;">
                            <input type="hidden" name="postId" value="${post.id}">
                            <button type="submit" style="color: red; border: none; background: none; cursor: pointer;">
                                [Удалить]
                            </button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>

            <!-- Форма добавления комментария -->
            <c:if test="${not empty sessionScope.user}">
                <div style="margin-top: 20px;">
                    <form action="comments/add" method="post">
                        <input type="hidden" name="postId" value="${post.id}">
                        <textarea name="content" placeholder="Ваш комментарий..." required
                                  style="width: 100%; padding: 10px; height: 80px;"></textarea>
                        <button type="submit" style="margin-top: 10px; padding: 8px 15px; background: #2c3e50; color: white; border: none;">
                            Добавить комментарий
                        </button>
                    </form>
                </div>
            </c:if>
        </div>
    </main>

    <jsp:include page="partial/footer.jsp"/>
</body>
</html>