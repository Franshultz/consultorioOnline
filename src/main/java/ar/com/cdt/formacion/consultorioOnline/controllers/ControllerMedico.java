package ar.com.cdt.formacion.consultorioOnline.controllers;

import ar.com.cdt.formacion.consultorioOnline.dto.MedicoConsultorioResponse;
import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/medico")
public class ControllerMedico {

    @GetMapping ("consultorio/existe")
    public ResponseEntity<?> ExisteConsultorioEspecialidad (@RequestParam int fk_medico, @RequestParam int fk_especialidad) {
        try {
            boolean existe = ServiceMedico.ExisteConsultorioEspecialidad(fk_medico, fk_especialidad);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body("Error inesperado al registrar el consultorio");
        }
    }

    @PostMapping("/consultorio")
    public ResponseEntity<?> crearConsultorio(@RequestBody Consultorio consultorio) {
        try {
            System.out.println("Cargando consultorio: " + consultorio.getNombreConsultorio());
            Consultorio consultorioCreado =  ServiceMedico.registrarConsultorio(consultorio);
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

    @GetMapping("/consultorios-especialidad/{idEspecialidad}")
    public ResponseEntity<List<MedicoConsultorioResponse>> obtenerConsultoriosPorEspecialidad(@PathVariable int idEspecialidad) {
        List<MedicoConsultorioResponse> consultorios = ServiceMedico.obtenerConsultoriosPorEspecialidad(idEspecialidad);

        if (consultorios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(consultorios);
    }


    @GetMapping("/especialidades")
    public List<Especialidad> obtenerEspecialidades() {
        try {
            return ServiceMedico.capturarListEspecialidades();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}/especialidades")
    public List<Especialidad> getEspecialidadesPorMedico(@PathVariable int idMedico) {
        return ServiceMedico.obtenerEspecialidadesPorMedico(idMedico);
    }
}
