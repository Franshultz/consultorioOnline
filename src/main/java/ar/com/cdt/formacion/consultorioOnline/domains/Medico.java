package ar.com.cdt.formacion.consultorioOnline.domains;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;	

public class Medico extends Usuario{
 
	private int idMedico;
	private Usuario usuario;
	private String matricula;
	private List<Especialidad> listaEspecialidades;
	private List<Consultorio> listaConsultorio;
    
	//Utilizo este para ingrsar Medico a l bd
	
    public Medico(Usuario usuario, String nombre, String apellido, String email, String clave, int dni,
    		LocalDate fechaNacimiento, Genero genero, EstadoUsuario estado, String matricula,
    		List<Especialidad> listaEspecialidades, List<Consultorio> listaConsultorio) {
        super(nombre, apellido, email, clave, dni, fechaNacimiento, genero, estado);
        this.matricula = matricula;
        this.listaEspecialidades = listaEspecialidades;
        this.listaConsultorio = listaConsultorio;
    }

    public Medico(int idMedico, Usuario usuario, String matricula, List<Especialidad> listaEspecialidades, List<Consultorio> listaConsultorio) {
    	this.idMedico = idMedico;
    	this.usuario = usuario;
        this.matricula = matricula;
        this.listaEspecialidades = listaEspecialidades;
        this.listaConsultorio = listaConsultorio;
    }
    
    //Utilizo este para traer los datos de Medico completo
    public Medico(int idMedico, Usuario usuario, String nombre, String apellido, String email, String clave, int dni,
    		LocalDate fechaNacimiento, Genero genero, EstadoUsuario estado, String matricula,
    		List<Especialidad> listaEspecialidades, List<Consultorio> listaConsultorio) {
        super(idMedico, nombre, apellido, email, clave, dni, fechaNacimiento, genero, estado);
        this.matricula = matricula;
        this.listaEspecialidades = listaEspecialidades;
        this.listaConsultorio = listaConsultorio;
    }
    
    //Por si acaso
    public Medico() {
    	
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
