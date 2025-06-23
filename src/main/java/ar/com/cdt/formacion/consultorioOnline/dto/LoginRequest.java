package ar.com.cdt.formacion.consultorioOnline.dto;

public class LoginRequest {

    private int dni;
    private String clave;

    public LoginRequest(int dni, String clave) {
        this.dni = dni;
        this.clave = clave;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
