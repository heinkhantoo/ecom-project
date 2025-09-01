package project_oodd.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import project_oodd.ecom.model.Variant;

public interface VariantRepository extends JpaRepository<Variant, Long> {
	Optional<Variant> findBySkuIgnoreCase(String sku);
	Optional<Variant> findByProduct(Long id);
}
