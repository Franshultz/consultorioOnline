package ar.com.cdt.formacion.consultorioOnline.dto;

import ar.com.cdt.formacion.consultorioOnline.models.*;

import java.time.LocalDate;
import java.util.List;

public class MedicoResponse extends UsuarioResponse {

    private int idMedico;
    private String matricula;
    private List<Especialidad> listaEspecialidades;
    private List<Consultorio> listaConsultorio;

    public MedicoResponse() {
        super();
    }

    public MedicoResponse(String nombre, int fk_usuario, int dni, String apellido, String email, LocalDate fechaNacimiento, Genero genero, EstadoUsuario estadoUsuario, int idMedico, String matricula, List<Consultorio> listaConsultorio, List<Especialidad> listaEspecialidades) {
        super(nombre, fk_usuario, dni, apellido, email, fechaNacimiento, genero, estadoUsuario);
        this.idMedico = idMedico;
        this.matricula = matricula;
        this.listaConsultorio = listaConsultorio;
        this.listaEspecialidades = listaEspecialidades;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
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
}
