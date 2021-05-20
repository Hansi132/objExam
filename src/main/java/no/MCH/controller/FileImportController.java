package no.MCH.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import no.MCH.exception.TypeNotFoundException;
import no.MCH.model.CustomerModel;
import no.MCH.model.EmployeeModel;
import no.MCH.model.OfficeModel;
import no.MCH.model.OrderModel;
import no.MCH.model.PaymentModel;
import no.MCH.model.ProductLineModel;
import no.MCH.model.ProductModel;

public class FileImportController {

	public void csvFileImport(File file, String type) throws IOException, TypeNotFoundException, SQLException {
		List<CustomerModel> customerList = new ArrayList<>();
		List<EmployeeModel> employeeList = new ArrayList<>();
		List<OrderModel> orderList = new ArrayList<>();
		List<PaymentModel> paymentList = new ArrayList<>();
		List<ProductModel> productList = new ArrayList<>();
		FileReader reader;
		BufferedReader br = null;
		String line;
		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			line = br.readLine();
		
		
			while (line != null) {
				String[] data = line.split(",");
				
				switch (type.toLowerCase()) {
				case "customer":
					customerList.add(new CustomerModel(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], new EmployeeModel(Integer.parseInt(data[11])), Double.parseDouble(data[12])));
					break;
				case "employee":
					employeeList.add(new EmployeeModel(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], new OfficeModel(data[5]), new EmployeeModel(Integer.parseInt(data[6])), data[7]));
					break;
				case "order":
					orderList.add(new OrderModel(Integer.parseInt(data[0]), Date.valueOf(data[1]), Date.valueOf(data[2]), Date.valueOf(data[3]), data[4], data[5], new CustomerModel(Integer.parseInt(data[6]))));
					break;
				case "payment":
					paymentList.add(new PaymentModel(new CustomerModel(Integer.parseInt(data[0])), data[1], Date.valueOf(data[2]), Double.parseDouble(data[3])));
					break;
				case "product":
					productList.add(new ProductModel(data[0], data[1], new ProductLineModel(data[2]), data[3], data[4], data[5], Short.parseShort(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8])));
					break;
				default:
					throw new TypeNotFoundException("Available type not found");
				}
				
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		
		switch (type.toLowerCase()) {
		case "customer":
			new CustomerController().addCustomer(customerList);
			break;
		case "employee":
			new EmployeeController().addEmployee(employeeList);
			break;
		case "order":
			new OrderController().addOrder(orderList);
			break;
		case "payment":
			new PaymentController().addPayment(paymentList);
			break;
		case "product":
			new ProductController().addProduct(productList);
			break;
		default:
			throw new TypeNotFoundException("Available type not found");
		}
		
	}
}
