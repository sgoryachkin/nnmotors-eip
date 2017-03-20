package ru.nnmotors.eip.business.api.model.exception;

public class SecurityCheckException extends RuntimeException {
	
    public SecurityCheckException(String message) {
        super(message);
    }
	
    public SecurityCheckException(String message, Throwable cause) {
        super(message, cause);
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = -6112090355879446761L;

}
