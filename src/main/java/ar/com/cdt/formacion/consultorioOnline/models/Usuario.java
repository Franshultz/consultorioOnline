package ar.com.cdt.formacion.consultorioOnline.models;

import java.time.LocalDate;


public class Usuario {
	
	private int id, dni, fk_usuario;
    private String nombre, apellido, email, clave;
    private LocalDate fechaNacimiento;

	private int genero_fk;
    private Genero genero;

	private int estadoUsuario_fk;
    private EstadoUsuario estadoUsuario;

	//Constructor Vacio por Default
	public Usuario(){};

	//Constructor para crear Medico o Paciente en BD
	public Usuario(String nombre, String apellido, String email, String clave, int dni, LocalDate fechaNacimiento,
				   int genero_fk, int estadoUsuario_fk) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.clave = clave;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.genero_fk = genero_fk;
		this.estadoUsuario_fk = estadoUsuario_fk;
	}

	//Constructor para crear Medico o Paciente en BD con id
	public Usuario(String nombre, String apellido, String email, String clave, int dni, LocalDate fechaNacimiento,
				   int genero_fk, int estadoUsuario_fk, int fk_usuario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.clave = clave;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.genero_fk = genero_fk;
		this.estadoUsuario_fk = estadoUsuario_fk;
		this.fk_usuario = fk_usuario;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getGenero_fk() {
		return genero_fk;
	}

	public void setGenero_fk(int genero_fk) {
		this.genero_fk = genero_fk;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getEstadoUsuario_fk() {
		return estadoUsuario_fk;
	}

	public void setEstadoUsuario_fk(int estadoUsuario_fk) {
		this.estadoUsuario_fk = estadoUsuario_fk;
	}

	public EstadoUsuario getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", clave="
				+ clave + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", estado="
				+ estadoUsuario +"]";
	}	
}
