package ar.com.cdt.formacion.consultorioOnline.controllers.auth;


import ar.com.cdt.formacion.consultorioOnline.dto.UsuarioAutocompletadoResponse;
import ar.com.cdt.formacion.consultorioOnline.models.*;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @PostMapping("/register/medico")
    public ResponseEntity<?> crearMedico(@RequestBody Medico medico) {
        try {
            int idMedico = serviceUsuario.registrarUsuarioMedico(medico);
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

    @PostMapping("/register/paciente")
    public ResponseEntity<?> crearPaciente(@RequestBody Paciente paciente) {
        try {
            int idPaciente = serviceUsuario.registrarUsuarioPaciente(paciente);
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
        boolean flag = ServiceUsuario.existeUsuarioXemail(email);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(Map.of(
                        "message", "Consulta creada correctamente",
                        "existeXmail", flag
                ));
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

    @GetMapping ("existe-consultorio")
    public ResponseEntity<?> existeConsultorioPorMedicoYEspecialidad (@RequestParam(name = "idMedico", required = true) int idMedico,
                                                                      @RequestParam(name = "fk_especialidad", required = true) int fk_especialidad) {
        try {
            boolean existe = ServiceMedico.ExisteConsultorioEspecialidad(idMedico, fk_especialidad);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body("Error inesperado al registrar el consultorio");
        }
    }

}

