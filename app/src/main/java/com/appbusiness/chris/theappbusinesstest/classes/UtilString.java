package com.appbusiness.chris.theappbusinesstest.classes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Chris on 12/08/2016.
 */
public class UtilString {

	public static String md5Hash(String s) throws NoSuchAlgorithmException {
		final String md5 = "MD5";

		MessageDigest m = MessageDigest.getInstance(md5);
		m.update(s.getBytes(), 0, s.length());
		return new BigInteger(1, m.digest()).toString(16);
	}

}
