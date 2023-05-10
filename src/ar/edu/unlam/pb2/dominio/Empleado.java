package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

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

}
