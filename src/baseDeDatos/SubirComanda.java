package baseDeDatos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mms.mms.clases.Categories;
import com.mms.mms.clases.Productes;
import com.mms.mms.clases.comandas.Comanda;
import com.mms.mms.clases.comandas.ProductosComanda;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class SubirComanda {

	Comanda c;
	int numeroMesa;

	public SubirComanda(Comanda c, int numeroMesa) {
		super();
		this.c = c;
		this.numeroMesa = numeroMesa;
	}

	public void subir() {
		// DATOS DE CONEXION A LA BASE DE DATOS
		String usuari = DatosConexion.usuari;
		String clau = DatosConexion.clau;
		String urlDades = DatosConexion.urlDades;

		String camarero = c.getCamarero();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(urlDades, usuari, clau);

			// INSERTO LA COMANDA
			PreparedStatement stmt = (PreparedStatement) conn
					.prepareStatement("INSERT INTO comandes (id,camarero) VALUES (?,?)");
			stmt.setInt(1, numeroMesa);
			stmt.setString(2, camarero);
			stmt.executeUpdate();

			// INSERTO LOS PRODUCTOS DE LA COMANDA
			ArrayList<ProductosComanda> pc = c.getProductosPedidos();
			PreparedStatement stmt2 = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO productos_pedidos (comanda,producto,cantidad,listo) VALUES (?,?,?,?)");
			stmt2.setInt(1, numeroMesa);
			for (int i = 0; i < pc.size(); i++) {
				stmt2.setInt(2, pc.get(i).getProducto().getId());
				stmt2.setInt(3, pc.get(i).getCantidad());
				stmt2.setBoolean(4, pc.get(i).isListo());
				stmt2.executeUpdate();
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Excepció del tipus SQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//BORRA UNA COMANDA
	public void borrarComanda() {
		// DATOS DE CONEXION A LA BASE DE DATOS
		String usuari = DatosConexion.usuari;
		String clau = DatosConexion.clau;
		String urlDades = DatosConexion.urlDades;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(urlDades, usuari, clau);
			
			//BORRO LOS PRODUCTOS ACTUALMENTE DE ESA COMANDA
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("DELETE FROM productos_pedidos where comanda = " + this.numeroMesa);
			PreparedStatement stmt2 = (PreparedStatement) conn.prepareStatement("DELETE FROM comandes where id = " + this.numeroMesa);
			
			stmt.execute();
			stmt.close();
			
			stmt2.execute();
			stmt2.close();
			
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Excepció del tipus SQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
