<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mascotas</title>
        <link rel="icon" href="assets/Clase.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <div class="container table-hover d-flex flex-column gap-3 vh-100 justify-content-center">
            <h1 class="text-center">Listados de mascotas</h1>
            <div class="d-flex justify-content-end">
                <a class="btn btn-outline-success" href="Mascota?accion=CBO">Agregar Mascota</a>
            </div>            
            <table border="1" class="table">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nombre de la Mascota</th>
                    <th scope="col">Nombre del Propietario</th>
                    <th scope="col">Raza</th>
                    <th scope="col">Peso</th>
                    <th scope="col">Fecha de nacimiento</th>
                    <th scope="col" colspan="2">Acciones</th>
                </tr>
                <!--Se muestra la mascotaDTO de la tabla mascota_view-->
                <c:forEach var="m" items="${mascotas}">
                    <tr>
                        <th scope="row">${m.id_mascota}</th>
                        <td>${m.nombre}</td>
                        <td>${m.propietario}</td>
                        <td>${m.fecha}</td>
                        <td>${m.peso}</td>
                        <td>${m.raza}</td>
                        <td>
                            <a class="btn btn-outline-warning" href="Mascota?accion=GET&id_mascota=${m.id_mascota}">Editar</a>
                        </td>
                        <td>
                            <a class="btn btn-outline-danger" href="Mascota?accion=DEL&id_mascota=${m.id_mascota}" onclick="return confirm('¿Estás seguro de eliminar?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
