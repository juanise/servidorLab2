package edu.uv.dawts.trabajofinal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerProyectos
 */
@WebServlet("/programador/VerTareas")
public class VerTareasProgramador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
		try {
			final List<Tarea> tareasList = ad.getTareasUsuario(2);
			request.setAttribute("tareas", tareasList);
			getServletContext().getRequestDispatcher(
					"/programador/muestraTareas.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
