package ar.com.cdt.formacion.consultorioOnline.repositories;

import ar.com.cdt.formacion.consultorioOnline.DTO.MedicoResponse;
import ar.com.cdt.formacion.consultorioOnline.DTO.PacienteResponse;
import ar.com.cdt.formacion.consultorioOnline.models.Medico;
import ar.com.cdt.formacion.consultorioOnline.models.Paciente;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.*;

@Repository
public class RepositoryPaciente {

    public static int guardarPaciente(Paciente paciente) {
        String sql = "INSERT INTO Paciente(cobertura, fk_usuario, fk_agenda) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, paciente.getCobertura());
            statement.setInt(2, paciente.getFk_usuario());
            int fk_agenda = guardarAgendaPaciente();

            statement.setInt(3, fk_agenda);
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

    public static int guardarAgendaPaciente(){
        String sql = "INSERT INTO Agenda(tipo_agenda) VALUES (?)";

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, "paciente");

            int comprobacion = stmt.executeUpdate();

            if (comprobacion > 0) {
                System.out.println("Se agregó exitosamente un agenda");

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Se pudo obtener la clave generada para la agenda");
                        int fk_agenda = generatedKeys.getInt(1);
                        return fk_agenda;
                    } else {
                        System.out.println("No se pudo obtener la clave generada para la agenda");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar agenda: " + e.getMessage());
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
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public static PacienteResponse ObtenerPacienteCompleto(int fk_usuario) {
        PacienteResponse paciente = new PacienteResponse();
        String sqlUsuario = "SELECT * FROM Usuario WHERE id_usuario = ?";
        String sqlPaciente = "SELECT * FROM Paciente WHERE fk_usuario = ?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement stmt = con.prepareStatement(sqlUsuario)) {

            stmt.setInt(1, fk_usuario);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                paciente.setFk_usuario(fk_usuario);
                paciente.setNombre(resultSet.getString("nombre"));
                paciente.setApellido(resultSet.getString("apellido"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setDni(resultSet.getInt("dni"));
                paciente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento").toLocalDate());

                paciente.setGenero(RepositoryUsuario.obtenerGeneroXid(resultSet.getInt("fk_genero")));
                paciente.setEstadoUsuario(RepositoryUsuario.obtenerEstadoUsuarioXid(resultSet.getInt("fk_estado_usuario")));

                try (PreparedStatement stmtPaciente = con.prepareStatement(sqlPaciente)){
                    stmtPaciente.setInt(1, fk_usuario);

                    ResultSet resultSet2 = stmtPaciente.executeQuery();

                    if (resultSet2.next()) {
                        paciente.setIdPaciente(resultSet2.getInt("id_paciente"));
                        paciente.setCobertura(resultSet2.getString("cobertura"));

                        return paciente;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }
}
