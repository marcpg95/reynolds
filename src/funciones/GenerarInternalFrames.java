package funciones;

import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GenerarInternalFrames {
	
	ArrayList<JInternalFrame> arrayInternalFrames = new ArrayList<JInternalFrame>();
	
	public ArrayList<JInternalFrame> GenerarComandaBarra(int cantidadComandas,JPanel internalFrames) {
		
		
		
		
		for (int i=0;i<cantidadComandas;i++) {
			
			JInternalFrame comanda = new JInternalFrame("Comanda"+i);
			comanda.setBounds(10, 153, 441, 263);
			internalFrames.add(comanda);
			comanda.setClosable(true);
			comanda.getContentPane().setLayout(null);
			arrayInternalFrames.add(comanda);
		}
		
		return arrayInternalFrames;
		
	}

}
