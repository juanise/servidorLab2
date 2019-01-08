package edu.uv.dawts.trabajofinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AccesoDatos {
	private PreparedStatement getTareasUsuario;
	private PreparedStatement getTareasProyecto;
	private PreparedStatement setFechaFinalizacion;
	private PreparedStatement creaProyecto;
	private PreparedStatement creaTarea;
	private PreparedStatement getProgramadores;
	private Statement st;
	private SimpleDateFormat formatter;

	public AccesoDatos(Connection c) throws Exception {
		getTareasUsuario = c
				.prepareStatement("select * from tareas where user_id=?");
		setFechaFinalizacion = c
				.prepareStatement("update tareas set fechafinalizacion=? where ? LIKE concat(';%', concat(ta_id, '%;'))");
		String pattern = "yyyy-MM-dd";
		formatter = new SimpleDateFormat(pattern);
		st = c.createStatement();
		creaProyecto = c
				.prepareStatement("insert into proyectos (nombre) values (?)");
		creaTarea = c
				.prepareStatement("insert into tareas (nombre,pr_id,fechalimite,user_id) values (?,?,?,?)");
		
		getTareasProyecto = c.prepareStatement("select * from tareas where pr_id=?");
		getProgramadores = c.prepareStatement("select u.user_id, u.username from users u "
				+ "join roles r on u.username = r.username and r.role = ? ");
	
	}
	
	public List<Programador> getAllProgramadores() throws Exception{
		getProgramadores.setString(1, Roles.PROGRAMADOR.getNombreRol());
		final ResultSet rs = getProgramadores.executeQuery();
		final List<Programador> programadoresList = new ArrayList<>();
		rs.beforeFirst();
		while (rs.next()) {
			final Programador pro = new Programador();
			pro.setUserId(rs.getInt(1));
			pro.setNombre(rs.getString(2));
			programadoresList.add(pro);
		}
		rs.close();
		return programadoresList;
	}

	public ArrayList<Tarea> getAllTareas(int proyecto) throws Exception {
		getTareasProyecto.setInt(1,proyecto);
		ResultSet rs = getTareasProyecto.executeQuery();
		rs.beforeFirst();
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		while (rs.next()) {
			Tarea t = new Tarea();
			t.setId(rs.getInt(1));
			t.setNombreTarea(rs.getString(2));
			t.setProyectoTarea(rs.getInt(3));
			t.setFechaTopeTarea(rs.getDate(4));
			t.setProgramadorTarea(rs.getInt(5));
			t.setFechaFinalizacionTarea(rs.getDate(6));
			tareas.add(t);
		}
		rs.close();
		return tareas;

	}

	public ArrayList<Proyecto> getAllProyectos() throws Exception {
		ResultSet rs = st.executeQuery("select * from proyectos");
		rs.beforeFirst();
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
		while (rs.next()) {
			Proyecto pr = new Proyecto();
			pr.setId(rs.getInt(1));
			pr.setNombre(rs.getString(2));
			proyectos.add(pr);
		}
		rs.close();
		return proyectos;
	}

	public ArrayList<Tarea> getTareasUsuario(int user) throws Exception {
		getTareasUsuario.setInt(1, user);
		ResultSet rs = getTareasUsuario.executeQuery();
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		while (rs.next()) {
			Tarea t = new Tarea();
			t.setId(rs.getInt(1));
			t.setNombreTarea(rs.getString(2));
			t.setProyectoTarea(rs.getInt(3));
			t.setFechaTopeTarea(rs.getDate(4));
			t.setProgramadorTarea(rs.getInt(5));
			t.setFechaFinalizacionTarea(rs.getDate(6));
			tareas.add(t);
		}
		rs.close();
		return tareas;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion, String... tareasList)
			throws Exception {
		final String tareas = UtilController.semicolonFormat(tareasList);
		String finalizacion = UtilController.getFechaYYYY_MM_DD(fechaFinalizacion);
		setFechaFinalizacion.setString(1, finalizacion);
		setFechaFinalizacion.setString(2, tareas);

		setFechaFinalizacion.executeUpdate();
	}

	public void creaProyecto(String nombre) throws Exception {
		creaProyecto.setString(1, nombre);

		creaProyecto.executeUpdate();
	}

	public void creaTarea(String nombre, int proyecto, int usuario, Date fechaTope) throws Exception {
		String limite = UtilController.getFechaYYYY_MM_DD(fechaTope);
		creaTarea.setString(1, nombre);
		creaTarea.setInt(2, proyecto);
		creaTarea.setString(3, limite);
		creaTarea.setInt(4, usuario);

		creaTarea.executeUpdate();
	}

	private String getDate(int year, int mes, int dia) {
		Calendar c = Calendar.getInstance();
		c.set(year, mes - 1, dia);
		String fecha = formatter.format(c.getTime());
		return fecha;
	}

	public void closeAll() throws Exception {
		close(st);
		close(creaTarea);
		close(creaProyecto);
		close(getTareasUsuario);
		close(setFechaFinalizacion);
		close(getTareasProyecto);
		close(getProgramadores);
	}
	
	private void close(AutoCloseable autoCloseable) throws Exception{
		if (autoCloseable != null) {
			autoCloseable.close();
		}
	}

}
