<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="ru">

<head>
    <title>Книга</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</head>

<body class="radiant" id="change_theme">

<jsp:include page="/jsp/partial/header.jsp" />

<div class="info">
    <h1 id="center_h1">Аренда книги</h1>
</div>

<table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <th>Название</th>
        <th>Автор</th>
        <th>Есть ли в наличии</th>
    </tr>


    <c:forEach var="book" items="${list}">

        <tr>
            <td>
                    ${book.name}
            </td>

            <td>
                    ${book.author}
            </td>
            <td>
                    ${book.available}
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/books" method="post">
                    <input type="hidden" name="bookID" value="${book.id}">
                    <button type="submit">В корзину</button>
                </form>
            </td>
        </tr>

    </c:forEach>

</table>
</body>

<jsp:include page="/jsp/partial/footer.jsp" />
</html>