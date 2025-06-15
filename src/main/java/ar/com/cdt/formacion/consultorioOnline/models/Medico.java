package ar.com.cdt.formacion.consultorioOnline.models;

import java.time.LocalDate;
import java.util.List;

public class Medico extends Usuario{
 
	private int idMedico;

	private int fk_usuario;
	private Usuario usuario;
	private String matricula;
	private List<Especialidad> listaEspecialidades;
	private List<Consultorio> listaConsultorio;


	public Medico() {};

	public Medico(String nombre, String apellido, String email, String clave, int dni,
				  LocalDate fechaNacimiento, int fk_genero, int fk_estado_usuario, String matricula) {
		super(nombre, apellido, email, clave, dni, fechaNacimiento, fk_genero, fk_estado_usuario);
		this.matricula = matricula;
	}

    public Medico(int fk_usuario, String nombre, String apellido, String email, String clave, int dni, LocalDate fechaNacimiento, int fk_genero, int fk_estado_usuario, String matricula) {
		super(nombre, apellido, email, clave, dni, fechaNacimiento, fk_genero, fk_estado_usuario);
		this.fk_usuario = fk_usuario;
		this.matricula = matricula;
	}

	public int getFk_usuario() {
		return fk_usuario;
	}

	public void setFk_usuario(int fk_usuario) {
		this.fk_usuario = fk_usuario;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	public List<Consultorio> getListaConsultorio() {
		return listaConsultorio;
	}

	public void setListaConsultorio(List<Consultorio> listaConsultorio) {
		this.listaConsultorio = listaConsultorio;
	}

	
	@Override
	public String toString() {
		return "Medico [usuario=" + usuario + ", matricula=" + matricula + ", listaEspecialidades="
				+ listaEspecialidades + ", listaConsultorio=" + listaConsultorio + "]";
	}		
}
