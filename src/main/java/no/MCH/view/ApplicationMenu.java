package no.MCH.view;



import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import no.MCH.Run;
import no.MCH.controller.CustomerController;
import no.MCH.controller.FileImportController;
import no.MCH.database.DatabaseConnection;
import no.MCH.exception.CustomerNotFoundException;
import no.MCH.exception.TypeNotFoundException;
import no.MCH.model.CustomerModel;
import no.MCH.model.DataTableModel;

public class ApplicationMenu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ApplicationMenu.class);
	
	
	
	private JMenu menuFile = null;
	private JMenuItem dBconnectionItem = null;
	private JMenuItem exitItem = null;
	private JMenuItem openFile = null;
	private JMenu help = null;
	private JMenuItem option = null;
	private JMenu actions = null;
	private JMenuItem addCustomer = null;
	private JMenuItem listCustomer = null;
	
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
		
		openFile = new JMenuItem("Open File");
		openFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(Run.getInstance().getGui());
            File file = fileChooser.getSelectedFile();
            JPanel jPanel = new JPanel();
            String[] types = {"customer", "employee", "order", "payment", "product"};
            Object[] options = {"Confirm", "Cancel"};
            JComboBox<String> typeList = new JComboBox<>(types);
            jPanel.add(new JLabel("What are you importing"));
            jPanel.add(typeList);
            int result = JOptionPane.showOptionDialog(null, jPanel, "Select import item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (result == JOptionPane.OK_OPTION) {
            	String selectedType = (String) typeList.getSelectedItem();
            	try {
					new FileImportController().csvFileImport(file, selectedType);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (TypeNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            }
        });
		menuFile.add(openFile);
        
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> System.exit(0));
		menuFile.add(exitItem);
		
		actions = new JMenu("Actions");
		addCustomer = new JMenuItem("Add customer");
		addCustomer.addActionListener(e -> {
			CustomerController controller = new CustomerController();
			try {
				controller.addCustomer(new AddCustomerPanel().customerPanel("Add Customer", null));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		listCustomer = new JMenuItem("List Customer");
		listCustomer.addActionListener(e -> {
			CustomerModel customer = new AddCustomerPanel().customerPanel("Customer filter", null);
			DataTableModel model = (DataTableModel) Run.getInstance().getTableContent().table.getModel();
			model.setColumnCount(0);
			model.setRowCount(0);
			model.fireTableDataChanged();
			CustomerController customerController = new CustomerController();
			Object[] columnNames = {"Customer Number", "Customer Name", "Contact Last Name", "Contact First Name", "Phone", "Address Line 1", "Address Line 2", "City", "State", "Postal Code", "Country", "Sales Rep Employee", "Credit Limit"};
			for (Object name : columnNames) {
				model.addColumn(name);
			}
			try {
				Object rowData[] = new Object[13];
				List<CustomerModel> customerList = customerController.getAllCustomers(customer);
				for (int i = 0; i < customerList.size(); i++) {
					rowData[0] = customerList.get(i).getCustomerNumber().toString();
					rowData[1] = customerList.get(i).getCustomerName();
					rowData[2] = customerList.get(i).getContactLastName();
					rowData[3] = customerList.get(i).getContactFirstName();
					rowData[4] = customerList.get(i).getPhone();
					rowData[5] = customerList.get(i).getAddressLine1();
					rowData[6] = customerList.get(i).getAddressLine2();
					rowData[7] = customerList.get(i).getCity();
					rowData[8] = customerList.get(i).getState();
					rowData[9] = customerList.get(i).getPostalCode();
					rowData[10] = customerList.get(i).getCountry();
					rowData[11] = customerList.get(i).getSalesRepEmployee().getEmployeeNumber();
					rowData[12] = customerList.get(i).getCreditLimit();
					model.addRow(rowData);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (CustomerNotFoundException e1) {
				e1.printStackTrace();
			}
			
			Run.getInstance().getTableContent().scrollPane.revalidate();
		});
		actions.add(addCustomer);
		actions.add(listCustomer);
		
		
		help = new JMenu("Help");
		
		option = new JMenuItem ("Info about this application");
		option.addActionListener(e -> JOptionPane.showMessageDialog(null, "Exam application created by Marthin, Christopher and Hans. " + "This Application has been connected to a database with customers, orders, employees etc. ", "Application information", JOptionPane.INFORMATION_MESSAGE));
		help.add(option);
		
		
		this.add(menuFile);
		this.add(actions);
		this.add(help);
	}		
}
