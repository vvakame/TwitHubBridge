package net.vvakame.twithubbridge.helper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.geronimo.mail.util.Hex;

public class EncryptUtil {
	private static final String ALGORITHM = "Blowfish";

	public static String encrypt(String key, String text)
			throws IllegalBlockSizeException, InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			BadPaddingException, NoSuchPaddingException {
		SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, sksSpec);
		byte[] encrypted = cipher.doFinal(text.getBytes());

		return new String(Hex.encode(encrypted));

	}

	public static String decrypt(String key, String encryptedStr)
			throws IllegalBlockSizeException, InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			BadPaddingException, NoSuchPaddingException {

		byte[] encrypted = Hex.decode(encryptedStr);
		SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, sksSpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return new String(decrypted);
	}
}
