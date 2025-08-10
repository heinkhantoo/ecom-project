package project_oodd.ecom.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import project_oodd.ecom.dto.UserDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.User;
import project_oodd.ecom.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getUsers() {
		List<User> user = userRepository.findAll();
		return convertToDTO(user);
	}

	public UserDTO getUserById(String code) {
		User user = userRepository.findByUserCodeIgnoreCase(code)
				.orElseThrow(() -> new AppException("User doesn't exist!", 404));
		return convertToDTO(user);
	}

	public UserDTO createUser(User data) {
		User user = new User();
		user.setEmail(data.getEmail());
		user.setName(data.getName());
		user.setPhno(data.getPhno());
		user.setRole(data.getRole());
		user.setPassword(data.getPassword());
		user.setConfirmPassword(data.getConfirmPassword());

		return convertToDTO(userRepository.save(user));
	}

	public UserDTO updateUser(String code, User data) {
		User user = userRepository.findByUserCodeIgnoreCase(code)
				.orElseThrow(() -> new AppException("User doesn't exist!", 404));
		if (data.getEmail() != null)
			user.setEmail(data.getEmail());
		if (data.getName() != null)
			user.setName(data.getName());
		if (data.getPhno() != null)
			user.setPhno(data.getPhno());
		if (data.getRole() != null)
			user.setRole(data.getRole());

		return convertToDTO(userRepository.save(user));
	}
	
	public void deleteUser(String code) {
		User user = userRepository.findByUserCodeIgnoreCase(code).orElseThrow(() -> new AppException("User doesn't exist!", 404));
		userRepository.delete(user);
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

	public List<UserDTO> convertToDTO(List<User> users) {
		if (users == null || users.isEmpty()) {
			return Collections.emptyList();
		}

		return users.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

}
