package no.MCH.view;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainContent extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MainContent() {
		mainContentBuilder();
		DefaultGui.componentModel.addComponent(this);
	}
	
	protected void mainContentBuilder() {
		JButton addCustomerButton = new JButton("Add customer");
		addCustomerButton.addActionListener(e -> new AddCustomerPanel());
		this.add(addCustomerButton);
	}

}
