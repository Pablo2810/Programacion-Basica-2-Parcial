package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Acceso;
import ar.edu.unlam.pb2.dominio.Contratado;
import ar.edu.unlam.pb2.dominio.Contrato;
import ar.edu.unlam.pb2.dominio.Credencial;
import ar.edu.unlam.pb2.dominio.Efectivo;
import ar.edu.unlam.pb2.dominio.Empleado;
import ar.edu.unlam.pb2.dominio.Empresa;
import ar.edu.unlam.pb2.dominio.Puerta;

public class TestBasicos {

	@Test
	public void queSePuedaCrearUnaEmpresa() {
		String nombre = "Empresa";

		Empresa empresa = new Empresa(nombre);

		assertNotNull(empresa);
	}

	@Test
	public void queSePuedaCrearUnaCredencial() {
		Integer id = 1;

		Credencial credencial = new Credencial(id);

		assertNotNull(credencial);
	}

	@Test
	public void queSePuedaCrearUnEmpleadoEfectivo() {
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		String obraSocial = "OSDE";

		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);

		assertNotNull(efectivo);
	}

	@Test
	public void queSePuedaCrearUnaPuerta() {
		Integer idPuerta = 1;

		Puerta puerta = new Puerta(idPuerta);

		assertNotNull(puerta);
	}

	@Test
	public void queSePuedaCrearUnEmpleadoContratado() {
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		LocalDate fechaCaducidad = LocalDate.parse("2023-01-23");

		Empleado contratado = new Contratado(legajo, nombreEmpleado, apellido, fechaIngreso, credencial,
				fechaCaducidad);

		assertNotNull(contratado);
	}

	@Test
	public void queSePuedaCrearUnAcceso() {
		Integer idPuerta = 1;
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		String obraSocial = "OSDE";
		LocalDateTime fechaYHora = LocalDateTime.now();

		Puerta puerta = new Puerta(idPuerta);
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);

		Acceso acceso = new Acceso(puerta, efectivo, fechaYHora);

		assertNotNull(acceso);
	}
}
