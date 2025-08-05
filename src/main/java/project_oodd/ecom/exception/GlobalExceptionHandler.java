package project_oodd.ecom.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import project_oodd.ecom.util.ApiResponse;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse<Map<String, Object>>> handleDataIntegrityViolation(
			DataIntegrityViolationException ex) {
		String message = "Data integrity violation";
		Throwable rootCause = ex.getRootCause();

		if (rootCause != null && rootCause.getMessage() != null) {
			String rootMsg = rootCause.getMessage().toLowerCase();

			if (rootMsg.contains("unique key")) {
				message = "Duplicate entry. The value you're trying to save already exists.";
			} else if (rootMsg.contains("reference")) {
				message = "Request failed due to related data. Please remove references before deleting.";
			} else if (rootMsg.contains("null")) {
				message = "Required fields cannot be empty.";
			}
		}

		AppException appException = new AppException(message, 400);
		return handleAppException(appException);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, Object>>> handleArgumentValidation(
			MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldError().getDefaultMessage();

		AppException appException = new AppException(message, 400);
		return handleAppException(appException);
	}

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ApiResponse<Map<String, Object>>> handleAppException(AppException ex) {

		ApiResponse<Map<String, Object>> response = new ApiResponse<>(ex.getStatus(), ex.getMessage());

		return ResponseEntity.status(HttpStatus.valueOf(ex.getStatusCode())).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Map<String, Object>>> handleGenericException(Exception ex) {

		ApiResponse<Map<String, Object>> response = new ApiResponse<>("error",
				"Something went wrong! Please contact service provider");
		System.out.println(ex);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}