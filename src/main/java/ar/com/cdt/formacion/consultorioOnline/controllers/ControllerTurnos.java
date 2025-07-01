package ar.com.cdt.formacion.consultorioOnline.controllers;

import ar.com.cdt.formacion.consultorioOnline.dto.TurnoResponse;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/turnos")
public class ControllerTurnos {

    @GetMapping("/consultorio")
    public ResponseEntity<?> obtenerTurnosPorConsultorio (@RequestParam int idConsultorio) {

        try {
            List<TurnoResponse> turnos = ServiceMedico.obtenerTurnosPorConsultorio(idConsultorio);
            return ResponseEntity.ok(turnos);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los turnos: " + e.getMessage());
        }
    }

    @GetMapping("/consultorioMisTurnos")
    public ResponseEntity<?> obtenerMisTurnos(@RequestParam int idPaciente) {
        System.out.println("palalsaplspalspalsplapslapslaplspala" + idPaciente);

        try {
            List<TurnoResponse> turnos = ServiceMedico.obtenerMisTurnos(idPaciente);
            return ResponseEntity.ok(turnos);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los turnos: " + e.getMessage());
        }
    }

    @PutMapping("/{idTurno}/reservar")
    public ResponseEntity<?> reservarTurno(
            @PathVariable int idTurno,
            @RequestBody Map<String, Object> payload) {

        try {
            int fkPaciente = (int) payload.get("fk_paciente");
            boolean exito = ServiceMedico.reservarTurno(idTurno, fkPaciente);
            if (exito) {
                return ResponseEntity.ok("Turno reservado");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo reservar el turno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
