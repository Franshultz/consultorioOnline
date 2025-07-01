package ar.com.cdt.formacion.consultorioOnline.repositories;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.cdt.formacion.consultorioOnline.dto.UsuarioAutocompletadoResponse;
import ar.com.cdt.formacion.consultorioOnline.exceptions.DatabaseException;
import ar.com.cdt.formacion.consultorioOnline.models.*;
import ar.com.cdt.formacion.consultorioOnline.util.EncriptarClave;
import org.springframework.stereotype.Repository;

@Repository
public class  RepositoryUsuario {

	public static int guardarUsuarioMedico(Medico medico) {
		String sql = "INSERT INTO Usuario(nombre, apellido, email, clave, dni, fecha_nacimiento, fk_genero, fk_estado_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, medico.getNombre());
			stmt.setString(2, medico.getApellido());
			stmt.setString(3, medico.getEmail());
			stmt.setString(4, EncriptarClave.encriptar(medico.getClave()));
			stmt.setInt(5, medico.getDni());
			stmt.setDate(6, Date.valueOf(medico.getFechaNacimiento()));
			stmt.setInt(7, medico.getGenero_fk());
			stmt.setInt(8, medico.getEstadoUsuario_fk());

			int comprobacion = stmt.executeUpdate();

			if (comprobacion > 0) {
				System.out.println("Se agregó exitosamente un usuario");

				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						System.out.println("Se pudo obtener la clave generada para el usuario");
						int fk_usuario = generatedKeys.getInt(1);
						medico.setFk_usuario(fk_usuario);
						return RepositoryMedico.guardarMedico(medico);
					} else {
						System.out.println("No se pudo obtener la clave generada para el usuario");
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}
        return 0;
    }

	public static int guardarUsuarioPaciente(Paciente paciente) {
		String sql = "INSERT INTO Usuario(nombre, apellido, email, clave, dni, fecha_nacimiento, fk_genero, fk_estado_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		System.out.println(paciente.getGenero_fk());
		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, paciente.getNombre());
			stmt.setString(2, paciente.getApellido());
			stmt.setString(3, paciente.getEmail());
			stmt.setString(4, EncriptarClave.encriptar(paciente.getClave()));
			stmt.setInt(5, paciente.getDni());
			stmt.setDate(6, Date.valueOf(paciente.getFechaNacimiento()));
			stmt.setInt(7, paciente.getGenero_fk());
			stmt.setInt(8, paciente.getEstadoUsuario_fk());

			int comprobacion = stmt.executeUpdate();

			if (comprobacion > 0) {
				System.out.println("Usuario paciente registrado correctamente");
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {

						int fk_usuario = generatedKeys.getInt(1);
						paciente.setFk_usuario(fk_usuario);
						return RepositoryPaciente.guardarPaciente(paciente);
					} else {
						System.out.println("No se pudo obtener la clave generada para el usuario");
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}
        return 0;
    }

	public static List<Genero> ObtenerGeneros() {
		String sql = "SELECT * FROM Genero";
		List<Genero> listaGeneros = new ArrayList<Genero>();

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next()) {
				listaGeneros.add(new Genero (
						resultSet.getInt("id_genero"),
						resultSet.getString("genero")
				));
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}

		return listaGeneros;
	}

	public static boolean existeUsuarioXdni(int dni, String clave) {
		String claveEncriptada = EncriptarClave.encriptar(clave);
		String sql = "SELECT id_usuario FROM Usuario WHERE dni = ? AND clave = ?";
		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, dni);
			stmt.setString(2, claveEncriptada);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Boca moriste en madrid");
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new DatabaseException("Error al acceder a la base de datos", e);
		}
	}


	public static boolean existeUsuario(String email, int dni) {
		String sql = "SELECT id_usuario FROM Usuario WHERE email = ? AND dni = ?";
		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, email);
			stmt.setInt(2, dni);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public static int obtenerIdUsuario(String email, int dni) {
		String sql = "SELECT id_usuario FROM Usuario WHERE email = ? AND dni = ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, email);
			stmt.setInt(2, dni);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_usuario");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static int obtenerIdUsuarioXdni(int dni, String clave) {
		String sql = "SELECT id_usuario FROM Usuario WHERE dni = ? AND clave = ?";

		String claveEncriptada = EncriptarClave.encriptar(clave);
		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, dni);
			stmt.setString(2, claveEncriptada);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt("id_usuario"));
				return rs.getInt("id_usuario");
			}

		} catch (SQLException e) {
			throw new DatabaseException("Error al acceder a la base de datos", e);
		}

		return -1;
	}


	public static List<DiaSemana> ObtenerDias() {
		String sql = "SELECT * FROM Dias";
		List<DiaSemana> listaDias = new ArrayList<DiaSemana>();

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			ResultSet resultSet = stmt.executeQuery();

			while(resultSet.next()) {
				listaDias.add(new DiaSemana (
						resultSet.getInt("id_dias"),
						resultSet.getString("dia")
				));
			}
		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			e.printStackTrace();
		}

		return listaDias;
	}

	public static Genero obtenerGeneroXid(int fk_genero) {
		String sqlGenero = "SELECT * FROM Genero WHERE id_genero = ?";

		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + fk_genero);
		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sqlGenero)) {

			stmt.setInt(1, fk_genero);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return new Genero(
						resultSet.getInt("id_genero"),
						resultSet.getString("genero")
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

        return null;
    }

	public static EstadoUsuario obtenerEstadoUsuarioXid(int fk_estado_usuario) {
		String sqlEstadoUsuario = "SELECT * FROM Estado_Usuario WHERE id_estado_usuario = ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sqlEstadoUsuario)) {

			stmt.setInt(1, fk_estado_usuario);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return new EstadoUsuario(
						resultSet.getInt("id_estado_usuario"),
						resultSet.getString("estado_usuario")
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static UsuarioAutocompletadoResponse ObtenerAutocompletadoUsuario(int dni) {
		String sqlUsuario = "SELECT * FROM Usuario WHERE dni = ?";

		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sqlUsuario)) {

			stmt.setInt(1, dni);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				// Obtengo datos base
				int idUsuario = resultSet.getInt("id_usuario");
				int dniUsuario = resultSet.getInt("dni");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				String email = resultSet.getString("email");
				String clave = resultSet.getString("clave");
				LocalDate fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
				Genero genero = RepositoryUsuario.obtenerGeneroXid(resultSet.getInt("fk_genero"));

				// Creo instancia vacía
				UsuarioAutocompletadoResponse response = new UsuarioAutocompletadoResponse();

				// Seteo datos básicos
				response.setIdUsuario(idUsuario);
				response.setDni(dniUsuario);
				response.setNombre(nombre);
				response.setApellido(apellido);
				response.setEmail(email);
				response.setClave(clave);
				response.setFechaNacimiento(fechaNacimiento);
				response.setGenero(genero);

				// Consulto si es médico y seteo matricula
				boolean esMedico = RepositoryMedico.existeMedico(idUsuario);
				response.setEsMedico(esMedico);
				if (esMedico) {
					String matricula = RepositoryMedico.obtenerMatriculaXid(idUsuario);
					response.setMatricula(matricula);
				} else {
					response.setMatricula(null);
				}

				// Consulto si es paciente y seteo cobertura
				boolean esPaciente = RepositoryPaciente.existePaciente(idUsuario);
				response.setEsPaciente(esPaciente);
				if (esPaciente) {
					String cobertura = RepositoryPaciente.obtenerCoberturaXid(idUsuario);
					response.setCobertura(cobertura);
				} else {
					response.setCobertura(null);
				}

				return response;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new UsuarioAutocompletadoResponse();
	}


	public static boolean existeUsuarioXemail(String email) {
		String sql = "SELECT id_usuario FROM Usuario WHERE email = ?";
		try (Connection con = Conexion.getInstancia().getConexion();
			 PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
        return false;
    }
}