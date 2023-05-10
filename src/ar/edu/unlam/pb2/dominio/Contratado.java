package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Contratado extends Empleado {
	private Contrato contrato;

	public Contratado(Integer legajo, String nombre, String apellido, LocalDate fechaIngreso, Credencial credencial,
			LocalDate FechaCaducidad) {
		super(legajo, nombre, apellido, fechaIngreso, credencial);
		this.contrato = new Contrato(FechaCaducidad);
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
}
