package ar.com.cdt.formacion.consultorioOnline.dto;

public class MedicoConsultorioResponse {

    private int id_consultorio;
    private String nombre_consultorio;
    private String horario_laboral_inicio;
    private String horario_laboral_fin;
    private int fk_especialidad;
    private int fk_medico;
    private String especialidad;
    private int duracion_turno;
    private String nombre;
    private String apellido;
    private String email;
    private String dias_seleccionados;


    public MedicoConsultorioResponse() {}

    public MedicoConsultorioResponse(int id_consultorio, String nombre_consultorio, String horario_laboral_inicio, String horario_laboral_fin, int fk_especialidad, int fk_medico,String especialidad, int duracion_turno, String nombre, String apellido ,String email, String dias_seleccionados) {
        this.id_consultorio = id_consultorio;
        this.nombre_consultorio = nombre_consultorio;
        this.horario_laboral_inicio = horario_laboral_inicio;
        this.horario_laboral_fin = horario_laboral_fin;
        this.fk_especialidad = fk_especialidad;
        this.fk_medico = fk_medico;
        this.especialidad = especialidad;
        this.duracion_turno = duracion_turno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dias_seleccionados = dias_seleccionados;
    }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    public String getNombre_consultorio() {
        return nombre_consultorio;
    }

    public void setNombre_consultorio(String nombre_consultorio) {
        this.nombre_consultorio = nombre_consultorio;
    }

    public String getHorario_laboral_fin() {
        return horario_laboral_fin;
    }

    public void setHorario_laboral_fin(String horario_laboral_fin) {
        this.horario_laboral_fin = horario_laboral_fin;
    }

    public int getFk_especialidad() {
        return fk_especialidad;
    }

    public void setFk_especialidad(int fk_especialidad) {
        this.fk_especialidad = fk_especialidad;
    }

    public String getHorario_laboral_inicio() {
        return horario_laboral_inicio;
    }

    public void setHorario_laboral_inicio(String horario_laboral_inicio) {
        this.horario_laboral_inicio = horario_laboral_inicio;
    }

    public int getFk_medico() {
        return fk_medico;
    }

    public void setFk_medico(int fk_medico) {
        this.fk_medico = fk_medico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getDuracion_turno() {
        return duracion_turno;
    }

    public void setDuracion_turno(int duracion_turno) {
        this.duracion_turno = duracion_turno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDias_seleccionados() {
        return dias_seleccionados;
    }

    public void setDias_seleccionados(String dias_seleccionados) {
        this.dias_seleccionados = dias_seleccionados;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MedicoConsultorioResponse{" +
                "id_consultorio=" + id_consultorio +
                ", nombre_consultorio='" + nombre_consultorio + '\'' +
                ", horario_laboral_inicio='" + horario_laboral_inicio + '\'' +
                ", horario_laboral_fin='" + horario_laboral_fin + '\'' +
                ", fk_especialidad=" + fk_especialidad +
                ", fk_medico=" + fk_medico +
                ", especialidad='" + especialidad + '\'' +
                ", duracion_turno=" + duracion_turno +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", dias_seleccionados='" + dias_seleccionados + '\'' +
                '}';
    }
}
