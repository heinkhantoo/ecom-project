package project_oodd.ecom.dto;

import java.util.UUID;

public class VariantReqDTO {
//	private UUID vid;
	private UUID product;
	private UUID color;
	private UUID size;
//	private String sku;
	private Integer stock;
	private String imageUrl;
	private String imageName;

//	public UUID getVid() {
//		return vid;
//	}
//
//	public void setVid(UUID vid) {
//		this.vid = vid;
//	}

	public UUID getColor() {
		return color;
	}

	public UUID getProduct() {
		return product;
	}

	public void setProduct(UUID product) {
		this.product = product;
	}

	public void setColor(UUID color) {
		this.color = color;
	}

	public UUID getSize() {
		return size;
	}

	public void setSize(UUID size) {
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

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageName() {
		return imageName;
	}


}
