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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	static Font negrita = new Font("Arial", Font.BOLD, 14);
	static int txtFieldSelector = 0;

	public static void InputDialog(JMenuItem menuCocina, JMenuItem menuBarra, JButton Servir, JButton Devolver,
			JInternalFrame barra, JMenu mnBarraCocina, String nombreCamarero, JInternalFrame internalLogin,
			JPanel internalFrames) {

		String password = Principal.camareros.get(nombreCamarero).getPassword(); // COJO LA PASSWORD DEL CAMARERO

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

		JPanel tecladoMayus = new JPanel();
		tecladoMayus.setLayout(new GridBagLayout());
		tecladoMayus.setBounds(-5, 70, 400, 150);
		tecladoMayus.setVisible(false);

		// Se ponen los panels dentro del internal

		internalPassw.add(tecladoMayus, BorderLayout.SOUTH);
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
				if (t1.getText().length() == 0) {

				} else {
					String cadena = t1.getText().substring(0, t1.getText().length() - 1);
					t1.setText(cadena);
				}
			}
		});

		gbConstraints.gridx = 8;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		teclado.add(btnBorrar, gbConstraints);
		// Boton de Mayus
		JButton btnMayus = new JButton("Mayus");
		btnMayus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teclado.setVisible(false);
				tecladoMayus.setVisible(true);
			}
		});
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		teclado.add(btnMayus, gbConstraints);

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

		int contadorxMayus = 0;
		int contadoryMayus = 1;
		// Teclado mayusculas
		for (int i = 0; i < row1.length(); i++) {
			char[] key0 = row1.toCharArray();
			JButton button = new JButton(Character.toString(key0[i]));
			String tecla = Character.toString(key0[i]);

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String datos = t1.getText();
					t1.setText(datos + tecla);
				}
			});
			gbConstraints.gridx = contadorxMayus;
			gbConstraints.gridy = contadoryMayus;
			gbConstraints.gridwidth = 1;
			tecladoMayus.add(button, gbConstraints);
			contadorxMayus++;
			if (contadorxMayus == 10) {
				contadoryMayus++;
				contadorxMayus = 0;
			}
		}
		// Boton de Borrar
		JButton btnBorrarMayus = new JButton("Borrar");
		btnBorrarMayus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t1.getText().length() == 0) {

				} else {
					String cadena = t1.getText().substring(0, t1.getText().length() - 1);
					t1.setText(cadena);
				}
			}
		});

		gbConstraints.gridx = 8;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		tecladoMayus.add(btnBorrarMayus, gbConstraints);
		// Boton de Mayus
		JButton btnMinus = new JButton("LCase");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tecladoMayus.setVisible(false);
				teclado.setVisible(true);

			}
		});
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		tecladoMayus.add(btnMinus, gbConstraints);

		// Boton de espacio
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
		tecladoMayus.add(espacioMayus, gbConstraints);

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

	}

	public static void LoginComplejo(JMenuItem menuCocina, JMenuItem menuBarra, JButton Servir, JButton Devolver,
			JInternalFrame barra, JPanel internalFrames, JMenu mnBarraCocina) {

		ConsultarCamareros cc = new ConsultarCamareros();

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

		// Se ponen los panels dentro del internal
		internalLogin.add(panelUsuario);

		ArrayList<Camarero> camareros = cc.getCamareros();

		Image imagenParsearIcono;
		Camarero leerCamarero;
		for (Entry<String, Camarero> leer : Principal.camareros.entrySet()) {
			leerCamarero = leer.getValue();
			ImageIcon imagenCamarero = new ImageIcon(leerCamarero.getImagen());
			imagenParsearIcono = imagenCamarero.getImage();
			imagenParsearIcono = imagenParsearIcono.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it
																											// the
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

	public static void LoginSimple(JMenuItem menuCocina, JMenuItem menuBarra, JButton Servir, JButton Devolver,
			JInternalFrame barra, JPanel internalFrames, JMenu mnBarraCocina) {

		mnBarraCocina.setEnabled(false);
		// String password = Principal.camareros.get(nombreCamarero).getPassword(); //

		JInternalFrame internalPassw = new JInternalFrame("Login");
		internalPassw.setBounds(250, 200, 400, 300);
		internalFrames.add(internalPassw);

		// Se construye el panel que ira dentro del JInternalFrame

		JPanel panelPassw = new JPanel();
		panelPassw.setBounds(71, 35, 220, 30);
		panelPassw.setLayout(new FlowLayout());

		JPanel panelUsuario = new JPanel();
		panelUsuario.setBounds(68, 5, 200, 30);
		panelUsuario.setLayout(new FlowLayout());
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBounds(300, 20, 60, 50);
		
		JPanel teclado = new JPanel();
		teclado.setLayout(new GridBagLayout());
		teclado.setBounds(-5, 70, 400, 150);

		JPanel tecladoMayus = new JPanel();
		tecladoMayus.setLayout(new GridBagLayout());
		tecladoMayus.setBounds(-5, 120, 400, 150);
		tecladoMayus.setVisible(false);

		// Se ponen los panels dentro del internal
		
		internalPassw.add(panelLogin);
		internalPassw.add(tecladoMayus, BorderLayout.SOUTH);
		internalPassw.add(teclado, BorderLayout.SOUTH);
		internalPassw.add(panelPassw);
		internalPassw.add(panelUsuario);
		
		

		GridBagConstraints gbConstraints = new GridBagConstraints();
		gbConstraints.weightx = 0;
		gbConstraints.weighty = 0;
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;

		JLabel labelPassword = new JLabel("Contraseña");
		labelPassword.setFont(negrita);
		panelPassw.add(labelPassword);

		JPasswordField t1 = new JPasswordField(10);
		t1.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
			}
			public void focusGained(FocusEvent e) {
				txtFieldSelector = 1;
			}
		});
		panelPassw.add(t1);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(negrita);
		panelUsuario.add(lblUser);

		JTextField txtfUsuario = new JTextField(10);
		txtfUsuario.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
			}
			public void focusGained(FocusEvent e) {
				txtFieldSelector = 0;
			}
		});
		panelUsuario.add(txtfUsuario);

		JButton botonLogin = new JButton("Login");
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameCamarero = txtfUsuario.getText();
				if (Principal.camareros.containsKey(nameCamarero)) {
					String panePassword = t1.getText();
					panePassword = DigestUtils.sha1Hex(panePassword);
					if (panePassword.contentEquals(Principal.camareros.get(nameCamarero).getPassword())) {
						JOptionPane.showMessageDialog(null, "Bienvenido " + nameCamarero + "!");
						menuBarra.setEnabled(true);
						Devolver.setEnabled(true);
						menuCocina.setEnabled(true);
						Servir.setEnabled(true);
						barra.setVisible(true);
						mnBarraCocina.setEnabled(true);
						// internalLogin.setVisible(false);
						internalPassw.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta vuelve a escribirla");
						t1.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usuario incorrecto");
					txtfUsuario.setText("");
					t1.setText("");
				}
			}
		});
		panelLogin.add(botonLogin);
		
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
					if (txtFieldSelector == 1) {
						String datos = t1.getText();
						t1.setText(datos + tecla);
					}
					if (txtFieldSelector == 0) {
						String datos = txtfUsuario.getText();
						txtfUsuario.setText(datos + tecla);
					}
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
				if (txtFieldSelector == 1) { // Password
					if (t1.getText().length() == 0) {

					} else {
						String cadena = t1.getText().substring(0, t1.getText().length() - 1);
						t1.setText(cadena);
					}
				}
				if (txtFieldSelector == 0) { // Usuario
					if (txtfUsuario.getText().length() == 0) {

					} else {
						String cadena = txtfUsuario.getText().substring(0, txtfUsuario.getText().length() - 1);
						txtfUsuario.setText(cadena);
					}
				}
			}
		});

		gbConstraints.gridx = 8;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		teclado.add(btnBorrar, gbConstraints);
		
		// Boton de Mayus
		JButton btnMayus = new JButton("Mayus");
		btnMayus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teclado.setVisible(false);
				tecladoMayus.setVisible(true);
			}
		});
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		teclado.add(btnMayus, gbConstraints);

		
		// Boton de espacio
		JButton espacio = new JButton(" ");
		espacio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtFieldSelector == 1) {
					String datos = t1.getText();
					t1.setText(datos + " ");
				}
				if (txtFieldSelector == 0) {
					String datos = txtfUsuario.getText();
					txtfUsuario.setText(datos + " ");
				}
			}
		});
		gbConstraints.gridx = 3;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 4;
		teclado.add(espacio, gbConstraints);

		int contadorxMayus = 0;
		int contadoryMayus = 1;
		
		// Teclado mayusculas
		for (int i = 0; i < row1.length(); i++) {
			char[] key0 = row1.toCharArray();
			JButton button = new JButton(Character.toString(key0[i]));
			String tecla = Character.toString(key0[i]);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (txtFieldSelector == 1) {
						String datos = t1.getText();
						t1.setText(datos + tecla);
					}
					if (txtFieldSelector == 0) {
						String datos = txtfUsuario.getText();
						txtfUsuario.setText(datos + tecla);
					}
				}
			});
			gbConstraints.gridx = contadorxMayus;
			gbConstraints.gridy = contadoryMayus;
			gbConstraints.gridwidth = 1;
			tecladoMayus.add(button, gbConstraints);
			contadorxMayus++;
			if (contadorxMayus == 10) {
				contadoryMayus++;
				contadorxMayus = 0;
			}
		}
		// Boton de Borrar Mayus
		JButton btnBorrarMayus = new JButton("Borrar");
		btnBorrarMayus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFieldSelector == 1) { // Password
					if (t1.getText().length() == 0) {
					} else {
						String cadena = t1.getText().substring(0, t1.getText().length() - 1);
						t1.setText(cadena);
					}
				}
				if (txtFieldSelector == 0) { // Usuario
					if (txtfUsuario.getText().length() == 0) {

					} else {
						String cadena = txtfUsuario.getText().substring(0, txtfUsuario.getText().length() - 1);
						txtfUsuario.setText(cadena);
					}
				}

			}
		});

		gbConstraints.gridx = 8;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		tecladoMayus.add(btnBorrarMayus, gbConstraints);
		// Boton de Mayus
		JButton btnMinus = new JButton("LCase");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tecladoMayus.setVisible(false);
				teclado.setVisible(true);

			}
		});
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		tecladoMayus.add(btnMinus, gbConstraints);

		// Boton de espacio
		JButton espacioMayus = new JButton(" ");
		espacioMayus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtFieldSelector == 1) {
					String datos = t1.getText();
					t1.setText(datos + " ");
				}
				if (txtFieldSelector == 0) {
					String datos = txtfUsuario.getText();
					txtfUsuario.setText(datos + " ");
				}
			}
		});
		gbConstraints.gridx = 3;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 4;
		tecladoMayus.add(espacioMayus, gbConstraints);

		// Con esto quitamos la flecha desplegable superior.
		BasicInternalFrameUI ui = (BasicInternalFrameUI) internalPassw.getUI();
		Container north = (Container) ui.getNorthPane();
		north.remove(0);
		north.validate(); 
		north.repaint();

		internalPassw.setClosable(false);
		internalPassw.setResizable(false); 
		internalFrames.add(internalPassw);

		// Se visualiza el JInternalFrame
		internalPassw.setVisible(true);

	}
}