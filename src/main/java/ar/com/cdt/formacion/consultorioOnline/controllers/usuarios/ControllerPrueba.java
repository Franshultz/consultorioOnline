package ar.com.cdt.formacion.consultorioOnline.controllers.usuarios;

import ar.com.cdt.formacion.consultorioOnline.util.GestionadorEmails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mailprueba")
public class ControllerPrueba {

    @Autowired
    private GestionadorEmails gestionadorEmails;

    @PostMapping
    public ResponseEntity<?> pruebamail(@RequestParam String to, @RequestParam String asunto, @RequestParam String texto) {
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        gestionadorEmails.enviarMail(to, asunto, texto);
        return null;
    }
}
