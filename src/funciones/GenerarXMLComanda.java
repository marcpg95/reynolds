package funciones;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mms.mms.clases.comandas.ProductosComanda;

public class GenerarXMLComanda {
	
	ArrayList<ProductosComanda> productos;
    int numeroMesa;
    File nuevoXML;
	
	public GenerarXMLComanda(ArrayList<ProductosComanda> productos, int numeroMesa) {
        this.productos = productos;
        this.numeroMesa = numeroMesa;
    }
	
	 public void crearXML() {
	        try {
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.newDocument();

	            //ESTA ES LA RAIZ DE LA COMANDA
	            Element raiz = doc.createElement("comanda");
	            doc.appendChild(raiz);

	            //ESTE ES EL NODO PRINCIPAL QUE CONTIENE LOS PRODUCTOS PEDIDOS
	            Element nMesa = doc.createElement("mesa");
	            raiz.appendChild(nMesa);

	            //PONGO COMO ATRIBUTO ID AL NODO PRINCIPAL EL NUMERO DE LA MESA
	            Attr attr = doc.createAttribute("id");
	            attr.setValue(Integer.toString(this.numeroMesa));
	            nMesa.setAttributeNode(attr);

	            //DEFINO EL ELEMENTO PRODUCTOS QUE CONTENDRA CADA PRODUCTO Y SU CANTIDAD
	            Element nProducto, nNombre, nPrecio;
	            Attr cantidad ;
	            int cant;
	            String nombre;
	            float precio;

	            //RECORRO EL ARRAYLIST CREANDO LOS NODOS DE CADA PRODUCTO
	            for(int i = 0; i < this.productos.size(); i++) {
	                nProducto = doc.createElement("producto");

	                nNombre = doc.createElement("nombre");
	                nombre = this.productos.get(i).getProducto().getNom();
	                nNombre.appendChild(doc.createTextNode(nombre));

	                cantidad = doc.createAttribute("cantidad");
	                cant = this.productos.get(i).getCantidad();
	                cantidad.setValue(Integer.toString(cant));

	                nPrecio = doc.createElement("precio");
	                precio = this.productos.get(i).getProducto().getPreu();
	                nPrecio.appendChild(doc.createTextNode(Float.toString(precio)));

	                nProducto.setAttributeNode(cantidad);
	                nProducto.appendChild(nNombre);
	                nProducto.appendChild(nPrecio);
	                nMesa.appendChild(nProducto);
	            }

	            //FINALIZO LA CREACION DEL XML
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            String nombreArchivo = "comanda" + this.numeroMesa+".xml";
	            nuevoXML = new File("Comandas" + File.separator + nombreArchivo);
	            StreamResult result = new StreamResult(nuevoXML);
	            transformer.transform(source, result);
	        } catch(Exception e) {
	        	System.out.println("Se ha producido un error generando el XML.");
	        }
	    }

}