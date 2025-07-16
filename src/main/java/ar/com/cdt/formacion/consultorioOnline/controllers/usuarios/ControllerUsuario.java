package ar.com.cdt.formacion.consultorioOnline.controllers.usuarios;

import ar.com.cdt.formacion.consultorioOnline.dto.*;
import ar.com.cdt.formacion.consultorioOnline.exceptions.CredencialesInvalidasException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.DatabaseException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.MedicoNoEncontradoException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.PacienteNoEncontradoException;
import ar.com.cdt.formacion.consultorioOnline.models.*;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceUsuario;
import ar.com.cdt.formacion.consultorioOnline.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class ControllerUsuario {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/nombrecompleto")
    public ResponseEntity<?> ObtenerNombreCompleto(@RequestHeader("Authorization") String header) {
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (JwtUtil.validarToken(token)) {
                int idUsuario = JwtUtil.obtenerIdUsuario(token);
                int idMedico = JwtUtil.obtenerIdMedico(token);
                int idPaciente = JwtUtil.obtenerIdPaciente(token);
                Boolean requiereSeleccion = JwtUtil.obtenerRequiereSeleccion(token);

                NombreCompletoResponse nombreApellido = ServiceUsuario.obtenerNombreCompleto(idUsuario);


                return ResponseEntity.ok(nombreApellido);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o ausente");
    }


}
