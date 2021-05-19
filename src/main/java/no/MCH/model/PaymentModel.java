package no.MCH.model;

import java.sql.Date;

public class PaymentModel {
	private CustomerModel customer;
	private String checkNumber;
	private Date paymentDate;
	private Double amount;
	
	public PaymentModel(CustomerModel customer, String checkNumber) {
		super();
		this.customer = customer;
		this.checkNumber = checkNumber;
	}
	
	public PaymentModel(CustomerModel customer, String checkNumber, Date paymentDate, Double amount) {
		super();
		this.customer = customer;
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public CustomerModel getCustomer() {
		return customer;
	}
	
	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}
	
	public String getCheckNumber() {
		return checkNumber;
	}
	
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
