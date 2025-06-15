package ar.com.cdt.formacion.consultorioOnline.DTO;

import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.models.EstadoUsuario;
import ar.com.cdt.formacion.consultorioOnline.models.Genero;

import java.time.LocalDate;
import java.util.List;

public class PacienteResponse implements UsuarioResponse{

    private int idPaciente, dni, fk_usuario;
    private String nombre, apellido, email;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private EstadoUsuario estadoUsuario;
    private String cobertura;

    public PacienteResponse() {}

    public PacienteResponse(int idPaciente, int dni, int fk_usuario, String nombre, String apellido, LocalDate fechaNacimiento, String email, Genero genero, EstadoUsuario estadoUsuario, String cobertura) {
        this.idPaciente = idPaciente;
        this.dni = dni;
        this.fk_usuario = fk_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.genero = genero;
        this.estadoUsuario = estadoUsuario;
        this.cobertura = cobertura;
    }


    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
