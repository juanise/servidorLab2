package edu.uv.dawts.trabajofinal;

public enum Roles {
	JEFE_PROYECTO("jefe_proyecto"),
	PROGRAMADOR("programador");
	
	private String nombreRol;
	
	private Roles(String role) {
		this.nombreRol = role;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	
}
