import java.awt.Component;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

// Classe qui permet d'afficher les heures d'emprunts dans une ComboBox
public class DateFormattedListCellRenderer extends DefaultListCellRenderer {

        private DateFormat format;
        
        // constructeur
        public DateFormattedListCellRenderer(DateFormat format) {
            this.format = format;
        }

        // red�finition de la m�thode getListCellRendererComponent
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // v�rifie que la valeur pass�  est une instance de la classe date
        	if (value instanceof Date) {
        		// formate la date
                value = format.format((Date) value);
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }

    }