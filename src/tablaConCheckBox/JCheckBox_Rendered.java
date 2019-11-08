package tablaConCheckBox;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JCheckBox_Rendered extends JCheckBox implements TableCellRenderer{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public JCheckBox_Rendered() {

}

public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
int row, int column) {
setSelected(!(value != null && ((Boolean) value).booleanValue()));
return this;
}
}
