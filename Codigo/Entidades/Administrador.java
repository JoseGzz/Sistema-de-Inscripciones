package entidades;
import java.sql.*;
import java.io.*;

public class Administrador extends Usuario {

	Connection conn;
	Statement stmt;
	protected String matricula;
	protected String nombre;
	protected String apellidoP;
	protected String apellidoM;
	protected String email;
	protected String telefono;

	public Administrador(String mat) {

        super(mat);

       try {
			String userName = "root";
			String password = "";
			String url = "jdbc:mysql://localhost/inscripciones";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("Cannot connect to database server");
		}

		try {
			String s = "INSERT INTO Administradores (Matricula)" + " VALUES (" + mat
					+ " )";
			System.out.println(s);
			stmt.executeUpdate(s);
		} catch (Exception e) {
			System.out.println("Cannot update database" + e);
		}

		nombre = "Default";
		telefono = "12345678";
		apellidoP = "Default";
		apellidoM = "Default";
		email = "Default";
		matricula = "01234567";
	}

	public String getNombre(String mat) {
		try {
			stmt.executeQuery("SELECT Nombre FROM Administradores WHERE Matricula = "
					+ mat);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			nombre = rs.getString("Nombre");
			rs.close();
			return (nombre);
		} catch (SQLException e) {
			System.out.println("Cannot getNombre()" + e);
		}
		return nombre;
	}

	public String getApellidoP(String mat) {
		try {
			stmt.executeQuery("SELECT ApellidoP FROM Administradores WHERE Matricula = "
					+ mat);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			apellidoP = rs.getString("ApellidoP");
			rs.close();
			return (apellidoP);
		} catch (SQLException e) {
			System.out.println("Cannot getSaldoApellidoP()" + e);
		}
		return apellidoP;
	}

	public String getApellidoM(String mat) {

		try {
			stmt.executeQuery("SELECT ApellidoM FROM Administradores WHERE Matricula = "
					+ mat);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			apellidoM = rs.getString("ApellidoM");
			rs.close();
			return (apellidoM);
		} catch (SQLException e) {
			System.out.println("Cannot getApellidoM()" + e);
		}
		return apellidoM;

	}

	public String getEmail(String mat) {

		try {
			stmt.executeQuery("SELECT Email FROM Administradores WHERE Matricula = "
					+ mat);
			ResultSet rs = stmt.getResultSet();
			rs.next();
			email = rs.getString("Email");
			rs.close();
			return (email);
		} catch (SQLException e) {
			System.out.println("Cannot getEmail()" + e);
		}
		return email;

	}

	public void setNombre(String mat, String nom) {

		try {
			String s = "UPDATE Administradores SET Nombre = " + nom
					+ " WHERE Matricula = " + mat;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}
	}

	public void setApellidoP(String mat, String apP) {

		try {
			String s = "UPDATE Administradores SET ApellidoP = " + apP
					+ " WHERE Matricula = " + mat;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}
	}

	public void setApellidoM(String mat, String apM) {

		try {
			String s = "UPDATE Administradores SET ApellidoM = " + apM
					+ " WHERE Matricula = " + mat;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}

	}

	public void setEmail(String mat, String mail) {

		try {
			String s = "UPDATE Administradores SET Email = " + mail
					+ " WHERE Matricula = " + mat;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute disposicion()" + e);
		}

	}

}
