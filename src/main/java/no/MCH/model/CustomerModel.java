package no.MCH.model;

public class CustomerModel {
	private Integer customerNumber;
	private String customerName;
	private String contactFirstName;
	private String contactLastName;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private EmployeeModel salesRepEmployee;
	private Double creditLimit;
	
	public CustomerModel(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public CustomerModel(Integer customerNumber, String customerName, String contactFirstName, String contactLastName, String phone,
			String addressLine1, String addressLine2, String city, String state, String postalCode, String country,
			EmployeeModel salesRepEmployee, Double creditLimit) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.salesRepEmployee = salesRepEmployee;
		this.creditLimit = creditLimit;
	}
	
	public Integer getCustomerNumber() {
		return customerNumber;
	}
	
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getContactFirstName() {
		return contactFirstName;
	}
	
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public EmployeeModel getSalesRepEmployee() {
		return salesRepEmployee;
	}
	
	public void setSalesRepEmployee(EmployeeModel salesRepEmployee) {
		this.salesRepEmployee = salesRepEmployee;
	}
	
	public Double getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	
}
