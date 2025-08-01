package project_oodd.ecom.exception;

@SuppressWarnings("serial")
public class AppException extends RuntimeException {
	
	    private final int statusCode;
	    private final String status;
	    private final boolean isOperational;

	    public AppException(String message, int statusCode) {
	        super(message);
	        this.statusCode = statusCode;
	        this.status = (statusCode >= 400 && statusCode < 500) ? "fail" : "error";
	        this.isOperational = true;
	    }

	    public int getStatusCode() {
	        return statusCode;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public boolean isOperational() {
	        return isOperational;
	    }
	}
