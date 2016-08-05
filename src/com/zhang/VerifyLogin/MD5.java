package com.zhang.VerifyLogin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5 {
	public static String do_checksum(String data) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		md5.update(data.getBytes(), 0, data.length());

		return Hex.encodeHexString(md5.digest());
	}
}