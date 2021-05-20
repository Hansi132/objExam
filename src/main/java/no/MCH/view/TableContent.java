package no.MCH.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import no.MCH.controller.CustomerController;
import no.MCH.model.CustomerModel;
import no.MCH.model.DataTableModel;
import no.MCH.model.EmployeeModel;

public class TableContent {
	JTable table;
	JScrollPane scrollPane;
	
	public TableContent(Object[][] data, String[] columnNames) {
		table = new JTable();
		table.setModel(new DataTableModel());
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		DefaultGui.componentModel.addComponent(scrollPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					String key = (String) target.getModel().getValueAt(row, 0);
					String columnName = target.getColumnName(0).toLowerCase();
					if (columnName.contains("customer")) {
						CustomerModel customer = new CustomerModel(
								Integer.parseInt(key),
								(String) target.getModel().getValueAt(row, 1),
								(String) target.getModel().getValueAt(row, 2),
								(String) target.getModel().getValueAt(row, 3),
								(String) target.getModel().getValueAt(row, 4),
								(String) target.getModel().getValueAt(row, 5),
								(String) target.getModel().getValueAt(row, 6),
								(String) target.getModel().getValueAt(row, 7),
								(String) target.getModel().getValueAt(row, 8),
								(String) target.getModel().getValueAt(row, 9),
								(String) target.getModel().getValueAt(row, 10),
								new EmployeeModel(Integer.parseInt(target.getModel().getValueAt(row, 11).toString())),
								Double.parseDouble(target.getModel().getValueAt(row, 12).toString()));
						CustomerModel updatedCustomer = new AddCustomerPanel().customerPanel("Update customer", customer);
						CustomerController controller = new CustomerController();
						try {
							controller.updateCustomer(updatedCustomer, Integer.parseInt(key));
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
					}
				}
			}
		});
	}
}
