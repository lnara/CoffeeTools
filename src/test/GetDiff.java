package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coffee.tools.CoffeeFileTools;
import coffee.tools.MD5FileUtil;

public class GetDiff {
	
	public static Map<Long , String> m = new HashMap<Long, String>();
	public static List<String> delFile = new ArrayList<String>();
	
	public static void findDeff(String pathS) throws IOException {
		List<File> list = CoffeeFileTools.getAllFileFromFolder(pathS);
		for(File f : list){
			Long l = f.length();
			if(m.containsKey(l)){
				System.out.println("相同文件 : " + m.get(l));
				delFile.add(f.getAbsolutePath()+"\r\n");
			} else {
				m.put(l, f.getAbsolutePath());
			}
		}
	}

	public static void main(String[] args) throws IOException {
//		GetDiff.findDeff("H:\\2 工具\\VM8\\安装包\\网拍");
//		GetDiff.findDeff("E:\\迅雷下载");
//		GetDiff.findDeff("D:\\VM8\\国新 part1");
//		CoffeeFileTools.write(new File("E:\\1.txt"), delFile);
		List<String> l = CoffeeFileTools.readFileByLine(new File("E:\\1.txt"));
		for(String temp : l){
			CoffeeFileTools.delete(temp);
		}
		
		
		
	}
}
