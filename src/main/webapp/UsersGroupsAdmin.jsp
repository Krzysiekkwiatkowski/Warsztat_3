<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 10.10.18
  Time: 20:37
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
<p><a href="http://localhost:8080/school/panelAdmin/UsersGroupsAdmin?action=add" > Add </a></p>
<table border="1px solid black">
    <tr>
        <th> Id: </th>
        <th> Name: </th>
        <th> Action: </th>
    </tr>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td> ${group.id} </td>
            <td> ${group.name} </td>
            <td> <a href="http://localhost:8080/school/panelAdmin/UsersGroupsAdmin?action=edit&id=${group.id}" > Edit </a> / <a href="http://localhost:8080/school/panelAdmin/UsersGroupsAdmin?action=delete&id=${group.id}" > Delete </a> </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@ include file="footer.jsp" %>
</body>
</html>
