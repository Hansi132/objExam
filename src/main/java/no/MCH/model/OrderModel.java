package no.MCH.model;

import java.sql.Date;

public class OrderModel {
	private Integer orderNumber;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	private String status;
	private String comments;
	private CustomerModel customer;
	
	public OrderModel(Integer orderNumber) {
		super();
		this.orderNumber = orderNumber;
	}
	
	public OrderModel(Integer orderNumber, Date orderDate, Date requiredDate, Date shippedDate, String status,
			String comments, CustomerModel customer) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
		this.customer = customer;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Date getRequiredDate() {
		return requiredDate;
	}
	
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	
	public Date getShippedDate() {
		return shippedDate;
	}
	
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public CustomerModel getCustomer() {
		return customer;
	}
	
	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

}
