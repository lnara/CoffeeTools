package coffee.dynamicProxy;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class MakeFileUtil {
	public static void createFile(String pathstr) throws IOException {
		// 创建多级目录
		String path = pathstr;
		// 为指定字符串构造一个 string tokenizer。 "/"字符是分隔标记的分隔符。分隔符字符本身不作为标记。
		StringTokenizer st = new StringTokenizer(path, "/");
		String path1 = st.nextToken() + "/";
		String path2 = path1;
		while (st.hasMoreTokens()) {
			path1 = st.nextToken() + "/";
			path2 += path1;
			File inbox = new File(path2);
			if (!inbox.exists())
				inbox.mkdir();
		}
	}
}