package ar.com.cdt.formacion.consultorioOnline.models;

public class EstadoUsuario {

	
	private int idEstadoUsuario;	
    private String estado;

	public EstadoUsuario() {}
	
	public EstadoUsuario(int idEstadoUsuario, String estado) {
		this.idEstadoUsuario = idEstadoUsuario;
		this.estado = estado;
	}

	public int getIdEstadoUsuario() {
		return idEstadoUsuario;
	}

	public void setIdEstadoUsuario(int idEstadoUsuario) {
		this.idEstadoUsuario = idEstadoUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "EstadoUsuario [idEstadoUsuario=" + idEstadoUsuario + ", estado=" + estado + "]";
	}	
}
