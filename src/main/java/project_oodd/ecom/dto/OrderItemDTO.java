package project_oodd.ecom.dto;

public class OrderItemDTO {
    private String productName;
    private int quantity;
    private double price;
    private double lineTotal;
    
    
	public OrderItemDTO(String productName, int quantity, double price, double lineTotal) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.lineTotal = lineTotal;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}
    
    
}
