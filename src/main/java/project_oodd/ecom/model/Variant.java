package project_oodd.ecom.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "product_variant", uniqueConstraints = @UniqueConstraint(columnNames = { "product_id", "color_id",
		"size_id" }))
public class Variant {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID vid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "color_id", nullable = false)
	private Color color;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "size_id", nullable = false)
	private Size size;

	private Integer stock;

	@Column(name = "image_url")
	private String imageUrl;

	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	@PrePersist
	public void onCreate() {
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		if (stock == null)
			this.stock = 0;
		if (imageUrl == null)
			this.imageUrl = "";
	}

	@PreUpdate
	public void onUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}

	public UUID getVid() {
		return vid;
	}

	public void setViId(UUID vid) {
		this.vid = vid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

//	public String getSku() {
//		return sku;
//	}
//
//	public void setSku(String sku) {
//		this.sku = sku;
//	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
