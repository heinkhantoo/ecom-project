package project_oodd.ecom.service;

import project_oodd.ecom.dto.UserDTO;
import project_oodd.ecom.model.User;

public interface AuthService {
	public UserDTO signUp(User user);
	public UserDTO login(String email, String password);

}
