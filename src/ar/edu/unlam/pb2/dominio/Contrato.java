package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Contrato {
	private LocalDate fechaCaducidad;

	public Contrato(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

}
