package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import project_oodd.ecom.dto.ProductDTO;
import project_oodd.ecom.dto.UserDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.User;
import project_oodd.ecom.service.UserService;
import project_oodd.ecom.util.ApiResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll() {
		List<UserDTO> users = userService.getUsers();
		Map<String, Object> data = Map.of("data", users);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", users.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getUserByEmail(@PathVariable String id) {

		UserDTO user = userService.getUserById(id);
		Map<String, Object> data = Map.of("data", user);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createUser(@Valid @RequestBody User body) {

		if (!body.passwordMatch()) {
			throw new AppException("Passwords do not match", 401);
		}
		UserDTO user = userService.createUser(body);
		Map<String, Object> data = Map.of("data", user);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateUser(@PathVariable String id, @RequestBody User body){
		UserDTO user = userService.updateUser(id, body);
		Map<String, Object> data = Map.of("data", user);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id){
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
