package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import coffee.tools.CoffeeFileTools;


public class ZipImageTest {


	public static void main(String[] args){
		String sourceFolderStr = "E:\\上传TEMP\\推女郎(更新至19期)\\版权图"; // 源目录
		
		List<File> fileList = new ArrayList<File>();
		CoffeeFileTools.getFilesFromFolder(sourceFolderStr, "*.jpg", fileList);
		
		List<File> l1 = new ArrayList<File>();
		List<File> l2 = new ArrayList<File>();
		List<File> l3 = new ArrayList<File>();
		List<File> l4 = new ArrayList<File>();
		
		for(int i=0;i<fileList.size();i++){
			int mod = i % 4;
			switch (mod) {
			case 0: l1.add(fileList.get(i)); break;
			case 1: l2.add(fileList.get(i)); break;
			case 2: l3.add(fileList.get(i)); break;
			case 3: l4.add(fileList.get(i)); break;
			}
		}
		
		ZipImageThread t1 = new ZipImageThread(l1);
		ZipImageThread t2 = new ZipImageThread(l2);
		ZipImageThread t3 = new ZipImageThread(l3);
		ZipImageThread t4 = new ZipImageThread(l4);
		
		Thread thread1 = new Thread(t1,"#第一小分队");
		Thread thread2 = new Thread(t2,"#第二小分队");
		Thread thread3 = new Thread(t3,"#第三小分队");
		Thread thread4 = new Thread(t4,"#第四小分队");
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
	}
	
	
}
