package project_oodd.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project_oodd.ecom.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmailIgnoreCase(String email);
	Optional<User> findByUserCodeIgnoreCase(String code);
}
