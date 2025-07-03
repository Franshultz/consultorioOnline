package ar.com.cdt.formacion.consultorioOnline.repositories;

import ar.com.cdt.formacion.consultorioOnline.dto.TurnoResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryTurnos {

    public static List<TurnoResponse> obtenerTurnosPorConsultorio(int idConsultorio) {
        String TurnosSql = "SELECT * FROM turno WHERE fk_consultorio = ? AND fk_estado_turno = 1";

        List<TurnoResponse> listaTurnos = new ArrayList<>();

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(TurnosSql)) {

            stmt.setInt(1, idConsultorio);
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
        String TurnosSql = "SELECT * FROM turno WHERE fk_estado_turno = 2 AND fk_paciente = ?";

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
        String sql = "UPDATE turno SET fk_estado_turno = ?, fk_paciente = ? WHERE id_turno = ?";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            int nuevoEstadoReservado = 2; // Por ejemplo, 2 = reservado

            stmt.setInt(1, nuevoEstadoReservado);
            stmt.setInt(2, fkPaciente);  // Asignamos el paciente que reserva
            stmt.setInt(3, idTurno);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
