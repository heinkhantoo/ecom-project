package project_oodd.ecom.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import project_oodd.ecom.dto.UserDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.User;
import project_oodd.ecom.security.Jwt;
import project_oodd.ecom.service.AuthService;
import project_oodd.ecom.util.ApiResponse;

@RestController
@RequestMapping("/api/users")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private Jwt jwt;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<Map<String, Object>>> signup(@Valid @RequestBody User body) {

		if (!body.passwordMatch()) {
			throw new AppException("Passwords do not match", 401);
		}

		UserDTO user = authService.signUp(body);
		Map<String, Object> data = Map.of("data", user);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody User body,
			HttpServletResponse res) {
		String email = body.getEmail();
		String password = body.getPassword();

		UserDTO user = authService.login(email, password);
		String token = jwt.generateToken(user.getUserCode());

		Cookie cookie = new Cookie("jwt", token);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
		res.addCookie(cookie);

		Map<String, Object> data = Map.of("token", token, "data", user);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<Map<String, Object>>> logout(HttpServletResponse res) {
		Cookie cookie = new Cookie("jwt", "loggedout");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(5);
		cookie.setPath("/");
		res.addCookie(cookie);

		System.out.println(res.toString());

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", "You have successfully logout");
		return ResponseEntity.ok(response);
	}
}
