package project_oodd.ecom.dto;

public class CartItemDTO {
	private String productName;
	private String color;
	private String size;
	private int quantity;
	private double lineTotal;
	
	

	public CartItemDTO(String productName, String color, String size, int quantity, double lineTotal) {
		super();
		this.productName = productName;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.lineTotal = lineTotal;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}

}
