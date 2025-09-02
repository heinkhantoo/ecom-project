package project_oodd.ecom.dto;

import java.util.UUID;

public class SubCategoryResDTO {
//	private String subCategoryCode;
	private UUID sid;
	private String subCategoryName;
	private String category;

//	public String getSubCategoryCode() {
//		return subCategoryCode;
//	}
//
//	public void setSubCategoryCode(String subCategoryCode) {
//		this.subCategoryCode = subCategoryCode;
//	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public UUID getSid() {
		return sid;
	}

	public void setSid(UUID sid) {
		this.sid = sid;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
