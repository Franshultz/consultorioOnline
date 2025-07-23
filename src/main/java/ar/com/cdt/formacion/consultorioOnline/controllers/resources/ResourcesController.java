package ar.com.cdt.formacion.consultorioOnline.controllers.resources;

import ar.com.cdt.formacion.consultorioOnline.dto.RefreshTokenResponse;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.models.Usuario;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryUsuario;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/token-existe")
    public ResponseEntity<?> tieneRefreshToken(@RequestParam int idUsuario) {
        String refreshToken = RepositoryUsuario.obtenerRefreshToken(idUsuario);

        if (refreshToken != null && !refreshToken.isEmpty()) {
            return ResponseEntity.ok(new RefreshTokenResponse(refreshToken));
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
