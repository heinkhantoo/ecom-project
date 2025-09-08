package project_oodd.ecom.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderDTO {
	private UUID orderId;
    private LocalDateTime orderDate;
    private String status;
    private double total;
    private List<OrderItemDTO> items;
    
    
	public OrderDTO(UUID orderId, LocalDateTime orderDate, String status, double total, List<OrderItemDTO> items) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.status = status;
		this.total = total;
		this.items = items;
	}
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<OrderItemDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
    
}
