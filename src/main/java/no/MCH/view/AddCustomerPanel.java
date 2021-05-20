package no.MCH.view;

import java.awt.GridLayout;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import no.MCH.controller.CustomerController;
import no.MCH.model.CustomerModel;
import no.MCH.model.EmployeeModel;
import no.MCH.utils.ImageLoader;

public class AddCustomerPanel {
	private static Logger log = Logger.getLogger(AddCustomerPanel.class);
	
	public AddCustomerPanel() {
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
		
		JPanel jPanel = new JPanel( new GridLayout(13, 2));
		
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
		
		int result = JOptionPane.showConfirmDialog(null, jPanel, "Add customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
		if (result == JOptionPane.OK_OPTION) {
			CustomerModel customer = new CustomerModel(
						Integer.parseInt(customerNumber.getText()),
						customerName.getText(),
						contactLastName.getText(),
						contactFirstName.getText(),
						phone.getText(),
						addressLine1.getText(),
						addressLine2.getText(),
						city.getText(),
						state.getText(),
						postalCode.getText(),
						country.getText(),
						new EmployeeModel(Integer.parseInt(salesRepEmployeeNumber.getText())),
						Double.parseDouble(creditLimit.getText())
					);
			try {
				new CustomerController().addCustomer(customer);
			} catch (SQLException e) {
				
				log.error(e.getMessage(), e);
			}
		}
	}
}
