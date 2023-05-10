package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;

public class Empresa {

	private String nombre;
	private HashSet<Puerta> puertas;

	public Empresa(String nombre) {
		this.nombre = nombre;
		this.puertas = new HashSet<Puerta>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregarPuerta(Puerta puerta) {
		puertas.add(puerta);
	}

	public HashSet<Puerta> getPuertas() {
		return puertas;
	}

	public void setPuertas(HashSet<Puerta> puertas) {
		this.puertas = puertas;
	}

}
