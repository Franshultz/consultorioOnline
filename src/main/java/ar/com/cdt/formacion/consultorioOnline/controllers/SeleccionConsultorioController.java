package ar.com.cdt.formacion.consultorioOnline.controllers;

import ar.com.cdt.formacion.consultorioOnline.dto.MedicoConsultorioResponse;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/seleccionconsultorio")
public class SeleccionConsultorioController {

        @GetMapping ("/porespecialidad")
        public ResponseEntity<?> obtenerConsultoriosEspecialiadad (@RequestParam int fk_especialidad) {
            System.out.println("PIPOOOOOOOO GOROSITOOO" + fk_especialidad);
            List<MedicoConsultorioResponse> consultorios = ServiceUsuario.obtenerConsultorioEspecialidad(fk_especialidad);
            System.out.print(consultorios);
            return ResponseEntity.ok(consultorios);
        }
}

