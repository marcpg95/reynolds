package funciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JTable;

public class Cobrar {
	// * Mueve el fichero de comandas a facturas
	public void CobrarBarra(int numeroMesa) {

		File borrarComanda = new File("Comandas/comanda" + (numeroMesa) + ".xml");
		borrarComanda.renameTo(new File("Facturas/comanda" + (numeroMesa) + ".xml"));

	}

	// Recupera el fichero de Facturas a comandas
	public void RecuperarCobrarBarra(int numeroMesa) {

		File borrarComanda = new File("Facturas/comanda" + (numeroMesa) + ".xml");
		borrarComanda.renameTo(new File("Comandas/comanda" + (numeroMesa) + ".xml"));

	}

}
