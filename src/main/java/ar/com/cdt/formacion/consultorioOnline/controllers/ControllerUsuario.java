package ar.com.cdt.formacion.consultorioOnline.controllers;

import ar.com.cdt.formacion.consultorioOnline.DTO.LoginRequest;
import ar.com.cdt.formacion.consultorioOnline.DTO.UsuarioResponse;
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

    private final ServiceUsuario serviceUsuario;

    public ControllerUsuario(ServiceUsuario serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    @PostMapping("/sesion")
    public UsuarioResponse iniciarSesion(@RequestBody LoginRequest loginRequest){
        try {
            return serviceUsuario.iniciarSesion(loginRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/medico")
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

    @PostMapping("/paciente")
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

    @GetMapping("/sexos")
    public ResponseEntity<?> obtenerSexos() {
        try {
            return ResponseEntity.ok(serviceUsuario.capturarListGeneros());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dias")
    public ResponseEntity<?> obtenerDias() {
        try {
            return ResponseEntity.ok(serviceUsuario.capturarListDias());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/datos-autocompletado")
    public ResponseEntity<?> obtenerUsuarioAutocompletado(@RequestParam int dni) {
        try {
            return ResponseEntity.ok(serviceUsuario.capturarAutocompletado(dni));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
