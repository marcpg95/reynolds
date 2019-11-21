package interfaz;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mms.mms.clases.Categories;
import com.mms.mms.clases.Productes;
import com.mms.mms.conexion.TestServer;

import baseDeDatos.ConsultarCamareros;
import baseDeDatos.ConsultarProductos;
import baseDeDatos.SubirFactura;
import clases.ProductosFactura;
import funciones.GenerarComanda;
import funciones.GenerarInternalFrames;
import tablaConCheckBox.JCheckBox_Cell;
import tablaConCheckBox.JCheckBox_Rendered;
import funciones.Cobrar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.swing.JInternalFrame;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Principal extends JFrame {
	
	public static HashMap<Integer, ArrayList<ProductosFactura>> facturar = new HashMap<Integer, ArrayList<ProductosFactura>>();
	public static HashMap<String, Categories> categorias = new ConsultarProductos().getProductos(); //RECOJO LOS PRODUCTOS DE LA BASE DE DATOS
	
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
		TestServer testServer = new TestServer();

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
		setBounds(250, 10, 916, 747);
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
		mnBarracocina.add(separator);

		JMenuItem menuItem_4 = new JMenuItem("Salir");
		mnBarracocina.add(menuItem_4);

		JPanel internalFrames = new JPanel();
		internalFrames.setBounds(0, 22, 900, 687);
		contentPane.add(internalFrames);
		internalFrames.setLayout(null);

		JInternalFrame barra = new JInternalFrame("Barra");
		barra.setBounds(0, 0, 844, 512);
		internalFrames.add(barra);
		barra.setClosable(true);
		barra.getContentPane().setLayout(null);

		JPanel panelMesas = new JPanel();
		panelMesas.setBounds(566, 226, 252, 246);
		barra.getContentPane().add(panelMesas);
		panelMesas.setLayout(new GridLayout(3, 5, 5, 10));

		JInternalFrame taules = new JInternalFrame("Mesas Cocina");
		taules.setBounds(10, 11, 844, 512);
		internalFrames.add(taules);
		taules.setClosable(true);
		taules.getContentPane().setLayout(null);
		File verComandas = new File("Comandas");
		String[] comandas = verComandas.list();
		int cantidadComandas = comandas.length;
		JButton btnServir = new JButton("Servir");
		btnServir.setBounds(362, 210, 90, 28);
		taules.getContentPane().add(btnServir);

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
		btnCobrar.setBounds(450, 315, 80, 30);
		barra.getContentPane().add(btnCobrar);

		JButton btnRecuperarUltimoCobro = new JButton("Recuperar");
		btnRecuperarUltimoCobro.setBounds(450, 345, 80, 30);
		barra.getContentPane().add(btnRecuperarUltimoCobro);

		JLabel lblTextoTotal = new JLabel("PRECIO CON IVA :");
		lblTextoTotal.setBounds(436, 261, 103, 23);
		barra.getContentPane().add(lblTextoTotal);

		JLabel lblPrecio = new JLabel("");
		lblPrecio.setBounds(471, 281, 80, 23);
		barra.getContentPane().add(lblPrecio);

		try {

			// *Lee el archivo config.xml para sacar la cantidad de mesas y en un futuro
			// algunas configuraciones
			File archivo = new File("archivos/config.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			NodeList listaConfig = document.getElementsByTagName("mesas");
			Node mesasXML = listaConfig.item(0);
			Element cantidadMesas = (Element) mesasXML;

			int totalMesas = Integer.parseInt(cantidadMesas.getElementsByTagName("num").item(0).getTextContent());

			// *Mira la cantidad de mesas que hay y genera botones(barra) y pestañas(cocina)
			// para las mesas

			for (int i = 0; i < arrayNumeroMesa.size(); i++) {

				JTabbedPane tabbedPaneMesa = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Mesa " + (arrayNumeroMesa.get(i)), null, tabbedPaneMesa, null);
				tabbedPane.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {

						// *Hace visible un frame u otro en la parte de coicna segun la pestaña clicada
						int contadorComanda=0;
						int contadorServido=1;
						for (int j = 0; j < arrayInternalFramesCocina.size()/2; j++) {
							if (tabbedPane.getSelectedIndex() != j) {
								arrayInternalFramesCocina.get(j+contadorComanda).setVisible(false);
								arrayInternalFramesCocina.get(j+contadorServido).setVisible(false);

							} else {
								arrayInternalFramesCocina.get(j+contadorComanda).setVisible(true);
								arrayInternalFramesCocina.get(j+contadorServido).setVisible(true);
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
		JInternalFrame vacio= new JInternalFrame("Mesa Vacia");
		vacio.setBounds(20,220,390,270);
		barra.getContentPane().add(vacio);
		vacio.setClosable(true);
		vacio.getContentPane().setLayout(null);
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubirFactura sf = new SubirFactura(facturar.get(mesaParaBorrar - 1), "Marc", mesaParaBorrar);
				sf.enviarFactura();
				// *Borra la comanda de comandas y del internal frame y la pasa a facturas
				new Cobrar().CobrarBarra(mesaParaBorrar);
				arrayInternalFramesBarra.get(numeroMesa - 1).setVisible(false);
				arrayInternalFramesBarra.set(numeroMesa - 1,vacio);
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
}
