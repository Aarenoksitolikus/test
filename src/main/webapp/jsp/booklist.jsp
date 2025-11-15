<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page session="false" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
  <h1>Your borrowed books: </h1>

  <ol>
    <c:forEach items="${books}" var="book">
      <li>
        <div>
          <h5>${book.title}</h5>
          <p>${book.author}</p>
        </div>
      </li>
    </c:forEach>
  </ol>
</body>
</html>
