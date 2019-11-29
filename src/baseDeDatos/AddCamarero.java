package baseDeDatos;

import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AddCamarero {

	String nombre, password;
	byte[] imagen;
	
	public AddCamarero(String nombre, String password, byte[] imagen) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.imagen = imagen;
	}
	
	public void subir() {
		// DATOS DE CONEXION A LA BASE DE DATOS
		String usuari = DatosConexion.usuari;
		String clau = DatosConexion.clau;
		String urlDades = DatosConexion.urlDades;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(urlDades, usuari, clau);

			// INSERTO LA COMANDA
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("INSERT INTO cambrers (nombre,password,imagen) VALUES (?,?,?)");
			stmt.setString(1, this.nombre);
			stmt.setString(2, this.password);
			Blob image = new SerialBlob(this.imagen);
			stmt.setBlob(3, image);
			
			stmt.executeUpdate();
			conn.close();
			System.out.println("Se ha subido un camarero a la base de datos.");
		} catch (ClassNotFoundException e) {
			System.out.println("Excepció del tipus SQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
