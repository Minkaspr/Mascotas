<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mascota - Actualizar</title>
        <link rel="icon" href="assets/Clase.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>        
        <div class="container table-hover d-flex flex-column  vh-100 justify-content-center">
            <div class="col-md-12">
                <h3 class="mb-3 text-center">Actualizar Registro</h3>
                <form class="row gap-5" action="Mascota" method="POST">
                    <input type="hidden" name="accion" value="UPD"/>
                    <input type="hidden" name="id_mascota" value="${mascota.id_mascota}"/>
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="nombre" class="form-label">Nombre:</label>
                            <input class="form-control" placeholder="" id="nombre" type="text" value="${mascota.nombre}" name="nombre"/>
                        </div>

                        <div class="col-md-6">
                            <label for="propietario" class="form-label">Propietario:</label>
                            <select class="form-select" id="propietario" name="propietario">
                                <option selected disabled>Seleccionar</option>
                                <c:forEach var="p" items="${propietarios}">
                                    <option value="${p.id_propietario}" <c:if test="${p.id_propietario == mascota.propietario}">selected</c:if>>
                                        ${p.dni} - ${p.nombres} ${p.paterno}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-5">
                            <label for="fecha" class="form-label">Fecha:</label>
                            <input class="form-control" id="fecha" type="date" value="${mascota.fecha}" name="fecha"/>
                        </div>

                        <div class="col-md-4">
                            <label for="peso" class="form-label">Peso:</label>
                            <input class="form-control" id="peso" type="number" step="0.01" min="0.01" value="${mascota.peso}" name="peso"/>
                        </div>

                        <div class="col-md-3">
                            <label for="raza" class="form-label">Raza:</label>
                            <select class="form-select" id="raza" name="raza">
                                <option selected disabled>Seleccionar</option>
                                <c:forEach var="r" items="${razas}">
                                    <option value="${r.id_raza}" <c:if test="${r.id_raza == mascota.raza}">selected</c:if>>
                                        ${r.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-12 d-flex justify-content-center">
                        <button class="btn btn-primary btn-lg" type="submit">Actualizar</button>
                    </div>
                </form>
                <!--Mostrar mensaje de Errores-->
                <c:if test="${message != null}">
                    <div class="card" style="width: 18rem;">
                        <div class="card-header">
                            Error
                        </div>
                        <ul class="list-group list-group-flush">
                            ${message}
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
