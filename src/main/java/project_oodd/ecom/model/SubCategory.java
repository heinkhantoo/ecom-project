package project_oodd.ecom.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID scid;

//	@Column(name = "code", length = 50, unique = true, nullable = false)
//	private String subCategoryCode;

	@Column(name = "name", length = 150, nullable = false)
	private String subCategoryName;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

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

	public UUID getScid() {
		return scid;
	}

	public void setScid(UUID scid) {
		this.scid = scid;
	}

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

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
