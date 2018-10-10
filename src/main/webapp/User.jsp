<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 10.10.18
  Time: 16:19
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
<p> User details: </p>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id:</th>
        <th> Username:</th>
        <th> Email:</th>
        <th> Group id:</th>
    </tr>
    <tr>
        <td> <c:out value="${user.id}" /> </td>
        <td> <c:out value="${user.username}" /> </td>
        <td> <c:out value="${user.email}" /> </td>
        <td> <c:out value="${user.userGroupId}" /> </td>
    </tr>
</table>
<p> User's solutions: </p>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id: </th>
        <th> Description: </th>
        <th> Exercise number: </th>
    </tr>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td> ${solution.id} </td>
            <td> ${solution.description} </td>
            <td> ${solution.exercise_id} </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@ include file="footer.jsp" %>
</body>
</html>
