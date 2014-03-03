package coffee.tools.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64{

	public static String JE(String key) throws Exception {
		byte[] b = (new BASE64Decoder().decodeBuffer(key));
		String returnStr = new String(b);
		return returnStr;
	}

	public static String JA(String data) throws Exception {
		byte[] key = data.getBytes();
		return (new BASE64Encoder()).encodeBuffer(key);
	}
}
