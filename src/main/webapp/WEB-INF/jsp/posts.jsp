<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Posts</title></head>
<body>
<c:choose>
  <c:when test="${not empty sessionScope.currentUser}">
    Привет, ${sessionScope.currentUser.username} | <a href="${pageContext.request.contextPath}/logout">Logout</a>
  </c:when>
  <c:otherwise>
    <a href="${pageContext.request.contextPath}/login">Login</a>
  </c:otherwise>
</c:choose>

<h1>Список постов</h1>
<c:forEach var="p" items="${posts}">
  <div>
    <h3><a href="${pageContext.request.contextPath}/post?id=${p.id}">${p.title}</a></h3>
    <small>Автор: ${p.authorId} | ${p.createdDate}</small>
  </div>
</c:forEach>
</body>
</html>