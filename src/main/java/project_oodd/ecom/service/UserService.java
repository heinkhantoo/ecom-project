package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.model.User;
import project_oodd.ecom.dto.UserDTO;

public interface UserService {

	List<UserDTO> getUsers();

	UserDTO getUserById(String code);

	UserDTO createUser(User user);

	UserDTO updateUser(String email, User user);

	void deleteUser(String user);
}
