package project_oodd.ecom.dto;

import java.util.UUID;

public class VariantResDTO {
	private UUID vid;
	private String product;
	private String color;
	private String size;
//	private String sku;
	private Integer stock;
	private String imageUrl;

	public UUID getVid() {
		return vid;
	}

	public void setVid(UUID vid) {
		this.vid = vid;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
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

//	public String getSku() {
//		return sku;
//	}
//
//	public void setSku(String sku) {
//		this.sku = sku;
//	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
