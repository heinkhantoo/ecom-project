package project_oodd.ecom.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.Cart;
import project_oodd.ecom.model.User;

public interface CartRepository extends JpaRepository<Cart, UUID> {
	Optional<Cart> findByUserAndActiveTrue(User user);
}
