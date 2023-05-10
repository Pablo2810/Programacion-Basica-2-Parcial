package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

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
}
