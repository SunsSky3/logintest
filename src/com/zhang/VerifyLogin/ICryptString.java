package com.zhang.VerifyLogin;

/**
 * interface for Encryption-Class
 * see: http://openmeetings.apache.org/CustomCryptMechanism.html
 * 
 * @author sebastianwagner
 *
 */

public interface ICryptString {
	
	/**
	 * create a pass phrase
	 * 
	 * @param userGivenPass
	 * @return
	 */
	public String createPassPhrase(String userGivenPass);
	
	/**
	 * verify a password
	 * 
	 * @param passGiven
	 * @param passwdFromDb
	 * @return
	 */
	public Boolean verifyPassword(String passGiven, String passwdFromDb);

}