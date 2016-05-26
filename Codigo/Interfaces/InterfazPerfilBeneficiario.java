package interfaces;

import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.Registro;

public class InterfazPerfilBeneficiario extends HttpServlet {
	HttpServletResponse thisResponse;
	HttpServletRequest thisRequest;
	PrintWriter out;
	Registro r;
	Inscripcion ins;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		thisResponse = response;
		thisRequest = request;
		thisResponse.setContentType("text/html");
		out = thisResponse.getWriter();

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<META http-equiv=Content-Type content=\"text/html\">");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<TITLE>INSCRIPCIONES</TITLE>");
		out.println("<h2>Perfil de Beneficiario</h2>");

		String operacion = request.getParameter("operacion");
		if (operacion == null) {
			iniciarDespliegue();
		} else if (operacion.equals("registrar")) {
			desplegarPerfil();
		}
	}

	public void iniciarDespliegue() {

		out.println("<form method=\"GET\" action=\"PerfilBeneficiario\">");
		out.println("<input type=\"hidden\" name=\"operacion\" value=\"registrar\"/>");

		out.println("<p> Matrícula <input type=\"text\" name=\"matricula\" size=\"15\"></p>");
		out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
		out.println("</form>");

		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
		out.println("</form>");

	}

	public void desplegarPerfil() {
		ins = new Inscripcion();

		int matricula = Integer.parseInt((thisRequest.getParameter("matricula")
				.trim()));

		int cantidad = 6;
		String[] campos = { "Nombre: ", "Apellido: ", "Matricula: ", "Edad: ",
				"Telefono: ", "Tutor: " };

		for (int i = 0; i < cantidad; i++) {
			out.println("<p>" + campos[i]
					+ ins.desplegarPerfilBeneficiario(matricula, i) + "</p>");
		}

		int cantTalleres = ins.contarTalleres(matricula);

		out.println("<h2> Mis Talleres </h2>");

		String talleres;

		for (int i = 1; i <= cantTalleres; i++) {
			talleres = ins.obtenerTalleres(matricula, i);
			out.println("<p>"
					+ ins.desplegarTalleres(Integer.parseInt(talleres))
					+ "</p>");
		}

		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
		out.println("</form>");

		out.println("</BODY>");
		out.println("</HTML>");

	}

}
