package no.MCH.view;

import java.awt.GridLayout;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import no.MCH.Run;
import no.MCH.controller.OrderController;
import no.MCH.model.DataTableModel;
import no.MCH.model.OrderModel;

public class OrderPanel {
	
	public void orderPanel() {
		Properties props = new Properties();
		props.put("text.today", "Today");
		props.put("text.month", "Month");
		props.put("text.year", "Year");
		
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, props);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, props);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		JPanel jPanel = new JPanel(new GridLayout(2, 0));
		
		jPanel.add(new JLabel("From date"));
		jPanel.add(datePanel1);
		jPanel.add(new JLabel("To date"));
		jPanel.add(datePanel2);
		
		int result = JOptionPane.showConfirmDialog(null, jPanel, "Order date filter", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		if (result == JOptionPane.OK_OPTION) {
			java.util.Date fromDateUtil = (java.util.Date) datePicker1.getModel().getValue();
			java.util.Date toDateUtil = (java.util.Date) datePicker2.getModel().getValue();
			Date fromDate = new Date(fromDateUtil.getTime());
			Date toDate = new Date(toDateUtil.getTime());
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			try {
				DataTableModel model = (DataTableModel) Run.getInstance().getTableContent().table.getModel();
				model.setColumnCount(0);
				model.setRowCount(0);
				model.fireTableDataChanged();
				Object[] columnNames = {"Order Number", "Order Date", "Required Date", "Shipped Date", "Status", "Comments", "Customer Number"};
				for (Object name : columnNames) {
					model.addColumn(name);
				}
				Object rowData[] = new Object[8];
				List<OrderModel> orderList = new OrderController().getAllOrders(null, fromDate, toDate);
				for (int i = 0; i < orderList.size(); i++) {
					rowData[0] = orderList.get(i).getOrderNumber().toString();
					rowData[1] = simpleDateFormat.format(orderList.get(i).getOrderDate());
					rowData[2] = simpleDateFormat.format(orderList.get(i).getRequiredDate());
					rowData[3] = simpleDateFormat.format(orderList.get(i).getShippedDate());
					rowData[4] = orderList.get(i).getStatus();
					rowData[5] = orderList.get(i).getComments();
					rowData[6] = orderList.get(i).getCustomer().getCustomerNumber().toString();
					model.addRow(rowData);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
