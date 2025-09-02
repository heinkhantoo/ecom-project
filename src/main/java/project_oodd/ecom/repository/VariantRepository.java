package project_oodd.ecom.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.Product;
import project_oodd.ecom.model.Variant;

public interface VariantRepository extends JpaRepository<Variant, UUID> {
	Optional<Variant> findByProduct(Product product);
}
