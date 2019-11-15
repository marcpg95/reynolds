package funciones;

import java.io.File;
import org.w3c.dom.Node;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GenerarComanda {

	public ArrayList<String> GenerarComandaBarra(int numeroMesa) {

		ArrayList<String> arrayComandaBarra = new ArrayList<>();
		try {
			File archivoComanda = new File("archivos/comanda" + numeroMesa + ".xml");
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		return arrayComandaBarra;
	}
}
