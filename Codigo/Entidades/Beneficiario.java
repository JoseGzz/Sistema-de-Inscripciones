package entidades;

import java.sql.*;
import java.io.*;

public class Beneficiario extends Usuario {

    Connection conn;
    Statement stmt;
    private String telefono;
    private String nombreTutor;
    private String apellidoTutor;
    private String telefonoTutor;

    public Beneficiario(String mat) {

        super(mat);
        nombreTutor = "Default";
        telefono = "12345678";
        apellidoTutor = "Default";
        telefonoTutor = "87654321";

        try {
                String userName = "root";
                String password = "";
                String url = "jdbc:mysql://localhost/inscripciones";
                Class.forName ("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection (url, userName, password);
                stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println ("Cannot connect to database server");
        }
    }

    public String getTelefono(String mat) {

        try {
                stmt.executeQuery("SELECT Telefono FROM Beneficiarios WHERE Matricula = "
                        + mat);
                ResultSet rs = stmt.getResultSet();
                rs.next();
                telefono = rs.getString("Telefono");
                rs.close();
                return (telefono);
            } catch (SQLException e) {
                System.out.println("Cannot getTelefono()" + e);
            }
            return telefono;
    }

    public void crearBeneficiario(String nombre, String apellido, int edad, String telefono, String tutor, int matricula) {

        try {
            String s = "INSERT INTO beneficiario (Nombre, Apellido, Edad, Telefono, Tutor, Matricula)" +
                " VALUES ('" + nombre + "', '" + apellido + "' , " + edad + " , '" + telefono + "' , '" + tutor + "' , " + matricula + " )";

            System.out.println(s);
            stmt.executeUpdate(s);
        } catch (SQLException e) {
			System.out.println("Cannot crearBeneficiario()" + e);
		}
    }

    public void inscribir(int matricula, String nombre) {

        int tallerID = 19;

        try {

             stmt.executeQuery("SELECT TallerID FROM talleres WHERE Nombre = '" + nombre + "'");
                ResultSet rs = stmt.getResultSet();
                rs.next();
                tallerID = rs.getInt("TallerID");
                rs.close();

        } catch (SQLException e) {
            System.out.println("Cannot crearBeneficiario()" + e);
        }

        try {
             String s = "INSERT INTO inscripcion (TallerID, Matricula)" +
                " VALUES (" + tallerID + ", " + matricula + " )";

            System.out.println(s);
            stmt.executeUpdate(s);

        } catch (SQLException e) {
            System.out.println("Cannot crearBeneficiario()" + e);
        }
    }

    public void desplegarPerfil(int matricula) {

     try {
            stmt.executeQuery("SELECT * FROM Beneficiarios WHERE Matricula = " + mat);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombreTutor = rs.getString("NombreTutor");
            rs.close();

            } catch (SQLException e) {
                System.out.println("Cannot desplegarPerfilTutor()" + e);
            }
    }


    public String getNombre(int mat) {

        String nombre = "";

        try {
                stmt.executeQuery("SELECT Nombre FROM beneficiario WHERE Matricula = " + mat);
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

    public String getApellido(int mat) {

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
			System.out.println("Cannot getApellido" + e);
		}
		return apellido;
	}

    public String getTelefono(int mat) {

     String tel = "";

          try {
                stmt.executeQuery("SELECT Telefono FROM beneficiario WHERE Matricula = "
                        + mat);
                ResultSet rs = stmt.getResultSet();
                rs.next();
                tel = rs.getString("Telefono");
                rs.close();
                return (tel);
            } catch (SQLException e) {
                System.out.println("Cannot getTelefono()" + e);
            }
        return tel;
    }


    public int getEdad(int mat) {
        int edad = 0;

          try {
                stmt.executeQuery("SELECT edad FROM beneficiario WHERE Matricula = "
                        + mat);
                ResultSet rs = stmt.getResultSet();
                rs.next();
                edad = rs.getInt("edad");
                rs.close();
                return (edad);
            } catch (SQLException e) {
                System.out.println("Cannot getEdad()" + e);
            }
        return edad;
    }

     public int contarTalleres(int mat) {
         int count = 0;
        try {
                stmt.executeQuery(" SELECT TallerID, COUNT(*) FROM inscripcion WHERE Matricula = "
                        + mat);
                ResultSet rs = stmt.getResultSet();
                rs.next();
                count = rs.getInt("COUNT(*)");
                rs.close();
                return (count);
            } catch (SQLException e) {
                System.out.println("Cannot contarTalleres()" + e);
            }
        return count;
     }

    public String obtenerTalleres(int mat, int indice) {
        String id = null;
        try {
            stmt.executeQuery("SELECT * FROM inscripcion WHERE Matricula = " + mat);
                ResultSet rs = stmt.getResultSet();
               for(int j = 0; j < indice; j++) {
                    rs.next();
               }
                id = rs.getString(1);
                rs.close();
                return (id);
            } catch (SQLException e) {
                System.out.println("Cannot obtenerTalleres()" + e);
            }
        return id;

    }

    public String desplegarTalleres(int idtaller) {
        String nombre = "";
        try {
            stmt.executeQuery(" SELECT Nombre FROM talleres WHERE TallerID = " + idtaller);
                ResultSet rs = stmt.getResultSet();
                rs.next();
                nombre = rs.getString("Nombre");
                rs.close();
                return (nombre);
            } catch (SQLException e) {
                System.out.println("Cannot desplegarTalleres()" + e);
            }
        return nombre;
    }


    public String getNombreTutor(int mat) {
        String nombre = "";
        try {
                stmt.executeQuery("SELECT Tutor FROM beneficiario WHERE Matricula = "
                        + mat);
                ResultSet rs = stmt.getResultSet();
                rs.next();
                nombre = rs.getString("Tutor");
                rs.close();
                return (nombre);
            } catch (SQLException e) {
                System.out.println("Cannot getNombreTutor()" + e);
            }
            return nombre;
    }

    public String getApellidoTutor(String mat) {
        try {
                stmt.executeQuery("SELECT ApellidoTutor FROM Beneficiarios WHERE Matricula = "
                        + mat);
                ResultSet rs = stmt.getResultSet();
                rs.next();
                apellidoTutor = rs.getString("ApellidoTutor");
                rs.close();
                return (apellidoTutor);
            } catch (SQLException e) {
                System.out.println("Cannot getApellidoTutor()" + e);
            }
        return apellidoTutor;
    }

    public String getTelefonoTutor(String mat) {
        try {
                stmt.executeQuery("SELECT TelefonoTutor FROM Beneficiarios WHERE Matricula = "
                        + mat);
                ResultSet rs = stmt.getResultSet();
                rs.next();
                telefonoTutor = rs.getString("TelefonoTutor");
                rs.close();
                return (telefonoTutor);
            } catch (SQLException e) {
                System.out.println("Cannot getTelefonoTutor()" + e);
            }
        return telefonoTutor;
    }

    public void setTelefono(String mat, String tel) {
        try {
			String s = "UPDATE Beneficiarios SET Telefono = " + tel
					+ " WHERE Matricula = " + mat;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute setTelefono()" + e);
		}
    }

    public void setNombreTutor(String mat, String nomT) {
        try {
			String s = "UPDATE Beneficiarios SET NombreTutor = " + nomT
					+ " WHERE Matricula = " + mat;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute setNombreTutor()" + e);
		}
    }

    public void setApellidoTutor(String mat, String apT) {
        try {
			String s = "UPDATE Beneficiarios SET ApellidoTutor = " + apT
					+ " WHERE Matricula = " + mat;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute setApellidoTutor()" + e);
		}
    }

    public void setTelefonoTutor(String mat, String telT) {
        try {
			String s = "UPDATE Beneficiarios SET TelefonoTutor = " + telT
					+ " WHERE Matricula = " + mat;
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Cannot execute setTelefonoTutor()" + e);
		}
    }


}
