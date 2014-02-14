package coffee.tools;

/**
 * 常量属性 
 * @author coffee 0.0
 */
public class CST {

	/** 文件分隔符(在 UNIX 系统中是 / , windows 系统中是 \ ) */
	public static String FILE_SEPARATOR;
	/** 路径分隔符(在 UNIX 系统中是 : , windows 系统中是 ; ) */
	public static String PATH_SEPARATOR;
	/** 行分隔符(换行分隔符) */
	public static String LINE_SEPARATOR;
	/** 操作系统的名称  例:Windows 7 */
	public static String OS_NAME;
	/** 操作系统的架构  例:x86 */
	public static String OS_ARCH;
	/** 操作系统的版本  例:6.1 */
	public static String OS_VERSION;

	// 初始化
	static {
		FILE_SEPARATOR = System.getProperty("file.separator");
		PATH_SEPARATOR = System.getProperty("path.separator");
		LINE_SEPARATOR = System.getProperty("line.separator");

		OS_NAME = System.getProperty("os.name");
		OS_ARCH = System.getProperty("os.arch");
		OS_VERSION = System.getProperty("os.version");
	}

	
	/** String 空 ""  */
	public static String NULL = "";
	
	
	
	// 测试
	public static void main(String[] args) {
		System.out.println(CST.FILE_SEPARATOR);
		System.out.println(CST.PATH_SEPARATOR);
		System.out.println(CST.LINE_SEPARATOR);
		System.out.println(CST.OS_NAME);
		System.out.println(CST.OS_ARCH);
		System.out.println(CST.OS_VERSION);
	}
	
}
