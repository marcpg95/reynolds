package funciones;

import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GenerarInternalFrames {
	
	ArrayList<JInternalFrame> arrayInternalFrames = new ArrayList<JInternalFrame>();
	
	public ArrayList<JInternalFrame> GenerarInternalBarra(int cantidadComandas,JInternalFrame barra) {
		
		
		
		
		for (int i=0;i<cantidadComandas;i++) {
			
			JInternalFrame comanda = new JInternalFrame("Comanda"+(i+1));
			comanda.setBounds(20, 220, 390, 270);
			barra.add(comanda);
			
			comanda.setClosable(true);
			comanda.getContentPane().setLayout(null);
			arrayInternalFrames.add(comanda);
		}
		
		return arrayInternalFrames;
		
	}

}
