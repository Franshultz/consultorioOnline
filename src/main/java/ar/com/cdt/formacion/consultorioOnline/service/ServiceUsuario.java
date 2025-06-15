package ar.com.cdt.formacion.consultorioOnline.service;

import ar.com.cdt.formacion.consultorioOnline.DTO.*;
import ar.com.cdt.formacion.consultorioOnline.models.*;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryMedico;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryPaciente;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUsuario {

    public UsuarioResponse iniciarSesion(LoginRequest loginRequest){
        if (RepositoryUsuario.existeUsuarioXdni(loginRequest.getDni(), loginRequest.getClave())) {
            int fk_usuario = RepositoryUsuario.obtenerIdUsuarioXdni(loginRequest.getDni(), loginRequest.getClave());

            boolean esMedico = RepositoryMedico.existeMedico(fk_usuario);
            boolean esPaciente = RepositoryPaciente.existePaciente(fk_usuario);
            if (esMedico && esPaciente){
                MedicoResponse medico = RepositoryMedico.ObtenerMedicoCompleto(fk_usuario);
                PacienteResponse paciente = RepositoryPaciente.ObtenerPacienteCompleto(fk_usuario);

                return new UsuarioMultipleResponse(medico, paciente);
            } else if (esMedico) {
                return RepositoryMedico.ObtenerMedicoCompleto(fk_usuario);
            } else if (esPaciente){
                return RepositoryPaciente.ObtenerPacienteCompleto(fk_usuario);
            }
        }
        return null;
    }



    public int registrarUsuarioMedico(Medico medico) {

        if (RepositoryUsuario.existeUsuario(medico.getEmail(), medico.getDni())) {
            int fk_usuario = RepositoryUsuario.obtenerIdUsuario(medico.getEmail(), medico.getDni());

            // 2. Verificar si ya está como paciente
            if (RepositoryMedico.existeMedico(fk_usuario)) {
                throw new IllegalStateException("El usuario ya está registrado como médico");
            } else {
                // 3. Finalmente si no esta como medico lo guarda
                medico.setFk_usuario(fk_usuario);
                return RepositoryMedico.guardarMedico(medico);
            }

        } else {
            // 4. Si no existe el usuario, lo creo completo
            return RepositoryUsuario.guardarUsuarioMedico(medico);
        }
    }

    public int registrarUsuarioPaciente(Paciente paciente) {
        // 1. Verificar si el usuario ya existe
        if (RepositoryUsuario.existeUsuario(paciente.getEmail(), paciente.getDni())) {
            int fk_usuario = RepositoryUsuario.obtenerIdUsuario(paciente.getEmail(), paciente.getDni());
            // 2. Verificar si ya está como paciente

            if (RepositoryPaciente.existePaciente(fk_usuario)) {
                throw new IllegalStateException("El usuario ya está registrado como paciente");
            } else {
                // 3. Finalmente si no esta como paciente lo guarda
                paciente.setFk_usuario(fk_usuario);
                return RepositoryPaciente.guardarPaciente(paciente);
            }

        } else {
            // 4. Si no existe el usuario, lo creo completo
            return RepositoryUsuario.guardarUsuarioPaciente(paciente);
        }
    }

    public List<Genero> capturarListGeneros() {
        return RepositoryUsuario.ObtenerGeneros();
    }

    public List<DiaSemana> capturarListDias() {
        return RepositoryUsuario.ObtenerDias();
    }


    public UsuarioAutocompletadoResponse capturarAutocompletado(int dni) {
        return RepositoryUsuario.ObtenerAutocompletadoUsuario(dni);
    }
}

