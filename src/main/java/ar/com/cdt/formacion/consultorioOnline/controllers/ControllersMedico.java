package ar.com.cdt.formacion.consultorioOnline.controllers;

//import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



import ar.com.cdt.formacion.consultorioOnline.business.Conexion;
import ar.com.cdt.formacion.consultorioOnline.domains.Agenda;
import ar.com.cdt.formacion.consultorioOnline.domains.Consultorio;
import ar.com.cdt.formacion.consultorioOnline.domains.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.domains.Medico;

public class ControllersMedico {
	
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static List<Especialidad> ObtenerEspecialidades() {
		 List<Especialidad> listaEspecialidades = new ArrayList<Especialidad>();
		 try {
			
			 PreparedStatement statement = (PreparedStatement) 
					 con.prepareStatement("SELECT * FROM `Especialidad`");
			 ResultSet resultSet = statement.executeQuery();
			 while (resultSet.next()) {
				 
				 listaEspecialidades.add(new Especialidad(
						 resultSet.getInt("id_especialidad"),
						 resultSet.getString("especialidad"),
						 resultSet.getInt("duracion_turno")
						 ));
			 }
			
		 } catch (Exception e) {
			 System.out.println("No se agregó");		
		 }
		return listaEspecialidades;
	}
	
	public static List<Especialidad> ObtenerEspecialidad(int id) {
		
		List<Especialidad> listaEspecialidadesMedico = new ArrayList<Especialidad>();
		try {
			
			PreparedStatement statement = con.prepareStatement(
		            "SELECT DISTINCT Especialidad.id_especialidad, Especialidad.especialidad, Especialidad.duracion_turno " +
		            "FROM Especialidad " +
		            "INNER JOIN Consultorio ON Consultorio.fk_especialidad = Especialidad.id_especialidad " +
		            "WHERE Consultorio.fk_medico = ?"
		            );
			 
			 statement.setInt(1, id); 
			 ResultSet resultSet = statement.executeQuery();
			 
			 while (resultSet.next()) {
				 
				 JOptionPane.showMessageDialog(null, "paso5");
				 listaEspecialidadesMedico.add(new Especialidad(
						 resultSet.getInt("id_especialidad"),
						 resultSet.getString("especialidad"),
						 resultSet.getInt("duracion_turno")
						 ));
			 }
			
		 } catch (Exception e) {
			 System.out.println("No se agregó");		
		 }
		return listaEspecialidadesMedico;
	}
	
	
	public static List<Consultorio> ObtenerConsultorios(int id){
		List<Consultorio> listaConsultoriosMedico = new ArrayList<Consultorio>();
		
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Consultorio WHERE fk_medico = ?");
			statement.setInt(1, id); 
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				JOptionPane.showMessageDialog(null, "paso6");
				listaConsultoriosMedico.add(new Consultorio(
						 resultSet.getInt("id_consultorio"),
						 resultSet.getString("nombre_consultorio"),
						 resultSet.getTime("horario_laboral_inicio"),
						 resultSet.getTime("horario_laboral_fin"),
						 null,
						 null
						 ));
			 }
		} catch (Exception e) {
			
		}
		
		return listaConsultoriosMedico;
	}

	public static void AgregarMedico(Medico medico) {

		try {
		    PreparedStatement statement = con.prepareStatement(
		        "INSERT INTO `Usuario`(`nombre`, `apellido`, `email`, `clave`, `dni`, `fecha_nacimiento`, `fk_genero`, `fk_estado_usuario`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
		        Statement.RETURN_GENERATED_KEYS
		    );

		    statement.setString(1, medico.getNombre()); 
		    statement.setString(2, medico.getApellido());
		    statement.setString(3, medico.getEmail());
		    statement.setString(4, medico.getClave());
		    statement.setInt(5, medico.getDni());
		    statement.setDate(6, Date.valueOf(medico.getFechaNacimiento()));
		    statement.setInt(7, medico.getGenero().getId());
		    statement.setInt(8, medico.getEstado().getIdEstadoUsuario());
	  
		    int comprobacion = statement.executeUpdate();

		    if (comprobacion > 0) {
		        JOptionPane.showMessageDialog(null, "Se agregó exitosamente un usuario");

		        ResultSet generatedKeys = statement.getGeneratedKeys();
		        if (generatedKeys.next()) {
		            int fk_usuario = generatedKeys.getInt(1);
		            System.out.println("ID de usuario generado: " + fk_usuario);
		       
		            PreparedStatement statement1 = con.prepareStatement(
		                "INSERT INTO `Medico`(`matricula`, `fk_usuario`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS 
		            );
		            statement1.setString(1, medico.getMatricula());
		            statement1.setInt(2, fk_usuario);

		            int comprobacion1 = statement1.executeUpdate();
		            if (comprobacion1 > 0) {
		                JOptionPane.showMessageDialog(null, "Se agregó el médico exitosamente");
		                
		                ResultSet generatedKeys1 = statement1.getGeneratedKeys();
		                if (generatedKeys1.next()) {
		                	int fk_medico = generatedKeys1.getInt(1);
		                	medico.setIdMedico(fk_medico);
		                	
		                	for (int i = 0; i < medico.getListaEspecialidades().size(); i++) {
		                		PreparedStatement statement2 = con.prepareStatement(
		                			    "INSERT INTO `Agenda`(`tipo_agenda`) VALUES (?)", Statement.RETURN_GENERATED_KEYS
		                			);
		                		
		                		statement2.setString(1, "Medico");
		                		int comprobacion2 = statement2.executeUpdate();
		                		
		                		if (comprobacion2 > 0) {
		                			ResultSet generatedKeys2 = statement2.getGeneratedKeys();
		                			
		                			if (generatedKeys2.next()) {
		                				int fk_agenda = generatedKeys2.getInt(1);
		                				String horaStrIncio = "09:00";
				                		String horaStrFin = "17:00";	
				                		
				                		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				                		
				                		LocalTime horaInicio = LocalTime.parse(horaStrIncio, formatter);
				                		LocalTime horaFin = LocalTime.parse(horaStrFin, formatter);
				                		
				                		
				                		PreparedStatement statement3 = con.prepareStatement(
				        		                "INSERT INTO `Consultorio`(`nombre_consultorio`, `horario_laboral_inicio`, `horario_laboral_fin`, `fk_medico`, `fk_especialidad`, `fk_agenda`) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
				        		            );
				                		statement3.setString(1, medico.getListaEspecialidades().get(i).getEspecialidad());
				                		statement3.setTime(2, Time.valueOf(horaInicio));
				                		statement3.setTime(3, Time.valueOf(horaFin));
				                		statement3.setInt(4, fk_medico);
				                		statement3.setInt(5, medico.getListaEspecialidades().get(i).getIdEspecialidad());
				                		statement3.setInt(6, fk_agenda);
				                		
				                		int comprobacion3 = statement3.executeUpdate();
				                        if (comprobacion3 > 0) {
				                            JOptionPane.showMessageDialog(null, "Consultorio creado");
				                            ResultSet generatedKeys3 = statement3.getGeneratedKeys();
				                            if (generatedKeys3.next()) {
				                            	int fk_consultorio = generatedKeys3.getInt(1);
				                            	PreparedStatement statement4 = con.prepareStatement(
						        		                "INSERT INTO `Consultorio_Medico`(`fk_consultorio`, `fk_medico`) VALUES (?, ?)"
						        		            );
				                            	statement4.setInt(1, fk_consultorio);
						                		statement4.setInt(2, fk_medico);
						                		int comprobacion4 = statement4.executeUpdate();
						                		if (comprobacion4 > 0) {
						                			JOptionPane.showMessageDialog(null, "Consultorio_Medico creado");
												}
											}
				                        }
									}	
								}	
							}
						} 
		                
		            } else {
		                JOptionPane.showMessageDialog(null, "Se creó el usuario, pero no se pudo agregar el médico");
		            }

		        } else {
		            JOptionPane.showMessageDialog(null, "No se pudo obtener la clave generada para el usuario");
		        }

		    } else {
		        JOptionPane.showMessageDialog(null, "No se pudo crear el usuario");
		    }

		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, "Error al agregar: " + e.getMessage());
		    e.printStackTrace();
		}
    }
}
