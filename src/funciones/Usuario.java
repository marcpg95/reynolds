package funciones;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.apache.commons.codec.digest.DigestUtils;

import com.mms.mms.clases.Camarero;
import com.mms.mms.clases.Categories;

import baseDeDatos.ConsultarCamareros;
import interfaz.Principal;

public class Usuario {
	static JButton botonCamarero = new JButton();
	static int contadorTeclado = 0;

	public static void InputDialog(JMenuItem menuCocina, JMenuItem menuBarra, JButton Servir, JButton Devolver,
			JInternalFrame barra, JMenu mnBarraCocina, String nombreCamarero, JInternalFrame internalLogin,
			JPanel internalFrames) {

		String password = Principal.camareros.get(nombreCamarero).getPassword(); // COJO LA PASSWORD DEL CAMARERO

		// boolean correcto = false;

		// while (!correcto) {
		Font negrita = new Font("Arial", Font.BOLD, 14);

		internalLogin.setVisible(true);

		JInternalFrame internalPassw = new JInternalFrame("Contraseña");
		internalPassw.setBounds(250, 150, 400, 250);
		internalFrames.add(internalPassw);

		// Se construye el panel que ira dentro del JInternalFrame

		JPanel panelPassw = new JPanel();
		panelPassw.setBounds(68, 5, 252, 246);
		panelPassw.setLayout(new FlowLayout());
		internalPassw.setVisible(true);

		JPanel teclado = new JPanel();
		teclado.setLayout(new GridBagLayout());
		teclado.setBounds(-5, 20, 400, 300);


		// Se ponen los panels dentro del internal
		
		internalPassw.add(teclado, BorderLayout.SOUTH);
		internalPassw.add(panelPassw);

		GridBagConstraints gbConstraints = new GridBagConstraints();
		gbConstraints.weightx = 0;
		gbConstraints.weighty = 0;
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;

		JLabel labelPassword = new JLabel("Contraseña");
		labelPassword.setFont(negrita);
		panelPassw.add(labelPassword);
		JPasswordField t1 = new JPasswordField(10);
		panelPassw.add(t1);

		JButton botonLogin = new JButton("Login");

		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String panePassword = t1.getText();
				panePassword = DigestUtils.sha1Hex(panePassword); // encriptamos la password
				if (panePassword.equals(password)) { // Compruebo si son la misma password
					JOptionPane.showMessageDialog(null, "Bienvenido " + nombreCamarero + "!");
					menuBarra.setEnabled(true);
					Devolver.setEnabled(true);
					menuCocina.setEnabled(true);
					Servir.setEnabled(true);
					barra.setVisible(true);
					mnBarraCocina.setEnabled(true);
					// correcto = true;
					internalLogin.setVisible(false);
					internalPassw.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta vuelve a escribirla");
					t1.setText("");
				}

			}
		});
		panelPassw.add(botonLogin);

		// Creamos el string que usaremos para el teclado.
		String row0 = "1234567890qwertyuiopasdfghjklñzxcvbnm,.-";
		String row1 = "1234567890QWERTYUIOPASDFGHJKLÑZXCVBNM,.-";

		int contadorx = 0;
		int contadory = 1;

		// Teclado minusculas
		for (int i = 0; i < row0.length(); i++) {
			char[] key0 = row0.toCharArray();
			JButton button = new JButton(Character.toString(key0[i]));
			String tecla = Character.toString(key0[i]);

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String datos = t1.getText();
					t1.setText(datos + tecla);
				}
			});
			gbConstraints.gridx = contadorx;
			gbConstraints.gridy = contadory;
			gbConstraints.gridwidth = 1;
			teclado.add(button, gbConstraints);
			contadorx++;
			if (contadorx == 10) {
				contadory++;
				contadorx = 0;
			}
		}

	
		

		// Boton de Borrar

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cadena = t1.getText().substring(0, t1.getText().length() - 1);
				t1.setText(cadena);
			}
		});

		gbConstraints.gridx = 8;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		teclado.add(btnBorrar, gbConstraints);

		
		// Boton de Mayus
		JButton btnMayus = new JButton("Mayus");
		

		gbConstraints.gridx = 0;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		teclado.add(btnMayus, gbConstraints);
		//tecladoMayus.add(btnMayus, gbConstraints);

		// Boton de espacio
		JButton espacio = new JButton(" ");
		espacio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String datos = t1.getText();
				t1.setText(datos + " ");
			}
		});
		gbConstraints.gridx = 3;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 4;
		teclado.add(espacio, gbConstraints);
		
		
		JButton espacioMayus = new JButton(" ");
		espacioMayus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String datos = t1.getText();
				t1.setText(datos + " ");
			}
		});
		gbConstraints.gridx = 3;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 4;
		

		// Con esto quitamos la flecha desplegable superior.
		BasicInternalFrameUI ui = (BasicInternalFrameUI) internalPassw.getUI();
		Container north = (Container) ui.getNorthPane();
		north.remove(0);
		north.validate(); // Usamos validate y repaint debido a que el contenedor ya era visible, por lo
							// tanto tienes que llamarlos para validar el cambio.
		north.repaint();

		internalPassw.setClosable(false);// Para que se pueda cerrar.
		internalPassw.setResizable(false); // Para que se pueda redimensionar el internalFrame.
		// internalLogin.getContentPane().setLayout(null); //Para modificar la posicion
		// de los componentes internos
		internalFrames.add(internalPassw);

		// Se visualiza el JInternalFrame
		internalPassw.setVisible(true);

		// panePassword = "";//(String)
		// JOptionPane.showInputDialog(null,teclado,"Escribe la contraseña");
		/*
		 * 
		 * panePassword = DigestUtils.sha1Hex(panePassword); //ENCRIPTO LA PASSWORD if
		 * (panePassword.equals(password)) { //COMPRUEBO SI SON LA MISMA
		 * JOptionPane.showMessageDialog(null, "Bienvenido " + nombreCamarero + "!");
		 * menuBarra.setEnabled(true); Devolver.setEnabled(true);
		 * menuCocina.setEnabled(true); Servir.setEnabled(true); barra.setVisible(true);
		 * mnBarraCocina.setEnabled(true); correcto = true;
		 * internalLogin.setVisible(false); } else { JOptionPane.showMessageDialog(null,
		 * "Contraseña incorrecta vuelve a escribirla"); }
		 * 
		 * }
		 */
	}

	public static void LoginSinAyuda(JMenuItem menuCocina, JMenuItem menuBarra, JButton Servir, JButton Devolver,
			JInternalFrame barra, JPanel internalFrames, JMenu mnBarraCocina) {

		ConsultarCamareros cc = new ConsultarCamareros();
		Font negrita = new Font("Arial", Font.BOLD, 14);

		mnBarraCocina.setEnabled(false);
		// Se construye el JInternalFrame
		JInternalFrame internalLogin = new JInternalFrame("Login");
		internalLogin.setBounds(250, 150, 400, 450);
		internalFrames.add(internalLogin);

		// Se construye el panel que ira dentro del JInternalFrame

		JPanel panelUsuario = new JPanel();
		panelUsuario.setBounds(68, 5, 252, 246);
		panelUsuario.setLayout(new GridLayout(3, 5, 5, 10));
		internalLogin.setVisible(true);

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

		// JLabel labelUser = new JLabel("User");
		// labelUser.setFont(negrita);
		// panelUsuario.add(labelUser);
		JTextField t1 = new JTextField(10);

		Image imagenParsearIcono;
		Camarero leerCamarero;
		for (Entry<String, Camarero> leer : Principal.camareros.entrySet()) {
			leerCamarero = leer.getValue();
			ImageIcon imagenCamarero = new ImageIcon(leerCamarero.getImagen());
			imagenParsearIcono = imagenCamarero.getImage();
			imagenParsearIcono = imagenParsearIcono.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it
																											// the
																											// smooth
																											// way
			imagenCamarero = new ImageIcon(imagenParsearIcono);
			JButton btnCamarero = new JButton(imagenCamarero);
			panelUsuario.add(btnCamarero);
			final String nombreCamareroLeido = leerCamarero.getNombre();
			btnCamarero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario.InputDialog(menuCocina, menuBarra, Servir, Devolver, barra, mnBarraCocina,
							nombreCamareroLeido, internalLogin, internalFrames);
					internalLogin.setVisible(false);
				}

			});
		}

		// panelUsuario.add(t1);

		/*
		 * JButton botonLogin = new JButton("Login");
		 * 
		 * botonLogin.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { String texto = t1.getText(); for (int j = 0;
		 * j < camareros.size(); j++) { if
		 * (camareros.get(j).getNombre().equalsIgnoreCase(texto)) {
		 * internalLogin.dispose(); Usuario.InputDialog(menuCocina, menuBarra, Servir,
		 * Devolver, barra, mnBarraCocina); break; } else if (j == (camareros.size() -
		 * 1) && !camareros.get(j).getNombre().equalsIgnoreCase(texto)) {
		 * t1.setText(null); JOptionPane.showMessageDialog(null, "Usuario incorrecto",
		 * "Error", JOptionPane.WARNING_MESSAGE); } } } });
		 * panelUsuario.add(botonLogin);
		 */
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

		// Con esto quitamos la flecha desplegable superior.
		BasicInternalFrameUI ui = (BasicInternalFrameUI) internalLogin.getUI();
		Container north = (Container) ui.getNorthPane();
		north.remove(0);
		north.validate(); // Usamos validate y repaint debido a que el contenedor ya era visible, por lo
							// tanto tienes que llamarlos para validar el cambio.
		north.repaint();

		internalLogin.setClosable(false);// Para que se pueda cerrar.
		internalLogin.setResizable(false); // Para que se pueda redimensionar el internalFrame.
		// internalLogin.getContentPane().setLayout(null); //Para modificar la posicion
		// de los componentes internos
		internalFrames.add(internalLogin);

		// Se visualiza el JInternalFrame
		internalLogin.setVisible(true);

	}
}
