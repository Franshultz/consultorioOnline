package ar.com.cdt.formacion.consultorioOnline.controllers.resources;

import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

    @GetMapping("/sexos")
    public ResponseEntity<?> obtenerSexos() {
        try {
            return ResponseEntity.ok(ServiceUsuario.capturarListGeneros());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
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

    @GetMapping("/dias")
    public ResponseEntity<?> obtenerDias() {
        try {
            return ResponseEntity.ok(ServiceUsuario.capturarListDias());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
