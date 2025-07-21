package ar.com.cdt.formacion.consultorioOnline.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class GestionadorEmails {

    private final JavaMailSender mailSender;

    @Autowired
    public GestionadorEmails(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void enviarMail (String to, String asunto,String texto) { // recibe el mail, el asunto y el cuerpo del mail
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + to + asunto + texto);
        // configurar javaMailSender  con STARTTLS  - COMANDO PARA SOLICITAR CONEXION SEGURA

        if (mailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) mailSender;
            javaMailSender.setHost("smtp.gmail.com");
            javaMailSender.setPort(587); // Puerto para STARTTLS
            javaMailSender.setUsername("onlineeconsultorio@gmail.com");  // correo emisor
            javaMailSender.setPassword("pxqwfcgrzuiipzbd"); // contraseña de app

            // propiedades ed STARTTLS
            java.util.Properties props = javaMailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS
            props.put("mail.smtp.starttls.required", "true"); // Requiere STARTTLS
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Confianza en el servidor SMTP
        }

        //Creación del mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);  // Cambia a tu correo de prueba
        message.setSubject(asunto);
        message.setText(texto);

        // Envío del correo
        try {
            mailSender.send(message);
            System.out.println("Correo de prueba enviado.");
        } catch (Exception e) {
            System.out.println("Error al enviar el correo de prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

