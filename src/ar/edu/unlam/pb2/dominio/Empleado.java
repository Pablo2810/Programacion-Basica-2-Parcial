package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Empleado {
	
	private Integer legajo;
	private String nombre;
	private String apellido;
	private LocalDate fechaIngreso;
	private Credencial credencial;
	
	public Empleado(Integer legajo, String nombre, String apellido, LocalDate fechaIngreso, Credencial credencial) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaIngreso = fechaIngreso;
		this.credencial = credencial;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Credencial getCredencial() {
		return credencial;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(legajo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(legajo, other.legajo);
	}
	
	

}
