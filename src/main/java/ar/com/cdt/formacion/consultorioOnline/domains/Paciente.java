package ar.com.cdt.formacion.consultorioOnline.domains;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;	


public class Paciente extends Usuario{

	private int idPaciente;
	private Usuario usuario;
	private String cobertura;

	
	public Paciente(Usuario usuario, String nombre, String apellido, String email, String clave, int dni,
			LocalDate fechaNacimiento, Genero genero, EstadoUsuario estado, String cobertura) {
		super(nombre, apellido, email, clave, dni, fechaNacimiento, genero, estado);
		this.cobertura = cobertura;
	}
	
	public Paciente(int id, Usuario usuario, String nombre, String apellido, String email, String clave, int dni,
			LocalDate fechaNacimiento, Genero genero, EstadoUsuario estado, String cobertura) {
		super(id, nombre, apellido, email, clave, dni, fechaNacimiento, genero, estado);
		this.cobertura = cobertura;
	}
	
	public Paciente(int idPaciente, Usuario usuario, String cobertura) {
    	this.idPaciente = idPaciente;
    	this.usuario = usuario;
        this.cobertura = cobertura;
    }
	
	public Paciente() {
		
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
