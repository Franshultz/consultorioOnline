package ar.com.cdt.formacion.consultorioOnline.business;

import javax.swing.JOptionPane;

import ar.com.cdt.formacion.consultorioOnline.controllers.ControllersUsuario;
import ar.com.cdt.formacion.consultorioOnline.domains.Usuario;

public class Sesion {

	
	public Usuario IniciarSesion() {
		
		int dni = Integer.parseInt(JOptionPane.showInputDialog("Inicie sesion con su nro de DNI:"));	
		String clave = JOptionPane.showInputDialog("Password:");	
		
	
		return ControllersUsuario.ObtenerSesion(dni, clave);
	}
}
