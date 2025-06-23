package ar.com.cdt.formacion.consultorioOnline.service;


import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.models.Genero;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryMedico;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMedico {

    public static Consultorio registrarConsultorio(Consultorio consultorio) {
        return RepositoryMedico.guardarConsultorio(consultorio);
    }

    public static boolean ExisteConsultorioEspecialidad(int fk_medico, int fk_especialidad){
        return RepositoryMedico.verificacionConsultorioDoble(fk_medico, fk_especialidad);
    }

    public static List<Especialidad> capturarListEspecialidades() {
        return RepositoryMedico.ObtenerEspecialidades();
    }

    public static List<Especialidad> obtenerEspecialidadesPorMedico (int idMedico) {
        return RepositoryMedico.obtenerEspecialidadesPorMedico(idMedico);
    }
}
