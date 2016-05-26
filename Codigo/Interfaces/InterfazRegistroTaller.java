package interfaces;

import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.Registro;
import java.util.Random;
import entidades.Taller;

public class InterfazRegistroTaller extends HttpServlet {
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
		out.println("<h2>Registrar Taller</h2>");

		String operacion = request.getParameter("operacion");
		if (operacion == null) {
			iniciarRegistro();
		} else if (operacion.equals("registrar")) {
			registrarTaller();
		}
	}

	public void iniciarRegistro() {

		r = new Registro();

		out.println("<form method=\"GET\" action=\"Registro\">");

		out.println("<input type=\"hidden\" name=\"operacion\" value=\"registrar\"/>");

		out.println("<p> Nombre <input type=\"text\" name=\"nombre\" size=\"15\"></p>");
		out.println("<p> Cupo <input type=\"text\" name=\"cupo\" size=\"5\"></p>");
		out.println("<p> Dirección <input type=\"text\" name=\"direccion\" size=\"25\"></p>");
		out.println("<p> Descripción <input type=\"text\" name=\"descripcion\" size=\"53\"></p>");
		out.println("<p> Edad Mínima <input type=\"text\" name=\"edad\" size=\"5\"></p>");

		out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
		out.println("</form>");

		// Botón de cancelar
		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
		out.println("</form>");
	}

	public void registrarTaller() {

		String nombre = (thisRequest.getParameter("nombre").trim());

		int cupo = Integer.parseInt(thisRequest.getParameter("cupo").trim());

		int tallerID = 50 + (int) (Math.random() * 100);

		out.println("<p>Registro Exitoso</p>");
		out.println("<p>ID del Taller: " + tallerID);
		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
		out.println("</form>");

		out.println("</BODY>");
		out.println("</HTML>");
		String direccion = (thisRequest.getParameter("direccion").trim());
		String descripcion = (thisRequest.getParameter("descripcion").trim());

		int edad = Integer.parseInt(thisRequest.getParameter("edad").trim());

		r.crearTaller(nombre, tallerID, edad, cupo, descripcion, direccion);

	}

}
