package ar.com.cdt.formacion.consultorioOnline.models;

import java.sql.Time;
import java.util.List;

public class Consultorio {

	private int idConsultorio;
	private String nombreConsultorio;

	private int fk_medico;
	private Medico medico;

	private int fk_especialidad;
	private Especialidad especialidad;

	private String horarioLaboralInicio;
	private String horarioLaboralFin;

	private int fk_agenda;
	private Agenda agendaMedico;

	private List<Integer> dias;
	private List<DiaSemana> diasSemana;


	public Consultorio() {}

	public Consultorio(String nombreConsultorio, int fk_especialidad, String horarioLaboralInicio, String horarioLaboralFin, int fk_medico , int fk_agenda, List<Integer> dias) {
		this.nombreConsultorio = nombreConsultorio;
		this.fk_especialidad = fk_especialidad;
		this.horarioLaboralInicio = horarioLaboralInicio;
		this.horarioLaboralFin = horarioLaboralFin;
		this.fk_medico = fk_medico;
		this.fk_agenda = fk_agenda;
		this.dias = dias;
	}

	public Consultorio(int idConsultorio, String nombreConsultorio, String horarioLaboralInicio, String horarioLaboralFin, int fk_medico, Especialidad especialidad , Agenda agendaMedico, List<DiaSemana> diasSemana) {
		this.idConsultorio = idConsultorio;
		this.nombreConsultorio = nombreConsultorio;
		this.horarioLaboralInicio = horarioLaboralInicio;
		this.horarioLaboralFin = horarioLaboralFin;
		this.fk_medico = fk_medico;
		this.especialidad = especialidad;
		this.agendaMedico = agendaMedico;
		this.diasSemana = diasSemana;
	}

	public int getIdConsultorio() {
		return idConsultorio;
	}

	public void setIdConsultorio(int idConsultorio) {
		this.idConsultorio = idConsultorio;
	}

	public Agenda getAgendaMedico() {
		return agendaMedico;
	}

	public void setAgendaMedico(Agenda agendaMedico) {
		this.agendaMedico = agendaMedico;
	}

	public int getFk_agenda() {
		return fk_agenda;
	}

	public void setFk_agenda(int fk_agenda) {
		this.fk_agenda = fk_agenda;
	}

	public String getHorarioLaboralFin() {
		return horarioLaboralFin;
	}

	public void setHorarioLaboralFin(String horarioLaboralFin) {
		this.horarioLaboralFin = horarioLaboralFin;
	}

	public String getNombreConsultorio() {
		return nombreConsultorio;
	}

	public void setNombreConsultorio(String nombreConsultorio) {
		this.nombreConsultorio = nombreConsultorio;
	}

	public int getFk_especialidad() {
		return fk_especialidad;
	}

	public void setFk_especialidad(int fk_especialidad) {
		this.fk_especialidad = fk_especialidad;
	}

	public String getHorarioLaboralInicio() {
		return horarioLaboralInicio;
	}

	public void setHorarioLaboralInicio(String horarioLaboralInicio) {
		this.horarioLaboralInicio = horarioLaboralInicio;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public int getFk_medico() {
		return fk_medico;
	}

	public void setFk_medico(int fk_medico) {
		this.fk_medico = fk_medico;
	}

	public List<Integer> getDias() {
		return dias;
	}

	public void setDias(List<Integer> dias) {
		this.dias = dias;
	}

	public List<DiaSemana> getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(List<DiaSemana> diasSemana) {
		this.diasSemana = diasSemana;
	}

	@Override
	public String toString() {
		return "Consultorio [idConsultorio=" + idConsultorio + ", nombreConsultorio=" + nombreConsultorio + ", medico="
				+ medico + ", especialidad=" + especialidad + ", horarioLaboralInicio=" + horarioLaboralInicio
				+ ", horarioLaboralFin=" + horarioLaboralFin + ", agendaMedico=" + agendaMedico + "]";
	}
	
}
