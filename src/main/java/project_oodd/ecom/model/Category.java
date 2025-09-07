package project_oodd.ecom.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID cid;

//	@Column(name = "code", length = 50, unique = true, nullable = false)
//	private String categoryCode;

	@Column(name = "name", unique = true, length = 150, nullable = false)
	private String categoryName;

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

	public UUID getCid() {
		return cid;
	}

	public void setCid(UUID cid) {
		this.cid = cid;
	}

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

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
