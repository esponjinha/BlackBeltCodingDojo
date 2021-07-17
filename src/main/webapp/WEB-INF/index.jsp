<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Bienvenido</title>
</head>
<body>
    <div class="container">
        <h1>Bienvenido</h1>
        <div class="row">
            <div class="col">
                <form:form action="registration" method="post" modelAttribute="user">
                    <h1>Registrate!</h1>
                    <div class="col">
                        <form:label path="firstName"></form:label>
                    </div>
                </form:form>
            </div>
            <div class="col">

            </div>
        </div>
    </div>
</body>
</html>
