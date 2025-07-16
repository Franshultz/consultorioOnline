package ar.com.cdt.formacion.consultorioOnline.service;

import ar.com.cdt.formacion.consultorioOnline.dto.*;
import ar.com.cdt.formacion.consultorioOnline.exceptions.*;
import ar.com.cdt.formacion.consultorioOnline.models.*;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryMedico;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryPaciente;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryUsuario;
import ar.com.cdt.formacion.consultorioOnline.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceUsuario {

    public String iniciarSesion(LoginRequest loginRequest) {
        try {
            UsuarioIdResponse user = new UsuarioIdResponse();

            if (RepositoryUsuario.existeUsuarioXdni(loginRequest.getDni(), loginRequest.getClave())) {
                int fk_usuario = RepositoryUsuario.obtenerIdUsuarioXdni(loginRequest.getDni(), loginRequest.getClave());

                user.setIdUsuario(fk_usuario);

                boolean esMedico = RepositoryMedico.existeMedico(fk_usuario);
                boolean esPaciente = RepositoryPaciente.existePaciente(fk_usuario);

                if (esMedico && esPaciente) {
                    int idMedico = RepositoryMedico.obtenerIDmedico(fk_usuario);
                    int idPaciente = RepositoryPaciente.obtenerIDpaciente(fk_usuario);
                    user.setIdMedico(idMedico);
                    user.setIdPaciente(idPaciente);
                    user.setRequiereSeleccion(true);

                } else if (esMedico) {
                    int idMedico = RepositoryMedico.obtenerIDmedico(fk_usuario);
                    user.setIdMedico(idMedico);

                } else if (esPaciente) {
                    int idPaciente = RepositoryPaciente.obtenerIDpaciente(fk_usuario);
                    user.setIdPaciente(idPaciente);
                }

                String token = JwtUtil.generarToken(user);
                return token;

            } else {
                throw new CredencialesInvalidasException("DNI o contraseña incorrectos");
            }

        } catch (DatabaseException e) {
            System.err.println("Error en base de datos durante iniciarSesion: " + e.getMessage());
            throw e;

        } catch (CredencialesInvalidasException e) {
            System.err.println(e.getMessage());
            throw e;

        } catch (MedicoNoEncontradoException | PacienteNoEncontradoException e) {
            System.err.println("Se encontró un usuario pero no se pudo obtener su perfil: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            throw new RuntimeException("Error inesperado en iniciarSesion", e);
        }
    }



    public int registrarUsuarioMedico(Medico medico) {
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

    public List<MedicoConsultorioResponse>obtenerConsultorioEspecialidad(int fk_especialidad) {
        System.out.println("PIPARDO GOROSITOVICH " + fk_especialidad);
        List<MedicoConsultorioResponse> listaConsultorio = RepositoryMedico.obtenerConsultoriosPorEspecialidad(fk_especialidad);
        return listaConsultorio;
    }

    public static NombreCompletoResponse obtenerNombreCompleto(int idUsuario) {
        return RepositoryUsuario.obtenerNombreCompleto(idUsuario);

    }
}

