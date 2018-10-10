<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 10.10.18
  Time: 14:16
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
        <th> Username: </th>
        <th> Email: </th>
        <th> User details: </th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td> ${user.id} </td>
            <td> ${user.username} </td>
            <td> ${user.email} </td>
            <td> <a href="#userId=${user.id}" > Szczegóły </a> </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@ include file="footer.jsp" %>
</body>
</html>
