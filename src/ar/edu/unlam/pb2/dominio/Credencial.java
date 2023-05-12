package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.Objects;

public class Credencial {
	
	private Integer id;
	private Estado estado;
	private HashSet<Integer> permisos;
	
	public Credencial(Integer id) {
		this.id = id;
		this.permisos = new HashSet<Integer>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public HashSet<Integer> getPermisos() {
		return permisos;
	}

	public void setPermisos(HashSet<Integer> permisos) {
		this.permisos = permisos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credencial other = (Credencial) obj;
		return Objects.equals(id, other.id);
	}

	// Agrega un permiso pasado como argumento, al listado de permisos de la credencial
	public void agregarPermiso(Integer permiso) {
		this.permisos.add(permiso);
	}
	
	public Integer buscarPermiso(Integer permiso) {
		for (Integer p : permisos) {
			if(p.equals(permiso)) {
				return p;
			}
		}
		return null;
	}
}
