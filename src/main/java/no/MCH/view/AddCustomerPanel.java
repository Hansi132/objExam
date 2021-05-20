package no.MCH.view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import no.MCH.model.CustomerModel;
import no.MCH.model.EmployeeModel;
import no.MCH.utils.ImageLoader;

public class AddCustomerPanel {
	private static Logger log = Logger.getLogger(AddCustomerPanel.class);
	
	public CustomerModel customerPanel(String title, CustomerModel customer) {
		JTextField customerNumber = new JTextField();
		JTextField customerName = new JTextField();
		JTextField contactLastName = new JTextField();
		JTextField contactFirstName = new JTextField();
		JTextField phone = new JTextField();
		JTextField addressLine1 = new JTextField();
		JTextField addressLine2 = new JTextField();
		JTextField city = new JTextField();
		JTextField state = new JTextField();
		JTextField postalCode = new JTextField();
		JTextField country = new JTextField();
		JTextField salesRepEmployeeNumber = new JTextField();
		JTextField creditLimit = new JTextField();
		
		if (customer != null) {
			customerNumber = new JTextField(customer.getCustomerNumber().toString());
			customerName = new JTextField(customer.getCustomerName());
			contactLastName = new JTextField(customer.getContactLastName());
			contactFirstName = new JTextField(customer.getContactFirstName());
			phone = new JTextField(customer.getPhone());
			addressLine1 = new JTextField(customer.getAddressLine1());
			addressLine2 = new JTextField(customer.getAddressLine2());
			city = new JTextField(customer.getCity());
			state = new JTextField(customer.getState());
			postalCode = new JTextField(customer.getPostalCode());
			country = new JTextField(customer.getCountry());
			salesRepEmployeeNumber = new JTextField(customer.getSalesRepEmployee().getEmployeeNumber().toString());
			creditLimit = new JTextField(customer.getCreditLimit().toString());
		} 
		
		
		JPanel jPanel = new JPanel( new GridLayout(0, 2));
		
		jPanel.add(new JLabel("Customer Number: "));
		jPanel.add(customerNumber);
		jPanel.add(new JLabel("Customer Name: "));
		jPanel.add(customerName);
		jPanel.add(new JLabel("Contact Last Name: "));
		jPanel.add(contactLastName);
		jPanel.add(new JLabel("Contact First Name: "));
		jPanel.add(contactFirstName);
		jPanel.add(new JLabel("Phone: "));
		jPanel.add(phone);
		jPanel.add(new JLabel("Address Line 1: "));
		jPanel.add(addressLine1);
		jPanel.add(new JLabel("Address Line 2: "));
		jPanel.add(addressLine2);
		jPanel.add(new JLabel("City: "));
		jPanel.add(city);
		jPanel.add(new JLabel("State: "));
		jPanel.add(state);
		jPanel.add(new JLabel("Postal Code: "));
		jPanel.add(postalCode);
		jPanel.add(new JLabel("Country: "));
		jPanel.add(country);
		jPanel.add(new JLabel("Sales Rep Employee Number: "));
		jPanel.add(salesRepEmployeeNumber);
		jPanel.add(new JLabel("Credit Limit: "));
		jPanel.add(creditLimit);
		
		ImageIcon icon = new ImageLoader().loadImage("/images/user.png", "Customer icon");
		
		int result = JOptionPane.showConfirmDialog(null, jPanel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
		if (result == JOptionPane.OK_OPTION) {
			return new CustomerModel(
						customerNumber.getText().equals("") ? null : Integer.parseInt(customerNumber.getText()),
						customerName.getText().equals("") ? null : customerName.getText(),
						contactLastName.getText().equals("") ? null : contactLastName.getText(),
						contactFirstName.getText().equals("") ? null : contactFirstName.getText(),
						phone.getText().equals("") ? null : phone.getText(),
						addressLine1.getText().equals("") ? null : addressLine1.getText(),
						addressLine2.getText().equals("") ? null : addressLine2.getText(),
						city.getText().equals("") ? null : city.getText(),
						state.getText().equals("") ? null : state.getText(),
						postalCode.getText().equals("") ? null : postalCode.getText(),
						country.getText().equals("") ? null : country.getText(),
						salesRepEmployeeNumber.getText().equals("") ? null : new EmployeeModel((salesRepEmployeeNumber.getText().equals("") ? null : Integer.parseInt(salesRepEmployeeNumber.getText()))),
						creditLimit.getText().equals("") ? null : Double.parseDouble(creditLimit.getText())
					);
		}
		return null;
	}
}
