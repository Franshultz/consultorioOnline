package ar.com.cdt.formacion.consultorioOnline.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import ar.com.cdt.formacion.consultorioOnline.dto.MedicoResponse;
import ar.com.cdt.formacion.consultorioOnline.exceptions.DatabaseException;
import ar.com.cdt.formacion.consultorioOnline.exceptions.MedicoNoEncontradoException;
import ar.com.cdt.formacion.consultorioOnline.models.*;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryMedico {

	public static boolean verificacionConsultorioDoble(int fk_medico, int fk_especialidad ){
		String validarEspecialidadSql = "SELECT COUNT(*) FROM Consultorio WHERE fk_medico = ? AND fk_especialidad = ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmtValidacion = con.prepareStatement(validarEspecialidadSql)){
			stmtValidacion.setInt(1, fk_medico);
			stmtValidacion.setInt(2, fk_especialidad);

			ResultSet rsValidar = stmtValidacion.executeQuery();

			if (rsValidar.next() && rsValidar.getInt(1) > 0) {
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
        return false;
    }

	public static Consultorio guardarConsultorio(Consultorio consultorio) {

		String sqlAgenda = "INSERT INTO Agenda(tipo_agenda) VALUES (?)";
		String sqlConsultorio = "INSERT INTO Consultorio(nombre_consultorio, horario_laboral_inicio, horario_laboral_fin, fk_medico, fk_especialidad, fk_agenda) VALUES (?, ?, ?, ?, ?, ?)";
		String sqlConsultorio_Medico = "INSERT INTO Consultorio_Medico(fk_consultorio, fk_medico) VALUES (?, ?)";
		String sqlConsultorio_Dias = "INSERT INTO Consultorio_Dias(fk_consultorio, fk_dias) VALUES (?, ?)";



			try (Connection con = Conexion.getInstancia().getConexion();
				 PreparedStatement stmtAgenda = con.prepareStatement(sqlAgenda, Statement.RETURN_GENERATED_KEYS)) {

				stmtAgenda.setString(1, "medico");

				int filasAgenda = stmtAgenda.executeUpdate();
				if (filasAgenda > 0) {
					try (ResultSet generatedKeys = stmtAgenda.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							int fk_agenda = generatedKeys.getInt(1);

							Time inicio = Time.valueOf(consultorio.getHorarioLaboralInicio() + ":00");  // convierte "08:00" en "08:00:00"

							Time fin = Time.valueOf(consultorio.getHorarioLaboralFin() + ":00");

							try (PreparedStatement stmtConsultorio = con.prepareStatement(sqlConsultorio, Statement.RETURN_GENERATED_KEYS)) {
								stmtConsultorio.setString(1, consultorio.getNombreConsultorio());
								stmtConsultorio.setTime(2, inicio);
								stmtConsultorio.setTime(3, fin);
								stmtConsultorio.setInt(4, consultorio.getFk_medico());
								stmtConsultorio.setInt(5, consultorio.getFk_especialidad());
								stmtConsultorio.setInt(6, fk_agenda);

								int filasConsultorio = stmtConsultorio.executeUpdate();
								if (filasConsultorio > 0) {
									System.out.println("Consultorio guardado correctamente");
									ResultSet generatedKeys2 = stmtConsultorio.getGeneratedKeys();
									if (generatedKeys2.next()) {
										int fk_consultorio = generatedKeys2.getInt(1);

										try (PreparedStatement stmtConsultorioMedico = con.prepareStatement(sqlConsultorio_Medico)) {
											stmtConsultorioMedico.setInt(1, fk_consultorio);
											stmtConsultorioMedico.setInt(2, consultorio.getFk_medico());
											stmtConsultorioMedico.executeUpdate();
										}

										try (PreparedStatement stmtConsultorioDias = con.prepareStatement(sqlConsultorio_Dias)) {
											for (int dia : consultorio.getDias()) {
												stmtConsultorioDias.setInt(1, fk_consultorio);
												stmtConsultorioDias.setInt(2, dia);
												stmtConsultorioDias.executeUpdate();
											}
										}

										consultorio.setIdConsultorio(fk_consultorio);
										return consultorio;
									}
								}
							}
						}
					}
				}
			} catch (SQLException e) {
				System.out.println("Error al agregar: " + e.getMessage());
				e.printStackTrace();
				throw new RuntimeException("Error al guardar consultorio: " + e.getMessage(), e);
			}
			return null;
	}



	public static int guardarMedico(Medico medico) {
		String sql = "INSERT INTO Medico(matricula, fk_usuario) VALUES (?, ?)";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, medico.getMatricula());
			statement.setInt(2, medico.getFk_usuario());

			int filasInsertadas = statement.executeUpdate();

			if (filasInsertadas > 0) {
				System.out.println("Se agregó el medico exitosamente");
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					System.out.println("ID generado: " + rs.getInt(1));
					return rs.getInt(1);
				}
			} else {
				System.out.println("Se creó el usuario, pero no se pudo agregar el medico");
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}
        return 0;
    }

	public static boolean validacionMedicoExiste(Medico medico) {
		String sqlUsuario = "SELECT id_usuario FROM Usuario WHERE email = ? AND dni = ?";
		String sqlMedico = "SELECT * FROM Medico WHERE fk_usuario = ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmtUsuario = con.prepareStatement(sqlUsuario)) {

			stmtUsuario.setString(1, medico.getEmail());
			stmtUsuario.setInt(2, medico.getDni());

			ResultSet rsUsuario = stmtUsuario.executeQuery();

			if (rsUsuario.next()) {
				int fk_usuario = rsUsuario.getInt("id_usuario");

				try (PreparedStatement stmtMedico = con.prepareStatement(sqlMedico)) {
					stmtMedico.setInt(1, fk_usuario);

					ResultSet rsMedico = stmtMedico.executeQuery();
					if (rsMedico.next()) {
						return true;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean existeMedico(int fk_usuario) {
		String sqlMedico = "SELECT * FROM Medico WHERE fk_usuario = ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmtMedico = con.prepareStatement(sqlMedico)) {
			stmtMedico.setInt(1, fk_usuario);

			ResultSet rsMedico = stmtMedico.executeQuery();
			if (rsMedico.next()) {
				return true;
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error al acceder a la base de datos", e);
		}
		return false;
	}


	public static List<Especialidad> ObtenerEspecialidades() {
		String sql = "SELECT * FROM Especialidad";
		List<Especialidad> listaEspecialidades = new ArrayList<Especialidad>();

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next()) {
				listaEspecialidades.add(new Especialidad (
						resultSet.getInt("id_especialidad"),
						resultSet.getString("especialidad"),
						resultSet.getInt("duracion_turno")
				));
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}

		return listaEspecialidades;
	}



	public static Especialidad ObtenerEspecialidadXid(int id_especialidad) {
		String sql = "SELECT * FROM Especialidad WHERE id_especialidad= ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id_especialidad);
			ResultSet resultSet = stmt.executeQuery();

			if(resultSet.next()) {
				return new Especialidad (
						resultSet.getInt("id_especialidad"),
						resultSet.getString("especialidad"),
						resultSet.getInt("duracion_turno")
				);
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public static Agenda ObtenerAgendaXid(int id_agenda) {
		String sql = "SELECT * FROM Agenda WHERE id_agenda= ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id_agenda);
			ResultSet resultSet = stmt.executeQuery();

			if(resultSet.next()) {
				return new Agenda (
						resultSet.getInt("id_agenda"),
						resultSet.getString("tipo_agenda")
				);
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public static DiaSemana ObtenerDiaSemanaXid(int id_dia) {
		String sql = "SELECT * FROM Dias WHERE id= ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id_dia);
			ResultSet resultSet = stmt.executeQuery();

			if(resultSet.next()) {
				return new DiaSemana (
						resultSet.getInt("id"),
						resultSet.getString("dia")
				);
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public static List<DiaSemana> ObtenerDiasSemanaXconsultorio(int fk_consultorio) {
		String sql = "SELECT * FROM Consultorio_Dias WHERE fk_consultorio=?";
		List<DiaSemana> listaDias = new ArrayList<DiaSemana>();

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, fk_consultorio);
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next()) {
				listaDias.add(
						ObtenerDiaSemanaXid(resultSet.getInt("fk_dias"))
				);
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}

		return listaDias;
	}


	public static List<Consultorio> ObtenerListaConsultoriosXid(int fk_medico) {
		String sql = "SELECT * FROM Consultorio WHERE fk_medico=?";
		List<Consultorio> listaConsultorios = new ArrayList<Consultorio>();

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, fk_medico);
			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next()) {
				listaConsultorios.add(new Consultorio (
						resultSet.getInt("id_consultorio"),
						resultSet.getString("nombre_consultorio"),
						resultSet.getString("horario_laboral_inicio").toString(),
						resultSet.getString("horario_laboral_fin").toString(),
						fk_medico,
						ObtenerEspecialidadXid(resultSet.getInt("fk_especialidad")),
						ObtenerAgendaXid(resultSet.getInt("fk_agenda")),
						ObtenerDiasSemanaXconsultorio(resultSet.getInt("id_consultorio")))
				);
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}

		return listaConsultorios;
	}






	public static MedicoResponse ObtenerMedicoCompleto(int fk_usuario) {
		MedicoResponse medico = new MedicoResponse();
		String sqlUsuario = "SELECT * FROM Usuario WHERE id_usuario = ?";
		String sqlMedico = "SELECT * FROM Medico WHERE fk_usuario = ?";
		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sqlUsuario)) {

			stmt.setInt(1, fk_usuario);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				medico.setFk_usuario(fk_usuario);
				medico.setNombre(resultSet.getString("nombre"));
				medico.setApellido(resultSet.getString("apellido"));
				medico.setEmail(resultSet.getString("email"));
				medico.setDni(resultSet.getInt("dni"));
				medico.setFechaNacimiento(resultSet.getDate("fecha_nacimiento").toLocalDate());

				medico.setGenero(RepositoryUsuario.obtenerGeneroXid(resultSet.getInt("fk_genero")));
				medico.setEstadoUsuario(RepositoryUsuario.obtenerEstadoUsuarioXid(resultSet.getInt("fk_estado_usuario")));

				try (PreparedStatement stmtMedico = con.prepareStatement(sqlMedico)){
					stmtMedico.setInt(1, fk_usuario);

					ResultSet resultSet2 = stmtMedico.executeQuery();

					if (resultSet2.next()) {
						medico.setIdMedico(resultSet2.getInt("id_medico"));
						medico.setMatricula(resultSet2.getString("matricula"));
						medico.setListaConsultorio(ObtenerListaConsultoriosXid(resultSet2.getInt("id_medico")));

						return medico;
					} else {
						throw new MedicoNoEncontradoException("Medico no encontrado para el usuario ID " + fk_usuario);
					}
				}
			}

		} catch (SQLException e) {
			throw new DatabaseException("Error al acceder a la base de datos", e);
		}
		throw new IllegalStateException("Flujo no alcanzable en iniciarSesion");
    }

	public static List<Especialidad> obtenerEspecialidadesPorMedico(int idMedico) {
		List<Especialidad> especialidades = new ArrayList<>();
		String sql = "SELECT e.id_especialidad, e.especialidad, e.duracion_turno " +
				"FROM Consultorio c " +
				"JOIN Especialidad e ON c.fk_especialidad = e.id_especialidad " +
				"WHERE c.fk_medico = ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, idMedico);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Especialidad esp = new Especialidad();
				esp.setIdEspecialidad(rs.getInt("id_especialidad"));
				esp.setEspecialidad(rs.getString("especialidad"));
				esp.setDuracionTurno(rs.getInt("duracion_turno"));
				especialidades.add(esp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return especialidades;
	}


	public static String obtenerMatriculaXid(int idUsuario) {
		String sqlMatricula = "SELECT matricula FROM Medico WHERE fk_usuario=?";
		String matricula= "";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sqlMatricula)) {
			stmt.setInt(1, idUsuario);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return matricula = rs.getString("matricula");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
        return matricula;
    }

}
