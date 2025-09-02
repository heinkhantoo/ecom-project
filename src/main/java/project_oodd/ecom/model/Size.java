package project_oodd.ecom.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Size {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID sid;

	@Column(length = 20, unique = true, nullable = false)
	private String value;

	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	@PrePersist
	public void onCreate() {
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
	}

	@PreUpdate
	public void onUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}

	public UUID getSid() {
		return sid;
	}

	public void setSid(UUID id) {
		this.sid = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
