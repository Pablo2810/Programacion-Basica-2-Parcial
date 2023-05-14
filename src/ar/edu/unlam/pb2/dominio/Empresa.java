package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Empresa {

	private String nombre;
	private HashSet<Puerta> puertas;
	private HashSet<Empleado> empleados;
	private HashSet<Credencial> credenciales;
	private ArrayList<Acceso> accesos;

	/********************************/
	
	public Empresa(String nombre) {
		this.nombre = nombre;
		this.puertas = new HashSet<Puerta>();
		this.empleados = new HashSet<Empleado>();
		this.credenciales = new HashSet<Credencial>();
		this.accesos = new ArrayList<Acceso>();
	}

	/********************************/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public HashSet<Puerta> getPuertas() {
		return puertas;
	}

	public void setPuertas(HashSet<Puerta> puertas) {
		this.puertas = puertas;
	}
	
	public HashSet<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(HashSet<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public HashSet<Credencial> getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(HashSet<Credencial> credenciales) {
		this.credenciales = credenciales;
	}

	public ArrayList<Acceso> getAccesos() {
		return accesos;
	}

	public void setAccesos(ArrayList<Acceso> accesos) {
		this.accesos = accesos;
	}
	
	/********************************/
	
	public void agregarPuerta(Puerta puerta) {
		puertas.add(puerta);
	}

	public void agregarEmpleado(Empleado empleado) {
		if (empleado instanceof Contratado) {
			if (((Contratado) empleado).getContrato().getFechaCaducidad().isAfter(empleado.getFechaIngreso())) {
				this.registrarCredencialesEmpleados(empleado);
			}
		} else {
			this.registrarCredencialesEmpleados(empleado);
		}
	}
	
	private void registrarCredencialesEmpleados(Empleado empleado) {
		if (!this.credenciales.contains(empleado.getCredencial())) {
			this.empleados.add(empleado);
			this.credenciales.add(empleado.getCredencial());
		} else {
			this.empleados.add(empleado);
		}
	}

	public void agregarCredencial(Credencial credencial) {
		credencial.setEstado(Estado.DESACTIVADA);
		this.credenciales.add(credencial);
	}
	
//	private void agregarCredencialActivada(Credencial credencial) {
//		credencial.setEstado(Estado.ACTIVADA);
//		this.credenciales.add(credencial);
//	}
	
	/********************************/
	
	public void asignarCredencialParaEmpleado(Empleado empleado, Credencial credencial) {
		if (empleado.getCredencial() == null) {
			empleado.setCredencial(credencial);
			credencial.setEstado(Estado.ACTIVADA);
		} else {
			System.out.println("El empleado ya tiene una credencial");
		}
	}

	public void eliminarEmpleadoYDesactivarCredencial(Empleado empleado) {
		for (Empleado empleadoObtenido : empleados) {
			if(empleadoObtenido.getLegajo().equals(empleado.getLegajo())) {
				for (Credencial credencial : credenciales) {
					if(credencial.getId().equals(empleado.getCredencial().getId())) {
						credencial.setEstado(Estado.DESACTIVADA);
						empleados.remove(empleado);
					}
				}
			}
		}
		
	}

	public void registrarAcceso(Empleado empleado, Puerta puerta) {
		if(empleado.getCredencial().buscarPermiso(puerta.getIdPuerta()) != null) {
			this.accesos.add(new Acceso(puerta,empleado,LocalDateTime.now()));
		}
	}

	// Agrega un permiso a una credencial especifica si la encuentra dentro del listado de credenciales.
	public void agregarPermisoACredencial(Integer permiso, Integer idCredencial) {
		if(buscarCredencialPorId(idCredencial) != null) {
			buscarCredencialPorId(idCredencial).agregarPermiso(permiso);
		}
	}

	// Busca una credencial por IdCredencial en el listado de credenciales.
	public Credencial buscarCredencialPorId(Integer idCredencial) {
		for (Credencial credencial : credenciales) {
			if(credencial.getId().equals(idCredencial)) {
				return credencial;
			}
		}
		return null;
	}

	public ArrayList<Acceso> obtenerListadoAccesosPorLegajoDeEmpleado(Integer legajo) {
		ArrayList<Acceso> listaAccesosFiltrada = new ArrayList<>();
		for (Acceso acceso : this.accesos) {
			if(acceso.getEmpleado().getLegajo().equals(legajo)) {
				listaAccesosFiltrada.add(acceso);
			}
		}
		return listaAccesosFiltrada;
	}
	
	public ArrayList<Acceso> obtenerListadoAccesosPorNroPuerta(Integer idPuerta) {
		ArrayList<Acceso> listaAccesos = new ArrayList<>();
		for (Acceso acceso : accesos) {
			if (acceso.getPuerta().getIdPuerta().equals(idPuerta)) {
				listaAccesos.add(acceso);
			}
		}
		return listaAccesos;

	}

	public ArrayList<Empleado> obtenerListaEmpleadosGenerales() {
		ArrayList<Empleado> listaEmpleadosGenerales = new ArrayList<Empleado>();
		for (Empleado empleado : empleados) {
			listaEmpleadosGenerales.add(empleado);
		}
		
		return listaEmpleadosGenerales;
	}

	public ArrayList<Empleado> obtenerListaEmpleadosEfectivos() {
		ArrayList<Empleado> listaEmpleadosEfectivos = new ArrayList<Empleado>();
		for (Empleado empleado : empleados) {
			if(empleado instanceof Efectivo) {
				listaEmpleadosEfectivos.add(empleado);
			}
		}
		return listaEmpleadosEfectivos;
	}

	public ArrayList<Empleado> obtenerListaEmpleadosContratados() {
		ArrayList<Empleado> listaEmpleadosContratados = new ArrayList<Empleado>();
		for (Empleado empleado : empleados) {
			if(empleado instanceof Contratado) {
				listaEmpleadosContratados.add(empleado);
			}
		}
		return listaEmpleadosContratados;
	}

	public Empleado obtenerEmpleadoPorLegajo(int legajo) {
		
		for (Empleado empleado : empleados) {
			if(empleado.getLegajo().equals(legajo)) {
				return empleado;
			}
		}
		return null;
	}

	public ArrayList<Empleado> obtenerListaEmpleadosEnFechaEspecifica(LocalDate fechaDeBusqueda) {
		ArrayList<Empleado> listaEmpleadosEnFechaEspecifica = new ArrayList<Empleado>();
		for (Empleado empleado : empleados) {
			if(empleado.getFechaIngreso().equals(fechaDeBusqueda)) {
				listaEmpleadosEnFechaEspecifica.add(empleado);
			}
		}
		return listaEmpleadosEnFechaEspecifica;
	}

	public ArrayList<Empleado> obtenerListadoEmpleadoPorPuerta(Puerta puerta) {
		ArrayList<Empleado> listaEmpleadosQueAccedieronPuertaEspecifica = new ArrayList<>();
		for (Acceso acceso : accesos) {
			if (acceso.getPuerta().equals(puerta)) {
				Empleado empleado = acceso.getEmpleado();
				if (!listaEmpleadosQueAccedieronPuertaEspecifica.contains(empleado)) {
					listaEmpleadosQueAccedieronPuertaEspecifica.add(empleado);
				}
			}
		}
		return listaEmpleadosQueAccedieronPuertaEspecifica;
	}
	

}
