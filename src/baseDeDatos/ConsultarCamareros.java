package baseDeDatos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ConsultarCamareros {
	
	private ArrayList<String> camareros;

	public ConsultarCamareros() {
		super();
		this.camareros = new ArrayList<String>();
		consulta();
	}

	public ArrayList<String> getCamareros() {
		return camareros;
	}

	public void consulta() {
		//DATOS DE CONEXION A LA BASE DE DATOS
		String usuari = "sql7312079";
		String clau = "8TpCCSEgdP";
		String urlDades = "jdbc:mysql://sql7.freesqldatabase.com/sql7312079?serverTimezone=UTC";
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(urlDades, usuari, clau);

			String consulta = "SELECT nombre FROM cambrers";
			
			Statement stmnt = (Statement) conn.createStatement();
			ResultSet resul = stmnt.executeQuery(consulta);
			
			while(resul.next()) {
				this.camareros.add(resul.getString("nombre"));
			}
			
		} catch (SQLException e) {
			System.out.println("Excepció del tipus SQL");
			e.printStackTrace();

		} catch (ClassNotFoundException ex) {
			System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
		}
		
	}

}
