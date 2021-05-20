package no.MCH.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.MCH.Run;
import no.MCH.controller.CustomerController;
import no.MCH.controller.EmployeeController;
import no.MCH.exception.CustomerNotFoundException;
import no.MCH.exception.EmployeeNotFoundException;
import no.MCH.model.CustomerModel;
import no.MCH.model.DataTableModel;
import no.MCH.model.EmployeeModel;

public class MainContent extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MainContent() {
		mainContentBuilder();
		this.setLayout(new GridLayout(0, 1));
		this.setAlignmentX(FlowLayout.LEFT);
		DefaultGui.componentModel.addComponent(this);
	}
	
	protected void mainContentBuilder() {
		addCustomerButton();
		listCustomerButton();
		deleteCustomerButton();
		
		addEmployeeButton();
		listEmployeeButton();
		deleteEmployeeButton();
		
		addOrderButton();
		listOrderButton();
		deleteOrderButton();
		
		addProductButton();
		listProductButton();
		deleteProductButton();
	}

	private void addCustomerButton() {
		JButton addCustomerButton = new JButton("Add customer");
		addCustomerButton.addActionListener(e -> {
			CustomerController controller = new CustomerController();
			try {
				controller.addCustomer(new AddCustomerPanel().customerPanel("Add customer", null));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		this.add(addCustomerButton);
	}
	
	private void listCustomerButton() {
		JButton listCustomerButton = new JButton("List customers");
		listCustomerButton.addActionListener(e -> {
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
		this.add(listCustomerButton);
	}
	
	private void deleteCustomerButton() {
		JButton deleteCustomerButton = new JButton("Delete customer");
		deleteCustomerButton.addActionListener(e -> {
			Object[] options = {"Delete", "Cancel"};
			Integer key = null;
			try {
				key = Integer.parseInt(new DeleteOptionPanel().createPanel("/images/user.png", "Customer icon", "Delete customer", options).toString());
			} catch(NumberFormatException | NullPointerException e3) {
				//Silent catch to avoid errors.
			}
			
			if (key != null) {
				try {
					new CustomerController().deleteCustomer(key);
				} catch (CustomerNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		this.add(deleteCustomerButton);
	}
	
	//Employee buttons
	private void addEmployeeButton() {
		JButton addEmployeeButton = new JButton("Add employee");
		addEmployeeButton.addActionListener(e -> {
			Object[] options = {"Create", "Cancel"};
			EmployeeModel employee = new EmployeePanel().employeePanel("Create Employee", null, options);
			EmployeeController controller = new EmployeeController();
			try {
				controller.addEmployee(employee);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		this.add(addEmployeeButton);
	}
	
	private void listEmployeeButton() {
		JButton listEmployeeButton = new JButton("List employee");
		listEmployeeButton.addActionListener(e -> {
			Object[] options = {"Search", "Cancel"};
			EmployeeModel employee = new EmployeePanel().employeePanel("Employee filter", null, options);
			DataTableModel model = (DataTableModel) Run.getInstance().getTableContent().table.getModel();
			model.setColumnCount(0);
			model.setRowCount(0);
			model.fireTableDataChanged();
			EmployeeController employeeController = new EmployeeController();
			Object[] columnNames = {"Employee Number", "Last Name", "First Name", "Extension", "Email", "Office Code", "Reports To", "Job Title"};
			for (Object name : columnNames) {
				model.addColumn(name);
			}
			try {
				Object rowData[] = new Object[8];
				List<EmployeeModel> employeeList = employeeController.getAllEmployees(employee);
				for (int i = 0; i < employeeList.size(); i++) {
					rowData[0] = employeeList.get(i).getEmployeeNumber().toString();
					rowData[1] = employeeList.get(i).getLastName();
					rowData[2] = employeeList.get(i).getFirstName();
					rowData[3] = employeeList.get(i).getExtension();
					rowData[4] = employeeList.get(i).getEmail();
					rowData[5] = employeeList.get(i).getOffice().getOfficeCode();
					rowData[6] = employeeList.get(i).getReportsTo().getEmployeeNumber().toString();
					rowData[7] = employeeList.get(i).getJobTitle();
					model.addRow(rowData);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (EmployeeNotFoundException e1) {
				e1.printStackTrace();
			}
			
			Run.getInstance().getTableContent().scrollPane.revalidate();
		});
		this.add(listEmployeeButton);
	}
	
	private void deleteEmployeeButton() {
		JButton deleteEmployeeButton = new JButton("Delete employee");
		deleteEmployeeButton.addActionListener(e -> {
			Object[] options = {"Delete", "Cancel"};
			Integer key = null;
			try {
				key = Integer.parseInt(new DeleteOptionPanel().createPanel("/images/user.png", "Customer icon", "Delete employee", options).toString());
			} catch(NumberFormatException | NullPointerException e3) {
				//Silent catch to avoid errors.
			}
			
			if (key != null) {
				try {
					new EmployeeController().deleteEmployee(key);
				} catch (CustomerNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		this.add(deleteEmployeeButton);
	}
	
	//Order buttons
	private void addOrderButton() {
		JButton addOrderButton = new JButton("Add Order");
		this.add(addOrderButton);
	}
	
	private void listOrderButton() {
		JButton listOrderButton = new JButton("List Order");
		this.add(listOrderButton);
	}
	
	private void deleteOrderButton() {
		JButton deleteOrderButton = new JButton("Delete Order");
		this.add(deleteOrderButton);
	}
	
	//Product buttons
	private void addProductButton() {
		JButton addProductButton = new JButton("Add Product");
		this.add(addProductButton);
	}
	
	private void listProductButton() {
		JButton listProductButton = new JButton("List Product");
		this.add(listProductButton);
	}
	
	private void deleteProductButton() {
		JButton deleteProductButton = new JButton("Delete Product");
		this.add(deleteProductButton);
	}

}
