<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>UsersForm</title>
</head>

<body>
<h2>User Information</h2>
<%--@elvariable id="command" type=""--%>
<form:form method = "POST" action = "/users/save" modelAttribute="command">
    <table>
        <tr>
            <td><form:label path = "username">UserName</form:label></td>
            <td><form:input path = "username" /></td>
        </tr>
        <tr>
            <td><form:label path = "surname">Surname</form:label></td>
            <td><form:input path = "surname" /></td>
        </tr>
        <tr>
            <td><form:label path = "patronymic">Patronymic</form:label></td>
            <td><form:input path = "patronymic" /></td>
        </tr>
        <tr>
            <td><form:label path = "phoneNumber">PhoneNumber</form:label></td>
            <td><form:input path = "phoneNumber" /></td>
        </tr>
        <tr>
            <td><form:label path = "login">Login</form:label></td>
            <td><form:input path = "login" /></td>
        </tr>
        <tr>
            <td><form:label path = "password">Password</form:label></td>
            <td><form:input path = "password" /></td>
        </tr>
        <tr>
            <td><form:label path = "birthDate">BirthDate</form:label></td>
            <td><form:input path = "birthDate" /></td>
        </tr>
        <tr>
            <td><form:label path = "mail">Mail</form:label></td>
            <td><form:input path = "mail" /></td>
        </tr>
        <tr>
            <td><form:label path = "countryLocation">CountryLocation</form:label></td>
            <td><form:input path = "countryLocation" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>
