package no.MCH.view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import no.MCH.model.EmployeeModel;
import no.MCH.model.OfficeModel;
import no.MCH.utils.ImageLoader;

public class EmployeePanel {

	public EmployeeModel employeePanel(String title, EmployeeModel employee, Object[] options) {
		JTextField employeeNumber = new JTextField();
		JTextField lastName = new JTextField();
		JTextField firstName = new JTextField();
		JTextField extension = new JTextField();
		JTextField email = new JTextField();
		JTextField officeCode = new JTextField();
		JTextField reportsTo = new JTextField();
		JTextField jobTitle = new JTextField();

		if (employee != null) {
			employeeNumber = new JTextField(employee.getEmployeeNumber().toString());
			lastName = new JTextField(employee.getLastName());
			firstName = new JTextField(employee.getFirstName());
			extension = new JTextField(employee.getExtension());
			email = new JTextField(employee.getEmail());
			officeCode = new JTextField(employee.getOffice().getOfficeCode());
			reportsTo = new JTextField(employee.getReportsTo().getEmployeeNumber().toString());
			jobTitle = new JTextField(employee.getJobTitle());
		}
		
		JPanel jPanel = new JPanel(new GridLayout(0, 2));
		
		jPanel.add(new JLabel("Employee Number: "));
		jPanel.add(employeeNumber);
		jPanel.add(new JLabel("Last Name: "));
		jPanel.add(lastName);
		jPanel.add(new JLabel("First Name: "));
		jPanel.add(firstName);
		jPanel.add(new JLabel("Extension: "));
		jPanel.add(extension);
		jPanel.add(new JLabel("Email: "));
		jPanel.add(email);
		jPanel.add(new JLabel("Office Code: "));
		jPanel.add(officeCode);
		jPanel.add(new JLabel("Reports To: "));
		jPanel.add(reportsTo);
		jPanel.add(new JLabel("Job Title: "));
		jPanel.add(jobTitle);
		
		ImageIcon icon = new ImageLoader().loadImage("/images/user.png", "Employee icon");
		
		int result = JOptionPane.showOptionDialog(null, jPanel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
		if (result == JOptionPane.OK_OPTION) {
			return new EmployeeModel(
					employeeNumber.getText().equals("") ? null : Integer.parseInt(employeeNumber.getText()),
					lastName.getText().equals("") ? null : lastName.getText(),
					firstName.getText().equals("") ? null : firstName.getText(),
					extension.getText().equals("") ? null : extension.getText(),
					email.getText().equals("") ? null : email.getText(),
					officeCode.getText().equals("") ? null : new OfficeModel(officeCode.getText()),
					reportsTo.getText().equals("") ? null : new EmployeeModel(Integer.parseInt(reportsTo.getText())),
					jobTitle.getText().equals("") ? null : jobTitle.getText()
					);
		}
		return null;
	}

}
