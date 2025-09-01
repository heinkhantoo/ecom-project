package project_oodd.ecom.dto;

import java.util.List;

public class ProductResDTO {

	private String productCode;
	private String productName;
	private Double price;
	private String img;
	private String category;
	private String subCategory;
	private List<VariantDTO> variants;

	public ProductResDTO() {
		defaultValues();
	}

	private void defaultValues() {
		if (category == null)
			this.category = "";
		if (subCategory == null)
			this.subCategory = "";
		if (img == null)
			this.img = "";
		if (price == null)
			this.price = 0.0;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public List<VariantDTO> getVariants() {
		return variants;
	}

	public void setVariants(List<VariantDTO> variants) {
		this.variants = variants;
	}
	
	
}
