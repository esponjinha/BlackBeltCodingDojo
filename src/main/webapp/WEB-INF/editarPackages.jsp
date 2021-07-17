<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <title><c:out value="${p.packageName}"/></title>
</head>
<body>
  <div class="container">
    <a href="/logout" class="btn btn-dark">Logout</a>
    <h1>Package: <c:out value="${p.packageName}"/></h1>
    <form:errors path="paquete.*"/>
    <form:form action="" method="post" modelAttribute="paquete">
      <input type="hidden" name="_method" value="put"/>
      <form:input type="hidden" path="packageName" value="${p.packageName}"/>
      <form:label path="packageCost" cssClass="form-label">Cost: </form:label>
      <form:input path="packageCost" cssClass="form-control" value="${p.packageCost}"/>
      <form:errors path="packageCost"/>

      <input type="submit" class="btn btn-dark mt-3"/>
      <a href="/packages/${p.id}/borrar" class="btn btn-dark mt-3">Eliminar</a>
      <a href="/packages" class="btn btn-dark mt-3">Cancelar</a>
      <p><c:out value="${error}"/></p>
    </form:form>

  </div>
</body>
</html>
