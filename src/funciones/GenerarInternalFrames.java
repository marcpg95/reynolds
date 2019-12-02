package funciones;

import java.awt.Container;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import interfaz.Principal;
import tablaConCheckBox.JCheckBox_Cell;
import tablaConCheckBox.JCheckBox_Rendered;

public class GenerarInternalFrames {

	public GenerarInternalFrames() {

	}

	ArrayList<JInternalFrame> arrayInternalFramesBarra = new ArrayList<JInternalFrame>();
	ArrayList<JInternalFrame> arrayInternalFramesCocina = new ArrayList<JInternalFrame>();

	String precioTotal = "0";
	int auxPrecioTotal = 0;
	int precio = 0;

	// *Genera los internal frames de barra
	public ArrayList<JInternalFrame> GenerarInternalBarra(int cantidadComandas, JInternalFrame barra,
			ArrayList<JTable> arrayTablaBarra) {
		GenerarComanda gc = Principal.getGC();
		ArrayList<String> arrayNumeroMesa = gc.usarNumeroMesa();

		// *Crea tantos internal frames como comandas haya y le asigna un JTABLE de su
		// comanda

		for (int i = 0; i < cantidadComandas; i++) {

			JInternalFrame comanda = new JInternalFrame("Comanda" + (arrayNumeroMesa.get(i)));
			comanda.setBounds(20, 350, 390, 270);
			barra.add(comanda);

			comanda.setClosable(true);
			comanda.getContentPane().setLayout(null);
			arrayInternalFramesBarra.add(comanda);

			arrayInternalFramesBarra.get(i).getContentPane().add(arrayTablaBarra.get(i));

			JScrollPane scrollPaneBarra = new JScrollPane(arrayTablaBarra.get(i));
			scrollPaneBarra.setBounds(0, 0, 386, 231);
			arrayInternalFramesBarra.get(i).getContentPane().add(scrollPaneBarra);
			arrayTablaBarra.get(i).getColumnModel().getColumn(3).setCellEditor(new JCheckBox_Cell(new JCheckBox()));
			arrayTablaBarra.get(i).getColumnModel().getColumn(3).setCellRenderer(new JCheckBox_Rendered());

			BasicInternalFrameUI ui = (BasicInternalFrameUI) comanda.getUI();
			Container north = (Container)ui.getNorthPane();
			north.remove(0);
			north.validate(); 
			north.repaint();
			
		}

		return arrayInternalFramesBarra;

	}

	// *Genera los internal frames de cocina
	public ArrayList<JInternalFrame> GenerarInternalCocina(int cantidadComandas, JInternalFrame barra,
			ArrayList<JTable> arrayTablaCocina) {
       
		GenerarComanda gc = Principal.getGC();
		ArrayList<String> arrayNumeroMesa = gc.usarNumeroMesa();
		
		// *Crea tantos internal frames como comandas haya y le asigna un JTABLE de su
		// comanda
int contadorComanda=0;
int contadorServido=1;
		for (int i = 0; i < cantidadComandas; i++) {

			JInternalFrame comandaCocina = new JInternalFrame("Comanda" + (arrayNumeroMesa.get(i)));
			comandaCocina.setBounds(0, 25, 825, 175);
			barra.add(comandaCocina);

			comandaCocina.setClosable(true);
			comandaCocina.getContentPane().setLayout(null);
			
			JInternalFrame servirCocina = new JInternalFrame("Servido Mesa: " + (arrayNumeroMesa.get(i)));
			servirCocina.setBounds(0, 250, 825, 225);
			barra.add(servirCocina);

			servirCocina.setClosable(true);
			servirCocina.getContentPane().setLayout(null);
			
			int numero = Integer.parseInt(arrayNumeroMesa.get(i));
			JLabel lblCamarero = new JLabel("Camarero : " + Principal.comandas.get(numero).getCamarero());
			lblCamarero.setBounds(400, 25, 103, 23);
			comandaCocina.getContentPane().add(lblCamarero);

			JLabel lblNumeroMesa = new JLabel("Numero Mesa : " + (arrayNumeroMesa.get(i)));
			lblNumeroMesa.setBounds(400, 50, 103, 23);
			comandaCocina.getContentPane().add(lblNumeroMesa);

			java.util.Date d = new java.util.Date();  
			Timestamp date = new Timestamp(d.getTime());
			JLabel lblFecha = new JLabel("Hora entrada pedido : " + date);
			lblFecha.setBounds(400, 75, 300, 23);
			comandaCocina.getContentPane().add(lblFecha);

			arrayInternalFramesCocina.add(comandaCocina);
			arrayInternalFramesCocina.add(servirCocina);
			
			arrayInternalFramesCocina.get(i+contadorComanda).getContentPane().add(arrayTablaCocina.get(i+contadorComanda));
			JScrollPane scrollPaneBarra = new JScrollPane(arrayTablaCocina.get(i+contadorComanda));
			scrollPaneBarra.setBounds(0, 0, 386, 231);
			arrayInternalFramesCocina.get(i+contadorComanda).getContentPane().add(scrollPaneBarra);
			arrayTablaCocina.get(i+contadorComanda).getColumnModel().getColumn(2).setCellEditor(new JCheckBox_Cell(new JCheckBox()));
			arrayTablaCocina.get(i+contadorComanda).getColumnModel().getColumn(2).setCellRenderer(new JCheckBox_Rendered());
           
			
			arrayInternalFramesCocina.get(i+contadorServido).getContentPane().add(arrayTablaCocina.get(i+contadorServido));
			JScrollPane scrollPaneBarra2 = new JScrollPane(arrayTablaCocina.get(i+contadorServido));
			scrollPaneBarra2.setBounds(0, 0, 386, 231);
			arrayInternalFramesCocina.get(i+contadorServido).getContentPane().add(scrollPaneBarra2);
			arrayTablaCocina.get(i+contadorServido).getColumnModel().getColumn(2).setCellEditor(new JCheckBox_Cell(new JCheckBox()));
			arrayTablaCocina.get(i+contadorServido).getColumnModel().getColumn(2).setCellRenderer(new JCheckBox_Rendered());
			
			contadorComanda++;
			contadorServido++;
			
			BasicInternalFrameUI ui = (BasicInternalFrameUI) servirCocina.getUI();
			Container north = (Container)ui.getNorthPane();
			north.remove(0);
			north.validate(); 
			north.repaint();
			
			BasicInternalFrameUI ui1 = (BasicInternalFrameUI) comandaCocina.getUI();
			Container north1 = (Container)ui1.getNorthPane();
			north1.remove(0);
			north1.validate(); 
			north1.repaint();
			
		}
		arrayInternalFramesCocina.get(0).setVisible(true);
		arrayInternalFramesCocina.get(1).setVisible(true);
		
		

		return arrayInternalFramesCocina;

	}

}
