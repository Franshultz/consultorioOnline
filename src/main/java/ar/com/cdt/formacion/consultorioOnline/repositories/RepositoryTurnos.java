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
        String TurnosSql = "SELECT * FROM turno WHERE fk_consultorio = ?";

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


}
