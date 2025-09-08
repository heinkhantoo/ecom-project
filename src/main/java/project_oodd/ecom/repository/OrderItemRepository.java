package project_oodd.ecom.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.Order;
import project_oodd.ecom.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

	List<OrderItem> findByOrder(Order order);
}
