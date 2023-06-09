package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
			if (this.verificarFechaCaducidad(empleado)) {
				if (!this.verificarCredencialDelEmpleado(empleado)) {
					Credencial credencial = buscarCredencialPorEstado(Estado.DESACTIVADA);
					asignarCredencial(credencial, empleado);
					this.empleados.add(empleado);
				}
			}
		} else {
			if (!this.verificarCredencialDelEmpleado(empleado)) {
				Credencial credencial = buscarCredencialPorEstado(Estado.DESACTIVADA);
				asignarCredencial(credencial, empleado);
				this.empleados.add(empleado);
			}
		}

		if (!this.credenciales.contains(empleado.getCredencial()) && empleado.getCredencial() != null) {
			agregarCredencial(empleado.getCredencial());
			asignarCredencial(empleado.getCredencial(), empleado);
		}
	}

	private Credencial buscarCredencialPorEstado(Estado desactivada) {
		for (Credencial credencial : credenciales) {
			if (credencial.getEstado().equals(desactivada)) {
				return credencial;
			}
		}
		return null;
	}

	private Boolean verificarCredencialDelEmpleado(Empleado empleado) {
		if (empleado.getCredencial() != null) {
			for (Empleado empleado2 : empleados) {
				if (empleado.getCredencial().equals(empleado2.getCredencial())) {
					return true;
				}
			}
		}
		return false;
	}

	private Boolean verificarFechaCaducidad(Empleado empleado) {
		Boolean fechaValida = false;
		if (((Contratado) empleado).getContrato().getFechaCaducidad().isAfter(empleado.getFechaIngreso())) {
			fechaValida = true;

		}
		return fechaValida;
	}

	// verifica que el contrato del empleado no este vencido. true -> si el contrato
	// está vigente.
	private Boolean verificarVencimientoDeContratoConFechaActual(Empleado empleado) {
		if (empleado instanceof Contratado) {
			if (((Contratado) empleado).getContrato().getFechaCaducidad().isAfter(LocalDate.now())) {
				return true;
			}
		}
		return false;
	}

	public void agregarCredencial(Credencial credencial) {
		credencial.setEstado(Estado.DESACTIVADA);
		this.credenciales.add(credencial);
	}

	/********************************/

	public void asignarCredencial(Credencial credencial, Empleado empleado) {
		if (empleado.getCredencial() == null) {
			if (buscarCredencialPorEstado(Estado.DESACTIVADA) != null) {
				empleado.setCredencial(credencial);
				credencial.setEstado(Estado.ACTIVADA);
			}
		}
	}

	public void eliminarEmpleadoYDesactivarCredencial(Empleado empleado) {
		for (Empleado empleadoObtenido : empleados) {
			if (empleadoObtenido.getLegajo().equals(empleado.getLegajo())) {
				for (Credencial credencial : credenciales) {
					if (credencial.getId().equals(empleado.getCredencial().getId())) {
						credencial.setEstado(Estado.DESACTIVADA);
						empleados.remove(empleado);
					}
				}
			}
		}

	}

	public void registrarAcceso(Empleado empleado, Puerta puerta) {
		if (verificarCredencialDelEmpleado(empleado)) {
			if (empleado.getCredencial().getEstado().equals(Estado.ACTIVADA)) {
				if (empleado.getCredencial().buscarPermiso(puerta.getIdPuerta()) != null) {
					if (empleado instanceof Contratado) {
						if (verificarVencimientoDeContratoConFechaActual(empleado)) {
							this.accesos.add(new Acceso(puerta, empleado, LocalDateTime.now()));
						}
					}
					if (empleado instanceof Efectivo) {
						this.accesos.add(new Acceso(puerta, empleado, LocalDateTime.now()));
					}
				}
			}
		}
	}

	// Agrega un permiso a una credencial especifica si la encuentra dentro del
	// listado de credenciales.
	public void agregarPermisoACredencial(Integer permiso, Integer idCredencial) {
		if (buscarCredencialPorId(idCredencial) != null) {
			buscarCredencialPorId(idCredencial).agregarPermiso(permiso);
		}
	}

	// Busca una credencial por IdCredencial en el listado de credenciales.
	public Credencial buscarCredencialPorId(Integer idCredencial) {
		for (Credencial credencial : credenciales) {
			if (credencial.getId().equals(idCredencial)) {
				return credencial;
			}
		}
		return null;
	}

	public ArrayList<Acceso> obtenerListadoAccesosPorLegajoDeEmpleado(Integer legajo) {
		ArrayList<Acceso> listaAccesosFiltrada = new ArrayList<>();
		for (Acceso acceso : this.accesos) {
			if (acceso.getEmpleado().getLegajo().equals(legajo)) {
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
			if (empleado instanceof Efectivo) {
				listaEmpleadosEfectivos.add(empleado);
			}
		}
		return listaEmpleadosEfectivos;
	}

	public ArrayList<Empleado> obtenerListaEmpleadosContratados() {
		ArrayList<Empleado> listaEmpleadosContratados = new ArrayList<Empleado>();
		for (Empleado empleado : empleados) {
			if (empleado instanceof Contratado) {
				listaEmpleadosContratados.add(empleado);
			}
		}
		return listaEmpleadosContratados;
	}

	public Empleado obtenerEmpleadoPorLegajo(int legajo) {

		for (Empleado empleado : empleados) {
			if (empleado.getLegajo().equals(legajo)) {
				return empleado;
			}
		}
		return null;
	}

	public ArrayList<Empleado> obtenerListaEmpleadosEnFechaEspecifica(LocalDate fechaDeBusqueda) {
		ArrayList<Empleado> listaEmpleadosEnFechaEspecifica = new ArrayList<Empleado>();
		for (Empleado empleado : empleados) {
			if (empleado.getFechaIngreso().equals(fechaDeBusqueda)) {
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
				// if (!listaEmpleadosQueAccedieronPuertaEspecifica.contains(empleado)) { // no
				// debe filtrar el empleado porque el acceso tiene un PK compuesta
				// (puerta,empleado y fecha-hora)
				listaEmpleadosQueAccedieronPuertaEspecifica.add(empleado);
				// }
			}
		}
		return listaEmpleadosQueAccedieronPuertaEspecifica;
	}

}
