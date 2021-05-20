package no.MCH.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.MCH.Run;
import no.MCH.controller.CustomerController;
import no.MCH.exception.CustomerNotFoundException;
import no.MCH.model.CustomerModel;
import no.MCH.model.DataTableModel;

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

}
