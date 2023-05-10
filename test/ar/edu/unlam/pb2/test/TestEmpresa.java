package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
