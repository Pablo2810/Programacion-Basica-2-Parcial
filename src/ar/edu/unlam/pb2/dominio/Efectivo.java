package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Efectivo extends Empleado {

	private String obraSocial;
	
	public Efectivo(Integer legajo, String nombre, String apellido, LocalDate fechaIngreso, Credencial credencial, String obraSocial) {
		super(legajo, nombre, apellido, fechaIngreso, credencial);
		
		this.obraSocial = obraSocial;
		
	}
	

}