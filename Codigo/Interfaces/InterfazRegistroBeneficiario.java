package interfaces;

import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.Registro;

public class InterfazRegistroBeneficiario extends HttpServlet {
	HttpServletResponse thisResponse;
	HttpServletRequest thisRequest;
	PrintWriter out;
	Registro r;

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
		out.println("<h2>Registrar Beneficiario</h2>");

		String operacion = request.getParameter("operacion");
		if (operacion == null) {
			iniciarRegistroBeneficiario();
		} else if (operacion.equals("registrar")) {
			registrarBeneficiario();
		}
	}

	public void iniciarRegistroBeneficiario() {

		out.println("<form method=\"GET\" action=\"RegistroBeneficiario\">");
		out.println("<input type=\"hidden\" name=\"operacion\" value=\"registrar\"/>");

		out.println("<p> Nombre <input type=\"text\" name=\"nombre\" size=\"15\"></p>");
		out.println("<p> Apellido <input type=\"text\" name=\"apellido\" size=\"15\"></p>");
		out.println("<p> Edad <input type=\"text\" name=\"edad\" size=\"15\"></p>");
		out.println("<p> Teléfono <input type=\"text\" name=\"telefono\" size=\"15\"></p>");
		out.println("<p> Tutor <input type=\"text\" name=\"tutor\" size=\"15\"></p>");

		out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
		out.println("</form>");

		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
		out.println("</form>");

	}

	public void registrarBeneficiario() {
		r = new Registro();

		String nombre = (thisRequest.getParameter("nombre").trim());
		String apellido = (thisRequest.getParameter("apellido").trim());
		int edad = Integer.parseInt((thisRequest.getParameter("edad").trim()));
		String telefono = (thisRequest.getParameter("telefono").trim());
		String tutor = (thisRequest.getParameter("tutor").trim());

		int matricula = 50 + (int) (Math.random() * 10000);

		r.crearBeneficiario(nombre, apellido, edad, telefono, tutor, matricula);

		out.println("<p>Registro Exitoso</p>");
		out.println("<p>Su matrícula es: " + matricula + "</p>");
		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
		out.println("</form>");

		out.println("</BODY>");
		out.println("</HTML>");

	}

}
