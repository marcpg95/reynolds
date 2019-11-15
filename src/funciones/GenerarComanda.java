package funciones;

import java.io.File;
import org.w3c.dom.Node;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import tablaConCheckBox.JCheckBox_Cell;
import tablaConCheckBox.JCheckBox_Rendered;

public class GenerarComanda {

	public ArrayList<JTable> GenerarComandaBarra(int numeroMesa,JInternalFrame barra,int cantidadComandas) {
		ArrayList<JTable> arrayTablaBarra = new ArrayList<>();
		ArrayList<String> arrayComandaBarra = new ArrayList<>();
		try {
			
			
			for(int x=0;x<cantidadComandas;x++) {
			File archivoComanda = new File("Comandas/comanda" + (x+1) + ".xml");
			DocumentBuilderFactory dbfComanda = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilderComanda = dbfComanda.newDocumentBuilder();
			Document documentComanda = documentBuilderComanda.parse(archivoComanda);
			documentComanda.getDocumentElement().normalize();
			NodeList listaComanda = documentComanda.getElementsByTagName("producto");

			//System.out.println(listaComanda.getLength());

		
			for (int temp = 0; temp < listaComanda.getLength(); temp++) {

				Node nNode = listaComanda.item(temp);

				// System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					// System.out.println("Cantidad : " + eElement.getAttribute("cantidad"));
					// System.out.println("nombre : " +
					// eElement.getElementsByTagName("nombre").item(0).getTextContent());
					// System.out.println("precio : " +
					// eElement.getElementsByTagName("precio").item(0).getTextContent());
					String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();

					String cantidad = eElement.getAttribute("cantidad");
					String precio = eElement.getElementsByTagName("precio").item(0).getTextContent();
					//System.out.println(nombre);
					//System.out.println(cantidad);
					//System.out.println(precio);
					arrayComandaBarra.add(nombre);
					arrayComandaBarra.add(cantidad);
					arrayComandaBarra.add(precio);
				}
			}
			JTable tableComandaBarra = new JTable();
			
			
			

			//for (int i = 0; i < arrayComandaBarra.size(); i++) {
			//	System.out.print(arrayComandaBarra.get(i));
			//}
			int contadorProducto = 0;
			int contadorCantidad = 1;
			int contadorPrecio=2;
			int tamanoBucle=0;
			if(arrayComandaBarra.size()%2!=0) {
				tamanoBucle=((arrayComandaBarra.size() / 3)-1);
			}
			else {
				tamanoBucle=((arrayComandaBarra.size() / 3)+2);
				
			}
			
			
			Object[][] o = new Object[arrayComandaBarra.size() / 3][4];
			for (int i = 0; i < tamanoBucle; i++) {
				
				o[i][0] = arrayComandaBarra.get(i + contadorProducto);

				o[i][1] = arrayComandaBarra.get(i + contadorCantidad);
				o[i][2] =  arrayComandaBarra.get(i + contadorPrecio);;
				o[i][3] = false;
				
				contadorCantidad+=2;
				contadorProducto+=2;
				contadorPrecio+=2;
				
				

			}
			contadorCantidad=0;
			contadorProducto=0;
			contadorPrecio=0;
			
			
			tableComandaBarra.setShowVerticalLines(false);

			tableComandaBarra.setBounds(10, 153, 441, 263);
			barra.getContentPane().add(tableComandaBarra);
			tableComandaBarra.setModel(
					new DefaultTableModel(o, new String[] { "Producto", "Cantidad", "Precio", "Cobrar" }));

			JScrollPane scrollPaneBarra = new JScrollPane(tableComandaBarra);
			scrollPaneBarra.setBounds(53, 226, 386, 231);
			barra.getContentPane().add(scrollPaneBarra);
			tableComandaBarra.getColumnModel().getColumn(3)
					.setCellEditor(new JCheckBox_Cell(new JCheckBox()));
			tableComandaBarra.getColumnModel().getColumn(3).setCellRenderer(new JCheckBox_Rendered());
			
			
			tableComandaBarra.setVisible(false);
			arrayTablaBarra.add(tableComandaBarra);
			arrayComandaBarra.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		
		return arrayTablaBarra;
	}
	
}
