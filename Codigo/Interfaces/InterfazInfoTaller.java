package interfaces;

import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.Registro;

public class InterfazInfoTaller extends HttpServlet {
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
		out.println("<h2>Información de Taller</h2>");

		String operacion = request.getParameter("operacion");
		if (operacion == null) {
			iniciarDespliegue();
		} else if (operacion.equals("registrar")) {
			desplegarInfo();
		}
	}

	public void iniciarDespliegue() {
		// NOMBRE DE TALLER
		out.println("<form method=\"GET\" action=\"InterfazInfoTaller\">");
		out.println("<input type=\"hidden\" name=\"operacion\" value=\"registrar\"/>");

		out.println("<p> IDTaller <input type=\"text\" name=\"idtaller\" size=\"15\"></p>");
		out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
		out.println("</form>");

		// Botón de cancelar
		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
		out.println("</form>");

	}

	public void desplegarInfo() {
		ins = new Inscripcion();
		// La funcion trim() elimina espacios antes y despues del valor
		int idtaller = Integer.parseInt((thisRequest.getParameter("idtaller")
				.trim()));

		int cantidad = 5;
		String[] campos = { "Nombre: ", "Descripcion: ", "Edad Mínima: ",
				"Cupo: ", "Lugar: " };

		for (int i = 0; i < cantidad; i++) {
			out.println("<p>" + campos[i]
					+ ins.desplegarInfoTaller(idtaller, i) + "</p>");
		}

		out.println("<h2> Lista de inscritos </h2>");

		int cantInscritos = ins.contarInscritos(idtaller);

		String inscritos;

		for (int i = 1; i <= cantInscritos; i++) {
			inscritos = ins.obtenerInscritos(idtaller, i);
			System.out.println(inscritos);
			out.println("<p>"
					+ ins.desplegarInscritos(Integer.parseInt(inscritos))
					+ ins.desplegarApellido(Integer.parseInt(inscritos)) + " "
					+ inscritos + "</p>");
		}

		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
		out.println("</form>");

		out.println("</BODY>");
		out.println("</HTML>");

	}

}
