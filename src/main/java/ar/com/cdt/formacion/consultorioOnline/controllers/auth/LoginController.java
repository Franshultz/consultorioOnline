package ar.com.cdt.formacion.consultorioOnline.controllers.auth;

import ar.com.cdt.formacion.consultorioOnline.dto.*;
import ar.com.cdt.formacion.consultorioOnline.exceptions.CredencialesInvalidasException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.DatabaseException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.MedicoNoEncontradoException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.PacienteNoEncontradoException;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @PostMapping("/sesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginRequest loginRequest) {
        try {
            String token = serviceUsuario.iniciarSesion(loginRequest);
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
}

