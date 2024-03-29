package no.MCH.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import no.MCH.controller.CustomerController;
import no.MCH.controller.EmployeeController;
import no.MCH.controller.OrderController;
import no.MCH.exception.EmployeeNotFoundException;
import no.MCH.exception.OrderNotFoundException;
import no.MCH.model.CustomerModel;
import no.MCH.model.DataTableModel;
import no.MCH.model.EmployeeModel;
import no.MCH.model.OfficeModel;
import no.MCH.model.OrderModel;

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
					Object[] options = {"Update", "Cancel"};
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
							if (updatedCustomer != null) controller.updateCustomer(updatedCustomer, Integer.parseInt(key));
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else if (columnName.contains("employee")) {
						EmployeeModel employee = new EmployeeModel(
								Integer.parseInt(key),
								(String) target.getModel().getValueAt(row, 1),
								(String) target.getModel().getValueAt(row, 2),
								(String) target.getModel().getValueAt(row, 3),
								(String) target.getModel().getValueAt(row, 4),
								new OfficeModel((String) target.getModel().getValueAt(row, 5)),
								new EmployeeModel(Integer.parseInt(target.getModel().getValueAt(row, 6).toString())),
								(String) target.getModel().getValueAt(row, 7)
								);
						EmployeeModel updatedEmployee = new EmployeePanel().employeePanel(columnName, employee, options);
						EmployeeController controller = new EmployeeController();

						if (updatedEmployee != null) {
							try {
								controller.updateEmployee(updatedEmployee, Integer.parseInt(key));
							} catch (NumberFormatException | EmployeeNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}
						}
				
					} else if (columnName.contains("order")) {
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date oDate = null;
						Date rDate = null;
						Date sDate = null;
						try {
							sDate = new Date(format.parse(target.getModel().getValueAt(row, 3).toString()).getTime());
							rDate = new Date(format.parse(target.getModel().getValueAt(row, 2).toString()).getTime());
							oDate = new Date(format.parse(target.getModel().getValueAt(row, 1).toString()).getTime());
						} catch (ParseException e2) {
							e2.printStackTrace();
						}
						OrderModel order = new OrderModel(
								Integer.parseInt(key),
								oDate,
								rDate,
								sDate,
								(String) target.getModel().getValueAt(row, 4),
								(String) target.getModel().getValueAt(row, 5),
								new CustomerModel(Integer.parseInt(target.getModel().getValueAt(row, 6).toString())));
						OrderModel updateOrder = new AddOrderPanel().orderPanel(columnName, order, options);
						OrderController controller = new OrderController();

						if (updateOrder != null) {
							try {
								controller.updateOrder(updateOrder, Integer.parseInt(key));
							} catch (NumberFormatException | SQLException | OrderNotFoundException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
	}
}
