package interfaz;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.mms.mms.clases.Camarero;
import com.mms.mms.clases.Categories;
import com.mms.mms.clases.Productes;
import com.mms.mms.clases.comandas.Comanda;
import com.mms.mms.conexion.TestServer;

import baseDeDatos.ConsultarCamareros;
import baseDeDatos.ConsultarProductos;
import baseDeDatos.RecuperarComandas;
import baseDeDatos.SubirComanda;

import funciones.GenerarComanda;
import funciones.GenerarInternalFrames;
import funciones.Usuario;

import funciones.Devolucion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;


import javax.swing.JInternalFrame;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import javax.swing.JMenuBar;
import javax.swing.JButton;

import javax.swing.ImageIcon;

import java.awt.SystemColor;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Principal extends JFrame {

	public static HashMap<String, Categories> categorias = new ConsultarProductos().getProductos(); // RECOJO LOS
																									// PRODUCTOS DE LA
																									// BASE DE DATOS
	public static HashMap<Integer, Comanda> comandas = new HashMap<Integer, Comanda>(); // LA KEY ES EL NUMERO DE LA
																						// MESA

	public static HashMap<String, Camarero> camareros = new HashMap<String, Camarero>();
	
	private static final long serialVersionUID = 1L;
	// *Crea los array list que se van a utilizar
	ArrayList<JTable> arrayTablaBarra = new ArrayList<JTable>();
	ArrayList<JTable> arrayTablaCocina = new ArrayList<JTable>();
	ArrayList<JInternalFrame> arrayInternalFramesBarra = new ArrayList<JInternalFrame>();
	ArrayList<JInternalFrame> arrayInternalFramesCocina = new ArrayList<JInternalFrame>();
	private JPanel contentPane;

	static GenerarComanda gc;
	// static GenerarInternalFrames gi;
	int numeroMesa = 1;
	int mesaParaBorrar = 0;
	int numeroCategoria;
	String nombreCategoria;

	public static GenerarComanda getGC() {
		return gc;
	}

	/*
	 * public static GenerarInternalFrames getGI() { return gi; }
	 */

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// RECUPERO LAS COMANDAS DE LA BASE DE DATOS
		RecuperarComandas rc = new RecuperarComandas();
		rc.consulta();
		
		//RECUPERO LOS CAMAREROS DE LA BASE DE DATOS
		ArrayList<Camarero> cambrers = new ConsultarCamareros().getCamareros();
		for(int i = 0; i < cambrers.size(); i++) {
			camareros.put(cambrers.get(i).getNombre(), cambrers.get(i));
		}

		// INICIO EL SERVIDOR
		new TestServer();

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 10, 880, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		menuBar.setBounds(0, 0, 900, 21);
		contentPane.add(menuBar);

		JMenu mnBarracocina = new JMenu("Barra/Cocina");
		menuBar.add(mnBarracocina);

		JMenuItem menuCocina = new JMenuItem("Cocina");
		mnBarracocina.add(menuCocina);

		JMenuItem menuBarra = new JMenuItem("Barra");
		mnBarracocina.add(menuBarra);

		JSeparator separator = new JSeparator();
		// mnBarracocina.add(separator);

		JMenuItem cambiarUsuario = new JMenuItem("Login");
		mnBarracocina.add(cambiarUsuario);

		JPanel internalFrames = new JPanel();
		internalFrames.setBounds(0, 22, 900, 687);
		contentPane.add(internalFrames);
		internalFrames.setLayout(null);

		JInternalFrame barra = new JInternalFrame("Barra");
		barra.setBounds(0, 0, 850, 650);
		internalFrames.add(barra);
		barra.setClosable(true);
		barra.getContentPane().setLayout(null);
		barra.setResizable(true);

		JPanel panelMesas = new JPanel();
		panelMesas.setBounds(566, 400, 252, 246);
		barra.getContentPane().add(panelMesas);
		panelMesas.setLayout(new GridLayout(3, 5, 5, 10));

		JInternalFrame taules = new JInternalFrame("Mesas Cocina");
		taules.setBounds(10, 11, 844, 512);
		internalFrames.add(taules);
		taules.setClosable(true);
		taules.getContentPane().setLayout(null);
		File verComandas = new File("Comandas");
		int cantidadComandas = comandas.size();
		JButton btnServir = new JButton("Servir");
		btnServir.setBounds(295, 210, 90, 28);
		taules.getContentPane().add(btnServir);
		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.setBounds(395, 210, 90, 28);
		taules.getContentPane().add(btnDevolver);
		
		BasicInternalFrameUI ui = (BasicInternalFrameUI) taules.getUI();
		Container north = (Container)ui.getNorthPane();
		north.remove(0);
		north.validate(); 
		north.repaint();
		
		
		// desactiva ciertas opciones segun el tipo de usuario que seas
		// Usuario.LoginSinAyuda(menuCocina,
		// menuBarra,btnServir,btnDevolver,barra,internalFrames,mnBarracocina);

		// Action listener para cambiar tipo de usuario una vez dentro
		cambiarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taules.setVisible(false);
				barra.setVisible(false);
				Usuario.LoginSinAyuda(menuCocina, menuBarra, btnServir, btnDevolver, barra, internalFrames,
						mnBarracocina);
				// Usuario.InputDialog(menuCocina, menuBarra,btnServir,btnDevolver,barra);
			}
		});
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 828, 28);
		taules.getContentPane().add(tabbedPane);
		// gi=new GenerarInternalFrames();
		gc = new GenerarComanda();
		// *Rellena los arrays
		arrayTablaCocina = gc.GenerarComandaCocina(numeroMesa, cantidadComandas);

		arrayInternalFramesCocina = new GenerarInternalFrames().GenerarInternalCocina(cantidadComandas, taules,
				arrayTablaCocina);

		ArrayList<String> arrayNumeroMesa = gc.usarNumeroMesa();

		arrayTablaBarra = gc.GenerarComandaBarra(numeroMesa, cantidadComandas);
		arrayInternalFramesBarra = new GenerarInternalFrames().GenerarInternalBarra(cantidadComandas, barra,
				arrayTablaBarra);
		ArrayList<Float> arrayPrecioMesa = gc.usarPrecioMesa();

		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.setBounds(450, 465, 80, 30);
		barra.getContentPane().add(btnCobrar);

		JButton btnRecuperarUltimoCobro = new JButton("Recuperar");
		btnRecuperarUltimoCobro.setBounds(450, 495, 80, 30);
		barra.getContentPane().add(btnRecuperarUltimoCobro);

		JLabel lblTextoTotal = new JLabel("PRECIO CON IVA :");
		lblTextoTotal.setBounds(436, 411, 103, 23);
		barra.getContentPane().add(lblTextoTotal);

		JLabel lblPrecio = new JLabel("");
		lblPrecio.setBounds(471, 431, 80, 23);
		barra.getContentPane().add(lblPrecio);

		try {
			JPanel panelCategorias = new JPanel();
			panelCategorias.setBounds(20, 270, 400, 70);
			barra.getContentPane().add(panelCategorias);
			panelCategorias.setLayout(new GridLayout(1, 5, 5, 10));

			ArrayList<JInternalFrame> productosDeCategorias = new ArrayList<JInternalFrame>();

			String nombreProductos;
			Categories categoriaProducto;
			ImageIcon iconoProductoBtn;
			Image imagenParsearIcono;
			HashMap<Integer, Productes> productosCategory;
			Productes productoEscogido;
			
			for (Entry<String, Categories> leer : categorias.entrySet()) {
				nombreCategoria = leer.getKey();
				JInternalFrame panelProductosCategorias = new JInternalFrame(nombreCategoria);
				panelProductosCategorias.setBounds(20, 15, 400, 250);
				barra.getContentPane().add(panelProductosCategorias);
				panelProductosCategorias.setLayout(new GridLayout(3, 5, 5, 10));
				panelProductosCategorias.setVisible(false);
				panelProductosCategorias.setClosable(true);
				nombreProductos = leer.getKey();
				categoriaProducto = leer.getValue();
				int cantidadProductos = categoriaProducto.getProductos().size();
				
				
				BasicInternalFrameUI ui1 = (BasicInternalFrameUI) panelProductosCategorias.getUI();
				Container north1 = (Container)ui1.getNorthPane();
				north1.remove(0);
				north1.validate(); 
				north1.repaint();

				
				
				productosCategory = categorias.get(nombreCategoria).getProductos();
				for (Entry<Integer, Productes> entry : productosCategory.entrySet()) {
					productoEscogido = entry.getValue();
					iconoProductoBtn = new ImageIcon(productoEscogido.getImage());
					imagenParsearIcono = iconoProductoBtn.getImage();
					imagenParsearIcono = imagenParsearIcono.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					iconoProductoBtn = new ImageIcon(imagenParsearIcono);
					
					JButton btnProducto = new JButton(productoEscogido.getNom(), iconoProductoBtn);

					panelProductosCategorias.add(btnProducto);
					btnProducto.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							JOptionPane.showMessageDialog(null, "funciona");

						}
					});
				}
				productosDeCategorias.add(panelProductosCategorias);

			}
			
			
			
			ImageIcon iconoCategoriaBtn;
			int contador = 0;
			String contadorString;
			ArrayList<String> nombreCategorias = new ArrayList<String>();
			for (Entry<String, Categories> leer : categorias.entrySet()) {	
				contador++;
				contadorString = Integer.toString(contador);
				nombreCategoria = leer.getKey();
				
				//CREO EL BOTON CON UNA IMAGEN
				iconoCategoriaBtn = new ImageIcon(categorias.get(nombreCategoria).getArrayImage());
				JButton btnCategoria = new JButton(contadorString, iconoCategoriaBtn);
				
				panelCategorias.add(btnCategoria);
				btnCategoria.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nombreMesa = ((JButton) e.getSource()).getText();
						numeroCategoria = Integer.parseInt(nombreMesa.substring(nombreMesa.length() - 1));

						for (int i = 0; i < productosDeCategorias.size(); i++) {
							productosDeCategorias.get(i).setVisible(false);
						}

						productosDeCategorias.get(numeroCategoria - 1).setVisible(true);
					}

				});

			}

			for (int i = 0; i < arrayNumeroMesa.size(); i++) {

				JTabbedPane tabbedPaneMesa = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Mesa " + (arrayNumeroMesa.get(i)), null, tabbedPaneMesa, null);
				tabbedPane.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {

						// *Hace visible un frame u otro en la parte de coicna segun la pestaña clicada
						int contadorComanda = 0;
						int contadorServido = 1;
						for (int j = 0; j < arrayInternalFramesCocina.size() / 2; j++) {
							if (tabbedPane.getSelectedIndex() != j) {
								arrayInternalFramesCocina.get(j + contadorComanda).setVisible(false);
								arrayInternalFramesCocina.get(j + contadorServido).setVisible(false);

							} else {
								arrayInternalFramesCocina.get(j + contadorComanda).setVisible(true);
								arrayInternalFramesCocina.get(j + contadorServido).setVisible(true);
							}
							contadorComanda++;
							contadorServido++;

						}

					}
				});

				JButton btnTaula = new JButton("Mesa " + arrayNumeroMesa.get(i));
				panelMesas.add(btnTaula);

				btnTaula.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String nombreMesa = ((JButton) e.getSource()).getText();
						// System.out.println(nombreMesa);
						numeroMesa = Integer.parseInt(nombreMesa.substring(nombreMesa.length() - 1));
						// System.out.println(numeroMesa);

						// *Añade el precio de la mesa en la zona de barra
						if (numeroMesa <= arrayPrecioMesa.size()) {
							String precioMesa = arrayPrecioMesa.get(numeroMesa - 1).toString();
							lblPrecio.setText(precioMesa);
						} else {
							int numeroMesaAux = numeroMesa;
							boolean mesaExiste = false;
							while (!mesaExiste) {
								if (numeroMesaAux > arrayPrecioMesa.size()) {
									numeroMesaAux--;
								} else {
									String precioMesa = arrayPrecioMesa.get(numeroMesaAux - 1).toString();
									lblPrecio.setText(precioMesa);
									mesaExiste = true;
								}
							}
						}

						// Hace visible en barrra la comanda de la mesa que clicas
						for (int i = 0; i < arrayInternalFramesBarra.size(); i++) {
							// System.out.println(arrayNumeroMesa.get(i));
							// System.out.println(numeroMesa);
							if ((numeroMesa) == Integer.parseInt(arrayNumeroMesa.get(i))) {
								arrayInternalFramesBarra.get(i).setVisible(true);
								mesaParaBorrar = Integer.parseInt(arrayNumeroMesa.get(i));
							} else
								arrayInternalFramesBarra.get(i).setVisible(false);

						}

					}
				});

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Devolucion.DevolucionDinero(barra, lblPrecio, btnCobrar);

		JInternalFrame vacio = new JInternalFrame("Mesa Vacia");
		vacio.setBounds(20, 220, 390, 270);
		barra.getContentPane().add(vacio);
		vacio.setClosable(true);
		vacio.getContentPane().setLayout(null);
		ArrayList<String> numeroMesaComprobar = gc.usarNumeroMesa();
		servirMesas(tabbedPane);
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("ENTRAMOS A BORRAR LA MESA " + mesaParaBorrar);

				/*
				 * // SUBO LA FACTURA A LA BASE DE DATOS SubirFactura sf = new
				 * SubirFactura(comandas.get(mesaParaBorrar).getProductosPedidos(),
				 * comandas.get(mesaParaBorrar).getCamarero(), mesaParaBorrar);
				 * sf.enviarFactura();
				 * 
				 * // BORRO LA COMANDA DE LA BASE DE DATOS SubirComanda sc = new
				 * SubirComanda(comandas.get(mesaParaBorrar), mesaParaBorrar);
				 * sc.borrarComanda();
				 * 
				 * // *Borra la comanda de comandas y del internal frame y la pasa a facturas
				 * for (int i = 0; i < numeroMesaComprobar.size(); i++) { if
				 * (numeroMesaComprobar.get(i).equalsIgnoreCase(Integer.toString(mesaParaBorrar)
				 * )) { arrayInternalFramesBarra.get(i).setVisible(false);
				 * arrayInternalFramesBarra.set(i, vacio); tabbedPane.remove(i);
				 * 
				 * } }
				 */

			}

		});
		/*
		 * btnRecuperarUltimoCobro.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) {
		 * 
		 * new Cobrar().RecuperarCobrarBarra(mesaParaBorrar);
		 * //arrayInternalFramesBarra=gi.GenerarInternalBarra(cantidadComandas,barra,
		 * arrayTablaBarra);
		 * arrayInternalFramesBarra.get(numeroMesa-1).setVisible(true);
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * });
		 */
		btnServir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroMesaAux = 0;
				boolean actualizarDatos = false;
				int contadorComanda = 0;
				int contadorServido = 1;
				for (int x = 0; x < arrayInternalFramesCocina.size() / 2; x++) {
					if (tabbedPane.getSelectedIndex() != x) {

					} else {

						DefaultTableModel modeloCocina = (DefaultTableModel) arrayTablaCocina
								.get(tabbedPane.getSelectedIndex() + contadorComanda).getModel();
						DefaultTableModel modeloServir = (DefaultTableModel) arrayTablaCocina
								.get(tabbedPane.getSelectedIndex() + contadorServido).getModel();

						/*
						 * for (int i = modeloServir.getRowCount()-1; i >=0 ; i--) {
						 * 
						 * modeloServir.removeRow(i);
						 * 
						 * 
						 * }
						 */

						for (int i = modeloCocina.getRowCount() - 1; i >= 0; i--) {
							Object fila[] = new Object[modeloCocina.getColumnCount()];
							boolean mover = true;
							boolean borrar = false;
							for (int j = 0; j < modeloCocina.getColumnCount(); j++) {
								mover = (boolean) modeloCocina.getValueAt(i, 2);
								if (mover == false) {
									fila[j] = modeloCocina.getValueAt(i, j);
									String mesaNombre = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
									numeroMesaAux = Integer.parseInt(mesaNombre.replaceAll("[^0-9]", ""));
									for (int p = 0; i < comandas.get(numeroMesaAux).getProductosPedidos().size(); p++) {
										if (comandas.get(numeroMesaAux).getProductosPedidos().get(p).getProducto()
												.getNom().equals(modeloCocina.getValueAt(i, 0))) {
											comandas.get(numeroMesaAux).getProductosPedidos().get(p).setListo(true);
											actualizarDatos = true;
											break;
										}
									}
									borrar = true;
									// System.out.println("hola "+ modeloCocina.getValueAt(i, 2));
								}

							}

							modeloServir.addRow(fila);
							if (borrar == true) {

								modeloCocina.removeRow(i);

							}

						}

						for (int i = modeloServir.getRowCount() - 1; i >= 0; i--) {

							if (modeloServir.getValueAt(i, 0) == null) {
								modeloServir.removeRow(i);
							}

						}
					}
					contadorComanda++;
					contadorServido++;
				}

				if (actualizarDatos == true) {
					SubirComanda sco = new SubirComanda(comandas.get(numeroMesaAux), numeroMesaAux);
					sco.borrarComanda();
					sco.subir();
				}
			}

		});

		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroMesaAux = 0;
				boolean actualizarDatos = false;
				int contadorComanda = 0;
				int contadorServido = 1;
				for (int x = 0; x < arrayInternalFramesCocina.size() / 2; x++) {
					if (tabbedPane.getSelectedIndex() != x) {

					} else {

						DefaultTableModel modeloCocina = (DefaultTableModel) arrayTablaCocina
								.get(tabbedPane.getSelectedIndex() + contadorComanda).getModel();
						DefaultTableModel modeloServir = (DefaultTableModel) arrayTablaCocina
								.get(tabbedPane.getSelectedIndex() + contadorServido).getModel();

						/*
						 * for (int i = modeloServir.getRowCount()-1; i >=0 ; i--) {
						 * 
						 * modeloServir.removeRow(i);
						 * 
						 * 
						 * }
						 */

						
						
						for (int i = modeloServir.getRowCount() - 1; i >= 0; i--) {
							Object fila[] = new Object[modeloServir.getColumnCount()];
							boolean mover = true;
							boolean borrar = false;
							for (int j = 0; j < modeloServir.getColumnCount(); j++) {
								mover = (boolean) modeloServir.getValueAt(i, 2);
								if (mover == false) {
									fila[j] = modeloServir.getValueAt(i, j);
									String mesaNombre = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
									numeroMesaAux = Integer.parseInt(mesaNombre.replaceAll("[^0-9]", ""));
									for (int p = 0; i < comandas.get(numeroMesaAux).getProductosPedidos().size(); p++) {
										if (comandas.get(numeroMesaAux).getProductosPedidos().get(p).getProducto()
												.getNom().equals(modeloServir.getValueAt(i, 0))) {
											comandas.get(numeroMesaAux).getProductosPedidos().get(p).setListo(false);
											actualizarDatos = true;
											break;
										}
									}
									borrar = true;
									// System.out.println("hola "+ modeloCocina.getValueAt(i, 2));
								}

							}

							modeloCocina.addRow(fila);
							if (borrar == true) {

								modeloServir.removeRow(i);

							}

						}

						for (int i = modeloCocina.getRowCount() - 1; i >= 0; i--) {

							if (modeloCocina.getValueAt(i, 0) == null) {
								modeloCocina.removeRow(i);
							}

						}
					}
					contadorComanda++;
					contadorServido++;
				}

				if (actualizarDatos == true) {
					SubirComanda sc = new SubirComanda(comandas.get(numeroMesaAux), numeroMesaAux);
					sc.borrarComanda();
					sc.subir();
				}
			}

		});
		
		BasicInternalFrameUI ui1 = (BasicInternalFrameUI) barra.getUI();
		Container north1 = (Container)ui1.getNorthPane();
		north1.remove(0);
		north1.validate(); 
		north1.repaint();

		menuCocina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// *hace visible el internal frame de cocina
				if (!taules.isVisible()) {
					taules.setVisible(true);
					barra.setVisible(false);

				} else {
					taules.setVisible(false);
				}

			}
		});

		menuBarra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// *hace visible el internal frame de barra
				if (!barra.isVisible()) {
					barra.setVisible(true);
					taules.setVisible(false);

				} else {

					barra.setVisible(false);
				}

			}
		});

	}
	

	public void servirMesas(JTabbedPane tabbedPane) {
		int contadorComanda = 0;
		int contadorServido = 1;
		for (int l = 0; l < arrayInternalFramesCocina.size() / 2; l++) {

			DefaultTableModel modeloCocina = (DefaultTableModel) arrayTablaCocina.get(l + contadorComanda).getModel();
			DefaultTableModel modeloServir = (DefaultTableModel) arrayTablaCocina.get(l + contadorServido).getModel();

			/*
			 * for (int i = modeloServir.getRowCount()-1; i >=0 ; i--) {
			 * 
			 * modeloServir.removeRow(i);
			 * 
			 * 
			 * }
			 */

			for (int i = modeloCocina.getRowCount() - 1; i >= 0; i--) {
				Object fila[] = new Object[modeloCocina.getColumnCount()];
				boolean mover = true;
				boolean borrar = false;
				for (int j = 0; j < modeloCocina.getColumnCount(); j++) {
					mover = (boolean) modeloCocina.getValueAt(i, 2);
					if (mover == false) {
						fila[j] = modeloCocina.getValueAt(i, j);

						borrar = true;
						// System.out.println("hola "+ modeloCocina.getValueAt(i, 2));
					}

				}

				modeloServir.addRow(fila);
				if (borrar == true) {

					modeloCocina.removeRow(i);

				}

			}

			for (int i = modeloServir.getRowCount() - 1; i >= 0; i--) {

				if (modeloServir.getValueAt(i, 0) == null) {
					modeloServir.removeRow(i);
				}

			}
			contadorComanda++;
			contadorServido++;
		}

	}
}
