<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
    <div class="container">
        <a href="/logout" class="btn btn-dark">Logout</a>
        <h1 class="mt-3">Admin dashboard</h1>
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
                    <td><c:out value="${usuario.fechaPago}"/></td>
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
            <c:forEach var="paquete" items="${paquetes}">
                <tr>
                    <th scope="row"><c:out value="${paquete.packageName}"/> </th>
                    <td><c:out value="${paquete.packageCost}"/></td>
                    <c:if test="${paquete.available == true}">
                        <td>Available</td>
                    </c:if>
                    <c:if test="${paquete.available == false}">
                        <td>Unavailable</td>
                    </c:if>
                    <td><c:out value="${paquete.users.size()}"/></td>
                    <c:if test="${paquete.available && paquete.users.size() == 0}">
                        <td><a href="/packages/${paquete.id}/desactivar">Desactivar</a> | <a href="/packages/${paquete.id}/editar">Editar</a></td>
                    </c:if>
                    <c:if test="${paquete.available && paquete.users.size() > 0}">
                        <td>Desactivar | <a href="/packages/${paquete.id}/editar">Editar</a></td>
                    </c:if>
                    <c:if test="${paquete.available == false}">
                        <td><a href="/packages/${paquete.id}/activar">Activar</a> | <a href="/packages/${paquete.id}/editar">Editar</a></td>
                    </c:if>
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
            <input class="btn btn-dark" type="submit" value="Register!"/>
        </form:form>
    </div>
</body>
</html>
