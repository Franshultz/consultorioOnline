package ar.com.cdt.formacion.consultorioOnline.domains;

import java.sql.Time;
import java.time.LocalTime;

public class Consultorio {

	private int idConsultorio;
	private String nombreConsultorio;
	private Medico medico;
	private Especialidad especialidad;
	private Time horarioLaboralInicio;
	private Time horarioLaboralFin;
	private Agenda agendaMedico;

	public Consultorio(int idConsultorio, String nombreConsultorio, Time horarioLaboralInicio,
			Time horarioLaboralFin, Medico medico, Especialidad especialidad, Agenda agendaMedico) {
		this.idConsultorio = idConsultorio;
		this.nombreConsultorio = nombreConsultorio;
		this.medico = medico;
		this.especialidad = especialidad;
		this.horarioLaboralInicio = horarioLaboralInicio;
		this.horarioLaboralFin = horarioLaboralFin;
		this.agendaMedico = agendaMedico;
	}
	
	public Consultorio(int idConsultorio, String nombreConsultorio, Time horarioLaboralInicio,
			Time horarioLaboralFin, Especialidad especialidad, Agenda agendaMedico) {
		this.idConsultorio = idConsultorio;
		this.nombreConsultorio = nombreConsultorio;
		this.especialidad = especialidad;
		this.horarioLaboralInicio = horarioLaboralInicio;
		this.horarioLaboralFin = horarioLaboralFin;
		this.agendaMedico = agendaMedico;
	}
	
	public Consultorio(String nombreConsultorio, Medico medico, Especialidad especialidad,
			Time horarioLaboralInicio, Time horarioLaboralFin, Agenda agendaMedico) {
		this.nombreConsultorio = nombreConsultorio;
		this.medico = medico;
		this.especialidad = especialidad;
		this.horarioLaboralInicio = horarioLaboralInicio;
		this.horarioLaboralFin = horarioLaboralFin;
		this.agendaMedico = agendaMedico;
	}
	
	public Consultorio() {
		
	}

	public int getIdConsultorio() {
		return idConsultorio;
	}

	public void setIdConsultorio(int idConsultorio) {
		this.idConsultorio = idConsultorio;
	}

	public String getNombreConsultorio() {
		return nombreConsultorio;
	}

	public void setNombreConsultorio(String nombreConsultorio) {
		this.nombreConsultorio = nombreConsultorio;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Time getHorarioLaboralInicio() {
		return horarioLaboralInicio;
	}

	public void setHorarioLaboralInicio(Time horarioLaboralInicio) {
		this.horarioLaboralInicio = horarioLaboralInicio;
	}

	public Time getHorarioLaboralFin() {
		return horarioLaboralFin;
	}

	public void setHorarioLaboralFin(Time horarioLaboralFin) {
		this.horarioLaboralFin = horarioLaboralFin;
	}

	public Agenda getAgendaMedico() {
		return agendaMedico;
	}

	public void setAgendaMedico(Agenda agendaMedico) {
		this.agendaMedico = agendaMedico;
	}

	@Override
	public String toString() {
		return "Consultorio [idConsultorio=" + idConsultorio + ", nombreConsultorio=" + nombreConsultorio + ", medico="
				+ medico + ", especialidad=" + especialidad + ", horarioLaboralInicio=" + horarioLaboralInicio
				+ ", horarioLaboralFin=" + horarioLaboralFin + ", agendaMedico=" + agendaMedico + "]";
	}
	
}
