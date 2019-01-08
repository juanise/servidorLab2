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
		final String programador = request.getParameter("programador");
	    request.setAttribute("nombre_pr", programador);
	%>
	<h1>
		Tareas del programador
		<%=programador%>
	</h1>

	<table>
		<tr>	
			<th>Select</th>
			<th>Nombre Tarea</th>
			<th>Programador</th>
			<th>Fecha Tope</th>
			<th>Fecha Finalización</th>
		</tr>
		<form action="/programador/FinalizarTareas" method="POST">
			<c:forEach items="${tareas}" var="tarea">
				<tr>
				<c:choose>
				<c:when test="${tarea.fechaFinalizacionTarea==null}">
					<td><input type="checkbox" name="finalizarTarea" value="${tarea.id}"></td>
				</c:when>
				<c:otherwise>
					<td><input type="checkbox" value="${tarea.id}" disabled="disabled"></td>
				</c:otherwise>
				</c:choose>
					<td>${tarea.nombreTarea}</td>
					<td>${tarea.programadorTarea}</td>
					<td>${tarea.fechaTopeTarea}</td>
						<td>
							<c:choose>
						    <c:when test="${tarea.fechaFinalizacionTarea!=null}">
						        Finalizada
						        <br />
						    </c:when>    
						    <c:otherwise>
						        Pendiente
						        <br />
						    </c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="submit" value="Finaliza Tarea" /></td>
			</tr>
		</form>
	</table>

</body>
</html>