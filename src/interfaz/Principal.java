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
	
	
	public int contadorMesas=0;
	
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

		JMenuItem menuItem = new JMenuItem("Nuevo");
		menu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("Abrir");
		menu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("Guardar");
		menu.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("Guardar como...");
		menu.add(menuItem_3);

		JSeparator separator = new JSeparator();
		menu.add(separator);

		JMenuItem menuItem_4 = new JMenuItem("Salir");
		menu.add(menuItem_4);

		JButton btnPostres = new JButton("Postres");
		menuBar.add(btnPostres);

		JButton btnBebidas = new JButton("Bebidas");
		menuBar.add(btnBebidas);
		
		JButton btnTaules = new JButton("Taules");
		menuBar.add(btnTaules);

		JPanel internalFrames = new JPanel();
		internalFrames.setBounds(0, 22, 900, 687);
		contentPane.add(internalFrames);
		internalFrames.setLayout(null);
		
		JInternalFrame taules = new JInternalFrame("Mesas");
		taules.setClosable(true);
		taules.setBounds(228, 85, 452, 331);
		internalFrames.add(taules);
		taules.getContentPane().setLayout(null);
		
		JButton btnCrearTaules = new JButton("Crear Mesas");
		btnCrearTaules.setBounds(40, 268, 104, 23);
		taules.getContentPane().add(btnCrearTaules);
		
		JButton btnQuitarTaules = new JButton("Quitar Mesas");
		btnQuitarTaules.setBounds(165, 268, 104, 23);
		taules.getContentPane().add(btnQuitarTaules);
		
		JButton btnBorrarTaules = new JButton("Borrar Todas");
		btnBorrarTaules.setBounds(290, 268, 104, 23);
		taules.getContentPane().add(btnBorrarTaules);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 416, 246);
		taules.getContentPane().add(panel);
		panel.setLayout(new GridLayout(9, 8, 5, 10));
		
		
		
		
		
		taules.setVisible(false);

		JInternalFrame bebidas = new JInternalFrame("Bebidas");
		bebidas.setBounds(227, 89, 447, 327);
		internalFrames.add(bebidas);
		bebidas.setNormalBounds(new Rectangle(0, 50, 0, 0));
		bebidas.setResizable(true);
		bebidas.setMaximizable(true);
		bebidas.setBackground(new Color(240, 240, 240));
		bebidas.setClosable(true);
		GroupLayout groupLayout = new GroupLayout(bebidas.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGap(0, 419, Short.MAX_VALUE));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGap(0, 237, Short.MAX_VALUE));
		bebidas.getContentPane().setLayout(groupLayout);
		bebidas.setVisible(false);

		JInternalFrame postres = new JInternalFrame("Postres");
		postres.setBounds(227, 85, 453, 335);
		internalFrames.add(postres);
		postres.setResizable(true);
		postres.setMaximizable(true);
		postres.setClosable(true);
		postres.setVisible(false);
		
		
		table = new JTable();
		//Para que se pueda seleccionar varias filas
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JScrollPane scroll = new JScrollPane(table);
		
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

        table.setModel(TableModel);

        //Se crea el JCheckBox en la columna indicada en getColumn, en este caso, la primera columna

        table.getColumnModel().getColumn( 0 ).setCellEditor( new Celda_CheckBox() );
                
        //para pintar la columna con el CheckBox en la tabla, en este caso, la primera columna

        table.getColumnModel().getColumn( 0 ).setCellRenderer(new Render_CheckBox());
		
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 565, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRecoger))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRecoger)
					.addContainerGap(154, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		
		
		
		
		
		
	

		// Abre el internal frame de bebidas y lo cambia entre visible y no visible
		btnBebidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!bebidas.isVisible()) {
					bebidas.setVisible(true);
					postres.setVisible(false);
					taules.setVisible(false);
					taules.setBounds(228, 85, 452, 331);
					postres.setBounds(227, 85, 453, 335);
				} else {
					bebidas.setVisible(false);
				}

			}
		});
		btnPostres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!postres.isVisible()) {
					postres.setVisible(true);
					bebidas.setVisible(false);
					taules.setVisible(false);
					taules.setBounds(228, 85, 452, 331);
					bebidas.setBounds(227, 89, 447, 327);
				} else {
					postres.setVisible(false);
				}

			}
		});
		btnTaules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!taules.isVisible()) {
					taules.setVisible(true);
					bebidas.setVisible(false);
					postres.setVisible(false);
					bebidas.setBounds(228, 85, 452, 331);
					bebidas.setBounds(227, 89, 447, 327);
				} else {
					taules.setVisible(false);
				}

			}
		});
		
		
		btnCrearTaules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//aLTaules.add(new JButton(Integer.toString(contadorMesas)));
				JButton btnTaulaAuto = new JButton(Integer.toString(contadorMesas+1));
				panel.add(btnTaulaAuto);
				
				
				
				contadorMesas++;
				revalidate();
				repaint();
				
				

				

			}
		});
		
		btnQuitarTaules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					
					if (contadorMesas>0) {
						panel.remove(contadorMesas-1);
						
						contadorMesas--;
						revalidate();
						repaint();
						}
					else {
						
						JOptionPane.showMessageDialog(null, "No puedes borrar mas mesas");
						
						
					}
					
				
				
				
				
				

				

			}
		});
		
		btnBorrarTaules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contadorMesas>0) {
				int n = JOptionPane.showOptionDialog(new JFrame(), "Estas seguro de quieres borrar todas las mesas", 
				        "Borrar todo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				        null, new Object[] {"Yes", "No"}, JOptionPane.YES_OPTION);

				        if (n == JOptionPane.YES_OPTION) {
				            System.out.println("Yes");
				            panel.removeAll();
							
							contadorMesas=0;
							revalidate();
							repaint();
							
				        } else if (n == JOptionPane.NO_OPTION) {
				            System.out.println("No");
				        } else if (n == JOptionPane.CLOSED_OPTION) {
				            System.out.println("No");
				        }
				
					
					
						
						}
					else {
						
						JOptionPane.showMessageDialog(null, "No puedes borrar mas mesas");
						
					}
					
				
				
				
				
				

				

			}
		});

	}
}
