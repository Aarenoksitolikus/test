<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="auth-page">
<div class="hero">
    <h1>Test</h1>
    <p>Контрольная по ОРИСу</p>
    <div class="auth-buttons">
        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">
            <i class="fas fa-sign-in-alt"></i> Войти
        </a>
        <a href="${pageContext.request.contextPath}/register" class="btn btn-secondary">
            <i class="fas fa-user-plus"></i> Регистрация
        </a>
    </div>
</div>
<jsp:include page="/jsp/partial/footer.jsp"/>
</body>
</html>