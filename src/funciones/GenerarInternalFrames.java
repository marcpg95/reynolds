package funciones;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import interfaz.Principal;
import tablaConCheckBox.JCheckBox_Cell;
import tablaConCheckBox.JCheckBox_Rendered;

public class GenerarInternalFrames {
	
	ArrayList<JInternalFrame> arrayInternalFramesBarra = new ArrayList<JInternalFrame>();
	ArrayList<JInternalFrame> arrayInternalFramesCocina = new ArrayList<JInternalFrame>();
	
	String precioTotal="0";
	int auxPrecioTotal=0;
	int precio=0;
	
	public ArrayList<JInternalFrame> GenerarInternalBarra(int cantidadComandas,JInternalFrame barra,ArrayList<JTable> arrayTablaBarra) {
		GenerarComanda gc = Principal.getGC();
		ArrayList<String>arrayNumeroMesa = gc.usarNumeroMesa();
		
		
		for(int i=0;i<arrayNumeroMesa.size();i++) {
			System.out.println(arrayNumeroMesa.get(i)+"hey");
		}
		for (int i=0;i<cantidadComandas;i++) {
			
			JInternalFrame comanda = new JInternalFrame("Comanda"+(arrayNumeroMesa.get(i)));
			comanda.setBounds(20, 220, 390, 270);
			barra.add(comanda);
			
			comanda.setClosable(true);
			comanda.getContentPane().setLayout(null);
			arrayInternalFramesBarra.add(comanda);
			
			arrayInternalFramesBarra.get(i).getContentPane().add(arrayTablaBarra.get(i));
			
			JScrollPane scrollPaneBarra = new JScrollPane(arrayTablaBarra.get(i));
			scrollPaneBarra.setBounds(0,0, 386, 231);
			arrayInternalFramesBarra.get(i).getContentPane().add(scrollPaneBarra);
			arrayTablaBarra.get(i).getColumnModel().getColumn(3)
					.setCellEditor(new JCheckBox_Cell(new JCheckBox()));
			arrayTablaBarra.get(i).getColumnModel().getColumn(3).setCellRenderer(new JCheckBox_Rendered());
			
			
			
		}
		
		return arrayInternalFramesBarra;
		
	}
	
public ArrayList<JInternalFrame> GenerarInternalCocina(int cantidadComandas,JInternalFrame barra,ArrayList<JTable> arrayTablaCocina) {
		
	GenerarComanda gc = Principal.getGC();
	ArrayList<String>arrayNumeroMesa = gc.usarNumeroMesa();
	
		
		
		for (int i=0;i<cantidadComandas;i++) {
			
			
			JInternalFrame comandaCocina = new JInternalFrame("Comanda"+(arrayNumeroMesa.get(i)));
			comandaCocina.setBounds(0, 25, 825, 450);
			barra.add(comandaCocina);
			
			comandaCocina.setClosable(true);
			comandaCocina.getContentPane().setLayout(null);
			JLabel lblCamarero = new JLabel("Camarero :");
			lblCamarero.setBounds(400, 25, 103, 23);
			comandaCocina.getContentPane().add(lblCamarero);
			
			JLabel lblNumeroMesa = new JLabel("Numero Mesa : "+(arrayNumeroMesa.get(i)));
			lblNumeroMesa.setBounds(400, 50, 103, 23);
			comandaCocina.getContentPane().add(lblNumeroMesa);
			
			JLabel lblFecha = new JLabel("Hora entrada pedido :");
			lblFecha.setBounds(400, 75, 160, 23);
			comandaCocina.getContentPane().add(lblFecha);
			
			arrayInternalFramesCocina.add(comandaCocina);
			
			arrayInternalFramesCocina.get(i).getContentPane().add(arrayTablaCocina.get(i));
			
			JScrollPane scrollPaneBarra = new JScrollPane(arrayTablaCocina.get(i));
			scrollPaneBarra.setBounds(0,0, 386, 231);
			arrayInternalFramesCocina.get(i).getContentPane().add(scrollPaneBarra);
			arrayTablaCocina.get(i).getColumnModel().getColumn(2)
					.setCellEditor(new JCheckBox_Cell(new JCheckBox()));
			arrayTablaCocina.get(i).getColumnModel().getColumn(2).setCellRenderer(new JCheckBox_Rendered());
			
		}
		
		return arrayInternalFramesCocina;
		
	}
	
	

}
