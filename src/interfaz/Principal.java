package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tablaConCheckBox.Celda_CheckBox;
import tablaConCheckBox.Render_CheckBox;

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

import java.awt.Color;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.GridLayout;

public class Principal extends JFrame {
	


	
	private static final long serialVersionUID = 1L;


	private JTable table;
	private JPanel contentPane;
	
	

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

		JMenu menu = new JMenu("Archivo");
		menuBar.add(menu);

		JMenuItem menuCocina = new JMenuItem("Cocina");
		menu.add(menuCocina);

		JMenuItem menuBarra = new JMenuItem("Barra");
		menu.add(menuBarra);

		JSeparator separator = new JSeparator();
		menu.add(separator);

		JMenuItem menuItem_4 = new JMenuItem("Salir");
		menu.add(menuItem_4);

	

		JPanel internalFrames = new JPanel();
		internalFrames.setBounds(0, 22, 900, 687);
		contentPane.add(internalFrames);
		internalFrames.setLayout(null);
		
		JInternalFrame taules = new JInternalFrame("Mesas Cocina");
		taules.setClosable(true);
		taules.setBounds(10, 11, 844, 512);
		internalFrames.add(taules);
		taules.getContentPane().setLayout(null);
		
				
				
				
				table = new JTable();
				//Para que se pueda seleccionar varias filas
				table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				
				JScrollPane scroll = new JScrollPane(table);
				scroll.setBounds(0, 0, 828, 472);
				taules.getContentPane().add(scroll);
				
				        table.setModel(new DefaultTableModel(
				        	new Object[][] {
				        		{null, null, null},
				        		{null, null, null},
				        		{null, null, null},
				        		{null, null, null},
				        		{null, null, null},
				        		{null, null, null},
				        		{null, null, null},
				        		{null, null, null},
				        	},
				        	new String[] {
				        		"New column", "New column", "New column"
				        	}
				        ));
		
		
		
		
		
		
		
		taules.setVisible(false);
		
        //se crea un TableModel con algunos datos y se asigna al JTable

        DefaultTableModel TableModel = new DefaultTableModel();

        TableModel.setDataVector(new Object[][] {

        { false, "Juan Perez", "12", "Hombre" },

        { false, "Homero J. Simpsons", "40", "Hombre" },

        { true, "Ned Flanders", "35", "Hombre" },

        { true, "Asuka Langley", "15", "Si gracias" },

        { false, "Rei Ayanami", "16", "Mujer" },

        { true, "shinji ikari", "15", "No se sabe" } }, new Object[] {

        "CheckBox", "Nombre", "Edad", "Sexo" });
		
		JButton btnRecoger = new JButton("Recojo comanda");
		btnRecoger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("BOTON:::");
				//Pongo el color de fondo que tiene la celda programada
				int cantFilas=table.getModel().getRowCount();
				for(int i=0;i<cantFilas;i++) {
					JCheckBox check = (JCheckBox)((Render_CheckBox)table.getCellRenderer(i, 0)).getComponent();
					boolean bol = (boolean)table.getValueAt(i, 0);
					System.out.println(bol);
					if( bol)  {
						//check.setBackground(Color.green);
						//((Render_CheckBox)table.getCellRenderer(i, 0)).setBackground(Color.green);
						System.out.println("Color es verde");
					}
					else {
						//check.setBackground(Color.red);
						//((Render_CheckBox)table.getCellRenderer(i, 0)).setBackground(Color.red);
						System.out.println("Color es rojo");
					}
					check.setEnabled(false);
					((Render_CheckBox)table.getCellRenderer(i, 0)).saludo();
				}
				
			}
		});
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnRecoger)
					.addContainerGap(779, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(249)
					.addComponent(btnRecoger)
					.addContainerGap(437, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		
		
		
		
		
		
	

		
		menuCocina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!taules.isVisible()) {
					taules.setVisible(true);
					
				} else {
					taules.setVisible(false);
				}

			}
		});
		
		
		

	}
}
