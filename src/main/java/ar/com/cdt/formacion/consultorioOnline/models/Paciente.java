package ar.com.cdt.formacion.consultorioOnline.models;

import java.time.LocalDate;


public class Paciente extends Usuario{

	int fk_usuario;
	private int idPaciente;
	private Usuario usuario;
	private String cobertura;

	public Paciente() {
		super();
	};

	public Paciente(String nombre, String apellido, String email, String clave, int dni,
				  LocalDate fechaNacimiento, int fk_genero, int fk_estado_usuario, String cobertura) {
		super(nombre, apellido, email, clave, dni, fechaNacimiento, fk_genero, fk_estado_usuario);
		this.cobertura = cobertura;
	}

	public Paciente(int fk_usuario, String nombre, String apellido, String email, String clave, int dni,
			LocalDate fechaNacimiento, int fk_genero, int fk_estado_usuario, String cobertura) {
		super(nombre, apellido, email, clave, dni, fechaNacimiento, fk_genero, fk_estado_usuario);
		this.fk_usuario = fk_usuario;
		this.cobertura = cobertura;
	}

	public int getFk_usuario() {
		return fk_usuario;
	}

	public void setFk_usuario(int fk_usuario) {
		this.fk_usuario = fk_usuario;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCobertura() {
		return cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	@Override
	public String toString() {
		return "Paciente [usuario=" + usuario + ", cobertura=" + cobertura + "]";
	}
}
