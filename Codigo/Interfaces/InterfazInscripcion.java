package interfaces;

import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.Registro;
import controles.Inscripcion;

public class InterfazInscripcion extends HttpServlet {
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
		out.println("<h2>Inscribir Taller</h2>");

		String operacion = request.getParameter("operacion");
		if (operacion == null) {
			iniciarInscripcion();
		} else if (operacion.equals("registrar")) {
			inscribir();
		}
	}

	public void iniciarInscripcion() {

		out.println("<form method=\"GET\" action=\"InterfazInscripcion\">");
		out.println("<input type=\"hidden\" name=\"operacion\" value=\"registrar\"/>");

		out.println("<p> Matrícula <input type=\"text\" name=\"matricula\" size=\"15\"></p>");
		out.println("<p> Nombre Taller <input type=\"text\" name=\"taller\" size=\"15\"></p>");

		out.println("<p><input type=\"submit\" value=\"Inscribir\"name=\"B1\"></p>");
		out.println("</form>");

		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
		out.println("</form>");

		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void inscribir() {

		String taller = (thisRequest.getParameter("taller").trim());
		int matricula = Integer.parseInt((thisRequest.getParameter("matricula")
				.trim()));

		ins = new Inscripcion();
		ins.inscribir(matricula, taller);

		out.println("<p>Inscripción Exitosa</p>");
		out.println("<form method=\"GET\" action=\"menuInscripciones.html\">");
		out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
		out.println("</form>");

		out.println("</BODY>");
		out.println("</HTML>");

	}

}
