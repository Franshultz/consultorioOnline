package ar.com.cdt.formacion.consultorioOnline.dto;

import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoResponse {

    private int id_turno;

    private UsuarioResponse medico;

    private Especialidad especialidad;

    private int fk_estado_turno;

    private Consultorio consultorio;

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    public TurnoResponse(int id_turno, LocalTime horaInicio, LocalTime horaFin, LocalDate fecha, Especialidad especialidad, UsuarioResponse medico, Consultorio consultorio, int fk_estado_turno) {
        this.id_turno = id_turno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.especialidad = especialidad;
        this.medico = medico;
        this.consultorio = consultorio;
        this.fk_estado_turno = fk_estado_turno;
    }

    public TurnoResponse() {

    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public UsuarioResponse getMedico() {
        return medico;
    }

    public void setMedico(UsuarioResponse medico) {
        this.medico = medico;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public int getFk_estado_turno() {
        return fk_estado_turno;
    }

    public void setFk_estado_turno(int fk_estado_turno) {
        this.fk_estado_turno = fk_estado_turno;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }
}
