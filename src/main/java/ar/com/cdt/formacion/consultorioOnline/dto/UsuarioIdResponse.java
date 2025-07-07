package ar.com.cdt.formacion.consultorioOnline.dto;

public class UsuarioIdResponse {

    private int idUsuario, idMedico, idPaciente;
    private boolean requiereSeleccion;

    public UsuarioIdResponse(){

    }

    public UsuarioIdResponse(int idUsuario) {
        this.idUsuario = idUsuario;
        this.idMedico = Integer.parseInt(null);
        this.idPaciente = Integer.parseInt(null);
        this.requiereSeleccion = false;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isRequiereSeleccion() {
        return requiereSeleccion;
    }

    public void setRequiereSeleccion(boolean requiereSeleccion) {
        this.requiereSeleccion = requiereSeleccion;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }
}
