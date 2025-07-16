package ar.com.cdt.formacion.consultorioOnline.controllers.consultorios;

import ar.com.cdt.formacion.consultorioOnline.dto.MedicoConsultorioResponse;
import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioController {

    @Autowired ServiceUsuario serviceUsuario;

    @GetMapping
    public ResponseEntity<?> obtenerConsultoriosXEspecialiadad (@RequestParam(name = "idEspecialidad", required = false) Integer idEspecialidad) {
        List<MedicoConsultorioResponse> consultorios = serviceUsuario.obtenerConsultorioEspecialidad(idEspecialidad);
        System.out.print(consultorios);
        return ResponseEntity.ok(consultorios);
    }
}

