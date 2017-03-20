package ru.nnmotors.eip.business.api.model.exception;

public class UserAccessCheckException extends SecurityCheckException {
	private static final long serialVersionUID = 4268046650473353914L;
	
	public UserAccessCheckException(Long userId, String message, Throwable cause) {
		super(message, cause);
		this.userId = userId;
	}
	
	public UserAccessCheckException(Long userId, String message) {
		super(message);
		this.userId = userId;
	}

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
