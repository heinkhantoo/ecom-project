package project_oodd.ecom.dto;

import java.util.UUID;

public class SubCategoryReqDTO {
//	private String subCategoryCode;
	private String subCategoryName;
	private UUID category;

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

//	public UUID getSid() {
//		return sid;
//	}
//
//	public void setSid(UUID sid) {
//		this.sid = sid;
//	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public UUID getCategory() {
		return category;
	}

	public void setCategory(UUID category) {
		this.category = category;
	}

}
