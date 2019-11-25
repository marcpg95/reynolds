package funciones;

import java.io.File;
import org.w3c.dom.Node;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import clases.ProductosFactura;
import interfaz.Principal;

public class GenerarComanda {

	public GenerarComanda() {

	}

//*Genera las comandas leyendo los ficheros xml
	public ArrayList<JTable> GenerarComandaBarra(int numeroMesa, int cantidadComandas) {
		ArrayList<JTable> arrayTablaBarra = new ArrayList<>();
		ArrayList<String> arrayComandaBarra = new ArrayList<>();
		int comandasCreadas = 0;
		int contadorComanda = 0;

		try {

			while (comandasCreadas < cantidadComandas) {

				File archivoComanda = new File("Comandas/comanda" + (contadorComanda + 1) + ".xml");

				contadorComanda++;

				if (archivoComanda.exists()) {

					comandasCreadas++;
					DocumentBuilderFactory dbfComanda = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilderComanda = dbfComanda.newDocumentBuilder();
					Document documentComanda = documentBuilderComanda.parse(archivoComanda);
					documentComanda.getDocumentElement().normalize();
					NodeList listaComanda = documentComanda.getElementsByTagName("producto");
					NodeList mesaComanda = documentComanda.getElementsByTagName("mesa");

					// System.out.println(listaComanda.getLength());

					for (int temp = 0; temp < listaComanda.getLength(); temp++) {

						Node nNode = listaComanda.item(temp);
						Node nNodeMesa = mesaComanda.item(0);

						// System.out.println("\nCurrent Element :" + nNode.getNodeName());

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;
							Element eElementMesa = (Element) nNodeMesa;

							// System.out.println("Cantidad : " + eElement.getAttribute("cantidad"));
							// System.out.println("nombre : " +
							// eElement.getElementsByTagName("nombre").item(0).getTextContent());
							// System.out.println("precio : " +
							// eElement.getElementsByTagName("precio").item(0).getTextContent());
							String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();

							String cantidad = eElement.getAttribute("cantidad");
							String mesa = eElementMesa.getAttribute("id");
							String precio = eElement.getElementsByTagName("precio").item(0).getTextContent();

							// System.out.println(nombre);
							// System.out.println(cantidad);
							// System.out.println(precio);

							arrayComandaBarra.add(nombre);
							arrayComandaBarra.add(cantidad);
							arrayComandaBarra.add(precio);
							
						}
					}
					JTable tableComandaBarra = new JTable();

					// for (int i = 0; i < arrayComandaBarra.size(); i++) {
					// System.out.print(arrayComandaBarra.get(i));
					// }
					int contadorProducto = 0;
					int contadorCantidad = 1;
					int contadorPrecio = 2;
					float precioTotal = 0;
					float auxPrecioTotal = 0;
					
					ArrayList<ProductosFactura> p = new ArrayList<ProductosFactura>();

					Object[][] o = new Object[(arrayComandaBarra.size()) / 3][4];
					for (int i = 0; i < (arrayComandaBarra.size()) / 3; i++) {

						o[i][0] = arrayComandaBarra.get((i) + contadorProducto);

						o[i][1] = arrayComandaBarra.get((i) + contadorCantidad);
						o[i][2] = arrayComandaBarra.get((i) + contadorPrecio);
											
						ProductosFactura pf = new ProductosFactura(Integer.parseInt(arrayComandaBarra.get((i) + contadorCantidad)), arrayComandaBarra.get((i) + contadorProducto), Float.parseFloat(arrayComandaBarra.get((i) + contadorPrecio)));
						p.add(pf);
												
						precioTotal += Float.parseFloat(arrayComandaBarra.get((i) + contadorPrecio));

						precioTotal *= Float.parseFloat(arrayComandaBarra.get((i) + contadorCantidad));
						// System.out.println((arrayComandaBarra.get((i) + contadorCantidad)));
						// System.out.println((arrayComandaBarra.get((i) + contadorPrecio)));
						auxPrecioTotal += precioTotal;
						o[i][3] = false;

						contadorCantidad += 2;
						contadorProducto += 2;
						contadorPrecio += 2;
						precioTotal = 0;

					}

					Principal.facturar.put(contadorComanda - 1, p);

					auxPrecioTotal *= 1.21;
					guardarPrecioMesa(auxPrecioTotal);

					contadorCantidad = 0;
					contadorProducto = 0;
					contadorPrecio = 0;
					precioTotal = 0;

					tableComandaBarra.setShowVerticalLines(false);

					tableComandaBarra.setBounds(0, 0, 441, 263);

					tableComandaBarra.setModel(
							new DefaultTableModel(o, new String[] { "Producto", "Cantidad", "Precio", "Cobrar" }));

					tableComandaBarra.setVisible(true);
					arrayTablaBarra.add(tableComandaBarra);
					arrayComandaBarra.clear();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		return arrayTablaBarra;
	}

	ArrayList<Float> precioMesa = new ArrayList<>();

//* Guarda el precio de las comandas de cada mesa
	public <ArrayList> void guardarPrecioMesa(Float precioMesa) {

		this.precioMesa.add(precioMesa);

	}

//* Usa el precio almacenado anteriormente
	public ArrayList<Float> usarPrecioMesa() {

		// System.out.println("entrausar");
		/*for (int i = 0; i < precioMesa.size(); i++) {
			System.out.println(precioMesa.get(i));
		}
*/
		return precioMesa;

	}

//*Guarda el numero de la mesa de las comandas
	ArrayList<String> numeroMesaCocina = new ArrayList<>();

	public void guardarNumeroMesa(String numeroMesa) {

		this.numeroMesaCocina.add(numeroMesa);

	}

//* Usa el numero de mesa de las comandas
	public ArrayList<String> usarNumeroMesa() {

		return numeroMesaCocina;

	}

//* Genera las comandas para cocina sin el precio
	public ArrayList<JTable> GenerarComandaCocina(int numeroMesa, int cantidadComandas) {
		ArrayList<JTable> arrayTablaCocina = new ArrayList<>();
		ArrayList<String> arrayComandaCocina = new ArrayList<>();
		int comandasCreadas = 0;
		int contadorComanda = 0;
		try {

			while (comandasCreadas < cantidadComandas) {
				// System.out.println("1");
				File archivoComanda = new File("Comandas/comanda" + (contadorComanda + 1) + ".xml");

				contadorComanda++;
				// System.out.println(comandasCreadas);
				// System.out.println(cantidadComandas);
				if (archivoComanda.exists()) {
					comandasCreadas++;

					DocumentBuilderFactory dbfComanda = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilderComanda = dbfComanda.newDocumentBuilder();
					Document documentComanda = documentBuilderComanda.parse(archivoComanda);
					documentComanda.getDocumentElement().normalize();
					NodeList listaComanda = documentComanda.getElementsByTagName("producto");
					NodeList mesaComanda = documentComanda.getElementsByTagName("mesa");

					// System.out.println(listaComanda.getLength());

					for (int temp = 0; temp < listaComanda.getLength(); temp++) {

						Node nNode = listaComanda.item(temp);
						Node nNodeMesa = mesaComanda.item(0);

						// System.out.println("\nCurrent Element :" + nNode.getNodeName());

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;
							Element eElementMesa = (Element) nNodeMesa;

							// System.out.println("Cantidad : " + eElement.getAttribute("cantidad"));
							// System.out.println("nombre : " +
							// eElement.getElementsByTagName("nombre").item(0).getTextContent());
							// System.out.println("precio : " +
							// eElement.getElementsByTagName("precio").item(0).getTextContent());
							String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();

							String cantidad = eElement.getAttribute("cantidad");
							String mesa = eElementMesa.getAttribute("id");

							// System.out.println(nombre);
							// System.out.println(cantidad);
							// System.out.println(precio);
							if (temp == 0) {
								guardarNumeroMesa(mesa);
							}
							arrayComandaCocina.add(nombre);
							arrayComandaCocina.add(cantidad);

						}
					}
					JTable tableComandaCocina = new JTable();
					JTable tableServidoCocina = new JTable();

					// for (int i = 0; i < arrayComandaBarra.size(); i++) {
					// System.out.print(arrayComandaBarra.get(i));
					// }
					int contadorProducto = 0;
					int contadorCantidad = 1;

					Object[][] comandao = new Object[(arrayComandaCocina.size()) / 2][3];
					for (int i = 0; i < (arrayComandaCocina.size()) / 2; i++) {

						comandao[i][0] = arrayComandaCocina.get((i) + contadorProducto);

						comandao[i][1] = arrayComandaCocina.get((i) + contadorCantidad);

						comandao[i][2] = true;

						contadorCantidad++;
						contadorProducto++;

					}
					contadorCantidad = 0;
					contadorProducto = 0;
					Object[][] servido = new Object[0][3];
					for (int i = 0; i < (arrayComandaCocina.size()) / 2; i++) {

						
						

					}
					tableServidoCocina.setShowVerticalLines(false);

					tableServidoCocina.setBounds(0, 0, 441, 263);

					tableServidoCocina
							.setModel(new DefaultTableModel(servido, new String[] { "Producto", "Cantidad","Devolver"}));

					tableServidoCocina.setVisible(true);
					
					

					tableComandaCocina.setShowVerticalLines(false);

					tableComandaCocina.setBounds(0, 0, 441, 263);

					tableComandaCocina
							.setModel(new DefaultTableModel(comandao, new String[] { "Producto", "Cantidad", "Listo" }));

					tableComandaCocina.setVisible(true);
					arrayTablaCocina.add(tableComandaCocina);
					arrayTablaCocina.add(tableServidoCocina);
					arrayComandaCocina.clear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		return arrayTablaCocina;
	}
	public  ArrayList<JTable> tableServido(JTable arrayTablaCocina) {
		
		
		return null;
		
	}

}
