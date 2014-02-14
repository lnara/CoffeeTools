package test;

import java.io.File;

import coffee.tools.CoffeeFileTools;

public class Temp {
	public static void main(String[] args) {
		String rootPath = "D:\\0 整理\\0 系列\\BeautyLeg\\BeautyLeg-1-900";
		String newRootPath = "D:\\0 整理\\0 系列\\BeautyLeg\\1-900缩略图\\";
		if(!new File(newRootPath).exists())
			CoffeeFileTools.createFolder(newRootPath);
		
		File[] folders = new File(rootPath).listFiles();
		
		for(File f : folders){
			File[] ff = f.listFiles();
			
			for(File q : ff){
				CoffeeFileTools.copyFile(q.getAbsolutePath(), newRootPath+q.getName());
			}
			
		}
	}
}
