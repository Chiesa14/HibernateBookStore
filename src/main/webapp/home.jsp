<%@ page import="java.util.List" %>
<%@ page import="Models.Book" %>
<%@ page import="Models.Publisher" %>
<%@ page import="Models.Library" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>

<h2>All Books</h2>
<button><a href="createBook.jsp">Add Book</a></button><br><br>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if (books != null && !books.isEmpty()) {
%>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Publisher</th>
        <th>Library</th>
        <th>Operations</th>

    </tr>
    </thead>
    <tbody>
    <%
        for (Book book : books) {
    %>
    <tr>
        <td><%= book.getId() %></td>
        <td><%= book.getTitle() %></td>
        <td><%= book.getAuthor() %></td>
        <td><%= book.getPublisher() != null ? book.getPublisher().getName() : "" %></td>
        <td><%= book.getLibrary() != null ? book.getLibrary().getName() : "" %></td>

        <td>
            <button><a href="updatebook?id=<%= book.getId() %>">Update</a></button>
            <button><a href="deletebook?id=<%= book.getId() %>">Delete</a></button>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<%} else {%>
<h4>No books available</h4>
<%}%>

</body>
</html>
