package ar.com.cdt.formacion.consultorioOnline.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.com.cdt.formacion.consultorioOnline.business.Conexion;
import ar.com.cdt.formacion.consultorioOnline.domains.Agenda;
import ar.com.cdt.formacion.consultorioOnline.domains.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.domains.EstadoUsuario;
import ar.com.cdt.formacion.consultorioOnline.domains.Genero;
import ar.com.cdt.formacion.consultorioOnline.domains.Medico;
import ar.com.cdt.formacion.consultorioOnline.domains.Paciente;
import ar.com.cdt.formacion.consultorioOnline.domains.Turno;
import ar.com.cdt.formacion.consultorioOnline.domains.Usuario;

public class ControllersUsuario {

	private static Connection con = Conexion.getInstance().getConnection();
	
	public static Usuario ObtenerSesion(int dni, String clave) {
		Usuario usuario_fk = null;
		Medico medico = null;
		Paciente paciente = null;
	
		try {
			PreparedStatement statement = (PreparedStatement) 
					 con.prepareStatement("SELECT * FROM Usuario WHERE dni=? AND clave=?");
			statement.setInt(1, dni);
			statement.setString(2, clave);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				JOptionPane.showMessageDialog(null, "paso1");
				PreparedStatement statement1 = (PreparedStatement) 
						con.prepareStatement("SELECT * FROM Medico WHERE fk_usuario=?");
				statement1.setInt(1, resultSet.getInt("id_usuario"));
				ResultSet resultSet1 = statement1.executeQuery();
				
				PreparedStatement statement2 = (PreparedStatement) 
						con.prepareStatement("SELECT * FROM Paciente WHERE fk_usuario=?");
				statement2.setInt(1, resultSet.getInt("id_usuario"));
				ResultSet resultSet2 = statement2.executeQuery();
				
				boolean esMedico = resultSet1.next();
				boolean esPaciente = resultSet2.next();
				
				if (esMedico && esPaciente) {
					JOptionPane.showMessageDialog(null, "El usuario tiene dos perfils, Medico y Paciente");
				} else if(esMedico){
					JOptionPane.showMessageDialog(null, "paso2");
					usuario_fk = new Usuario(	
							resultSet.getInt("id_usuario"),
							resultSet.getString("nombre"),
							resultSet.getString("apellido"),
							resultSet.getString("email"),
							resultSet.getString("clave"),
							resultSet.getInt("dni"),
							resultSet.getDate("fecha_nacimiento").toLocalDate(),
							ObtenerGenero(resultSet.getInt("fk_genero")),
							ObtenerEstadoUsuario(resultSet.getInt("fk_estado_usuario"))
							);
					
					
					
					medico = new Medico(
							resultSet1.getInt("id_medico"),
							usuario_fk,
							resultSet1.getString("matricula"),
							ControllersMedico.ObtenerEspecialidad(resultSet1.getInt("id_medico")),
							ControllersMedico.ObtenerConsultorios(resultSet1.getInt("id_medico"))
							);
					
					List<Especialidad> listaEspecilalidades = ControllersMedico.ObtenerEspecialidad(resultSet1.getInt("id_medico"));
					List<Agenda> listaAgendas = ObtenerAgendas(resultSet1.getInt("id_medico"));
					
					for (int i = 0; i < medico.getListaConsultorio().size(); i++) {
						medico.getListaConsultorio().get(i).setEspecialidad(listaEspecilalidades.get(i));
						medico.getListaConsultorio().get(i).setAgendaMedico(listaAgendas.get(i));
					}
					return medico;
					
				} else if(esPaciente) {
					usuario_fk = new Usuario(	
							resultSet.getInt("id_usuario"),
							resultSet.getString("nombre"),
							resultSet.getString("apellido"),
							resultSet.getString("email"),
							resultSet.getString("clave"),
							resultSet.getInt("dni"),
							resultSet.getDate("fecha_nacimiento").toLocalDate(),
							ObtenerGenero(resultSet.getInt("fk_genero")),
							ObtenerEstadoUsuario(resultSet.getInt("fk_estado_usuario"))
							);
					
					paciente = new Paciente(
							resultSet2.getInt("id_paciente"),
							usuario_fk,
							resultSet2.getString("cobertura")
							);
					return paciente;
				}
			} else {
				JOptionPane.showMessageDialog(null, "No se inicio sesion con usuario");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static List<Agenda> ObtenerAgendas(int id){
		List<Agenda> listaAgendas = new ArrayList<Agenda>();
		
		try {
			
			 PreparedStatement statement = (PreparedStatement) 
					 con.prepareStatement("SELECT id_agenda, tipo_agenda FROM Agenda INNER JOIN Consultorio ON Consultorio.fk_agenda = Agenda.id_agenda WHERE Consultorio.fk_medico = ?");
			 statement.setInt(1, id);
			 ResultSet resultSet = statement.executeQuery();
			 while (resultSet.next()) {
				 
				 listaAgendas.add(new Agenda(
						    resultSet.getInt("id_agenda"),
						    resultSet.getString("tipo_agenda"),
						    new ArrayList<Turno>()
						));
			 }
			
		 } catch (Exception e) {
			 System.out.println("Error al obtener agendas: " + e.getMessage());
				e.printStackTrace();		
		 }
		return listaAgendas;
	}
	
	public static List<EstadoUsuario> ObtenerEstadosUsuario() {
		 List<EstadoUsuario> listaEstadosUsuario = new ArrayList<EstadoUsuario>();
		 try {
			
			 PreparedStatement statement = (PreparedStatement) 
					 con.prepareStatement("SELECT * FROM `Estado_Usuario`");
			 ResultSet resultSet = statement.executeQuery();
			 while (resultSet.next()) {
				 
				 listaEstadosUsuario.add(new EstadoUsuario(
						 resultSet.getInt("id_estado_usuario"),
						 resultSet.getString("estado_usuario")
						 ));
			 }
			
		 } catch (Exception e) {
			 System.out.println("No se agregó");		
		 }
		return listaEstadosUsuario;
	}
	
	
	public static EstadoUsuario ObtenerEstadoUsuario(int id) {
		 EstadoUsuario estadoUsuario = null;
		 try {
			
			 PreparedStatement statement = (PreparedStatement) 
					 con.prepareStatement("SELECT * FROM `Estado_Usuario` WHERE id_estado_usuario=?");
			 statement.setInt(1, id);
			 ResultSet resultSet = statement.executeQuery();
			 if (resultSet.next()) {	
				 JOptionPane.showMessageDialog(null, "paso4");
				 estadoUsuario = new EstadoUsuario(
						 resultSet.getInt("id_estado_usuario"),
						 resultSet.getString("estado_usuario")
					);
			 } else {
				 JOptionPane.showMessageDialog(null, "No se trajo correctamente Estado Usuario");
			 }
			
		 } catch (Exception e) {
			 System.out.println("No se agregó");		
		 }
		return estadoUsuario;
	}
	
	
	public static Genero ObtenerGenero(int id){	
		Genero genero = null;
		try {
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `Genero` WHERE id_genero=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				JOptionPane.showMessageDialog(null, "paso3");
				genero = new Genero(
						resultSet.getInt("id_genero"),
						resultSet.getString("genero")
						);		
			} else {
				JOptionPane.showMessageDialog(null, "No se encontro el genero");
			}
			
		} catch (Exception e) {
			System.out.println("No se agregó");
		}
		return genero;
	}
	
	
	
	public static List<Genero> ObtenerGeneros(){	
		List<Genero> listaGeneros = new ArrayList<Genero>();
		try {
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `Genero`");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				listaGeneros.add(new Genero(
							resultSet.getInt("id_genero"),
							resultSet.getString("genero")
						)
				);
				
			}	
		} catch (Exception e) {
			System.out.println("No se agregó");
		}
		return listaGeneros;
	}
}
