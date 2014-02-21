package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

import coffee.tools.CoffeeFileTools;
import coffee.tools.ZipImage;

public class ZipImageThread implements Runnable{
	
	private List<File> fileList;

	public ZipImageThread(List<File> fileList){
		this.fileList = fileList;
	}
	
	@Override
	public void run() {

		String sourceFolderStr = "E:\\1\\国内 网络 火热 援交 3P 多P 应有尽有"; // 源目录
		String targetFolderStr = "E:\\afterZIP\\333333333333\\";
		
		File sourceFolder = new File(sourceFolderStr);
		File targetFolder = new File(targetFolderStr);
		
		if(!sourceFolder.exists()){
			System.out.println("源文件夹不存在，请检查！");
		} 
		if(!targetFolder.exists()) {
			System.out.println("目标文件夹不存在，创建中。。。。。");
			CoffeeFileTools.createFolder(targetFolder);
			System.out.println("目标文件夹不存在，创建成功!");
		}
		
		File temp;
		for(int i=0;i<fileList.size();i++){
			temp = fileList.get(i);
			// System.out.println(Thread.currentThread().getName() + " : 正在处理  -- " + temp.getAbsolutePath());
			String newPath = targetFolderStr + "\\"+temp.getParentFile().getName()+"\\" ;
			
			if(!CoffeeFileTools.fileExist(newPath))
				CoffeeFileTools.createFolder(newPath);
			
			try {
				ZipImage.imageZip(temp, new File(newPath+ temp.getName()), null, 800, 600, 1.0f);
			} catch (IOException e) {
				System.out.println("遇到不能正常处理的");
				// 有些图片 用 ImageIO读取时会抛异常
				// http://feicer.iteye.com/blog/803138
				try { 
					File srcImageFileGood = new File("D:\\tempImageFolder\\" + temp.getParentFile().getName()+"\\"+temp.getName()); 
					if(!CoffeeFileTools.fileExist(srcImageFileGood))
						CoffeeFileTools.createFolder(srcImageFileGood);
						
					JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new FileInputStream(temp));   
					BufferedImage image;
					image = decoder.decodeAsBufferedImage();
					ImageIO.write(image, "JPEG", srcImageFileGood);  
					
					ZipImage.imageZip(srcImageFileGood, new File(newPath+ temp.getName()), null, 800, 600, 0.7f);
				} catch (ImageFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		System.out.println("操作成功");
	}
}
