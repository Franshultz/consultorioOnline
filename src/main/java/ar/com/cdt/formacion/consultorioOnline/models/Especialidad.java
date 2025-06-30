package ar.com.cdt.formacion.consultorioOnline.models;


public class Especialidad {

	
    private int idEspecialidad;
    private String especialidad;
    private int duracionTurno;

	public Especialidad() {}
	
	public Especialidad(int idEspecialidad, String especialidad, int duracionTurno) {
		this.idEspecialidad = idEspecialidad;
		this.especialidad = especialidad;
		this.duracionTurno = duracionTurno;
	}
	
	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getDuracionTurno() {
		return duracionTurno;
	}

	public void setDuracionTurno(int duracionTurno) {
		this.duracionTurno = duracionTurno;
	}

	@Override
	public String toString() {
		return "Especialidad [idEspecialidad=" + idEspecialidad + ", especialidad=" + especialidad + ", duracionTurno="
				+ duracionTurno + "]";
	}

}
