<form method="post" action="/login">
    <input type="text" name="username" placeholder="Логин" required>
    <input type="password" name="password" placeholder="Пароль" required>
    <button type="submit">Войти</button>
</form>

<c:if test="${not empty error}">
    <p>${error}</p>
</c:if>
