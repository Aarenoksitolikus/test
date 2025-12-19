<form method="post" action="/register">
    <input type="text" name="username" placeholder="Логин" required>
    <input type="password" name="password" placeholder="Пароль (>6)" required>
    <button type="submit">Создать аккаунт</button>
</form>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>
