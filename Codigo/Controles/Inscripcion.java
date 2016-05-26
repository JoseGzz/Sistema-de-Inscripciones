package controles;

import entidades.Taller;
import entidades.Beneficiario;

public class Inscripcion {

	Taller taller;
	Beneficiario beneficiario;

	public Inscripcion() {
		taller = new Taller();
		beneficiario = new Beneficiario("A01036121");
	}

	// Metodos de Taller

	public void inscribir(int matricula, String nombreTaller) {
		beneficiario.inscribir(matricula, nombreTaller);
		taller.actualizarCupo(nombreTaller);
	}

	public String desplegarPerfilBeneficiario(int matricula, int i) {

		switch (i) {

		case 0:
			return beneficiario.getNombre(matricula);

		case 1:
			return beneficiario.getApellido(matricula);

		case 2:
			return String.valueOf(matricula);

		case 3:
			return String.valueOf(beneficiario.getEdad(matricula));

		case 4:
			return beneficiario.getTelefono(matricula);

		case 5:
			return beneficiario.getNombreTutor(matricula);

		default:

		}

		return "error";

	}

	public int contarTalleres(int mat) {

		return beneficiario.contarTalleres(mat);
	}

	public String desplegarTalleres(int idtaller) {
		return beneficiario.desplegarTalleres(idtaller);

	}

	public String obtenerTalleres(int mat, int indice) {

		return beneficiario.obtenerTalleres(mat, indice);
	}

	// METODOS DESPLIEGUE INFO TALLER

	public int contarInscritos(int idtaller) {

		return taller.contarInscritos(idtaller);
	}

	public String desplegarInscritos(int matricula) {
		return taller.desplegarInscritos(matricula);
	}

	public String desplegarApellido(int matricula) {
		return taller.desplegarApellido(matricula);

	}

	public String obtenerInscritos(int idtaller, int indice) {

		return taller.obtenerInscritos(idtaller, indice);
	}

	public String desplegarInfoTaller(int idtaller, int i) {

		switch (i) {

		case 0:
			return taller.getNombre(idtaller);

		case 1:
			return taller.getDescripcion(idtaller);

		case 2:
			return String.valueOf(taller.getEdad(idtaller));

		case 3:
			return String.valueOf(taller.getCupo(idtaller));

		case 4:
			return taller.getDireccion(idtaller);

		default:

		}

		return "error";

	}

}
