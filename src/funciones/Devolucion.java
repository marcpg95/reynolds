package funciones;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaz.Principal;

public class Devolucion {

	public static void DevolucionDinero(JInternalFrame barra, JLabel lblPrecio, JButton btnCobrar) {
		Font clrNegro = new Font("Arial", Font.BOLD, 14);
		JPanel panelDevolver = new JPanel();
		panelDevolver.setBounds(450, 100, 300, 100); // Posicion - dimensiones
		barra.getContentPane().add(panelDevolver);

		JLabel lblPagado = new JLabel("Pagado");
		lblPagado.setBounds(480, 20, 150, 30);
		lblPagado.setFont(clrNegro);
		barra.getContentPane().add(lblPagado);

		JTextField fldPagado = new JTextField();
		fldPagado.setEditable(false);
		fldPagado.setBounds(550, 25, 150, 25);
		barra.getContentPane().add(fldPagado);

		JLabel lblCambio = new JLabel("Cambio");
		lblCambio.setBounds(480, 50, 150, 30);
		lblCambio.setFont(clrNegro);
		barra.getContentPane().add(lblCambio);

		JTextField fldCambio = new JTextField();
		fldCambio.setEditable(false);
		fldCambio.setBounds(550, 55, 150, 25);
		barra.getContentPane().add(fldCambio);

		String tecladoCobrar = "1234567890.";
		for (int j = 0; j < tecladoCobrar.length(); j++) {
			char[] key0 = tecladoCobrar.toCharArray();
			JButton btnNumeros = new JButton(Character.toString(key0[j]));
			String tecla = Character.toString(key0[j]);

			btnNumeros.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String datosDinero = fldPagado.getText();
					fldPagado.setText(datosDinero + tecla);
				}
			});
			panelDevolver.add(btnNumeros);
		}
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fldPagado.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No has introducido ningun valor", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String precioMesa = lblPrecio.getText();
					String pagado = fldPagado.getText();
					float precioMesaFloat = Float.parseFloat(precioMesa);
					float pagadoFloat = Float.parseFloat(pagado);
					String total = String.valueOf(pagadoFloat - precioMesaFloat);
					fldCambio.setText(total + "€");

					String ponerEuro = fldPagado.getText() + "€";
					fldPagado.setText(ponerEuro);
				}
			}

		});
	}
}