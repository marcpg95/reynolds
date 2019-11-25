package funciones;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Usuario {

	public static void InputDialog(JMenuItem menuCocina,JMenuItem menuBarra,JButton Servir,JButton Devolver) {

			String passwordCocinero="1";
			String passwordCamarero="2";
			String passwordAdmin="3";
			boolean correcto=false;

	        String[] options = {"Cocinero", "Camarero", "Administrador"};
	       
	        String n = (String)JOptionPane.showInputDialog(null, "Escoge el tipo de usuario",null, JOptionPane.QUESTION_MESSAGE,null, options, null);
	      

	        if(n=="Cocinero"){
	        	while(!correcto) {
	        	String panePassword=(String)JOptionPane.showInputDialog("Escribe la contraseña");
	        	
	        	if(panePassword.equalsIgnoreCase(passwordCocinero)) {
	        		JOptionPane.showMessageDialog(null, "Te has conectado como cocinero");
	        		menuBarra.setEnabled(false);
		        	Devolver.setEnabled(false);
		        	menuCocina.setEnabled(true);
		        	Servir.setEnabled(true);
		        	correcto=true;
		        	System.out.println("entro");
	        	}
	        	else {
	        		JOptionPane.showMessageDialog(null, "Contraseña incorrecta vuelve a escribirla");
	        	}
	        	System.out.println(panePassword);
	        	}
	        }
	        else if(n=="Camarero") {
	        	while(!correcto) {
	        	String panePassword=(String)JOptionPane.showInputDialog("Escribe la contraseña");
	        	if(panePassword.equalsIgnoreCase(passwordCamarero)) {
	        		JOptionPane.showMessageDialog(null, "Te has conectado como camarero");
	        		menuCocina.setEnabled(true);
		        	Servir.setEnabled(false);
		        	menuBarra.setEnabled(true);
		        	Devolver.setEnabled(true);
		        	correcto=true;
	        	}
	        	else {
	        		JOptionPane.showMessageDialog(null, "Contraseña incorrecta vuelve a escribirla");
	        	}
	        	}
	        }
	        else if (n=="Administrador"){
	        	while(!correcto) {
	        	String panePassword=(String)JOptionPane.showInputDialog("Escribe la contraseña");
	        	if(panePassword.equalsIgnoreCase(passwordAdmin)) {
	        		JOptionPane.showMessageDialog(null, "Te has conectado como administrador");
	        		menuBarra.setEnabled(true);
		        	Devolver.setEnabled(true);
		        	menuCocina.setEnabled(true);
		        	Servir.setEnabled(true);
		        	correcto=true;
	        	}
	        	else {
	        		JOptionPane.showMessageDialog(null, "Contraseña incorrecta vuelve a escribirla");
	        	}
	        	}
	        	
	        	
	        }
	        else {
	        	JOptionPane.showMessageDialog(null, "Usuario no valido");
	        }
	        
			
	        
	       

}

}
