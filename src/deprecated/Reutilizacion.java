/*

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

package deprecated;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Reutilizacion {
//botones
 * 	JButton btnPostres = new JButton("Postres");
		menuBar.add(btnPostres);

		JButton btnBebidas = new JButton("Bebidas");
		menuBar.add(btnBebidas);
		
		JButton btnTaules = new JButton("Taules");
		menuBar.add(btnTaules);
	JButton btnCrearTaules = new JButton("Crear Mesas");
		btnCrearTaules.setBounds(40, 268, 104, 23);
		taules.getContentPane().add(btnCrearTaules);
		
		JButton btnQuitarTaules = new JButton("Quitar Mesas");
		btnQuitarTaules.setBounds(165, 268, 104, 23);
		taules.getContentPane().add(btnQuitarTaules);
		
		JButton btnBorrarTaules = new JButton("Borrar Todas");
		btnBorrarTaules.setBounds(290, 268, 104, 23);
		taules.getContentPane().add(btnBorrarTaules);
//grid autoajustable		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 416, 246);
		taules.getContentPane().add(panel);
		panel.setLayout(new GridLayout(9, 8, 5, 10));
//Añadir mesas	
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
//quitar mesas	
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
	//borrar mesas
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

}
*/
