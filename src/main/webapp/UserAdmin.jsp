<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 12.10.18
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<p>
<%@ include file="header.jsp" %>
</br></br>
<p> <a href="http://localhost:8080/school/panelAdmin/UserAdmin?action=add"> Add </a> </p>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id: </th>
        <th> Username: </th>
        <th> Email: </th>
        <th> User's group id: </th>
        <th> Action: </th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td> ${user.id} </td>
            <td> ${user.username} </td>
            <td> ${user.email} </td>
            <td> ${user.userGroupId} </td>
            <td> <a href="http://localhost:8080/school/panelAdmin/UserAdmin?action=edit&id=${user.id}"> Edit </a> / <a href="http://localhost:8080/school/panelAdmin/UserAdmin?action=delete&id=${user.id}"> Delete </a> </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@ include file="footer.jsp" %>
</body>
</html>
