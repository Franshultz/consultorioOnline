package ar.com.cdt.formacion.consultorioOnline.service;

import ar.com.cdt.formacion.consultorioOnline.dto.MedicoConsultorioResponse;
import ar.com.cdt.formacion.consultorioOnline.dto.TurnoResponse;
import ar.com.cdt.formacion.consultorioOnline.models.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.models.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryMedico;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryPaciente;
import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryTurnos;
import ar.com.cdt.formacion.consultorioOnline.util.GoogleCalendarMeetService;
import com.google.api.client.auth.oauth2.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMedico {

    @Autowired
    private GoogleCalendarMeetService calendarService;

    @Autowired
    private RepositoryPaciente repositoryPaciente;

    public void cargarFoto(int id_medico, MultipartFile foto) {
        RepositoryMedico.cargarFoto(id_medico, foto);
    }

    public Consultorio registrarConsultorio(Consultorio consultorio) {
        Consultorio consultorioCreado = RepositoryMedico.guardarConsultorio(consultorio);

        int minutosLaborales = (int) Duration.between(consultorioCreado.getHorarioLaboralInicio(), consultorioCreado.getHorarioLaboralFin()).toMinutes();
        int cantidadTurnosXdia = minutosLaborales/RepositoryMedico.ObtenerEspecialidadXid(consultorioCreado.getFk_especialidad()).getDuracionTurno();

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


    public static boolean ExisteConsultorioEspecialidad(int fk_medico, int fk_especialidad){
        return RepositoryMedico.verificacionConsultorioDoble(fk_medico, fk_especialidad);
    }

    public static List<Especialidad> capturarListEspecialidades() {
        return RepositoryMedico.ObtenerEspecialidades();
    }

    public static List<Especialidad> obtenerEspecialidadesPorMedico (int idMedico) {
        return RepositoryMedico.obtenerEspecialidadesPorMedico(idMedico);
    }

    public List<MedicoConsultorioResponse> obtenerConsultoriosPorEspecialidad(int idEspecialidad) {
        return RepositoryMedico.obtenerConsultoriosPorEspecialidad(idEspecialidad);
    }

    public List<TurnoResponse> obtenerTurnosPorConsultorioYFecha(int idConsultorio, LocalDate fecha) {
        RepositoryTurnos.actualizarEstadoTurnosVencidos();

        return RepositoryTurnos.obtenerTurnosPorConsultorioYFecha(idConsultorio, fecha);
    }

    public List<TurnoResponse> obtenerTurnosMedicoPorConsultorioYFecha(int idConsultorio, LocalDate fecha) {
        RepositoryTurnos.actualizarEstadoTurnosVencidos();

        return RepositoryTurnos.obtenerTurnosMedicoPorConsultorioYFecha(idConsultorio, fecha);
    }

    public static List<TurnoResponse> obtenerMisTurnos(int fk_paciente) {
        RepositoryTurnos.actualizarEstadoTurnosVencidos();

        return RepositoryTurnos.obtenerMisTurnos(fk_paciente);
    }

    public static List<TurnoResponse> obtenerMisTurnosMedico(int idMedico) {
        RepositoryTurnos.actualizarEstadoTurnosVencidos();

        return RepositoryTurnos.obtenerMisTurnosMedico(idMedico);
    }

    public static List<TurnoResponse> obtenerHistoricos(int fk_paciente) {
        RepositoryTurnos.actualizarEstadoTurnosVencidos();

        return RepositoryTurnos.obtenerHistoricos(fk_paciente);
    }

    public static List<TurnoResponse> obtenerHistoricosMedico(int idMedico) {
        RepositoryTurnos.actualizarEstadoTurnosVencidos();

        return RepositoryTurnos.obtenerHistoricosMedico(idMedico);
    }

    public boolean reservarTurno(int idTurno, int fkPaciente) {
        boolean reservado = RepositoryTurnos.reservarTurno(idTurno, fkPaciente);
        if (!reservado) return false;

        try {
            ZonedDateTime[] horarios = RepositoryTurnos.obtenerHorario(idTurno);
            ZonedDateTime inicio = horarios[0];
            ZonedDateTime fin = horarios[1];

            int idMedico = RepositoryTurnos.obtenerIdMedicoPorTurno(idTurno);

            int idUsuarioMedico = RepositoryMedico.obtenerFkUsuarioPorIdMedico(idMedico);
            int idUsuarioPaciente = RepositoryMedico.obtenerFkUsuarioPorIdPaciente(fkPaciente);

            String refreshTokenMedico = RepositoryMedico.obtenerRefreshTokenPorId(idUsuarioMedico);
            String refreshTokenPaciente = RepositoryMedico.obtenerRefreshTokenPorId(idUsuarioPaciente);

            if (refreshTokenMedico == null || refreshTokenPaciente == null) {
                System.out.println("Alguno de los usuarios no autorizó Google Calendar");
                return false;
            }

            Credential credMedico = calendarService.crearCredentialConRefreshToken(refreshTokenMedico);
            Credential credPaciente = calendarService.crearCredentialConRefreshToken(refreshTokenPaciente);

            String enlaceMeet = calendarService.crearEventoConMeet(credMedico, "Turno médico", inicio, fin);

            calendarService.crearEventoConMeetCompartido(credPaciente, "Turno médico", inicio, fin, enlaceMeet);

            return RepositoryTurnos.actualizarEnlaceMeet(idTurno, enlaceMeet);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean deshabilitarTurno(int idTurno){
        boolean done = RepositoryTurnos.deshabilitarTurno(idTurno);
        return done;
    }

    public static boolean habilitarTurno(int idTurno){
        boolean done = RepositoryTurnos.habilitarTurno(idTurno);
        return done;
    }

    public static  boolean cancelarTurno(int idTurno) {
        boolean cancelado = RepositoryTurnos.cancelarTurno(idTurno);
        return cancelado;
    }

    public List<Consultorio> obtenerConsultoriosXidMedico(int idMedico) {

        return RepositoryMedico.ObtenerListaConsultoriosXid(idMedico);
    }


}
