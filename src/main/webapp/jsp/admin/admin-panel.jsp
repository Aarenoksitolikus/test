<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp" />
<main class="container">
    <h2>Admin Panel</h2>

    <div>
        <p><a href="/books">Books</a></p>
        <p><a href="/admin/users">Users</a></p>
        <p><a href="/admin/borrows">Current Borrows</a></p>
    </div>
</main>
<jsp:include page="/jsp/partial/footer.jsp" />
</body>
</html>