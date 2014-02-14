package test;

import java.io.File;
import java.io.IOException;

import coffee.tools.CoffeeFileTools;
import coffee.tools.ZipHelper;

public class TestZipHelper {

	public static void main(String[] args) {
		File root = new File("H:\\2 工具\\VM8\\安装包\\00000000000 系列\\推女郎(更新至19期)\\套图");
		String zipRoot = "D:\\zip\\";
		File[] noFiles = root.listFiles();

		String folderName = "";
		for (int i = 0; i < noFiles.length; i++) {
			folderName = noFiles[i].getName(); // 处理下文件名
			try {
				ZipHelper.ZIP(noFiles[i].getAbsolutePath(), zipRoot + folderName + ".zip");
				// 删除
				// CoffeeFileTools.deleteFolder(noFiles[i]);
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("有异常,文件夹名：" + noFiles[i].getName());
			}
			//if(i==100) break;
		}

	}
}
