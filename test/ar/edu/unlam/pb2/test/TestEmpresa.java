package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;


import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Acceso;

import ar.edu.unlam.pb2.dominio.Contratado;

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
//		String nombre = "Queremos Aprobar";
//		Integer legajo = 123;
//		String nombreEmpleado = "Esteban";
//		String apellido = "Quito";
//		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
//		String obraSocial = "OSDE";
		
		Empresa empresa = new Empresa("Queremos Aprobar");
		Empleado efectivo = new Efectivo(123, "Esteban", "Quito", LocalDate.parse("2023-01-01"), "OSDE");
		
		empresa.agregarEmpleado(efectivo);
		int CANTIDAD_ESPERADA = 1;
		
		assertEquals(CANTIDAD_ESPERADA,empresa.getEmpleados().size());
		assertFalse(empresa.getCredenciales().isEmpty());
	}
	
	@Test
	public void queNoSePuedanAgregarDosEmpleadosConElMismoLegajoYCredencialAUnaEmpresa() {
//		String nombre = "Queremos Aprobar";
//		Integer legajo = 123;
//		String nombreEmpleado = "Esteban";
//		String nombreEmpleado2 = "Gonzalo";
//		String apellido = "Quito";
//		String apellido2 = "Gonzales";
//		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
//		Integer idCredencial = 1;
//		String obraSocial = "OSDE";
//		int CANTIDAD_ESPERADA = 1;
		Empresa empresa = new Empresa("Queremos Aprobar");
		
		Credencial credencial = new Credencial(1);
		
		Empleado efectivo = new Efectivo(123, "Esteban", "Quito", LocalDate.parse("2023-01-01"), credencial, "OSDE");
		Empleado efectivo2 = new Efectivo(123, "Gonzalo", "Gonzales", LocalDate.parse("2023-01-01"), credencial, "OSDE");
		
		empresa.agregarEmpleado(efectivo);
		empresa.agregarEmpleado(efectivo2);
		
		assertEquals(1,empresa.getEmpleados().size());
	}
	
	@Test
	public void queNoSePuedaAgregarUnEmpleadoConUnaCredencialAnteriorMenteRegistrada() {
//		String nombre = "Queremos Aprobar";
//		Integer legajo = 123;
//		String nombreEmpleado = "Esteban";
//		String apellido = "Quito";
//		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
//		Integer idCredencial = 1;
//		String obraSocial = "OSDE";
//		int CANTIDAD_EMPLEADOS_ESPERADOS = 0;
//		int CANTIDAD_CREDENCIALES_ESPERADAS = 1;

		Empresa empresa = new Empresa("Queremos Aprobar");
		
		Credencial credencial = new Credencial(1);
		
		Empleado efectivoA = new Efectivo(123, "Esteban", "Quito", LocalDate.parse("2023-01-01"), credencial, "OSDE");
		Empleado efectivoB = new Efectivo(456, "A", "A", LocalDate.parse("2023-01-01"), "UP");
		
		empresa.agregarCredencial(credencial);
		empresa.agregarEmpleado(efectivoA);
		//empresa.agregarEmpleado(efectivoB);

		assertEquals(0,empresa.getEmpleados().size());
		assertEquals(1,empresa.getCredenciales().size());
		
		
	}
	
		@Test
	 	public void queSeLePuedaAsignarUnaCredencialAnteriormenteRegistradaAUnEmpleadoYActivarla() {
//			String nombre = "Queremos Aprobar";
//			Integer legajo = 123;
//			String nombreEmpleado = "Esteban";
//			String apellido = "Quito";
//			LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
//			String obraSocial = "OSDE";
//			Integer idCredencial = 1;
			
			Empresa empresa = new Empresa("Queremos Aprobar");
			
			Credencial credencial = new Credencial(1);
			
			Empleado efectivoA = new Efectivo(123, "Esteban", "Quito", LocalDate.parse("2023-01-01"), credencial, "OSDE");
			Empleado efectivoB = new Efectivo(456, "A", "A", LocalDate.parse("2023-01-01"), "UP");
			
			empresa.agregarCredencial(credencial);
			
			empresa.asignarCredencialParaEmpleado(efectivoB, credencial);
			
			Integer valorObtenido = efectivoB.getCredencial().getId(); //1
			
			Estado estadoEsperado = Estado.ACTIVADA;
			Estado estadoObtenido = efectivoA.getCredencial().getEstado();
			
			assertEquals(credencial.getId(), valorObtenido);
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
	
	@Test
	public void queSePuedaAgregarUnPermisoEnLaCredencial() {
		Empresa empresa = new Empresa("Queremos aprobar");
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		Integer permiso = 1;
		
		empresa.agregarCredencial(credencial);
		empresa.agregarPermisoACredencial(permiso,idCredencial);
		
		assertEquals(1,empresa.buscarCredencialPorId(idCredencial).getPermisos().size());
	}
	
	@Test
	public void queSePuedaRegistrarUnAccesoEnLaEmpresa() {
		Empresa empresa = new Empresa("Queremos aprobar");
		
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		String obraSocial = "OSDE";
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
		
		Integer idPuerta = 1;
		Puerta puerta = new Puerta(idPuerta);
		
		empresa.agregarPuerta(puerta);
		empresa.agregarEmpleado(efectivo);
		empresa.agregarCredencial(credencial);
		empresa.agregarPermisoACredencial(idPuerta, idCredencial);
		
		empresa.registrarAcceso(efectivo,puerta);
		
		assertEquals(1,empresa.getAccesos().size());
	}
	@Test
	public void queNoSePuedaRegistrarUnAccesoEnLaEmpresaCuandoLaCredencialDelEmpleadoNoTienePermisoParaAccederAUnaPuerta() {
		Empresa empresa = new Empresa("Queremos aprobar");
		
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		String obraSocial = "OSDE";
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
		
		Integer idPuerta = 1;
		Puerta puerta = new Puerta(idPuerta);
		
		empresa.agregarPuerta(puerta);
		empresa.agregarEmpleado(efectivo);
		empresa.agregarCredencial(efectivo.getCredencial());
		
		empresa.registrarAcceso(efectivo,puerta);
		
		assertEquals(0,empresa.getAccesos().size());
	}
	
	@Test
	public void queSePuedaObtenerUnListadoDeAccesosDeLaEmpresaPorEmpleado() {
		Empresa empresa = new Empresa("Queremos aprobar");
		Puerta puerta1 = new Puerta(1);
		Puerta puerta2 = new Puerta(2);
		Credencial credencial = new Credencial(1);
		
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		String obraSocial = "OSDE";
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, obraSocial);
		
		empresa.agregarPuerta(puerta1);
		empresa.agregarPuerta(puerta2);
		empresa.agregarCredencial(credencial);
		empresa.agregarEmpleado(efectivo);
		empresa.asignarCredencialParaEmpleado(efectivo, credencial);
		empresa.agregarPermisoACredencial(2, 1);
		
		empresa.registrarAcceso(efectivo,puerta1); // no se registra porque no tiene permiso para esta puerta.
		empresa.registrarAcceso(efectivo,puerta2);
		
		int CANTIDAD_DE_ACCESOS_ESPERADOS = 1;
		ArrayList<Acceso> accesosObtenidos = empresa.obtenerListadoAccesosPorLegajoDeEmpleado(efectivo.getLegajo());
		
		assertEquals(CANTIDAD_DE_ACCESOS_ESPERADOS,accesosObtenidos.size());
	}


	public void queSePuedaObtenerUnListadoDeAccesosPorNumeroDePuerta() {
		Empresa empresa = new Empresa("Queremos Aprobar");
		
		Puerta puertaA = new Puerta(1);
		Puerta puertaB = new Puerta(2);
		Puerta puertaC = new Puerta(3);
		Puerta puertaD = new Puerta(4);
		
		Credencial credencial = new Credencial(1);
		
		Empleado efectivo = new Efectivo(123, "Esteban", "Quito", LocalDate.parse("2023-01-01"), credencial, "OSDE");
		Empleado contratado = new Contratado(1, "Carlos", "Lopez", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-02"));
		
		empresa.agregarPuerta(puertaA);
		empresa.agregarPuerta(puertaB);
		empresa.agregarPuerta(puertaC);
		empresa.agregarPuerta(puertaD);
		
		empresa.agregarCredencial(credencial);
		
		empresa.agregarPermisoACredencial(puertaA.getIdPuerta(), credencial.getId());
		empresa.agregarPermisoACredencial(puertaB.getIdPuerta(), credencial.getId());
		empresa.agregarPermisoACredencial(puertaC.getIdPuerta(), credencial.getId());
		empresa.agregarPermisoACredencial(puertaD.getIdPuerta(), credencial.getId());
		
		empresa.agregarEmpleado(efectivo);
		empresa.agregarEmpleado(contratado);
		
		empresa.asignarCredencialParaEmpleado(contratado, credencial);
		
		empresa.registrarAcceso(efectivo,puertaA);
		empresa.registrarAcceso(contratado, puertaB);
		empresa.registrarAcceso(contratado, puertaA);
		empresa.registrarAcceso(efectivo, puertaC);
		empresa.registrarAcceso(efectivo, puertaA);
		empresa.registrarAcceso(efectivo, puertaB);
		
		ArrayList<Acceso> listaAccesosPorPuerta = empresa.obtenerListadoAccesosPorNroPuerta(2);
		
		Integer valEsp = 2;
		Integer valObt = listaAccesosPorPuerta.size();
		
//		for (Acceso acceso : listaAccesosPorPuerta) {
//			System.out.println(acceso.getEmpleado().getApellido());
//		}
		
		assertEquals(valEsp, valObt);
	}

}
