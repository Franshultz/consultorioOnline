package ar.com.cdt.formacion.consultorioOnline.controllers;

import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.models.Genero;
import ar.com.cdt.formacion.consultorioOnline.models.Medico;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/medico")
public class ControllerMedico {


    @PostMapping("/consultorio")
    public boolean crearConsultorio(@RequestBody List<Consultorio> listaConsultorio) {
        try {
            for (Consultorio consultorio : listaConsultorio) {
                System.out.println("Cargando consultorio: " + consultorio.getNombreConsultorio());
                ServiceMedico.registrarConsultorio(consultorio);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
}
