package funciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JTable;

public class Cobrar {
	
	public void CobrarBarra(int numeroMesa){
		
		
			
			
		 File borrarComanda = new File("Comandas/comanda" + (numeroMesa) + ".xml");
		 borrarComanda.renameTo(new File("Facturas/comanda" + (numeroMesa) + ".xml"));
		 
		
	     
		 
	        

		
		
		
		
		
	}

	public void RecuperarCobrarBarra(int numeroMesa){
		
		
			
		
		 File borrarComanda = new File("Facturas/comanda" + (numeroMesa) + ".xml");
		 borrarComanda.renameTo(new File("Comandas/comanda" + (numeroMesa) + ".xml"));
		
		 
	        

		
		
		
		
		
	}
	public class FileCopy {
	    public FileCopy(String sourceFile, String destinationFile) {
	        System.out.println("Desde: " + sourceFile);
	        System.out.println("Hacia: " + destinationFile);

	        try {
	            File inFile = new File(sourceFile);
	            File outFile = new File(destinationFile);

	            FileInputStream in = new FileInputStream(inFile);
	            FileOutputStream out = new FileOutputStream(outFile);

	            int c;
	            while( (c = in.read() ) != -1)
	                out.write(c);

	            in.close();
	            out.close();
	        } catch(IOException e) {
	            System.err.println("Hubo un error de entrada/salida!!!");
	        }
	    }


   }
}
