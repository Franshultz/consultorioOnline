package ar.com.cdt.formacion.consultorioOnline.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
