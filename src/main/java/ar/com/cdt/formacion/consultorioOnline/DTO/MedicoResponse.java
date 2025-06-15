package ar.com.cdt.formacion.consultorioOnline.DTO;

import ar.com.cdt.formacion.consultorioOnline.models.*;

import java.time.LocalDate;
import java.util.List;

public class MedicoResponse implements UsuarioResponse{


    private int idMedico, dni, fk_usuario;
    private String nombre, apellido, email;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private EstadoUsuario estadoUsuario;
    private String matricula;
    private List<Especialidad> listaEspecialidades;
    private List<Consultorio> listaConsultorio;


    public MedicoResponse() {}

    public MedicoResponse(int idMedico , String nombre, String apellido, String email, int dni,
                  LocalDate fechaNacimiento, Genero genero, EstadoUsuario estadoUsuario, String matricula, int fk_usuario,
                  List<Especialidad> listaEspecialidades, List<Consultorio> listaConsultorio) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.estadoUsuario = estadoUsuario;
        this.matricula = matricula;
        this.fk_usuario = fk_usuario;
        this.listaEspecialidades = listaEspecialidades;
        this.listaConsultorio = listaConsultorio;
    }


    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
