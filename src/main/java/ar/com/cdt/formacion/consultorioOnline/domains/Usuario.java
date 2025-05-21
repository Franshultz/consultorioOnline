package ar.com.cdt.formacion.consultorioOnline.domains;

import java.time.LocalDate;


public class Usuario {
	
	private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private int dni;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private EstadoUsuario estadoUsuario;
	
	public Usuario(String nombre, String apellido, String email, String clave, int dni, LocalDate fechaNacimiento,
			Genero genero, EstadoUsuario estadoUsuario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.clave = clave;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.estadoUsuario = estadoUsuario;
	}
	
	public Usuario(int id, String nombre, String apellido, String email, String clave, int dni, LocalDate fechaNacimiento,
            Genero genero, EstadoUsuario estadoUsuario) {
		this(nombre, apellido, email, clave, dni, fechaNacimiento, genero, estadoUsuario);
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.clave = clave;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.estadoUsuario = estadoUsuario;
	}

	public Usuario(){

    }


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public EstadoUsuario getEstado() {
		return estadoUsuario;
	}

	public void setEstado(EstadoUsuario estado) {
		this.estadoUsuario = estado;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", clave="
				+ clave + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", estado="
				+ estadoUsuario +"]";
	}	
}
