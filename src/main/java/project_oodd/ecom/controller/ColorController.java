package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project_oodd.ecom.dto.ColorDTO;
import project_oodd.ecom.model.Color;
import project_oodd.ecom.service.ColorService;
import project_oodd.ecom.util.ApiResponse;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
	@Autowired
	private ColorService colorService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll() {
		List<ColorDTO> colors = colorService.getColors();
		Map<String, Object> data = Map.of("data", colors);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", colors.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getColorById(@PathVariable String id) {

		ColorDTO color = colorService.getColorById(id);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createColor(@RequestBody Color body) {

		ColorDTO color = colorService.createColor(body);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateColor(@PathVariable String id,
			@RequestBody Color body) {
		ColorDTO color = colorService.updateColor(id, body);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
		colorService.deleteColor(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
