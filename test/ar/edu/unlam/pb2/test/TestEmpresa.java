package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Credencial;
import ar.edu.unlam.pb2.dominio.Efectivo;
import ar.edu.unlam.pb2.dominio.Empleado;
import ar.edu.unlam.pb2.dominio.Empresa;
import ar.edu.unlam.pb2.dominio.Estado;
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
		String obraSocial = "OSDE";

		Empresa empresa = new Empresa(nombre);
		Credencial credencial = new Credencial(idCredencial);
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
		empresa.agregarEmpleado(efectivo);
		int CANTIDAD_ESPERADA = 1;
		
		assertEquals(CANTIDAD_ESPERADA,empresa.getEmpleados().size());
	}
	
	@Test
	public void queSePuedaAgregarUnEmpleadoSinCredencialAUnaEmpresa() {
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		String obraSocial = "OSDE";

		Empresa empresa = new Empresa(nombre);
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, obraSocial);
		empresa.agregarEmpleado(efectivo);
		int CANTIDAD_ESPERADA = 1;
		
		assertEquals(CANTIDAD_ESPERADA,empresa.getEmpleados().size());
		assertEquals(0,empresa.getCredenciales().size());
	}
	
	@Test
	public void queNoSePuedanAgregarDosEmpleadosConElMismoLegajoYCredencialAUnaEmpresa() {
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String nombreEmpleado2 = "Gonzalo";
		String apellido = "Quito";
		String apellido2 = "Gonzales";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		Integer idCredencial = 1;
		String obraSocial = "OSDE";
		int CANTIDAD_ESPERADA = 1;

		Empresa empresa = new Empresa(nombre);
		Credencial credencial = new Credencial(idCredencial);
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
		Empleado efectivo2 = new Efectivo(legajo, nombreEmpleado2, apellido2, fechaIngreso, credencial, obraSocial);
		empresa.agregarEmpleado(efectivo);
		empresa.agregarEmpleado(efectivo2);
		
		assertEquals(CANTIDAD_ESPERADA,empresa.getEmpleados().size());
	}
	
	@Test
	public void queNoSePuedaAgregarUnEmpleadoConUnaCredencialAnteriorMenteRegistrada() {
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		Integer idCredencial = 1;
		String obraSocial = "OSDE";
		int CANTIDAD_EMPLEADOS_ESPERADOS = 0;
		int CANTIDAD_CREDENCIALES_ESPERADAS = 1;

		Empresa empresa = new Empresa(nombre);
		Credencial credencial = new Credencial(idCredencial);
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
		empresa.agregarCredencial(credencial);
		empresa.agregarEmpleado(efectivo);

		
		assertEquals(CANTIDAD_EMPLEADOS_ESPERADOS,empresa.getEmpleados().size());
		assertEquals(CANTIDAD_CREDENCIALES_ESPERADAS,empresa.getCredenciales().size());
		
		
	}
	
		@Test
	 	public void queSeLePuedaAsignarUnaCredencialAnteriormenteRegistradaAUnEmpleadoYActivarla() {
			String nombre = "Queremos Aprobar";
			Integer legajo = 123;
			String nombreEmpleado = "Esteban";
			String apellido = "Quito";
			LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
			String obraSocial = "OSDE";
			Integer idCredencial = 1;
			
			Empresa empresa = new Empresa(nombre);
			Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, obraSocial);
			Credencial credencial = new Credencial(idCredencial);
			empresa.agregarCredencial(credencial);
			empresa.asignarCredencialDesactivadaYActivarla(efectivo);
			Integer valorObtenido = efectivo.getCredencial().getId();
			Estado estadoEsperado = Estado.ACTIVADA;
			Estado estadoObtenido = efectivo.getCredencial().getEstado();
			

			assertEquals(idCredencial, valorObtenido);
			assertEquals(estadoEsperado, estadoObtenido);
			
		}


		@Test
		public void queSePuedaEliminarUnEmpleadoYSeDesactiveSuCrendecial() {
			
			String nombre = "Queremos Aprobar";
			Integer legajo = 123;
			String nombreEmpleado = "Esteban";
			String apellido = "Quito";
			LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
			Integer idCredencial = 1;
			String obraSocial = "OSDE";

			Empresa empresa = new Empresa(nombre);
			Credencial credencial = new Credencial(idCredencial);
			Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
			empresa.agregarEmpleado(efectivo);
			empresa.eliminarEmpleadoYDesactivarCredencial(efectivo);
			Estado estadoEsperado = Estado.DESACTIVADA;
			Estado estadoObtenido;
			Integer cantidadEsperada = 0;
			Integer valorObtenido = empresa.getEmpleados().size();
			for (Credencial credencialObtenida : empresa.getCredenciales()) {
				if(credencialObtenida.getId().equals(idCredencial)) {
					estadoObtenido = credencialObtenida.getEstado();
					assertEquals(estadoEsperado, estadoObtenido);
				}
			}
			
			assertEquals(cantidadEsperada, valorObtenido);
			
		}
			

	
	@Test
	public void queSePuedaAgregarUnaCredencialAUnaEmpresa() {
		Empresa empresa = new Empresa("Queremos aprobar");
		Credencial credencial = new Credencial(1);
		
		empresa.agregarCredencial(credencial);
		
		Integer valEsp = 1;
		Integer valObt = empresa.getCredenciales().size();
		
		assertEquals(valEsp, valObt);
	}
	
	
	@Test
	public void queNoSePuedaAgregarUnaCredencialConMismoID() {
		Empresa empresa = new Empresa("Queremos aprobar");
		Credencial credencial1 = new Credencial(1);
		Credencial credencial2 = new Credencial(2);
		Credencial credencial3 = new Credencial(1);
		
		empresa.agregarCredencial(credencial1);
		empresa.agregarCredencial(credencial2);
		empresa.agregarCredencial(credencial3);
		
		Integer valEsp = 2;
		Integer valObt = empresa.getCredenciales().size();
		
		assertEquals(valEsp, valObt);
	}
}
