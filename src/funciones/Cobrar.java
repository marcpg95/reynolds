package funciones;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JTable;

public class Cobrar {
	
	public void CobrarBarra(ArrayList<String> arrayNumeroMesa,int numeroMesa){
		
		
			
			
		 File borrarComanda = new File("Comandas/comanda" + (numeroMesa) + ".xml");
		 System.out.println(numeroMesa);
	        borrarComanda.delete();

		
		
		
		
		
	}

}
