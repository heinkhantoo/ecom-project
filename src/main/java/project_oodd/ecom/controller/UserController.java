package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import project_oodd.ecom.dto.UserDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.User;
import project_oodd.ecom.service.UserService;
import project_oodd.ecom.util.ApiResponse;
import project_oodd.ecom.util.Role;
import project_oodd.ecom.util.RoleRestriction;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll(@AuthenticationPrincipal User user) {
		RoleRestriction.restrictTo(user, Role.ADMIN);
		List<UserDTO> users = userService.getUsers();
		Map<String, Object> data = Map.of("data", users);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", users.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getUserByEmail(@AuthenticationPrincipal User user, @PathVariable String id) {
		RoleRestriction.restrictTo(user, Role.ADMIN);
		UserDTO u = userService.getUserById(id);
		Map<String, Object> data = Map.of("data", u);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createUser(@AuthenticationPrincipal User user,@Valid @RequestBody User body) {
		RoleRestriction.restrictTo(user, Role.ADMIN);
		if (!body.passwordMatch()) {
			throw new AppException("Passwords do not match", 401);
		}
		UserDTO u = userService.createUser(body);
		Map<String, Object> data = Map.of("data", u);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateUser(@AuthenticationPrincipal User user, @PathVariable String id, @RequestBody User body){
		
		RoleRestriction.restrictTo(user, Role.ADMIN);
		
		UserDTO u = userService.updateUser(id, body);
		Map<String, Object> data = Map.of("data", u);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal User user, @PathVariable String id){
		
		RoleRestriction.restrictTo(user, Role.ADMIN);
		
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
