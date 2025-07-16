package ar.com.cdt.formacion.consultorioOnline.controllers.usuarios;

import ar.com.cdt.formacion.consultorioOnline.dto.MedicoConsultorioResponse;
import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/medico")
public class ControllerMedico {

    @Autowired ServiceMedico serviceMedico;

    @PostMapping("/consultorios")
    public ResponseEntity<?> crearConsultorio(@RequestBody Consultorio consultorio) {
        try {
            System.out.println("Cargando consultorio: " + consultorio.getNombreConsultorio());
            Consultorio consultorioCreado =  serviceMedico.registrarConsultorio(consultorio);
            return ResponseEntity
                    .status(HttpStatus.CREATED) // 201
                    .body(Map.of(
                            "message", "Consultorio creado correctamente",
                            "consultorio_creado", consultorioCreado
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body("Error inesperado al registrar el consultorio");
        }
    }

    @GetMapping("/consultorios/{idMedico}")
    public ResponseEntity<?> obtenerConsultorios(@PathVariable int idMedico) {
        try {
            System.out.println("ID MEDICO: " + idMedico);
            List<Consultorio> consultorios = serviceMedico.obtenerConsultoriosXidMedico(idMedico);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of(
                            "message", "Consultorios obtenidos correctamente",
                            "consultorios", consultorios
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los consultorios");
        }
    }
}
