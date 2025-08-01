package project_oodd.ecom.util;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
	    private String status;
	    private Integer results;
	    private String message;
	    private T data;   


	    public ApiResponse(String status, T data) {
	        this.status = status;
	        this.data = data;
	    }

	    public ApiResponse(String status, String message) {
	        this.status = status;
	        this.message = message;
	    }

	    public ApiResponse(String status, int results, T data) {
	        this.status = status;
	        this.results = results;
	        this.data = data;
	    }
	    
	    public ApiResponse(String status, int results, String message, T data) {
	        this.status = status;
	        this.results = results;
	        this.message = message;
	        this.data = data;
	    }

		public String getStatus() {
			return status;
		}

		public Integer getResults() {
			return results;
		}

		public String getMessage() {
			return message;
		}

		public T getData() {
			return data;
		}

	    
	}
