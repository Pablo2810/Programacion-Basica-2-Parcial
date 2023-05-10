package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;

public class Empresa {

	private String nombre;
	private HashSet<Puerta> puertas;
	private HashSet<Empleado> empleados;

	public Empresa(String nombre) {
		this.nombre = nombre;
		this.puertas = new HashSet<Puerta>();
		this.empleados = new HashSet<Empleado>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregarPuerta(Puerta puerta) {
		puertas.add(puerta);
	}

	public HashSet<Puerta> getPuertas() {
		return puertas;
	}

	public void setPuertas(HashSet<Puerta> puertas) {
		this.puertas = puertas;
	}

	public void agregarEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
		
	}

	public HashSet<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(HashSet<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	

}
