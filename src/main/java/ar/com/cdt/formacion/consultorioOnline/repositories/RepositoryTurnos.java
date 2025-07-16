package ar.com.cdt.formacion.consultorioOnline.repositories;

import ar.com.cdt.formacion.consultorioOnline.dto.TurnoResponse;
import ar.com.cdt.formacion.consultorioOnline.util.GoogleCalendarMeetService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryTurnos {


    @Autowired
    private GoogleCalendarMeetService calendarService;

    public static List<TurnoResponse> obtenerTurnosPorConsultorioYFecha(int idConsultorio, LocalDate fecha) {
        String TurnosSql = """
        SELECT * FROM Turno
        WHERE fk_consultorio = ?
          AND fk_estado_turno = 1
          AND fecha = ?
    """;

        List<TurnoResponse> listaTurnos = new ArrayList<>();

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(TurnosSql)) {

            stmt.setInt(1, idConsultorio);
            stmt.setDate(2, Date.valueOf(fecha));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaTurnos.add(new TurnoResponse(
                        rs.getInt("id_turno"),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime(),
                        rs.getDate("fecha").toLocalDate(),
                        RepositoryMedico.ObtenerEspecialidadXid(rs.getInt("fk_especialidad")),
                        RepositoryMedico.obtenerMedicoDatosSimples(rs.getInt("fk_medico")),
                        RepositoryMedico.obtenerConsultorioXid(idConsultorio),
                        rs.getInt("fk_estado_turno")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaTurnos;
    }


    public static List<TurnoResponse> obtenerMisTurnos(int fk_paciente) {
        String TurnosSql = "SELECT * FROM Turno WHERE fk_estado_turno = 2 AND fk_paciente = ?";

        System.out.println("Buscando turnos para paciente: ooooooooooooooooooooOOOOOOOOOOOOOOOOOOO" + fk_paciente);


        List<TurnoResponse> listaTurnos = new ArrayList<>();

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(TurnosSql)) {

            stmt.setInt(1, fk_paciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaTurnos.add(new TurnoResponse(
                        rs.getInt("id_turno"),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime(),
                        rs.getDate("fecha").toLocalDate(),
                        RepositoryMedico.ObtenerEspecialidadXid(rs.getInt("fk_especialidad")),
                        RepositoryMedico.obtenerMedicoDatosSimples(rs.getInt("fk_medico")),
                        RepositoryMedico.obtenerConsultorioXid(rs.getInt("fk_consultorio")),
                        rs.getInt("fk_estado_turno")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaTurnos;
    }

    public static List<TurnoResponse> obtenerHistoricos(int fk_paciente) {
        String TurnosSql = "SELECT * FROM Turno WHERE fk_estado_turno = 5 AND fk_paciente = ?";

        System.out.println("Buscando turnos para paciente: ooooooooooooooooooooOOOOOOOOOOOOOOOOOOO" + fk_paciente);


        List<TurnoResponse> listaTurnos = new ArrayList<>();

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(TurnosSql)) {

            stmt.setInt(1, fk_paciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaTurnos.add(new TurnoResponse(
                        rs.getInt("id_turno"),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_fin").toLocalTime(),
                        rs.getDate("fecha").toLocalDate(),
                        RepositoryMedico.ObtenerEspecialidadXid(rs.getInt("fk_especialidad")),
                        RepositoryMedico.obtenerMedicoDatosSimples(rs.getInt("fk_medico")),
                        RepositoryMedico.obtenerConsultorioXid(rs.getInt("fk_consultorio")),
                        rs.getInt("fk_estado_turno")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaTurnos;
    }



    public static boolean reservarTurno(int idTurno, int fkPaciente) {
        String sql = "UPDATE Turno SET fk_estado_turno = ?, fk_paciente = ? WHERE id_turno = ?";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            int nuevoEstadoReservado = 2;
            stmt.setInt(1, nuevoEstadoReservado);
            stmt.setInt(2, fkPaciente);
            stmt.setInt(3, idTurno);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cancelarTurno(int idTurno) {
        String sql = "UPDATE Turno SET fk_estado_turno = ?, fk_paciente = ?, enlace = ? WHERE id_turno = ?";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            int nuevoEstadoDisponible = 1;
            stmt.setInt(1, nuevoEstadoDisponible);
            stmt.setNull(2, java.sql.Types.INTEGER);
            stmt.setNull(3, java.sql.Types.VARCHAR);
            stmt.setInt(4, idTurno);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarEnlaceMeet(int idTurno, String enlaceMeet) {
        String sql = "UPDATE Turno SET enlace = ? WHERE id_turno = ?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, enlaceMeet);
            stmt.setInt(2, idTurno);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void actualizarEstadoTurnosVencidos() {
        String sql = """
            UPDATE Turno
            SET fk_estado_turno = 5
            WHERE fk_estado_turno IN (1, 2)
                AND (
                    fecha < CURRENT_DATE
                    OR (fecha = CURRENT_DATE AND hora_inicio < CURRENT_TIME)
                )
        """;

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ZonedDateTime[] obtenerHorario(int idTurno) {
        String sqlHorarios = "SELECT fecha, hora_inicio, hora_fin FROM Turno WHERE id_turno = ?";

        ZonedDateTime[] horarios = new ZonedDateTime[2];

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sqlHorarios)) {

            // Setear el parámetro
            stmt.setInt(1, idTurno);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                LocalTime horaInicio = rs.getTime("hora_inicio").toLocalTime();
                LocalTime horaFin = rs.getTime("hora_fin").toLocalTime();

                // Zona horaria deseada
                ZoneId zona = ZoneId.of("America/Argentina/Buenos_Aires");

                horarios[0] = ZonedDateTime.of(fecha, horaInicio, zona);
                horarios[1] = ZonedDateTime.of(fecha, horaFin, zona);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener horarios del turno", e);
        }

        return horarios;
    }


}
