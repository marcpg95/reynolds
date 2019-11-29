package baseDeDatos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mms.mms.clases.Productes;
import com.mms.mms.clases.comandas.Comanda;
import com.mms.mms.clases.comandas.ProductosComanda;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import interfaz.Principal;

public class RecuperarComandas {
	
	public RecuperarComandas() {

	}
	
	public void consulta() {
		// DATOS DE CONEXION A LA BASE DE DATOS
				String usuari = DatosConexion.usuari;
				String clau = DatosConexion.clau;
				String urlDades = DatosConexion.urlDades;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = (Connection) DriverManager.getConnection(urlDades, usuari, clau);

					ArrayList<ProductosComanda> productos = new ArrayList<ProductosComanda>();
					ProductosComanda productosPedidos;
					Productes producto;
					Comanda c;
					int numeroMesa, cantidad, idProducto;
					String camarero, nom, descripcion;
					Blob image;
					byte[] imagen;
					float preu;
					boolean listo;
					
					String consultarComanda = "SELECT id, camarero FROM comandes";
					String consultarProductosPedidos, consultarProducto;
					Statement stmnt = (Statement) conn.createStatement();
					Statement stmnt2 = (Statement) conn.createStatement();
					Statement stmnt3 = (Statement) conn.createStatement();
					ResultSet resul, resul2, resul3;
					resul = stmnt.executeQuery(consultarComanda);

					while (resul.next()) {
						numeroMesa = resul.getInt("id");
						camarero = resul.getString("camarero");
						consultarProductosPedidos = "SELECT producto, cantidad, listo FROM productos_pedidos WHERE comanda = " + numeroMesa;
						resul2 = stmnt2.executeQuery(consultarProductosPedidos);
						while(resul2.next()) {
							idProducto = resul2.getInt("producto");
							cantidad = resul2.getInt("cantidad");
							listo = resul2.getBoolean("listo");
							consultarProducto = "SELECT nom, descripcion, image, preu FROM productes WHERE id = " + idProducto;
							resul3 = stmnt3.executeQuery(consultarProducto);
							while(resul3.next()) {
								nom = resul3.getString("nom");
								descripcion = resul3.getString("descripcion");
								image = (Blob) resul3.getBlob("image");
								imagen = image.getBytes(1, (int) image.length());
								preu = resul3.getFloat("preu");
								producto = new Productes(nom, descripcion, preu, idProducto, imagen);
								productosPedidos = new ProductosComanda(producto, cantidad, listo);
								productos.add(productosPedidos);
							}
							c = new Comanda(productos, camarero);
							Principal.comandas.put(numeroMesa, c);
						}
						productos = new ArrayList<ProductosComanda>();
					}
					System.out.println("Comandas cargadas de la base de datos.");
				} catch (SQLException e) {
					System.out.println("Excepció del tipus SQL");
					e.printStackTrace();
				} catch (ClassNotFoundException ex) {
					System.out.println("No se ha encontrado el Driver MySQL para JDBC.");
				}
	}

}
