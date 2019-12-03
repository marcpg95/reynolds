package funciones;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Devolucion {
	
	public static void DevolucionDinero(JInternalFrame barra, JLabel lblPrecio, JButton btnCobrar,JTextField fldCambio,JTextField fldPagado) {
		Font clrNegro = new Font("Arial", Font.BOLD, 14);
		JPanel panelDevolver = new JPanel();
		panelDevolver.setBounds(485, 100, 300, 100); // Posicion - dimensiones
		barra.getContentPane().add(panelDevolver);

		JLabel lblPagado = new JLabel("Pagado");
		lblPagado.setBounds(515, 20, 150, 30);
		lblPagado.setFont(clrNegro);
		barra.getContentPane().add(lblPagado);

		
		fldPagado.setEditable(false);
		fldPagado.setBounds(585, 25, 150, 25);
		barra.getContentPane().add(fldPagado);

		JLabel lblCambio = new JLabel("Cambio");
		lblCambio.setBounds(515, 50, 150, 30);
		lblCambio.setFont(clrNegro);
		barra.getContentPane().add(lblCambio);

		
		fldCambio.setEditable(false);
		fldCambio.setBounds(585, 55, 150, 25);
		barra.getContentPane().add(fldCambio);
		
		String tecladoCobrar = "1234567890.";
		for (int j = 0; j < tecladoCobrar.length(); j++) {
			char[] key0 = tecladoCobrar.toCharArray();
			JButton btnNumeros = new JButton(Character.toString(key0[j]));
			String tecla = Character.toString(key0[j]);

			btnNumeros.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(fldPagado.isEnabled()) {
					String datosDinero = fldPagado.getText();
					fldPagado.setText(datosDinero + tecla);
					}
				}
			});
			panelDevolver.add(btnNumeros);
		}
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String borrado = fldPagado.getText();
				String borradoFinal = borrado.substring(0, borrado.length()-1);
				fldPagado.setText(borradoFinal);
				
			}
		});
		panelDevolver.add(btnBorrar);
		
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (fldPagado.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No has introducido ningun valor", "Error", JOptionPane.WARNING_MESSAGE);
					fldPagado.setText("");
				} else {
					DecimalFormat formato1 = new DecimalFormat("#0.00");//Para limitar el numero de decimales de un double o un float.
					String precioMesa = lblPrecio.getText();
					String pagado = fldPagado.getText();
					float precioMesaFloat = Float.parseFloat(precioMesa);
					float pagadoFloat = Float.parseFloat(pagado);
					String total = String.valueOf(formato1.format(pagadoFloat - precioMesaFloat));
					if  (pagadoFloat<precioMesaFloat) {
						JOptionPane.showMessageDialog(null, "La cantidad pagada tiene que ser mayor al precio total", "Error", JOptionPane.WARNING_MESSAGE);
						fldPagado.setText("");
					}
					else {
					fldCambio.setText(total + "€");
					fldPagado.setEnabled(false);
					String ponerEuro = fldPagado.getText() + "€";
					fldPagado.setText(ponerEuro);
					}
					
					
					
					
				}
			}

		});
		
		/*
		btnTaula.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fldPagado.setText("");
				fldCambio.setText("");
				fldPagado.enable();
				
			}
		});
		*/
	}

	
}