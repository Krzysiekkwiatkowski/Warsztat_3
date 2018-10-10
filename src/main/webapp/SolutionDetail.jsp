<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 09.10.18
  Time: 22:01
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
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id: </th>
        <th> Created: </th>
        <th> Updated: </th>
        <th> Description: </th>
        <th> Exercise id: </th>
        <th> User id: </th>
    </tr>
    <tr>
        <td> ${solution.id} </td>
        <td> ${solution.created} </td>
        <td> ${solution.updated} </td>
        <td> ${solution.description} </td>
        <td> ${solution.exercise_id} </td>
        <td> ${solution.user_id} </td>
    </tr>
</table>
</br></br>
<%@ include file="footer.jsp" %>
</body>
</html>
