package ar.com.cdt.formacion.consultorioOnline.controllers;

import ar.com.cdt.formacion.consultorioOnline.dto.LoginRequest;
import ar.com.cdt.formacion.consultorioOnline.dto.UsuarioAutocompletadoResponse;
import ar.com.cdt.formacion.consultorioOnline.dto.UsuarioIdResponse;
import ar.com.cdt.formacion.consultorioOnline.dto.UsuarioResponse;
import ar.com.cdt.formacion.consultorioOnline.exceptions.CredencialesInvalidasException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.DatabaseException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.MedicoNoEncontradoException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.PacienteNoEncontradoException;
import ar.com.cdt.formacion.consultorioOnline.models.*;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class ControllerUsuario {


    @PostMapping("/sesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginRequest loginRequest) {
        try {
            String token = ServiceUsuario.iniciarSesion(loginRequest);
            return ResponseEntity
                    .status(HttpStatus.OK) // 200 OK
                    .body(Map.of(
                            "message", "Inicio de sesión exitoso",
                            "token", token
                    ));
        } catch (CredencialesInvalidasException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401 Unauthorized
                    .body(Map.of("error", e.getMessage()));
        } catch (MedicoNoEncontradoException | PacienteNoEncontradoException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND) // 404 Not Found
                    .body(Map.of("error", e.getMessage()));
        } catch (DatabaseException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
                    .body(Map.of("error", "Error de base de datos: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error inesperado al iniciar sesión"));
        }
    }


    @PostMapping("/medico")
    public ResponseEntity<?> crearMedico(@RequestBody Medico medico) {
        try {
            int idMedico = ServiceUsuario.registrarUsuarioMedico(medico);
            return ResponseEntity
                    .status(HttpStatus.CREATED) // 201
                    .body(Map.of(
                            "message", "Médico creado correctamente",
                            "id_medico", idMedico
                    ));
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body("Error inesperado al registrar el médico");
        }
    }

    @PostMapping("/paciente")
    public ResponseEntity<?> crearPaciente(@RequestBody Paciente paciente) {
        try {
            int idPaciente = ServiceUsuario.registrarUsuarioPaciente(paciente);
            return ResponseEntity
                    .status(HttpStatus.CREATED) // 201
                    .body(Map.of(
                            "message", "Paciente creado correctamente",
                            "id_paciente", idPaciente
                    ));
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body("Error inesperado al registrar el paciente");
        }
    }

    @GetMapping("/existe-email")
        public ResponseEntity<?> existeEmail(@RequestParam String email) {
        System.out.println("ppppppppppPPPPPPPPPPPPPPPPPPPPPPPPPP" + email);
        boolean flag = ServiceUsuario.existeUsuarioXemail(email);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(Map.of(
                        "message", "Consulta creada correctamente",
                        "existeXmail", flag
                ));
    }

    @GetMapping("/sexos")
    public ResponseEntity<?> obtenerSexos() {
        try {
            return ResponseEntity.ok(ServiceUsuario.capturarListGeneros());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dias")
    public ResponseEntity<?> obtenerDias() {
        try {
            return ResponseEntity.ok(ServiceUsuario.capturarListDias());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/buscarPorDni")
    public ResponseEntity<?> buscarPorDni(@RequestParam int dni) {
        try {
        UsuarioAutocompletadoResponse datos = ServiceUsuario.capturarAutocompletado(dni);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(Map.of(
                        "message", "Paciente creado correctamente",
                        "datos_autocompletado", datos
                ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body("Error inesperado en el autocmpletado");
        }
    }

}
