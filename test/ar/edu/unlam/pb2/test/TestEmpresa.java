package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Credencial;
import ar.edu.unlam.pb2.dominio.Efectivo;
import ar.edu.unlam.pb2.dominio.Empleado;
import ar.edu.unlam.pb2.dominio.Empresa;
import ar.edu.unlam.pb2.dominio.Puerta;

public class TestEmpresa {

	@Test
	public void queSePuedaAgregarUnaPuertaAUnaEmpresa() {
		int CANTIDAD_ESPERADA = 1;
		String nombre = "Queremos Aprobar";
		Empresa empresa = new Empresa(nombre);
		
		Integer idPuerta = 1;
		Puerta puerta = new Puerta(idPuerta);
		
		empresa.agregarPuerta(puerta);
		
		assertEquals(CANTIDAD_ESPERADA,empresa.getPuertas().size());
	}
	@Test
	public void queNoSePuedaAgregarDosPuertasConElMismoNumeroDePuertaAUnaEmpresa() {
		int CANTIDAD_ESPERADA = 1;
		String nombre = "Queremos Aprobar";
		Empresa empresa = new Empresa(nombre);
		
		Integer idPuerta = 1;
		Puerta puerta = new Puerta(idPuerta);
		Integer idPuerta2 = 1;
		Puerta puerta2 = new Puerta(idPuerta2);
		
		empresa.agregarPuerta(puerta);
		empresa.agregarPuerta(puerta2);
		
		assertEquals(CANTIDAD_ESPERADA,empresa.getPuertas().size());
	}
	
	@Test
	public void queSePuedaAgregarUnEmpleadoAUnaEmpresa() {
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		String obraSocial = "OSDE";

		Empresa empresa = new Empresa(nombre);
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
		empresa.agregarEmpleado(efectivo);
		int CANTIDAD_ESPERADA = 1;
		
		assertEquals(CANTIDAD_ESPERADA,empresa.getEmpleados().size());
	}
	
	@Test
	public void queNoSePuedanAgregarDosEmpleadosConElMismoLegajoAUnaEmpresa() {
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String nombreEmpleado2 = "Gonzalo";
		String apellido = "Quito";
		String apellido2 = "Gonzales";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		String obraSocial = "OSDE";
		int CANTIDAD_ESPERADA = 1;

		Empresa empresa = new Empresa(nombre);
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
		Empleado efectivo2 = new Efectivo(legajo, nombreEmpleado2, apellido2, fechaIngreso, credencial, obraSocial);
		empresa.agregarEmpleado(efectivo);
		empresa.agregarEmpleado(efectivo2);
		
		assertEquals(CANTIDAD_ESPERADA,empresa.getEmpleados().size());
	}
	
}
