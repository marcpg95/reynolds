package baseDeDatos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mms.mms.clases.Camarero;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ConsultarCamareros {
	
	private ArrayList<Camarero> camareros;

	public ConsultarCamareros() {
		super();
		this.camareros = new ArrayList<Camarero>();
		consulta();
	}

	public ArrayList<Camarero> getCamareros() {
		return camareros;
	}

	public void consulta() {
		//DATOS DE CONEXION A LA BASE DE DATOS
		String usuari = DatosConexion.usuari;
		String clau = DatosConexion.clau;
		String urlDades = DatosConexion.urlDades;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(urlDades, usuari, clau);
			String consulta = "SELECT nombre, password, imagen FROM cambrers";
			Camarero c;
			
			Statement stmnt = (Statement) conn.createStatement();
			ResultSet resul = stmnt.executeQuery(consulta);
			
			String nombre, password;
			Blob image;
			byte[] imagen;
			
			while(resul.next()) {
				nombre = resul.getString("nombre");
				password = resul.getString("password");
				image = (Blob) resul.getBlob("imagen");
				imagen = image.getBytes(1, (int) image.length());
				
				c = new Camarero(nombre, password, imagen);
				this.camareros.add(c);
			}
			
		} catch (SQLException e) {
			System.out.println("Excepció del tipus SQL");

		} catch (ClassNotFoundException ex) {
			System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
		}
		
	}

}
