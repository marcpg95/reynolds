package tablaConCheckBox;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class Render_CheckBox extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
private JComponent component = new JCheckBox();
//private Color colorFondo;

/*public Color getColorFondo() {
	return colorFondo;
}


public void setColorFondo(Color colorFondo) {
	this.colorFondo = colorFondo;
}*/


/** Constructor de clase */

public Render_CheckBox() {
    setOpaque(true);
    //colorFondo= new Color(200,0,0);
}


public JComponent getComponent() {
	return component;
}


public void setComponent(JComponent component) {
	this.component = component;
}


public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
  
  //Color de fondo de la celda
  
  //( (JCheckBox) component).setBackground( new Color(0,200,0) );
  //obtiene valor boolean y coloca valor en el JCheckBox
  boolean b = ((Boolean) value).booleanValue();
  JCheckBox check=( (JCheckBox) component);
  check.setSelected( b );
  if( b ) {
	  ( (JCheckBox) component).setBackground(Color.yellow);
	  check.setEnabled(false);
  }
  else {
	  ( (JCheckBox) component).setBackground(Color.red);
  }

  return ( (JCheckBox) component);

}

public void saludo() {System.out.println("holita"); }

}
