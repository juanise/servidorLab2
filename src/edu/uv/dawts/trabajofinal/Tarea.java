package edu.uv.dawts.trabajofinal;

import java.util.Date;

//JavaBean Tarea
public class Tarea {
	private int id;
	private String nombreTarea;
	private int proyectoTarea;
	private Date fechaTopeTarea;
	private Date fechaFinalizacionTarea;
	private int programadorTarea;

	public Tarea() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	public int getProyectoTarea() {
		return proyectoTarea;
	}

	public void setProyectoTarea(int proyectoTarea) {
		this.proyectoTarea = proyectoTarea;
	}

	public Date getFechaTopeTarea() {
		return fechaTopeTarea;
	}

	public void setFechaTopeTarea(Date fechaTopeTarea) {
		this.fechaTopeTarea = fechaTopeTarea;
	}

	public Date getFechaFinalizacionTarea() {
		return fechaFinalizacionTarea;
	}

	public void setFechaFinalizacionTarea(Date fechaFinalizacionTarea) {
		this.fechaFinalizacionTarea = fechaFinalizacionTarea;
	}

	public int getProgramadorTarea() {
		return programadorTarea;
	}

	public void setProgramadorTarea(int programadorTarea) {
		this.programadorTarea = programadorTarea;
	}

	public int getId() {
		return id;
	}

}
