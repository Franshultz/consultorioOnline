package ar.com.cdt.formacion.consultorioOnline.models;


public class EstadoTurno {

	private int idEstadoTurno;

    private String estado;


	public EstadoTurno() {}
	
	public EstadoTurno(int idEstadoTurno, String estado) {
		this.idEstadoTurno = idEstadoTurno;
		this.estado = estado;
	}
	
	public int getIdEstado() {
		return idEstadoTurno;
	}

	public void setIdEstado(int idEstado) {
		this.idEstadoTurno = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Estado [idEstado=" + idEstadoTurno + ", estado=" + estado + "]";
	}
}
