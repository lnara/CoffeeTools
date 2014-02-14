package outer;

public class OtherUseless {

	/**
	 *  翻转数组
	 * 
	 * @param args
	 * @author coffee
	 */
	public static <K> void resoveArray(K arr[]) {
		int start_index = 0;
		int end_index = arr.length - 1;
		K temp = null;
		for (;;) {
			if (start_index >= end_index)
				break;
			temp = arr[start_index];
			arr[start_index] = arr[end_index];
			arr[end_index] = temp;
			end_index--;
			start_index++;
		}
	}
	
	/**
	 * 数字格式化成带逗号的形式
	 *  // 其实有 NumberFormet 类已经搞定 了
	 *	// baidu: java 数字格式化
	 * @param floatStr
	 * @return
	 * @author coffee
	 */
	public static String addSeparation2Float(String floatStr) {
		String first = floatStr.split("\\.")[0];
		if (first.length() <= 3 || floatStr.split("\\.").length != 2)
			return floatStr;

		StringBuffer sbRet = new StringBuffer();

		int lDaoxu = first.length() / 3;
		if (!(first.length() % 3 == 0)) {
			lDaoxu++;
		}
		String[] daoxu = new String[lDaoxu];

		int itemp = 0;
		for (int i = 0; i < first.length(); i += 3) {
			itemp = first.length() - 3 - i;
			daoxu[i / 3] = first.substring(itemp < 0 ? 0 : itemp,
					first.length() - i);
		}

		for (int j = lDaoxu; j > 0; j--) {
			sbRet.append(daoxu[j - 1]);
			if (j != 1)
				sbRet.append(",");
		}

		return sbRet.toString() + "." + floatStr.split("\\.")[1];
	}

	
	public static void main(String[] args) {
		System.out.println(OtherUseless.addSeparation2Float("10002369.2324"));
		
	}


}
