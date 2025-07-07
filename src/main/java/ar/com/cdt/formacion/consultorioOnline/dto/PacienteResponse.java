package ar.com.cdt.formacion.consultorioOnline.dto;


import ar.com.cdt.formacion.consultorioOnline.models.EstadoUsuario;
import ar.com.cdt.formacion.consultorioOnline.models.Genero;

import java.time.LocalDate;

public class PacienteResponse extends UsuarioResponse {

    private int idPaciente;
    private String cobertura;

    public PacienteResponse() {
        super();
    }

    public PacienteResponse (int id_usuario, int idPaciente){
        super(id_usuario);
        this.idPaciente = idPaciente;
    }

    public PacienteResponse(String nombre, int id_usuario, int dni, String apellido, String email, LocalDate fechaNacimiento, Genero genero, EstadoUsuario estadoUsuario, int idPaciente, String cobertura) {
        super(nombre, id_usuario, dni, apellido, email, fechaNacimiento, genero, estadoUsuario);
        this.idPaciente = idPaciente;
        this.cobertura = cobertura;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }
}
