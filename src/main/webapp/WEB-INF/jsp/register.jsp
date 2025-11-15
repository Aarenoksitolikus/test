<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validatePassword() {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const errorDiv = document.getElementById('passwordError');

            if (password.length < 8) {
                errorDiv.textContent = 'Пароль должен содержать минимум 8 символов';
                return false;
            }

            if (!/\d/.test(password)) {
                errorDiv.textContent = 'Пароль должен содержать хотя бы одну цифру';
                return false;
            }

            if (!/[a-zA-Z]/.test(password)) {
                errorDiv.textContent = 'Пароль должен содержать хотя бы одну букву';
                return false;
            }

            if (password !== confirmPassword) {
                errorDiv.textContent = 'Пароли не совпадают';
                return false;
            }

            errorDiv.textContent = '';
            return true;
        }

        function validateForm() {
            return validatePassword();
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4 class="text-center">Регистрация</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/auth/register" method="post" onsubmit="return validateForm()">
                            <div class="mb-3">
                                <label for="username" class="form-label">Имя пользователя</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Пароль</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       onkeyup="validatePassword()"  minlength="10" required>
                                <div class="form-text">Пароль должен содержать минимум 8 символов, включая цифры и буквы</div>
                            </div>
                            <div class="mb-3">
                                <label for="confirmPassword" class="form-label">Подтверждение пароля</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                                       onkeyup="validatePassword()"  minlength="10" required>
                            </div>
                            <div id="passwordError" class="text-danger mb-3"></div>
                            <button type="submit" class="btn btn-primary w-100">Зарегистрироваться</button>
                        </form>
                        <div class="text-center mt-3">
                            <a href="${pageContext.request.contextPath}/auth/login">Уже есть аккаунт? Войдите</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>