package project_oodd.ecom.dto;

import java.util.UUID;

public class SizeDTO {
	private UUID sid;
	private String value;

	public UUID getSid() {
		return sid;
	}

	public void setSid(UUID sid) {
		this.sid = sid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
