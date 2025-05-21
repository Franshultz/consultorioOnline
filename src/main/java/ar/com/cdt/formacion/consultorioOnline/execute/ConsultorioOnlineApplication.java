package ar.com.cdt.formacion.consultorioOnline.execute;

import javax.swing.JOptionPane;

import ar.com.cdt.formacion.consultorioOnline.business.MedicoServices;
import ar.com.cdt.formacion.consultorioOnline.business.Sesion;
import ar.com.cdt.formacion.consultorioOnline.domains.Medico;
import ar.com.cdt.formacion.consultorioOnline.domains.Usuario;

public class ConsultorioOnlineApplication {
	

	public static void main(String[] args) {

//        MedicoServices medicoServices = new MedicoServices();
//        medicoServices.altaMedico();    
        
        Sesion sesionService = new Sesion();
        Medico sesion = (Medico) sesionService.IniciarSesion();
        
        JOptionPane.showMessageDialog(null, "AAAAAAAAA" + sesion.getListaConsultorio().get(1).getAgendaMedico().getTipoAgenda());
        
	}
}
