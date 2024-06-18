<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book to Library</title>
    <style>
        /* Add any styles here */
    </style>
</head>
<body>
<h2>Add Book to Library</h2>
<form method="post" action="createBook">
    <label for="title">Title:</label>
    <input type="text" name="title" id="title" required/><br><br>

    <label for="author">Author:</label>
    <input type="text" name="author" id="author" required/><br><br>

    <label for="publisher">Publisher:</label>
    <input type="text" name="publisher" id="publisher"/><br><br>

    <label for="library">Library:</label>
    <input type="text" name="library" id="library"/><br><br>


    <label for="tags">Tags (separated by commas):</label>
    <input type="text" name="tags" id="tags"/><br><br>


    <input type="submit" value="Add Book">
</form>
</body>
</html>
