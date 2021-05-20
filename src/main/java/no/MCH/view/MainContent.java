package no.MCH.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.MCH.Run;

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
			Object[][] data = {
				    {"Kathy", "Smith",
				     "Snowboarding", new Integer(5), new Boolean(false)},
				    {"John", "Doe",
				     "Rowing", new Integer(3), new Boolean(true)},
				    {"Sue", "Black",
				     "Knitting", new Integer(2), new Boolean(false)},
				    {"Jane", "White",
				     "Speed reading", new Integer(20), new Boolean(true)},
				    {"Joe", "Brown",
				     "Pool", new Integer(10), new Boolean(false)}
				};
			String[] columnNames = {"First Name",
                    "Last Name",
                    "Sport",
                    "# of Years",
                    "Vegetarian"};
			Run.getInstance().getTableContent().changeData(data, columnNames);
			DefaultGui.refreshGui(Run.getInstance().getGui());
		});
		this.add(listCustomerButton);
	}

}
