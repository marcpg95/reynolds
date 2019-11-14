package funciones;

import java.io.File;
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
			File archivoComanda = new File("archivos/comanda"+numeroMesa+".xml");
			DocumentBuilderFactory dbfComanda = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilderComanda = dbfComanda.newDocumentBuilder();
			Document documentComanda = documentBuilderComanda.parse(archivoComanda);
			documentComanda.getDocumentElement().normalize();
			NodeList listaComanda = documentComanda.getElementsByTagName("producto");
			
			System.out.println(listaComanda.getLength());
			
			for (int i = 0; i < listaComanda.getLength(); i++) {
				Element elementoMesa = (Element) listaComanda.item(i);
				NodeList elementoProducto = elementoMesa.getElementsByTagName("nombre");
				for (int j = 0; j < elementoProducto.getLength(); j++) {
					
					Element elementoCantidad = (Element) elementoMesa.getElementsByTagName("nombre").item(j);
					String nombreProducto = elementoCantidad.getTextContent();
					Element elementoPrecio = (Element) elementoMesa.getElementsByTagName("precio").item(j);
					String precio = elementoPrecio.getTextContent();
					System.out.println(nombreProducto);
					Element listaCantidad = (Element) elementoMesa.getElementsByTagName("producto").item(0);
					
					System.out.println(precio);
					String cantidadProducto = listaCantidad.getAttribute("cantidad");
					System.out.println(cantidadProducto);
					arrayComandaBarra.add(nombreProducto);
					arrayComandaBarra.add(cantidadProducto);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayComandaBarra;
	}
}
