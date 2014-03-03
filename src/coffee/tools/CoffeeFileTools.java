package coffee.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

/**
 * 文件相关工具类<br>
 * 其中一些方法与 apache.commons.io.FileUtils 重复<br>
 * 用不用随意
 * 
 * @author coffee
 */
public class CoffeeFileTools {

	/** 如果完全匹配某个字符集检测算法, 则该属性保存该字符集的名称. */
	private static String encoding = null;
	private static boolean found = false;

	/**
	 * 判断文件or文件夹是否存在
	 * 
	 * @param file
	 *            文件实体
	 * @return true or false : 存在 or 不存在
	 */
	public static boolean fileExist(File file) {
		return file == null ? false : file.exists();
	}

	/**
	 * 判断文件or文件夹是否存在
	 * 
	 * @param filePath
	 *            文件全路径
	 * @return true or false : 存在 or 不存在
	 */
	public static boolean fileExist(String filePath) {
		File file = new File(filePath);
		return file == null ? false : file.exists();
	}

	/**
	 * 重命名文件
	 * 
	 * @param file
	 *            要重命名的 File 实体
	 * @param newName
	 *            新名字(只是文件名,不是全路径名)
	 * @return true or false : 重命名 成功 or 失败
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public static boolean renameFile(File file, String newName)
			throws FileNotFoundException, Exception {
		if (!fileExist(file)) {
			throw new FileNotFoundException("指定的文件不存在,请检查!!!");
		} else {
			File newFile = new File(file.getParent() + "/" + newName);
			if (newFile.exists()) {
				// 目录下已经有了
				throw new Exception("已经存在!!");
			} else {
				file.renameTo(newFile);
			}
		}
		return true;
	}

	/**
	 * 重命名文件
	 * 
	 * @param filePath
	 *            要重命名的文件的路径
	 * @param oldName
	 *            原文件名
	 * @param newName
	 *            新文件名
	 * @return true or false : 重命名 成功 or 失败
	 * @throws Exception
	 */
	public static boolean renameFile(String filePath, String oldName,
			String newName) throws Exception {

		if (!fileExist(filePath + oldName)) {
			throw new FileNotFoundException("### INFO "+new Date().toString()+" Message:"+ 
					"指定的文件 " + filePath + oldName
					+ "未找到");
		} else {
			File oldFile = new File(filePath + oldName);
			renameFile(oldFile, newName);
		}
		return true;
	}

	/**
	 * 删除文件(只删除文件,如果 不是一个文件,将返回false,如果 想删除 文件夹,请使用 deleteFolder() 或不区分 文件 文件夹的
	 * delete()) <br>
	 * 说明 :如果传递的路径不是一个文件,则返回删除失败
	 * 
	 * @param filePath
	 *            文件的路径
	 * @return true or false 删除 成功 or 失败
	 * @throws FileNotFoundException
	 */
	public static boolean deleteFile(String filePath)
			throws FileNotFoundException {
		File deleteFile = new File(filePath);

		if (deleteFile.isFile()) {
			if (!fileExist(deleteFile))
				throw new FileNotFoundException("### INFO "+new Date().toString()+" Message:"+
						"指定的文件" + filePath + "未找到");
			return deleteFile.delete();
		} else {
			return false;
		}
	}

	/**
	 * 删除文件<br>
	 * 说明 :如果传递的路径不是一个文件,则返回删除失败
	 * 
	 * @param file
	 *            要删除 的文件对象
	 * @return true or false 删除 成功 or 失败
	 * @throws FileNotFoundException
	 */
	public static boolean deleteFile(File file) throws FileNotFoundException {
		if (file.isFile()) {
			if (!fileExist(file))
				throw new FileNotFoundException();
			return file.delete();
		} else {
			return false;
		}
	}

	/**
	 * 删除文件夹<br>
	 * 
	 * @param folderPath
	 *            文件夹路径
	 * @return true or false 删除 成功 or 失败
	 * @throws FileNotFoundException
	 */
	public static boolean deleteFolder(String folderPath)
			throws FileNotFoundException {

		File folder = new File(folderPath);

		if (!folder.isDirectory() || !fileExist(folderPath))
			throw new FileNotFoundException();

		File[] files = folder.listFiles();

		boolean flag = true;
		int result = 0;

		for (File f : files) {
			if (f.isDirectory()) {
				flag = deleteFolder(f.getAbsolutePath());
			} else {
				flag = f.delete();
			}
			result += flag ? 0 : 1;
		}

		return folder.delete() == true ? (result == 0 ? true : false) : false;
	}

	/**
	 * 删除文件夹<br>
	 * 
	 * @param folder
	 *            要删除 的文件夹对象
	 * @return true or false 删除 成功 or 失败
	 * @throws FileNotFoundException
	 */
	public static boolean deleteFolder(File folder)
			throws FileNotFoundException {
		return deleteFolder(folder.getAbsolutePath());
	}

	/**
	 * 删除文件 或文件夹,不区分 是文件还是文件夹,如果 是文件夹的话会删除 该 文件夹下的所有文件
	 * 
	 * @param path
	 *            要删除的文件夹路径
	 * @return true or false 删除 成功 or 失败
	 * @throws FileNotFoundException
	 */
	public static boolean delete(String path) throws FileNotFoundException {
		File xx = new File(path);
		return xx.isFile() ? deleteFile(xx) : deleteFolder(xx);
	}

	/**
	 * 除文件 或文件夹,不区分 是文件还是文件夹,如果 是文件夹的话会删除 该 文件夹下的所有文件
	 * 
	 * @param xx
	 *            要删除 的对象
	 * @return true or false 删除 成功 or 失败
	 * @throws FileNotFoundException
	 */
	public static boolean delete(File xx) throws FileNotFoundException {
		return xx.isFile() ? deleteFile(xx) : deleteFolder(xx);
	}

	/**
	 * 创建文件夹<br>
	 * 注意:如果上层文件夹不存在 ,则会自动创建文件夹.如果 当前要创建 的文件夹已经存在 ,则返回true <br>
	 * <h3>
	 * <ol>
	 * 注意相对路径问题
	 * </ol>
	 * </h3>
	 * 
	 * @param folder
	 *            要创建 的对象
	 * @return true or false 创建成功 or 失败
	 */
	public static boolean createFolder(File folder) {
		boolean b = true;
		if (!fileExist(folder))
			if (!folder.mkdirs()) {
				b = false;
				System.out.println("### INFO "+new Date().toString()+" Message:"
						+"创建文件夹" + folder.getAbsolutePath()
						+ " 出错,请检查文件夹名称,路径 是否正确! ");
			}
		return b;
	}

	/**
	 * 创建文件夹<br>
	 * 注意:如果上层文件夹不存在 ,则会自动创建文件夹. 如果 当前要创建 的文件夹已经存在 ,则返回true <br>
	 * <h3>
	 * <ol>
	 * 注意相对路径问题
	 * </ol>
	 * </h3>
	 * 
	 * @param folderPath
	 *            要创建文件夹的路径
	 * @return true or false 创建成功 or 失败
	 */
	public static boolean createFolder(String folderPath) {
		File folder = new File(folderPath);
		return createFolder(folder);
	}

	/**
	 * 创建文件<br>
	 * 注意:如果上层文件夹不存在 ,则会自动创建文件夹
	 * 
	 * @param filePath
	 *            文件的路径全名,包含 [文件名.扩展名]
	 * @return true or false 创建成功 or 失败
	 * @throws Exception
	 */
	public static boolean createFile(String filePath) throws Exception {
		File file = new File(filePath);
		return createFile(file);
	}

	/**
	 * 创建文件<br>
	 * 注意:如果上层文件夹不存在 ,则会自动创建文件夹
	 * 
	 * @param file
	 *            要创建 的文件对象
	 * @return true or false 创建成功 or 失败
	 */
	public static boolean createFile(File file){
		boolean b = true;

		if (!fileExist(file)) {
			// 创建父目录
			if (!fileExist(file.getParentFile())) {
				if (!createFolder(file.getParentFile())) {
					return false;
				}
			}
			// 创建文件
			try {
				b = file.createNewFile();
			} catch (IOException e) {
				b = false;
				System.out.println("### INFO "+new Date().toString()+" Message:"+
						"创建文件" + file.getAbsolutePath()
						+ " 出错,请检查文件名,路径 是否正确");
			}
		}

		return b;
	}

	/**
	 * 获得某一文件的行数
	 * 
	 * @param file
	 *            文件对象
	 * @return 文件的行数
	 * @throws FileNotFoundException
	 */
	public static int getFileLineCount(File file) throws FileNotFoundException {
		if (!fileExist(file))
			throw new FileNotFoundException("### INFO "+new Date().toString()+" Message:"+
					"文件 " + file.getAbsolutePath()
					+ "未找到,请检查");

		FileReader fr = null;
		BufferedReader br = null;
		int count = 0;
		try {
			fr = new FileReader(file.getAbsolutePath());
			br = new BufferedReader(fr);
			
			while ((br.readLine()) != null) {
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * 获取文件的行数
	 * 
	 * @param filePath
	 *            文件的路径
	 * @return 文件的行数
	 * @throws FileNotFoundException
	 */
	public static int getFileLineCount(String filePath)
			throws FileNotFoundException {
		return getFileLineCount(new File(filePath));
	}

	/**
	 * 读取文件内容，可指定从某行(startLineNum)读取到某行(endLineNum)<br>
	 * 说明：<br>
	 * 1.结束行数须大于开始行数且小于总行数,当结束行数为0时,表示读取到文件结尾<br>
	 * 2.开始行数必须大于0,且小于结束行数,当结束行数为0时除外<br>
	 * 3.当 charset 为 null 时 表示不指定字符 集，让程序自行判断
	 * 
	 * @param file
	 *            要读取的文件
	 * @param startLineNum
	 *            开始的行数
	 * @param endLineNum
	 *            结束的行数
	 * @param charset
	 *            文件的字符集
	 * @return 每一行为一个字符串的 集合
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public static List<String> readFileByLine(File file, int startLineNum,
			int endLineNum, String charset) {
		LineNumberReader reader = null;
		List<String> result = new ArrayList<String>();

		if (!(fileExist(file) && file.isFile()))
			return null;
		if (startLineNum <= 0 || (startLineNum > endLineNum && endLineNum != 0))
			return null;

		try {

			int sumLineNum = getFileLineCount(file);

			if (endLineNum <= 0)
				endLineNum = sumLineNum;
			if (isNull(charset))
				charset = getFileCharset(file);
			reader = new LineNumberReader(new InputStreamReader(
					new FileInputStream(file), charset));
			String tempString = null;
			int line = 1;

			while ((tempString = reader.readLine()) != null) {
				if (line >= startLineNum
						&& line <= (endLineNum >= sumLineNum ? sumLineNum
								: endLineNum)) {
					result.add(tempString);
				}
				if (line >= endLineNum)
					break;
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		
		return result;
	}

	/**
	 * 按行读取文件<br>
	 * 注意：文件如果过大，请考虑内存的利用，可调用 <br>
	 * readFileByLine(File file, int startLineNum, int endLineNum, String
	 * charset)<br>
	 * 进行分次读取
	 * 
	 * @param file
	 *            要读取的文件
	 * @param charset
	 *            该 文件的字符集，如果为null 则为程序自动指定一个字符集
	 * @return List 
	 */
	public static List<String> readFileByLine(File file, String charset) {
		return readFileByLine(file, 1, 0, charset);
	}

	public static List<String> readFileByLine(File file) {
		return readFileByLine(file, null);
	}

	/**
	 * 获取文件的编码
	 * 
	 * @param file
	 *            目标文件
	 * @return 文件的编码
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileCharset(File file)
			throws FileNotFoundException, IOException {
		return geestFileEncoding(file, new nsDetector());
	}

	/**
	 * 获取文件的编码
	 * 
	 * @param file
	 *            目标文件
	 * @param languageHint
	 * <br>
	 *            语言提示区域代码 eg：<br>
	 *            1 : Japanese; 2 : Chinese; 3 : Simplified Chinese;<br>
	 *            4 : Traditional Chinese; 5 : Korean; 6 : Dont know (default)
	 * @return String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileCharset(File file, int languageHint)
			throws FileNotFoundException, IOException {
		return geestFileEncoding(file, new nsDetector(languageHint));
	}

	/**
	 * 获取文件的编码
	 * 
	 * @param filePath
	 *            文件的路径
	 * @return 文件的编码
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileCharset(String filePath)
			throws FileNotFoundException, IOException {
		return geestFileEncoding(new File(filePath), new nsDetector());
	}

	/**
	 * 获取文件的编码
	 * 
	 * @param filePath
	 *            文件的路径 语言提示区域代码 eg：<br>
	 *            1 : Japanese; 2 : Chinese; 3 : Simplified Chinese;<br>
	 *            4 : Traditional Chinese; 5 : Korean; 6 : Dont know (default)
	 * @return 文件的编码
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileCharset(String filePath, int languageHint)
			throws FileNotFoundException, IOException {
		return geestFileEncoding(new File(filePath), new nsDetector(
				languageHint));
	}

	// 私有方法，用于检测文件的编码
	private static String geestFileEncoding(File file, nsDetector det)
			throws FileNotFoundException, IOException {

		det.Init(new nsICharsetDetectionObserver() {
			public void Notify(String charset) {
				found = true;
				encoding = charset;
			}
		});

		BufferedInputStream imp = new BufferedInputStream(new FileInputStream(
				file));

		byte[] buf = new byte[1024];
		int len;
		boolean done = false;
		boolean isAscii = true;

		while ((len = imp.read(buf, 0, buf.length)) != -1) {
			if (isAscii)
				isAscii = det.isAscii(buf, len);
			if (!isAscii && !done)
				done = det.DoIt(buf, len, false);
		}
		det.DataEnd();

		if (isAscii) {
			encoding = "ASCII";
			found = true;
		}

		if (!found) {
			String prob[] = det.getProbableCharsets();
			if (prob.length > 0) {
				encoding = prob[0];
			} else {
				return null;
			}
		}
		return encoding;
	}

	/**
	 * 获取某一文件夹下的所有文件的总数
	 * 
	 * @param folder
	 *            目标文件夹
	 * @return 该文件夹下的文件总数
	 */
	public static long getFileNumberFromFolder(String folder,long fileNumber) {
		File targeFolder = new File(folder);
		if (!fileExist(targeFolder) || targeFolder.isFile())
			return fileNumber;
		File[] tempFile = targeFolder.listFiles();
		if (tempFile == null)
			return fileNumber;

		for (File f : tempFile) {
			if (f.isFile()) {
				fileNumber++;
			} else {
				fileNumber = getFileNumberFromFolder(f.getAbsolutePath(),fileNumber);
			}
		}
		
		return fileNumber;
	}

	/**
	 * 获得文件的大小,字节表示
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 返回文件的大小,单位kb,如果文件不存在返回null
	 */
	public static Double getFileByteSize(String filePath) {
		if (!fileExist(filePath))
			return null;
		else {
			File file = new File(filePath);
			double intNum = Math.ceil(file.length());
			return new Double(intNum);
		}
	}

	/**
	 * 判断某一文件是否为空
	 * 
	 * @param filePath
	 *            文件的路径字符串，e.g. "c:\cs.txt"
	 * @return 如果文件为空返回true, 否则返回false
	 * @throws IOException
	 */
	public static boolean FileIsNull(String filePath) throws IOException {
		boolean result = false;
		FileReader fr = new FileReader(filePath);
		if (fr.read() == -1) {
			result = true;
		} else {
		}
		fr.close();
		return result;
	}

	/**
	 * 获取某一文件夹下的所有文件对象 <br>
	 * 慎用，文件过多可能 会导致读取过慢
	 * 
	 * @param folder
	 * @return List
	 */
	public static List<File> getAllFileFromFolder(String folder) {
		List<File> list = new ArrayList<File>();
		getFilesFromFolder(folder, null, list);
		return list;
	}

	/**
	 * 获取某一文件夹下的符合要求的文件对象 <br>
	 * 慎用，文件过多可能 会导致读取过慢 <br>
	 * 说明 ：<br>
	 * likeFileName : 为 查询的筛选条件，类似Windows 里的通配符 <br>
	 * 例： "*a*.*" 则会查询出所有文件名带有 a 字母的文件 <br>
	 * "a??.*" 则会查询出文件名首字母是a且文件名是3个字符，扩展名不限的所有文件 <h1>请保证您输入的通配符是正确的！！！</h1><br>
	 * <br>
	 * <br>
	 * 
	 * @param folder
	 *            目标文件夹
	 * @param likeFileName
	 *            筛选条件
	 * @param resultList
	 *            返回的查询结果 List
	 */
	public static void getFilesFromFolder(String folder, String likeFileName,
			List<File> resultList) {
		File targeFolder = new File(folder);
		if (!fileExist(targeFolder) || targeFolder.isFile())
			return;
		File[] tempFile = targeFolder.listFiles();
		if (tempFile == null)
			return;

		if (isNull(likeFileName)) {
			likeFileName = "*";
		}
		// 把 传入的 * 和 ? 替换成 .* 和 .{N} N为？的个数　
		likeFileName = getLianXuQuestionMark(likeFileName.replace("*", ".*"));
		Pattern pattern = Pattern.compile(likeFileName);
		Matcher matcher = null;
		for (File f : tempFile) {
			if (f.isFile()) {
				matcher = pattern.matcher(f.getName());
				if (matcher.matches()) {
					resultList.add(f);
				}
			} else {
				getFilesFromFolder(f.getAbsolutePath(), likeFileName,
						resultList);
			}
		}
	}

	
	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}

	}
	
	/** 
	 * 将List里的String写到file中
	 * @param file 
	 * @param list
	 */
	public static boolean write(File file , List<String> list) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		String s = "";
		for(int i = 0; i < list.size();i++){
			s = list.get(i);
			writer.write(s);
		}
		writer.close();
		return true;
	}
	
	/** 
	 * 将List里的String写到file中
	 * @param file 
	 * @param list
	 */
	public static boolean write(String file , List<String> list) throws IOException{
		return write(new File(file),list);
	}
	
	
	/**
	 * 读取指定行号内容
	 */
	public static String readFileByLine(File file, int lineNum) {
		String returnStr = "";
		List<String> list = readFileByLine(file, lineNum, lineNum,null);
		if (list != null && list.size() > 0) {
			returnStr = list.get(0);
		}
		return returnStr;
	}
	
	/** 连续的 ? 替换成 .{N} N为?的个数 */
	private static String getLianXuQuestionMark(String s) {
		int firstIndex = s.indexOf("?");
		int endIndex = s.length();
		if (firstIndex > -1) {
			// 有 ？ 的时候
			for (int i = firstIndex; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c != '?') {
					endIndex = i;
					break;
				}
			}
			String temp1 = s.substring(0, firstIndex);
			String temp2 = s.substring(endIndex);
			s = temp1 + ".{" + (endIndex - firstIndex) + "}" + temp2;
			s = getLianXuQuestionMark(s);
		}
		return s;
	}

	/** 判断是否为空 "" 和 NULL */
	private static boolean isNull(String s) {
		return s == null || "".equals(s);
	}
	
}