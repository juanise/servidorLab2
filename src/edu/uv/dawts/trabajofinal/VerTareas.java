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
@WebServlet("/jefeproyecto/VerTareas")
public class VerTareas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
		final String idProyecto = request.getParameter("id_pr");
		
		if (idProyecto != null) {
			try {
				final List<Programador> programadoresList = ad.getAllProgramadores();
				request.setAttribute("programadoresList", programadoresList);
				final List<Tarea> tareasList = ad.getAllTareas(Integer.valueOf(idProyecto));
				request.setAttribute("tareas", tareasList);
				getServletContext().getRequestDispatcher(
						"/jefeproyecto/muestraTareas.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
