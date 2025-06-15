package ar.com.cdt.formacion.consultorioOnline.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

    private int id;
	

	private Paciente paciente;

	private Medico medico;
	
    private Especialidad especialidad;
	
	private EstadoTurno estado;

    private LocalDate fecha;
	
    private LocalTime horaInicio;
    
    private LocalTime horaFin;

    private int tiempo;

    private String asunto;

    private String enlace;
    
    public Turno(int tiempo, String asunto, String enlace, Paciente paciente, Medico medico,
            LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,  EstadoTurno estado, Especialidad especialidad) {
    	this.paciente = paciente;
    	this.medico = medico;
    	this.especialidad = especialidad;
    	this.estado = estado;
    	this.fecha = fecha;
   		this.horaInicio = horaInicio;
   		this.horaFin = horaFin;
    	this.tiempo = tiempo;
    	this.asunto = asunto;
    	this.enlace = enlace;  		
    }
    
    
	public Turno(int id, int tiempo, String asunto, String enlace, Paciente paciente, Medico medico,
			LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, EstadoTurno estado, Especialidad especialidad) {
		this.id = id;
		this.paciente = paciente;
    	this.medico = medico;
    	this.especialidad = especialidad;
    	this.estado = estado;
    	this.fecha = fecha;
   		this.horaInicio = horaInicio;
   		this.horaFin = horaFin;
    	this.tiempo = tiempo;
    	this.asunto = asunto;
    	this.enlace = enlace; 
	}

	
	public Turno() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
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


	public EstadoTurno getEstado() {
		return estado;
	}

	public void setEstado(EstadoTurno estado) {
		this.estado = estado;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}


	@Override
	public String toString() {
		return "Turno [id=" + id + ", paciente=" + paciente + ", medico=" + medico + ", especialidad=" + especialidad
				+ ", estado=" + estado + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin
				+ ", tiempo=" + tiempo + ", asunto=" + asunto + ", enlace=" + enlace + "]";
	}

}
