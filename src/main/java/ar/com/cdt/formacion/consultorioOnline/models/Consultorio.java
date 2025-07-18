package ar.com.cdt.formacion.consultorioOnline.models;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Consultorio {

	private int idConsultorio;
	private String nombreConsultorio;

	private int fk_medico;
	private Medico medico;

	private Especialidad especialidad;
	private int fk_especialidad;

	private LocalTime horarioLaboralInicio;
	private LocalTime horarioLaboralFin;

	private List<Integer> dias;
	private List<DiaSemana> diasSemana;


	public Consultorio() {}

	public Consultorio(int idConsultorio, String nombreConsultorio, LocalTime horarioLaboralInicio, LocalTime horarioLaboralFin, int fk_medico, int fk_especialidad , List<DiaSemana> diasSemana) {
		this.idConsultorio = idConsultorio;
		this.nombreConsultorio = nombreConsultorio;
		this.horarioLaboralInicio = horarioLaboralInicio;
		this.horarioLaboralFin = horarioLaboralFin;
		this.fk_medico = fk_medico;
		this.fk_especialidad = fk_especialidad;
		this.diasSemana = diasSemana;
	}

	public Consultorio(int idConsultorio, String nombreConsultorio, LocalTime horarioLaboralInicio, LocalTime horarioLaboralFin, int fk_medico, Especialidad especialidad , List<DiaSemana> diasSemana) {
		this.idConsultorio = idConsultorio;
		this.nombreConsultorio = nombreConsultorio;
		this.horarioLaboralInicio = horarioLaboralInicio;
		this.horarioLaboralFin = horarioLaboralFin;
		this.fk_medico = fk_medico;
		this.especialidad = especialidad;
		this.diasSemana = diasSemana;
	}

	public int getIdConsultorio() {
		return idConsultorio;
	}

	public void setIdConsultorio(int idConsultorio) {
		this.idConsultorio = idConsultorio;
	}

	public LocalTime getHorarioLaboralInicio() {
		return horarioLaboralInicio;
	}

	public void setHorarioLaboralInicio(LocalTime horarioLaboralInicio) {
		this.horarioLaboralInicio = horarioLaboralInicio;
	}

	public LocalTime getHorarioLaboralFin() {
		return horarioLaboralFin;
	}

	public void setHorarioLaboralFin(LocalTime horarioLaboralFin) {
		this.horarioLaboralFin = horarioLaboralFin;
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

	public int getFk_especialidad() {
		return fk_especialidad;
	}

	public void setFk_especialidad(int fk_especialidad) {
		this.fk_especialidad = fk_especialidad;
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
		return "Consultorio{" +
				"idConsultorio=" + idConsultorio +
				", nombreConsultorio='" + nombreConsultorio + '\'' +
				", fk_medico=" + fk_medico +
				", medico=" + medico +
				", fk_especialidad=" + fk_especialidad +
				", horarioLaboralInicio=" + horarioLaboralInicio +
				", horarioLaboralFin=" + horarioLaboralFin +
				", dias=" + dias +
				", diasSemana=" + diasSemana +
				'}';
	}
}
