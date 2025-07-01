package ar.com.cdt.formacion.consultorioOnline.controllers;

import ar.com.cdt.formacion.consultorioOnline.dto.TurnoResponse;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
