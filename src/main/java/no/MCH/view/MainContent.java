package no.MCH.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainContent extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MainContent() {
		mainContentBuilder();
		this.setLayout(new GridLayout(10, 1));
		this.setAlignmentX(LEFT_ALIGNMENT);
		DefaultGui.componentModel.addComponent(this);
	}
	
	protected void mainContentBuilder() {
		addCustomerButton();
		listCustomerButton();
	}

	private void addCustomerButton() {
		JButton addCustomerButton = new JButton("Add customer");
		addCustomerButton.addActionListener(e -> new AddCustomerPanel());
		this.add(addCustomerButton);
	}
	
	private void listCustomerButton() {
		JButton listCustomerButton = new JButton("List customers");
		listCustomerButton.addActionListener(e -> {
			
		});
		this.add(listCustomerButton);
	}

}
