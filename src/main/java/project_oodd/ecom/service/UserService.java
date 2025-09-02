package project_oodd.ecom.service;

import java.util.List;
import java.util.UUID;

import project_oodd.ecom.model.User;
import project_oodd.ecom.dto.UserDTO;

public interface UserService {

	List<UserDTO> getUsers();

	UserDTO getUserById(UUID id);

	UserDTO createUser(User user);

	UserDTO updateUser(UUID id, User user);

	void deleteUser(UUID user);
}
