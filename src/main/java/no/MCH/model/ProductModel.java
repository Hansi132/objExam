package no.MCH.model;

public class ProductModel {
	private String productCode;
	private String productName;
	private ProductLineModel productLine;
	private String productScale;
	private String productVendor;
	private String productDescription;
	private short quantityInStock;
	private double buyPrice;
	private double MSRP;
	
	public ProductModel(String productCode) {
		super();
		this.productCode = productCode;
	}

	public ProductModel(String productCode, String productName, ProductLineModel productLine, String productScale,
			String productVendor, String productDescription, short quantityInStock, double buyPrice, double mSRP) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.MSRP = mSRP;
	}

	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public ProductLineModel getProductLine() {
		return productLine;
	}
	
	public void setProductLine(ProductLineModel productLine) {
		this.productLine = productLine;
	}
	
	public String getProductScale() {
		return productScale;
	}
	
	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}
	
	public String getProductVendor() {
		return productVendor;
	}
	
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public short getQuantityInStock() {
		return quantityInStock;
	}
	
	public void setQuantityInStock(short quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
	public double getBuyPrice() {
		return buyPrice;
	}
	
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	public double getMSRP() {
		return MSRP;
	}
	
	public void setMSRP(double mSRP) {
		MSRP = mSRP;
	}

}
