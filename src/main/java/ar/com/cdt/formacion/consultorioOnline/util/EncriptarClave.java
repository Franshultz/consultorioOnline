package ar.com.cdt.formacion.consultorioOnline.util;

import java.security.MessageDigest;
/**
 *
 */
public class EncriptarClave {
    /*
     * Method para encriptar contraseña
     * @param pass
     */
    public static String encriptar(String pass) {
        try {
            MessageDigest mensaje = MessageDigest.getInstance("SHA");
            mensaje.update(pass.getBytes());
            byte[] rbt=mensaje.digest();
            StringBuilder constructor = new StringBuilder();

            for(byte b: rbt) {
                constructor.append(String.format("%02x", b));
            }
            return constructor.toString();
        } catch (Exception e) {
            /*
             * Nothing
             */
        }
        return null;
    }
}
