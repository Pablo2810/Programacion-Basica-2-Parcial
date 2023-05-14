package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

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
		String nombre = "Queremos Aprobar";
		Empresa empresa = new Empresa(nombre);
		
		Integer idPuerta = 1;
		Puerta puerta = new Puerta(idPuerta);
		
		empresa.agregarPuerta(puerta);
		
		assertEquals(1 , empresa.getPuertas().size());
	}
	
	@Test
	public void queNoSePuedaAgregarDosPuertasConElMismoNumeroDePuertaAUnaEmpresa() {
		String nombre = "Queremos Aprobar";
		Empresa empresa = new Empresa(nombre);
	
		Puerta puerta = new Puerta(1);
		Puerta puerta2 = new Puerta(1);
		
		empresa.agregarPuerta(puerta);
		empresa.agregarPuerta(puerta2);
		
		assertEquals(1 , empresa.getPuertas().size());
	}
	
	@Test
	public void queSePuedaAgregarUnEmpleadoAUnaEmpresa() {
		String nombre = "Queremos Aprobar";
		Empresa empresa = new Empresa(nombre);
		
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		String obraSocial = "OSDE";
		Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);
		
		empresa.agregarEmpleado(efectivo);
		
		assertEquals(1 , empresa.getEmpleados().size());
	}
	
	@Test
	public void queNoSePuedaAgregarUnEmpleadoContratadoConUnaFechaDeCaducidadInvalida() {
		Empresa empresa = new Empresa("Queremos aprobar");
		
		Empleado contratado1 = new Contratado(1, "Carlos", "Lopez", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-12-20"));
		Empleado contratado2 = new Contratado(2, "Esteban", "Lopez", LocalDate.parse("2023-10-10"), LocalDate.parse("2023-09-09"));
		
		empresa.agregarEmpleado(contratado1);
		empresa.agregarEmpleado(contratado2); // X
		
		Integer valEsp = 1;
		Integer valObt = empresa.getEmpleados().size();
		
		assertEquals(valEsp, valObt);
	}
	
	@Test
	public void queSePuedaAgregarUnEmpleadoSinCredencialAUnaEmpresa() {
		Empresa empresa = new Empresa("Queremos Aprobar");
		Empleado efectivo = new Efectivo(123, "Esteban", "Quito", LocalDate.parse("2023-01-01"), "OSDE");
		
		empresa.agregarEmpleado(efectivo);
		
		assertEquals(1, empresa.getEmpleados().size());
		assertEquals(0, empresa.getCredenciales().size());
	}
	
	@Test
	public void queNoSePuedanAgregarDosEmpleadosConElMismoLegajo() {
		Empresa empresa = new Empresa("Queremos Aprobar");
		
		Credencial credencial = new Credencial(1);
		Credencial otraCredencial = new Credencial(2);
		
		Empleado efectivo1 = new Efectivo(1, "Esteban", "Quito", LocalDate.parse("2023-01-01"), credencial, "OSDE");
		Empleado efectivo2 = new Efectivo(1, "Gonzalo", "Gonzales", LocalDate.parse("2023-01-01"), otraCredencial, "OSDE");
		
		empresa.agregarEmpleado(efectivo1);
		empresa.agregarEmpleado(efectivo2); // X
		
		assertEquals(1,empresa.getEmpleados().size());
	}
	
	@Test
	public void queNoSePuedanAgregarDosEmpleadosConLaMismaCredencial() {
		Empresa empresa = new Empresa("Queremos Aprobar");
		
		Credencial credencial = new Credencial(1);
		
		Empleado efectivo1 = new Efectivo(1, "Esteban", "Quito", LocalDate.parse("2023-01-01"), credencial, "OSDE");
		Empleado efectivo2 = new Efectivo(2, "Gonzalo", "Gonzales", LocalDate.parse("2023-01-01"), credencial, "OSDE");
		
		empresa.agregarEmpleado(efectivo1);
		empresa.agregarEmpleado(efectivo2); // X
		
		assertEquals(1,empresa.getEmpleados().size());
	}
	
	@Test
	public void queNoSePuedaAgregarUnaCredencialAnteriorMenteRegistrada() {
		Empresa empresa = new Empresa("Queremos Aprobar");
		
		Credencial credencial = new Credencial(1);
		
		Empleado efectivoA = new Efectivo(123, "Esteban", "Quito", LocalDate.parse("2023-01-01"), credencial, "OSDE");
		
		empresa.agregarEmpleado(efectivoA);
		empresa.agregarCredencial(credencial); // Esto no lo agrega por el metodo "agregarEmpleado"

		assertEquals(1,empresa.getCredenciales().size());
	}
	
		@Test
	 	public void queSeLePuedaAsignarUnaCredencialAnteriormenteRegistradaAUnEmpleadoYActivarla() {
			Empresa empresa = new Empresa("Queremos Aprobar");
			
			Credencial credencial = new Credencial(1);
			
			Empleado efectivoA = new Efectivo(123, "Esteban", "Quito", LocalDate.parse("2023-01-01"), credencial, "OSDE");
			Empleado efectivoB = new Efectivo(456, "A", "A", LocalDate.parse("2023-01-01"), "UP");
			
			empresa.agregarCredencial(credencial);
			
			assertNull(efectivoB.getCredencial());
			empresa.asignarCredencialParaEmpleado(efectivoB, credencial); // Aca ACTIVA la credencial
			assertNotNull(efectivoB.getCredencial());
			
			Estado estadoEsperado = Estado.ACTIVADA;
			Estado estadoObtenido = efectivoA.getCredencial().getEstado();
			
			assertEquals(estadoEsperado, estadoObtenido);
		}


		@Test
		public void queSePuedaEliminarUnEmpleadoYSeDesactiveSuCrendecial() {
			String nombre = "Queremos Aprobar";
			Empresa empresa = new Empresa(nombre);
			
			Credencial credencial = new Credencial(1);
			
			Integer legajo = 123;
			String nombreEmpleado = "Esteban";
			String apellido = "Quito";
			LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
			String obraSocial = "OSDE";
			Empleado efectivo = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial, obraSocial);

			empresa.agregarEmpleado(efectivo);
			empresa.eliminarEmpleadoYDesactivarCredencial(efectivo);
			
			Estado estadoEsperado = Estado.DESACTIVADA;
			Estado estadoObtenido;
			Integer cantidadEsperada = 0;
			Integer valorObtenido = empresa.getEmpleados().size();
			for (Credencial credencialObtenida : empresa.getCredenciales()) {
				if(credencialObtenida.getId().equals(credencial.getId())) {
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
		empresa.agregarCredencial(credencial3); // X
		
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
		empresa.agregarCredencial(efectivo.getCredencial());
		empresa.agregarPermisoACredencial(idPuerta, idCredencial);
		
		empresa.registrarAcceso(efectivo,puerta);
		
		assertEquals(1,empresa.getCredenciales().size());
		assertEquals(1,empresa.getEmpleados().size());
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
		empresa.agregarPermisoACredencial(idPuerta, idCredencial);
		
		empresa.registrarAcceso(efectivo,puerta);
		
		assertEquals(0,empresa.getAccesos().size());
	}
	@Test
	public void queNoSePuedaRegistrarUnAccesoEnLaEmpresaCuandoElEmpleadoContratadoTieneElContratoVencido() {
		Empresa empresa = new Empresa("Queremos aprobar");
		
		Integer idCredencial = 1;
		Credencial credencial = new Credencial(idCredencial);
		
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		LocalDate fechaDeCaducidad = LocalDate.parse("2023-04-01");
		Empleado contratado = new Contratado(legajo, nombreEmpleado, apellido, fechaIngreso, credencial,fechaDeCaducidad);
		
		Integer idPuerta = 3;
		Puerta puerta = new Puerta(idPuerta);
		
		empresa.agregarPuerta(puerta);
		empresa.agregarEmpleado(contratado);
		empresa.agregarCredencial(contratado.getCredencial());
		empresa.agregarPermisoACredencial(idPuerta, idCredencial);
		
		empresa.registrarAcceso(contratado,puerta);
		
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
		
		assertEquals(valEsp, valObt);
	}
	
	@Test
	public void queSePuedaObtenerUnaListaDeEmpleadosGenerales() {
		
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		LocalDate fechaEgreso = LocalDate.parse("2023-01-04");
		String obraSocial = "OSDE";

		Empresa empresa = new Empresa(nombre);
		Credencial credencial1 = new Credencial(1);
		Credencial credencial2 = new Credencial(2);
		Credencial credencial3 = new Credencial(3);
		Credencial credencial4 = new Credencial(4);
		Empleado efectivo1 = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial1, obraSocial);
		Empleado efectivo2 = new Efectivo(132, nombreEmpleado, apellido, fechaIngreso, credencial2, obraSocial);
		Empleado contratado1 = new Contratado(213, nombreEmpleado, apellido, fechaIngreso, credencial3, fechaEgreso);
		Empleado contratado2 = new Contratado(312, nombreEmpleado, apellido, fechaIngreso, credencial4, fechaEgreso);
		empresa.agregarEmpleado(efectivo1);
		empresa.agregarEmpleado(efectivo2);
		empresa.agregarEmpleado(contratado1);
		empresa.agregarEmpleado(contratado2);
		ArrayList<Empleado> listaEmpleadosGenerales = empresa.obtenerListaEmpleadosGenerales();
		int valorEsperado = 4;
		
		assertEquals(valorEsperado, listaEmpleadosGenerales.size());
		
		
	}
	
	@Test
	public void queSePuedaObtenerUnaListaDeEmpleadosEfectivos() {
		
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		LocalDate fechaEgreso = LocalDate.parse("2023-01-04");
		String obraSocial = "OSDE";

		Empresa empresa = new Empresa(nombre);
		Credencial credencial1 = new Credencial(1);
		Credencial credencial2 = new Credencial(2);
		Credencial credencial3 = new Credencial(3);
		Credencial credencial4 = new Credencial(4);
		Credencial credencial5 = new Credencial(5);
		Empleado efectivo1 = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial1, obraSocial);
		Empleado efectivo2 = new Efectivo(132, nombreEmpleado, apellido, fechaIngreso, credencial2, obraSocial);
		Empleado efectivo3 = new Efectivo(231, nombreEmpleado, apellido, fechaIngreso, credencial5, obraSocial);
		Empleado contratado1 = new Contratado(213, nombreEmpleado, apellido, fechaIngreso, credencial3, fechaEgreso);
		Empleado contratado2 = new Contratado(312, nombreEmpleado, apellido, fechaIngreso, credencial4, fechaEgreso);
		empresa.agregarEmpleado(efectivo1);
		empresa.agregarEmpleado(efectivo2);
		empresa.agregarEmpleado(efectivo3);
		empresa.agregarEmpleado(contratado1);
		empresa.agregarEmpleado(contratado2);
		ArrayList<Empleado> listaEmpleadosEfectivos = empresa.obtenerListaEmpleadosEfectivos();
		int valorEsperado = 3;
		
		assertEquals(valorEsperado, listaEmpleadosEfectivos.size());
		
		
	}
	
	@Test
	public void queSePuedaObtenerUnaListaDeEmpleadosContratados() {
		
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		LocalDate fechaEgreso = LocalDate.parse("2023-01-04");
		String obraSocial = "OSDE";

		Empresa empresa = new Empresa(nombre);
		Credencial credencial1 = new Credencial(1);
		Credencial credencial2 = new Credencial(2);
		Credencial credencial3 = new Credencial(3);
		Credencial credencial4 = new Credencial(4);
		Credencial credencial5 = new Credencial(5);
		Empleado efectivo1 = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial1, obraSocial);
		Empleado efectivo2 = new Efectivo(132, nombreEmpleado, apellido, fechaIngreso, credencial2, obraSocial);
		Empleado contratado1 = new Contratado(213, nombreEmpleado, apellido, fechaIngreso, credencial3, fechaEgreso);
		Empleado contratado2 = new Contratado(312, nombreEmpleado, apellido, fechaIngreso, credencial4, fechaEgreso);
		Empleado contratado3 = new Contratado(231, nombreEmpleado, apellido, fechaIngreso, credencial5, fechaEgreso);
		empresa.agregarEmpleado(efectivo1);
		empresa.agregarEmpleado(efectivo2);
		empresa.agregarEmpleado(contratado1);
		empresa.agregarEmpleado(contratado2);
		empresa.agregarEmpleado(contratado3);
		ArrayList<Empleado> listaEmpleadosContratados = empresa.obtenerListaEmpleadosContratados();
		int valorEsperado = 3;
		
		assertEquals(valorEsperado, listaEmpleadosContratados.size());
		
		
	}
	
	@Test
	public void queSePuedaObtenerUnEmpleadoPorSuLegajo() {
		
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		LocalDate fechaEgreso = LocalDate.parse("2023-01-04");
		String obraSocial = "OSDE";

		Empresa empresa = new Empresa(nombre);
		Credencial credencial1 = new Credencial(1);
		Credencial credencial2 = new Credencial(2);
		Credencial credencial3 = new Credencial(3);
		Credencial credencial4 = new Credencial(4);
		Credencial credencial5 = new Credencial(5);
		Empleado efectivo1 = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial1, obraSocial);
		Empleado efectivo2 = new Efectivo(132, nombreEmpleado, apellido, fechaIngreso, credencial2, obraSocial);
		Empleado contratado1 = new Contratado(213, nombreEmpleado, apellido, fechaIngreso, credencial3, fechaEgreso);
		Empleado contratado2 = new Contratado(312, "Juan", apellido, fechaIngreso, credencial4, fechaEgreso);
		Empleado contratado3 = new Contratado(231, nombreEmpleado, apellido, fechaIngreso, credencial5, fechaEgreso);
		empresa.agregarEmpleado(efectivo1);
		empresa.agregarEmpleado(efectivo2);
		empresa.agregarEmpleado(contratado1);
		empresa.agregarEmpleado(contratado2);
		empresa.agregarEmpleado(contratado3);
		Empleado empleadoObtenido = empresa.obtenerEmpleadoPorLegajo(312);
		int valorEsperado = 312;
		
		assertEquals(empleadoObtenido.getNombre(), "Juan");
		assertEquals(valorEsperado, 312);
		
	}
	
	@Test
	public void queSePuedaObtenerUnaListaDeEmpleadosIngresadosEnUnaFechaEspecifica() {
		
		String nombre = "Queremos Aprobar";
		Integer legajo = 123;
		String nombreEmpleado = "Esteban";
		String apellido = "Quito";
		LocalDate fechaIngreso = LocalDate.parse("2023-01-01");
		LocalDate fechaIngreso2 = LocalDate.parse("2022-01-01");
		LocalDate fechaIngreso3 = LocalDate.parse("2022-01-01");
		LocalDate fechaIngreso4 = LocalDate.parse("2022-01-01");
		LocalDate fechaEgreso = LocalDate.parse("2023-01-04");
		LocalDate fechaDeBusqueda = LocalDate.parse("2022-01-01");
		String obraSocial = "OSDE";

		Empresa empresa = new Empresa(nombre);
		Credencial credencial1 = new Credencial(1);
		Credencial credencial2 = new Credencial(2);
		Credencial credencial3 = new Credencial(3);
		Credencial credencial4 = new Credencial(4);
		Empleado efectivo1 = new Efectivo(legajo, nombreEmpleado, apellido, fechaIngreso, credencial1, obraSocial);
		Empleado efectivo2 = new Efectivo(132, nombreEmpleado, apellido, fechaIngreso2, credencial2, obraSocial);
		Empleado contratado1 = new Contratado(213, nombreEmpleado, apellido, fechaIngreso3, credencial3, fechaEgreso);
		Empleado contratado2 = new Contratado(312, nombreEmpleado, apellido, fechaIngreso4, credencial4, fechaEgreso);
		empresa.agregarEmpleado(efectivo1);
		empresa.agregarEmpleado(efectivo2);
		empresa.agregarEmpleado(contratado1);
		empresa.agregarEmpleado(contratado2);
		ArrayList<Empleado> listaEmpleadosEnFechaEspecifica = empresa.obtenerListaEmpleadosEnFechaEspecifica(fechaDeBusqueda);
		int valorEsperado = 3;
		
		assertEquals(valorEsperado, listaEmpleadosEnFechaEspecifica.size());
		
		
	}
	
	@Test
	public void queSePuedaObtenerUnaListaDeAccesosDeEmpleadosPorUnaPuertaEspecifica() {
		Empresa empresa = new Empresa("Queremos aprobar");
		
		Credencial cred1 = new Credencial(1);
		Credencial cred2 = new Credencial(2);
		Credencial cred3 = new Credencial(3);
		Credencial cred4 = new Credencial(4);
		
		Puerta puerta1 = new Puerta(1);
		Puerta puerta2 = new Puerta(2);
		Puerta puerta3 = new Puerta(3);
		Puerta puerta4 = new Puerta(4);
		
		Empleado emp1 = new Efectivo(1, "David", "A", LocalDate.parse("2023-01-02"), cred1, "UP");
		Empleado emp2 = new Efectivo(2, "Mirtha", "B", LocalDate.parse("2023-02-16"), cred2, "UP");
		Empleado emp3 = new Contratado(3, "Carlos", "C", LocalDate.parse("2023-03-21"), cred3, LocalDate.parse("2023-10-10"));
		Empleado emp4 = new Contratado(4, "Luna", "D", LocalDate.parse("2023-04-12"), cred4, LocalDate.parse("2023-10-10"));
		
		empresa.agregarEmpleado(emp1); 
		empresa.agregarEmpleado(emp2);
		empresa.agregarEmpleado(emp3);
		empresa.agregarEmpleado(emp4);

		//Si no agregamos este permiso de credencial la "puerta1" si la ponemos de parametro la lista estara vacia. No tiene permiso.
		//empresa.agregarPermisoACredencial(puerta1.getIdPuerta(), cred1.getId()); 
		empresa.agregarPermisoACredencial(puerta2.getIdPuerta(), cred1.getId());
		empresa.agregarPermisoACredencial(puerta2.getIdPuerta(), cred2.getId());
		empresa.agregarPermisoACredencial(puerta2.getIdPuerta(), cred3.getId());
		
		empresa.registrarAcceso(emp1, puerta1);
		empresa.registrarAcceso(emp2, puerta2);
		empresa.registrarAcceso(emp3, puerta3);
		empresa.registrarAcceso(emp4, puerta4);
		empresa.registrarAcceso(emp1, puerta2);
		empresa.registrarAcceso(emp3, puerta2);
		empresa.registrarAcceso(emp2, puerta2);
		
		ArrayList<Empleado> listaEmpleadosQueAccedieronPuerta = empresa.obtenerListadoEmpleadoPorPuerta(puerta2);
		
		
		Integer valEsp = 3;
		Integer valObt = listaEmpleadosQueAccedieronPuerta.size();
		
		assertEquals(valEsp, valObt);
	}
}
