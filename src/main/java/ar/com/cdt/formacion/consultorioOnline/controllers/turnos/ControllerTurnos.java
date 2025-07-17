package ar.com.cdt.formacion.consultorioOnline.controllers.turnos;

import ar.com.cdt.formacion.consultorioOnline.dto.TurnoResponse;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/turnos")
public class ControllerTurnos {
    @Autowired
    private ServiceMedico serviceMedico;

    @GetMapping("/{idConsultorio}")
    public ResponseEntity<?> obtenerTurnosPorConsultorioYfecha(@PathVariable int idConsultorio, @RequestParam LocalDate fecha) {
        try {
            List<TurnoResponse> turnos = serviceMedico.obtenerTurnosPorConsultorioYFecha(idConsultorio, fecha);
            return ResponseEntity.ok(turnos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los turnos: " + e.getMessage());
        }
    }

    @GetMapping("/misturnos")
    public ResponseEntity<?> obtenerMisTurnos(@RequestParam int idPaciente) {
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

    @GetMapping("/misturnosmedico")
    public ResponseEntity<?> obtenerMisTurnosMedico(@RequestParam int idMedico) {
        try {
            List<TurnoResponse> turnos = ServiceMedico.obtenerMisTurnosMedico(idMedico);
            return ResponseEntity.ok(turnos);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los turnos: " + e.getMessage());
        }
    }

    @GetMapping("/historicos/{idPaciente}")
    public ResponseEntity<?> obtenerHistoricos(@PathVariable int idPaciente) {

        try {
            List<TurnoResponse> turnos = ServiceMedico.obtenerHistoricos(idPaciente);
            return ResponseEntity.ok(turnos);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los turnos: " + e.getMessage());
        }
    }

    @GetMapping("/historicosmedico/{idMedico}")
    public ResponseEntity<?> obtenerHistoricosMedico(@PathVariable int idMedico) {

        try {
            List<TurnoResponse> turnos = ServiceMedico.obtenerHistoricosMedico(idMedico);
            return ResponseEntity.ok(turnos);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los turnos: " + e.getMessage());
        }
    }


    @PutMapping("/reserva/{idTurno}")
    public ResponseEntity<?> reservarTurno(@PathVariable int idTurno, @RequestParam int fk_paciente) {

        try {
            boolean exito = serviceMedico.reservarTurno(idTurno, fk_paciente);

            if (exito) {
                return ResponseEntity.ok("Turno reservado y evento generado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo reservar el turno.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al reservar turno: " + e.getMessage());
        }
    }

    @PutMapping("/cancelar/{idTurno}")
    public ResponseEntity<?> cancelarTurno(@PathVariable int idTurno) {

        try {
            boolean exito = serviceMedico.cancelarTurno(idTurno);

            if (exito) {
                return ResponseEntity.ok("Turno cancelado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo cancelar el turno.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cancelar turno: " + e.getMessage());
        }
    }
}
