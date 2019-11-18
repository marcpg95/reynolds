package interfaz;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import conexion.ServidorTCP;
import funciones.GenerarComanda;
import tablaConCheckBox.JCheckBox_Cell;
import tablaConCheckBox.JCheckBox_Rendered;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
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

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	ArrayList<JTable> arrayTablaBarra = new ArrayList<JTable>();
	ArrayList<JInternalFrame> arrayInternalFrames = new ArrayList<JInternalFrame>();
	private JPanel contentPane;
	
	int numeroMesa = 1;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 828, 28);
		taules.getContentPane().add(tabbedPane);
		JTable tableComanda = new JTable();
		tableComanda.setShowVerticalLines(false);

		tableComanda.setBounds(10, 153, 441, 263);
		taules.getContentPane().add(tableComanda);

		tableComanda.setModel(new DefaultTableModel(new Object[][] { { "CocaCola", false }, { "Cerveza", false },
				{ "Bravas", false }, { "Chocos", false }, { "Fanta", false }, { "Entrecot", false },

		}, new String[] { "Producto", "Listo" }));

		JScrollPane scrollPane = new JScrollPane(tableComanda);
		scrollPane.setBounds(45, 83, 276, 314);
		taules.getContentPane().add(scrollPane);
		tableComanda.getColumnModel().getColumn(1).setCellEditor(new JCheckBox_Cell(new JCheckBox()));
		tableComanda.getColumnModel().getColumn(1).setCellRenderer(new JCheckBox_Rendered());
		
		try {
			File archivo = new File("archivos/config.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			NodeList listaConfig = document.getElementsByTagName("mesas");
			Node mesasXML = listaConfig.item(0);
			Element cantidadMesas = (Element) mesasXML;

			int totalMesas = Integer.parseInt(cantidadMesas.getElementsByTagName("num").item(0).getTextContent());
			arrayTablaBarra = new GenerarComanda().GenerarComandaBarra(numeroMesa, barra, cantidadComandas);
			
			
			for (int i = 0; i < totalMesas; i++) {

				JTabbedPane tabbedPaneMesa = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Mesa " + (i + 1), null, tabbedPaneMesa, null);

				JButton btnTaula = new JButton("Mesa " + Integer.toString(i + 1));
				panelMesas.add(btnTaula);

				btnTaula.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String nombreMesa = ((JButton) e.getSource()).getText();
						// System.out.println(nombreMesa);
						numeroMesa = Integer.parseInt(nombreMesa.substring(nombreMesa.length() - 1));
						// System.out.println(numeroMesa);

						System.out.println(numeroMesa - 1);
						for (int i = 0; i < cantidadComandas; i++) {
							if ((numeroMesa - 1) != i) {
								arrayTablaBarra.get(i).setVisible(false);
								
							} else
								arrayTablaBarra.get(i).setVisible(true);
							   
						}

					}
				});

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		menuCocina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
