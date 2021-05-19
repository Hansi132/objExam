package no.MCH.model;

public class ProductLineModel {
	private String productLine;
	private String textDescription;
	private String htmlDescription;
	private byte[] image;
	
	public ProductLineModel(String productLine) {
		this.productLine = productLine;
	}
	
	public ProductLineModel(String productLine, String textDescription, String htmlDescription, byte[] image) {
		super();
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image = image;
	}

	public String getProductLine() {
		return productLine;
	}
	
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	
	public String getTextDescription() {
		return textDescription;
	}
	
	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}
	
	public String getHtmlDescription() {
		return htmlDescription;
	}
	
	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
}
