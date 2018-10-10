<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 09.10.18
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
</br></br>
<p> Ostatnio dodane rozwiązania: </p>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id: </th>
        <th> Description: </th>
        <th> Exercise: </th>
        <th> User: </th>
        <th> Details: </th>
    </tr>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td> ${solution.id} </td>
            <td> ${solution.description} </td>
            <td> ${solution.exercise_id} </td>
            <td> ${solution.user_id} </td>
            <td> <a href="http://localhost:8080/school/SolutionDetail?id=${solution.user_id}" > Szczegóły </a> </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@ include file="footer.jsp" %>
</body>
</html>
