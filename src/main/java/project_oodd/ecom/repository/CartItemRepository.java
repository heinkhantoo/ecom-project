package project_oodd.ecom.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.Cart;
import project_oodd.ecom.model.CartItem;
import project_oodd.ecom.model.Variant;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
	Optional<CartItem> findByCartAndProduct(Cart cart, Variant product);

	List<CartItem> findByCart(Cart cart);
}
