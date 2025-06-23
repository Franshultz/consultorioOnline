package ar.com.cdt.formacion.consultorioOnline.dto;

import ar.com.cdt.formacion.consultorioOnline.models.EstadoUsuario;
import ar.com.cdt.formacion.consultorioOnline.models.Genero;

import java.time.LocalDate;

public class UsuarioAutocompletadoResponse {

    private int idUsuario, dni;
    private String nombre, apellido, email, clave;
    private LocalDate fechaNacimiento;
    private Genero genero;

    private boolean esMedico;
    private String matricula;

    private boolean esPaciente;
    private String cobertura;

    private EstadoUsuario estado;

    public UsuarioAutocompletadoResponse() {

    }

    public UsuarioAutocompletadoResponse(int idUsuario, int dni, String nombre, String apellido, String email, String clave, LocalDate fechaNacimiento, Genero genero, boolean esMedico, String matricula, boolean esPaciente, String cobertura, EstadoUsuario estado) {
        this.idUsuario = idUsuario;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.clave = clave;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.esMedico = esMedico;
        this.matricula = matricula;
        this.esPaciente = esPaciente;
        this.cobertura = cobertura;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isEsPaciente() {
        return esPaciente;
    }

    public void setEsPaciente(boolean esPaciente) {
        this.esPaciente = esPaciente;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public boolean isEsMedico() {
        return esMedico;
    }

    public void setEsMedico(boolean esMedico) {
        this.esMedico = esMedico;
    }
}
