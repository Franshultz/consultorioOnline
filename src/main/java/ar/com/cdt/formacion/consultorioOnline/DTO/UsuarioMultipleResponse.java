package ar.com.cdt.formacion.consultorioOnline.DTO;

public class UsuarioMultipleResponse implements UsuarioResponse{

    private MedicoResponse medico;
    private PacienteResponse paciente;
    private boolean requiereSeleccion = true;

    public UsuarioMultipleResponse(MedicoResponse medico, PacienteResponse paciente) {
        this.medico = medico;
        this.paciente = paciente;
    }

    public MedicoResponse getMedico() {
        return medico;
    }

    public void setMedico(MedicoResponse medico) {
        this.medico = medico;
    }

    public PacienteResponse getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteResponse paciente) {
        this.paciente = paciente;
    }

    public boolean isRequiereSeleccion() {
        return requiereSeleccion;
    }

    public void setRequiereSeleccion(boolean requiereSeleccion) {
        this.requiereSeleccion = requiereSeleccion;
    }
}
