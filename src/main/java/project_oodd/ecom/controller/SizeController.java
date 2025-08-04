package project_oodd.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project_oodd.ecom.dto.SizeDTO;
import project_oodd.ecom.model.Size;
import project_oodd.ecom.service.SizeService;
import project_oodd.ecom.util.ApiResponse;

@RestController
@RequestMapping("/api/sizes")
public class SizeController {
	@Autowired
	private SizeService sizeService;

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAll() {
		List<SizeDTO> colors = sizeService.getSizes();
		Map<String, Object> data = Map.of("data", colors);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", colors.size(), data);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getColorById(@PathVariable String id) {

		SizeDTO color = sizeService.getSizeById(id);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Map<String, Object>>> createColor(@RequestBody Size body) {

		SizeDTO color = sizeService.createSize(body);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> updateColor(@PathVariable String id,
			@RequestBody Size body) {
		SizeDTO color = sizeService.updateSize(id, body);
		Map<String, Object> data = Map.of("data", color);

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("success", data);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
		sizeService.deleteSize(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
