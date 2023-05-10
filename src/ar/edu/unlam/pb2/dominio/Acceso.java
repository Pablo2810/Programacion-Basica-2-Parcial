package ar.edu.unlam.pb2.dominio;

import java.time.LocalDateTime;

public class Acceso {

	private LocalDateTime fechaYHora;
	private Puerta puerta;
	private Empleado empleado;
	
	public Acceso(Puerta puerta, Empleado empleado, LocalDateTime fechaYHora) {
		this.puerta = puerta;
		this.empleado = empleado;
		this.fechaYHora = fechaYHora;
	}

	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(LocalDateTime fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public Puerta getPuerta() {
		return puerta;
	}

	public void setPuerta(Puerta puerta) {
		this.puerta = puerta;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
