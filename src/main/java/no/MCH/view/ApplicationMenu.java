package no.MCH.view;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import no.MCH.database.DatabaseConnection;

public class ApplicationMenu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ApplicationMenu.class);
	
	private JMenu menuFile = null;
	private JMenuItem dBconnectionItem = null;
	private JMenuItem exitItem = null;
	private JMenu help = null;
	private JMenuItem option = null;
	private JMenu actions = null;
	private JMenuItem addCustomer = null;
	
	protected ApplicationMenu() {
		displayMenuBar();
	}
	
	protected void displayMenuBar() {
		
		menuFile = new JMenu("File");
		
		dBconnectionItem = new JMenuItem("Test your database connection");
		dBconnectionItem.addActionListener(e -> {
			if (DatabaseConnection.testConnection()) {
				JOptionPane.showMessageDialog(null, "You have connection to the database.", "DatabaseConnection", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error you do not have connection to the database.", "DatabaseConnection", JOptionPane.ERROR_MESSAGE);
			}
		});
		menuFile.add(dBconnectionItem);
		
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> System.exit(0));
		menuFile.add(exitItem);
		
		actions = new JMenu("Actions");
		addCustomer = new JMenuItem("Add customer");
		addCustomer.addActionListener(e -> {
			new AddCustomerPanel();
		});
		actions.add(addCustomer);
		
		help = new JMenu("Help");
		
		option = new JMenuItem ("Info about this application");
		option.addActionListener(e -> JOptionPane.showMessageDialog(null, "Exam application created by Marthin, Christopher and Hans", "Application information", JOptionPane.INFORMATION_MESSAGE));
		help.add(option);
		
		
		this.add(menuFile);
		this.add(actions);
		this.add(help);
	}		
}
