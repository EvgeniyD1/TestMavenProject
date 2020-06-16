<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Buildings</title>
</head>
<body>
<div>
    <h1>Buildings</h1>
</div>
<table>
    <tr>
        <td>BuildingId</td>
        <td>BuildingType</td>
        <td>BuildingRoomsCount</td>
        <td>BuildingTotalRoomsArea</td>
        <td>BuildingTownLocation</td>
    </tr>
    <tr>
        <td>${building.id}</td>
        <td>${building.type}</td>
        <td>${building.roomsCount}</td>
        <td>${building.totalRoomsArea}</td>
        <td>${building.townLocation}</td>
    </tr>
</table>

</body>
</html>
