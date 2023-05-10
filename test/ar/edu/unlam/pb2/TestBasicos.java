package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

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
}
