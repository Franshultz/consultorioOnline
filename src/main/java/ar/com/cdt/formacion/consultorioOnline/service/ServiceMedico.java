package ar.com.cdt.formacion.consultorioOnline.service;


import ar.com.cdt.formacion.consultorioOnline.dto.MedicoConsultorioResponse;
import ar.com.cdt.formacion.consultorioOnline.dto.TurnoResponse;
import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.models.DiaSemana;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryMedico;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryTurnos;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMedico {

    public static Consultorio registrarConsultorio(Consultorio consultorio) {
        Consultorio consultorioCreado = RepositoryMedico.guardarConsultorio(consultorio);

        int minutosLaborales = (int) Duration.between(consultorioCreado.getHorarioLaboralInicio(), consultorioCreado.getHorarioLaboralFin()).toMinutes();
        int cantidadTurnosXdia = minutosLaborales/RepositoryMedico.ObtenerEspecialidadXid(consultorioCreado.getFk_especialidad()).getDuracionTurno();

        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" + cantidadTurnosXdia);
        LocalDate hoy = LocalDate.now();
        LocalDate fin = hoy.plusDays(60);

        List<LocalDateTime> fechasHorarios = new ArrayList<>();

        for (LocalDate fecha = hoy; fecha.isBefore(fin); fecha = fecha.plusDays(1)) {
            int diaSemana = fecha.getDayOfWeek().getValue();

            for (Integer dia : consultorioCreado.getDias()) {
                if (dia.equals(diaSemana)) {
                    LocalTime horarioInicioTurno = consultorio.getHorarioLaboralInicio();
                    int duracion = RepositoryMedico.ObtenerEspecialidadXid(consultorioCreado.getFk_especialidad()).getDuracionTurno();

                    for (int i = 0; i < cantidadTurnosXdia; i++) {
                        fechasHorarios.add(LocalDateTime.of(fecha, horarioInicioTurno));
                        horarioInicioTurno = horarioInicioTurno.plusMinutes(duracion);
                    }
                }
            }
        }

        RepositoryMedico.generarTurnosBatch(consultorioCreado, fechasHorarios);

        return consultorioCreado;
    }




    /*public static Consultorio registrarConsultorio(Consultorio consultorio) {
        Consultorio consultorioCreado = RepositoryMedico.guardarConsultorio(consultorio);

        int minutosLaborales = (int) Duration.between(consultorioCreado.getHorarioLaboralInicio(), consultorioCreado.getHorarioLaboralFin()).toMinutes();
        int cantidadTurnosXdia = minutosLaborales/RepositoryMedico.ObtenerEspecialidadXid(consultorioCreado.getFk_especialidad()).getDuracionTurno();

        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" + cantidadTurnosXdia);
        LocalDate hoy = LocalDate.now();
        LocalDate fin = hoy.plusDays(60);

        for (LocalDate fecha = hoy; fecha.isBefore(fin); fecha = fecha.plusDays(1)) {
            int diaSemana = fecha.getDayOfWeek().getValue();

            for (Integer dia : consultorioCreado.getDias()) {
                if (dia.equals(diaSemana)) {
                    LocalTime horarioInicioTurno = consultorio.getHorarioLaboralInicio();

                    for (int i = 0; i < cantidadTurnosXdia; i++) {
                        RepositoryMedico.generarTurnos(consultorioCreado, horarioInicioTurno, fecha);
                        horarioInicioTurno = horarioInicioTurno.plusMinutes(RepositoryMedico.ObtenerEspecialidadXid(consultorioCreado.getFk_especialidad()).getDuracionTurno());
                    }
                }
            }
        }

        return consultorioCreado;
    }*/

    public static boolean ExisteConsultorioEspecialidad(int fk_medico, int fk_especialidad){
        return RepositoryMedico.verificacionConsultorioDoble(fk_medico, fk_especialidad);
    }

    public static List<Especialidad> capturarListEspecialidades() {
        return RepositoryMedico.ObtenerEspecialidades();
    }

    public static List<Especialidad> obtenerEspecialidadesPorMedico (int idMedico) {
        return RepositoryMedico.obtenerEspecialidadesPorMedico(idMedico);
    }

    public static List<MedicoConsultorioResponse> obtenerConsultoriosPorEspecialidad(int idEspecialidad) {
        return RepositoryMedico.obtenerConsultoriosPorEspecialidad(idEspecialidad);
    }

    public static List<TurnoResponse> obtenerTurnosPorConsultorio(int idConsultorio) {
        return RepositoryTurnos.obtenerTurnosPorConsultorio(idConsultorio);
    }
    public static List<TurnoResponse> obtenerMisTurnos(int fk_paciente) {
        return RepositoryTurnos.obtenerMisTurnos(fk_paciente);
    }

    public static boolean reservarTurno(int idTurno, int fkPaciente) {
        return RepositoryTurnos.reservarTurno(idTurno, fkPaciente);
    }
}
