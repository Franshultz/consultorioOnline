package ar.com.cdt.formacion.consultorioOnline.repositories;

import ar.com.cdt.formacion.consultorioOnline.dto.PacienteResponse;
import ar.com.cdt.formacion.consultorioOnline.exceptions.DatabaseException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.PacienteNoEncontradoException;
import ar.com.cdt.formacion.consultorioOnline.models.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class RepositoryPaciente {

    public static int guardarPaciente(Paciente paciente) {
        String sql = "INSERT INTO Paciente(cobertura, fk_usuario) VALUES (?, ?)";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, paciente.getCobertura());
            statement.setInt(2, paciente.getFk_usuario());

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Se agregó el paciente exitosamente");
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("ID generado: " + rs.getInt(1));
                    return rs.getInt(1);
                }
            } else {
                System.out.println("Se creó el usuario, pero no se pudo agregar el paciente");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean existePaciente(int fk_usuario) {
        String sqlPaciente = "SELECT * FROM Paciente WHERE fk_usuario = ?";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmtPaciente = con.prepareStatement(sqlPaciente)) {
            stmtPaciente.setInt(1, fk_usuario);

            ResultSet rsPaciente = stmtPaciente.executeQuery();
            if (rsPaciente.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al acceder a la base de datos", e);
        }
        return false;
    }


    public static PacienteResponse ObtenerPacienteCompleto(int fk_usuario) {
        String sqlUsuario = "SELECT * FROM Usuario WHERE id_usuario = ?";
        String sqlPaciente = "SELECT * FROM Paciente WHERE fk_usuario = ?";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sqlUsuario)) {

            stmt.setInt(1, fk_usuario);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                PacienteResponse paciente = new PacienteResponse();
                paciente.setFk_usuario(fk_usuario);
                paciente.setNombre(resultSet.getString("nombre"));
                paciente.setApellido(resultSet.getString("apellido"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setDni(resultSet.getInt("dni"));
                paciente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento").toLocalDate());
                paciente.setGenero(RepositoryUsuario.obtenerGeneroXid(resultSet.getInt("fk_genero")));
                paciente.setEstadoUsuario(RepositoryUsuario.obtenerEstadoUsuarioXid(resultSet.getInt("fk_estado_usuario")));

                try (PreparedStatement stmtPaciente = con.prepareStatement(sqlPaciente)) {
                    stmtPaciente.setInt(1, fk_usuario);
                    ResultSet resultSet2 = stmtPaciente.executeQuery();

                    if (resultSet2.next()) {
                        paciente.setIdPaciente(resultSet2.getInt("id_paciente"));
                        paciente.setCobertura(resultSet2.getString("cobertura"));

                        return paciente;
                    } else {
                        throw new PacienteNoEncontradoException("Paciente no encontrado para el usuario ID " + fk_usuario);
                    }
                }

            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al acceder a la base de datos al obtener paciente completo", e);
        }
        throw new IllegalStateException("Flujo no alcanzable en iniciarSesion");
    }



    public static String obtenerCoberturaXid(int idUsuario) {
        String sqlCobertura = "SELECT cobertura FROM Paciente WHERE fk_usuario=?";
        String cobertura= "";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sqlCobertura)) {
            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return cobertura = rs.getString("cobertura");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cobertura;
    }


    public void guardarRefreshToken(int fkPaciente, String refreshToken) {
        String sql = "UPDATE Paciente SET google_refresh_token = ? WHERE id_paciente = ?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, refreshToken);
            stmt.setInt(2, fkPaciente);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarAccessToken(int fkPaciente, String accessToken) {
        String sql = "UPDATE Paciente SET google_access_token = ? WHERE id_paciente = ?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, accessToken);
            stmt.setInt(2, fkPaciente);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String obtenerRefreshToken(int fkPaciente) {
        String sql = "SELECT google_refresh_token FROM Paciente WHERE id_paciente = ?";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, fkPaciente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("google_refresh_token");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // si no se encuentra
    }
}
