package no.MCH.model;

public class OrderDetailModel {
	private OrderModel order;
	private ProductModel product;
	private Integer quantityOrdered;
	private Double priceEach;
	private Integer orderLineNumber;
	
	public OrderDetailModel(OrderModel order, ProductModel product) {
		super();
		this.order = order;
		this.product = product;
	}

	public OrderDetailModel(OrderModel order, ProductModel product, Integer quantityOrdered, Double priceEach,
			Integer orderLineNumber) {
		super();
		this.order = order;
		this.product = product;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public Double getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}

	public Integer getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(Integer orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
	
}
