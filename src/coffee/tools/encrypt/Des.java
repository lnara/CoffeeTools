package coffee.tools.encrypt;


import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Des {

	private final static String DES = "DES";

	public static String JA(String data) throws Exception {
		byte[] bt = encrypt(data.getBytes(), IcJAJE.CST.getK2().getBytes());
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}

	public static String JE(String data) throws IOException,
			Exception {
		if (data == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = decoder.decodeBuffer(data);
		byte[] bt = decrypt(buf,IcJAJE.CST.getK2().getBytes());
		return new String(bt);
	}

	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}
	
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}
	
	
}