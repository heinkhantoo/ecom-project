package project_oodd.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project_oodd.ecom.dto.UserDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.User;
import project_oodd.ecom.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO signUp(User data) {
		return convertToDTO(userRepository.save(data));
	}

	public UserDTO login(String email, String password) {

		if (email == null || password == null) {
			throw new AppException("Please provide emaill and password", 400);

		}

		User dbUser = userRepository.findByEmailIgnoreCase(email)
				.orElseThrow(() -> new AppException("User doesn't exist with this email. Please signup first", 404));

		if (dbUser.correctPassword(password, dbUser.getPassword()) == false)
			throw new AppException("Incorrect password", 401);

		return convertToDTO(dbUser);
	}

	public UserDTO convertToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUserCode(user.getUserCode());
		dto.setEmail(user.getEmail());
		dto.setName(user.getName());
		dto.setPhno(user.getPhno());
		dto.setRole(user.getRole());
		dto.setActive(user.isActive());

		return dto;
	}
}
