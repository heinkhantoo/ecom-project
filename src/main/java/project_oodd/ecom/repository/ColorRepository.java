package project_oodd.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
	Optional<Color> findByColorCodeIgnoreCase(String description);
}
