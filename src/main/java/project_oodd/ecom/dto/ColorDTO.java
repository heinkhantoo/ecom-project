package project_oodd.ecom.dto;

import java.util.UUID;

public class ColorDTO {
	private UUID cid;
	private String colorDescription;
	private String hex;

//	public String getColorCode() {
//		return colorCode;
//	}
//
//	public void setColorCode(String colorCode) {
//		this.colorCode = colorCode;
//	}

	public UUID getCid() {
		return cid;
	}

	public void setCid(UUID cid) {
		this.cid = cid;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public String getColorDescription() {
		return colorDescription;
	}

	public void setColorDescription(String colorDescription) {
		this.colorDescription = colorDescription;
	}

}
