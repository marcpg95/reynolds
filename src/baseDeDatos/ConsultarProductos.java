package baseDeDatos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.mms.mms.clases.Categories;
import com.mms.mms.clases.Productes;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ConsultarProductos {

	private HashMap<String, Categories> categorias;

	public ConsultarProductos() {
		super();
		this.categorias = new HashMap<String, Categories>();
		consulta();
	}

	public HashMap<String, Categories> getProductos() {
		return this.categorias;
	}

	public void consulta() {
		// DATOS DE CONEXION A LA BASE DE DATOS
		String usuari = DatosConexion.usuari;
		String clau = DatosConexion.clau;
		String urlDades = DatosConexion.urlDades;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(urlDades, usuari, clau);
			String consulta = "SELECT id, nom, descripcion, image, preu, categoria FROM productes";

			Statement stmnt = (Statement) conn.createStatement();
			ResultSet resul = stmnt.executeQuery(consulta);

			int id;
			float preu;
			String nom, descripcion, image, categoria;

			HashMap<Integer, Productes> productos = new HashMap<Integer, Productes>(); // ESTO VA A CATEGORIES
			String auxiliarCategoria = "";

			while (resul.next()) {
				id = resul.getInt("id");
				nom = resul.getString("nom");
				descripcion = resul.getString("descripcion");
				image = resul.getString("image");
				preu = resul.getFloat("preu");
				categoria = resul.getString("categoria");

				if (!auxiliarCategoria.equals("") && !auxiliarCategoria.equals(categoria)) { //CUANDO CAMBIE DE CATEGORIA
					Categories c = new Categories(productos);
					this.categorias.put(auxiliarCategoria, c); //GUARDO LA CATEGORIA Y SUS PRODUCTOS
					productos = new HashMap<Integer, Productes>(); //REINICIO EL HASHMAP
				}

				auxiliarCategoria = categoria;

				Productes p = new Productes(nom, descripcion, image, preu, id);
				productos.put(id, p);
			}
			Categories c = new Categories(productos);
			this.categorias.put(auxiliarCategoria, c); 
		} catch (SQLException e) {
			System.out.println("Excepció del tipus SQL");

		} catch (ClassNotFoundException ex) {
			System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
		}

	}

}