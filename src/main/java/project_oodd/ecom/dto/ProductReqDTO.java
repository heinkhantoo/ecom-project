package project_oodd.ecom.dto;

import java.util.List;
import java.util.UUID;

public class ProductReqDTO {

//	private String productCode;
//	private UUID pid;
	private String productName;
	private Double price;
	private String img;
	private UUID subCategory;
//	private LocalDateTime createdDate;
	private List<VariantReqDTO> variants;

//	public String getProductCode() {
//		return productCode;
//	}
//
//	public void setProductCode(String productCode) {
//		this.productCode = productCode;
//	}

//	public UUID getPid() {
//		return pid;
//	}
//
//	public void setPid(UUID pid) {
//		this.pid = pid;
//	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}

	public UUID getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(UUID subCategory) {
		this.subCategory = subCategory;
	}

//	public LocalDateTime getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(LocalDateTime createdDate) {
//		this.createdDate = createdDate;
//	}

	public List<VariantReqDTO> getVariants() {
		return variants;
	}

	public void setVariants(List<VariantReqDTO> variants) {
		this.variants = variants;
	}

}
