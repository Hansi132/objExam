package no.MCH.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import no.MCH.utils.ImageLoader;

public class DeleteOptionPanel {
	
	public Object createPanel(String path, String description, String paneTitle, Object[] panelOptions) {
		JTextField key = new JTextField();
		JPanel panel = new JPanel();
		panel.add(new JLabel("Primary key"));
		panel.add(key);
		
		ImageIcon icon = new ImageLoader().loadImage(path, description);
		
		int result = JOptionPane.showOptionDialog(null, panel, paneTitle, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, panelOptions, panelOptions[0]);
		if (result == JOptionPane.OK_OPTION) {
			return key.getText().equals("") ? null : key.getText();
		}
		return null;
	}

}
