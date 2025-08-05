package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import project_oodd.ecom.dto.ColorDTO;
import project_oodd.ecom.model.Color;
import project_oodd.ecom.model.User;
import project_oodd.ecom.service.ColorService;
import project_oodd.ecom.util.ApiResponse;
import project_oodd.ecom.util.Role;
import project_oodd.ecom.util.RoleRestriction;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
	@Autowired
	private ColorService colorService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll(@AuthenticationPrincipal User user) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		
		List<ColorDTO> colors = colorService.getColors();
		Map<String, Object> data = Map.of("data", colors);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", colors.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getColorById(@AuthenticationPrincipal User user, @PathVariable String id) {

		ColorDTO color = colorService.getColorById(id);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createColor(@AuthenticationPrincipal User user, @RequestBody Color body) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		
		ColorDTO color = colorService.createColor(body);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateColor(@AuthenticationPrincipal User user, @PathVariable String id,
			@RequestBody Color body) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		
		ColorDTO color = colorService.updateColor(id, body);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal User user, @PathVariable String id) {

		RoleRestriction.restrictTo(user, Role.ADMIN, Role.MANAGER);
		
		colorService.deleteColor(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
