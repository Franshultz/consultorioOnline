package ar.com.cdt.formacion.consultorioOnline.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

    private int id;

	private int fk_paciente;

	private int fk_medico;
	
    private int fk_especialidad;
	
	private int fk_estado_turno;

	private int fk_consultorio;

    private LocalDate fecha;
	
    private LocalTime horaInicio;
    
    private LocalTime horaFin;

    private int tiempo;

    private String asunto;

    private String enlace;

	public Turno() {

	}

	public Turno(int id, String asunto, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String enlace, int fk_medico, int fk_especialidad, int fk_estado_turno, int fk_consultorio) {
		this.id = id;
		this.asunto = asunto;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.enlace = enlace;
		this.fk_medico = fk_medico;
		this.fk_paciente = Integer.parseInt(null);
		this.fk_especialidad = fk_especialidad;
		this.fk_estado_turno = fk_estado_turno;
		this.fk_consultorio = fk_consultorio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_paciente() {
		return fk_paciente;
	}

	public void setFk_paciente(int fk_paciente) {
		this.fk_paciente = fk_paciente;
	}

	public int getFk_medico() {
		return fk_medico;
	}

	public void setFk_medico(int fk_medico) {
		this.fk_medico = fk_medico;
	}

	public int getFk_especialidad() {
		return fk_especialidad;
	}

	public void setFk_especialidad(int fk_especialidad) {
		this.fk_especialidad = fk_especialidad;
	}

	public int getFk_estado_turno() {
		return fk_estado_turno;
	}

	public void setFk_estado_turno(int fk_estado_turno) {
		this.fk_estado_turno = fk_estado_turno;
	}

	public int getFk_consultorio() {
		return fk_consultorio;
	}

	public void setFk_consultorio(int fk_consultorio) {
		this.fk_consultorio = fk_consultorio;
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

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
}
