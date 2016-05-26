package controles;

import entidades.Taller;

import entidades.Beneficiario;
import entidades.Administrador;
import entidades.Usuario;

public class Registro {

	Taller taller;
	Beneficiario beneficiario;
	Administrador admin;

	public Registro() {
		taller = new Taller();
		beneficiario = new Beneficiario("A01036121");
		admin = new Administrador("A0123");
	}

	// Metodos de Taller

	public String crearTaller(String nombre, int id, int edad, int cupo,
			String descripcion, String direccion) {
		return taller.crearTaller(nombre, id, edad, cupo, descripcion,
				direccion);
	}

	public void setNombreTaller(int tallerId, String nombre) {

		taller.setNombre(tallerId, nombre);

	}

	public void setDescripcionTaller(int talleriD, String descripcion) {
		taller.setDescripcion(talleriD, descripcion);
	}

	public void setDireccionTaller(int talleriD, String direccion) {
		taller.setDireccion(talleriD, direccion);
	}

	public void setCupoTaller(int talleriD, int cupo) {
		taller.setCupo(talleriD, cupo);
	}

	public void setHorarioTaller(int talleriD, String horario) {
		taller.setHorario(talleriD, horario);
	}

	public void setEdad(int tallerID, int edad) {
		taller.setEdad(tallerID, edad);
	}

	public int getEdad(int tallerID) {
		return taller.getEdad(tallerID);
	}

	public void agregarBeneficiario(int idBeneficiario, String nombreTaller) {
		taller.agregarBeneficiario(idBeneficiario, nombreTaller);

	}

	// Metodos de Beneficiario

	public void crearBeneficiario(String nombre, String apellido, int edad,
			String telefono, String tutor, int matricula) {
		beneficiario.crearBeneficiario(nombre, apellido, edad, telefono, tutor,
				matricula);
	}

	public void setNombre(String mat, String nom) {
		beneficiario.setNombre(mat, nom);
	}

	public void setApellidoP(String mat, String apP) {
		beneficiario.setApellidoP(mat, apP);
	}

	public void setApellidoM(String mat, String apM) {
		beneficiario.setApellidoM(mat, apM);
	}

	public void setEmail(String mat, String mail) {
		beneficiario.setEmail(mat, mail);
	}

	// Metodos administrador
	public void setNombreAdmin(String mat, String nom) {
		admin.setNombre(mat, nom);
	}

	public void setApellidoPAdmin(String mat, String apP) {
		admin.setApellidoP(mat, apP);
	}

	public void setApellidoMAdmin(String mat, String apM) {
		admin.setApellidoM(mat, apM);
	}

	public void setEmailAdmin(String mat, String mail) {
		admin.setEmail(mat, mail);
	}

}
