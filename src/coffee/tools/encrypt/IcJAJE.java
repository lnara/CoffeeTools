package coffee.tools.encrypt;

public class IcJAJE {

	// 内部常量类
	static class CST{
		private static  byte[] K1Arr = "i^)(9-p35@%)$YE%^&5(j.&^&o(*T)$Y%!#0@3fuckyi0*GpG@=+@j.&6^)(0-=+".getBytes();
		private static String K2Str = "i^)(9-p35@%!@#$shitaa%^&*()_+@j.&6^)(0-=+";
		public static byte[] getK1(){
			return K1Arr;
		}
		public static String getK2(){
			return K2Str;
		}
	}
	
	
	public static String endecrypt(String data) {
		int keylen, pos;
		pos = 0;
		keylen = CST.getK1().length;
		byte[] dataByteArr = data.getBytes();
		byte[] after = new byte[dataByteArr.length];
		for (int i = 0; i < dataByteArr.length; i++) {
			after[i] = (byte) (dataByteArr[i] ^ CST.getK1()[pos]);
			pos++;
			if (keylen == pos)
				pos = 0;
		}
		return new String(after);
	}

	public static String endecrypt2(String data, boolean type) {
		String returnStr = "";
		int keylen; // key串长度
		int pos; // key串 当前位置
		pos = 0;
		keylen = CST.getK1().length;
		byte[] dataByteArr = data.getBytes();
		byte[] after = new byte[dataByteArr.length];
		for (int i = 0; i < dataByteArr.length; i++) {
			if (type) // 加
				if (dataByteArr[i] == 127)
					after[i] = 0;
				else
					after[i] = (byte) (dataByteArr[i] + 1);
			else // 解
			if (dataByteArr[i] == 0)
				after[i] = 127;
			else
				after[i] = (byte) (dataByteArr[i] - 1);
			pos++;
			if (keylen == pos)
				pos = 0;
		}

		returnStr = new String(after);
		return returnStr;
	}
}
