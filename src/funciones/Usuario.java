package funciones;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.mms.mms.clases.Camarero;

import baseDeDatos.ConsultarCamareros;


public class Usuario {
	public static void InputDialog(JMenuItem menuCocina, JMenuItem menuBarra, JButton Servir, JButton Devolver,
			JInternalFrame barra, JMenu mnBarraCocina) {

		String passwordCocinero = "1";
		String passwordCamarero = "2";
		String passwordAdmin = "3";
		boolean correcto = false;

		String[] options = { "Cocinero", "Camarero", "Administrador" };

		String n = (String) JOptionPane.showInputDialog(null, "Escoge el tipo de usuario", null,
				JOptionPane.QUESTION_MESSAGE, null, options, null);

		if (n == "Cocinero") {
			while (!correcto) {
				String panePassword = (String) JOptionPane.showInputDialog("Escribe la contraseña");

				if (panePassword.equalsIgnoreCase(passwordCocinero)) {
					JOptionPane.showMessageDialog(null, "Te has conectado como cocinero");
					menuBarra.setEnabled(false);
					Devolver.setEnabled(false);
					menuCocina.setEnabled(true);
					Servir.setEnabled(true);
					barra.setVisible(false);
					mnBarraCocina.setEnabled(true);
					correcto = true;

				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta vuelve a escribirla");
				}
			}
		} else if (n == "Camarero") {
			while (!correcto) {
				String panePassword = (String) JOptionPane.showInputDialog("Escribe la contraseña");
				if (panePassword.equalsIgnoreCase(passwordCamarero)) {
					JOptionPane.showMessageDialog(null, "Te has conectado como camarero");
					menuCocina.setEnabled(true);
					Servir.setEnabled(false);
					menuBarra.setEnabled(true);
					Devolver.setEnabled(true);
					mnBarraCocina.setEnabled(true);
					correcto = true;
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta vuelve a escribirla");
				}
			}
		} else if (n == "Administrador") {
			while (!correcto) {
				String panePassword = (String) JOptionPane.showInputDialog("Escribe la contraseña");
				if (panePassword.equalsIgnoreCase(passwordAdmin)) {
					JOptionPane.showMessageDialog(null, "Te has conectado como administrador");
					menuBarra.setEnabled(true);
					Devolver.setEnabled(true);
					menuCocina.setEnabled(true);
					Servir.setEnabled(true);
					mnBarraCocina.setEnabled(true);
					correcto = true;
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta vuelve a escribirla");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Usuario no valido");
		}

	}

	public static void LoginSinAyuda(JMenuItem menuCocina, JMenuItem menuBarra, JButton Servir, JButton Devolver,
			JInternalFrame barra, JPanel internalFrames, JMenu mnBarraCocina) {

		ConsultarCamareros cc = new ConsultarCamareros();
		Font negrita = new Font("Arial", Font.BOLD, 14);
		
		mnBarraCocina.setEnabled(false);
		// Se construye el JInternalFrame
		JInternalFrame internalLogin = new JInternalFrame("Login");
		internalLogin.setBounds(250, 150, 400, 250);
		internalFrames.add(internalLogin);
		

		// Se construye el panel que ira dentro del JInternalFrame
		JPanel panelUsuario = new JPanel();
		// panelUsuario.setBounds(68, 5, 252, 246);
		JPanel teclado = new JPanel();
		teclado.setLayout(new GridBagLayout());
		// teclado.setBounds(-5, 20, 400, 300);

		// Se ponen los panels dentro del internal
		internalLogin.add(teclado, BorderLayout.SOUTH);
		internalLogin.add(panelUsuario);

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;

	
		ArrayList<Camarero> camareros = cc.getCamareros();

		panelUsuario.setLayout(new FlowLayout());
		JLabel labelUser = new JLabel("User");
		labelUser.setFont(negrita);
		panelUsuario.add(labelUser);
		JTextField t1 = new JTextField(10);
		panelUsuario.add(t1);

		JButton botonLogin = new JButton("Login");

		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = t1.getText();
				for (int j = 0; j < camareros.size(); j++) {
					if (camareros.get(j).getNombre().equalsIgnoreCase(texto)) {
						internalLogin.dispose();
						Usuario.InputDialog(menuCocina, menuBarra, Servir, Devolver, barra, mnBarraCocina);
						break;
					} else if (j == (camareros.size() - 1) && !camareros.get(j).getNombre().equalsIgnoreCase(texto)) {
						t1.setText(null);
						JOptionPane.showMessageDialog(null, "Usuario incorrecto", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panelUsuario.add(botonLogin);

		// Creamos el string que usaremos para el teclado.
		String row0 = "1234567890qwertyuiopasdfghjklñzxcvbnm,.-";
		int contadorx = 0;
		int contadory = 1;
		for (int i = 0; i < row0.length(); i++) {
			char[] key0 = row0.toCharArray();
			JButton button = new JButton(Character.toString(key0[i]));
			String tecla = Character.toString(key0[i]);

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String datos = t1.getText();
					t1.setText(datos + tecla);

					JButton obj = (JButton) e.getSource();
					String boton = obj.getText();
					if (boton.contentEquals("-")) {
					}
				}
			});
			c.gridx = contadorx;
			c.gridy = contadory;
			c.gridwidth = 1;
			teclado.add(button, c);
			contadorx++;
			if (contadorx == 10) {
				contadory++;
				contadorx = 0;
			}
		}

		// Boton de espacio
		JButton espacio = new JButton(" ");
		espacio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String datos = t1.getText();
				t1.setText(datos + " ");
			}
		});
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 4;
		teclado.add(espacio, c);
		
		//Con  esto quitamos la flecha desplegable superior.
		BasicInternalFrameUI ui = (BasicInternalFrameUI) internalLogin.getUI();
		Container north = (Container)ui.getNorthPane();
		north.remove(0);
		north.validate(); //Usamos validate y repaint debido a que el contenedor ya era visible, por lo tanto tienes que llamarlos para validar el cambio.
		north.repaint();
		
		internalLogin.setClosable(false);// Para que se pueda cerrar.
		internalLogin.setResizable(false); // Para que se pueda redimensionar el internalFrame.
		// internalLogin.getContentPane().setLayout(null); //Para modificar la posicion de los componentes internos
		internalFrames.add(internalLogin);

		// Se visualiza el JInternalFrame
		internalLogin.setVisible(true);

	}
}
