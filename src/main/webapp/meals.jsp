<%--
  Created by IntelliJ IDEA.
  User: GeneGenie
  Date: 08.06.2019
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MealsList</title>
</head>
<body>
<h2> Meals</h2>

<table>
    <style type="text/css">
        TABLE {
            width: 500px; /* Ширина таблицы */
            border: 1px solid black; /* Рамка вокруг таблицы */
        }
        TD, TH {
            border: 1px solid black;
            padding: 3px; /* Поля вокруг содержимого ячеек */
        }
        TH {
            text-align: left; /* Выравнивание по левому краю */
            background: black; /* Цвет фона */
            color: white; /* Цвет текста */
        }
    </style>

        <th> Время</th>
        <th> Наименование</th>
        <th> Калории</th>

    <c:forEach items="${meals}" var="meal">

        <c:if test="${meal.excess == true}">
            <tr bgcolor = "red">
        </c:if>
        <c:if test="${meal.excess != true}">
            <tr  bgcolor = "green">>
        </c:if>
            <td>
                <javatime:format value="${meal.dateTime}" pattern="dd-MM-yyyy HH:MM" />

            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
