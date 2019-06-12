<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GeneGenie
  Date: 12.06.2019
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:url value="/meals" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${id}">
    <label for="description">Описание</label>
    <input type="text" name="description" id="description" value="${meal.description}">
    <br>
    <label for="day">День </label>
    <input type="number" name="day" id="day" value="${date}">
    <br>
    <label for="month">Месяц </label>
    <input type="number" name="month" id="month" value="${month}">
    <br>
    <label for="year">Год </label>
    <input type="number" name="year" id="year" value="${year}">
    <br>
    <label for="hour">Часы </label>
    <input type="number" name="hour" id="hour" value="${hour}">
    <br>
    <label for="minute">Минуты </label>
    <input type="number" name="minute" id="minute" value="${minute}">
    <br>

    <label for="calories">Калории </label>
    <input type="number" name="calories" id="calories" value="${meal.calories}">
    <br>

    <input type="submit" value="Добавить">

</form>

</body>
</html>
