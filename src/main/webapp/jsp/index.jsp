<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Index</title>
  <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/jsp/partial/header.jsp"/>
<main class="container">
  <h3>All books:</h3>
  <ul>
    <c:forEach items="books" var="book">
      <li>
        <div>
          <h5>${book.title}</h5>
          <p>${book.author}</p>
          <p>${book.available}</p>
        </div>
      </li>
    </c:forEach>
  </ul>

</main>
<jsp:include page="/jsp/partial/footer.jsp"/>

<script src="/js/script.js"></script>
</body>
</html>