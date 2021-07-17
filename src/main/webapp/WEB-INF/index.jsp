<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Bienvenido</title>
</head>
<body class="bg-secondary.bg-gradient">
    <div class="container">
        <h1>Bienvenido</h1>
        <div class="row">
            <div class="col">
                <form:form action="/registration" method="post" modelAttribute="user">
                    <h1>Registrate!</h1>
                    <div class="col">
                        <form:label path="firstName" cssClass="form-label">Nombre: </form:label>
                        <form:input path="firstName" cssClass="form-control"/>
                        <form:errors path="firstName" cssClass="red"/>
                    </div>
                    <div class="col">
                        <form:label path="lastName" cssClass="form-label">Apellido: </form:label>
                        <form:input path="lastName" cssClass="form-control"/>
                        <form:errors path="lastName" cssClass="red"/>
                    </div>
                    <div class="col">
                        <form:label path="password" cssClass="form-label">Contraseña: </form:label>
                        <form:input path="password" cssClass="form-control"/>
                        <form:errors path="lastName" cssClass="red"/>
                    </div>
                    <div class="col">
                        <form:label path="passwordConfirmation" cssClass="form-label">Confirmar contraseña: </form:label>
                        <form:input path="passwordConfirmation" cssClass="form-control"/>
                        <form:errors path="passwordConfirmation" cssClass="red"/>
                    </div>
                    <input type="submit" value="Registrarse" class="btn btn-warning"/>
                </form:form>
            </div>
            <div class="col">
                <form action="/login" method="post">
                    <h1>Login</h1>
                    <p class="red"><c:out value="${error}" /></p>
                    <div class="col">
                        <label class="form-label">Email: </label>
                        <input name="email" class="form-control"/>

                    </div>
                    <div class="col">
                        <label class="form-label">Contraseña: </label>
                        <input name="password" class="form-control"/>
                    </div>
                    <input type="submit" value="Login" class="btn btn-warning"/>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
