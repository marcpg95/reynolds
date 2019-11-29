package baseDeDatos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.mms.mms.clases.Categories;
import com.mms.mms.clases.Productes;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
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
			String consulta2;

			Statement stmnt = (Statement) conn.createStatement();
			Statement stmnt2 = (Statement) conn.createStatement();
			ResultSet resul = stmnt.executeQuery(consulta);
			ResultSet resulCategory;

			int id;
			float preu;
			String nom, descripcion, categoria;
			Blob imagenCategoria = null;
			Blob image;
			byte[] imageCategory, imagen;

			HashMap<Integer, Productes> productos = new HashMap<Integer, Productes>(); // ESTO VA A CATEGORIES

			while (resul.next()) {
				id = resul.getInt("id");
				nom = resul.getString("nom");
				descripcion = resul.getString("descripcion");
				image = (Blob) resul.getBlob("image");
				imagen = image.getBytes(1, (int) image.length());
				preu = resul.getFloat("preu");
				categoria = resul.getString("categoria");
				Productes p = new Productes(nom, descripcion, preu, id, imagen);
				
				if(this.categorias.containsKey(categoria)) {
					this.categorias.get(categoria).getProductos().put(id, p);
				} else {
					consulta2 = "SELECT imagen FROM categories WHERE categoria = '" + categoria + "'";
					resulCategory = stmnt2.executeQuery(consulta2);
					while(resulCategory.next()) {
						imagenCategoria = (Blob) resulCategory.getBlob("imagen");
					}
					imageCategory = imagenCategoria.getBytes(1, (int) imagenCategoria.length());
					productos = new HashMap<Integer, Productes>();
					productos.put(id, p);
					Categories c = new Categories(productos, imageCategory);
					this.categorias.put(categoria, c);
				}
			}
			System.out.println("Productos cargados de la base de datos.");
		} catch (SQLException e) {
			System.out.println("Excepció del tipus SQL");

		} catch (ClassNotFoundException ex) {
			System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
		}

	}

}