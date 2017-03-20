package ru.nnmotors.eip.business.api.service;

public interface SecurityService {
	
	/**
	 * throw SecurityCheckException if the userId not "admin" and not current logged user
	 * 
	 * @param userId
	 */
	void checkUser(Long userId);
	
	/**
	 * return false if checkUser thrown SecurityCheckException
	 * 
	 * @param userId
	 * @return
	 */
	boolean isCheckUser(Long userId);

}
