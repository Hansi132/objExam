package no.MCH.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;




public class applicationMenu extends JMenuBar implements ActionListener {
	
	
	
	private JMenu menuFile = null;
	private JMenuItem dBconnectionItem = null;
	private JMenuItem exitItem = null;
	private JMenu help = null;
	private JMenuItem option = null;
	private JMenu actions = null;
	
	protected applicationMenu() {
		displayMenuBar();
	}
	
	protected void displayMenuBar() {
		
		menuFile = new JMenu("File");
		
		dBconnectionItem = new JMenuItem("Test your database connection");
		dBconnectionItem.addActionListener(this);
		menuFile.add(dBconnectionItem);
		
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		menuFile.add(exitItem);
		
		actions = new JMenu("Actions");
		
		help = new JMenu("Help");
		
		option = new JMenuItem ("Info about this application");
		option.addActionListener(this);
		help.add(option);
		
		
		this.add(menuFile);
		this.add(actions);
		this.add(help);
	}
		
}
