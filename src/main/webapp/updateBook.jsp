<%@ page import="Models.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Book</title>
</head>
<body>
<%Book book = (Book) request.getAttribute("book");%>
<form action="updatebook" method="post">
    <label for="id">Book id:</label><input name="id" id="id" value="<%=book.getId()%>" disabled="disabled"><br><br>
    <label for="title"> Title:
        <input name="title" id="title" value="<%=book.getTitle()%>"/><br><br>
    </label>
    <label for="author"> Author:
        <input name="author" id="author" value="<%=book.getAuthor()%>"/><br><br>
    </label>
    <label for="publisher"> Publisher:
        <input name="publisher" id="publisher" value="<%= book.getPublisher().getName() %>"/><br><br>
    </label>
    <label for="library"> Library:
        <input name="library" id="library" value="<%= book.getLibrary().getName() %>"/><br><br>
    </label>
    <input type="submit" value="Update"/><br><br>
</form>
</body>
</html>
