package edu.uv.dawts.trabajofinal;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTarea
 */
@WebServlet("/jefeproyecto/AddTarea")
public class AddTarea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTarea() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("!Hola");
		final AccesoDatos ad = (AccesoDatos) getServletContext().getAttribute("bd");
		final Tarea tarea = UtilController.obtenerBeanDeParametros(request, Tarea.class);
		final Calendar cal = Calendar.getInstance();
		cal.setTime(tarea.getFechaTopeTarea());
		try {
			ad.creaTarea(tarea.getNombreTarea(), 
					tarea.getProyectoTarea(), tarea.getProgramadorTarea(), tarea.getFechaTopeTarea());
			final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jefeproyecto/VerTareas");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
