<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tareas</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

table, tr, td, th {
	border: 1px solid blue;
}

th {
	font-weight: bold;
}

td {
	padding-left: 2em;
	padding-right: 2em;
}
</style>
</head>
<body>
	<%
		final String proyecto = request.getParameter("nombre_pr");
	    request.setAttribute("nombre_pr", proyecto);
	%>
	<%
		final String id_pr = request.getParameter("id_pr");
		request.setAttribute("id_pr", id_pr);
	%>
	<h1>
		Tareas del proyecto
		<%=proyecto%>
	</h1>

	<table>
		<tr>
			<th>Nombre Tarea</th>
			<th>Programador</th>
			<th>Fecha Tope</th>
			<th>Fecha Finalización</th>
		</tr>
		<c:forEach items="${tareas}" var="tarea">
			<tr>
				<td>${tarea.nombreTarea}</td>
				<td>${tarea.programadorTarea}</td>
				<td>${tarea.fechaTopeTarea}</td>
				<td>${tarea.fechaFinalizacionTarea}</td>
			</tr>
		</c:forEach>
	</table>

	<h2>Crear una nueva tarea</h2>

	<form action="/TrabajoFinal/jefeproyecto/AddTarea" method="POST">
		<table>
			<tr>
				<td>Nombre de la Tarea: <input type="text" name="nombreTarea" /></td>
			</tr>
			<tr>
				<td>Programador: <select name="programadorTarea">
						<c:forEach var="programador" items="${programadoresList}">
							<option value="${programador.userId}">
								<c:out value="${programador.nombre}" />
							</option>
						</c:forEach>
				</select> </td>
			</tr>
			<tr>
				<td>
					<p>Proyecto : <%=proyecto%></p>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" name="proyectoTarea" value="<%=id_pr%>"></td>
				<td><input type="hidden" name="id_pr" value="<%=id_pr%>"></td>
				<td><input type="hidden" name="nombre_pr" value="<%=proyecto%>"></td>
			</tr>
			<tr>
				<td>Fecha Tope : <input type="date" name="fechaTopeTarea"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Crear Tarea" /></td>
			</tr>
		</table>
	</form>

</body>
</html>