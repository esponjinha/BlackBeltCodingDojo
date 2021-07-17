<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Admin dashboard</h1>
<table class="table">
    <thead class="">
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Next Due Date</th>
        <th scope="col">Amount Date</th>
        <th scope="col">Package Type</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="usuario" items="${usuarios}">
        <tr>
            <th scope="row"><c:out value="${usuario.firstName} ${usuario.lastName}"/> </th>
            <td>Mark</td>
            <td><c:out value="${usuario.paquete.packageCost}"/></td>
            <td><c:out value="${usuario.paquete.packageName}"/></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<table class="table">
    <thead class="">
    <tr>
        <th scope="col">Package name</th>
        <th scope="col">Package cost</th>
        <th scope="col">Available</th>
        <th scope="col">Users</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="usuario" items="${usuarios}">
        <tr>
            <th scope="row"><c:out value="${usuario.firstName} ${usuario.lastName}"/> </th>
            <td>Mark</td>
            <td><c:out value="${usuario.paquete.packageCost}"/></td>
            <td><c:out value="${usuario.paquete.packageName}"/></td>
        </tr>
    </c:forEach>

    </tbody>
</table>






<form:form method="POST" action="" cssClass="form col border border-1 rounded" modelAttribute="paquete" >
    <p class="form-group col">
        <form:label path="packageName">Name: </form:label>
        <form:input cssClass="form-control" path="packageName"/>
        <form:errors path="packageName"/>
    </p>
    <p class="col">
        <form:label path="packageCost">Package Cost:</form:label>
        <form:input cssClass="form-control" path="packageCost"/>
        <form:errors path="packageCost"/>
    </p>
    <input class="btn btn-warning" type="submit" value="Register!"/>
</form:form>

</body>
</html>
