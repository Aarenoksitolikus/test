<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h2>Users</h2>

    <c:forEach var="user" items="${users}">
        <div style="border: 1px solid #ccc; padding: 10px; margin: 10px 0;">
            <p><strong>${user.username}</strong> (${user.role})</p>
            <a href="/admin/user-borrows?userId=${user.id}">View books</a>
        </div>
    </c:forEach>
</main>
<jsp:include page="/jsp/partial/footer.jsp" />
</body>
</html>