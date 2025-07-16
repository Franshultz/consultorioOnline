package ar.com.cdt.formacion.consultorioOnline.dto;

import ar.com.cdt.formacion.consultorioOnline.models.DiaSemana;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.models.Medico;

import java.time.LocalTime;
import java.util.List;

public class ConsultorioResponse {
    private int idConsultorio;
    private String nombreConsultorio;

    private UsuarioResponse usuarioResponse;

    private Especialidad especialidad;


    public ConsultorioResponse(int idConsultorio, String nombreConsultorio, UsuarioResponse usuarioResponse, Especialidad especialidad) {
        this.idConsultorio = idConsultorio;
        this.nombreConsultorio = nombreConsultorio;
        this.usuarioResponse = usuarioResponse;
        this.especialidad = especialidad;
    }

    public int getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public UsuarioResponse getUsuarioResponse() {
        return usuarioResponse;
    }

    public void setUsuarioResponse(UsuarioResponse usuarioResponse) {
        this.usuarioResponse = usuarioResponse;
    }

    public String getNombreConsultorio() {
        return nombreConsultorio;
    }

    public void setNombreConsultorio(String nombreConsultorio) {
        this.nombreConsultorio = nombreConsultorio;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
