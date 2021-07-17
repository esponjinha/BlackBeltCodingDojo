<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between">
        <h1>Welcome <c:out value="${u.firstName} ${u.lastName}"/></h1>
        <a class="btn btn-dark" href="/logout">Logout</a>
    </div>

    <form action="/users/${u.id}/changeplan" method="GET">
        <ul class="list-group">
            <li class="list-group-item"> Your current Package: <c:out value="${u.paquete.packageName}"/> </li>
            <li class="list-group-item"> Package Cost: $<c:out value="${u.paquete.packageCost}"/></li>

        </ul>
        <h3> Switch PackAge </h3>
        <select name="paquete">
            <c:forEach var="paquete" items="${paquetes}">
                <option value="${paquete.id}"> <c:out value="${paquete.packageName}"/> </option>
            </c:forEach>
        </select>
    <button class="btn btn-dark" type="submit">Actualizar</button>
    </form>



</div>
</body>
</html>
