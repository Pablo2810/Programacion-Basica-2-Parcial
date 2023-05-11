package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;

public class Empresa {

	private String nombre;
	private HashSet<Puerta> puertas;
	private HashSet<Empleado> empleados;
	private HashSet<Credencial> credenciales;

	public Empresa(String nombre) {
		this.nombre = nombre;
		this.puertas = new HashSet<Puerta>();
		this.empleados = new HashSet<Empleado>();
		this.credenciales = new HashSet<Credencial>();
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
		
		if(this.empleados.isEmpty() && this.credenciales.isEmpty()) {
			this.empleados.add(empleado);
			if(empleado.getCredencial() != null) {
			this.agregarCredencialActivada(empleado.getCredencial());
			 }
			
		}else {
			for (Credencial credencialObtenida : credenciales) {
				if(!credencialObtenida.getId().equals(empleado.getCredencial().getId())) {
					this.empleados.add(empleado);
					this.agregarCredencialActivada(empleado.getCredencial());
		}
			}
			
		}
		
	
	}
		

	private void agregarCredencialActivada(Credencial credencial) {
		credencial.setEstado(Estado.ACTIVADA);
		this.credenciales.add(credencial);
		
	}

	public HashSet<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(HashSet<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public HashSet<Credencial> getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(HashSet<Credencial> credenciales) {
		this.credenciales = credenciales;
	}

	public void agregarCredencial(Credencial credencial) {
		credencial.setEstado(Estado.DESACTIVADA);
		this.credenciales.add(credencial);
	}

	public void asignarCredencialDesactivadaYActivarla(Empleado empleado) {
		
		for (Credencial credencial : credenciales) {
			if(credencial.getEstado().equals(Estado.DESACTIVADA)) {
				credencial.setEstado(Estado.ACTIVADA);
				empleado.setCredencial(credencial);
			}
		}
		
	}

	public void eliminarEmpleadoYDesactivarCredencial(Empleado empleado) {
		
		for (Empleado empleadoObtenido : empleados) {
			if(empleadoObtenido.getLegajo().equals(empleado.getLegajo())) {
				for (Credencial credencial : credenciales) {
					if(credencial.getId().equals(empleado.getCredencial().getId())) {
						credencial.setEstado(Estado.DESACTIVADA);
						empleados.remove(empleado);
					}
				}
			}
		}
		
	}
	

}
