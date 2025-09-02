package project_oodd.ecom.dto;

import java.util.UUID;

public class CategoryDTO {
	private UUID cid;
	private String categoryName;
//	private String categoryCode;

//	public String getCategoryCode() {
//		return categoryCode;
//	}
//
//	public void setCategoryCode(String categoryCode) {
//		this.categoryCode = categoryCode;
//	}
	

	public String getCategoryName() {
		return categoryName;
	}

	public UUID getCid() {
		return cid;
	}

	public void setCid(UUID cid) {
		this.cid = cid;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
