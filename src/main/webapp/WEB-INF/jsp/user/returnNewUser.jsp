<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@page isELIgnored = "false" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>

<body>
<h2>NewUser</h2>
<table>
    <tr>
        <td>Id</td>
        <td>${id}</td>
    </tr>
    <tr>
        <td>Username</td>
        <td>${username}</td>
    </tr>
    <tr>
        <td>Surname</td>
        <td>${surname}</td>
    </tr>
    <tr>
        <td>Patronymic</td>
        <td>${patronymic}</td>
    </tr>
    <tr>
        <td>PhoneNumber</td>
        <td>${phoneNumber}</td>
    </tr>
    <tr>
        <td>Login</td>
        <td>${login}</td>
    </tr>
    <tr>
        <td>Password</td>
        <td>${password}</td>
    </tr>
    <tr>
        <td>Created</td>
        <td>${created}</td>
    </tr>
    <tr>
        <td>Changed</td>
        <td>${changed}</td>
    </tr>
    <tr>
        <td>BirthDate</td>
        <td>${birthDate}</td>
    </tr>
    <tr>
        <td>isBlocked</td>
        <td>${isBlocked}</td>
    </tr>
    <tr>
        <td>Mail</td>
        <td>${mail}</td>
    </tr>
    <tr>
        <td>CountryLocation</td>
        <td>${countryLocation}</td>
    </tr>
</table>
</body>

</html>
