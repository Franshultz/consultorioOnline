package ar.com.cdt.formacion.consultorioOnline.dto;

import ar.com.cdt.formacion.consultorioOnline.models.EstadoUsuario;
import ar.com.cdt.formacion.consultorioOnline.models.Genero;

import java.time.LocalDate;

public class UsuarioResponse {

    private int dni, fk_usuario;
    private String nombre, apellido, email;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private EstadoUsuario estadoUsuario;

    public UsuarioResponse() {

    }

    public UsuarioResponse(String nombre, int fk_usuario, int dni, String apellido, String email, LocalDate fechaNacimiento, Genero genero, EstadoUsuario estadoUsuario) {
        this.nombre = nombre;
        this.fk_usuario = fk_usuario;
        this.dni = dni;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.estadoUsuario = estadoUsuario;
    }

    public UsuarioResponse(String nombre, String apellido, int dni, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
}
