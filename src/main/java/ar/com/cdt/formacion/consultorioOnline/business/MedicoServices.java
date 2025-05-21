package ar.com.cdt.formacion.consultorioOnline.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.com.cdt.formacion.consultorioOnline.domains.Especialidad;
import ar.com.cdt.formacion.consultorioOnline.domains.EstadoUsuario;
import ar.com.cdt.formacion.consultorioOnline.domains.Genero;
import ar.com.cdt.formacion.consultorioOnline.domains.Medico;
import ar.com.cdt.formacion.consultorioOnline.business.MedicoServices;
import ar.com.cdt.formacion.consultorioOnline.controllers.ControllersMedico;
import ar.com.cdt.formacion.consultorioOnline.controllers.ControllersUsuario;



public class MedicoServices {

    public void altaMedico() {
        Medico medico = new Medico();
        
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
        medico.setNombre(nombre);
        
        String apellido = JOptionPane.showInputDialog("Ingrese su apellido");
        medico.setApellido(apellido);
        
        String email = JOptionPane.showInputDialog("Ingrese un email");
        medico.setEmail(email);
        
        String password = JOptionPane.showInputDialog("Ingrese una contraseña");
        medico.setClave(password); 
        
        int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su DNI"));
        medico.setDni(dni);
        
        String string_fecha_nacimiento = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento xx/xx/xxxx");
        
        String[] fecha = string_fecha_nacimiento.split("/");
        int[] fechaEntera = new int[3];
        for (int i = 0; i < 3; i++) {
            fechaEntera[i] = Integer.parseInt(fecha[i]);
        }
        LocalDate fecha_nacimiento = LocalDate.of(fechaEntera[2], fechaEntera[1], fechaEntera[0]);
        medico.setFechaNacimiento(fecha_nacimiento);
        
        List<Genero> listaGeneros = ControllersUsuario.ObtenerGeneros(); 
        String[] nombresGeneros = new String[listaGeneros.size()];

        for (int i = 0; i < listaGeneros.size(); i++) {
        	nombresGeneros[i] = listaGeneros.get(i).getGenero();
        }
        
        String seleccionGenero = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una especialidad:",
                "Especialidades Médicas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresGeneros,
                nombresGeneros[0]
            );
        
        for(Genero genero : listaGeneros) {
        	if (seleccionGenero.equals(genero.getGenero())) {
				medico.setGenero(genero);
			}
        }
        
        
        
        List<EstadoUsuario> listaEstadosUsuario = ControllersUsuario.ObtenerEstadosUsuario();
        for (EstadoUsuario estadoUsuario : listaEstadosUsuario) {
            if (estadoUsuario.getEstado().equalsIgnoreCase("activo")) {
                medico.setEstado(estadoUsuario);
                break;
            }
        }
        
        String matricula = JOptionPane.showInputDialog("Ingrese su matrícula de médico");
        medico.setMatricula(matricula);
        
        
        
        List<Especialidad> listaEspecialidadesMedico = new ArrayList<>();
        List<Especialidad> listaEspecialidades = ControllersMedico.ObtenerEspecialidades();
        String[] nombresEspecialidades = new String[listaEspecialidades.size()];

        for (int i = 0; i < listaEspecialidades.size(); i++) {
            nombresEspecialidades[i] = listaEspecialidades.get(i).getEspecialidad();
        }

        boolean continuar = true;
        while (continuar) {
            String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una especialidad:",
                "Especialidades Médicas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresEspecialidades,
                nombresEspecialidades[0]
            );

            if (seleccion != null) {
                for (Especialidad especialidad : listaEspecialidades) {
                    if (seleccion.equals(especialidad.getEspecialidad())) {
                        if (!listaEspecialidadesMedico.contains(especialidad)) {
                            listaEspecialidadesMedico.add(especialidad); // o crear nueva instancia si preferís
                        } else {
                            JOptionPane.showMessageDialog(null, "Ya agregaste esa especialidad.");
                        }
                        break;
                    }
                }

                int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Desea agregar otra especialidad?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.NO_OPTION) {
                    continuar = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se seleccionó ninguna especialidad.\nPor favor, seleccioná una.");
            }
        }

        medico.setListaEspecialidades(listaEspecialidadesMedico);
        
        ControllersMedico.AgregarMedico(medico);
    }

}

	