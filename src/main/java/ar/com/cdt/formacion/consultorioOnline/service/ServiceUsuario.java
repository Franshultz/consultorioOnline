package ar.com.cdt.formacion.consultorioOnline.service;

import ar.com.cdt.formacion.consultorioOnline.dto.*;
import ar.com.cdt.formacion.consultorioOnline.exceptions.*;
import ar.com.cdt.formacion.consultorioOnline.models.*;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryMedico;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryPaciente;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryUsuario;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceUsuario {

    public static UsuarioResponse iniciarSesion(LoginRequest loginRequest) {
        try {
            if (RepositoryUsuario.existeUsuarioXdni(loginRequest.getDni(), loginRequest.getClave())) {
                int fk_usuario = RepositoryUsuario.obtenerIdUsuarioXdni(loginRequest.getDni(), loginRequest.getClave());

                boolean esMedico = RepositoryMedico.existeMedico(fk_usuario);
                boolean esPaciente = RepositoryPaciente.existePaciente(fk_usuario);

                if (esMedico && esPaciente) {
                    MedicoResponse medico = RepositoryMedico.ObtenerMedicoCompleto(fk_usuario);
                    PacienteResponse paciente = RepositoryPaciente.ObtenerPacienteCompleto(fk_usuario);

                    return new UsuarioMultipleResponse(medico, paciente);
                } else if (esMedico) {
                    return RepositoryMedico.ObtenerMedicoCompleto(fk_usuario);
                } else if (esPaciente) {
                    return RepositoryPaciente.ObtenerPacienteCompleto(fk_usuario);
                }
            } else {
                throw new CredencialesInvalidasException("DNI o contraseña incorrectos");
            }
        } catch (DatabaseException e) {
            System.err.println("Error en base de datos durante iniciarSesion: " + e.getMessage());
            throw e;
        } catch (MedicoNoEncontradoException | PacienteNoEncontradoException e) {
            System.err.println("Se encontro un usuario pero no se pudo obtener su perfil: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            // Opcionalmente podés lanzar una excepción custom aquí también
            throw new RuntimeException("Error inesperado en iniciarSesion", e);
        }
        throw new IllegalStateException("Flujo no alcanzable en iniciarSesion");
    }


    public static int registrarUsuarioMedico(Medico medico) {
        // 1. Verificar si el usuario ya existe
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

    public static int registrarUsuarioPaciente(Paciente paciente) {
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

    public static List<Genero> capturarListGeneros() {
        return RepositoryUsuario.ObtenerGeneros();
    }

    public static List<DiaSemana> capturarListDias() {
        return RepositoryUsuario.ObtenerDias();
    }


    public static UsuarioAutocompletadoResponse capturarAutocompletado(int dni) {
        return RepositoryUsuario.ObtenerAutocompletadoUsuario(dni);
    }

    public static boolean existeUsuarioXemail(String email) {
        return RepositoryUsuario.existeUsuarioXemail(email);
    }

    public static List<MedicoConsultorioResponse>obtenerConsultorioEspecialidad(int fk_especialidad) {
        System.out.println("PIPARDO GOROSITOVICH " + fk_especialidad);
        List<MedicoConsultorioResponse> listaConsultorio = RepositoryMedico.obtenerConsultoriosPorEspecialidad(fk_especialidad);
        return listaConsultorio;
    }
}

