package org.safari.platform.common.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String algorithmName = "md5";
	private static final int hashIterations = 2;

	public static void encryptPassword(String password) {
		String salt = randomNumberGenerator.nextBytes().toHex();
		salt=salt.substring(0,16);
		System.out.println(salt);
		String newPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(salt), hashIterations).toHex();
		System.out.println(newPassword);
	}

	public static void main(String[] args) {
		encryptPassword("123456");
	}
}
