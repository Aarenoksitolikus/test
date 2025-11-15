function validateRegisterForm() {
  const pwd = document.getElementById('password').value;
  const re = /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}/;
  if (!re.test(pwd)) {
    alert('Пароль должен содержать минимум 8 символов, 1 заглавную букву, 1 цифру и 1 спецсимвол');
    return false;
  }
  return true;
}