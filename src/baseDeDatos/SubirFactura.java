package baseDeDatos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import clases.ProductosFactura;

public class SubirFactura {
		
	private ArrayList<ProductosFactura> productos;
	private String camarero;
	private int numeroMesa;
	
	public SubirFactura(ArrayList<ProductosFactura> prod, String cambrer, int mesa) {
		super();
		this.productos = prod;
		this.camarero = cambrer;
		this.numeroMesa = mesa;
	}
	
	public void enviarFactura() {
		
		//DATOS DE CONEXION A LA BASE DE DATOS
		String usuari = "sql7312079";
		String clau = "8TpCCSEgdP";
		String urlDades = "jdbc:mysql://sql7.freesqldatabase.com/sql7312079?serverTimezone=UTC";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(urlDades, usuari, clau);

			String consultarMaxID = "SELECT id FROM factures";
			Statement stmnt = (Statement) conn.createStatement();
			ResultSet resul = stmnt.executeQuery(consultarMaxID);
			
			int id = 0; 
			
			while(resul.next()) {
				id = resul.getInt("id") + 1; //LA ID QUE TENDRÁ LA NUEVA FACTURA
			}
						
			//AÑADO LOS DATOS A LA TABLA FACTURAS
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("INSERT INTO factures (id,numeroMesa,camarero) VALUES (?,?,?)");
			stmt.setString(1, Integer.toString(id));
			stmt.setString(2, Integer.toString(numeroMesa));
			stmt.setString(3, camarero);
			stmt.executeUpdate();
			
			//AÑADO LOS DATOS DEL PRODUCTO A LA TABLA PRODUCTOS_FACTURA
			PreparedStatement stmt2;
			for(int i = 0; i < productos.size(); i++) {
			stmt2 = (PreparedStatement) conn.prepareStatement("INSERT INTO productos_factura (factura,producto,cantidad,precio) VALUES (?,?,?,?)");
			stmt2.setString(1, Integer.toString(id));
			stmt2.setString(2, productos.get(i).getNombreProducto());
			stmt2.setString(3, Integer.toString(productos.get(i).getCantidadProducto()));
			stmt2.setString(4, Float.toString(productos.get(i).getPrecioProducto()));
			stmt2.executeUpdate();		
			}
		} catch (SQLException e) {
			System.out.println("Excepció del tipus SQL");
			e.printStackTrace();

		} catch (ClassNotFoundException ex) {
			System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
		}

	}

}
