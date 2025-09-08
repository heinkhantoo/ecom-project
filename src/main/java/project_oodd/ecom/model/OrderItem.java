package project_oodd.ecom.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Variant product;
    
    private int quantity;
    private double price;
    
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
	
	public double getLineTotal() {
        return product.getProduct().getPrice() * quantity;
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Variant getProduct() {
		return product;
	}

	public void setProduct(Variant product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
