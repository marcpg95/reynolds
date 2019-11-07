package interfaz;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField numeroTaules;
	private ArrayList<JButton> aLTaules = new ArrayList<JButton>();

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
				JButton btnTaulaAuto = new JButton(Integer.toString(contadorMesas));
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
						panel.removeAll();
						
						contadorMesas=0;
						revalidate();
						repaint();
						}
					else {
						
						JOptionPane.showMessageDialog(null, "No puedes borrar mas mesas");
						
					}
					
				
				
				
				
				

				

			}
		});

	}
}
