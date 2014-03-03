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

	public static void main(String[] args) {
		Map<String, Integer> m = new HashMap<String, Integer>();

//		String f = "D:\\1";
//
//		List<File> list = CoffeeFileTools.getAllFileFromFolder(f);
//		List<String> delList = new ArrayList<String>();
//		try {
//			for (File ff : list) {
//
//				String md5Str;
//				md5Str = MD5FileUtil.getFileMD5String(ff);
//				System.out.println("fuck" + md5Str);
//				if (m.containsKey(md5Str)) {
//					delList.add(ff.getAbsolutePath()+"\r\n");
//				} else {
//					m.put(md5Str, 1);
//				}
//			}
			
//			list = new ArrayList<File>();
//			for (String fff : delList) {
//				System.out.println(fff);
//				CoffeeFileTools.delete(fff);
//			}
//			CoffeeFileTools.write("D:\\abc.txt", delList);
		//	
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		List<String> l = CoffeeFileTools.readFileByLine(new File("D:\\abc.txt"), 1, 0, "UTF-8");
		
		for(String s : l){
			try {
				CoffeeFileTools.delete(s);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
}
