package funciones;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tablaConCheckBox.JCheckBox_Cell;
import tablaConCheckBox.JCheckBox_Rendered;

public class GenerarInternalFrames {
	
	ArrayList<JInternalFrame> arrayInternalFrames = new ArrayList<JInternalFrame>();
	
	public ArrayList<JInternalFrame> GenerarInternalBarra(int cantidadComandas,JInternalFrame barra,ArrayList<JTable> arrayTablaBarra) {
		
		
		
		
		for (int i=0;i<cantidadComandas;i++) {
			
			JInternalFrame comanda = new JInternalFrame("Comanda"+(i+1));
			comanda.setBounds(20, 220, 390, 270);
			barra.add(comanda);
			
			comanda.setClosable(true);
			comanda.getContentPane().setLayout(null);
			arrayInternalFrames.add(comanda);
			
			arrayInternalFrames.get(i).getContentPane().add(arrayTablaBarra.get(i));
			
			JScrollPane scrollPaneBarra = new JScrollPane(arrayTablaBarra.get(i));
			scrollPaneBarra.setBounds(0,0, 386, 231);
			arrayInternalFrames.get(i).getContentPane().add(scrollPaneBarra);
			arrayTablaBarra.get(i).getColumnModel().getColumn(3)
					.setCellEditor(new JCheckBox_Cell(new JCheckBox()));
			arrayTablaBarra.get(i).getColumnModel().getColumn(3).setCellRenderer(new JCheckBox_Rendered());
			
		}
		
		return arrayInternalFrames;
		
	}

}
