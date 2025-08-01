package project_oodd.ecom.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import project_oodd.ecom.util.ApiResponse;

@RestController
public class FallBackController {
	
	@RequestMapping("/**")
	public ResponseEntity<ApiResponse<Map<String, Object>>> handleInvalidURL(HttpServletRequest request){
		ApiResponse<Map<String, Object>> response = new ApiResponse<>("fail", "The requested path <" + request.getRequestURI().toString() + "> is invalid! Cannot be found on this server");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	

}
