package ar.edu.unlam.pb2.dominio;

public class Puerta {

	private Integer idPuerta;

	public Puerta(Integer idPuerta) {

		this.idPuerta = idPuerta;
	}

	public Integer getIdPuerta() {
		return idPuerta;
	}

	public void setIdPuerta(Integer idPuerta) {
		this.idPuerta = idPuerta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPuerta == null) ? 0 : idPuerta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Puerta other = (Puerta) obj;
		if (idPuerta == null) {
			if (other.idPuerta != null)
				return false;
		} else if (!idPuerta.equals(other.idPuerta))
			return false;
		return true;
	}

}
