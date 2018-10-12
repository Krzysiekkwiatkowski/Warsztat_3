<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 12.10.18
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
</br></br>
<p> Solutions: </p>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id: </th>
        <th> Updated: </th>
        <th> Description: </th>
        <th> Exercise's number: </th>
        <th> User's number: </th>
    </tr>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td> ${solution.id} </td>
            <td> ${solution.updated} </td>
            <td> ${solution.description} </td>
            <td> ${solution.exercise_id} </td>
            <td> ${solution.user_id} </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@ include file="footer.jsp" %>
</body>
</html>
