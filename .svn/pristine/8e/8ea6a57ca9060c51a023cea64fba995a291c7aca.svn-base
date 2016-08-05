package com.zhang.VerifyLogin;

import java.security.NoSuchAlgorithmException;

public class MD5Implementation implements ICryptString {
	/*
	 * (non-Javadoc)
	 * @see org.apache.openmeetings.utils.crypt.ICryptString#createPassPhrase(java.lang.String)
	 */
	public String createPassPhrase(String userGivenPass) {
		String passPhrase = null;
		try {
			passPhrase = MD5.do_checksum(userGivenPass);	
					
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return passPhrase;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.openmeetings.utils.crypt.ICryptString#verifyPassword(java.lang.String, java.lang.String)
	 */
	public Boolean verifyPassword(String passGiven, String passwdFromDb) {
		return (passwdFromDb.equals(createPassPhrase(passGiven)));
	}
	
}
