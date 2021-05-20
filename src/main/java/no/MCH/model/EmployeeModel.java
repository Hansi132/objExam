package no.MCH.model;

public class EmployeeModel {
	private Integer employeeNumber;
	private String firstName;
	private String lastName;
	private String extension;
	private String email;
	private OfficeModel office;
	private EmployeeModel reportsTo;
	private String jobTitle;
	
	public EmployeeModel(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public EmployeeModel(Integer employeeNumber, String lastName, String firstName, String extension, String email,
			OfficeModel office, EmployeeModel reportsTo, String jobTitle) {
		super();
		this.employeeNumber = employeeNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.extension = extension;
		this.email = email;
		this.office = office;
		this.reportsTo = reportsTo;
		this.jobTitle = jobTitle;
	}
	
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public OfficeModel getOffice() {
		return office;
	}

	public void setOffice(OfficeModel office) {
		this.office = office;
	}

	public EmployeeModel getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(EmployeeModel reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

}
