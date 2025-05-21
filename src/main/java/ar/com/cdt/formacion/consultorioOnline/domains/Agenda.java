package ar.com.cdt.formacion.consultorioOnline.domains;

import java.util.List;


public class Agenda {

	private int idAgenda;
	private String tipoAgenda;
	private List<Turno> listaTurnos;
	
	public Agenda(int idAgenda, String tipoAgenda ,List<Turno> listaTurnos) {
		this.idAgenda = idAgenda;
		this.tipoAgenda = tipoAgenda;
		this.listaTurnos = listaTurnos;
	}
	
	public Agenda(List<Turno> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}
	
	public Agenda() {
		
	}

	public int getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}

	public List<Turno> getListaTurnos() {
		return listaTurnos;
	}

	public void setListaTurnos(List<Turno> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}

	public String getTipoAgenda() {
		return tipoAgenda;
	}

	public void setTipoAgenda(String tipoAgenda) {
		this.tipoAgenda = tipoAgenda;
	}

	
}
