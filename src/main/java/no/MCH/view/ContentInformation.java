package no.MCH.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ContentInformation extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ContentInformation() {
		DefaultGui.componentModel.addComponent(this);
		drawContent();
	}
	
	protected void drawContent() {
		this.add(new JTextArea());
	}

}
