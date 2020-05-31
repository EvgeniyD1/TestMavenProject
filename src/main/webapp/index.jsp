<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">--%>
    <title>Start!</title>
</head>
<body>
Start page
<p></p>
    <form action="FrontController" method="post">
        <input type="submit" value="Button FrontController"/>
    </form>
        <form action="hello" method="post">
    <input type="submit" value="Button Hello"/>
    </form>
    <form action="bye" method="post">
        <input type="submit" value="Button Bye"/>
    </form>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="forward"/>
        Enter login <br/>
        <input type="text" name="login" value=""/><br/>
        Enter password <br/>
        <input type="password" name="password" value=""><br/>
        <input type="submit" value="Button Send">
    </form>
</body>
</html>
