package no.MCH.view;

import java.awt.GridLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import no.MCH.model.CustomerModel;
import no.MCH.model.OrderModel;
import no.MCH.utils.ImageLoader;

public class AddOrderPanel {

	public OrderModel orderPanel(String title, OrderModel order, Object[] options) {
		JTextField orderNumber = new JTextField();
		JTextField orderDate = new JTextField();
		JTextField requiredDate = new JTextField();
		JTextField shippedDate = new JTextField();
		JTextField status = new JTextField();
		JTextField comments = new JTextField();
		JTextField customerNumber = new JTextField();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (order != null) {
			orderNumber = new JTextField(order.getOrderNumber().toString());
			orderDate = new JTextField(formatter.format(order.getOrderDate()));
			requiredDate = new JTextField(formatter.format(order.getRequiredDate()));
			shippedDate = new JTextField(formatter.format(order.getShippedDate()));
			status = new JTextField(order.getStatus());
			comments = new JTextField(order.getComments());
			customerNumber = new JTextField(order.getCustomer().getCustomerNumber().toString());
		}
		
		JPanel jPanel = new JPanel(new GridLayout(0, 2));
		
		jPanel.add(new JLabel("Order Number: "));
		jPanel.add(orderNumber);
		jPanel.add(new JLabel("Order Date: "));
		jPanel.add(orderDate);
		jPanel.add(new JLabel("Required Date: "));
		jPanel.add(requiredDate);
		jPanel.add(new JLabel("Shipped Date: "));
		jPanel.add(shippedDate);
		jPanel.add(new JLabel("Status: "));
		jPanel.add(status);
		jPanel.add(new JLabel("Comments: "));
		jPanel.add(comments);
		jPanel.add(new JLabel("Customer Number: "));
		jPanel.add(customerNumber);
		
		ImageIcon icon = new ImageLoader().loadImage("/images/note.png", "Order icon");
		int result = JOptionPane.showOptionDialog(null, jPanel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
		if (result == JOptionPane.OK_OPTION) {
			return new OrderModel(
					orderNumber.getText().equals("") ? null : Integer.parseInt(orderNumber.getText()),
					orderDate.getText().equals("") ? null : Date.valueOf(orderDate.getText()),
					requiredDate.getText().equals("") ? null : Date.valueOf(requiredDate.getText()),
					shippedDate.getText().equals("") ? null : Date.valueOf(shippedDate.getText()),
					status.getText().equals("") ? null : status.getText(),
					comments.getText().equals("") ? null : comments.getText(),
					customerNumber.getText().equals("") ? null : new CustomerModel(Integer.parseInt(customerNumber.getText()))
					);
		}
		return null;
	}
}
