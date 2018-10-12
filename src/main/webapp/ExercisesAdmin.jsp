<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 11.10.18
  Time: 23:19
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
<p><a href="http://localhost:8080/school/panelAdmin/ExercisesAdmin?action=add" > Add </a></p>
<table border="1px solid black">
    <tr>
        <th> Id:</th>
        <th> Title:</th>
        <th> Description:</th>
        <th> Action:</th>
    </tr>
    <c:forEach items="${exercises}" var="exercise">
        <tr>
            <td> ${exercise.id} </td>
            <td> ${exercise.title} </td>
            <td> ${exercise.description} </td>
            <td> <a href="http://localhost:8080/school/panelAdmin/ExercisesAdmin?action=edit&id=${exercise.id}" > Edit </a> / <a href="http://localhost:8080/school/panelAdmin/ExercisesAdmin?action=delete&id=${exercise.id}" > Delete </a> </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@ include file="footer.jsp" %>
</body>
</html>
