package entidades;

import java.sql.*;
import java.io.*;

public class Taller {

	Connection conn;
	Statement stmt;

	public Taller() {
		try {
			int tallerID = 999999;
			String nombre = "Default";
			String descripcion = "Default";
			String direccion = "Default";
			String userName = "root";
			String password = "";
			int cupo = 0;
			String horario = "Default";
			String url = "jdbc:mysql://localhost/inscripciones";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("Cannot connect to database server");
		}
	}

	public int contarInscritos(int idtaller) {
		int count = 0;
		try {
			stmt.executeQuery(" SELECT Matricula, COUNT(*) FROM inscripcion WHERE TallerID = "
					+ idtaller);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			count = rs.getInt("COUNT(*)");
			rs.close();
			return (count);
		} catch (SQLException e) {
			System.out.println("Cannot contarInscritos()" + e);
		}
		return count;
	}

	public String obtenerInscritos(int idtaller, int indice) {
		String id = null;
		try {
			stmt.executeQuery("SELECT * FROM inscripcion WHERE TallerID = "
					+ idtaller);
			ResultSet rs = stmt.getResultSet();
			for (int j = 0; j < indice; j++) {
				rs.next();
			}
			id = rs.getString(2);
			rs.close();
			return (id);
		} catch (SQLException e) {
			System.out.println("Cannot obtenerInscritos())" + e);
		}
		return id;

	}

	public String desplegarInscritos(int mat) {
		String nombre = "";
		try {
			stmt.executeQuery("SELECT Nombre FROM beneficiario WHERE Matricula = "
					+ mat);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			nombre = rs.getString("Nombre");
			rs.close();
			return (nombre);
		} catch (SQLException e) {
			System.out.println("Cannot desplegarInscritos()" + e);
		}
		return nombre;
	}

	public String desplegarApellido(int mat) {
		String apellido = "";
		try {
			stmt.executeQuery("SELECT Apellido FROM beneficiario WHERE Matricula = "
					+ mat);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			apellido = rs.getString("Apellido");
			rs.close();
			return (apellido);
		} catch (SQLException e) {
			System.out.println("Cannot desplegarapellido()" + e);
		}
		return apellido;
	}

	public String crearTaller(String nombre, int id, int edad, int cupo,
			String descripcion, String direccion) {
		try {
			String s = "INSERT INTO talleres (Nombre, TallerID, Edad, Direccion, Cupo, Descripcion)"
					+ " VALUES ('"
					+ nombre
					+ "', "
					+ id
					+ " , "
					+ edad
					+ " , '"
					+ direccion
					+ "' , "
					+ cupo
					+ " , '"
					+ descripcion
					+ "' )";

			System.out.println(s);
			stmt.executeUpdate(s);
			return "exito";
		} catch (SQLException e) {
			System.out.println("Cannot crearTaller()" + e);
		}
		return "error";
	}

	public void agregarBeneficiario(int idBeneficiario, String nombreTaller) {
	}

	public String getNombre(int idtaller) {
		String nombre = "";
		try {
			stmt.executeQuery("SELECT Nombre FROM talleres WHERE TallerID = "
					+ idtaller);
			ResultSet rs = stmt.getResultSet();
			rs.next(); // Va al registro ya validado
			nombre = rs.getString("Nombre");
			rs.close();
			return (nombre);
		} catch (SQLException e) {
			System.out.println("Cannot getNombre()" + e);
		}
		return nombre;
	}

	public void setNombre(int taller, String nombre) {
		try {
			String s = "UPDATE talleres SET nombre = " + nombre
					+ " WHERE tallerID = " + taller;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}
	}

	public String getDescripcion(int idtaller) {
		String descripcion = "";
		try {
			stmt.executeQuery("SELECT descripcion FROM talleres WHERE TallerID = "
					+ idtaller);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			descripcion = rs.getString("descripcion");
			rs.close();
			return (descripcion);
		} catch (SQLException e) {
			System.out.println("Cannot getDescripcion()" + e);
		}
		return descripcion;
	}

	public void setDescripcion(int taller, String descripcion) {
		try {
			String s = "UPDATE talleres SET descripcion = " + descripcion
					+ " WHERE tallerID = " + taller;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}
	}

	public String getDireccion(int idtaller) {
		String direccion = "";
		try {
			stmt.executeQuery("SELECT direccion FROM talleres WHERE TallerID = "
					+ idtaller);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			direccion = rs.getString("direccion");
			rs.close();
			return (direccion);
		} catch (SQLException e) {
			System.out.println("Cannot getDireccion()" + e);
		}
		return direccion;
	}

	public void setDireccion(int taller, String direccion) {
		try {
			String s = "UPDATE talleres SET direccion = " + direccion
					+ " WHERE tallerID = " + taller;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}
	}

	public int getCupo(int idtaller) {
		int cupo = 0;
		try {
			stmt.executeQuery("SELECT Cupo FROM talleres WHERE TallerID = "
					+ idtaller);
			ResultSet rs = stmt.getResultSet();
			rs.next(); // Va al registro ya validado
			cupo = rs.getInt("Cupo");
			rs.close();
			return (cupo);
		} catch (SQLException e) {
			System.out.println("Cannot getCupo()" + e);
		}
		return cupo;
	}

	public void actualizarCupo(String nombre) {
		int id = 0;
		try {
			stmt.executeQuery("SELECT TallerID FROM talleres WHERE Nombre = '"
					+ nombre + "'");
			ResultSet rs = stmt.getResultSet();
			rs.next(); // Va al registro ya validado
			id = rs.getInt("TallerID");
			rs.close();
			setCupo(id, getCupo(id) - 1);
		} catch (SQLException e) {
			System.out.println("Cannot actualizarCupo()" + e);
		}

	}

	public void setCupo(int taller, int cupo) {
		try {
			String s = "UPDATE talleres SET cupo = " + cupo
					+ " WHERE TallerID = " + taller;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}
	}

	public String getHorario(int idtaller) {
		String horario;
		try {
			stmt.executeQuery("SELECT horario FROM talleres WHERE tallerID = "
					+ idtaller);
			ResultSet rs = stmt.getResultSet();
			rs.next(); // Va al registro ya validado
			horario = rs.getString("horario");
			rs.close();
			return (horario);
		} catch (SQLException e) {
			System.out.println("Cannot getHorario()" + e);
		}
		horario = "";
		return horario;
	}

	public void setHorario(int idTaller, String horario) {
		try {
			String s = "UPDATE talleres SET horario = " + horario
					+ " WHERE tallerID = " + idTaller;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}
	}

	public void setEdad(int idTaller, int edad) {
		try {
			String s = "UPDATE talleres SET edad = " + edad
					+ " WHERE tallerID = " + idTaller;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}
	}

	public int getEdad(int idTaller) {
		int edadRetorno = 0;
		try {
			stmt.executeQuery("SELECT edad FROM talleres WHERE TallerID = "
					+ idTaller);
			ResultSet rs = stmt.getResultSet();
			rs.next(); // Va al registro ya validado
			edadRetorno = Integer.parseInt(rs.getString("edad"));
			rs.close();
			return (edadRetorno);
		} catch (SQLException e) {
			System.out.println("Cannot getEdad()" + e);
		}
		return edadRetorno;
	}

}
