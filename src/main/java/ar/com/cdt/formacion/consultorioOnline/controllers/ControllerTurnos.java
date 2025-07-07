package ar.com.cdt.formacion.consultorioOnline.controllers;

import ar.com.cdt.formacion.consultorioOnline.dto.TurnoResponse;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import com.google.api.client.auth.oauth2.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/turnos")
public class ControllerTurnos {
    @Autowired
    private ServiceMedico serviceMedico;

    @GetMapping("/consultorio")
    public ResponseEntity<?> obtenerTurnosPorConsultorioYfecha(@RequestParam int idConsultorio, @RequestParam LocalDate fecha
    ) {
        try {
            List<TurnoResponse> turnos = ServiceMedico.obtenerTurnosPorConsultorioYFecha(idConsultorio, fecha);
            return ResponseEntity.ok(turnos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los turnos: " + e.getMessage());
        }
    }

    @GetMapping("/misTurnos")
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

    @GetMapping("/historicos")
    public ResponseEntity<?> obtenerHistoricos(@RequestParam int idPaciente) {
        System.out.println("palalsaplspalspalsplapslapslaplspala" + idPaciente);

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


    @PutMapping("/reserva")
    public ResponseEntity<?> reservarTurno(@RequestParam int idTurno, @RequestParam int fk_paciente) {

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
}
